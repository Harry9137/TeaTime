package io.github.harry9137.teatime.worldgen;

import io.github.harry9137.teatime.block.BlockCropBase;
import io.github.harry9137.teatime.registry.ModCrops;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

/**
 * A modified version of {@link net.minecraft.world.gen.structure.StructureVillagePieces.Field2}
 */
public class TeaField extends StructureVillagePieces.Village
{
    public TeaField()
    {
    }

    public TeaField(StructureVillagePieces.Start start, int type, StructureBoundingBox boundingBox, EnumFacing facing)
    {
        super(start, type);
        this.setCoordBaseMode(facing);
        this.boundingBox = boundingBox;
    }

    public static TeaField createPiece(StructureVillagePieces.Start start, List<StructureComponent> structureComponents, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType)
    {
        StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, 0, 0, 0, 7, 4, 9, facing);
        return canVillageGoDeeper(structureBoundingBox) && StructureComponent.findIntersecting(structureComponents, structureBoundingBox) == null ? new TeaField(start, componentType, structureBoundingBox, facing) : null;
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox)
    {
        if (this.averageGroundLvl < 0)
        {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBox);

            if (this.averageGroundLvl < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
        }

        IBlockState log = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
        this.fillWithBlocks(world, structureBoundingBox, 0, 1, 0, 6, 4, 8, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(world, structureBoundingBox, 1, 0, 1, 2, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
        this.fillWithBlocks(world, structureBoundingBox, 4, 0, 1, 5, 0, 7, Blocks.FARMLAND.getDefaultState(), Blocks.FARMLAND.getDefaultState(), false);
        this.fillWithBlocks(world, structureBoundingBox, 0, 0, 0, 0, 0, 8, log, log, false);
        this.fillWithBlocks(world, structureBoundingBox, 6, 0, 0, 6, 0, 8, log, log, false);
        this.fillWithBlocks(world, structureBoundingBox, 1, 0, 0, 5, 0, 0, log, log, false);
        this.fillWithBlocks(world, structureBoundingBox, 1, 0, 8, 5, 0, 8, log, log, false);
        this.fillWithBlocks(world, structureBoundingBox, 3, 0, 1, 3, 0, 7, Blocks.WATER.getDefaultState(), Blocks.WATER.getDefaultState(), false);

        for (int z = 1; z <= 7; ++z)
        {
            int maxAge = ModCrops.TeaBlock.getMaxAge();
            int minAge = maxAge / 3;
            this.setBlockState(world, ModCrops.TeaBlock.getStateFromMeta(MathHelper.getRandomIntegerInRange(random, minAge, maxAge)), 1, 1, z, structureBoundingBox);
            this.setBlockState(world, ModCrops.TeaBlock.getStateFromMeta(MathHelper.getRandomIntegerInRange(random, minAge, maxAge)), 2, 1, z, structureBoundingBox);
            this.setBlockState(world, ModCrops.TeaBlock.getStateFromMeta(MathHelper.getRandomIntegerInRange(random, minAge, maxAge)), 4, 1, z, structureBoundingBox);
            this.setBlockState(world, ModCrops.TeaBlock.getStateFromMeta(MathHelper.getRandomIntegerInRange(random, minAge, maxAge)), 5, 1, z, structureBoundingBox);
        }

        for (int z = 0; z < 9; ++z)
        {
            for (int x = 0; x < 7; ++x)
            {
                this.clearCurrentPositionBlocksUpwards(world, x, 4, z, structureBoundingBox);
                this.replaceAirAndLiquidDownwards(world, Blocks.DIRT.getDefaultState(), x, -1, z, structureBoundingBox);
            }
        }

        return true;
    }
}

