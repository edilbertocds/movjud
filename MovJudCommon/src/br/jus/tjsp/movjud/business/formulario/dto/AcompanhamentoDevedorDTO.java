package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

public class AcompanhamentoDevedorDTO extends BaseDTO<String>{
    public AcompanhamentoDevedorDTO() {
        super();
    }
    private String unidadeNome;
    private String foroNome;
    private String formulariosPendentes;
    @Override
    public void setId(String id) {
    }

    @Override
    public String getId() {
        return foroNome+"-"+unidadeNome;
    }

    public void setUnidadeNome(String unidadeNome) {
        this.unidadeNome = unidadeNome;
    }

    public String getUnidadeNome() {
        return unidadeNome;
    }

    public void setForoNome(String foroNome) {
        this.foroNome = foroNome;
    }

    public String getForoNome() {
        return foroNome;
    }

    public void setFormulariosPendentes(String formulariosPendentes) {
        this.formulariosPendentes = formulariosPendentes;
    }

    public String getFormulariosPendentes() {
        return formulariosPendentes;
    }
}
