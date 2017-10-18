package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

public class MateriaDTO extends BaseDTO<Long>{
    private Long codigoArea;
    private String labelArea;

    public void setCodigoArea(Long codigoArea) {
        this.codigoArea = codigoArea;
    }

    public Long getCodigoArea() {
        return codigoArea;
    }

    public void setLabelArea(String labelArea) {
        this.labelArea = labelArea;
    }

    public String getLabelArea() {
        return labelArea;
    }

    public MateriaDTO() {
    }

    @Override
    public void setId(Long id) {
        setCodigoArea(id);
    }

    @Override
    public Long getId() {
        return getCodigoArea();
    }
}
