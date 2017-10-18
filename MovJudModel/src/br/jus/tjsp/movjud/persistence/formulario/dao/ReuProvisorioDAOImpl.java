package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.Query;

@Stateless
public class ReuProvisorioDAOImpl extends BaseDAOImpl<ReuProvisorio> implements ReuProvisorioDAO{
    public ReuProvisorioDAOImpl() {
        super();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(ReuProvisorio filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();

        parametros.add(new ParametroOperacao("dataBaixa",
                                             filter.getDataBaixa(),
                                             SQLOperatorType.IsNull));
        if(filter.getUnidade()!=null){
            parametros.add(new ParametroOperacao("unidade.idUnidade",
                                                 filter.getUnidade().getIdUnidade(),
                                                 SQLOperatorType.Equal));
        }
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(ReuProvisorio filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<ReuProvisorio> getPersistentClass() {
        return ReuProvisorio.class;
    }

    @Override
    // <epr> (20170717) proposição de query para resolver o problema de reu provisório na apresentação
    // select r.*
    // from CAD_REU_PROVISORIO r
    // inner join CAD_REU_PROVISORIO_HIST h on h.ID_REU_PROVISORIO=r.ID_CAD_REU_PROVISORIO
    // where r.FK_CAD_UNIDADE=79 and /*
    //      h.NR_MES=6 and
    //      h.NR_ANO=2017*/ 
    //      (r.DT_BAIXA is null or (EXTRACT(YEAR FROM r.DT_BAIXA) >=2017 and EXTRACT(MONTH FROM r.DT_BAIXA) >= 3)) and
    //      h.ID_REU_PROVISORIO_HIST = (select MAX(h2.ID_REU_PROVISORIO_HIST) 
    //                                  from CAD_REU_PROVISORIO_HIST h2 
    //                                  where h2.ID_REU_PROVISORIO=r.ID_CAD_REU_PROVISORIO and
    //                                        h2.NR_MES < = 3 and
    //                                        h2.NR_ANO <= 2017)
    // order by r.NM_REU_PROV asc, h.DT_ATUALIZACAO desc;
    public List<ReuProvisorio> listarReusProvisoriosUnidade(ReuProvisorio reuProvisorio, Integer ano, Integer mes) {
        List<ReuProvisorio> lista = null;//new ArrayList<ReuProvisorio>();
        StringBuilder jpaNQ = new StringBuilder();
        jpaNQ.append("select r.* ");
        jpaNQ.append("from CAD_REU_PROVISORIO r ");
        jpaNQ.append("inner join CAD_REU_PROVISORIO_HIST h on h.ID_REU_PROVISORIO=r.ID_CAD_REU_PROVISORIO ");
        jpaNQ.append("where r.FK_CAD_UNIDADE = ? and ");
        jpaNQ.append("      (r.DT_BAIXA is null or (EXTRACT(YEAR FROM r.DT_BAIXA) >= ? and EXTRACT(MONTH FROM r.DT_BAIXA) >= ?)) and ");
        jpaNQ.append("      h.ID_REU_PROVISORIO_HIST = (select MAX(h2.ID_REU_PROVISORIO_HIST) ");
        jpaNQ.append("                                  from CAD_REU_PROVISORIO_HIST h2 "); 
        jpaNQ.append("                                  where h2.ID_REU_PROVISORIO=r.ID_CAD_REU_PROVISORIO and ");
        jpaNQ.append("                                        h2.NR_ANO <= ? and ");
        jpaNQ.append("                                        h2.NR_MES <= ?) ");
        Query query = null;
        if(reuProvisorio.getNomeReuProvisorio() != null && !reuProvisorio.getNomeReuProvisorio().isEmpty()) {
            jpaNQ.append(" and r.NM_REU_PROV like ? ");
            jpaNQ.append(" order by r.NM_REU_PROV asc, h.DT_ATUALIZACAO desc");
            query = getPersistenceManager().getManager().createNativeQuery(jpaNQ.toString(), ReuProvisorio.class);
            query.setParameter(1, reuProvisorio.getUnidade().getIdUnidade());
            query.setParameter(2, ano);
            query.setParameter(3, mes);
            query.setParameter(4, ano);
            query.setParameter(5, mes);
            query.setParameter(6, "%"+reuProvisorio.getNomeReuProvisorio()+"%");
            lista = (List<ReuProvisorio>)query.getResultList();
        } else  {
            jpaNQ.append(" order by r.NM_REU_PROV asc, h.DT_ATUALIZACAO desc");
            query = getPersistenceManager().getManager().createNativeQuery(jpaNQ.toString(), ReuProvisorio.class);
            query.setParameter(1, reuProvisorio.getUnidade().getIdUnidade());
            query.setParameter(2, ano);
            query.setParameter(3, mes);
            query.setParameter(4, ano);
            query.setParameter(5, mes);
            lista = (List<ReuProvisorio>)query.getResultList();
        }
        /*
        StringBuilder jpaQl = new StringBuilder();
        // <epr>(20170718) 0.7.3 construção proposta (JPAQL)
        jpaQl.append("select reuProvisorio from ReuProvisorio reuProvisorio ");
        jpaQl.append("inner join fetch reuProvisorio.historicoReuProvisorio historicoReuProvisorio ");
        jpaQl.append("where reuProvisorio.unidade.idUnidade = ?1 and ");
        jpaQl.append("      (reuProvisorio.dataBaixa is null) or ");
        jpaQl.append("      (func('MONTH', reuProvisorio.dataBaixa) >= ?2 and func('YEAR', reuProvisorio.dataBaixa) >= ?3) and ");
        jpaQl.append("      historicoReuProvisorio.idReuProvisorioHistorico = (select max(historicoReuProvisorio2.idReuProvisorioHistorico) ");
        jpaQl.append("                                                         from reuProvisorio.historicoReuProvisorio historicoReuProvisorio2 ");
        jpaQl.append("                                                         where historicoReuProvisorio2.mes <= ?2 and ");
        jpaQl.append("                                                               historicoReuProvisorio2.ano <= ?3) ");
        if(reuProvisorio.getNomeReuProvisorio()!=null && !reuProvisorio.getNomeReuProvisorio().isEmpty()) {
            jpaQl.append(" and UPPER(reuProvisorio.nomeReuProvisorio) like UPPER(?4) ");
            jpaQl.append("order by reuProvisorio.nomeReuProvisorio asc, historicoReuProvisorio.dataAtualizacao desc ");
            lista = getPersistenceManager().listarPorJPQL(jpaQl, reuProvisorio.getUnidade().getIdUnidade(), mes, ano, "%"+reuProvisorio.getNomeReuProvisorio()+"%");
        } else {
            jpaQl.append("order by reuProvisorio.nomeReuProvisorio asc, historicoReuProvisorio.dataAtualizacao desc ");
            lista = getPersistenceManager().listarPorJPQL(jpaQl, reuProvisorio.getUnidade().getIdUnidade(), mes, ano);
        }
        // <epr>(20170718)(1) 0.7.3 construção proposta
        */
    
        /*
        <epr>(20170718 construção original
        jpaQl.append("select reuProvisorio from ReuProvisorio reuProvisorio");
        jpaQl.append(" inner join fetch reuProvisorio.historicosReuProvisorio historicosReuProvisorio");
        jpaQl.append(" where reuProvisorio.unidade.idUnidade = ?1");
        //jpaQl.append(" where historicosReuProvisorio.idReuProvisorioHistorico = 17924061");
        if(reuProvisorio.getNomeReuProvisorio()==null || reuProvisorio.getNomeReuProvisorio().isEmpty()){
            jpaQl.append(" and historicosReuProvisorio.ano = ?2 and historicosReuProvisorio.mes = ?3");
            lista = getPersistenceManager().listarPorJPQL(jpaQl, reuProvisorio.getUnidade().getIdUnidade(), ano, mes);
            //lista = getPersistenceManager().listarPorJPQL(jpaQl);
        }else{
            jpaQl.append(" and UPPER(reuProvisorio.nomeReuProvisorio) like UPPER(?2)");
            jpaQl.append(" and historicosReuProvisorio.ano = ?3 and historicosReuProvisorio.mes = ?4");
            lista = getPersistenceManager().listarPorJPQL(jpaQl, reuProvisorio.getUnidade().getIdUnidade(), "%" +reuProvisorio.getNomeReuProvisorio()+ "%", ano, mes);
        }
        </epr>
        */
        
        /* 
        <epr> (20170718) comentários originais
        //jpaQl.append(" and historicosReuProvisorio.dataUltimaMovimentacao = (select MAX(historicosReuProvisorio.dataUltimaMovimentacao) from ReuProvisorio reuProvisorio");
        //jpaQl.append(" inner join reuProvisorio.historicosReuProvisorio historicosReuProvisorio");
        //jpaQl.append(" where reuProvisorio.unidade.idUnidade = ?1");
        //jpaQl.append(" and historicosReuProvisorio.ano = ?2 and historicosReuProvisorio.mes = ?3)");
        </epr>
        */

        
        return lista;
    }

    @Override
    public void deletarReuProvisorio(ReuProvisorio reuProvisorio) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("delete from ReuProvisorio reuProvisorio where");
        jpaQl.append(" reuProvisorio.idReuProvisorio = ?1");
        getPersistenceManager().atualizarPorJPQL(jpaQl, reuProvisorio.getIdReuProvisorio());
    }
}
