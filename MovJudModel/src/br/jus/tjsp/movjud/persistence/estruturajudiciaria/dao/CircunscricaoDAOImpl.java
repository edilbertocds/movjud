package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Circunscricao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class CircunscricaoDAOImpl extends BaseDAOImpl<Circunscricao> implements CircunscricaoDAO{
    public CircunscricaoDAOImpl() {
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Circunscricao filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("nomeCircunscricao", filter.getNomeCircunscricao(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));
        
        if(filter.getRegiao() != null)
            parametros.add(new ParametroOperacao("regiao.nomeRegiao", filter.getRegiao().getNomeRegiao(), SQLOperatorType.LikeFullNoCase));
        
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Circunscricao filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<Circunscricao> getPersistentClass() {
        return Circunscricao.class;
    }

    @Override
    public List<Circunscricao> listarCircunscricaoOrdenadoPorNome(Circunscricao circunscricao, Paginacao paginacao) {
        return listarComFiltroOrdenacao(circunscricao, "nomeCircunscricao", true, paginacao);
    }
}
