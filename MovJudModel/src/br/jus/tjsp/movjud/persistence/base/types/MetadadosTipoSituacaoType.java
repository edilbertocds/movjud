package br.jus.tjsp.movjud.persistence.base.types;

import br.jus.tjsp.movjud.business.formulario.dto.MetadadoSituacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SituacaoFormularioDTO;

import java.util.List;

public enum MetadadosTipoSituacaoType {
    
    EM_EDICAO("EM_EDICAO"),
    EM_USO("EM_USO"),
    HISTORICO("HISTORICO");
    
    private static final long serialVersionUID = 20160926170207L;
    
    private final String codigo;
    
    public static MetadadoSituacaoFormularioDTO recuperaSituacaoFormularioDTOPorCodigo(List<MetadadoSituacaoFormularioDTO> listaTipoSituacao, MetadadosTipoSituacaoType codigo){
        MetadadoSituacaoFormularioDTO situacao = null;
        for(MetadadoSituacaoFormularioDTO situacaoFormularioDTO : listaTipoSituacao){
            if(situacaoFormularioDTO.getIdentificadorSituacaoFormulario().equals(codigo.getCodigo())){
                situacao = situacaoFormularioDTO;
                break;
            }
        }
        return situacao;
    }
    
    MetadadosTipoSituacaoType(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
}
