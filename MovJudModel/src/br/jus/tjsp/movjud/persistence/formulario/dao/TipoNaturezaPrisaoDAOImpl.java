package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.TipoNaturezaPrisao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class TipoNaturezaPrisaoDAOImpl extends BaseDAOImpl<TipoNaturezaPrisao> implements TipoNaturezaPrisaoDAO{
    public TipoNaturezaPrisaoDAOImpl() {
        super();
    }

    @Override
    public List<TipoNaturezaPrisao> listarTipoNaturezaPrisaoOrdenadoPorNome(TipoNaturezaPrisao filter) {
        return listarComFiltroOrdenacao(filter, "descricaoTipoNatureza", true);
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoNaturezaPrisao filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.Equal));
        return parametros;  
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoNaturezaPrisao filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<TipoNaturezaPrisao> getPersistentClass() {
        return TipoNaturezaPrisao.class;
    }
}
