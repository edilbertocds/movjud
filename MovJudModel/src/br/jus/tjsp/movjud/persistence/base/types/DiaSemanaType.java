package br.jus.tjsp.movjud.persistence.base.types;

public enum DiaSemanaType {

    DOMINGO(1, "DOMINGO", "Domingo"),
    SEGUNDA_FEIRA(2, "SEGUNDA_FEIRA", "Segunda-Feira"),
    TERCA_FEIRA(3, "TERCA_FEIRA", "Terça-Feira"),
    QUARTA_FEIRA(4, "QUARTA_FEIRA", "Quarta-Feira"),
    QUINTA_FEIRA(5, "QUINTA_FEIRA", "Quinta-Feira"),
    SEXTA_FEIRA(6, "SEXTA_FEIRA", "Sexta-Feira"),
    SABADO(7, "SABADO", "Sábado");    
    
    private static final long serialVersionUID = 20160901101007L;
    
    private final Integer sequencia;
    
    private final String codigo;
    
    private final String nome;
    
    DiaSemanaType(Integer sequencia, String codigo, String nome) {
        this.sequencia = sequencia;
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getSequencia() {
        return sequencia;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    
    public String getNome() {
        return this.nome;
    }
}
