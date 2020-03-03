package com.epam.osipchik.gym.controller.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceDispatcher {

    private static final String RESOURCE_FILE = "locale";
    private ResourceDispatcher() {
    }

    public static String getValueFromPropFile(String key, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_FILE,locale);
        return resourceBundle.getString(key);
    }
}
