package org.impstack.jme.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @author remy
 * @since 12/02/17
 */
public final class ApplicationVersion {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationVersion.class);
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(ApplicationVersion.class.getResourceAsStream("/version.properties"));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static String NAME = properties.getProperty("name", "");
    public static String VERSION = properties.getProperty("version", "");
    public static String BUILD = properties.getProperty("build", "");
    public static String BUILD_TIMESTAMP = properties.getProperty("build.timestamp", "");

}
