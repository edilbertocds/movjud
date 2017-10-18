package br.jus.tjsp.movjud.business.estruturajudiciaria.dto;


public class UnidadeDTO {
    public UnidadeDTO() {
	super();
    }
    
    private String nomeUnidade;
    
    private String nomeForo;
    
    private String formulariosPendentes;

    public void setNomeUnidade(String nomeUnidade) {
	this.nomeUnidade = nomeUnidade;
    }

    public String getNomeUnidade() {
	return nomeUnidade;
    }

    public void setNomeForo(String nomeForo) {
	this.nomeForo = nomeForo;
    }

    public String getNomeForo() {
	return nomeForo;
    }

    public void setFormulariosPendentes(String formulariosPendentes) {
	this.formulariosPendentes = formulariosPendentes;
    }

    public String getFormulariosPendentes() {
	return formulariosPendentes;
    }
}
