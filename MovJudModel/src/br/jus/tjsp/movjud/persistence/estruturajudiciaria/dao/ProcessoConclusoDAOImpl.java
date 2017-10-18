package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.ProcessoConcluso;

import br.jus.tjsp.movjud.persistence.entity.Usuario;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class ProcessoConclusoDAOImpl extends BaseDAOImpl<ProcessoConcluso> implements ProcessoConclusoDAO{
    public ProcessoConclusoDAOImpl() {
        super();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(ProcessoConcluso filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("mes", filter.getMes(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("ano", filter.getAno(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("sourceFormulario", filter.getSourceFormulario(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("dataBaixa", filter.getDataBaixa(), SQLOperatorType.IsNull));
        if(filter.getUnidade() != null)
            parametros.add(new ParametroOperacao("unidade.idUnidade", filter.getUnidade().getIdUnidade(), SQLOperatorType.Equal));
        if(filter.getUsuario() != null){
            parametros.add(new ParametroOperacao("usuario.idUsuario", filter.getUsuario().getIdUsuario(), SQLOperatorType.Equal));
        }
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(ProcessoConcluso filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<ProcessoConcluso> getPersistentClass() {
        return ProcessoConcluso.class;
    }

    @Override
    public List<ProcessoConcluso> listarProcessosConclusosAnoMesReferencia(ProcessoConcluso processoConcluso) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select processoConcluso from ProcessoConcluso processoConcluso where");
        jpaQl.append(" processoConcluso.usuario.idUsuario = ?1 and");
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?2 and");
        jpaQl.append(" processoConcluso.ano = ?3 and");
        jpaQl.append(" processoConcluso.mes = ?4 and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?5");
        jpaQl.append(" order by processoConcluso.dataConclusao");
        return getPersistenceManager().listarPorJPQL(jpaQl, processoConcluso.getUsuario().getIdUsuario(),
                                                                   processoConcluso.getUnidade().getIdUnidade(),
                                                                   processoConcluso.getAno(), processoConcluso.getMes(), processoConcluso.getSourceFormulario());
    }

    @Override
    public List<ProcessoConcluso> listarProcessosConclusosMesAnterior(ProcessoConcluso processoConcluso,
                                                                      List<BigDecimal> processosConclusosMesAtual) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select processoConcluso from ProcessoConcluso processoConcluso where");
        jpaQl.append(" processoConcluso.usuario.idUsuario = ?1 and");
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?2 and");
        jpaQl.append(" processoConcluso.ano = ?3 and");
        jpaQl.append(" processoConcluso.mes = ?4 and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?5 and");
        jpaQl.append(" processoConcluso.dataBaixa is null");
        if (processosConclusosMesAtual != null && !processosConclusosMesAtual.isEmpty()) {
                    notInList(jpaQl, "processoConcluso.numeroProcesso", processosConclusosMesAtual);
        }
        return getPersistenceManager()
               .listarPorJPQL(jpaQl, processoConcluso.getUsuario().getIdUsuario(),
                              processoConcluso.getUnidade().getIdUnidade(), processoConcluso.getAno(),
                              processoConcluso.getMes() - 1, processoConcluso.getSourceFormulario());
    }

    @Override
    public List<Usuario> listarMagistradosComProcessosConclusosNaUnidade(ProcessoConcluso processoConcluso) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select DISTINCT(processoConcluso.usuario) from ProcessoConcluso processoConcluso where");
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?1 and");
        jpaQl.append(" processoConcluso.ano = ?2 and");
        jpaQl.append(" processoConcluso.mes = ?3 and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?4");
        return getPersistenceManager().listarPorJPQL(jpaQl, processoConcluso.getUnidade().getIdUnidade(),
                                                            processoConcluso.getAno(), processoConcluso.getMes(), processoConcluso.getSourceFormulario());
    }

    @Override
    public List<Usuario> listarMagistradosComProcessosConclusosNaUnidadeMesAnterior(ProcessoConcluso processoConcluso, List<Long> processosConclusosMesAtual) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select DISTINCT(processoConcluso.usuario) from ProcessoConcluso processoConcluso where");
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?1 and");
        jpaQl.append(" processoConcluso.ano = ?2 and");
        jpaQl.append(" processoConcluso.mes = ?3 and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?4 and");
        jpaQl.append(" processoConcluso.dataBaixa is null");
        if(processosConclusosMesAtual!=null && !processosConclusosMesAtual.isEmpty()){
            notInList(jpaQl, "processoConcluso.usuario.idUsuario", processosConclusosMesAtual);
        }
        return getPersistenceManager().listarPorJPQL(jpaQl, processoConcluso.getUnidade().getIdUnidade(),
                                                            processoConcluso.getAno(), processoConcluso.getMes()-1, processoConcluso.getSourceFormulario());
    }

    @Override
    public List<ProcessoConcluso> listarProcessosConclusosMesesSubsequentes(ProcessoConcluso processoConcluso) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select processoConcluso from ProcessoConcluso processoConcluso where");
        jpaQl.append(" processoConcluso.usuario.idUsuario = ?1 and");
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?2 and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?6 and");
        jpaQl.append(" ((processoConcluso.ano = ?3 and");
        jpaQl.append(" processoConcluso.mes > ?4) or processoConcluso.ano > ?3) and");
        jpaQl.append(" processoConcluso.numeroProcesso = ?5");
        
        return getPersistenceManager().listarPorJPQL(jpaQl, processoConcluso.getUsuario().getIdUsuario(),
                                                            processoConcluso.getUnidade().getIdUnidade(),
                                                            processoConcluso.getAno(), processoConcluso.getMes(), 
                                                            processoConcluso.getNumeroProcesso(), processoConcluso.getSourceFormulario());
    }

    @Override
    public void deletarProcessosConclusosSubsequentes(ProcessoConcluso processoConcluso) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("delete from ProcessoConcluso processoConcluso where");
        jpaQl.append(" processoConcluso.usuario.idUsuario = ?1 and");
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?2 and");
        jpaQl.append(" ((processoConcluso.ano = ?3 and");
        jpaQl.append(" processoConcluso.mes > ?4) or processoConcluso.ano > ?3) and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?5 and");
        jpaQl.append(" processoConcluso.numeroProcesso = ?6");
        
        getPersistenceManager().atualizarPorJPQL(jpaQl, processoConcluso.getUsuario().getIdUsuario(),
                                                                    processoConcluso.getUnidade().getIdUnidade(),
                                                                    processoConcluso.getAno(), processoConcluso.getMes(), 
                                                                    processoConcluso.getSourceFormulario() ,processoConcluso.getNumeroProcesso());
    }

    @Override
    public void deletarProcessosConclusosAtualESubsequentes(ProcessoConcluso processoConcluso) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("delete from ProcessoConcluso processoConcluso where");
        jpaQl.append(" processoConcluso.usuario.idUsuario = ?1 and");
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?2 and");
        jpaQl.append(" ((processoConcluso.ano = ?3 and");
        jpaQl.append(" processoConcluso.mes >= ?4) or processoConcluso.ano > ?3) and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?5 and");
        jpaQl.append(" processoConcluso.numeroProcesso = ?6");
        
        getPersistenceManager().atualizarPorJPQL(jpaQl, processoConcluso.getUsuario().getIdUsuario(),
                                                                    processoConcluso.getUnidade().getIdUnidade(),
                                                                    processoConcluso.getAno(), processoConcluso.getMes(), 
                                                                    processoConcluso.getSourceFormulario() ,processoConcluso.getNumeroProcesso());
    }
}
