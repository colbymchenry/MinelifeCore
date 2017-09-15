package com.minelife.core;


import com.google.common.eventbus.EventBus;

import com.minelife.core.init.MinelifeCoreLoadingPlugin;
import cpw.mods.fml.client.FMLFileResourcePack;

import cpw.mods.fml.client.FMLFolderResourcePack;

import cpw.mods.fml.common.DummyModContainer;

import cpw.mods.fml.common.LoadController;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;

import cpw.mods.fml.common.event.*;


import cpw.mods.fml.common.network.NetworkRegistry;

import cpw.mods.fml.relauncher.FMLInjectionData;



import org.apache.logging.log4j.Level;



import java.io.File;


import java.util.Collections;

public class MinelifeCoreContainer extends DummyModContainer {


    public static final int FORGE_BULD_MIN = 1448;


    public MinelifeCoreContainer() {

        super(new ModMetadata());

        ModMetadata meta = super.getMetadata();

        meta.modId = MinelifeCoreLoadingPlugin.MOD_ID;

        meta.name = "MinelifeCore";

        meta.version = MinelifeCoreLoadingPlugin.MOD_VERSION;

        meta.authorList = Collections.singletonList("colby_mchenry");

        meta.description = "The coremod needed for Minelife.";

//        meta.url = "http://www.minecraftforge.net/forum/index.php/topic,2828.0.html";

        meta.screenshots = new String[0];

        meta.logoFile = "";

    }


    @Override

    public boolean registerBus(EventBus bus, LoadController controller) {

        bus.register(this);

        return true;

    }


    @Mod.EventHandler

    public void modConstruction(FMLConstructionEvent event) {

        if (Integer.parseInt(FMLInjectionData.data()[3].toString()) < FORGE_BULD_MIN) {

            MinelifeCoreLoadingPlugin.MOD_LOG.log(Level.FATAL, "The installed version of Forge is outdated! Minimum build required is %d, installed is build %s. " +

                            "Either update Forge or remove this mod, it will cause problems otherwise!", FORGE_BULD_MIN,

                    FMLInjectionData.data()[3]);

        }


        NetworkRegistry.INSTANCE.register(this, this.getClass(), null, event.getASMHarvestedData());
        System.out.println("FUCK");

    }

    @Mod.EventHandler

    public void preInit(FMLPreInitializationEvent evt)

    {
        System.out.println("CALLED");


    }


    @Override

    public File getSource() {

        return MinelifeCoreLoadingPlugin.source;

    }


    @Override

    public Class<?> getCustomResourcePackClass() {

        return getSource().isDirectory() ? FMLFolderResourcePack.class : FMLFileResourcePack.class;

    }

}