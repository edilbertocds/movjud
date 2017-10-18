package br.jus.tjsp.movjud.persistence.base.types;

public enum AcaoType {
    ATUALIZAR("Atualizar"),
    EXCLUIR("Excluir"),
    INSERIR("Inserir");
    
    private String nomeAcao;
    
    AcaoType(String nomeAcao) {
        this.nomeAcao = nomeAcao;        
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }
}
