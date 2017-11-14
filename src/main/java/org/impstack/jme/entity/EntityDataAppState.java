package org.impstack.jme.entity;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.simsilica.es.EntityData;
import com.simsilica.es.base.DefaultEntityData;

/**
 * @author remy
 * @since 11/10/17.
 */
public class EntityDataAppState extends BaseAppState {

    private EntityData entityData;

    public EntityDataAppState() {
        this(new DefaultEntityData());
    }

    public EntityDataAppState(EntityData entityData) {
        this.entityData = entityData;
    }

    @Override
    protected void initialize(Application app) {
    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }

    @Override
    protected void cleanup(Application app) {
        this.entityData.close();
        this.entityData = null;
    }

    public EntityData getEntityData() {
        return entityData;
    }

}
