package com.minelife.core.init;

import com.minelife.core.MinelifeCoreContainer;
import com.minelife.core.asm_transformer.ASMHelper;
import com.minelife.core.asm_transformer.ASMNames;
import com.minelife.core.asm_transformer.AnnotationChecker;
import com.minelife.core.asm_transformer.TransformPlayerDismountCtrl;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin.DependsOn;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

import net.minecraft.entity.player.EntityPlayer;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;



import java.io.File;

import java.net.URISyntaxException;

import java.util.Map;


//
@SortingIndex( 1001 )

@MCVersion( MinelifeCoreLoadingPlugin.MC_VERSION )

@DependsOn( "forge" )

@TransformerExclusions( { "com.minelife.core.asm_transformer", "com.minelife.core.init" } )

public class MinelifeCoreLoadingPlugin implements IFMLLoadingPlugin
{

    public static final String MC_VERSION = "1.7.10";

    public static final String MOD_ID = "minelifecore";

    public static final Logger MOD_LOG = LogManager.getLogger(MOD_ID);

    public static final String MOD_VERSION = "2.6.0";



    public static File source;



    @Override

    public String getAccessTransformerClass() {

        return null;

    }



    @Override

    public String[] getASMTransformerClass() {

        return new String[] {

                TransformPlayerDismountCtrl.class.getName(),

                AnnotationChecker.class.getName() // KEEP THIS AS LAST

        };

    }



    @Override

    public String getModContainerClass() {

        return MinelifeCoreContainer.class.getName();

    }



    @Override

    public String getSetupClass() {

        return null;

    }



    /**

     * Code from diesieben07 in here.

     * Thanks ~SanAndreasP

     */

    @Override

    public void injectData(Map<String, Object> data) {

        ASMHelper.isMCP = !(Boolean) data.get("runtimeDeobfuscationEnabled");



        source = (File) data.get("coremodLocation");

        if( source == null ) {          // this is usually in a dev env

            try {

                source = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());

                if( !(new File(source, "assets")).exists() ) {          // fix for IntelliJ

                    source = new File(source.getParentFile().getParentFile(), "resources/main");

                }

            } catch( URISyntaxException e ) {

                throw new RuntimeException("Failed to acquire source location for MinelifeCoreContainer!", e);

            }

        }

    }

}