package br.jus.tjsp.movjud.business.formulario.service;

import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.CompetenciaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.GrupoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.HistoricoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.LiberacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.MateriaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.MetadadoSituacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ProcessoConclusoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ProcessosConclusosCpcDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ReuDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SegmentoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SituacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SubSecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoConclusoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoMateriaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoRegraDTO;
import br.jus.tjsp.movjud.business.utils.helper.IndicadorProgressoUtil;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.Formulario;
import br.jus.tjsp.movjud.persistence.entity.FormularioVinculacao;
import br.jus.tjsp.movjud.persistence.entity.MetadadosCampo;
import br.jus.tjsp.movjud.persistence.entity.MetadadosFormulario;
import br.jus.tjsp.movjud.persistence.entity.MetadadosGrupoCampo;
import br.jus.tjsp.movjud.persistence.entity.MetadadosTipoRegra;
import br.jus.tjsp.movjud.persistence.entity.PeriodoProcessoConcluso;
import br.jus.tjsp.movjud.persistence.entity.PreCarga;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorio;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorioHistorico;
import br.jus.tjsp.movjud.persistence.entity.TipoMotivoBaixa;
import br.jus.tjsp.movjud.persistence.entity.TipoNaturezaPrisao;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.ejb.Asynchronous;
import javax.ejb.Local;

@Local
public interface FormularioService {
        
    FormularioDTO criarCopiaFomularioDTO(FormularioDTO formularioDTO);
        
    List<MetadadosGrupoCampo> listarMetadadosGrupoCamposComFiltro(CampoDTO campoDTO);
    
    List<CampoDTO> listarMetadadosCamposComFiltro(CampoDTO campoDTO);
    
    List<CampoDTO> listarMetadadosCamposReaproveitamentoComPaginacao(CampoDTO campoDTO,Paginacao paginacao, List<CampoDTO> listaCampos);
    
    boolean dominioBIExistente(CampoDTO dominioBI);
    
    boolean dominioBIExistente(GrupoDTO dominioBI);
    
    MetadadosCampo salvarMetadadosCampo(MetadadosCampo metadadosCampo);
    
    FormularioDTO liberarVersaoMetadadosFormulario(FormularioDTO formularioDTO);
    
    FormularioDTO salvarMetadadosFormulario(FormularioDTO formularioDTO);
    
    List<FormularioDTO> listarMetadadosFormulariosComFiltro(FormularioDTO formularioDTO, Paginacao paginacao);

    List<CompetenciaDTO> listarTipoCompetencia();
    
    List<MateriaDTO> listarTipoAreas();
    
    List<SegmentoDTO> listarTipoSegmentos();
    
    List<MetadadoSituacaoFormularioDTO> listarMetadadosTipoSituacao();
    
    List<SituacaoFormularioDTO> listarTipoSituacao();
    
    void excluirMetadadosFormulario(FormularioDTO formularioDTO);
    
    List<TipoRegraDTO> listarMetadadosTipoRegra();
    
    TipoRegraDTO salvarMetadadosTipoRegra(TipoRegraDTO tipoRegraDTO);
    
    List<FormularioVinculacao> listarFormularioVinculacao(FormularioVinculacao filter);
    
    List<MetadadosFormulario> listarMetadadosFormulariosEmUso();

    List<MetadadosTipoRegra> listarTiposRegra();
    
    Unidade obterUnidade(Unidade unidade);
    
    List<TipoMateriaDTO> listarTipoMaterias();
    
    TipoMateriaDTO salvarTipoMateriaDTO(TipoMateriaDTO tipoMateriaDTO);
    
    List<FormularioDTO> listarFormulariosComFiltro(FormularioDTO formularioDTO, Paginacao paginacao);
    
    List<FormularioDTO> listarFormulariosComFiltros(List<Long> listaAno, List<Long> listaMes, Long idUnidade, String descricaoSourceFormulario);
    
    List<FormularioDTO> listarFormulariosDoUsuario(FormularioDTO filtro, Usuario usuario, Paginacao paginacao); 
    
    List<Formulario> listarFormulariosDaUnidade(Unidade unidade);
    
    List<Formulario> listarFormulariosMesAnoReferenciaDaUnidade(Unidade unidade);
    
    List<Unidade> listarUnidadesComFiltroPaginacao(Unidade unidade, Paginacao paginacao);
    
    List<Usuario> listarUsuarioMagistradoPorNomeComPaginacao(Usuario filter, Paginacao paginacao);
    
    Unidade salvarUnidade(Unidade unidade);
   
    List<TipoNaturezaPrisao> listarTipoNaturezaPrisaoOrdenadoPorNome();
    
    List<TipoMotivoBaixa> listarTipoMotivoBaixaOrdenadoPorNome();
    
    List<EstabelecimentoPrisional> listarEstabelecimentoPrisionalOrdenadoPorNome(EstabelecimentoPrisional estabelecimentoPrisional);
    
    List<EstabelecimentoPrisional> listarEstabelecimentoPrisionalOrdenadoPorNomePaginacao(EstabelecimentoPrisional estabelecimentoPrisional,Paginacao paginacao);
   
