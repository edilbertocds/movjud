package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.PeriodoProcessoConcluso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class PeriodoProcessoConclusoDAOImpl extends BaseDAOImpl<PeriodoProcessoConcluso> implements PeriodoProcessoConclusoDAO{
    public PeriodoProcessoConclusoDAOImpl() {
        super();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(PeriodoProcessoConcluso filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("mes", filter.getMes(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("ano", filter.getAno(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("dataInicio", filter.getDataPeriodo(), SQLOperatorType.LessOrEqual));
        parametros.add(new ParametroOperacao("dataFim", filter.getDataPeriodo(), SQLOperatorType.GreaterOrEqual));
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(PeriodoProcessoConcluso filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("mes", filter.getMes(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("ano", filter.getAno(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("dataInicio", filter.getDataPeriodo(), SQLOperatorType.LessOrEqual));
        parametros.add(new ParametroOperacao("dataFim", filter.getDataPeriodo(), SQLOperatorType.GreaterOrEqual));
        return parametros;
    }

    @Override
    public Class<PeriodoProcessoConcluso> getPersistentClass() {
        return PeriodoProcessoConcluso.class;
    }
}
