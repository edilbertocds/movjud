package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

public class MetadadoSituacaoFormularioDTO extends BaseDTO<Long>{
    
    private Long codigoSituacaoFormulario;
    private String identificadorSituacaoFormulario;
    private String labelSituacaoFormulario;

    @Override
    public void setId(Long id) {
        setCodigoSituacaoFormulario(id);
    }

    @Override
    public Long getId() {
        return getCodigoSituacaoFormulario();
    }

    public void setCodigoSituacaoFormulario(Long codigoSituacaoFormulario) {
        this.codigoSituacaoFormulario = codigoSituacaoFormulario;
    }

    public Long getCodigoSituacaoFormulario() {
        return codigoSituacaoFormulario;
    }

    public void setLabelSituacaoFormulario(String labelSituacaoFormulario) {
        this.labelSituacaoFormulario = labelSituacaoFormulario;
    }

    public String getLabelSituacaoFormulario() {
        return labelSituacaoFormulario;
    }

    public void setIdentificadorSituacaoFormulario(String identificadorSituacaoFormulario) {
        this.identificadorSituacaoFormulario = identificadorSituacaoFormulario;
    }

    public String getIdentificadorSituacaoFormulario() {
        return identificadorSituacaoFormulario;
    }
    public MetadadoSituacaoFormularioDTO() {
        super();
    }
}
