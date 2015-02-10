package Sandbox.ZipExtractor;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class GenericSettings {
    private final static Logger logger            = Logger.getLogger(GenericSettings.class.getName());
    public static final String  GLOBAL_UTF        = "UTF-8";
    public static final String  INPUT_START_REGEX = "\\A";

    public static String getPropertyValue(final String name) {
        if (StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name)) {
            InputStream inputStream = GenericSettings.class.getClassLoader().getResourceAsStream("configuration.properties");
            Properties properties = new Properties();

            try {
                properties.load(inputStream);
            } catch (IOException ex) {
                logger.severe(ex.toString());
            }

            return properties.getProperty(name);
        }

        return null;
    }
}