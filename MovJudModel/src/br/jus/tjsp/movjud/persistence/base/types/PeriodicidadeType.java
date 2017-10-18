package br.jus.tjsp.movjud.persistence.base.types;

public enum PeriodicidadeType {
    
    UMA_VEZ("UMA_VEZ"),
    DIARIO("DIARIO"),
    SEMANAL("SEMANAL"),
    DIAS_ANTES_ACAO("DIAS_ANTES_ACAO"),
    DATA_ESPECIFICA("DATA_ESPECIFICA");
    
    private static final long serialVersionUID = 20160901101007L;
    
    private final String codigo;
    
    PeriodicidadeType(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
}
