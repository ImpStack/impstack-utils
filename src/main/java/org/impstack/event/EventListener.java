package org.impstack.event;

/**
 * @author remy
 * @since 22/10/17.
 */
public interface EventListener<T extends Event> {

    void onEvent(T event);

}
