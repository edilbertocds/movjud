package br.jus.tjsp.movjud.business.formulario.types;


public enum TipoValidacaoType {
    VALIDACAO_ERRO("ERRO", "Erro"),
    VALIDACAO_AVISO("AVISO", "Aviso"),
    VALIDACAO_CONFIRMACAO("CONFIRMACAO", "Confirmação");

    String codigoValidacao;
    String descricaoValidacao;

    TipoValidacaoType(String codigoValidacao, String descricaoValidacao) {
        this.codigoValidacao = codigoValidacao;
        this.descricaoValidacao = descricaoValidacao;
    }

    public static TipoValidacaoType getTipoValidacaoByCodigo(String codigoValidacao) {
        TipoValidacaoType tipoValidacao = null;

        if (codigoValidacao != null) {
            for (TipoValidacaoType itemValidacao : values()) {
                if (itemValidacao.getCodigoValidacao().equals(codigoValidacao.toUpperCase().trim())) {
                    tipoValidacao = itemValidacao;
                    break;
                }
            }
        }
        return tipoValidacao;
    }

    public void setCodigoValidacao(String codigoValidacao) {
        this.codigoValidacao = codigoValidacao;
    }

    public String getCodigoValidacao() {
        return codigoValidacao;
    }

    public void setDescricaoValidacao(String descricaoValidacao) {
        this.descricaoValidacao = descricaoValidacao;
    }

    public String getDescricaoValidacao() {
        return descricaoValidacao;
    }
}
