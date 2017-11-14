package org.impstack.jme.entity;

import com.jme3.scene.Spatial;
import com.simsilica.es.EntityComponent;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

import java.util.Optional;

/**
 * @author remy
 * @since 2/11/17.
 */
public final class EntityUtils {

    public static final String ENTITY_ID_KEY = "ENTITY_ID";

    /**
     * Sets the unique identifier of the entity on the given spatial.
     *
     * @param spatial
     * @param entityId
     * @return
     */
    public static EntityId setEntityId(Spatial spatial, EntityId entityId) {
        spatial.setUserData(ENTITY_ID_KEY, entityId.getId());
        return entityId;
    }

    /**
     * Returns the EntityId for the given spatial.
     *
     * @param spatial
     * @return
     */
    public static EntityId getEntityId(Spatial spatial) {
        return new EntityId(spatial.getUserData(ENTITY_ID_KEY));
    }

    /**
     * Returns an optional of the EntityId for the given spatial. When the EntityId is not found on the given spatial,
     * it will look for an EntityId on the parent of the spatial, bubbling up until an EntityId is found.
     * @param spatial
     * @return
     */
    public static Optional<EntityId> findEntityId(Spatial spatial) {
        if (spatial == null) {
            return Optional.empty();
        }
        if (spatial.getUserDataKeys().contains(ENTITY_ID_KEY)) {
            return Optional.of(new EntityId(spatial.getUserData(ENTITY_ID_KEY)));
        } else {
            Optional<EntityId> entityId = findEntityId(spatial.getParent());
            if (entityId.isPresent()) {
                return entityId;
            }
        }
        return Optional.empty();
    }

    /**
     * Wraps the result of the retrieval of the EntityComponent for the EntityId in the given EntityData in an OptionaL
     * @param entityId : id of the entity
     * @param type : class of the component
     * @param entityData : data set of entities
     * @param <T>
     * @return
     */
    public static <T extends EntityComponent> Optional<T> getComponent(EntityId entityId, Class<T> type, EntityData entityData) {
        return Optional.ofNullable(entityData.getComponent(entityId, type));
    }

}
