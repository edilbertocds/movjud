package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

public class SegmentoDTO extends BaseDTO<Long>{
    
    private Long codigoSegmento;
    private String labelSegmento;

    public void setCodigoSegmento(Long codigoSegmento) {
        this.codigoSegmento = codigoSegmento;
    }

    public Long getCodigoSegmento() {
        return codigoSegmento;
    }

    public void setLabelSegmento(String labelSegmento) {
        this.labelSegmento = labelSegmento;
    }

    public String getLabelSegmento() {
        return labelSegmento;
    }

    public SegmentoDTO() {
    }

    @Override
    public void setId(Long id) {
        setCodigoSegmento(id);
    }

    @Override
    public Long getId() {
        return getCodigoSegmento();
    }
}
