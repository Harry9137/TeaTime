package io.github.harry9137.teatime.proxy;

import io.github.harry9137.teatime.registry.ModCrafting;
import io.github.harry9137.teatime.registry.ModCrops;
import io.github.harry9137.teatime.worldgen.TeaField;
import io.github.harry9137.teatime.worldgen.VillageHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        ModCrops.init();
    }

    public void init(FMLInitializationEvent event){
        VillagerRegistry.instance().registerVillageCreationHandler(new VillageHandler());
        MapGenStructureIO.registerStructureComponent(TeaField.class, "ViTea");

        ModCrafting.init();
    }

    public void postInit(FMLPostInitializationEvent event){

    }

    public void registerItemModel(Item item, int state){

    }
}
