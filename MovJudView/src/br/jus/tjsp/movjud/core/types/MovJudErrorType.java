package br.jus.tjsp.movjud.core.types;

import br.jus.tjsp.movjud.core.util.AppBundleProperties;

public enum MovJudErrorType {
    
    ERRO_BANCO_UK("ORA-00001", AppBundleProperties.getString("msg.exception.registrounico")),
    ERRO_BANCO_RELACIONAMENTO("ORA-02292", AppBundleProperties.getString("msg.exception.erroExclusaoRelacionamento")),
    ERRO_BANCO_NOTNULL("ORA-01400", AppBundleProperties.getString("msg.exception.notnull")),
    ERRO_INESPERADO("ERRO_INESPERADO", AppBundleProperties.getString("msg.exception.erroinesperado"));
    
    MovJudErrorType(String codigoErro, String mensagenErro){
        this.codigoErro = codigoErro;
        this.mensagenErro = mensagenErro;
    }
    
    public static MovJudErrorType getErroPorMensagem(String mensagemErro) {
        MovJudErrorType erro = null;
        if (mensagemErro != null) {
            for (MovJudErrorType erroItem : values()) {
                if (mensagemErro.toUpperCase().contains(erroItem.getCodigoErro().toUpperCase())) {
                    erro = erroItem;
                    break;
                }
            }
            if(erro==null){
                erro = MovJudErrorType.ERRO_INESPERADO;
            }
        }
        return erro;
    }
    
    private String codigoErro;
    private String mensagenErro;


    public void setCodigoErro(String codigoErro) {
        this.codigoErro = codigoErro;
    }

    public String getCodigoErro() {
        return codigoErro;
    }

    public void setMensagenErro(String mensagenErro) {
        this.mensagenErro = mensagenErro;
    }

    public String getMensagenErro() {
        return mensagenErro;
    }
}
