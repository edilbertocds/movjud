package br.jus.tjsp.movjud.persistence.configuracao.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.entity.TipoPeriodicidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class TipoPeriodicidadeDAOImpl extends BaseDAOImpl<TipoPeriodicidade> implements TipoPeriodicidadeDAO {

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoPeriodicidade filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
        
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoPeriodicidade filter) {
        return Collections.emptyList();
    }

    @Override
    public Class<TipoPeriodicidade> getPersistentClass() {
        return TipoPeriodicidade.class;
    }
}
