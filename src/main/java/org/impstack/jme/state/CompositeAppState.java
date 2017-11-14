package org.impstack.jme.state;

import com.jme3.app.Application;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An AppState that can have child AppStates. When the parent AppState is attached, all the child AppStates will
 * also be attached. Same thing happens when the parent AppState is detached, all the child AppStates will be
 * detached. When the parent AppState is paused, all the child AppStates will also be paused. When the parent
 * AppState is enabled, all child AppStates will be enabled. There is no check for the previous state of the child!
 *
 * Initialize is called after the stateAttached method is called. The
 * stateDetached method is called before the cleanup method.
 *
 * @author remy
 * @since 11/10/17.
 */
public class CompositeAppState extends BaseAppState {

    private static final Logger LOG = LoggerFactory.getLogger(CompositeAppState.class);

    List<AppState> childStates = new ArrayList<>();
    private boolean attached = false;

    public CompositeAppState(AppState... appStates) {
        this.childStates.addAll(Arrays.asList(appStates));
    }

    public void addChild(AppState appState) {
        childStates.add(appState);
        if (attached) {
            getStateManager().attach(appState);
        }
    }

    public void addChildren(AppState... appStates) {
        Arrays.stream(appStates).forEach(this::addChild);
    }

    public void removeChild(AppState appState) {
        childStates.remove(appState);
        if (attached) {
            getStateManager().detach(appState);
        }
    }

    public void removeChildren(AppState... appStates) {
        Arrays.stream(appStates).forEach(this::removeChild);
    }

    @Override
    public void stateAttached(AppStateManager stateManager) {
        childStates.forEach(appState -> {
            LOG.debug("Attaching {}", appState);
            stateManager.attach(appState);
        });
        attached = true;
    }

    @Override
    protected void initialize(Application app) {
    }

    @Override
    protected void onEnable() {
        childStates.forEach(appState -> {
            LOG.debug("Enabling {}", appState);
            appState.setEnabled(true);
        });
    }

    @Override
    protected void onDisable() {
        childStates.forEach(appState -> {
            LOG.debug("Disabling {}", appState);
            appState.setEnabled(false);
        });
    }

    @Override
    public void stateDetached(AppStateManager stateManager) {
        childStates.forEach(appState -> {
            LOG.debug("Detaching {}", appState);
            stateManager.detach(appState);
        });
        attached = false;
    }

    @Override
    protected void cleanup(Application app) {
    }

}
