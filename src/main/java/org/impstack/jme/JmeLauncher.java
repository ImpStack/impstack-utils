package org.impstack.jme;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import org.impstack.jme.config.ApplicationContext;

/**
 * @author remy
 * @since 17/10/17.
 */
public abstract class JmeLauncher extends SimpleApplication {

    protected JmeLauncher() {
        super((AppState) null);
        ApplicationContext.INSTANCE.start(this);
    }

    protected abstract AppState getFirstAppState();

    @Override
    public void simpleInitApp() {
        ApplicationContext.INSTANCE.initialize(getFirstAppState());
    }

    @Override
    public void handleError(String errMsg, Throwable t) {
        ApplicationContext.INSTANCE.handleError(errMsg, t);
    }

    @Override
    public void destroy() {
        super.destroy();
        ApplicationContext.INSTANCE.cleanup();
    }

}
