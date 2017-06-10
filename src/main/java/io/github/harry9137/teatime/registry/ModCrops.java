package io.github.harry9137.teatime.registry;

import io.github.harry9137.teatime.TeaTime;
import io.github.harry9137.teatime.block.*;
import io.github.harry9137.teatime.item.*;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLLog;

import java.util.HashMap;

import static net.minecraftforge.fml.common.registry.GameRegistry.*;

public class ModCrops {
    public static HashMap<BlockCropBase, Item> seedsMap = new HashMap<BlockCropBase, Item>();
    public static HashMap<BlockCropBase, Item> harvestItemMap = new HashMap<BlockCropBase, Item>();

    public static BlockCropBase TeaBlock;
    public static Item TeaSeed;
    public static Item TeaLeaf;

    public static void init(){
        TeaBlock = registerCrop(new BlockTea());
        TeaSeed = registerSeed(TeaBlock, new ItemTeaSeed(TeaBlock));
        TeaLeaf = registerHarvestItem(TeaBlock, new ItemTeaLeaf());
    }

    private static BlockCropBase registerCrop(BlockCropBase crop){
        if(crop.getRegistryName() == null){
            FMLLog.bigWarning("You forgot to give " + crop.getClass().toString() + " a registry name.");
            return null;
        }
        register(crop);
        return crop;
    }

    private static Item registerSeed(BlockCropBase crop, Item item){
        if(item.getRegistryName() == null){
            FMLLog.bigWarning("You forgot to give " + item.getClass().toString() + " a registry name.");
            return null;
        }
        seedsMap.put(crop, item);
        register(item);

        TeaTime.proxy.registerItemModel(item, 0);

        return item;
    }

    private static Item registerHarvestItem(BlockCropBase crop, Item item){
        if(item.getRegistryName() == null){
            FMLLog.bigWarning("You forgot to give " + item.getClass().toString() + " a registry name.");
            return null;
        }
        harvestItemMap.put(crop, item);
        register(item);

        TeaTime.proxy.registerItemModel(item, 0);

        return item;
    }
}
