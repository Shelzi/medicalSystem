package com.epam.medicalsystem.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("property.messages",
                    Locale.US);

    // класс извлекает информацию из файла messages_en_US.properties
    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
