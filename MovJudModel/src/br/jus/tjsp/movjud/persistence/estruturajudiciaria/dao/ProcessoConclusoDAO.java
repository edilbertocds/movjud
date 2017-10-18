package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.entity.ProcessoConcluso;

import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;


import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;
@Local
public interface ProcessoConclusoDAO extends BaseDAO<ProcessoConcluso>{
    
    List<ProcessoConcluso> listarProcessosConclusosAnoMesReferencia(ProcessoConcluso processoConcluso);
    
    List<ProcessoConcluso> listarProcessosConclusosMesesSubsequentes(ProcessoConcluso processoConcluso);
    
    List<ProcessoConcluso> listarProcessosConclusosMesAnterior(ProcessoConcluso processoConcluso, List<BigDecimal> processosConclusosMesAtual);
    
    List<Usuario> listarMagistradosComProcessosConclusosNaUnidade(ProcessoConcluso processoConcluso);
    
    List<Usuario> listarMagistradosComProcessosConclusosNaUnidadeMesAnterior(ProcessoConcluso processoConcluso, List<Long> processosConclusosMesAtual);

    void deletarProcessosConclusosSubsequentes(ProcessoConcluso processoConcluso);
    
    void deletarProcessosConclusosAtualESubsequentes(ProcessoConcluso processoConcluso);
}
