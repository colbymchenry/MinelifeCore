package com.minelife.core.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.Entity;

public class EntityDismountEvent extends Event {

    public Entity entity;

    public EntityDismountEvent(Entity entity) {
        this.entity = entity;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
