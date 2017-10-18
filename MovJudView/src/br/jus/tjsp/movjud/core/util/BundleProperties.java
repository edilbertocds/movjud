package br.jus.tjsp.movjud.core.util;

import java.util.ResourceBundle;

public class BundleProperties {

    private ResourceBundle resourceBundle;

    public BundleProperties(String BUNDLE_NAME) {
        this.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);    
    }

    public String getString(String key) {
        String msg = null;
        try {
            msg =  resourceBundle.getString(key);
        } catch (Exception e) {
            msg = "??? "+ key +" ???";   
        }
        return msg;
    }

    /**
     * Retorna uma string contida no arquivo de propriedades através da chave
     * passada e mesclando com o valor das chaves contidas no objeto params. Os
     * valores das chaves contidas no objeto params também são obtidos no
     * arquivo de propriedades.
     *
     * @param key
     * @param params
     * @return string
     */
    public String getString(String key, String... params) {
        String msg = null;
        try {
            msg = resourceBundle.getString(key);
            for (int i = 0; i < params.length; i++) {
                msg = msg.replace("{" + i + "}", resourceBundle.getString(params[i]));
            }
        } catch (Exception e) {
            msg = "??? "+ key +" ???";
        }
        return msg;
    }

    /**
     * Retorna uma string contida no arquivo de propriedades através da chave
     * passada e mesclando com as strings passadas como parámetro
     *
     * @param key
     * @param params
     * @return string
     */
    public String getStringFixa(String key, String... params) {
        String msg = null;
        try {
            msg = resourceBundle.getString(key);
            for (int i = 0; i < params.length; i++) {
                msg = msg.replace("{" + i + "}", params[i]);
            }
            return msg;
        } catch (Exception e) {
            msg = "??? "+ key +" ???";
        }
        return msg;
    }
}
