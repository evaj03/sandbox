package Sandbox.utils;

import java.util.logging.Logger;

public class GenericSettings {
    private final static Logger logger            = Logger.getLogger(GenericSettings.class.getName());
    public static final String  GLOBAL_UTF        = "UTF-8";
    public static final String  INPUT_START_REGEX = "\\A";

//    public static String getPropertyValue(final String name) {
//        if (StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name)) {
//            // Is Unix like system (AIX, HP-UX, Irix, Linux, MacOSX, Solaris or SUN OS
//            if (SystemUtils.IS_OS_UNIX) {
//                return getLinxProperty(name);
//            }
//            // Assume Windose
//            else {
//                return getWindowsProperty(name);
//            }
//        }
//
//        return null;
//    }


//    private static String getLinxProperty(final String name) {
//        InputStream inputStream = GenericSettings.class.getClassLoader().getResourceAsStream("OS_UNIX_Configuration.properties");
//        Properties  properties  = new Properties();
//
//        try {
//            properties.load(inputStream);
//        } catch (IOException ex) {
//            logger.severe(ex.toString());
//        }
//
//        return properties.getProperty(name);
//    }
//
//
//    private static String getWindowsProperty(final String name) {
//        InputStream inputStream = GenericSettings.class.getClassLoader().getResourceAsStream("OS_WINDOWS_Configuration.properties");
//        Properties  properties  = new Properties();
//
//        try {
//            properties.load(inputStream);
//        } catch (IOException ex) {
//            logger.severe(ex.toString());
//        }
//
//        return properties.getProperty(name);
//    }
}
