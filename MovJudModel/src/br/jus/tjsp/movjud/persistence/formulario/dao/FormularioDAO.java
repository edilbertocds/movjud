package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Formulario;
import br.jus.tjsp.movjud.persistence.entity.PermissaoUnidadeTemporaria;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.List;

import javax.ejb.Local;

@Local
public interface FormularioDAO extends BaseDAO<Formulario>{
    
    List<Formulario> listarFormulariosPorCodigo(Formulario filter, Paginacao paginacao);
    
    List<Formulario> listarFormulariosOrdenadoPorDescricao(Formulario filter);
    
    List<Formulario> listarFormulariosDoUsuario(Formulario filtro, Usuario usuario, Paginacao paginacao);
    
    List<Formulario> listarFormulariosPorSituacoes(String... tiposSituacao);
    
    List<Formulario> listarFormularioGeral(Formulario filtro, List<String> listaTipoSituacao);
    
    List<Formulario> listarFormularioGeral(Formulario filtro, List<String> listaTipoSituacao, List<PermissaoUnidadeTemporaria> listaPermissao);
    
    List<Formulario> listarFormularioGeralComPaginacao(Formulario filtro, Paginacao paginacao, List<String> listaTipoSituacao);
    
    List<Formulario> listarFormularioGeralComPaginacaoPermissao(Formulario filtro, Paginacao paginacao, List<String> listaTipoSituacao, List<PermissaoUnidadeTemporaria> listaPermissao);
    
    List<Formulario> listarFormularioGeralComPaginacaoUnidade(Formulario filtro, Paginacao paginacao, List<String> listaTipoSituacao, List<Unidade> listaUnidade);
    
    Long countFormulariosAnoMesReferencia(int ano, int mes);
    
    List<Formulario> listarFormulariosComFiltros(List<Long> listaAno, List<Long> listaMes, Long idUnidade, String descricaoSourceFormulario);

    Formulario recuperarFormularioMesAnterior(Formulario formularioAtual);
    
    Formulario recuperarFormularioAnoMesReferencia(Formulario filtro);
    
    Formulario recuperarPrimeiroFormularioUnidade(Formulario filtro);
    
    Formulario recuperarFormularioPorIdFormulario(Long idFormulario);
    
    void updateSituacaoFormulario(Long idFormulario, Long idSituacaoAntiga, Long idSituacaoNova, Long idUsuario, String motivo);
}
