package br.jus.tjsp.movjud.business.formulario.types;


public enum SecaoType {
    DADOS_UNIDADES("SU", "Produtividade da Unidade", 1L),
    MAGISTRADO("M", "Magistrado", 2L),
    ESTABELECIMENTOS_PRISIONAIS("ES", "Estabelecimentos Prisionais", 3L),
    MATERIA("MA", "Matérias", 4L),
    REUS("R", "Réus", 5L);
    
    private String codigoSecao;
    private String labelSecao;
    private Long ordemSecao;

    SecaoType(String codigoSecao, String labelSecao, Long ordemSecao) {
        this.codigoSecao = codigoSecao;      
        this.labelSecao = labelSecao;
        this.ordemSecao = ordemSecao;
    }

    public void setCodigoSecao(String codigoSecao) {
        this.codigoSecao = codigoSecao;
    }

    public String getCodigoSecao() {
        return codigoSecao;
    }


    public void setLabelSecao(String labelSecao) {
        this.labelSecao = labelSecao;
    }

    public String getLabelSecao() {
        return labelSecao;
    }

    public void setOrdemSecao(Long ordemSecao) {
        this.ordemSecao = ordemSecao;
    }

    public Long getOrdemSecao() {
        return ordemSecao;
    }
    
    public static SecaoType getSecaoByCodigo(String codigo) {
        SecaoType secao = null;
        if (codigo != null) {
            for (SecaoType secaoItem : values()) {
                if (secaoItem.getCodigoSecao().equals(codigo.toUpperCase().trim())) {
                    secao = secaoItem;
                    break;
                }
            }
        }
        return secao;
    }

}
