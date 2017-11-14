package org.impstack.jme.config;

import com.jme3.system.AppSettings;
import org.impstack.awt.Resolution;
import org.impstack.awt.ResolutionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * @author remy
 * @since 29/03/17.
 */
public final class ApplicationSettings {

    private final Class<?> clazz;
    private final Logger LOG = LoggerFactory.getLogger(ApplicationSettings.class);
    private final String RESET_TRIGGER = "resetPreferences";
    private final String FULLSCREEN_TRIGGER = "fullscreen";
    private final boolean VSYNC = true;
    private final boolean GAMMA_CORRECTION = true;
    private final int FRAMERATE = 60;
    private final int ANTI_ALIASING = 2;
    private final String RENDERER = AppSettings.LWJGL_OPENGL2;
    private final String WIDTH = "width";
    private final String HEIGHT = "height";
    private final String BITS_PER_PIXEL = "bitsPerPixel";
    private final Resolution defaultResolution;
    private boolean FULLSCREEN = false;

    public ApplicationSettings(Class<?> clazz) {
        this(clazz, ResolutionHelper.INSTANCE.getBestWindowedResolution());
    }

    public ApplicationSettings(Class<?> clazz, Resolution defaultResolution) {
        this.clazz = clazz;
        this.defaultResolution = defaultResolution;
        if (System.getProperty(RESET_TRIGGER) != null) {
            reset();
        }
        if (System.getProperty(FULLSCREEN_TRIGGER) != null) {
            FULLSCREEN = true;
        }
    }

    public AppSettings load() {
        Preferences preferences = Preferences.userNodeForPackage(clazz);

        LOG.info("Loading application settings {}", preferences.absolutePath());

        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode displayMode = graphicsDevice.getDisplayMode();

        AppSettings settings = new AppSettings(true);
        settings.setFrequency(displayMode.getRefreshRate());
        settings.setTitle(ApplicationVersion.NAME + " - " + ApplicationVersion.VERSION + " build " + ApplicationVersion.BUILD + " " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.mmm").format(new Date(Long.parseLong(ApplicationVersion.BUILD_TIMESTAMP))));
        settings.setGammaCorrection(GAMMA_CORRECTION);
        settings.setFrameRate(FRAMERATE);
        settings.setVSync(VSYNC);
        settings.setSamples(ANTI_ALIASING);
        settings.setRenderer(RENDERER);
        if (FULLSCREEN) {
            settings.setWidth(preferences.getInt(WIDTH, displayMode.getWidth()));
            settings.setHeight(preferences.getInt(HEIGHT, displayMode.getHeight()));
            settings.setBitsPerPixel(preferences.getInt(BITS_PER_PIXEL, displayMode.getBitDepth()));
        } else {
            settings.setWidth(preferences.getInt(WIDTH, defaultResolution.getWidth()));
            settings.setHeight(preferences.getInt(HEIGHT, defaultResolution.getHeight()));
            settings.setBitsPerPixel(preferences.getInt(BITS_PER_PIXEL, defaultResolution.getBpp()));
        }
        settings.setFullscreen(FULLSCREEN);

        settings.entrySet().forEach(entry -> LOG.debug("{} -> {}", entry.getKey(), entry.getValue()));
        return settings;
    }

    public void save(AppSettings settings) {
        Preferences preferences = Preferences.userNodeForPackage(clazz);

        LOG.info("Saving application settings {}", preferences.absolutePath());

        preferences.putInt(WIDTH, settings.getWidth());
        preferences.putInt(HEIGHT, settings.getHeight());
        preferences.putInt(BITS_PER_PIXEL, settings.getBitsPerPixel());
        try {
            preferences.flush();
            Arrays.stream(preferences.keys()).forEach(key -> LOG.debug("{} -> {}", key, preferences.get(key, null)));
        } catch (BackingStoreException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void reset() {
        Preferences preferences = Preferences.userNodeForPackage(clazz);
        LOG.info("Resetting application settings {}", preferences.absolutePath());
        try {
            preferences.removeNode();
        } catch (BackingStoreException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
