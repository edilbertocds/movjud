package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorio;

import br.jus.tjsp.movjud.persistence.entity.ReuProvisorioHistorico;

import br.jus.tjsp.movjud.persistence.entity.TipoMotivoBaixa;
import br.jus.tjsp.movjud.persistence.entity.TipoNaturezaPrisao;

import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.EstabelecimentoPrisionalDAO;

import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.UnidadeDAO;

import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.UsuarioDAO;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.persistence.Query;

@Stateless
public class ReuProvisorioDAOImpl extends BaseDAOImpl<ReuProvisorio> implements ReuProvisorioDAO{
    @EJB
    EstabelecimentoPrisionalDAO estabelecimentoPrisionalDAO;
    
    @EJB
    UnidadeDAO unidadeDAO;
    
    @EJB
    UsuarioDAO usuarioDAO;
    
    @EJB
    TipoMotivoBaixaDAO tipoMotivoBaixaDAO;
    
    @EJB
    TipoNaturezaPrisaoDAO tipoNaturezaPrisaoDAO;
    
    public ReuProvisorioDAOImpl() {
        super();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(ReuProvisorio filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();

        if(!filter.getHistoricosReuProvisorio().isEmpty()){
        parametros.add(new ParametroOperacao("reuProvisorio.dataBaixa",
                                             filter.getHistoricosReuProvisorio().get(0).getDataBaixa(),
                                             SQLOperatorType.IsNull));
        }
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
        // List<ReuProvisorio> lista = null;//new ArrayList<ReuProvisorio>();
        List lista = null;
        StringBuilder jpaNQ = new StringBuilder();
        //jpaNQ.append("select r.* ");
        //jpaNQ.append("from CAD_REU_PROVISORIO r ");
        //jpaNQ.append("inner join CAD_REU_PROVISORIO_HIST h on h.ID_REU_PROVISORIO=r.ID_CAD_REU_PROVISORIO ");
        //jpaNQ.append("where r.FK_CAD_UNIDADE = ? and ");
        //jpaNQ.append("      (r.DT_BAIXA is null or (EXTRACT(YEAR FROM r.DT_BAIXA) >= ? and EXTRACT(MONTH FROM r.DT_BAIXA) >= ?)) and ");
        //jpaNQ.append("      h.ID_REU_PROVISORIO_HIST = (select MAX(h2.ID_REU_PROVISORIO_HIST) ");
        //jpaNQ.append("                                  from CAD_REU_PROVISORIO_HIST h2 "); 
        //jpaNQ.append("                                  where h2.ID_REU_PROVISORIO=r.ID_CAD_REU_PROVISORIO and ");
        //jpaNQ.append("                                        h2.NR_ANO <= ? and ");
        //jpaNQ.append("                                        h2.NR_MES <= ?) ");
        
        /* ======================================= */
        jpaNQ.append("select r.ID_CAD_REU_PROVISORIO," + 
        "r.NM_REU_PROV," + 
        "r.NM_MAE_REU_PROV," + 
        "r.TP_SEXO," + 
        "r.SAJPG_CD_PESSOA_ID," + 
        "r.SAJPG_BASE_ORIGEM_ID," + 
        "r.TP_SITUACAO," + 
        "r.DT_INCLUSAO," + 
        "r.DT_ATUALIZACAO," + 
        "h.ID_REU_PROVISORIO_HIST," + 
        "h.FK_TIPO_NATUREZA_PRISAO," + 
        "h.NR_MES," + 
        "h.NR_ANO," + 
        "h.NR_PROCESSO," + 
        "h.NR_CONTROLE_ORDEM," + 
        "h.DT_ULT_MOV," + 
        "h.DS_CONTEUDO_ULT_MOV," + 
        "h.TP_SITUACAO," + 
        "h.DT_INCLUSAO," + 
        "h.DT_ATUALIZACAO," + 
        "h.DS_RELATORIO_CGJ," + 
        "h.DT_BAIXA," + 
        "h.DT_DATA_BAIXA," + 
        "h.DT_LEVADO_MAGISTRADO," + 
        "h.DT_PRISAO," + 
        "h.FK_CAD_ESTAB_PRISIONAL," + 
        "h.FK_CAD_USUARIO," + 
        "h.FK_TIPO_MOTIVO_BAIXA," + 
        "h.FK_CAD_UNIDADE ");
        jpaNQ.append("from CAD_REU_PROVISORIO r ");
        jpaNQ.append("inner join CAD_REU_PROVISORIO_HIST h on h.ID_REU_PROVISORIO=r.ID_CAD_REU_PROVISORIO ");
        jpaNQ.append("where h.FK_CAD_UNIDADE = ? and ");  
        jpaNQ.append(" ((h.DT_DATA_BAIXA is null and h.FK_TIPO_MOTIVO_BAIXA is null) or (EXTRACT(YEAR FROM h.DT_DATA_BAIXA) >= ? and EXTRACT(MONTH FROM h.DT_DATA_BAIXA) >= ?)) and ");
        jpaNQ.append(" h.ID_REU_PROVISORIO_HIST = (select MAX(h2.ID_REU_PROVISORIO_HIST)    from CAD_REU_PROVISORIO_HIST h2    ");   
        jpaNQ.append(" where h2.ID_REU_PROVISORIO=r.ID_CAD_REU_PROVISORIO and h2.NR_ANO <= ? and h2.NR_MES <= ?)  ");
        
        /* 2017.12.05 - 18:00 - Tirar o filtro de data da data da baixa
        jpaNQ.append(" select r.* ");
        jpaNQ.append(" from CAD_REU_PROVISORIO r inner join  ");
        jpaNQ.append(" CAD_REU_PROVISORIO_HIST h on h.ID_REU_PROVISORIO=r.ID_CAD_REU_PROVISORIO ");
        jpaNQ.append(" where  ");
        jpaNQ.append(" r.FK_CAD_UNIDADE = ? and h.NR_ANO = ? and h.NR_MES = ?  ");
        */
        Query query = null;
        if(reuProvisorio.getNomeReuProvisorio() != null && !reuProvisorio.getNomeReuProvisorio().isEmpty()) {
            jpaNQ.append(" and r.NM_REU_PROV like ? ");
            jpaNQ.append(" order by r.NM_REU_PROV asc, h.DT_ATUALIZACAO desc");
            query = getPersistenceManager().getManager().createNativeQuery(jpaNQ.toString()/*, ReuProvisorio.class*/);
            query.setParameter(1, reuProvisorio.getUnidade().getIdUnidade());
            query.setParameter(2, ano);
            query.setParameter(3, mes);
            query.setParameter(4, ano);
            query.setParameter(5, mes);
            query.setParameter(4, "%"+reuProvisorio.getNomeReuProvisorio()+"%");
            lista = /*(List<ReuProvisorio>)*/query.getResultList();
        } else  {
            jpaNQ.append(" order by r.NM_REU_PROV asc, h.DT_ATUALIZACAO desc");
            query = getPersistenceManager().getManager().createNativeQuery(jpaNQ.toString()/*, ReuProvisorio.class*/);
            query.setParameter(1, reuProvisorio.getUnidade().getIdUnidade());
            query.setParameter(2, ano);
            query.setParameter(3, mes);
            query.setParameter(4, ano);
            query.setParameter(5, mes);
            lista = /*(List<ReuProvisorio>)*/query.getResultList();
        }
        /*
        
        StringBuilder jpaQl = new StringBuilder();
        //?? <epr>(20170718) 0.7.3 construção proposta (JPAQL)
        jpaQl.append("select reuProvisorio from ReuProvisorio reuProvisorio ");
        jpaQl.append("inner join fetch reuProvisorio.historicosReuProvisorio historicoReuProvisorio ");
        jpaQl.append("where historicoReuProvisorio.unidade = ?1 and ");
        jpaQl.append("      (historicoReuProvisorio.dataBaixa is null) or ");
        jpaQl.append("      (EXTRACT(MONTH from historicoReuProvisorio.dtDataBaixa) >= ?2 and EXTRACT(YEAR from historicoReuProvisorio.dtDataBaixa) >= ?3) and ");
        jpaQl.append("      historicoReuProvisorio.idReuProvisorioHistorico = (select max(historicoReuProvisorio2.idReuProvisorioHistorico) ");
        jpaQl.append("                                                         from reuProvisorio.historicosReuProvisorio historicoReuProvisorio2 ");
        jpaQl.append("                                                         where historicoReuProvisorio2.mes <= ?2 and ");
        jpaQl.append("                                                               historicoReuProvisorio2.ano <= ?3) ");
        if(reuProvisorio.getNomeReuProvisorio() !=null && !reuProvisorio.getNomeReuProvisorio().isEmpty()) {
            jpaQl.append(" and UPPER(reuProvisorio.nomeReuProvisorio) like UPPER(?4) ");
            jpaQl.append("order by reuProvisorio.nomeReuProvisorio asc, historicoReuProvisorio.dataAtualizacao desc ");
            lista = getPersistenceManager().listarPorJPQL(jpaQl, reuProvisorio.getUnidade(), mes, ano, "%"+reuProvisorio.getNomeReuProvisorio()+"%");
        } else {
            jpaQl.append("order by reuProvisorio.nomeReuProvisorio asc, historicoReuProvisorio.dataAtualizacao desc ");
            lista = getPersistenceManager().listarPorJPQL(jpaQl, reuProvisorio.getUnidade(), mes, ano);
        }
        // <epr>(20170718)(1) 0.7.3 construção proposta
        
    
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
        List<ReuProvisorio> result = ((List<Object[]>)lista)
            .stream()
            .map((i) -> {
                ReuProvisorio r = new ReuProvisorio();
                /*
                0. r.ID_CAD_REU_PROVISORIO
                1. r.NM_REU_PROV
                2. r.NM_MAE_REU_PROV
                3. r.TP_SEXO
                4. r.SAJPG_CD_PESSOA_ID
                5. r.SAJPG_BASE_ORIGEM_ID
                6. r.TP_SITUACAO
                7. r.DT_INCLUSAO
                8. r.DT_ATUALIZACAO
                */
                r.setId(((BigDecimal)i[0]).longValue());
                r.setNomeReuProvisorio((String)i[1]);
                r.setNomeMaeReuProvisorio((String)i[2]);
                r.setSexo((String)i[3]);
                r.setCodigoPessoaSaj(((BigDecimal)i[4]).longValue());
                r.setIdBaseOrigemSaj((String)i[5]);
                r.setFlagTipoSituacao((String)i[6]);
                r.setDataInclusao((Date)i[7]);
                r.setDataAtualizacao((Date)i[8]);
                List<ReuProvisorioHistorico> h = new ArrayList<ReuProvisorioHistorico>();
                ReuProvisorioHistorico u = new ReuProvisorioHistorico();
                /*        
                9. h.ID_REU_PROVISORIO_HIST
                10. h.FK_TIPO_NATUREZA_PRISAO
                11. h.NR_MES
                12. h.NR_ANO
                13. h.NR_PROCESSO
                14. h.NR_CONTROLE_ORDEM
                15. h.DT_ULT_MOV
                16. h.DS_CONTEUDO_ULT_MOV
                17. h.TP_SITUACAO
                18. h.DT_INCLUSAO
                19. h.DT_ATUALIZACAO
                20. h.DS_RELATORIO_CGJ
                21. h.DT_BAIXA
                22. h.DT_DATA_BAIXA
                23. h.DT_LEVADO_MAGISTRADO
                24. h.DT_PRISAO
                25. h.FK_CAD_ESTAB_PRISIONAL
                26. h.FK_CAD_USUARIO
                27. h.FK_TIPO_MOTIVO_BAIXA
                28. h.FK_CAD_UNIDADE
                */                
                u.setId(((BigDecimal)i[9]).longValue());
                if(i[10] != null) {
                    TipoNaturezaPrisao tnp = tipoNaturezaPrisaoDAO.procurarPorId(((BigDecimal)i[10]).longValue());
                    u.setTipoNaturezaPrisao(tnp);
                }
                u.setMes(((BigDecimal)i[11]).intValue());
                u.setAno(((BigDecimal)i[12]).intValue());
                u.setNumeroProcesso((String)i[13]);
                u.setNumeroControleOrdem((String)i[14]);
                u.setDataUltimaMovimentacao((Date)i[15]);
                u.setDescricaoConteudoUltimaMovimentacao((String)i[16]);
                u.setFlagTipoSituacao((String)i[17]);
                u.setDataInclusao((Date)i[18]);
                u.setDataAtualizacao((Date)i[19]);
                u.setDescricaoRelatorioCgj((String)i[20]);
                u.setDataBaixa((Date)i[21]);
                u.setDtDataBaixa((Date)i[22]);
                u.setDataLevadoMagistrado((Date)i[23]);
                u.setDataPrisao((Date)i[24]);
                if(i[25] != null) {
                    EstabelecimentoPrisional ep = estabelecimentoPrisionalDAO.procurarPorId(((BigDecimal)i[25]).longValue());
                    u.setEstabelecimentoPrisional(ep);
                }
                if(i[26] != null) {
                    Usuario usu = usuarioDAO.procurarPorId(((BigDecimal)i[26]).longValue());
                    u.setUsuario(usu);
                }
                if(i[27] != null) {
                    TipoMotivoBaixa tmb = new TipoMotivoBaixa();
                    tmb.setId(((BigDecimal)i[27]).longValue());
                    u.setTipoMotivoBaixa(tmb);
                }
                if(i[28] != null) {
                    Unidade un = unidadeDAO.procurarPorId(((BigDecimal)i[28]).longValue());
                    u.setUnidade(un);
                    r.setUnidade(un);
                }
                h.add(u);
                r.setHistoricosReuProvisorio(h);
                return r;
            })
            .collect(Collectors.toList());
        return result;
    }

    @Override
    public void deletarReuProvisorio(ReuProvisorio reuProvisorio) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("delete from ReuProvisorio reuProvisorio where");
        jpaQl.append(" reuProvisorio.idReuProvisorio = ?1");
        getPersistenceManager().atualizarPorJPQL(jpaQl, reuProvisorio.getIdReuProvisorio());
    }
}
