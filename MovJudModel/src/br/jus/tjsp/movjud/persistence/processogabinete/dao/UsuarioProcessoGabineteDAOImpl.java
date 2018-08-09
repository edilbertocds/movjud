package br.jus.tjsp.movjud.persistence.processogabinete.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.UsuarioProcessoGabinete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class UsuarioProcessoGabineteDAOImpl extends BaseDAOImpl<UsuarioProcessoGabinete> implements UsuarioProcessoGabineteDAO {

    @Override
    public Class<UsuarioProcessoGabinete> getPersistentClass() {
        return UsuarioProcessoGabinete.class;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(UsuarioProcessoGabinete filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();

        if (filter.getUsuario() != null) {
            if (filter.getUsuario().getNome() != null)
                parametros.add(new ParametroOperacao("usuario.nome", filter.getUsuario().getNome(), SQLOperatorType.LikeFullNoCase));
        }

        if (filter.getProcessoGabinete() != null) {
            if (filter.getProcessoGabinete().getTipoProcesso() != null) 
                parametros.add(new ParametroOperacao("processoGabinete.tipoProcesso", filter.getProcessoGabinete().getTipoProcesso(), SQLOperatorType.LikeFullNoCase));
            
            if (filter.getProcessoGabinete().getFlagArquivado() != null) 
                parametros.add(new ParametroOperacao("processoGabinete.flagArquivado", filter.getProcessoGabinete().getFlagArquivado(), SQLOperatorType.Equal));
        }

        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(UsuarioProcessoGabinete filter) {
        return Collections.emptyList();
    }

    @Override
    public List<UsuarioProcessoGabinete> listarMagistradosProcessoGabinetePaginado(UsuarioProcessoGabinete filter,
                                                                                   Paginacao paginacao) {
        return listarComFiltro(filter, paginacao);
    }
}
