package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;
import br.jus.tjsp.movjud.business.formulario.types.ProcessoConclusoType;

public class TipoConclusoDTO extends BaseDTO<Long>{
    
    private Long idTipoProcessoConcluso;
    
    private String descricaoTipoProcessoConcluso;
    
    private ProcessoConclusoType tipoProcessoConcluso;
    
    public TipoConclusoDTO() {
        super();
    }

    @Override
    public void setId(Long id) {
        setIdTipoProcessoConcluso(id);
    }

    @Override
    public Long getId() {
        return getIdTipoProcessoConcluso();
    }


    public void setIdTipoProcessoConcluso(Long idTipoProcessoConcluso) {
        this.idTipoProcessoConcluso = idTipoProcessoConcluso;
    }

    public Long getIdTipoProcessoConcluso() {
        return idTipoProcessoConcluso;
    }

    public void setDescricaoTipoProcessoConcluso(String descricaoTipoProcessoConcluso) {
        this.descricaoTipoProcessoConcluso = descricaoTipoProcessoConcluso;
    }

    public String getDescricaoTipoProcessoConcluso() {
        return descricaoTipoProcessoConcluso;
    }

    public void setTipoProcessoConcluso(ProcessoConclusoType tipoProcessoConcluso) {
        this.tipoProcessoConcluso = tipoProcessoConcluso;
    }

    public ProcessoConclusoType getTipoProcessoConcluso() {
        return tipoProcessoConcluso;
    }
}
