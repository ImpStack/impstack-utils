package org.impstack.jme.config;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.scene.Node;
import com.simsilica.lemur.GuiGlobals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogManager;

/**
 * @author remy
 * @since 12/02/17
 */
public enum ApplicationContext {
    INSTANCE;

    private final Logger LOG = LoggerFactory.getLogger(ApplicationContext.class);

    private SimpleApplication app;
    private ApplicationSettings applicationSettings;
    private ScheduledExecutorService executorService;
    private boolean stopping = false;

    public void start(SimpleApplication application) {
        start(application, new ApplicationSettings(application.getClass()));
    }

    public void start(SimpleApplication application, ApplicationSettings settings) {
        LOG.info("Starting {} ...", ApplicationVersion.NAME);
        this.app = application;

        LogManager.getLogManager().getLogger("").setLevel(Level.ALL);
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        applicationSettings = settings;
        app.setSettings(applicationSettings.load());
        app.setShowSettings(false);
        app.setPauseOnLostFocus(false);

        setExecutorService(new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1));

        application.start();
        LOG.info("Starting {}... OK", ApplicationVersion.NAME);
    }

    public void initialize(AppState appState) {
        LOG.info("Initializing {} application context...", ApplicationVersion.NAME);
        // remove the default escape mapping
        app.getInputManager().deleteMapping(SimpleApplication.INPUT_MAPPING_EXIT);
        // initialize lemur globals, so that the default components can find what they need.
        GuiGlobals.initialize(app);
        // attach the first appstate
        app.getStateManager().attach(appState);
        LOG.info("Initializing {} application context... OK", ApplicationVersion.NAME);
    }

    public void stop() {
        app.stop();
    }

    public void cleanup() {
        if (!stopping) {
            stopping = true;

            LOG.info("Closing {} application context...", ApplicationVersion.NAME);
            if (executorService != null) {
                executorService.shutdown();
                try {
                    while (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                        LOG.info("Shutting down thread pool...");
                    }
                } catch (InterruptedException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            LOG.info("Closing {} application context... OK", ApplicationVersion.NAME);
        }
    }

    public void handleError(String message, Throwable t) {
        LOG.error(message, t);
        stop();
    }

    public Node getGuiNode() {
        return app.getGuiNode();
    }

    public Node getRootNode() {
        return app.getRootNode();
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    public SimpleApplication getApplication() {
        return app;
    }
}
