package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Regiao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class RegiaoDAOImpl extends BaseDAOImpl<Regiao> implements RegiaoDAO{

    public RegiaoDAOImpl() {
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Regiao filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        parametros.add(new ParametroOperacao("nomeRegiao", filter.getNomeRegiao(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Regiao filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<Regiao> getPersistentClass() {
        return Regiao.class;
    }

    @Override
    public List<Regiao> listarRegioesOrdenadoPorNome(Regiao regiao,Paginacao paginacao) {
        return listarComFiltroOrdenacao(regiao, "nomeRegiao", true, paginacao);
    }
}
