package br.jus.tjsp.movjud.business.formulario.types;

public enum ProcessoConclusoType {
    
    SENTENCA("SENTENCA"),
    DESPACHO("DESPACHO"),
    DECISAO("DECISAO"),
    NAO_INFORMADO("-1");
    
    private String descricaoProcessoConcluso;
    
    public static ProcessoConclusoType recuperarTipoProcessoConclusoPorCodigo(String codigo){
        ProcessoConclusoType processoConclusoType = null;
        for(ProcessoConclusoType processo : ProcessoConclusoType.values()){
            if(processo.getDescricaoProcessoConcluso().equals(codigo)){
                processoConclusoType = processo;
            }   
        }
        return processoConclusoType;
    }
    
    ProcessoConclusoType(String descricaoProcessoConcluso) {
        this.descricaoProcessoConcluso = descricaoProcessoConcluso;
    }

    public void setDescricaoProcessoConcluso(String descricaoProcessoConcluso) {
        this.descricaoProcessoConcluso = descricaoProcessoConcluso;
    }

    public String getDescricaoProcessoConcluso() {
        return descricaoProcessoConcluso;
    }
}
