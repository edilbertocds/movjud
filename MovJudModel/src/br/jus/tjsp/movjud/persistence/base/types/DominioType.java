package br.jus.tjsp.movjud.persistence.base.types;

public enum DominioType {
    
    CONFIGURACAO("Configuração"),
    CONTROLE_FORMULARIOS("Controle de Formulários"),
    ESTRUTURA_JUDICIARIA("Estrutura Judiciária"),
    FORMULARIOS("Formulários");
    
    private String nomeDominio;
    
    DominioType(String nomeDominio) {
        this.nomeDominio = nomeDominio;        
    }


    public void setNomeDominio(String nomeDominio) {
        this.nomeDominio = nomeDominio;
    }

    public String getNomeDominio() {
        return nomeDominio;
    }

}
