package com.minelife.core.asm_transformer;

import com.minelife.core.event.EntityDismountEvent;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;

public class Hooks {

    public static boolean fireDismountEvent(Entity entity) {
        EntityDismountEvent event = new EntityDismountEvent(entity);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
            return false;
        else
            return true;
    }

}
