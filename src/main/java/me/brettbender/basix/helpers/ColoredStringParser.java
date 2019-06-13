/*
 * Basix Bukkit Plugin
 * Copyright (C) 2019 Brett Bender
 */

package me.brettbender.basix.helpers;

public class ColoredStringParser {

    public static String parseColoredString(String str) {
        if (str == null) return null;
        return str.replaceAll("&([0-9a-f,rlomnk])", "\u00A7$1");
    }

}
