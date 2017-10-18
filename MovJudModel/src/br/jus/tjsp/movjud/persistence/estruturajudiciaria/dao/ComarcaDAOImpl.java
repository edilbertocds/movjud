package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Comarca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class ComarcaDAOImpl extends BaseDAOImpl<Comarca> implements ComarcaDAO{
    public ComarcaDAOImpl() {
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Comarca filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("nomeComarca", filter.getNomeComarca(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));

        if(filter.getCircunscricao() != null){
            parametros.add(new ParametroOperacao("circunscricao.nomeCircunscricao", filter.getCircunscricao().getNomeCircunscricao(), SQLOperatorType.LikeFullNoCase));
            if(filter.getCircunscricao().getRegiao() != null)
                parametros.add(new ParametroOperacao("circunscricao.regiao.nomeRegiao", filter.getCircunscricao().getRegiao().getNomeRegiao(), SQLOperatorType.LikeFullNoCase));
        }
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Comarca filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<Comarca> getPersistentClass() {
        return Comarca.class;
    }

    @Override
    public List<Comarca> listarComarcaOrdenadoPorNome(Comarca comarca, Paginacao paginacao) {
        return listarComFiltroOrdenacao(comarca, "nomeComarca", true, paginacao);
    }
}
