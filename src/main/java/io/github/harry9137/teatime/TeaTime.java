package io.github.harry9137.teatime;

import io.github.harry9137.teatime.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TeaTime.MODID, version = TeaTime.VERSION, name = TeaTime.NAME)
public class TeaTime {

    public static final String MODID = "teatime";
    public static final String NAME = "Tea Time";
    public static final String VERSION = "1.10.2-0.0.1.0";

    @SidedProxy(clientSide = "io.github.harry9137.teatime.proxy.ClientProxy", serverSide = "io.github.harry9137.teatime.proxy.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }

}
