package br.jus.tjsp.movjud.persistence.base.types;

public enum VariavelType {

    ANO_ATUAL("$ANO_ATUAL","[$]ANO_ATUAL"),
    DATA_ATUAL("$DATA_ATUAL","[$]DATA_ATUAL"),
    MES_ATUAL("$MES_ATUAL","[$]MES_ATUAL"),
    OBS("$OBS","[$]OBS"),
    USUARIO("$USUARIO","[$]USUARIO");
    
    private static final long serialVersionUID = 20160901100607L;
    
    private final String codigo;
    
    private final String codigoRegex;
    
    VariavelType(String codigo, String codigoRegex) {
        this.codigo = codigo;
        this.codigoRegex = codigoRegex;
    }
    
    public String getCodigo() {
        return this.codigo;
    }

    public String getCodigoRegex() {
        return codigoRegex;
    }
}
