package org.impstack.jme.entity;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.simsilica.es.EntityData;

/**
 * @author remy
 * @since 11/10/17.
 */
public abstract class BaseEntityAppState extends BaseAppState {

    private EntityData entityData;

    @Override
    protected void initialize(Application app) {
        this.entityData = getState(EntityDataAppState.class).getEntityData();
        initialize();
    }

    protected abstract void initialize();

    public EntityData getEntityData() {
        return entityData;
    }

}
