package br.jus.tjsp.movjud.persistence.base.types;

import br.jus.tjsp.movjud.business.formulario.dto.MetadadoSituacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SituacaoFormularioDTO;

import java.util.List;


public enum TipoSituacaoType {
    
    ABERTO("ABERTO"),
    CONCLUIDO("CONCLUIDO"),
    EM_PREENCHIMENTO("EM_PREENCHIMENTO"),
    DEVOLVIDO("DEVOLVIDO"),
    ENVIADO_CGJ("ENVIADO_CGJ"),
    RETIFICACAO_SOLICITADA("RETIFICACAO_SOLICITADA"),
    RETIFICACAO_EM_PREENCHIMENTO("RETIFICACAO_EM_PREENCHIMENTO"),
    RETIFICACAO_CONCLUIDA("RETIFICACAO_CONCLUIDA"),
    RETIFICACAO_DEVOLVIDA("RETIFICACAO_DEVOLVIDA"),
    RETIFICACAO_ENVIADA_AO_CGJ("RETIFICACAO_ENVIADA_AO_CGJ"),
    RETIFICACAO_REPROVADA("RETIFICACAO_REPROVADA"),
    RETIFICACAO_APROVADA("RETIFICACAO_APROVADA");
    
    private static final long serialVersionUID = 20160926170207L;
    
    private final String codigo;
    
    public static SituacaoFormularioDTO recuperarSituacaoFormularioPorCodigo(List<SituacaoFormularioDTO> listaTipoSituacao, TipoSituacaoType tipoSituacao){
        SituacaoFormularioDTO situacao = null;
        for(SituacaoFormularioDTO situacaoFormularioDTO : listaTipoSituacao){
            if(situacaoFormularioDTO.getIdentificadorSituacaoFormulario().equals(tipoSituacao.getCodigo())){
                situacao = situacaoFormularioDTO;
                break;
            }
        }
        return situacao;
    }
    
    TipoSituacaoType(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
}
