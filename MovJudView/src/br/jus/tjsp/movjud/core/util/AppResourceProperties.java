package br.jus.tjsp.movjud.core.util;

import java.util.ResourceBundle;

public class AppResourceProperties{
    private static final String BUNDLE_NAME = "AppResource";
    private static final BundleProperties BP = new BundleProperties(BUNDLE_NAME);

    private AppResourceProperties() {
        
    }

    public static String getString(String key) {
        return BP.getString(key);
    }

    public static String getString(String key, String[] params) {
        return BP.getString(key, params);
    }

    public static String getStringFixa(String key, String[] params) {
        return BP.getStringFixa(key, params);
    }
}
