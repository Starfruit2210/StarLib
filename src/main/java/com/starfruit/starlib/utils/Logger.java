package com.starfruit.starlib.utils;

import com.starfruit.starlib.StarLib;

public class Logger {

    public static void info(String... messages) {
        for (String message : messages) {
            StarLib.instance().getLogger().info(message);
        }
    }

    public static void warning(String... messages) {
        for (String message : messages) {
            StarLib.instance().getLogger().warning(message);
        }
    }

    public static void severe(String... messages) {
        for (String message : messages) {
            StarLib.instance().getLogger().severe(message);
        }
    }

    public static void finest(String... messages) {
        for (String message : messages) {
            StarLib.instance().getLogger().finest(message);
        }
    }
    
}
