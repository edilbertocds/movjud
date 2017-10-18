package br.jus.tjsp.movjud.business.formulario.types;

public enum TipoCampoType {
    
    DATA("Data","Campo de Data", "DAT", "img.campoDataOn", "img.campoDataOff"),
    FORMULA("Fórmula","Expressão utilizada para calcular valores, baseado em campos existentes", "FOR", "img.campoFormulaOn", "img.campoFormulaOff"),
    LISTA("Lista de Seleção","Lista de seleção única, baseada em opções definidas pelo usuário", "LIS", "img.campoListaOn", "img.campoListaOff"),
    NUMERO("Número","Campo numérico", "NUM", "img.campoNumeroOn", "img.campoNumeroOff"),
    TEXTO("Texto","Campo do tipo texto", "TXT", "img.campoTextoOn", "img.campoTextoOff"),
    TITULO("Título","Texto de destaque, dentro de um grupo", "TIT", "img.campoTituloOn", "img.campoTituloOff"),
    LABEL("Label","Campo label, com formatação padrão", "LAB", "img.campoLabelOn", "img.campoLabelOff");

    private String labelTipoCampo;
    private String hintTipoCampo;
    private String valoTipoCampo;
    private String imagemOn;
    private String imagemOff;
    
    TipoCampoType(String labelTipoCampo, String hintTipoCampo, String valoTipoCampo, String imagemOn, String imagemOff) {
        this.labelTipoCampo = labelTipoCampo;
        this.hintTipoCampo = hintTipoCampo;
        this.valoTipoCampo = valoTipoCampo;
        this.imagemOn = imagemOn;
        this.imagemOff = imagemOff;
    }

    public void setLabelTipoCampo(String labelTipoCampo) {
        this.labelTipoCampo = labelTipoCampo;
    }

    public String getLabelTipoCampo() {
        return labelTipoCampo;
    }

    public void setHintTipoCampo(String hintTipoCampo) {
        this.hintTipoCampo = hintTipoCampo;
    }

    public String getHintTipoCampo() {
        return hintTipoCampo;
    }

    public void setValoTipoCampo(String valoTipoCampo) {
        this.valoTipoCampo = valoTipoCampo;
    }

    public String getValoTipoCampo() {
        return valoTipoCampo;
    }

    public void setImagemOn(String imagemOn) {
        this.imagemOn = imagemOn;
    }

    public String getImagemOn() {
        return imagemOn;
    }

    public void setImagemOff(String imagemOff) {
        this.imagemOff = imagemOff;
    }

    public String getImagemOff() {
        return imagemOff;
    }

    public static TipoCampoType retornarTipoCampoEnum(String valorTipoCampo){
        if(DATA.valoTipoCampo.equals(valorTipoCampo)){
            return DATA;
        }else if(FORMULA.valoTipoCampo.equals(valorTipoCampo)){
            return FORMULA;
        }else if(LISTA.valoTipoCampo.equals(valorTipoCampo)){
            return LISTA;
        }else if(NUMERO.valoTipoCampo.equals(valorTipoCampo)){
            return NUMERO;
        }else if(TEXTO.valoTipoCampo.equals(valorTipoCampo)){
            return TEXTO;
        }else if(TITULO.valoTipoCampo.equals(valorTipoCampo)){
            return TITULO;
        }else if(LABEL.valoTipoCampo.equals(valorTipoCampo)){
            return LABEL;
        }
        return null;
    }

}
