package br.jus.tjsp.movjud.core.util;


public class AppBundleProperties {
    
    private static final String BUNDLE_NAME = "AppBundle";
    private static final BundleProperties BP = new BundleProperties(BUNDLE_NAME);

    private AppBundleProperties() {
        
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
