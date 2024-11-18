package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

class HydrothermalVentShaftBlock(settings: Settings?) :
    AbstractPlantBlock(settings, Direction.UP, SHAPE, true), FluidFillable {
    override fun getStem(): AbstractPlantStemBlock {
        return HybridAquaticBlocks.HYDROTHERMAL_VENT as AbstractPlantStemBlock
    }

    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getStill(false)
    }

    override fun canAttachTo(state: BlockState): Boolean {
        return state.isOf(Blocks.STONE) || state.isOf(Blocks.TUFF) || state.isOf(Blocks.MAGMA_BLOCK) || state.isOf(stem)|| super.canAttachTo(state)
    }

    override fun canFillWithFluid(world: BlockView, pos: BlockPos, state: BlockState, fluid: Fluid): Boolean {
        return false
    }

    override fun isFertilizable(world: WorldView?, pos: BlockPos?, state: BlockState?, isClient: Boolean): Boolean {
        return false
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        return COLLISION_SHAPE
    }

    override fun tryFillWithFluid(
        world: WorldAccess,
        pos: BlockPos,
        state: BlockState,
        fluidState: FluidState
    ): Boolean {
        return false
    }

    companion object {
        private val SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
        private val COLLISION_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
    }
}