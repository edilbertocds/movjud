package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.ProcessoConcluso;
import br.jus.tjsp.movjud.persistence.entity.TipoFilaProcesso;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    public List<ProcessoConcluso> listarProcessosConclusosAnoMesReferenciaUnidade(ProcessoConcluso processoConcluso) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select processoConcluso from ProcessoConcluso processoConcluso where");
        //jpaQl.append(" processoConcluso.usuario.idUsuario = ?1 and");
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?1 and");
        jpaQl.append(" processoConcluso.ano = ?2 and");
        jpaQl.append(" processoConcluso.mes = ?3 and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?4");
        jpaQl.append(" order by processoConcluso.dataConclusao");
        return getPersistenceManager().listarPorJPQL(jpaQl, 
                                                     //processoConcluso.getUsuario().getIdUsuario(),
                                                                   processoConcluso.getUnidade().getIdUnidade(),
                                                                   processoConcluso.getAno(), processoConcluso.getMes(), processoConcluso.getSourceFormulario());
    }

    @Override
    public List<TipoFilaProcesso> listarTipoFilaProcesso() {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append(" select distinct  ");
        jpaQl.append(" tfp.idTipoFilaProcesso , tfp.dsTipoFilaProcesso   ");
        jpaQl.append(" from  ");
        jpaQl.append(" TipoFilaProcesso tfp  ");
        
        List results = getPersistenceManager().listarPorJPQL(jpaQl);
        
        //List<TipoFilaProcesso> listaTipoFilaProcesso = getPersistenceManager().listarPorJPQL(jpaQl);
        List<TipoFilaProcesso> listaTipoFilaProcesso =  new ArrayList<TipoFilaProcesso>();
        for(int i = 0; i < results.size(); i++){
            TipoFilaProcesso tfp = new TipoFilaProcesso();
            //@SuppressWarnings("oracle.jdeveloper.java.unchecked-conversion-or-cast")
            Object[] result = (Object[]) results.get(i);
            tfp.setId((Long) result[0]);
            tfp.setDsTipoFilaProcesso((String) result[1]);
            listaTipoFilaProcesso.add(tfp);
        }
        
        return listaTipoFilaProcesso;
    }

    @Override
    public HashMap <String, Integer> countProcessosConclusosAnoMesReferenciaUnidade(ProcessoConcluso processoConcluso, List<BigDecimal> processosConclusosMesAtual) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append(" select  ");
        jpaQl.append(" tfp.ID_TIPO_FILA_PROCESSO,  ");
        jpaQl.append(" tfp.DS_TIPO_FILA_PROCESSO,  ");
        jpaQl.append(" count(pc.ID_CAD_PROCESSO_CONCLUSO) COUNT  ");
        jpaQl.append(" from  ");
        jpaQl.append(" TIPO_FILA_PROCESSO tfp left join  ");
        jpaQl.append(" cad_processo_concluso pc on pc.FK_TIPO_FILA_PROCESSO = tfp.ID_TIPO_FILA_PROCESSO  ");
        
        jpaQl.append(" where ");
        
        jpaQl.append(" (pc.FK_CAD_UNIDADE = ?1 and   ");
        jpaQl.append(" pc.NR_ANO = ?2 and  ");
        jpaQl.append(" pc.NR_MES = ?3 and  ");
        jpaQl.append(" pc.DS_SRC_FORMULARIO = ?4 )  ");
        
        jpaQl.append(" or  ");
        
        jpaQl.append(" (pc.FK_CAD_UNIDADE = ?5 and ");
        jpaQl.append(" pc.NR_ANO = ?6 and ");
        jpaQl.append(" pc.NR_MES = ?7 and  ");
        jpaQl.append(" pc.DS_SRC_FORMULARIO = ?8 and ");
        jpaQl.append(" pc.DT_BAIXA is null  ");
       
        if (processosConclusosMesAtual != null && !processosConclusosMesAtual.isEmpty()) {
                    notInList(jpaQl, "pc.NR_PROCESSO", processosConclusosMesAtual);
        }
        
       jpaQl.append(" ) ");
       
       jpaQl.append(" group by  ");
       jpaQl.append(" tfp.ID_TIPO_FILA_PROCESSO, tfp.DS_TIPO_FILA_PROCESSO  ");
       jpaQl.append(" order by  ");
       jpaQl.append(" tfp.ID_TIPO_FILA_PROCESSO  ");
        
        List results = getPersistenceManager().listarPorNativeQuery(jpaQl.toString(), 
                                                            //processoConcluso.getUsuario().getIdUsuario(),
                                                                         processoConcluso.getUnidade().getIdUnidade(),
                                                                         processoConcluso.getAno(), processoConcluso.getMes(), processoConcluso.getSourceFormulario(),
                                                                         processoConcluso.getUnidade().getIdUnidade(), processoConcluso.getAno(),
                                                                         processoConcluso.getMes() - 1, processoConcluso.getSourceFormulario());
        
        HashMap <String, Integer> countProcessosConclusosAnoMesReferenciaUnidade = new HashMap<String, Integer>();
        for(int i = 0; i < results.size(); i++){
            //@SuppressWarnings("oracle.jdeveloper.java.unchecked-conversion-or-cast")
            Object[] result = (Object[]) results.get(i);
            String label = (String) result[1];
            Integer value = ((BigDecimal) result[2]).intValue();
            countProcessosConclusosAnoMesReferenciaUnidade.put(label, value);
        }
        
        return countProcessosConclusosAnoMesReferenciaUnidade;
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
    public List<ProcessoConcluso> listarProcessosConclusosMesAnteriorUnidade(ProcessoConcluso processoConcluso, List<BigDecimal> processosConclusosMesAtual) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select processoConcluso from ProcessoConcluso processoConcluso where");
        //jpaQl.append(" processoConcluso.usuario.idUsuario = ?1 and");
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?1 and");
        jpaQl.append(" processoConcluso.ano = ?2 and");
        jpaQl.append(" processoConcluso.mes = ?3 and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?4 and");
        jpaQl.append(" processoConcluso.dataBaixa is null");
        if (processosConclusosMesAtual != null && !processosConclusosMesAtual.isEmpty()) {
                    notInList(jpaQl, "processoConcluso.numeroProcesso", processosConclusosMesAtual);
        }
        
        List<ProcessoConcluso> listaProcessosConclusosMesAnteriorUnidade = getPersistenceManager()
               .listarPorJPQL(jpaQl, 
                              //processoConcluso.getUsuario().getIdUsuario(),
                              processoConcluso.getUnidade().getIdUnidade(), processoConcluso.getAno(),
                              processoConcluso.getMes() - 1, processoConcluso.getSourceFormulario());
        
        return listaProcessosConclusosMesAnteriorUnidade;
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
        
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?1 and");
        jpaQl.append(" ((processoConcluso.ano = ?2 and");
        jpaQl.append(" processoConcluso.mes > ?3) or processoConcluso.ano > ?2) and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?4 and");
        jpaQl.append(" processoConcluso.numeroProcesso = ?5");
        
        if(processoConcluso.getUsuario() != null && processoConcluso.getUsuario().getIdUsuario() != null && processoConcluso.getUsuario().getIdUsuario().longValue() > -1){
            jpaQl.append(" and processoConcluso.usuario.idUsuario = ?6 ");
            getPersistenceManager().atualizarPorJPQL(jpaQl, processoConcluso.getUnidade().getIdUnidade(),
                                                                    processoConcluso.getAno(), processoConcluso.getMes(), 
                                                                    processoConcluso.getSourceFormulario() ,processoConcluso.getNumeroProcesso(), 
                                                                    processoConcluso.getUsuario().getIdUsuario());
        } else {
            getPersistenceManager().atualizarPorJPQL(jpaQl, processoConcluso.getUnidade().getIdUnidade(),
                                                                    processoConcluso.getAno(), processoConcluso.getMes(), 
                                                                    processoConcluso.getSourceFormulario() ,processoConcluso.getNumeroProcesso());
        }
    }

    @Override
    public void deletarProcessosConclusosAtualESubsequentes(ProcessoConcluso processoConcluso) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("delete from ProcessoConcluso processoConcluso where");
        
        jpaQl.append(" processoConcluso.unidade.idUnidade = ?1 and");
        jpaQl.append(" ((processoConcluso.ano = ?2 and");
        jpaQl.append(" processoConcluso.mes >= ?3) or processoConcluso.ano > ?2) and");
        jpaQl.append(" processoConcluso.sourceFormulario = ?4 and");
        jpaQl.append(" processoConcluso.numeroProcesso = ?5 ");
        
        if(processoConcluso.getUsuario() != null && processoConcluso.getUsuario().getIdUsuario() != null && processoConcluso.getUsuario().getIdUsuario().longValue() > -1){
            jpaQl.append(" and processoConcluso.usuario.idUsuario = ?6 ");
            getPersistenceManager().atualizarPorJPQL(jpaQl, processoConcluso.getUnidade().getIdUnidade(),
                                                                    processoConcluso.getAno(), processoConcluso.getMes(), 
                                                                    processoConcluso.getSourceFormulario() ,processoConcluso.getNumeroProcesso(), 
                                                                    processoConcluso.getUsuario().getIdUsuario());
        }else{
            getPersistenceManager().atualizarPorJPQL(jpaQl, processoConcluso.getUnidade().getIdUnidade(),
                                                                        processoConcluso.getAno(), processoConcluso.getMes(), 
                                                                        processoConcluso.getSourceFormulario() ,processoConcluso.getNumeroProcesso());
        }
    }
}
