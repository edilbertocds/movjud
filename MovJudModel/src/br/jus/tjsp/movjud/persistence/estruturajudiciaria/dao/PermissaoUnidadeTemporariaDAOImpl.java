package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;


import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.PermissaoUnidadeTemporaria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;


@Stateless
public class PermissaoUnidadeTemporariaDAOImpl extends BaseDAOImpl<PermissaoUnidadeTemporaria> implements PermissaoUnidadeTemporariaDAO {

    public Class<PermissaoUnidadeTemporaria> getPersistentClass() {
        return PermissaoUnidadeTemporaria.class;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(PermissaoUnidadeTemporaria filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();

        if (filter.getUsuario() != null && filter.getUsuario().getIdUsuario() != null) {
            parametros.add(new ParametroOperacao("usuario.idUsuario", filter.getUsuario().getIdUsuario(),
                                                 SQLOperatorType.Equal));
        }
        if (filter.getUsuario() != null && filter.getUsuario().getNome() != null) {
            parametros.add(new ParametroOperacao("usuario.nome", filter.getUsuario().getNome(),
                                                 SQLOperatorType.LikeFullNoCase));
        }
        if (filter.getUnidade() != null) {
            parametros.add(new ParametroOperacao("unidade.idUnidade", filter.getUnidade().getIdUnidade(),
                                                 SQLOperatorType.Equal));
            if (filter.getUnidade().getUsuario() != null && filter.getUnidade().getUsuario().getIdUsuario() != null) {
                parametros.add(new ParametroOperacao("unidade.usuario.idUsuario",
                                                     filter.getUnidade().getUsuario().getIdUsuario(),
                                                     SQLOperatorType.Equal));
            }
        }
        if (filter.getDataInicio() != null) {
            parametros.add(new ParametroOperacao("dataInicio", filter.getDataInicio(), SQLOperatorType.Equal));
        }
        if (filter.getDataValidade() != null) {
            parametros.add(new ParametroOperacao("dataValidade", filter.getDataValidade(), SQLOperatorType.Equal));
        }


        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(PermissaoUnidadeTemporaria filter) {
        return Collections.emptyList();
    }

    @Override
    public List<PermissaoUnidadeTemporaria> listarPermissaoUnidadeTemporariaOrdenadaPorNomeComPaginacao(PermissaoUnidadeTemporaria filter,
                                                                                                        Paginacao paginacao) {
        return listarComFiltroOrdenacao(filter, "usuario.nome", true, paginacao);
    }

    @Override
    public List<PermissaoUnidadeTemporaria> listarPermissaoUnidadeTemporariaPorUsuarioEDataAtual(PermissaoUnidadeTemporaria filter) {
        
        StringBuilder jpaQl = new StringBuilder();
        jpaQl = new StringBuilder();
        jpaQl.append("select permissao from PermissaoUnidadeTemporaria permissao where CURRENT_DATE between permissao.dataInicio ");
        jpaQl.append("and permissao.dataValidade ");
        jpaQl.append("and permissao.usuario.idUsuario = " + filter.getUsuario().getIdUsuario());
       

        return getPersistenceManager().listarPorJPQL(jpaQl);
    }
}

