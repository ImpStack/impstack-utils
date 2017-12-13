package org.impstack.jme.state;

import com.simsilica.lemur.Panel;

/**
 * @author remy
 * @since 13/12/17.
 */
public interface DebugAppState {

    Panel getDebugPanel();

    default String getName() {
        return this.getClass().getSimpleName();
    }
}
