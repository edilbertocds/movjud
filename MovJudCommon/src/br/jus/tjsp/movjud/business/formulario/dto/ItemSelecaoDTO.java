package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

public class ItemSelecaoDTO extends BaseDTO<String>{
    
    private String codigoItemSelecao;
    private String labelItemSelecao;
    private Long idEntidadeCampo;

    public ItemSelecaoDTO() {
        super();
    }

    @Override
    public void setId(String id) {
        setCodigoItemSelecao(id);
    }

    @Override
    public String getId() {
        return getCodigoItemSelecao();
    }
    

    public void setCodigoItemSelecao(String codigoItemSelecaoo) {
        this.codigoItemSelecao = codigoItemSelecaoo;
    }

    public String getCodigoItemSelecao() {
        return codigoItemSelecao;
    }

    public void setLabelItemSelecao(String labelItemSelecao) {
        this.labelItemSelecao = labelItemSelecao;
    }

    public String getLabelItemSelecao() {
        return labelItemSelecao;
    }

    public void setIdEntidadeCampo(Long idEntidadeCampo) {
        this.idEntidadeCampo = idEntidadeCampo;
    }

    public Long getIdEntidadeCampo() {
        return idEntidadeCampo;
    }
}
