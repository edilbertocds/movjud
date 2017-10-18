package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.TipoEntrancia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class TipoEntranciaDAOImpl extends BaseDAOImpl<TipoEntrancia> implements TipoEntranciaDAO{
    public TipoEntranciaDAOImpl() {
    }


    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoEntrancia filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        parametros.add(new ParametroOperacao("nomeEntrancia", filter.getNomeEntrancia(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoEntrancia filter) {
        return Collections.emptyList();
    }

    @Override
    public Class<TipoEntrancia> getPersistentClass() {
        return TipoEntrancia.class;
    }
}
