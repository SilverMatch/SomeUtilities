package com.silvermatch.someutilities;

import net.minecraftforge.common.MinecraftForge;

import com.silvermatch.someutilities.event.KeepXPEventHandler;
import com.silvermatch.someutilities.init.ModBlocks;
import com.silvermatch.someutilities.init.ModTileEntities;
import com.silvermatch.someutilities.keepxp.KeepXP;
import com.silvermatch.someutilities.network.DescriptionHandler;
import com.silvermatch.someutilities.network.NetworkHandler;
import com.silvermatch.someutilities.proxy.CommonProxy;
import com.silvermatch.someutilities.reference.Reference;
import com.silvermatch.someutilities.utility.Log;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class SomeUtilities{
    @Mod.Instance(Reference.MOD_ID)
    public static SomeUtilities instance;
    public KeepXP keepXP;
    

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ModBlocks.init();
        ModTileEntities.init();
        proxy.preInit();
        //GameRegistry.registerWorldGenerator(new WorldGeneratorFlag(), 0);
        NetworkHandler.init();
        DescriptionHandler.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        
        keepXP = new KeepXP();
        MinecraftForge.EVENT_BUS.register(new KeepXPEventHandler());//For registering events from the net.miencraftforge.event package.
        FMLCommonHandler.instance().bus().register(new KeepXPEventHandler());//For registering events from the cpw.mods.fml.gameevent package.
        
        
        Log.info("Pre Initialization Complete!");
        
        
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init();
        Log.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit();
        Log.info("Post Initialization Complete!");
    }
}
