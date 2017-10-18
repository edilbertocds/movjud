package br.jus.tjsp.movjud.persistence.base.types;

public enum AbrangenciaType {
    
    USUARIO_ESPECIFICO("USUARIO_ESPECIFICO"),
    PERFIL("PERFIL"),
    TODOS("TODOS"),
    USUARIO_ACAO("USUARIO_ACAO");
    
    private static final long serialVersionUID = 20160901100607L;
    
    private final String codigo;
    
    AbrangenciaType(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
}
