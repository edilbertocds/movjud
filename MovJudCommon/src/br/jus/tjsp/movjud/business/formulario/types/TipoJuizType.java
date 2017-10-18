package br.jus.tjsp.movjud.business.formulario.types;

import java.util.Arrays;

public enum TipoJuizType {
    TITULAR("Titular", "TITULAR"),
    RESPONSAVEL_JUIZO("Responsável pelo Juízo", "RESP_JUIZ"),
    AUXILIAR("Auxiliar", "AUX"),
    VINCULO_PROCESSO("Vínculo ao Processo", "VINC_PROCESS"),
    SUBSTITUTO("Substituto", "SUBST");
    
    private String descricaoTipoJuiz;
    private String codigoTipoJuiz;
    
    TipoJuizType(String descricaoTipoJuiz, String codigoTipoJuiz) {
        this.descricaoTipoJuiz = descricaoTipoJuiz;
        this.codigoTipoJuiz = codigoTipoJuiz;
    }

    public static TipoJuizType recuperarTipoJuizPorCodigo(String codigo){
        TipoJuizType retorno = null;
        for(TipoJuizType tipoJuiz : Arrays.asList(TipoJuizType.values())){
            if(tipoJuiz.getCodigoTipoJuiz().equals(codigo)){
                retorno = tipoJuiz;
            }
        }
        return retorno;
    }

    public void setDescricaoTipoJuiz(String descricaoTipoJuiz) {
        this.descricaoTipoJuiz = descricaoTipoJuiz;
    }

    public String getDescricaoTipoJuiz() {
        return descricaoTipoJuiz;
    }


    public void setCodigoTipoJuiz(String codigoTipoJuiz) {
        this.codigoTipoJuiz = codigoTipoJuiz;
    }

    public String getCodigoTipoJuiz() {
        return codigoTipoJuiz;
    }
}
