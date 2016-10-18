package io.github.harry9137.teatime.block;

import io.github.harry9137.teatime.registry.ModCrops;
import net.minecraft.block.*;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockCropBase extends BlockCrops {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);
    public final Block validBase;

    public BlockCropBase(Block validBase) {
        super();
        this.validBase = validBase;
        this.setDefaultState(blockState.getBaseState().withProperty(AGE, 0));
    }

    public boolean canPlant(Block block) {
        return block == validBase;
    }

    protected PropertyInteger getAge() {
        return AGE;
    }

    public int getHarvestReadyAge() {
        return 7;
    }

    public boolean isHarvestReady(IBlockState state) {
        return state.getValue(getAge()) >= getHarvestReadyAge();
    }

    protected Item getSeeds() {
        final Item seeds = ModCrops.seedsMap.get(this);

        if (seeds == null) {
            FMLLog.bigWarning("Couldn't find seeds for " + this.getLocalizedName());
            return new Item();
        }

        return seeds;
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(getSeeds());
    }

    @Override
    public boolean canGrow(World worl, BlockPos pos, IBlockState state, boolean isClient) {
        return !isHarvestReady(state);
    }

    protected Item getHarvestedItem() {
        final Item harvestedItem = ModCrops.harvestItemMap.get(this);
        if (harvestedItem == null) {
            FMLLog.bigWarning("Couldn't find drops for " + this.getLocalizedName());
            return new Item();
        }

        return harvestedItem;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(getAge(), meta);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rnd) {
        this.checkAndDropBlock(world, pos, state);

        if (world.getLightFromNeighbors(pos.up()) >= 9) {
            int i = this.getMetaFromState(state);

            if (i < this.getHarvestReadyAge()) {
                float f = getGrowthChance(this, world, pos);

                if (rnd.nextInt((int) (25.0F / f) + 1) == 0) {
                    world.setBlockState(pos, this.getStateFromMeta(i + 1), 2);
                }
            }
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rnd, int fortune) {
        if (!isHarvestReady(state)) {
            return getSeeds();
        } else {
            return getHarvestedItem();
        }
    }

    protected int getRandomInt(World world) {
        return MathHelper.getRandomIntegerInRange(world.rand, 1, 7);
    }

    @Override
    public void grow(World world, BlockPos pos, IBlockState state) {
        int newGrowth = getMetaFromState(state) + getRandomInt(world);
        int maxGrowth = getHarvestReadyAge();

        if (newGrowth > maxGrowth) {
            newGrowth = maxGrowth;
        }

        world.setBlockState(pos, getStateFromMeta(newGrowth), 2);
    }

    @Override
    public void grow(World world, Random rnd, BlockPos pos, IBlockState state) {
        grow(world, pos, state);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> retVal = new ArrayList<ItemStack>();

        int i = MathHelper.getRandomIntegerInRange(new Random(), 1, 3);

        if(isHarvestReady(state))
            retVal.add(new ItemStack(ModCrops.TeaLeaf, i, 0));
        else
            retVal.add(new ItemStack(ModCrops.TeaSeed, 1, 0));

        return retVal;
    }


}
