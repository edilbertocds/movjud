package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

public class CompetenciaDTO extends BaseDTO<Long> implements Comparable<CompetenciaDTO>{
    
    private Long codigoCompetencia;
    private String labelCompetencia;

    public void setCodigoCompetencia(Long codigoCompetencia) {
        this.codigoCompetencia = codigoCompetencia;
    }

    public Long getCodigoCompetencia() {
        return codigoCompetencia;
    }

    public void setLabelCompetencia(String labelCompetencia) {
        this.labelCompetencia = labelCompetencia;
    }

    public String getLabelCompetencia() {
        return labelCompetencia;
    }

    public CompetenciaDTO() {
    }

    @Override
    public int compareTo(CompetenciaDTO o) {
        return labelCompetencia.compareTo(o.labelCompetencia);
    }

    @Override
    public void setId(Long id) {
        setCodigoCompetencia(id);
    }

    @Override
    public Long getId() {
        return getCodigoCompetencia();
    }
}
