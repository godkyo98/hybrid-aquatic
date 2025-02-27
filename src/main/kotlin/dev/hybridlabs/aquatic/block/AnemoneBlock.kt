package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.entity.fish.ClownfishEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.registry.tag.FluidTags
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties.WATERLOGGED
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.GameRules
import net.minecraft.world.World
import net.minecraft.world.WorldAccess

@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
class AnemoneBlock(settings: Settings) : PlantBlock(settings), BlockEntityProvider, Waterloggable {
    init {
        defaultState = stateManager.defaultState
            .with(WATERLOGGED, true)
    }

    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        if (entity is LivingEntity) {
            if (entity is ClownfishEntity) {
                if (!world.isClient) {
                    tryHideClownfish(entity, world, pos)
                }
            } else {
                entity.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 3 * 20, 1))
            }
        }
    }

    private fun tryHideClownfish(entity: ClownfishEntity, world: World, pos: BlockPos) {
        if (entity.isBaby || !entity.navigation.isIdle) {
            return
        }

        val blockEntity = world.getBlockEntity(pos)
        if (blockEntity is AnemoneBlockEntity) {
            if (blockEntity.hideClownfish(entity)) {
                entity.discard()
            }
        }
    }

    override fun onBreak(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity) {
        if (!world.isClient && player.isCreative && world.gameRules.getBoolean(GameRules.DO_TILE_DROPS)) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is AnemoneBlockEntity) {
                blockEntity.emergencyReleaseHiddenClownfish()
            }
        }

        super.onBreak(world, pos, state, player)
    }

    override fun canPlantOnTop(floor: BlockState, world: BlockView, pos: BlockPos): Boolean {
        return !floor.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty || floor.isSideSolidFullSquare(
            world,
            pos,
            Direction.UP
        )
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        return if (!canPlaceAt(state, world, pos)) {
            Blocks.AIR.defaultState
        } else super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos)
    }

    override fun <T : BlockEntity> getTicker(
        world: World,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return BlockWithEntity.checkType(type, HybridAquaticBlockEntityTypes.ANEMONE, AnemoneBlockEntity::tick)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return COLLISION_SHAPE
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return SHAPE
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val fluidState = ctx.world.getFluidState(ctx.blockPos)
        return if (fluidState.isIn(FluidTags.WATER)) defaultState.with(
            WATERLOGGED,
            ctx.world.getFluidState(ctx.blockPos).isOf(Fluids.WATER)
        ) else null
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return AnemoneBlockEntity(pos, state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return false
    }

    companion object {
        private val SHAPE = createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0)
        private val COLLISION_SHAPE = createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0)
    }
}
