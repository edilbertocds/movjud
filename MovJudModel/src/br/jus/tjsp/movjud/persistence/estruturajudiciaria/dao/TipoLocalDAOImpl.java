package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.TipoLocal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class TipoLocalDAOImpl extends BaseDAOImpl<TipoLocal> implements TipoLocalDAO{
    public TipoLocalDAOImpl() {
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoLocal filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        parametros.add(new ParametroOperacao("nomeLocal", filter.getNomeLocal(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));
        parametros.add(new ParametroOperacao("flagUnidadePrisional", filter.getFlagUnidadePrisional(), SQLOperatorType.EqualNoCase));
        parametros.add(new ParametroOperacao("flagColegioRecursal", filter.getFlagColegioRecursal(), SQLOperatorType.EqualNoCase));
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoLocal filter) {
        return Collections.emptyList();
    }

    @Override
    public Class<TipoLocal> getPersistentClass() {
        return TipoLocal.class;
    }
}