    List<Unidade> carregarUnidadesDevedoras();
   
    FormularioDTO salvarFormulario(FormularioDTO formularioDTO, SecaoDTO secaoMagistrado, SecaoDTO secaoReus, SubSecaoDTO subsecaoCpcDTO);
    FormularioDTO salvarFormulario(FormularioDTO formularioDTO, SecaoDTO secaoMagistrado, SecaoDTO secaoReus, SubSecaoDTO subsecaoCpcDTO, Map<Long, ProcessoConclusoDTO> listaRemoverProcessoConclusoDTO);
    
    // <epr> Parâmetro para passar situacaoFormularioDTO
    void liberarFormulariosParaUnidade(Unidade unidade, SituacaoFormularioDTO situacaoFormularioDTO);
    // </epr>
    
    void liberarFormulariosTodasUnidades(IndicadorProgressoUtil indicadorProgresso);
    
    FormularioDTO salvarMetadadosFormulario(FormularioDTO formularioDTO, FormularioDTO formularioHistorico);
    
    void atualizarMagistradosFormularios(List<FormularioDTO> listaFormularios, Usuario usuario);
    
    List<TipoConclusoDTO> listarTipoProcessoConclusoPorDescricao();
    
    TipoConclusoDTO obterTipoConclusoPorId(Long id);

    List<FormularioDTO> listarFormulariosGeral(List<String> listaTipoSituacao ,FormularioDTO filtro);
    
    List<FormularioDTO> listarFormulariosGeralComPaginacao(List<String> listaTipoSituacao ,FormularioDTO filtro, Paginacao paginacao, Usuario usuarioLogado);

    List<PreCarga> listarCamposPreCarga(PreCarga preCarga);    
    
    LiberacaoFormularioDTO countFormulariosLiberados();
    
    ProcessosConclusosCpcDTO listarProcessosConclusosPorUnidade(ProcessoConclusoDTO processoConclusoDTO);
    
    List<ProcessoConclusoDTO> listarProcessosConclusosMagistradoPorUnidade(ProcessoConclusoDTO processoConclusoDTO);

    List<ProcessoConclusoDTO> listarProcessosConclusosMesAnterior(ProcessoConclusoDTO filtroProcesso, List<BigDecimal> listaNumeroProcessos);

    ProcessoConclusoDTO listarProcessoConclusosMaisAntigo(ProcessoConclusoDTO filtroProcesso);

    List<ProcessoConclusoDTO> listarProcessosConclusosMesesSubsequentes(ProcessoConclusoDTO filtroProcesso);

    List<ProcessoConclusoDTO> listarProcessosConclusosMesesAnteriores(ProcessoConclusoDTO filtroProcesso);

    List<Usuario> listarMagistradosProcessosConclusosUnidade(Long unidade, Integer ano, Integer mes, String sourceFormulario);
    
    boolean validarPeriodoConclusoNoMesAnoReferencia(PeriodoProcessoConcluso filtro);
    
    PeriodoProcessoConcluso recuperarPeriodoProcessoConclusoPorAnoMes(PeriodoProcessoConcluso filtro);
    
    PeriodoProcessoConcluso recuperarAnoMesPorPeriodoProcessoConcluso(PeriodoProcessoConcluso filtro);
    
    SubSecaoDTO incluirValoresPreCarga(SubSecaoDTO subSecaoDTO, List<PreCarga> listaCamposPreCarga);
    
    List<ReuDTO> listarReusHistoricoMesAnoReferencia(List<ReuDTO> listaReusDTO, FormularioDTO formularioDTO);
    
    boolean validarReuHistoricoMesesAnteriores(ReuProvisorioHistorico reuHist);
    
    List<ReuDTO> listarReusProvisorioUnidade(ReuProvisorio filtro, Integer ano, Integer mes);
    
    Long countUtilizacaoCampo(CampoDTO filtro);
    
    FormularioDTO recuperarFormularioMesAnterior(FormularioDTO formularioAtualDTO);
    
    FormularioDTO recuperarFormularioMesAnoReferencia(FormularioDTO filtro);
    
    FormularioDTO recuperarPrimeiroFormularioUnidade(FormularioDTO filtro);
    
    Formulario recuperarFormularioPorIdFormulario(Long idFormulario);
    
    FormularioDTO recuperarFormularioDTOPorIdFormulario(Long idFormulario);
    
    void updateSituacaoFormulario(Long idFormulario, Long idSituacaoAntiga, Long idSituacaoNova, Long idUsuario, String motivo);
    
    @Asynchronous
    Future<List<SecaoDTO>> asyncCompleteFormularioDTO(FormularioDTO formularioDTO);
    
    @Asynchronous
    Future<List<HistoricoFormularioDTO>> asyncCompleteHistoricoFormularioDTO(FormularioDTO formularioDTO);
    
    FormularioDTO recuperarMetadadosFormulario(FormularioDTO formularioDTO);    
}
