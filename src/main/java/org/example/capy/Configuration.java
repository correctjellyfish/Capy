package org.example.capy;

import com.googlecode.lanterna.TextColor;

public class Configuration {
    private static Configuration INSTANCE;

    public TextColor foreground;
    public TextColor background;
    public int tabSize;

    private Configuration() {
        // Currently just sets some defaults, this will be changed to being read from a
        // file
        this.foreground = TextColor.Indexed.fromRGB(205, 214, 244);
        this.background = TextColor.Indexed.fromRGB(30, 30, 46);
        this.tabSize = 4;
    }

    public static Configuration getConfig() {
        if (INSTANCE == null) {
            INSTANCE = new Configuration();
        }
        return INSTANCE;
    }
}
