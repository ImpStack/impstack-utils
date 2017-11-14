package org.impstack.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author remy
 * @since 22/10/17.
 */
@SuppressWarnings("unchecked")
public enum EventBus {
    INSTANCE;

    private final Logger LOG = LoggerFactory.getLogger(EventBus.class);
    private final Map<Class<? extends Event>, List<EventListener>> listeners;

    private EventBus() {
        this.listeners = new HashMap<>();
    }

    public <T extends Event> void publish(T event) {
        LOG.trace("Publishing {}", event);
        getListenersForEvent(event.getClass()).forEach(listener -> listener.onEvent(event));
    }

    public void registerListener(EventListener<? extends Event> listener, Class<? extends Event> event) {
        getListenersForEvent(event).add(listener);
    }

    public void unregisterListener(EventListener<? extends Event> listener, Class<? extends Event> event) {
        getListenersForEvent(event).remove(listener);
    }

    private List<EventListener> getListenersForEvent(Class<? extends Event> event) {
        return listeners.computeIfAbsent(event, k -> new ArrayList<>());
    }
}
