package com.epam.medicalsystem.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("locale.messages",
                    Locale.US);

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
