package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorioHistorico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class ReuProvisorioHistoricoDAOImpl extends BaseDAOImpl<ReuProvisorioHistorico> implements ReuProvisorioHistoricoDAO{
    public ReuProvisorioHistoricoDAOImpl() {
        super();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(ReuProvisorioHistorico filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();

        parametros.add(new ParametroOperacao("ano",
                                             filter.getAno(),
                                             SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("mes",
                                             filter.getMes(),
                                             SQLOperatorType.Equal));
        if(filter.getReuProvisorio()!=null){
            parametros.add(new ParametroOperacao("reuProvisorio.idReuProvisorio",
                                                 filter.getReuProvisorio().getIdReuProvisorio(),
                                                 SQLOperatorType.Equal));
        }
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(ReuProvisorioHistorico filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<ReuProvisorioHistorico> getPersistentClass() {
        return ReuProvisorioHistorico.class;
    }

    @Override
    public Long countReusHistoricoMesesAnterior(ReuProvisorioHistorico reuHist) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select count(reuProvisorioHistorico) from ReuProvisorioHistorico reuProvisorioHistorico where");
        jpaQl.append(" (reuProvisorioHistorico.ano < ?1 or (reuProvisorioHistorico.ano = ?1 and reuProvisorioHistorico.mes < ?2)) and");
        jpaQl.append(" reuProvisorioHistorico.reuProvisorio.idReuProvisorio = ?3");
        return getPersistenceManager().procurarPorJPQLSingleResult(jpaQl, reuHist.getAno(), reuHist.getMes(), reuHist.getReuProvisorio().getIdReuProvisorio());
    }

    @Override
    public void deletarHistoricoReu(ReuProvisorioHistorico reuHistorico) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("delete from ReuProvisorioHistorico reuProvisorioHistorico where");
        jpaQl.append(" reuProvisorioHistorico.reuProvisorio.idReuProvisorio = ?1");
        getPersistenceManager().atualizarPorJPQL(jpaQl, reuHistorico.getReuProvisorio().getIdReuProvisorio());
    }
}
