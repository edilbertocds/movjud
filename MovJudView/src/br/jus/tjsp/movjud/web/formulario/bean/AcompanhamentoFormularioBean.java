package br.jus.tjsp.movjud.web.formulario.bean;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.base.dto.BaseObject;
import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.business.formula.utils.FormulaCalculo;
import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.EstabelecimentoEntidadeDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ProcessoConclusoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ProcessosConclusosCpcDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ReuDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SituacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SubSecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoConclusoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoMateriaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoRegraDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ValidacaoDTO;
import br.jus.tjsp.movjud.business.formulario.helper.FormularioUtils;
import br.jus.tjsp.movjud.business.formulario.service.FormularioService;
import br.jus.tjsp.movjud.business.formulario.types.SecaoType;
import br.jus.tjsp.movjud.business.formulario.types.TipoJuizType;
import br.jus.tjsp.movjud.business.formulario.types.TipoValidacaoType;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.types.TipoSituacaoType;
import br.jus.tjsp.movjud.persistence.entity.BaseEntity;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.Foro;
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.PeriodoProcessoConcluso;
import br.jus.tjsp.movjud.persistence.entity.PreCarga;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorio;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorioHistorico;
import br.jus.tjsp.movjud.persistence.entity.TipoMotivoBaixa;
import br.jus.tjsp.movjud.persistence.entity.TipoNaturezaPrisao;
import br.jus.tjsp.movjud.persistence.entity.TipoSituacao;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;
import br.jus.tjsp.movjud.web.relatorio.ExtensaoRelatorio;
import br.jus.tjsp.movjud.web.relatorio.GeracaoRelatorio;
import br.jus.tjsp.movjud.web.relatorio.RelatorioFormulario;
import br.jus.tjsp.movjud.web.relatorio.Template;

import java.io.IOException;
import java.io.OutputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.util.ResetUtils;

import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.util.Service;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;

public class AcompanhamentoFormularioBean extends BaseBean<FormularioDTO> {

    private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(AcompanhamentoFormularioBean.class);

    private Template templateRelatorio;
    private ExtensaoRelatorio extensaoRelatorio;
    private transient RelatorioFormulario relatorioFormulario;
    private transient GeracaoRelatorio gerarRelatorio;

    FormularioService formularioService;
    EstruturaJudiciariaService estruturaJudiciariaService;

    private StringBuilder consistencia;
    private StringBuilder inconsistenciaDataBaixa;
    private String inconsistenciaRemoverProcessoConcluso;
    private String periodoProcessoConclusoInicio;
    private String periodoProcessoConclusoFim;
    private Calendar periodoProcessoConclusoInicioCalendar =
        Calendar.getInstance(); // ultimoDiaMesReferenciaAnteriorCemDias.setTime(periodo.getDataInicio());
    private Calendar periodoProcessoConclusoFimCalendar =
        Calendar.getInstance(); // ultimoDiaMesReferenciaMenosCemDias.setTime(periodo.getDataFim());
    /*
    private Calendar ultimoDiaMesReferenciaMenosCemDias = Calendar.getInstance();
    private Calendar ultimoDiaMesReferenciaAnteriorCemDias = Calendar.getInstance();
    */

    private SubSecaoDTO subSecaoProcessoConclusoDTO = new SubSecaoDTO();

    private boolean mostrarAviso;
    private Usuario magistrado;
    private RichPanelGroupLayout painelPrincipal;
    private SecaoDTO secaoDadosUnidade;
    private SecaoDTO secaoMagistrado;
    private SecaoDTO secaoEstabelecimento;
    private SecaoDTO secaoMateria;
    private SecaoDTO secaoReus;

    private Map<Long, Boolean> tiposRegraFormulario;

    private boolean controleValidacaoAviso;
    private ProcessoConclusoDTO processoConclusoDTO;
    private Map<Long, ProcessoConclusoDTO> listaRemoverProcessoConclusoDTO;
    private Long idMagistrado;
    private TipoMateriaDTO materiaSubSecao;
    private SubSecaoDTO subSecaoMateriaRemover;
    private SubSecaoDTO subSecaoMagistradoRemover;
    private ReuDTO reu;
    private ReuDTO reuCopia;
    private List<TipoNaturezaPrisao> listaTipoNaturezaPrisao;
    private List<TipoMotivoBaixa> listarTipoMotivoBaixa;
    private List<TipoJuizType> listaTipoJuiz;
    private List<TipoConclusoDTO> listaProcessosConclusos;
    private List<SituacaoFormularioDTO> listaTipoSituacaoCadForm;
    private EstabelecimentoPrisional estabelecimentoPrisionalReu;
    private EstabelecimentoEntidadeDTO estabelecimentoEntidadeDTO;
    private String filtroReuProvisorio;
    private List<ReuDTO> listaReusProvisoriosFiltrada;
    private List<ReuDTO> listaRemoverReusDTO;
    private boolean funcaoRetificacao;

    //Componentes de telas de Consultas
    private Foro foro;
    private List<Unidade> listaUnidade;
    private List<Foro> listaForo;
    private List<SelectItem> listaMes;
    private List<SelectItem> listaAno;
    private List<SituacaoFormularioDTO> listaSituacao;
    private boolean administrador;
    private Usuario usuarioLogado;
    private String action;
    private Usuario usuarioMagistrado;
    private String sugestaoMagistrado;
    private String motivoDevolucao;

    private List<String> listaTipoSituacaoConsulta;

    private List<Unidade> listaUnidadeFormulario;
    private String unidadeFormularioMagistrado;
    private Long mesReferencia;
    private Long anoReferencia;

    private List<Long> listaMesReferencia;
    private List<Long> listaAnoReferencia;

    private String tituloPagina;
    private List<FormularioDTO> listaFormulariosRetificacao;

    private FormularioDTO formularioEnvio;
    private FormularioDTO formularioMesAnterior;

    private static final String APROVAR = "APROVAR";
    private static final String REPROVAR = "REPROVAR";

    /**Constantes do MB */

    //Origens de tela
    private static final String ORIGEM_TELA_CONSULTAR = "CONSULTAR";
    private static final String ORIGEM_TELA_ENVIAR = "ENVIAR";
    private static final String ORIGEM_TELA_PREENCHER = "PREENCHER";
    private static final String ORIGEM_TELA_RETIFICAR = "RETIFICAR";
    private static final String ORIGEM_TELA_AVALIAR = "AVALIAR";

    private static final String TITULO_TELA_CONSULTAR = "label.consultarFormulario.consultarFormulario";
    private static final String TITULO_TELA_ENVIAR = "menu.formularios.consultarEnviar";
    private static final String TITULO_TELA_PREENCHER = "menu.formularios.consultarPreencher";
    private static final String TITULO_TELA_RETIFICAR = "label.consultarFormulario.consultarRetificacoes";
    private static final String TITULO_TELA_AVALIAR = "label.consultarFormulario.avaliarRetificar";

    private static final String VALIDACAO_MAGISTRADO_PROCESSOS = "VALIDACAO_MAGISTRADO_PROCESSOS";
    private static final String VALIDACAO_MAGISTRADO_APROVADOR = "VALIDACAO_MAGISTRADO_APROVADOR";
    private static final String VALIDACAO_REUS_ULTIMO_MOVIMENTO = "VALIDACAO_REUS_ULTIMO_MOVIMENTO";
    private static final String VALIDACAO_ESTAB_PRISIONAIS = "VALIDACAO_ESTAB_PRISIONAIS";
    private static final String VALIDACAO_CAMPOS_ZERADOS = "VALIDACAO_CAMPOS_ZERADOS";

    private static final int ANO_REGRA_PROCESSO_CONCLUSO = 2016;

    private int colunas;
    private boolean alterarMagistrado;
    private RichPopup popupAdicionarProcessoConcluso;
    private RichPopup popupMensagemRetificarProcessoConcluso;
    private RichPopup popupConsistencias;
    private RichPopup popupBaixaProcessoConcluso;
    private RichPopup popupDataFimDesignacao;
    private RichPopup popupDataBaixaRetificar;
    private ProcessoConclusoDTO processoConclusoClone;
    private RichPopup popupInconsistenciasReus;
    private RichPopup popupAlterarReu;
    private RichPopup popupRemoverReuInconsistencia;
    private RichPopup popupErroValidacao;
    private RichPopup popupAvisoValidacao;
    private RichPopup popupConfirmacaoValidacao;
    private Map<TipoValidacaoType, List<ValidacaoDTO>> mapValidacao;
    private String mensagemValidacao;
    private String tipoValidacao;
    private ValidacaoDTO validacaoCorrente;
    private RichPopup popupInfoDataPrisaoReu;
    private RichPopup popupIncluirReus;
    private boolean edicaoReu;
    private RichPopup popupInconsistenciaProcessoCemDias;
    private RichPopup popupRemoverProcessoConclusoForaDoPeriodo;

    private boolean atualizarPainelPrincipal = false;

    private boolean processoConclusoUnidadeBoolean = false;

    private boolean existeFormularioPreenchidoAnteriormente = false;

    private boolean triggerRecalc = false;
    
    private boolean isRetificacao = false;

    public AcompanhamentoFormularioBean() {
        formularioService = (FormularioService) getBean(FormularioService.class);
        estruturaJudiciariaService = (EstruturaJudiciariaService) getBean(EstruturaJudiciariaService.class);

        extensaoRelatorio = ExtensaoRelatorio.EXTENSAO_PDF;
        templateRelatorio = Template.FORMULARIO;
        relatorioFormulario = new RelatorioFormulario();
        gerarRelatorio = new GeracaoRelatorio();
    }

    /* PostConstruct executa logo apos o managedBean ser instanciado  */
    //@PostConstruct
    @Override
    public void init() {
        super.initDefault();
        calcularColuna();
        listaTipoNaturezaPrisao = formularioService.listarTipoNaturezaPrisaoOrdenadoPorNome();
        listarTipoMotivoBaixa = formularioService.listarTipoMotivoBaixaOrdenadoPorNome();
        listaTipoSituacaoCadForm = formularioService.listarTipoSituacao();
        listaTipoJuiz = Arrays.asList(TipoJuizType.values());
        listaProcessosConclusos = formularioService.listarTipoProcessoConclusoPorDescricao();
        listaRemoverProcessoConclusoDTO = new HashMap<Long, ProcessoConclusoDTO>();
        usuarioLogado = getLoginBean().getUsuarioSessao();
        //mockUser();
        action = acaoPageFlow();
        //throw new MovJudViewException("DEU RUIM");
        listaRemoverReusDTO = new ArrayList<ReuDTO>();
        inicializarConsultar();
    }

    public Date getPrimeiroDiaMesreferencia() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, entidadePersistencia.getAno().intValue());
        calendar.set(Calendar.MONTH, entidadePersistencia.getMes().intValue() - 1);
        return calendar.getTime();
    }

    public Date getUltimoDiaMesReferencia() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, entidadePersistencia.getAno().intValue());
        calendar.set(Calendar.MONTH, entidadePersistencia.getMes().intValue() - 1);
        calendar.add(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
        return calendar.getTime();
    }

    private String acaoPageFlow() {
        ADFContext context = ADFContext.getCurrent();
        return (String) context.getPageFlowScope().get("controleFluxo");
    }


    /** InÃƒÂ­cio Consulta Geral  **/
    private void inicializarConsultar() {
        //Consultar Geral
        inicializarVariaveisConsulta();
        usuarioMagistrado = new Usuario();
        foro = new Foro();
        foro.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        if (verificarPerfilUsuario() != null) {
            administrador = verificarPerfilUsuario();
            if (administrador) {
                popularListasAdm();
            } else {
                //entidadeFiltro.setNomeMagistrado(usuarioLogado.getNome());
                popularListasNaoAdm();
            }
            popularListaMes();
            popularListaAno();
            popularListaSituacao();
        }
        //if(listaEntidade == null) -- não realizar a consulta mesmo que não estiver nulo, não funciona a paginação. 01.06.2018
        pesquisarEntidadeConsultarGeral();
    }

    private void inicializarVariaveisConsulta() {
        zerarEntidadeFiltro();
        zerarCamposRodape();
        listaUnidade = new ArrayList<>();
        listaForo = new ArrayList<>();
        listaMes = new ArrayList<SelectItem>();
        listaAno = new ArrayList<SelectItem>();
        listaSituacao = new ArrayList<>();
        listaTipoSituacaoConsulta = new ArrayList<>();
        usuarioMagistrado = new Usuario();
        listaMesReferencia = new ArrayList<Long>();
        listaAnoReferencia = new ArrayList<Long>();
        listaFormulariosRetificacao = new ArrayList<FormularioDTO>();
        alterarMagistrado = true;
    }

    private void zerarCamposRodape() {
        motivoDevolucao = "";
        unidadeFormularioMagistrado = "";
        anoReferencia = new Long(0);
        mesReferencia = new Long(0);
        sugestaoMagistrado = "";
        formularioEnvio = new FormularioDTO();
    }

    private void zerarEntidadeFiltro() {
        entidadeFiltro.setAno(new Long(0));
        entidadeFiltro.setMes(new Long(0));
        entidadeFiltro.setIdForo(0L);
        entidadeFiltro.setIdUnidade(0L);
        entidadeFiltro.setSituacaoFormularioDTO(new SituacaoFormularioDTO());
        entidadeFiltro.getSituacaoFormularioDTO().setCodigoSituacaoFormulario(0L);
        entidadeFiltro.setIdUsuarioPreenchimento(0L);
        entidadeFiltro.setNomeUsuarioPreenchimento("");
        entidadeFiltro.setIdMagistrado(0L);
        entidadeFiltro.setNomeMagistrado("");
    }

    /** Regras de perfis e de tipo situaçÃƒÂµes para cada tela*/

    /**
     * Regra Consultar Geral:
     * FormulÃƒÂ¡rios com status "Enviado CGJ", "Retificação Reprovada", "Retificação Aprovada",
     *      "Retificação Em Preenchimento", "Retificação ConcluÃƒÂ­da", "Retificação Devolvida" e
     *      "Retificação Enviada ao CGJ".
     *
     * Regra Consultar/Preencher:
     * FormulÃƒÂ¡rios com status "Aberto", "Em preenchimento", "Devolvido", e "ConcluÃƒÂ­do".
     *
     * Regra Consultar/Enviar:
     * FormulÃƒÂ¡rios com status "Concluido" e "Retificaçao Concluida".
     *
     * Regra Consultar/Retificar:
     * FormulÃƒÂ¡rios com status "Retificação Reprovada", "Retificação Aprovada",
     *      "Retificação Em Preenchimento", "Retificação ConcluÃƒÂ­da", "Retificação Devolvida" e
     *      "Retificação Enviada ao CGJ".
     *
     */
    private void popularListaTiposSituacaoRegra() {
        listaTipoSituacaoConsulta = new ArrayList<String>();
        if (ORIGEM_TELA_CONSULTAR.equals(action) || ORIGEM_TELA_RETIFICAR.equals(action)) {
            listaTipoSituacaoConsulta.add(TipoSituacaoType.ENVIADO_CGJ.getCodigo());
            listaTipoSituacaoConsulta.add(TipoSituacaoType.RETIFICACAO_REPROVADA.getCodigo());
            listaTipoSituacaoConsulta.add(TipoSituacaoType.RETIFICACAO_APROVADA.getCodigo());
            listaTipoSituacaoConsulta.add(TipoSituacaoType.RETIFICACAO_EM_PREENCHIMENTO.getCodigo());
            listaTipoSituacaoConsulta.add(TipoSituacaoType.RETIFICACAO_CONCLUIDA.getCodigo());
            listaTipoSituacaoConsulta.add(TipoSituacaoType.RETIFICACAO_DEVOLVIDA.getCodigo());
            listaTipoSituacaoConsulta.add(TipoSituacaoType.RETIFICACAO_ENVIADA_AO_CGJ.getCodigo());
        } else if (ORIGEM_TELA_PREENCHER.equals(action)) {
            listaTipoSituacaoConsulta.add(TipoSituacaoType.ABERTO.getCodigo());
            listaTipoSituacaoConsulta.add(TipoSituacaoType.EM_PREENCHIMENTO.getCodigo());
            listaTipoSituacaoConsulta.add(TipoSituacaoType.CONCLUIDO.getCodigo());
            listaTipoSituacaoConsulta.add(TipoSituacaoType.DEVOLVIDO.getCodigo());
        } else if (ORIGEM_TELA_ENVIAR.equals(action)) {
            listaTipoSituacaoConsulta.add(TipoSituacaoType.CONCLUIDO.getCodigo());
            listaTipoSituacaoConsulta.add(TipoSituacaoType.RETIFICACAO_CONCLUIDA.getCodigo());
            if (verificarPerfilUsuario() != null && !verificarPerfilUsuario()) {
                usuarioMagistrado = usuarioLogado;
            }
        } else if (ORIGEM_TELA_AVALIAR.equals(action)) {
            listaTipoSituacaoConsulta.add(TipoSituacaoType.RETIFICACAO_SOLICITADA.getCodigo());
        }
    }

    private Boolean verificarPerfilUsuario() {
        entidadeFiltro.setPerfilConsulta(usuarioLogado.getPerfil().getCodigoPerfil());
        if (ConstantesMovjud.PERFIL_COD_ADMIN.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
            return true;
        }

        if (ORIGEM_TELA_PREENCHER.equals(action)) {
            if (ConstantesMovjud.PERFIL_COD_RESPONSAVEL.equals(usuarioLogado.getPerfil().getCodigoPerfil()) ||
                ConstantesMovjud.PERFIL_COD_FUNCIONARIO.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                return false;
            }
        }

        if (ORIGEM_TELA_ENVIAR.equals(action)) {
            if (ConstantesMovjud.PERFIL_COD_MASSESCORR.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                return true;
            }
            if (ConstantesMovjud.PERFIL_COD_DESEMBARGADOR.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                return true;
            }
            if (ConstantesMovjud.PERFIL_COD_MAGISTRADO.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                entidadeFiltro.setNomeMagistrado(usuarioLogado.getNome());
                entidadeFiltro.setIdMagistrado(usuarioLogado.getIdUsuario());
                return false;
            }
        }

        if (ORIGEM_TELA_RETIFICAR.equals(action)) {
            if (ConstantesMovjud.PERFIL_COD_RESPONSAVEL.equals(usuarioLogado.getPerfil().getCodigoPerfil()) ||
                ConstantesMovjud.PERFIL_COD_FUNCIONARIO.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                return false;
            }
        }

        if (ORIGEM_TELA_CONSULTAR.equals(action)) {
            if (ConstantesMovjud.PERFIL_COD_MASSESCORR.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                return true;
            }
            if (ConstantesMovjud.PERFIL_COD_DESEMBARGADOR.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                return true;
            }
            if (ConstantesMovjud.PERFIL_COD_MAGISTRADO.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                entidadeFiltro.setNomeMagistrado(usuarioLogado.getNome());
                entidadeFiltro.setIdMagistrado(usuarioLogado.getIdUsuario());
                return false;
            }
            if (ConstantesMovjud.PERFIL_COD_CONSULTA.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                return true;
            }
        }

        /* ------------------
        if (ORIGEM_TELA_RETIFICAR.equals(action) || ORIGEM_TELA_CONSULTAR.equals(action)) {
            if (ConstantesMovjud.PERFIL_COD_RESPONSAVEL.equals(usuarioLogado.getPerfil().getCodigoPerfil()) ||
                ConstantesMovjud.PERFIL_COD_FUNCIONARIO.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                return false;
            }
        } else if (ORIGEM_TELA_PREENCHER.equals(action)) {
            if (ConstantesMovjud.PERFIL_COD_RESPONSAVEL.equals(usuarioLogado.getPerfil().getCodigoPerfil()) ||
                ConstantesMovjud.PERFIL_COD_FUNCIONARIO.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                return false;
            }
        } else if (ORIGEM_TELA_ENVIAR.equals(action)) {
            if (ConstantesMovjud.PERFIL_COD_MAGISTRADO.equals(usuarioLogado.getPerfil().getCodigoPerfil()) ||
                ConstantesMovjud.PERFIL_COD_DESEMBARGADOR.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                entidadeFiltro.setNomeMagistrado(usuarioLogado.getNome());
                entidadeFiltro.setIdMagistrado(usuarioLogado.getIdUsuario());
                return false;
            }
        } else if (ORIGEM_TELA_AVALIAR.equals(action)) {
            return null;
        }
        */
        return null;
    }
    
    public boolean isRetificacao() {
        return this.isRetificacao;
    }

    private void calcularColuna() {
        if (ORIGEM_TELA_CONSULTAR.equals(action)) {
            tituloPagina = AppBundleProperties.getString(TITULO_TELA_CONSULTAR);
            colunas = 130;
        } else if (ORIGEM_TELA_RETIFICAR.equals(action)) {
            tituloPagina = AppBundleProperties.getString(TITULO_TELA_RETIFICAR);
            colunas = 125;
        } else if (ORIGEM_TELA_PREENCHER.equals(action)) {
            tituloPagina = AppBundleProperties.getString(TITULO_TELA_PREENCHER);
            colunas = 95;
        } else if (ORIGEM_TELA_ENVIAR.equals(action)) {
            tituloPagina = AppBundleProperties.getString(TITULO_TELA_ENVIAR);
            colunas = 112;
        } else if (ORIGEM_TELA_AVALIAR.equals(action)) {
            isRetificacao = true;
            tituloPagina = AppBundleProperties.getString(TITULO_TELA_AVALIAR);
            colunas = 187;
        }
    }

    /** Montagem das listas e validaçÃƒÂµes**/
    private void popularListasAdm() {
        listaUnidade = new ArrayList<>();
        listaForo = estruturaJudiciariaService.listarForoComFiltro(foro);
    }

    private void popularListasNaoAdm() {
        listaUnidade = new ArrayList<>();
        listaUnidade = estruturaJudiciariaService.listarUnidadesCodigoDescricaoPorUsuario(usuarioLogado);
        Set<String> hashForos = new HashSet<String>();
        for (Unidade u : listaUnidade) {
            hashForos.add(u.getForo().getNomeForo());
        }
        listaForo = new ArrayList<Foro>();
        for (String s : hashForos) {
            Foro f = new Foro();
            f.setNomeForo(s);
            listaForo.addAll(estruturaJudiciariaService.listarForoComFiltro(f));
        }
    }

    private void popularListaSituacao() {
        popularListaTiposSituacaoRegra();
        List<TipoSituacao> lista = estruturaJudiciariaService.listarTipoSituacao();
        for (TipoSituacao tp : lista) {
            if (listaTipoSituacaoConsulta.contains(tp.getCodigoSituacao())) {
                SituacaoFormularioDTO situacao = new SituacaoFormularioDTO();
                situacao.setCodigoSituacaoFormulario(tp.getIdTipoSituacao());
                situacao.setLabelSituacaoFormulario(tp.getDescricaoSituacao());
                listaSituacao.add(situacao);
            }
        }
    }

    private void popularListaMes() {
        for (int i = 1; i <= 12; i++) {
            listaMes.add(new SelectItem(new Long(i), String.valueOf(i)));
        }
    }

    private void popularListaAno() {
        for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= 1990; i--) {
            listaAno.add(new SelectItem(new Long(i), String.valueOf(i)));
        }
    }

    public void filtrarProcessoConcluso(ActionEvent actionEvent) {
        atualizarComponenteDeTela("painelSecaoMagistrado");
    }

    public void filtrarProcessoConclusoUnidade(ActionEvent actionEvent) {
        atualizarComponenteDeTela("painelProcessoConclusoUnidade");
    }

    private void pesquisarEntidadeConsultarGeral() {
        if (verificarPerfilUsuario() != null) {
            listaEntidade =
                formularioService.listarFormulariosGeralComPaginacao(listaTipoSituacaoConsulta, entidadeFiltro,
                                                                     paginacao, usuarioLogado);

            // <epr-desempenho> teste lista
            /*for (FormularioDTO formularioDTO : listaEntidade) {
                formularioDTO.setFutureListaSecoes(formularioService.asyncCompleteFormularioDTO(formularioDTO));
                formularioDTO.setFutureListaHistoricoFormulario(formularioService.asyncCompleteHistoricoFormularioDTO(formularioDTO));
            }*/
            // </epr-desempenho> teste lista

            atualizarListasPreenchimentoMagistrado();
        } else {
            listaEntidade = new ArrayList<>();
        }
        // limpar listaFormulariosRetificação para refletir corretamente a seleção vizível.
        listaFormulariosRetificacao = new ArrayList<FormularioDTO>();

        zerarCamposRodape();
    }

    private void atualizarListasPreenchimentoMagistrado() {
        listaUnidadeFormulario = new ArrayList<>();
        Set<String> hashUnidade = new HashSet<String>();
        Set<Long> hashMes = new HashSet<Long>();
        Set<Long> hashAno = new HashSet<Long>();
        listaMesReferencia.clear();
        listaAnoReferencia.clear();
        if (listaEntidade != null) {
            for (FormularioDTO formulario : listaEntidade) {
                hashUnidade.add(formulario.getNomeUnidade());
                hashMes.add(formulario.getMes());
                hashAno.add(formulario.getAno());
            }
            for (String nomeUnidade : hashUnidade) {
                Unidade u = new Unidade();
                u.setNomeUnidade(nomeUnidade);
                listaUnidadeFormulario.add(u);
            }
            listaMesReferencia.addAll(hashMes);
            listaAnoReferencia.addAll(hashAno);
            Collections.sort(listaMesReferencia);
            Collections.sort(listaAnoReferencia);

        }
    }

    /** MÃƒÂ©todos para atualização de componentes de tela por ajax **/
    public void atualizarListaUnidade(ValueChangeEvent e) {
        foro.setIdForo(Long.parseLong(String.valueOf(e.getNewValue())));
        entidadeFiltro.setIdForo(foro.getIdForo());
        entidadeFiltro.setIdUnidade(0L);
        listaUnidade = estruturaJudiciariaService.listarUnidadesCodigoDescricaoPorForoEUsuario(foro, usuarioLogado);
        atualizarComponenteDeTela("inputFiltroUnidade");
    }

    /* Metodo para selecionar linha de tabela */
    @Override
    public void selecionarEntidadeEmTabela(SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        entidadeOriginal = (FormularioDTO) richTable.getSelectedRowData();
        entidadePersistencia = (FormularioDTO) entidadeOriginal.clonar();

        if (ORIGEM_TELA_RETIFICAR.equals(action)) {
            /*if (TipoSituacaoType.RETIFICACAO_REPROVADA.getCodigo().equals(entidadeOriginal.getSituacaoFormularioDTO().getIdentificadorSituacaoFormulario())) {
                alterarMagistrado = false;
            } else {
                // <epr> 0.7.19 Erro apontado pela Eliane
                // RETIFICAÃƒâ€¡ÃƒÆ’O APROVADA, RETIFICAÃƒâ€¡ÃƒÆ’O EM PREENCHIMENTO, RETIFICAÃƒâ€¡ÃƒÆ’O CONCLUIDA
                // alterarMagistrado = true;
                alterarMagistrado = entidadeOriginal.getSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_APROVADA.toString()) ||
                    entidadeOriginal.getSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_EM_PREENCHIMENTO.toString()) ||
                    entidadeOriginal.getSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_CONCLUIDA.toString());
                // </epr>
            }*/
            alterarMagistrado = true;
            if (entidadePersistencia.isFlagRetificacao()) {
                //listaFormulariosRetificacao.add(entidadePersistencia);
            } else {
                if (listaFormulariosRetificacao.contains(entidadePersistencia)) {
                    listaFormulariosRetificacao.remove(entidadePersistencia);
                }
            }
        }
        if (ORIGEM_TELA_PREENCHER.equals(action)) {
            unidadeFormularioMagistrado = entidadePersistencia.getNomeUnidade();
            mesReferencia = entidadePersistencia.getMes();
            anoReferencia = entidadePersistencia.getAno();
            usuarioMagistrado.setIdUsuario(entidadePersistencia.getIdMagistrado());
            usuarioMagistrado.setNome(entidadePersistencia.getNomeMagistrado());
            sugestaoMagistrado = usuarioMagistrado.getNome();
        }
        if (ORIGEM_TELA_ENVIAR.equals(action)) {
            formularioEnvio = (FormularioDTO) richTable.getSelectedRowData();
        }

        richTable.getSelectedRowKeys().clear();
    }

    /** Ações dos botões */
    public String enviarFormulario(DialogEvent dialogEvent) {

        entidadePersistencia = recuperarFormulario(entidadePersistencia);

        if (dialogEvent.getOutcome() ==
            DialogEvent.Outcome.ok) {
            //Mudar status de "CONCLUIDO" para "ENVIADO_CGJ"
            entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                 TipoSituacaoType.ENVIADO_CGJ));
            List<String> listaSituacao = new ArrayList<String>();
            if (TipoSituacaoType.CONCLUIDO
                                .getCodigo()
                                .equals(entidadePersistencia.getSituacaoFormularioDTO()
                                        .getIdentificadorSituacaoFormulario())) {
                //se for tipo situação = 'concluído', deve-se enviar todo o lote, conforme filtro mes/ano, unidade e magistrado

                listaSituacao.add(TipoSituacaoType.ABERTO.getCodigo());
                listaSituacao.add(TipoSituacaoType.EM_PREENCHIMENTO.getCodigo());
                listaSituacao.add(TipoSituacaoType.DEVOLVIDO.getCodigo());

                FormularioDTO filtro = new FormularioDTO();

                filtro.setIdUnidade(entidadePersistencia.getIdUnidade());
                filtro.setAno(entidadePersistencia.getAno());
                filtro.setMes(entidadePersistencia.getMes());
                filtro.setNomeMagistrado(entidadePersistencia.getNomeMagistrado());
                filtro.setIdMagistrado(entidadePersistencia.getIdMagistrado());
                filtro.setSituacaoFormularioDTO(new SituacaoFormularioDTO());

                List<FormularioDTO> listaForm = formularioService.listarFormulariosGeral(listaSituacao, filtro);

                if (listaForm != null && listaForm.size() > 0) {
                    mensagemErro(AppBundleProperties.getString("msg.formulario.formulariosAbertos"));
                    return "";
                } else {
                    listaSituacao.clear();
                    listaSituacao.add(TipoSituacaoType.CONCLUIDO.getCodigo());

                    //listaForm = formularioService.listarFormulariosGeral(listaSituacao, entidadeFiltro);
                    listaForm = formularioService.listarFormulariosGeral(listaSituacao, filtro);
                    for (FormularioDTO form : listaForm) {
                        form.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                             TipoSituacaoType.ENVIADO_CGJ));
                        form.setIdUsuarioAlteracao(usuarioLogado.getId());
                        formularioService.salvarFormulario(form, null, null, null);
                    }
                }
                //zerarEntidadeFiltro();
            } else {
                //se for retificação concluída

                entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                     TipoSituacaoType.RETIFICACAO_ENVIADA_AO_CGJ));
                entidadePersistencia.setIdUsuarioAlteracao(usuarioLogado.getId());
                formularioService.salvarFormulario(entidadePersistencia, null, null, null);
            }
            pesquisarEntidade();
        }
        return "";
    }

    public String solicitarRetificacao(DialogEvent dialogEvent) {
        //Mudar status de "CONCLUIDO" para "RETIFICACAO SOLICITADA"
        for (FormularioDTO form : listaFormulariosRetificacao) {
            logger.info(AppBundleProperties.getString("msg.formulario.logSolicitacaoRetificacao") +
                        entidadePersistencia.getNomeFormulario());
            form.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                 TipoSituacaoType.RETIFICACAO_SOLICITADA));
            form.setObservacaoHistorico(motivoDevolucao);
            //Preencher histórico
            form.setIdUsuarioAlteracao(usuarioLogado.getId());
            formularioService.salvarFormulario(form, null, null, null);
        }
        listaFormulariosRetificacao.clear();
        zerarCamposRodape();
        pesquisarEntidade();
        return "";
    }


    public void validarEnviarFormulario(ActionEvent actionEvent) {
        String msg = null;
        if (formularioEnvio == null || formularioEnvio.getIdFormulario() == null ||
            new Long(0).equals(formularioEnvio.getIdFormulario())) {
            msg = AppBundleProperties.getString("msg.formulario.nenhumFormularioSelecionadoParaEnviar");
        }
        if (msg == null) {
            UIComponent component = findComponent("confirmacaoEnviarFormularioPopUp");
            RichPopup popup = (RichPopup) component;
            popup.show(new RichPopup.PopupHints());
        } else {
            mensagemErro(msg);
        }
    }


    public void validarSolicitarRetificacao(ActionEvent actionEvent) {
        String msg = null;
        if (motivoDevolucao == null || "".equals(motivoDevolucao)) {
            msg = AppBundleProperties.getString("msg.formulario.motivoDevolucaoEmBranco");
        }
        listaFormulariosRetificacao = new ArrayList<FormularioDTO>();
        for (FormularioDTO form : listaEntidade) {
            if (form.isFlagRetificacao()) {

                form = recuperarFormulario(form);
                form.setFlagRetificacao(true);

                listaFormulariosRetificacao.add(form);
            }
        }
        if (listaFormulariosRetificacao.isEmpty()) {
            msg = AppBundleProperties.getString("msg.formulario.nenhumFormularioSelecionadoParaRetificacao");
        }

        for (FormularioDTO form : listaFormulariosRetificacao) {
            if (form.getSituacaoFormularioDTO()
                    .getIdentificadorSituacaoFormulario()
                    .equalsIgnoreCase(TipoSituacaoType.ENVIADO_CGJ.toString()) == false &&
                form.getSituacaoFormularioDTO()
                                                                                               .getIdentificadorSituacaoFormulario()
                                                                                               .equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_ENVIADA_AO_CGJ.toString()) ==
                                  false &&
                                  form.getSituacaoFormularioDTO()
                                               .getIdentificadorSituacaoFormulario()
                                               .equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_REPROVADA.toString()) ==
                                  false) {
                msg =
                    AppBundleProperties.getString("msg.formulario.formularioNaoPermitidoSelecionadoParaSolicitarRetificacao");
                break;
            }
        }

        if (msg == null) {
            UIComponent component = findComponent("confirmacaoSolicitarRetificacaoPopUp");
            RichPopup popup = (RichPopup) component;
            popup.show(new RichPopup.PopupHints());
        } else {
            mensagemErro(msg);
        }
    }

    /** Sugestao de usuário**/
    public List autoCompletarUsuario(String parametro) {
        usuarioMagistrado = new Usuario();
        usuarioMagistrado.setNome(parametro);
        // <epr> 0.7.16 lista com perfis MAGISTRADO, DESEMBARGADOR e MAGSITRADO ASSESSOR CORREGEDORIA
        /*
        usuarioMagistrado.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        Perfil p = new Perfil();
        p.setCodigoPerfil(ConstantesMovjud.PERFIL_COD_MAGISTRADO);
        usuarioMagistrado.setPerfil(p);

        listaParametros =
            estruturaJudiciariaService.listarUsuariosComFiltroPaginacao(usuarioMagistrado, paginacaoSeguestao);
        result.addAll(listaParametros);
        */
        listaParametros =
            formularioService.listarUsuarioMagistradoPorNomeComPaginacao(usuarioMagistrado, paginacaoSeguestao);
        // </epr> 0.7.16  lista com perfis MAGISTRADO, DESEMBARGADOR e MAGSITRADO ASSESSOR CORREGEDORIA
        return montarSelectItemEntidades(listaParametros, Usuario.class);
    }

    public <E extends BaseEntity> List<SelectItem> montarSelectItemEntidades(List listaParametros, Class<E> classe) {
        List<SelectItem> listaSugestoes = null;
        E entidade = null;
        try {
            entidade = classe.newInstance();
        } catch (Exception e) {
        }
        if (listaParametros != null) {
            listaSugestoes = new ArrayList<SelectItem>();

            for (Usuario item : ((List<Usuario>) listaParametros)) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(item.getNome());
                selectItem.setValue(item.getIdUsuario());
                listaSugestoes.add(selectItem);
            }
        }
        return listaSugestoes;
    }

    private boolean verificaExistenciaMagistradoSecao(Long idMagistrado) {
        boolean existe = false;
        
        if (secaoMagistrado != null && secaoMagistrado.getListaSubSecoes() != null && 
            !secaoMagistrado.getListaSubSecoes().isEmpty()) {
            
            for (SubSecaoDTO subSecaoMagistrado : secaoMagistrado.getListaSubSecoes()) {
                if (subSecaoMagistrado.getIdMagistrado().equals(idMagistrado)) {
                    existe = true;
                    break;
                }
            }
        }
        
        return existe;
    }

    public void alterarNomeUsuarioSelecionada(ValueChangeEvent valueChangeEvent) {
        try {
            usuarioMagistrado =
                (alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                      Usuario.class));
            sugestaoMagistrado = usuarioMagistrado.getNome();
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), usuarioMagistrado.getNome());
        } catch (Exception e) {
            usuarioMagistrado = new Usuario();
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), "");
        }
    }

    /** Fim Consulta Geral **/


    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        // TODO Implement this method
    }

    public void avaliarFormulario(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            if (entidadePersistencia.getAprovarRetificacao().equals(APROVAR)) {
                entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                     TipoSituacaoType.RETIFICACAO_APROVADA));
            } else {
                entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                     TipoSituacaoType.RETIFICACAO_REPROVADA));
            }
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.getApplication()
                .getNavigationHandler()
                .handleNavigation(fctx, null, persistirEntidade());
        }
    }

    public void devolverFormulario(DialogEvent dialogEvent) {
        if (entidadePersistencia.getSituacaoFormularioDTO()
                                .getIdentificadorSituacaoFormulario()
                                .equals(TipoSituacaoType.CONCLUIDO.getCodigo())) {
            entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                 TipoSituacaoType.DEVOLVIDO));
        } else {
            entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                 TipoSituacaoType.RETIFICACAO_DEVOLVIDA));

        }
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.getApplication()
            .getNavigationHandler()
            .handleNavigation(fctx, null, persistirEntidade());
    }

    private void concluirPreenchimento() {
        List<ReuDTO> listaReusInconsistentes = null;
        List<Usuario> listaInconsistenciasMagistrado = null;
        Map<String, Boolean> consistencias = new HashMap<String, Boolean>();
        if (secaoMagistrado != null && secaoMagistrado.isTabelaProcessos())
            listaInconsistenciasMagistrado =
                todosOsMagistradosNoFormulario(secaoMagistrado,
                                               formularioService.listarMagistradosProcessosConclusosUnidade(entidadePersistencia.getIdUnidade(),
                                                                                                            entidadePersistencia.getAno()
                                                                                                            .intValue(),
                                                                                                            entidadePersistencia.getMes()
                                                                                                            .intValue(),
                                                                                                            entidadePersistencia.getCodigoFormulario()));
        if (listaInconsistenciasMagistrado != null && !listaInconsistenciasMagistrado.isEmpty()) {
            consistencias.put(VALIDACAO_MAGISTRADO_PROCESSOS, true);
        }

        if (secaoReus != null) {
            listaReusInconsistentes = validarUltimoMovimentoSecaoReu(secaoReus, getUltimoDiaMesReferencia());
            if (listaReusInconsistentes != null && !listaReusInconsistentes.isEmpty()) {
                consistencias.put(VALIDACAO_REUS_ULTIMO_MOVIMENTO, true);
            }
        }

        if (secaoEstabelecimento != null) {
            if(!validarEstabelecimentosPrisionais(secaoEstabelecimento)) {
                consistencias.put(VALIDACAO_ESTAB_PRISIONAIS, true);
            }
        }

        if (entidadePersistencia.getIdMagistrado() == null) {
            consistencias.put(VALIDACAO_MAGISTRADO_APROVADOR, true);
        }
        
        if (!consistencias.containsValue(true)) {
            if (isSituacaoAbertoEmPreenchimentoConcluidoDevolvido()) {
                entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                     TipoSituacaoType.CONCLUIDO));
            } else {
                entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                     TipoSituacaoType.RETIFICACAO_CONCLUIDA));
            }
            entidadePersistencia.setDataConclusao(new Date());
            recalcularFormulas();
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.getApplication()
                .getNavigationHandler()
                .handleNavigation(fctx, null, persistirEntidade());
        } else {
            consistencia = new StringBuilder();
            if (consistencias.getOrDefault(VALIDACAO_MAGISTRADO_PROCESSOS, false)) {
                consistencia.append("<li>");
                consistencia.append(AppBundleProperties.getString("msg.formulario.validacaoMagistradoProcessoConcluso"));
                consistencia.append("<ul>");
                for (Usuario usuario : listaInconsistenciasMagistrado) {
                    consistencia.append("<p> - " + usuario.getNome() + "</p>");
                }
                consistencia.append("</ul></li>");
            }
            if (consistencias.getOrDefault(VALIDACAO_MAGISTRADO_APROVADOR, false)) {
                consistencia.append("<li>");
                consistencia.append(AppBundleProperties.getString("msg.formulario.validacaoMagistradoAprovador"));
                consistencia.append("</li><br>");
            }
            if (consistencias.getOrDefault(VALIDACAO_REUS_ULTIMO_MOVIMENTO, false)) {
                consistencia.append("<li>");
                consistencia.append(AppBundleProperties.getString("msg.formulario.validacaoReuUltimoMovimento"));
                consistencia.append("<ul>");
                for (ReuDTO reuDTO : listaReusInconsistentes) {
                    consistencia.append("<p> - " + reuDTO.getNomeReuProvisorio() + "</p>");
                }
                consistencia.append("</ul></li><br>");
            }
            if (consistencias.getOrDefault(VALIDACAO_ESTAB_PRISIONAIS, false)) {
                consistencia.append("<li>");
                consistencia.append(AppBundleProperties.getString("msg.formulario.validacaoEstabPrisionais"));
                consistencia.append("</li>");
            }
            popupConsistencias.show(new RichPopup.PopupHints());
        }
    }

    public void concluirPreenchimento(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            if (validarRegrasFormulas()) {
                logger.info(AppBundleProperties.getString("msg.formulario.logPreenchimentoConcluido") +
                            entidadePersistencia.getNomeFormulario());
                concluirPreenchimento();
            }
        }
    }


    private boolean validarRegrasFormulas() {
        boolean retorno = false;
        TipoValidacaoType typeValidacao = TipoValidacaoType.VALIDACAO_ERRO;
        tipoValidacao = "erro";
        mapValidacao = FormularioUtils.recuperarMensagensValidacaoFormula(entidadePersistencia, formularioMesAnterior);

        List<ValidacaoDTO> listaValidacao = mapValidacao.get(TipoValidacaoType.VALIDACAO_ERRO);
        if (listaValidacao.isEmpty()) {
            typeValidacao = TipoValidacaoType.VALIDACAO_CONFIRMACAO;
            tipoValidacao = "confirmacao";
            listaValidacao = mapValidacao.get(typeValidacao);
            if (listaValidacao.isEmpty() || validarConfirmacaoValidacao(listaValidacao)) {
                typeValidacao = TipoValidacaoType.VALIDACAO_AVISO;
                tipoValidacao = "aviso";
                listaValidacao = mapValidacao.get(typeValidacao);
                if (listaValidacao.isEmpty() || controleValidacaoAviso) {
                    retorno = true;
                    controleValidacaoAviso = false;
                }
            }
        }
        if (retorno == false)
            abrirPopupValidacao(typeValidacao, listaValidacao);
        return retorno;
    }

    private void abrirPopupValidacao(TipoValidacaoType tipoValidacao, List<ValidacaoDTO> listaValidacao) {
        final int MAX_NUM_MSG = 5;
        int countMsgs = 0;
        // TODO: investigar mais a fundo a implementação da validação para resolver corretamente este erro.
        // <epr> 0.6.7 evitar duplicação no aviso
        List<Integer> hashInserted = new ArrayList<Integer>();
        // </epr> 0.6.7 evitar duplicação no aviso
        StringBuilder validacao = new StringBuilder();
        if (TipoValidacaoType.VALIDACAO_ERRO.equals(tipoValidacao)) {
            validacao.append("Foram identificados alguns erros de validação:");
            validacao.append("<ul><li>");
            validacao.append(tipoValidacao.getDescricaoValidacao() + ":");
            validacao.append("<ul>");
            for (ValidacaoDTO val : listaValidacao) {
                // <epr> 0.6.7 evitar duplicação no aviso
                if (hashInserted.contains(("ERRO " + val.getMensagem()).hashCode())) {
                    continue;
                }
                hashInserted.add(("ERRO " + val.getMensagem()).hashCode());
                // </epr> 0.6.7 evitar duplicação no aviso
                if (countMsgs == MAX_NUM_MSG) {
                    validacao.append("<li><b>Existem mais erros não exibidos...</b></li>");
                    break;
                }
                validacao.append("<li>" + val.getMensagem() + "</li><br>");
                countMsgs++;
            }
            validacao.append("</ul></li></ul>");
            mensagemValidacao = validacao.toString();
            getPopupErroValidacao().show(new RichPopup.PopupHints());
        } else if (TipoValidacaoType.VALIDACAO_AVISO.equals(tipoValidacao)) {
            validacao.append("Foram identificados alguns alertas de validação:");
            validacao.append("<ul><li>");
            validacao.append(tipoValidacao.getDescricaoValidacao() + ":");
            validacao.append("<ul>");
            for (ValidacaoDTO val : listaValidacao) {
                // <epr> 0.6.7 evitar duplicação no aviso
                if (hashInserted.contains(("AVISO " + val.getMensagem()).hashCode())) {
                    continue;
                }
                hashInserted.add(("AVISO " + val.getMensagem()).hashCode());
                // </epr> 0.6.7 evitr duplicação no aviso
                if (countMsgs == MAX_NUM_MSG) {
                    validacao.append("<li><b>Existem mais erros não exibidos...</b></li>");
                    break;
                }
                validacao.append("<li>" + val.getMensagem() + "</li><br>");
            }
            validacao.append("</ul></li></ul>");
            mensagemValidacao = validacao.toString();
            getPopupAvisoValidacao().show(new RichPopup.PopupHints());
        } else if (TipoValidacaoType.VALIDACAO_CONFIRMACAO.equals(tipoValidacao)) {
            validacao.append("Foram identificados alguns itens que precisam de confirmação:<br>");
            for (ValidacaoDTO val : listaValidacao) {
                if (!val.isValidacaoAceita()) {
                    validacao.append(val.getMensagem());
                    break;
                }
            }
            mensagemValidacao = validacao.toString();
            getPopupConfirmacaoValidacao().show(new RichPopup.PopupHints());
        }
    }

    private void zerarValidacoes() {
        mapValidacao = null;
        mensagemValidacao = null;
        tipoValidacao = null;
    }

    public void aceitarConfirmacaoValidacao(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            validacaoCorrente.setValidacaoAceita(true);
            concluirPreenchimento(dialogEvent);
        }
    }

    private boolean validarConfirmacaoValidacao(List<ValidacaoDTO> listaValidacao) {
        for (ValidacaoDTO validacao : listaValidacao) {
            if (!validacao.isValidacaoAceita()) {
                validacaoCorrente = validacao;
                return false;
            }
        }
        return true;
    }

    public void aceitarAvisoValidacao(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            controleValidacaoAviso = true;
            concluirPreenchimento(dialogEvent);
        }
    }

    public static List<Usuario> todosOsMagistradosNoFormulario(SecaoDTO secaoMagistrado,
                                                               List<Usuario> listaMagistrados) {
        List<Usuario> listaMagistradosRetorno = null;
        List<Usuario> listaMagistradosExistentes = null;
        if (secaoMagistrado != null && listaMagistrados != null) {
            listaMagistradosExistentes = new ArrayList<Usuario>();
            listaMagistradosRetorno = new ArrayList<Usuario>();
            listaMagistradosRetorno.addAll(listaMagistrados);
            if (secaoMagistrado.getListaSubSecoes() != null && !secaoMagistrado.getListaSubSecoes().isEmpty()) {
                for (SubSecaoDTO subSecaoDTO : secaoMagistrado.getListaSubSecoes()) {
                    Usuario magistradoForaDaSecao =
                        existeMagistradoNaSecao(listaMagistrados, subSecaoDTO.getIdMagistrado());
                    if (magistradoForaDaSecao != null)
                        listaMagistradosExistentes.add(magistradoForaDaSecao);
                }
            }
            listaMagistradosRetorno.removeAll(listaMagistradosExistentes);
        }
        return listaMagistradosRetorno;
    }

    public static Usuario existeMagistradoNaSecao(List<Usuario> listaMagistrados, Long idMagistrado) {
        Usuario retorno = null;
        if (listaMagistrados != null && !listaMagistrados.isEmpty()) {
            for (Usuario magistrado : listaMagistrados) {

                if (magistrado.getIdUsuario().equals(idMagistrado)) {
                    retorno = magistrado;
                }
            }
        }
        return retorno;
    }

    public String salvarPreenchimento() {
        if (isSituacaoAbertoEmPreenchimentoConcluidoDevolvido()) {
            entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                 TipoSituacaoType.EM_PREENCHIMENTO));
        } else {
            entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                 TipoSituacaoType.RETIFICACAO_EM_PREENCHIMENTO));
        }
        // <epr 3) Item 143>
        if (listaRemoverProcessoConclusoDTO != null && !listaRemoverProcessoConclusoDTO.isEmpty()) {
            
            for(SubSecaoDTO subSecaoMagistrado : getSecaoMagistrado().getListaSubSecoes()){
            //SubSecaoDTO subSecaoMagistrado = FormularioUtils.encontrarSubSecaoPorMagistrado(this.idMagistrado, secaoMagistrado);
            //if (subSecaoMagistrado != null) {
                subSecaoMagistrado.getListaProcessosConclusos().removeAll(listaRemoverProcessoConclusoDTO.values());
            //}
            }
            if (subSecaoProcessoConclusoDTO != null && subSecaoProcessoConclusoDTO.getProcessosConclusosCpc() != null &&
                subSecaoProcessoConclusoDTO.getProcessosConclusosCpc().getListaProcessosConclusos() != null &&
                !subSecaoProcessoConclusoDTO.getProcessosConclusosCpc()
                                                                                                                                           .getListaProcessosConclusos()
                                                                                                                                           .isEmpty()) {
                subSecaoProcessoConclusoDTO.getProcessosConclusosCpc()
                                           .getListaProcessosConclusos()
                                           .removeAll(listaRemoverProcessoConclusoDTO.values());
                subSecaoProcessoConclusoDTO.getListaProcessosConclusos()
                    .removeAll(listaRemoverProcessoConclusoDTO.values());
                //secaoDadosUnidade.getListaSubSecoes().add(subSecaoProcessoConclusoDTO); // TESTAR (E ACHAR O PONTO DO DATA BAIXA)
            }
            //listaRemoverProcessoConclusoDTO.clear();
        }
        // </epr 3) Item 143>
        // <epr Task 109>
        if ((listaReusProvisoriosFiltrada != null) && (listaReusProvisoriosFiltrada.size() > 0)) {
            for (ReuDTO reu : listaReusProvisoriosFiltrada) {
                if (reu.getMarcadoExclusao()) {
                    if (reu.getIdReuProvisorio() != null) {
                        secaoReus.getListaSubSecoes()
                                 .get(0)
                                 .getListaReusHistoricoDeletar()
                                 .add(reu);
                    }
                    secaoReus.getListaSubSecoes()
                             .get(0)
                             .getListaReus()
                             .remove(reu);
                }
            }
        }
        // </epr Task 109>
        recalcularFormulasAnteriorEatual();
        return persistirEntidade();
    }

    public boolean isSituacaoAbertoEmPreenchimentoConcluidoDevolvido() {
        boolean retorno = false;
        if (entidadePersistencia.getSituacaoFormularioDTO() == null ||
            (entidadePersistencia.getSituacaoFormularioDTO()
                                                                                            .getIdentificadorSituacaoFormulario()
                                                                                            .equals(TipoSituacaoType.ABERTO.getCodigo()) ||
             entidadePersistencia.getSituacaoFormularioDTO()
                                                                                                                                                                .getIdentificadorSituacaoFormulario()
                                                                                                                                                                .equals(TipoSituacaoType.EM_PREENCHIMENTO.getCodigo()) ||
                            entidadePersistencia.getSituacaoFormularioDTO()
                                                                                                                                                                                                                                              .getIdentificadorSituacaoFormulario()
                                                                                                                                                                                                                                              .equals(TipoSituacaoType.CONCLUIDO.getCodigo()) ||
                                                          entidadePersistencia.getSituacaoFormularioDTO()
                                                                                                                                                                                                                                                                                                                     .getIdentificadorSituacaoFormulario()
                                                                                                                                                                                                                                                                                                                     .equals(TipoSituacaoType.DEVOLVIDO.getCodigo()))) {
            entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                 TipoSituacaoType.EM_PREENCHIMENTO));
            retorno = true;
        }
        return retorno;
    }

    public String persistirEntidade() {
        // <epr 20180830: remover confirmação avaliar retificaçao>
        ADFContext context = ADFContext.getCurrent();
        String controleFluxo = (String) context.getPageFlowScope().get("controleFluxo");
        if(controleFluxo.equals("AVALIAR")) {
            if (entidadePersistencia.getAprovarRetificacao().equals(APROVAR)) {
                entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                     TipoSituacaoType.RETIFICACAO_APROVADA));
            } else {
                entidadePersistencia.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(listaTipoSituacaoCadForm,
                                                                                                                     TipoSituacaoType.RETIFICACAO_REPROVADA));
            }            
        }
        // </epr 20180830: remover confirmação avaliar retificaçao>
        entidadePersistencia.setIdUsuarioAlteracao(usuarioLogado.getId());
        formularioService.salvarFormulario(entidadePersistencia, secaoMagistrado, secaoReus,
                                           subSecaoProcessoConclusoDTO, listaRemoverProcessoConclusoDTO);
        listaRemoverProcessoConclusoDTO.clear();
        painelPrincipal = new RichPanelGroupLayout();
        return "voltar";
    }

    @Override
    public String pesquisarEntidade() {
        pesquisarEntidadeConsultarGeral();
        return null;
    }

    public String buscarEntidade() {
        listaEntidade = formularioService.listarFormulariosComFiltro(entidadeFiltro, paginacao);
        return null;
    }

    public String fecharAviso() {
        mostrarAviso = false;
        atualizarComponenteDeTela("painelAviso");
        return null;
    }

    @Override
    public String excluirEntidade() {
        return null;
    }

    @Override
    public Class getClasseEntidade() {
        return FormularioDTO.class;
    }

    public void resetSecoes() {
        secaoDadosUnidade = null;
        secaoMagistrado = null;
        secaoEstabelecimento = null;
        secaoMateria = null;
        secaoReus = null;
        zerarValidacoes();
    }

    public String recalcularFormulas() {
        recalcularFormulasAnteriorEatual();
        atualizarComponenteDeTela(painelPrincipal);
        return null;
    }

    public boolean recalcularFormulas2() {
        formularioMesAnterior = formularioService.recuperarFormularioMesAnterior(entidadePersistencia);
        entidadePersistencia =
                FormulaCalculo.calcularFormulasDoFormulario(entidadePersistencia, formularioMesAnterior);
        if (triggerRecalc) {
            atualizarComponenteDeTela(painelPrincipal);
            triggerRecalc = false;
        }
        return true;
    }

    /*public Calendar getUltimoDiaMesReferenciaMenosCemDias(){
        --Calendar ultimoDiaMesReferenciaMenosCemDias = Calendar.getInstance();
        --ultimoDiaMesReferenciaMenosCemDias.setTime(getUltimoDiaMesReferencia());
        --ultimoDiaMesReferenciaMenosCemDias.add(Calendar.DATE, -100);
        return this.ultimoDiaMesReferenciaMenosCemDias;
    }*/

    public String initPreencherFormulario() {
        return initPreencherFormulario(false);
    }
    
    private String initPreencherFormulario(boolean relatorioPDF) {
        //Formulario f = formularioService.recuperarFormularioPorIdFormulario(entidadePersistencia.getIdFormulario());
        /** recupera Formulário */
        entidadePersistencia = recuperarFormulario(entidadePersistencia);
        /*entidadePersistencia = formularioService.recuperarFormularioDTOPorIdFormulario(entidadePersistencia.getIdFormulario());
        entidadePersistencia.setFutureListaSecoes(formularioService.asyncCompleteFormularioDTO(entidadePersistencia));
        entidadePersistencia.setFutureListaHistoricoFormulario(formularioService.asyncCompleteHistoricoFormularioDTO(entidadePersistencia));*/
        /** FIM -  recupera Formulário */

        if (ORIGEM_TELA_RETIFICAR.equals(action))
            funcaoRetificacao = true;
        else
            funcaoRetificacao = false;


        resetSecoes();
        sugestao = "";
        filtroReuProvisorio = "";
        if (usuarioMagistrado != null && usuarioMagistrado.getIdUsuario() != null) {
            entidadePersistencia.setIdMagistrado(usuarioMagistrado.getIdUsuario());
            entidadePersistencia.setNomeMagistrado(usuarioMagistrado.getNome());
        }

        entidadePersistencia.setIdUsuarioPreenchimento(getLoginBean().getUsuarioSessao().getIdUsuario());
        entidadePersistencia.setNomeUsuarioPreenchimento(getLoginBean().getUsuarioSessao().getNome());
        logger.info(AppBundleProperties.getString("msg.formulario.logAlteracaoFormulario") +
                    entidadePersistencia.getNomeFormulario() + " Por: " +
                    entidadePersistencia.getNomeUsuarioPreenchimento());

        PeriodoProcessoConcluso periodo =
            formularioService.recuperarPeriodoProcessoConclusoPorAnoMes(new PeriodoProcessoConcluso(entidadePersistencia.getAno()
                                                                                                    .intValue(), null,
                                                                                                    null,
                                                                                                    entidadePersistencia.getMes()
                                                                                                    .intValue(), null));
        if (periodo != null) {
            periodoProcessoConclusoInicio = new SimpleDateFormat("dd/MM/yyyy").format(periodo.getDataInicio());
            periodoProcessoConclusoFim = new SimpleDateFormat("dd/MM/yyyy").format(periodo.getDataFim());
            periodoProcessoConclusoInicioCalendar.setTime(periodo.getDataInicio());
            periodoProcessoConclusoFimCalendar.setTime(periodo.getDataFim());
        }

        tiposRegraFormulario = new HashMap<Long, Boolean>();
        if (entidadePersistencia.getListaTipoRegrasFormulario() != null) {
            for (TipoRegraDTO tipoRegraDTO : entidadePersistencia.getListaTipoRegrasFormulario()) {
                tiposRegraFormulario.put(tipoRegraDTO.getCodigoTipoRegra(), true);
            }
        }
        processoConclusoDTO = new ProcessoConclusoDTO();
        if (entidadePersistencia.getAviso() == null || entidadePersistencia.getAviso().isEmpty()) {
            mostrarAviso = false;
        } else {
            mostrarAviso = true;
        }

        for (SecaoDTO secao : entidadePersistencia.getListaSecoes()) {
            if (secao.getCodigoSecao().equals(SecaoType.DADOS_UNIDADES.getCodigoSecao())) {
                secaoDadosUnidade = secao;
                ProcessosConclusosCpcDTO processosConclusosCpcDTO =
                    formularioService.listarProcessosConclusosPorUnidade(new ProcessoConclusoDTO(entidadePersistencia.getAno()
                                                                                                 .intValue(),
                                                                                                 entidadePersistencia.getMes()
                                                                                                 .intValue(),
                                                                                                 entidadePersistencia.getIdUnidade(),
                                                                                                 null,
                                                                                                 entidadePersistencia.getCodigoFormulario()));
                subSecaoProcessoConclusoDTO = new SubSecaoDTO();
                subSecaoProcessoConclusoDTO.setLabelSecao("Processos Conclusos da Unidade");
                subSecaoProcessoConclusoDTO.setCodigoSubSecao("SUG");
                subSecaoProcessoConclusoDTO.setTabelaProcessos(secaoDadosUnidade.isTabelaProcessos());
                subSecaoProcessoConclusoDTO.getProcessosConclusosCpc().setListaTipoFilaProcessoDTO(processosConclusosCpcDTO.getListaTipoFilaProcessoDTO());
                if(!processosConclusosCpcDTO.getListaProcessosConclusos().isEmpty()){
                    subSecaoProcessoConclusoDTO.setLabelSecao("Processos Conclusos da Unidade");
                    // <epr> subSecaoProcessoConclusoDTO = new SubSecaoDTO();
                    subSecaoProcessoConclusoDTO.setProcessosConclusosCpc(processosConclusosCpcDTO);
                    //subSecaoProcessoConclusoDTO.getProcessosConclusosCpc().setListaProcessosConclusos(processosConclusosCpcDTO.getListaProcessosConclusos());
                    subSecaoProcessoConclusoDTO.setListaProcessosConclusos(processosConclusosCpcDTO.getListaProcessosConclusos());

                    for (SecaoDTO ss : entidadePersistencia.getListaSecoes()) {
                        if (ss.getCodigoSecao().equals(SecaoType.MAGISTRADO.getCodigoSecao())) {
                            for (ProcessoConclusoDTO pcDTO : processosConclusosCpcDTO.getListaProcessosConclusos()) {
                                for (SubSecaoDTO ssDTO : ss.getListaSubSecoes()) {
                                    if ((pcDTO.getIdMagistradoProcesso() != null && ssDTO.getIdMagistrado() != null) &&
                                        pcDTO.getIdMagistradoProcesso().intValue() ==
                                        ssDTO.getIdMagistrado().intValue()) {
                                        pcDTO.setNomeMagistrado(ssDTO.getNomeMagistrado());
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
                if(relatorioPDF)
                   secao.getListaSubSecoes().add(subSecaoProcessoConclusoDTO);
            } else if (secao.getCodigoSecao().equals(SecaoType.MAGISTRADO.getCodigoSecao())) {
                for (SubSecaoDTO subSecaoDTO : secao.getListaSubSecoes()) {
                    List<ProcessoConclusoDTO> listaProcessosConclusos =
                        formularioService.listarProcessosConclusosMagistradoPorUnidade(new ProcessoConclusoDTO(entidadePersistencia.getAno()
                                                                                                               .intValue(),
                                                                                                               entidadePersistencia.getMes()
                                                                                                               .intValue(),
                                                                                                               entidadePersistencia.getIdUnidade(),
                                                                                                               subSecaoDTO.getIdMagistrado(),
                                                                                                               entidadePersistencia.getCodigoFormulario()));
                    /*listaProcessosConclusosUnidade.addAll(listaProcessosConclusos);*/
                    subSecaoDTO.setListaProcessosConclusos(listaProcessosConclusos);
                    /*if(!subSecaoDTO.getListaProcessosConclusos().isEmpty())
                        subSecaoProcessoConclusoDTO = subSecaoDTO;*/
                }
                secaoMagistrado = secao;
            } else if (secao.getCodigoSecao().equals(SecaoType.ESTABELECIMENTOS_PRISIONAIS.getCodigoSecao())) {
                secaoEstabelecimento = secao;
            } else if (secao.getCodigoSecao().equals(SecaoType.MATERIA.getCodigoSecao())) {
                secaoMateria = secao;
            } else if (secao.getCodigoSecao().equals(SecaoType.REUS.getCodigoSecao())) {
                for (SubSecaoDTO subSecaoDTO : secao.getListaSubSecoes()) {
                    List<ReuDTO> listaReusProvisorioSemBaixa =
                        formularioService.listarReusProvisorioUnidade(new ReuProvisorio(new Unidade(entidadePersistencia.getIdUnidade())),
                                                                      entidadePersistencia.getAno().intValue(),
                                                                      entidadePersistencia.getMes().intValue());
                    subSecaoDTO.setListaReus(listaReusProvisorioSemBaixa);
                    listaReusProvisoriosFiltrada = new ArrayList<ReuDTO>();
                    listaReusProvisoriosFiltrada.addAll(listaReusProvisorioSemBaixa);
                }
                secaoReus = secao;
            }
        }

        if (entidadePersistencia != null && entidadePersistencia.getListaHistoricoFormulario() != null)
            Collections.sort(entidadePersistencia.getListaHistoricoFormulario(), Collections.reverseOrder());

        //Luis - Retirei pois a regra da existencia do formulario anterior precisa ser aplicada na consulta
        //por conta do tipo regra primeiro acesso nos campos
        formularioMesAnterior = formularioService.recuperarFormularioMesAnterior(entidadePersistencia);

        if (!visualizar) {
            entidadePersistencia =
                FormulaCalculo.calcularFormulasDoFormulario(entidadePersistencia, formularioMesAnterior);
            this.triggerRecalc = true;
        }

        existeFormularioPreenchidoAnteriormente = (formularioMesAnterior != null);

        // Erro 116 - SÃƒÂ³ atualiza na tela apÃƒÂ³s a existencia do componente "RichPanelGroupLayout painelPrincipal" existir para ser atualizado.
        if (painelPrincipal !=
            null) // quando estÃƒÂ¡ nulo, ele recalcula apÃƒÂ³s o Framework criar o objeto (no set utilizado).
            atualizarComponenteDeTela(painelPrincipal);
        else
            this.atualizarPainelPrincipal = true;

        return "preencherFormulario";
    }

    public String initAlterarMetadadosFormulario() {
        visualizar = false;
        return initPreencherFormulario();
    }

    public String initVisualizarMetadadosFormulario() {
        visualizar = true;
        return initPreencherFormulario();
    }

    public String initBaixarMetadadosFormulario() {
        visualizar = true;
        return initPreencherFormulario();
    }

    public String initAvaliarRetificacaoFormulario() {
        visualizar = true;
        return initPreencherFormulario();
    }

    public void selecionarEstabelecimentoEntidadeEmTabela(SelectionEvent selectionEvent) {
        RichTable tabelaEstabelecimento = (RichTable) selectionEvent.getComponent();
        estabelecimentoEntidadeDTO = (EstabelecimentoEntidadeDTO) tabelaEstabelecimento.getSelectedRowData();
        tabelaEstabelecimento.getSelectedRowKeys().clear();
    }

    public void alterarNomeMagistradoSelecionado(ValueChangeEvent valueChangeEvent) {
        try {
            magistrado =
                alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                     Usuario.class);
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), magistrado.getNome());
        } catch (Exception e) {
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    public void alterarNomeMagistradoSelecionadoEstabelecimento(ValueChangeEvent valueChangeEvent) {
        try {
            magistrado =
                alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                     Usuario.class);
            estabelecimentoEntidadeDTO.setIdMagistrado(magistrado.getIdUsuario());
            estabelecimentoEntidadeDTO.setNomeMagistrado(magistrado.getNome());
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), magistrado.getNome());
        } catch (Exception e) {
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    public void alterarNomeMagistradoSelecionadoReus(ValueChangeEvent valueChangeEvent) {
        if (!String.valueOf(valueChangeEvent.getNewValue()).equals("")) {
            try {
                magistrado =
                    alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                         Usuario.class);
                reu.setIdMagistrado(magistrado.getIdUsuario());
                reu.setNomeMagistrado(magistrado.getNome());
                atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), reu.getNomeMagistrado());
            } catch (Exception e) {
                atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
            }
        } else {
            reu.setIdMagistrado(null);
            reu.setNomeMagistrado(null);
        }
    }

    public void alterarNomeEstabelecimentoPrisionalSelecionadoReus(ValueChangeEvent valueChangeEvent) {
        try {
            estabelecimentoPrisionalReu =
                alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                     EstabelecimentoPrisional.class);
            reu.setIdEstabelecimentoPrisional(estabelecimentoPrisionalReu.getIdEstabelecimentoPrisional());
            reu.setNomeEstabelecimentoPrisional(estabelecimentoPrisionalReu.getNomeEstabelecimentoPrisional());
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(),
                                                  reu.getNomeEstabelecimentoPrisional());
        } catch (Exception e) {
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    @Override
    public List listarAutoCompletar(String parametro) {
        Usuario magistrado = new Usuario();
        magistrado.setNome(parametro);
        return formularioService.listarUsuarioMagistradoPorNomeComPaginacao(magistrado, paginacaoSeguestao);
    }

    public List autoCompletarEstabelecimentoPrisional(String parametro) {
        listaParametros = listarAutoCompletarEstabelecimentoPrisional(parametro);
        return montarSelectItemEstabelecimentoPrisional(((List<EstabelecimentoPrisional>) listaParametros));
    }

    public List<EstabelecimentoPrisional> listarAutoCompletarEstabelecimentoPrisional(String parametro) {
        EstabelecimentoPrisional estabelecimentoPrisional = new EstabelecimentoPrisional();
        estabelecimentoPrisional.setNomeEstabelecimentoPrisional(parametro);
        return formularioService.listarEstabelecimentoPrisionalOrdenadoPorNomePaginacao(estabelecimentoPrisional,
                                                                                        paginacaoSeguestao);
    }

    @Override
    public List<SelectItem> montarSelectItem(List<? extends BaseObject> listaParametros) {
        List<SelectItem> listaSugestoes = null;
        if (listaParametros != null) {
            listaSugestoes = new ArrayList<SelectItem>();
            for (Usuario item : ((List<Usuario>) listaParametros)) {
                if (!verificaExistenciaMagistradoSecao(item.getIdUsuario())) {
                    SelectItem selectItem = new SelectItem();
                    selectItem.setLabel(item.getNome());
                    selectItem.setValue(item.getIdUsuario());
                    listaSugestoes.add(selectItem);
                }
            }
        }
        return listaSugestoes;
    }

    public List<SelectItem> montarSelectItemEstabelecimentoPrisional(List<EstabelecimentoPrisional> listaParametros) {
        List<SelectItem> listaSugestoes = null;
        if (listaParametros != null) {
            listaSugestoes = new ArrayList<SelectItem>();
            for (EstabelecimentoPrisional item : listaParametros) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(item.getNomeEstabelecimentoPrisional());
                selectItem.setValue(item.getIdEstabelecimentoPrisional());
                listaSugestoes.add(selectItem);
            }
        }
        return listaSugestoes;
    }

    public void adicionarSubSecaoMagistrado(ActionEvent actionEvent) {
        if (magistrado != null) {
            secaoMagistrado.getListaSubSecoes().add(SubSecaoDTO.gerarNovaSubSecao(secaoMagistrado));
            secaoMagistrado.getListaSubSecoes()
                           .get(secaoMagistrado.getListaSubSecoes().size() - 1)
                           .setIdMagistrado(magistrado.getIdUsuario());
            secaoMagistrado.getListaSubSecoes()
                           .get(secaoMagistrado.getListaSubSecoes().size() - 1)
                           .setNomeMagistrado(magistrado.getNome());
            secaoMagistrado.getListaSubSecoes()
                           .get(secaoMagistrado.getListaSubSecoes().size() -
                                1)
                           .setListaProcessosConclusos(formularioService.listarProcessosConclusosMagistradoPorUnidade(new ProcessoConclusoDTO(entidadePersistencia.getAno()
                                                                                                                                              .intValue(),
                                                                                                                                              entidadePersistencia.getMes()
                                                                                                                                              .intValue(),
                                                                                                                                              entidadePersistencia.getIdUnidade(),
                                                                                                                                              magistrado.getIdUsuario(),
                                                                                                                                              entidadePersistencia.getCodigoFormulario())));
            List<PreCarga> listaCamposPreCarga =
                formularioService.listarCamposPreCarga(new PreCarga(entidadePersistencia.getAno().intValue(), null,
                                                                    null, entidadePersistencia.getCodigoFormulario(),
                                                                    null, entidadePersistencia.getMes().intValue(),
                                                                    new Unidade(entidadePersistencia.getIdUnidade()),
                                                                    null, magistrado));
            formularioService.incluirValoresPreCarga(secaoMagistrado.getListaSubSecoes()
                                                     .get(secaoMagistrado.getListaSubSecoes().size() - 1),
                                                     listaCamposPreCarga);
            sugestao = "";
            magistrado = null;
            RichPanelBox painelSecaoMagistrado = (RichPanelBox) findComponent("painelSecaoMagistrado");
            painelSecaoMagistrado.setDisclosed(true);
            atualizarComponenteDeTela(painelSecaoMagistrado);
            
            // <epr 20180816 - setFocus primeiro selectOneChoice da secao incluída
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExtendedRenderKitService service = (ExtendedRenderKitService)Service.getRenderKitService(facesContext, ExtendedRenderKitService.class);
            service.addScript(facesContext, "setTimeout(setFocusUltimaSessaoMagistrado('" + painelSecaoMagistrado.getClientId(facesContext) + "'), 500);");
            // </epr 20180816
        }
    }

    public void initDialogProcessoConclusoUnidade(ActionEvent actionEvent) {
        processoConclusoUnidadeBoolean = true;
        initDialogProcessoConcluso(actionEvent);
        processoConclusoUnidadeBoolean = true;
    }

    public void initDialogProcessoConcluso(ActionEvent actionEvent) {
        processoConclusoDTO = new ProcessoConclusoDTO();
        if (!processoConclusoUnidadeBoolean) {
            RichPanelBox subSecao = (RichPanelBox) actionEvent.getComponent()
                                                              .getParent()
                                                              .getParent()
                                                              .getParent();
            RichOutputText idMagistrado = (RichOutputText) subSecao.getToolbar()
                                                                   .getChildren()
                                                                   .get(2);
            this.idMagistrado = (Long) idMagistrado.getValue();
        }
        RichPopup.PopupHints hint = new RichPopup.PopupHints();
        RichPopup popupProcessoConcluso = (RichPopup) findComponent("popupAdicionarProcessoConcluso");
        popupProcessoConcluso.show(hint);
        processoConclusoUnidadeBoolean = false;
    }
    
    private Date fimDaDesignacao;
    public void setFimDaDesignacao(Date fimDaDesignacao) {
        this.fimDaDesignacao = fimDaDesignacao;
    }
    
    public Date getFimDaDesignacao() {
        return this.fimDaDesignacao;
    }
    
    public void aplicarFimDesignacao(ActionEvent actionEvent) {
        /*
        RichPanelBox subSecao = (RichPanelBox) actionEvent.getComponent()
                                                          .getParent()
                                                          .getParent()
                                                          .getParent();
        RichOutputText idMagistrado = (RichOutputText) subSecao.getToolbar()
                                                               .getChildren()
                                                               .get(2); */
        UIComponent comp = actionEvent.getComponent();
        RichOutputText idMagistrado = (RichOutputText)comp.getParent().getChildren().get(0);
        Long magistrado = (Long) idMagistrado.getValue();
        List<ProcessoConclusoDTO> processosMagistrado = FormularioUtils.encontrarSubSecaoPorMagistrado(magistrado, secaoMagistrado)
                                       .getListaProcessosConclusos();
        for(ProcessoConclusoDTO p : processosMagistrado) {
            p.setDtDesignacaoFim(getFimDaDesignacao());
        }
        setFimDaDesignacao(null);
    }

    public String adicionarProcessoConcluso() {
        // <epr> 2.0.33 - verificar duplicidade 20/07/2018
        long numDup = subSecaoProcessoConclusoDTO.getListaProcessosConclusos()
                                                 .stream()
                                                 .filter(p -> p.getNumeroProcesso().equals(processoConclusoDTO.getNumeroProcesso()))
                                                 .count();
        if (numDup > 0) {
            mensagemErro("Número de processo concluso duplicado");
            return null;
        }
        // <epr> 0.7.15 - admitindo não informado, conforme discutido com a equipe 04/08/2017
        if (processoConclusoDTO.getTipoConclusoDTO() == null) {
            TipoConclusoDTO tipoConclusoDTO = formularioService.obterTipoConclusoPorId(-1L);
            processoConclusoDTO.setTipoConclusoDTO(tipoConclusoDTO);
        }
        // </epr> 0.7.15 - admitindo não informado, conforme discutido com a equipe 04/08/2017
        if (formularioMesAnterior != null) {
            //Regra Antiga
            if (entidadePersistencia.getAno().intValue() == ANO_REGRA_PROCESSO_CONCLUSO) {
                /*Calendar ultimoDiaMesReferenciaMenosCemDias = getUltimoDiaMesReferenciaMenosCemDias();
                Calendar ultimoDiaMesReferenciaAnteriorCemDias = Calendar.getInstance();
                ultimoDiaMesReferenciaAnteriorCemDias.setTime(ultimoDiaMesReferenciaMenosCemDias.getTime());
                ultimoDiaMesReferenciaAnteriorCemDias.add(Calendar.DATE, -30);*/
                if (processoConclusoDTO.getDataConclusao().before(periodoProcessoConclusoInicioCalendar.getTime()) ||
                    processoConclusoDTO.getDataConclusao().after(periodoProcessoConclusoFimCalendar.getTime())) {
                    getPopupMensagemRetificarProcessoConcluso().show(new RichPopup.PopupHints());
                } else {
                    adicionarProcessoConclusoDTO();
                    if (processoConclusoUnidadeBoolean) {
                        subSecaoProcessoConclusoDTO.getListaProcessosConclusos().add(processoConclusoDTO);
                    } else {
                        verificaProcessoConclusoUnidade();
                        FormularioUtils.encontrarSubSecaoPorMagistrado(this.idMagistrado, secaoMagistrado)
                                       .getListaProcessosConclusos()
                                       .add(processoConclusoDTO);
                    }
                    processoConclusoDTO =
                        new ProcessoConclusoDTO(processoConclusoDTO.getDataConclusao(),
                                                (TipoConclusoDTO) processoConclusoDTO.getTipoConclusoDTO().clonar(),
                                                processoConclusoDTO.getTipoFilaProcessoDTO());
                }
                //Regra Nova
            } else {
                if (validarPeriodoProcessoConcluso()) {
                    adicionarProcessoConclusoDTO();
                    if (processoConclusoUnidadeBoolean || this.idMagistrado == null ||
                        this.idMagistrado.longValue() < 1 || secaoMagistrado == null) {
                        subSecaoProcessoConclusoDTO.getListaProcessosConclusos().add(processoConclusoDTO);
                    } else {
                        verificaProcessoConclusoUnidade();
                        FormularioUtils.encontrarSubSecaoPorMagistrado(this.idMagistrado, secaoMagistrado)
                                       .getListaProcessosConclusos()
                                       .add(processoConclusoDTO);
                    }
                    processoConclusoDTO =
                        new ProcessoConclusoDTO(processoConclusoDTO.getDataConclusao(),
                                                (TipoConclusoDTO) processoConclusoDTO.getTipoConclusoDTO().clonar(),
                                                processoConclusoDTO.getTipoFilaProcessoDTO());
                } else {
                    getPopupMensagemRetificarProcessoConcluso().show(new RichPopup.PopupHints());
                }
            }
        } else {
            if (validarDataConclusaoNoPeriodoDeCemDias(processoConclusoDTO, getUltimoDiaMesReferencia())) {
                adicionarProcessoConclusoDTO();
                if (processoConclusoUnidadeBoolean) {
                    subSecaoProcessoConclusoDTO.getListaProcessosConclusos().add(processoConclusoDTO);
                } else {
                    verificaProcessoConclusoUnidade();
                    FormularioUtils.encontrarSubSecaoPorMagistrado(this.idMagistrado, secaoMagistrado)
                                   .getListaProcessosConclusos()
                                   .add(processoConclusoDTO);
                }
                processoConclusoDTO =
                    new ProcessoConclusoDTO(processoConclusoDTO.getDataConclusao(),
                                            (TipoConclusoDTO) processoConclusoDTO.getTipoConclusoDTO().clonar(),
                                            processoConclusoDTO.getTipoFilaProcessoDTO());
            } else {
                getPopupInconsistenciaProcessoCemDias().show(new RichPopup.PopupHints());
            }
        }
        return null;
    }

    public String concluirAdicionarProcessoConcluso() {
        if (formularioMesAnterior != null) {
            if (validarPeriodoProcessoConcluso()) {
                adicionarProcessoConclusoDTO();
                verificaProcessoConclusoUnidade();
                FormularioUtils.encontrarSubSecaoPorMagistrado(this.idMagistrado, secaoMagistrado)
                               .getListaProcessosConclusos()
                               .add(processoConclusoDTO);
                getPopupAdicionarProcessoConcluso().hide();
            } else {
                getPopupMensagemRetificarProcessoConcluso().show(new RichPopup.PopupHints());
            }
        } else {
            if (validarDataConclusaoNoPeriodoDeCemDias(processoConclusoDTO, getUltimoDiaMesReferencia())) {
                adicionarProcessoConclusoDTO();
                verificaProcessoConclusoUnidade();
                FormularioUtils.encontrarSubSecaoPorMagistrado(this.idMagistrado, secaoMagistrado)
                               .getListaProcessosConclusos()
                               .add(processoConclusoDTO);
                getPopupAdicionarProcessoConcluso().hide();
            } else {
                getPopupInconsistenciaProcessoCemDias().show(new RichPopup.PopupHints());
            }
        }
        return null;
    }

    public boolean validarPeriodoProcessoConcluso() {
        boolean retorno = false;
        List<ProcessoConclusoDTO> listaProcessosConclusosMesAnterior =
            formularioService.listarProcessosConclusosMesAnterior(new ProcessoConclusoDTO(entidadePersistencia.getAno()
                                                                                          .intValue(),
                                                                                          entidadePersistencia.getMes()
                                                                                          .intValue(),
                                                                                          entidadePersistencia.getIdUnidade(),
                                                                                          this.idMagistrado,
                                                                                          entidadePersistencia.getCodigoFormulario()),
                                                                  null);

        if (formularioService.validarPeriodoConclusoNoMesAnoReferencia(new PeriodoProcessoConcluso(entidadePersistencia.getAno()
                                                                                                   .intValue(), null,
                                                                                                   null,
                                                                                                   entidadePersistencia.getMes()
                                                                                                   .intValue(),
                                                                                                   processoConclusoDTO.getDataConclusao())) ||
            contemProcessoNoMesAnterior(listaProcessosConclusosMesAnterior, processoConclusoDTO)) {
            //Esse if está estranho, pois esse "validarPeriodoConclusoNoMesAnoReferencia" sempre vai retornar como TRUE, uma vez que exista o periodo para o mes e ano do formulario, e só
            retorno = true;
        }
        return retorno;
    }

    public void adicionarProcessoConclusoDTO() {
        if (!processoConclusoUnidadeBoolean)
            processoConclusoDTO.setIdMagistradoProcesso(this.idMagistrado);
        processoConclusoDTO.setAno(entidadePersistencia.getAno().intValue());
        processoConclusoDTO.setMes(entidadePersistencia.getMes().intValue());
        processoConclusoDTO.setIdUnidadeProcesso(entidadePersistencia.getIdUnidade());
        processoConclusoDTO.setSrcFormulario(entidadePersistencia.getCodigoFormulario());
    }

    public String cancelarAdicionarProcessoConcluso() {
        processoConclusoDTO = new ProcessoConclusoDTO();
        ResetUtils.reset(getPopupAdicionarProcessoConcluso());
        getPopupAdicionarProcessoConcluso().hide();
        processoConclusoUnidadeBoolean = false;
        return null;
    }

    public static boolean contemProcessoNoMesAnterior(List<ProcessoConclusoDTO> processosConclusosMesAnterior,
                                                      ProcessoConclusoDTO processoMesAtual) {
        boolean retorno = false;
        for (ProcessoConclusoDTO processoConcluso : processosConclusosMesAnterior) {
            if (processoConcluso.getNumeroProcesso().equals(processoMesAtual.getNumeroProcesso()) &&
                processoConcluso.getDataConclusao().equals(processoMesAtual.getDataConclusao())) {
                retorno = true;
            }
        }
        return retorno;
    }

    public void selecionarProcessoConclusoEmTabela(SelectionEvent selectionEvent) {
        RichTable tabelaProcessosConclusos = (RichTable) selectionEvent.getComponent();
        processoConclusoDTO = (ProcessoConclusoDTO) tabelaProcessosConclusos.getSelectedRowData();
        processoConclusoClone = (ProcessoConclusoDTO) processoConclusoDTO.clonar();
        tabelaProcessosConclusos.getSelectedRowKeys().clear();
    }

    public void removerProcessoConclusoUnidade(ActionEvent actionEvent) {
        this.processoConclusoUnidadeBoolean = true;
        removerProcessoConcluso(actionEvent);
    }

    public void removerProcessoConcluso(ActionEvent actionEvent) {
        ProcessoConclusoDTO processoNecessarioRetificar = formularioService.listarProcessoConclusosMaisAntigo(new ProcessoConclusoDTO(entidadePersistencia.getAno().intValue(),
                                                                                                        entidadePersistencia.getMes().intValue(),
                                                                                                        entidadePersistencia.getIdUnidade(),
                                                                                                        processoConclusoDTO.getIdMagistradoProcesso(),
                                                                                                        processoConclusoDTO.getNumeroProcesso(),
                                                                                                        entidadePersistencia.getCodigoFormulario()));

        if (processoNecessarioRetificar != null) {
            inconsistenciaRemoverProcessoConcluso = processoNecessarioRetificar.getMes() + "/" + 
                                            processoNecessarioRetificar.getAno();
            
            getPopupRemoverProcessoConclusoForaDoPeriodo().show(new RichPopup.PopupHints());
        } else {
            RichPanelBox subSecao = (RichPanelBox) actionEvent.getComponent().getParent().getParent()
                                                              .getParent().getParent().getParent()
                                                              .getParent().getParent();
            
            RichOutputText idMagistrado = (RichOutputText) subSecao.getToolbar().getChildren().get(2);
            
            this.idMagistrado = (Long) idMagistrado.getValue();
            processoConclusoDTO.setMarcadoExclusao(true);
            
            listaRemoverProcessoConclusoDTO.put(processoConclusoDTO.getId(), processoConclusoDTO);
        }
    }
    
    public List<FormularioDTO> validarRetificacoesFormularioEmProcessosConclusos(boolean acaoDeletar) {
        return validarRetificacoesFormularioEmProcessosConclusos(acaoDeletar, false);
    }

    public List<FormularioDTO> validarRetificacoesFormularioEmProcessosConclusos(boolean acaoDeletar,
                                                                                 boolean acaoDeletarNaUnidade) {
        List<FormularioDTO> listaFormulariosNecessarioRetificar = null;
        List<ProcessoConclusoDTO> listaProcessosConclusosSubsequentes =
            formularioService.listarProcessosConclusosMesesSubsequentes(new ProcessoConclusoDTO(entidadePersistencia.getAno()
                                                                                                .intValue(),
                                                                                                entidadePersistencia.getMes()
                                                                                                .intValue(),
                                                                                                entidadePersistencia.getIdUnidade(),
                                                                                                processoConclusoDTO.getIdMagistradoProcesso(),
                                                                                                processoConclusoDTO.getNumeroProcesso(),
                                                                                                entidadePersistencia.getCodigoFormulario()));
        if (listaProcessosConclusosSubsequentes != null && !listaProcessosConclusosSubsequentes.isEmpty()) {
            List<Long> listaAnos = new ArrayList<Long>();
            List<Long> listaMeses = new ArrayList<Long>();
            for (ProcessoConclusoDTO processoConclusoDTO : listaProcessosConclusosSubsequentes) {
                listaAnos.add(Long.valueOf(processoConclusoDTO.getAno()));
                listaMeses.add(Long.valueOf(processoConclusoDTO.getMes()));
            }
            List<FormularioDTO> listaFormulariosComProcessosMesesSubSequentes =
                formularioService.listarFormulariosComFiltros(listaAnos, listaMeses,
                                                              entidadePersistencia.getIdUnidade(),
                                                              entidadePersistencia.getCodigoFormulario());
            for (FormularioDTO formularioDTO : listaFormulariosComProcessosMesesSubSequentes) {
                if (!formularioDTO.getSituacaoFormularioDTO()
                                  .getIdentificadorSituacaoFormulario()
                                  .equals(TipoSituacaoType.ENVIADO_CGJ.getCodigo()) &&
                    !formularioDTO.getSituacaoFormularioDTO()
                                  .getIdentificadorSituacaoFormulario()
                                  .equals(TipoSituacaoType.RETIFICACAO_ENVIADA_AO_CGJ.getCodigo()) &&
                    !formularioDTO.getSituacaoFormularioDTO()
                                  .getIdentificadorSituacaoFormulario()
                                  .equals(TipoSituacaoType.RETIFICACAO_REPROVADA.getCodigo())) {
                    
                    if (acaoDeletar) {
                        if (processoConclusoUnidadeBoolean || processoConclusoDTO.getIdMagistradoProcesso() == null) {
                            subSecaoProcessoConclusoDTO.getListaProcessosConclusosDeletarAtualESubsequentes()
                                .add(processoConclusoDTO);
                        } else {
                            FormularioUtils.encontrarSubSecaoPorMagistrado(processoConclusoDTO.getIdMagistradoProcesso(), secaoMagistrado)
                                           .getListaProcessosConclusosDeletarAtualESubsequentes()
                                           .add(processoConclusoDTO);
                        }
                    } else {
                        if (processoConclusoUnidadeBoolean || processoConclusoDTO.getIdMagistradoProcesso() == null) {
                            subSecaoProcessoConclusoDTO.getListaProcessosConclusosDeletarSubsequentes()
                                .add(processoConclusoDTO);
                        } else {
                            FormularioUtils.encontrarSubSecaoPorMagistrado(processoConclusoDTO.getIdMagistradoProcesso(), secaoMagistrado)
                                           .getListaProcessosConclusosDeletarSubsequentes()
                                           .add(processoConclusoDTO);
                        }
                    }
                } else {
                    if (listaFormulariosNecessarioRetificar == null) {
                        listaFormulariosNecessarioRetificar = new ArrayList<FormularioDTO>();
                    }
                    listaFormulariosNecessarioRetificar.add(formularioDTO);
                }
            }
        } else {
            /*if (processoConclusoUnidadeBoolean || processoConclusoDTO.getIdMagistradoProcesso() == null) {
                subSecaoProcessoConclusoDTO.getListaProcessosConclusosDeletarAtualESubsequentes()
                    .add(processoConclusoDTO);
            } else {*/
            if (acaoDeletar){
                try{FormularioUtils.encontrarSubSecaoPorMagistrado(processoConclusoDTO.getIdMagistradoProcesso(), secaoMagistrado)
                               .getListaProcessosConclusosDeletarAtualESubsequentes()
                               .add(processoConclusoDTO);
                }catch(Exception ex){
                    subSecaoProcessoConclusoDTO.getListaProcessosConclusosDeletarAtualESubsequentes()
                        .add(processoConclusoDTO);
                }
            }
            //}
        }
        return listaFormulariosNecessarioRetificar;
    }

    public String baixarProcessoConcluso() {
        List<FormularioDTO> listaFormulariosNecessarioRetificar =
            validarRetificacoesFormularioEmProcessosConclusos(false);
        if (listaFormulariosNecessarioRetificar != null && !listaFormulariosNecessarioRetificar.isEmpty()) {
            inconsistenciaDataBaixa = new StringBuilder();
            for (FormularioDTO formularioDTO : listaFormulariosNecessarioRetificar) {
                inconsistenciaDataBaixa.append("<li>" + formularioDTO.getNomeFormulario() + " - " +
                                               formularioDTO.getMes() + "/" + formularioDTO.getAno() + "</li>");
            }
            getPopupDataBaixaRetificar().show(new RichPopup.PopupHints());
        } else {
            getPopupBaixaProcessoConcluso().hide();
        }
        return null;
    }

    public String cancelarBaixaProcesso() {
        processoConclusoDTO.setDataBaixa(processoConclusoClone.getDataBaixa());
        getPopupBaixaProcessoConcluso().hide();
        return null;
    }

    public String inserirDataFimDesignacao() {
        getPopupDataFimDesignacao().hide();
        return null;
    }

    public String cancelarInserirDataFimDesignacao() {
        processoConclusoDTO.setDtDesignacaoFim(processoConclusoClone.getDtDesignacaoFim());
        getPopupDataFimDesignacao().hide();
        return null;
    }

    public void removerSubSecaoTipoMagistrado(ActionEvent actionEvent) {
        RichOutputText tipoMagistradoExcluir = (RichOutputText) actionEvent.getComponent()
                                                                           .getParent()
                                                                           .getChildren()
                                                                           .get(1);
        subSecaoMagistradoRemover = (SubSecaoDTO) tipoMagistradoExcluir.getValue();
        RichPopup popupConfirmacaoExclusao = (RichPopup) findComponent("popupExcluirMagistrado");
        popupConfirmacaoExclusao.show(new RichPopup.PopupHints());
    }

    public String getNomeMagistradoRemover() {
        return subSecaoMagistradoRemover.getNomeMagistrado();
    }

    public void removerSubSecaoTipoMagistrado(DialogEvent dialogEvent) {
        if (secaoMagistrado.getListaSubSecoes().contains(subSecaoMagistradoRemover)) {
            secaoMagistrado.getListaSubSecoes().remove(subSecaoMagistradoRemover);
            subSecaoMagistradoRemover = null;
            atualizarComponenteDeTela("painelSecaoMagistrado");
        }
    }


    public void adicionarSubSecaoMateria(ActionEvent actionEvent) {
        if (materiaSubSecao != null && !contemMateria(materiaSubSecao)) {
            secaoMateria.getListaSubSecoes().add(new SubSecaoDTO(secaoMateria));
            secaoMateria.getListaSubSecoes()
                        .get(secaoMateria.getListaSubSecoes().size() - 1)
                        .setTipoMateria(materiaSubSecao);
            RichPanelBox painelSecaoMateria = (RichPanelBox) findComponent("painelSecaoMateria");
            painelSecaoMateria.setDisclosed(true);
            entidadePersistencia =
                FormulaCalculo.calcularFormulasDoFormulario(entidadePersistencia, formularioMesAnterior);
            atualizarComponenteDeTela("painelSecaoMateria");
        }
    }

    public boolean contemMateria(TipoMateriaDTO materia) {
        boolean flag = false;
        for (SubSecaoDTO subSecaoDto : secaoMateria.getListaSubSecoes()) {
            if (subSecaoDto.getTipoMateria().equals(materia)) {
                flag = true;
            }
        }
        return flag;
    }

    public void removerSubSecaoTipoMateria(ActionEvent actionEvent) {
        RichOutputText tipoMateriaExcluir = (RichOutputText) actionEvent.getComponent()
                                                                        .getParent()
                                                                        .getChildren()
                                                                        .get(1);
        subSecaoMateriaRemover = (SubSecaoDTO) tipoMateriaExcluir.getValue();
        RichPopup popupConfirmacaoExclusao = (RichPopup) findComponent("popupExcluirMateria");
        popupConfirmacaoExclusao.show(new RichPopup.PopupHints());
    }

    public String getNomeMateriaRemover() {
        return subSecaoMateriaRemover.getTipoMateria().getNomeTipoMateria();
    }

    public void removerSubSecaoTipoMateria(DialogEvent dialogEvent) {

        if (secaoMateria.getListaSubSecoes().contains(subSecaoMateriaRemover)) {
            secaoMateria.getListaSubSecoes().remove(subSecaoMateriaRemover);
            subSecaoMateriaRemover = null;
            atualizarComponenteDeTela("painelSecaoMateria");
        }
    }

    public void initDialogManterReu(PopupFetchEvent popupFetchEvent) {
        reu = new ReuDTO();
        edicaoReu = false;
    }


    public void selecionarReuEmTabela(SelectionEvent selectionEvent) {
        RichTable tabelaDadosReus = (RichTable) selectionEvent.getComponent();
        reu = (ReuDTO) tabelaDadosReus.getSelectedRowData();
        reuCopia = (ReuDTO) reu.clonar();
        tabelaDadosReus.getSelectedRowKeys().clear();
    }

    public String alterarReuTabela() {
        return salvarPopupConfirmarDataPrisaoReu();
    }

    public static List<ReuDTO> validarUltimoMovimentoSecaoReu(SecaoDTO secaoReu, Date ultimoDiaMesReferencia) {
        List<ReuDTO> listaReusInconsistentes = new ArrayList<ReuDTO>();
        if (secaoReu != null) {
            for (SubSecaoDTO subSecaoReu : secaoReu.getListaSubSecoes()) {
                for (ReuDTO reu : subSecaoReu.getListaReus()) {
                    if (!validarUltimoMovimentoNoPeriodoDeNoventaDias(reu, ultimoDiaMesReferencia)) {
                        listaReusInconsistentes.add(reu);
                    }
                }
            }
        }
        return listaReusInconsistentes;
    }
    
    private static boolean validarEstabelecimentosPrisionais(SecaoDTO secaoEstabelecimento) {
        boolean estabelecimentosValidados;
        if (secaoEstabelecimento.getListaSubSecoes() != null) {
            for (SubSecaoDTO subSecao : secaoEstabelecimento.getListaSubSecoes()) {
                for (EstabelecimentoEntidadeDTO estabelecimento : subSecao.getListaEstabelecimentosEntidade()) {
                    estabelecimentosValidados = true;
                    if (estabelecimento.isInspecaoNaoRealizada())
                        estabelecimentosValidados = (estabelecimento.getMotivoInspecaoNaoRealizada() != null && !estabelecimento.getMotivoInspecaoNaoRealizada().isEmpty()); 
                    else
                        estabelecimentosValidados = (estabelecimento.getNomeMagistrado() != null && 
                                                     !estabelecimento.getNomeMagistrado().isEmpty()); 
                
                    if (!estabelecimentosValidados) return false;
                }
            }
        }
                
        return true;
    }

    public static boolean validarUltimoMovimentoNoPeriodoDeNoventaDias(ReuDTO reu, Date ultimoDiaMesReferencia) {
        boolean retorno = false;
        // <epr 1) Item 141>
        if (reu.getIdMotivoBaixa() != null && reu.getDataBaixa() != null) {
            // se motivo baixa e data baixa, ignora outras validaçÃƒÂµes.
            return true;
        }
        // </epr 1) Item 141>
        Calendar ultimoDiaMesReferenciaMenosNoventaDias = Calendar.getInstance();
        ultimoDiaMesReferenciaMenosNoventaDias.setTime(ultimoDiaMesReferencia);
        ultimoDiaMesReferenciaMenosNoventaDias.add(Calendar.DATE, -90);
        if ((reu.getDataUltimoMovimento() != null &&
             !ultimoDiaMesReferenciaMenosNoventaDias.getTime().after(reu.getDataUltimoMovimento())) ||
            (reu.getDataLevadoMagistrado() != null &&
             !ultimoDiaMesReferenciaMenosNoventaDias.getTime().after(reu.getDataLevadoMagistrado()))) {
            retorno = true;
        }
        return retorno;
    }

    public void motivoBaixaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        RichPopup dialogReu = this.getPopupAlterarReu();
        if (dialogReu != null) {
            RichInputDate dataBaixa = (RichInputDate) findComponent(dialogReu, "dataBaixa");
            if (dataBaixa != null) {
                Object dataBaixaValue = valueChangeEvent.getNewValue();
                dataBaixa.setRequired(dataBaixaValue != null);
                AdfFacesContext.getCurrentInstance().addPartialTarget(dataBaixa);
            }
        }
    }

    public boolean isDataBaixaRequired() {
        RichPopup dialogReu = this.getPopupAlterarReu();
        if (dialogReu != null) {
            RichSelectOneChoice motivoBaixa = (RichSelectOneChoice) findComponent(dialogReu, "motivoBaixa");
            RichInputDate dataBaixa = (RichInputDate) findComponent(dialogReu, "dataBaixa");
            if (motivoBaixa != null && dataBaixa != null) {
                Object motivoBaixaValue = motivoBaixa.getValue();
                dataBaixa.setRequired(motivoBaixaValue != null && dataBaixa.getValue() == null);
            }
        }
        return false;
    }

    public void dataBaixaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        RichPopup dialogReu = this.getPopupAlterarReu();
        if (dialogReu != null) {
            RichSelectOneChoice motivoBaixa = (RichSelectOneChoice) findComponent(dialogReu, "motivoBaixa");
            if (motivoBaixa != null) {
                Object dataBaixaValue = valueChangeEvent.getNewValue();
                motivoBaixa.setRequired(dataBaixaValue != null);
                AdfFacesContext.getCurrentInstance().addPartialTarget(motivoBaixa);
            }
        }
    }

    public boolean isMotivoBaixaRequired() {
        RichPopup dialogReu = this.getPopupAlterarReu();
        if (dialogReu != null) {
            RichInputDate dataBaixa = (RichInputDate) findComponent(dialogReu, "dataBaixa");
            RichSelectOneChoice motivoBaixa = (RichSelectOneChoice) findComponent(dialogReu, "motivoBaixa");
            if (dataBaixa != null && motivoBaixa != null) {
                Object dataBaixaValue = dataBaixa.getValue();
                motivoBaixa.setRequired(dataBaixaValue != null && motivoBaixa.getValue() == null);
            }
        }
        return false;
    }

    public boolean validarDataConclusaoNoPeriodoDeCemDias(ProcessoConclusoDTO processoConclusoDTO,
                                                          Date ultimoDiaMesReferencia) {
        boolean retorno = false;
        /*Calendar ultimoDiaMesReferenciaMenosCemDias = Calendar.getInstance();
        ultimoDiaMesReferenciaMenosCemDias.setTime(ultimoDiaMesReferencia);
        ultimoDiaMesReferenciaMenosCemDias.add(Calendar.DATE, -100);*/
        if ((processoConclusoDTO.getDataConclusao() != null &&
             !periodoProcessoConclusoFimCalendar.getTime().before(processoConclusoDTO.getDataConclusao()))) {
            retorno = true;
        }
        return retorno;
    }

    public void cancelarAlteracaoReu(PopupCanceledEvent popupCanceledEvent) {
        reu.setReuDTO(reuCopia);
        cancelarInsercao(popupCanceledEvent);
    }

    public void removerReuTabela(ActionEvent actionEvent) {
        if (reu.getIdReuProvisorio() == null) {
            reu.setMarcadoExclusao(true);
            /* epr Task 109: movido para salvar entidade
            secaoReus.getListaSubSecoes()
                     .get(0)
                     .getListaReus()
                     .remove(reu);
            listaReusProvisoriosFiltrada.remove(reu);
            */
            atualizarComponenteDeTela(findComponent("tabelaDeReus"));
            atualizarComponenteDeTela("painelSecaoReus");
            RichPopup confirmacaoDeletarReusPopup = (RichPopup) findComponent("confirmacaoDeletarReusPopup");
            confirmacaoDeletarReusPopup.hide();
        } else if (reu.getAno() != null && reu.getAno().equals(entidadePersistencia.getAno().intValue()) &&
                   reu.getMes().equals(entidadePersistencia.getMes().intValue()) &&
                   !formularioService.validarReuHistoricoMesesAnteriores(new ReuProvisorioHistorico(reu.getMes()
                                                                                                    .intValue(),
                                                                                                    reu.getAno()
                                                                                                    .intValue(),
                                                                                                    new ReuProvisorio(reu.getIdReuProvisorio())))) {
            reu.setMarcadoExclusao(true);
            /* epr Task 109: movido para método salvar entidada ***
            secaoReus.getListaSubSecoes()
                     .get(0)
                     .getListaReusHistoricoDeletar()
                     .add(reu);
            secaoReus.getListaSubSecoes()
                     .get(0)
                     .getListaReus()
                     .remove(reu);
            listaReusProvisoriosFiltrada.remove(reu);
            */
            atualizarComponenteDeTela(findComponent("tabelaDeReus"));
            atualizarComponenteDeTela("painelSecaoReus");
            RichPopup confirmacaoDeletarReusPopup = (RichPopup) findComponent("confirmacaoDeletarReusPopup");
            confirmacaoDeletarReusPopup.hide();
        } else {
            getPopupRemoverReuInconsistencia().show(new RichPopup.PopupHints());
        }
    }

    /** BotÃƒÂµes de alteração de status de formulÃƒÂ¡rio e atribuição de dados a formulÃƒÂ¡rios*/
    public String definirMagistrado(DialogEvent dialogEvent) {

        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            logger.info(AppBundleProperties.getString("msg.formulario.logDefinirMagistrado") +
                        usuarioMagistrado.getNome() + " / " +
                        AppBundleProperties.getString("msg.formulario.logFormulario") +
                        entidadePersistencia.getNomeFormulario());
            persistirListaConjuntoDefinirMagistradoFormularios();
            zerarCamposRodape();
        }

        pesquisarEntidade();

        return "";
    }

    private void popularEntidadeFiltro() {
        if (ORIGEM_TELA_PREENCHER.equals(action)) {
            entidadeFiltro.setAno(anoReferencia);
            entidadeFiltro.setMes(mesReferencia);
            entidadeFiltro.setIdMagistrado(usuarioMagistrado.getIdUsuario());
            entidadeFiltro.setNomeMagistrado(usuarioMagistrado.getNome());
        } else {
            entidadeFiltro.setAno(entidadePersistencia.getAno());
            entidadeFiltro.setMes(entidadePersistencia.getMes());
            entidadeFiltro.setIdMagistrado(entidadePersistencia.getIdMagistrado());
            entidadeFiltro.setNomeMagistrado(entidadePersistencia.getNomeMagistrado());
        }

    }

    // <0.7.19> introduzido alteração
    // <20171009>Validar quanto à necessidade de modificar por método exclusivo</20171009>
    private void persistirListaConjuntoDefinirMagistradoFormularios() {
        if (ORIGEM_TELA_RETIFICAR.equals(acaoPageFlow())) {
            for (FormularioDTO form :
                 listaFormulariosRetificacao) {
                // Erro 109 - SÃƒÂ³ permitir alterar o Magistrado na Retificação para os seguintes Status - 08.09.2017 - Paula Covo
                if (form.getSituacaoFormularioDTO()
                        .getIdentificadorSituacaoFormulario()
                        .equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_APROVADA.toString()) ||
                    form.getSituacaoFormularioDTO()
                                                                                                   .getIdentificadorSituacaoFormulario()
                                                                                                   .equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_EM_PREENCHIMENTO.toString()) ||
                                          form.getSituacaoFormularioDTO()
                                                                                                                                                                                      .getIdentificadorSituacaoFormulario()
                                                                                                                                                                                      .equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_CONCLUIDA.toString())) {
/** recupera Formulário Antes de Salvar */
form = recuperarFormulario(form);
                    /** FIM -  recupera Formulário Antes de Salvar */
                    form.setNomeMagistrado(usuarioMagistrado.getNome());
                    form.setIdMagistrado(usuarioMagistrado.getIdUsuario());
                    form.setIdUsuarioAlteracao(usuarioLogado.getId());
                    formularioService.salvarFormulario(form, null, null, null);
                }
            }
        } else if (ORIGEM_TELA_PREENCHER.equals(acaoPageFlow())) {
            for (FormularioDTO form : listaEntidade) {
                if (anoReferencia.equals(form.getAno()) && mesReferencia.equals(form.getMes()) &&
                    unidadeFormularioMagistrado.equals(form.getNomeUnidade())) {
                    /** recupera Formulário Antes de Salvar */
                    form = recuperarFormulario(form);
                    /** FIM -  recupera Formulário Antes de Salvar */
                    form.setNomeMagistrado(usuarioMagistrado.getNome());
                    form.setIdMagistrado(usuarioMagistrado.getIdUsuario());
                    form.setIdUsuarioAlteracao(usuarioLogado.getId());
                    formularioService.salvarFormulario(form, null, null, null);
                }
            }
        }
    }

    // </epr>0.7.19
    /* <0.7.19> comentado
    private void persistirListaConjuntoDefinirMagistradoFormularios() {
        // <epr> 0.6.7 - Para corrigir possÃƒÂ­vel situação de erro (referÃƒÂªncia invÃƒÂ¡lida a entidadePersistencia)
        FormularioDTO refRetificar = null;
        // </epr> 0.6.7 - Para corrigir possÃƒÂ­vel situação de erro (referÃƒÂªncia invÃƒÂ¡lida a entidadePersistencia)
        for (FormularioDTO form : listaEntidade) {
            if (ORIGEM_TELA_PREENCHER.equals(acaoPageFlow())) {
                if (anoReferencia.equals(form.getAno()) && mesReferencia.equals(form.getMes()) &&
                    unidadeFormularioMagistrado.equals(form.getNomeUnidade())) {
                    form.setNomeMagistrado(usuarioMagistrado.getNome());
                    form.setIdMagistrado(usuarioMagistrado.getIdUsuario());
                    formularioService.salvarFormulario(form, null, null);
                }
            } else if (ORIGEM_TELA_RETIFICAR.equals(acaoPageFlow())) {
                // <erp> 0.6.7 - Para corrigir possÃƒÂ­vel situação de erro (referÃƒÂªncia invÃƒÂ¡lida a entidadePersistencia)
                if(refRetificar == null) {
                    if(entidadePersistencia != null && entidadePersistencia.getAno() != null && entidadePersistencia.getMes() != null && entidadePersistencia.getNomeUnidade() != null) {
                        refRetificar = entidadePersistencia;
                    } else if(form != null && form.getAno() != null && form.getMes() != null && form.getNomeUnidade() != null) {
                        refRetificar = form;
                    } else {
                        continue;
                    }
                }
                // </epr> 0.6.7 - Para corrigir possÃƒÂ­vel situação de erro (referÃƒÂªncia invÃƒÂ¡lida a entidadePersistencia)
                // <epr>  0.6.7 - refRetificar usada em lugar de entidadePersistencia
                if (refRetificar.getAno().equals(form.getAno()) &&
                    refRetificar.getMes().equals(form.getMes()) &&
                    refRetificar.getNomeUnidade().equals(form.getNomeUnidade())) {
                // </epr>
                    form.setNomeMagistrado(usuarioMagistrado.getNome());
                    form.setIdMagistrado(usuarioMagistrado.getIdUsuario());
                    formularioService.salvarFormulario(form, null, null);
                }
            }
        }
    }
    */

    public void validarDefinirMagistrado(ActionEvent actionEvent) {
        String msg = null;
        if (ORIGEM_TELA_PREENCHER.equals(acaoPageFlow())) {
            if ("".equals(unidadeFormularioMagistrado)) {
                msg = AppBundleProperties.getString("msg.formulario.unidadeNaoSelecionada");
            } else if (mesReferencia == null || new Long(0).equals(mesReferencia)) {
                msg = AppBundleProperties.getString("msg.formulario.mesReferenciaNaoSelecionado");
            } else if (anoReferencia == null || new Long(0).equals(anoReferencia)) {
                msg = AppBundleProperties.getString("msg.formulario.anoReferenciaNaoSelecionado");
            }
        }
        if ((usuarioMagistrado == null || usuarioMagistrado.getIdUsuario() == null ||
             usuarioMagistrado.getNome() == null) && msg == null) {
            msg = AppBundleProperties.getString("msg.formulario.magistradoNaoSelecionado");
        }

        if (ORIGEM_TELA_RETIFICAR.equals(action)) {
            listaFormulariosRetificacao = new ArrayList<FormularioDTO>();
            for (FormularioDTO form : listaEntidade) {
                if (form.isFlagRetificacao()) {
                    listaFormulariosRetificacao.add(form);
                }
            }
            if (listaFormulariosRetificacao.isEmpty()) {
                msg = AppBundleProperties.getString("msg.formulario.nenhumFormularioSelecionadoParaDefinirMagistrado");
            }

            for (FormularioDTO form : listaFormulariosRetificacao) {
                if (form.getSituacaoFormularioDTO()
                        .getIdentificadorSituacaoFormulario()
                        .equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_APROVADA.toString()) == false &&
                    form.getSituacaoFormularioDTO()
                                                                                                            .getIdentificadorSituacaoFormulario()
                                                                                                            .equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_EM_PREENCHIMENTO.toString()) ==
                                          false &&
                                          form.getSituacaoFormularioDTO()
                                                       .getIdentificadorSituacaoFormulario()
                                                       .equalsIgnoreCase(TipoSituacaoType.RETIFICACAO_CONCLUIDA.toString()) ==
                                          false) {
                    msg =
                        AppBundleProperties.getString("msg.formulario.formularioNaoPermitidoSelecionadoParaDefinirMagistrado");
                    break;
                }
            }
        }

        if (msg == null) {
            UIComponent component = findComponent("confirmacaoDefinirMagistradoPopUp");
            RichPopup popup = (RichPopup) component;
            popup.show(new RichPopup.PopupHints());
        } else {
            mensagemErro(msg);
        }
    }

    public void setPainelPrincipal(RichPanelGroupLayout painelPrincipal) {
        this.painelPrincipal = painelPrincipal;
        // Erro 116 - SÃƒÂ³ atualiza na tela apÃƒÂ³s a existencia do componente "RichPanelGroupLayout painelPrincipal" existir para ser atualizado.
        if (atualizarPainelPrincipal) {
            atualizarComponenteDeTela(this.painelPrincipal);
            atualizarPainelPrincipal = false;
        }
    }

    public RichPanelGroupLayout getPainelPrincipal() {
        return painelPrincipal;
    }

    public void setSecaoMagistrado(SecaoDTO secaoMagistrado) {
        this.secaoMagistrado = secaoMagistrado;
    }

    public SecaoDTO getSecaoMagistrado() {
        return secaoMagistrado;
    }

    public void setSecaoMateria(SecaoDTO secaoMateria) {
        this.secaoMateria = secaoMateria;
    }

    public SecaoDTO getSecaoMateria() {
        return secaoMateria;
    }

    public void setSecaoDadosUnidade(SecaoDTO secaoDadosUnidade) {
        this.secaoDadosUnidade = secaoDadosUnidade;
    }

    public SecaoDTO getSecaoDadosUnidade() {
        return secaoDadosUnidade;
    }

    public void setSecaoEstabelecimento(SecaoDTO secaoEstabelecimento) {
        this.secaoEstabelecimento = secaoEstabelecimento;
    }

    public SecaoDTO getSecaoEstabelecimento() {
        return secaoEstabelecimento;
    }

    public void setSecaoReus(SecaoDTO secaoReus) {
        this.secaoReus = secaoReus;
    }

    public SecaoDTO getSecaoReus() {
        return secaoReus;
    }

    public List<SecaoDTO> getSecoes() {
        return entidadePersistencia.getListaSecoes();
    }

    public void setMostrarAviso(boolean mostrarAviso) {
        this.mostrarAviso = mostrarAviso;
    }

    public boolean isMostrarAviso() {
        return mostrarAviso;
    }

    public void setProcessoConclusoDTO(ProcessoConclusoDTO processoConclusoDTO) {
        this.processoConclusoDTO = processoConclusoDTO;
    }

    public ProcessoConclusoDTO getProcessoConclusoDTO() {
        return processoConclusoDTO;
    }

    public void setMateriaSubSecao(TipoMateriaDTO materiaSubSecao) {
        this.materiaSubSecao = materiaSubSecao;
    }

    public TipoMateriaDTO getMateriaSubSecao() {
        return materiaSubSecao;
    }

    public void setReu(ReuDTO reu) {
        this.reu = reu;
    }

    public ReuDTO getReu() {
        return reu;
    }

    public void setListaTipoNaturezaPrisao(List<TipoNaturezaPrisao> listaTipoNaturezaPrisao) {
        this.listaTipoNaturezaPrisao = listaTipoNaturezaPrisao;
    }

    public List<TipoNaturezaPrisao> getListaTipoNaturezaPrisao() {
        return listaTipoNaturezaPrisao;
    }

    public void setListarTipoMotivoBaixa(List<TipoMotivoBaixa> listarTipoMotivoBaixa) {
        this.listarTipoMotivoBaixa = listarTipoMotivoBaixa;
    }

    public List<TipoMotivoBaixa> getListarTipoMotivoBaixa() {
        return listarTipoMotivoBaixa;
    }

    public void setListaTipoJuiz(List<TipoJuizType> listarTipoJuiz) {
        this.listaTipoJuiz = listarTipoJuiz;
    }

    public List<TipoJuizType> getListaTipoJuiz() {
        return listaTipoJuiz;
    }

    public void setListaProcessosConclusos(List<TipoConclusoDTO> listaProcessosConclusos) {
        this.listaProcessosConclusos = listaProcessosConclusos;
    }

    public List<TipoConclusoDTO> getListaProcessosConclusos() {
        return listaProcessosConclusos;
    }

    public String cancelarPreenchimentoFormulario() {
        painelPrincipal = new RichPanelGroupLayout();
        return "voltar";
    }

    public void setMagistrado(Usuario magistrado) {
        this.magistrado = magistrado;
    }

    public Usuario getMagistrado() {
        return magistrado;
    }

    public void setIdMagistrado(Long idMagistrado) {
        this.idMagistrado = idMagistrado;
    }

    public Long getIdMagistrado() {
        return idMagistrado;
    }

    public void setSubSecaoMateriaRemover(SubSecaoDTO subSecaoMateriaRemover) {
        this.subSecaoMateriaRemover = subSecaoMateriaRemover;
    }

    public SubSecaoDTO getSubSecaoMateriaRemover() {
        return subSecaoMateriaRemover;
    }

    public void setEstabelecimentoPrisionalReu(EstabelecimentoPrisional estabelecimentoPrisionalReu) {
        this.estabelecimentoPrisionalReu = estabelecimentoPrisionalReu;
    }

    public EstabelecimentoPrisional getEstabelecimentoPrisionalReu() {
        return estabelecimentoPrisionalReu;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }

    public Foro getForo() {
        return foro;
    }

    public void setListaUnidade(List<Unidade> listaUnidade) {
        this.listaUnidade = listaUnidade;
    }

    public List<Unidade> getListaUnidade() {
        return listaUnidade;
    }

    public void setListaForo(List<Foro> listaForo) {
        this.listaForo = listaForo;
    }

    public List<Foro> getListaForo() {
        return listaForo;
    }

    public void setListaMes(List<SelectItem> listaMes) {
        this.listaMes = listaMes;
    }

    public List<SelectItem> getListaMes() {
        return listaMes;
    }

    public void setListaAno(List<SelectItem> listaAno) {
        this.listaAno = listaAno;
    }

    public List<SelectItem> getListaAno() {
        return listaAno;
    }

    public void setListaSituacao(List<SituacaoFormularioDTO> listaSituacao) {
        this.listaSituacao = listaSituacao;
    }

    public List<SituacaoFormularioDTO> getListaSituacao() {
        return listaSituacao;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setListaTipoSituacaoConsulta(List<String> listaTipoSituacaoConsulta) {
        this.listaTipoSituacaoConsulta = listaTipoSituacaoConsulta;
    }

    public List<String> getListaTipoSituacaoConsulta() {
        return listaTipoSituacaoConsulta;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setUsuarioMagistrado(Usuario usuarioMagistrado) {
        this.usuarioMagistrado = usuarioMagistrado;
    }

    public Usuario getUsuarioMagistrado() {
        return usuarioMagistrado;
    }

    public void setSugestaoMagistrado(String sugestaoMagistrado) {
        this.sugestaoMagistrado = sugestaoMagistrado;
    }

    public String getSugestaoMagistrado() {
        return sugestaoMagistrado;
    }

    public void setListaUnidadeFormulario(List<Unidade> listaUnidadeFormulario) {
        this.listaUnidadeFormulario = listaUnidadeFormulario;
    }

    public List<Unidade> getListaUnidadeFormulario() {
        return listaUnidadeFormulario;
    }

    public void setTiposRegraFormulario(Map<Long, Boolean> tiposRegraFormulario) {
        this.tiposRegraFormulario = tiposRegraFormulario;
    }

    public Map<Long, Boolean> getTiposRegraFormulario() {
        return tiposRegraFormulario;
    }

    public void setUnidadeFormularioMagistrado(String unidadeFormularioMagistrado) {
        this.unidadeFormularioMagistrado = unidadeFormularioMagistrado;
    }

    public String getUnidadeFormularioMagistrado() {
        return unidadeFormularioMagistrado;
    }

    public void setMesReferencia(Long mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Long getMesReferencia() {
        return mesReferencia;
    }

    public void setAnoReferencia(Long anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public Long getAnoReferencia() {
        return anoReferencia;
    }

    public void setTituloPagina(String tituloPagina) {
        this.tituloPagina = tituloPagina;
    }

    public String getTituloPagina() {
        return tituloPagina;
    }

    public void setFiltroReuProvisorio(String filtroReuProvisorio) {
        this.filtroReuProvisorio = filtroReuProvisorio;
    }

    public String getFiltroReuProvisorio() {
        return filtroReuProvisorio;
    }

    public long getLongValorMaximo() {
        return Long.MAX_VALUE;
    }

    public void setListaMesReferencia(List<Long> listaMesReferencia) {
        this.listaMesReferencia = listaMesReferencia;
    }

    public List<Long> getListaMesReferencia() {
        return listaMesReferencia;
    }

    public void setListaAnoReferencia(List<Long> listaAnoReferencia) {
        this.listaAnoReferencia = listaAnoReferencia;
    }

    public List<Long> getListaAnoReferencia() {
        return listaAnoReferencia;
    }

    public void setMotivoDevolucao(String motivoDevolucao) {
        this.motivoDevolucao = motivoDevolucao;
    }

    public String getMotivoDevolucao() {
        return motivoDevolucao;
    }

    public void setAlterarMagistrado(boolean alterarMagistrado) {
        this.alterarMagistrado = alterarMagistrado;
    }

    public boolean isAlterarMagistrado() {
        return alterarMagistrado;
    }

    public void setListaFormulariosRetificacao(List<FormularioDTO> listaFormulariosRetificacao) {
        this.listaFormulariosRetificacao = listaFormulariosRetificacao;
    }

    public List<FormularioDTO> getListaFormulariosRetificacao() {
        return listaFormulariosRetificacao;
    }

    public void baixarFormulario(FacesContext facesContext, OutputStream outputStream) throws JRException,
                                                                                              IOException {
        // FAZ O DOWNLOAD DO FORMULARIO
        logger.info(AppBundleProperties.getString("msg.formulario.logDownloadFormuluario") + entidadePersistencia.getNomeFormulario());
        initPreencherFormulario(true);
        JRDataSource dataSource = relatorioFormulario.obterDataSourceColecao(entidadePersistencia);        
        Map<String, Object> parametros = relatorioFormulario.obterParametros(entidadePersistencia);
        parametros.put("tiposRegraFormulario", tiposRegraFormulario);
        //System.out.println("tiposRegraFormulario 6 "+tiposRegraFormulario.get(6l));
        //System.out.println("tiposRegraFormulario 0 "+tiposRegraFormulario.get(0));
        //System.out.println("tiposRegraFormulario 1234567890 "+tiposRegraFormulario.get(1234567890));
        gerarRelatorio.processarRelatorio(extensaoRelatorio, templateRelatorio, outputStream, facesContext, parametros,
                                          dataSource);
    }

    public void setPopupAdicionarProcessoConcluso(RichPopup popupAdicionarProcessoConcluso) {
        this.popupAdicionarProcessoConcluso = popupAdicionarProcessoConcluso;
    }

    public RichPopup getPopupAdicionarProcessoConcluso() {
        return popupAdicionarProcessoConcluso;
    }

    public void setPopupMensagemRetificarProcessoConcluso(RichPopup popupMensagemRetificarProcessoConcluso) {
        this.popupMensagemRetificarProcessoConcluso = popupMensagemRetificarProcessoConcluso;
    }

    public RichPopup getPopupMensagemRetificarProcessoConcluso() {
        return popupMensagemRetificarProcessoConcluso;
    }

    public void setConsistencia(StringBuilder consistencia) {
        this.consistencia = consistencia;
    }

    public StringBuilder getConsistencia() {
        return consistencia;
    }

    private void mockUser() {
        usuarioLogado = new Usuario();
        usuarioLogado.setNome("RENATA PEREIRA NOGUEIRA");
        usuarioLogado.setIdUsuario(34792L);
        Perfil p = new Perfil();
        p.setIdPerfil(1L);
        p.setCodigoPerfil(ConstantesMovjud.PERFIL_COD_RESPONSAVEL);
        usuarioLogado.setPerfil(p);
    }

    public void setPopupConsistencias(RichPopup popupConsistencias) {
        this.popupConsistencias = popupConsistencias;
    }

    public RichPopup getPopupConsistencias() {
        return popupConsistencias;
    }

    public void setPeriodoProcessoConclusoInicio(String periodoProcessoConclusoInicio) {
        this.periodoProcessoConclusoInicio = periodoProcessoConclusoInicio;
    }

    public String getPeriodoProcessoConclusoInicio() {
        return periodoProcessoConclusoInicio;
    }

    public void setPeriodoProcessoConclusoFim(String periodoProcessoConclusoFim) {
        this.periodoProcessoConclusoFim = periodoProcessoConclusoFim;
    }

    public String getPeriodoProcessoConclusoFim() {
        return periodoProcessoConclusoFim;
    }

    public void setInconsistenciaDataBaixa(StringBuilder inconsistenciaDataBaixa) {
        this.inconsistenciaDataBaixa = inconsistenciaDataBaixa;
    }

    public StringBuilder getInconsistenciaDataBaixa() {
        return inconsistenciaDataBaixa;
    }

    public void setFormularioEnvio(FormularioDTO formularioEnvio) {
        this.formularioEnvio = formularioEnvio;
    }

    public FormularioDTO getFormularioEnvio() {
        return formularioEnvio;
    }

    public void setPopupBaixaProcessoConcluso(RichPopup popupBaixaProcessoConcluso) {
        this.popupBaixaProcessoConcluso = popupBaixaProcessoConcluso;
    }

    public RichPopup getPopupBaixaProcessoConcluso() {
        return popupBaixaProcessoConcluso;
    }

    public void setPopupDataBaixaRetificar(RichPopup popupDataBaixaRetificar) {
        this.popupDataBaixaRetificar = popupDataBaixaRetificar;
    }

    public RichPopup getPopupDataBaixaRetificar() {
        return popupDataBaixaRetificar;
    }

    public void setPopupInconsistenciasReus(RichPopup popupInconsistenciasReus) {
        this.popupInconsistenciasReus = popupInconsistenciasReus;
    }

    public RichPopup getPopupInconsistenciasReus() {
        return popupInconsistenciasReus;
    }

    public void setPopupAlterarReu(RichPopup popupAlterarReu) {
        this.popupAlterarReu = popupAlterarReu;
    }

    public RichPopup getPopupAlterarReu() {
        return popupAlterarReu;
    }

    public void setPopupRemoverReuInconsistencia(RichPopup popupRemoverReuInconsistencia) {
        this.popupRemoverReuInconsistencia = popupRemoverReuInconsistencia;
    }

    public RichPopup getPopupRemoverReuInconsistencia() {
        return popupRemoverReuInconsistencia;
    }

    public void setMensagemValidacao(String mensagemValidacao) {
        this.mensagemValidacao = mensagemValidacao;
    }

    public String getMensagemValidacao() {
        return mensagemValidacao;
    }

    public void setTipoValidacao(String tipoValidacao) {
        this.tipoValidacao = tipoValidacao;
    }

    public String getTipoValidacao() {
        return tipoValidacao;
    }


    public void setPopupErroValidacao(RichPopup popupErroValidacao) {
        this.popupErroValidacao = popupErroValidacao;
    }

    public RichPopup getPopupErroValidacao() {
        return popupErroValidacao;
    }

    public void setPopupAvisoValidacao(RichPopup popupAvisoValidacao) {
        this.popupAvisoValidacao = popupAvisoValidacao;
    }

    public RichPopup getPopupAvisoValidacao() {
        return popupAvisoValidacao;
    }

    public void setPopupConfirmacaoValidacao(RichPopup popupConfirmacaoValidacao) {
        this.popupConfirmacaoValidacao = popupConfirmacaoValidacao;
    }

    public RichPopup getPopupConfirmacaoValidacao() {
        return popupConfirmacaoValidacao;
    }

    public String salvarPopupInserirReu() {
        if (reu.getDataPrisao().before(getPrimeiroDiaMesreferencia())) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            popupInfoDataPrisaoReu.show(hints);
        } else {
            salvarPopupConfirmarDataPrisaoReu();
        }
        return null;
    }

    public void setPopupInfoDataPrisaoReu(RichPopup popupInfoDataPrisaoReu) {
        this.popupInfoDataPrisaoReu = popupInfoDataPrisaoReu;
    }

    public RichPopup getPopupInfoDataPrisaoReu() {
        return popupInfoDataPrisaoReu;
    }

    public String salvarPopupConfirmarDataPrisaoReu() {
        popupInfoDataPrisaoReu.hide();
        RichInputDate dataFeitoLevadoAoMagistrado = (RichInputDate) findComponent("dataFeitoLevadoAoMagistrado");
        RichInputText magistradoReu = (RichInputText) findComponent("inputTextMagistradoReus");
        RichInputText relatorioCGJ = (RichInputText) findComponent("inputTextRelatorioCGJ");
        RichInputText presoProvisorio = (RichInputText) findComponent("it27");
        boolean camposConsistentes = true;
        if(!validaNomeReuEmaeReu()){
            mensagemErroComponente(presoProvisorio, AppBundleProperties.getString("msg.validacao.duplicidadeReuProvisorio"));
            camposConsistentes = false;
        }
        
        if (dataFeitoLevadoAoMagistrado.getValue() != null) {
            if ((magistradoReu.getValue() == null || magistradoReu.getValue()
                                                                  .toString()
                                                                  .isEmpty()) ||
                (relatorioCGJ.getValue() == null ||
                 relatorioCGJ.getValue()
                                                                                                                                 .toString()
                                                                                                                                 .isEmpty())) {
                
                if (magistradoReu.getValue() == null || magistradoReu.getValue()
                                                                     .toString()
                                                                     .isEmpty())
                    mensagemErroComponente(magistradoReu, AppBundleProperties.getString("msg.validacao"));
                if (relatorioCGJ.getValue() == null || relatorioCGJ.getValue()
                                                                   .toString()
                                                                   .isEmpty())
                    mensagemErroComponente(relatorioCGJ, AppBundleProperties.getString("msg.validacao"));
                camposConsistentes = false;
            }
        }
        if (camposConsistentes) {
            if (validarUltimoMovimentoNoPeriodoDeNoventaDias(reu, getUltimoDiaMesReferencia())) {
                if (edicaoReu) {
                    // <epr 20180824, edição sempre gera novo registro histórico>
                    reu.setIdReuHistorico(null);
                    
                    if (isPreencherDtDataBaixa())
                        reu.setDtDataBaixa(new Date());
                    
                    secaoReus.getListaSubSecoes()
                             .get(0)
                             .getListaReus()
                             .set(secaoReus.getListaSubSecoes()
                                           .get(0)
                                           .getListaReus()
                                           .indexOf(reu), reu);
                    getPopupAlterarReu().hide();
                } else {
                    reu.setAno(entidadePersistencia.getAno().intValue());
                    reu.setMes(entidadePersistencia.getMes().intValue());
                    reu.setIdUnidade(entidadePersistencia.getIdUnidade());
                    secaoReus.getListaSubSecoes()
                             .get(0)
                             .getListaReus()
                             .add(reu);
                    getPopupIncluirReus().hide();
                }
                filtrarReusProvisorios(null);
                atualizarComponenteDeTela("tabelaDeReus");
            } else {
                getPopupInconsistenciasReus().show(new RichPopup.PopupHints());
            }
        }
        return null;
    }

    public void setPopupIncluirReus(RichPopup popupIncluirReus) {
        this.popupIncluirReus = popupIncluirReus;
    }

    public RichPopup getPopupIncluirReus() {
        return popupIncluirReus;
    }

    public void setEdicaoReu(boolean edicaoReu) {
        this.edicaoReu = edicaoReu;
    }

    public boolean isEdicaoReu() {
        return edicaoReu;
    }

    public void setControleValidacaoAviso(boolean controleValidacaoAviso) {
        this.controleValidacaoAviso = controleValidacaoAviso;
    }

    public boolean isControleValidacaoAviso() {
        return controleValidacaoAviso;
    }

    public void initPopupAlterarReus(PopupFetchEvent popupFetchEvent) {
        edicaoReu = true;
    }


    public void setPopupInconsistenciaProcessoCemDias(RichPopup popupInconsistenciaProcessoCemDias) {
        this.popupInconsistenciaProcessoCemDias = popupInconsistenciaProcessoCemDias;
    }

    public RichPopup getPopupInconsistenciaProcessoCemDias() {
        return popupInconsistenciaProcessoCemDias;
    }

    public void inspecaoNaoRealizadaChangeListener(ValueChangeEvent valueChangeEvent) {
        RichSelectBooleanCheckbox sbc1 = (RichSelectBooleanCheckbox) findComponent("sbc1");
        if (sbc1.isSelected()) {
            RichInputText itMagistrado = (RichInputText) findComponent("it27");
            RichInputDate idData = (RichInputDate) findComponent("id22");
            itMagistrado.setValue("");
            idData.setValue("");
            atualizarComponenteDeTela(itMagistrado);
            atualizarComponenteDeTela(idData);
        } else {
            RichInputText itMotivo = (RichInputText) findComponent("it28");
            itMotivo.setValue("");
            atualizarComponenteDeTela(itMotivo);
        }
    }

    public String limparSugestaoMagistrado() {
        RichInputText it = (RichInputText) findComponent("inputTextMagistradoSugestao");
        it.setValue("");
        ResetUtils.reset(it);
        AdfFacesContext.getCurrentInstance().addPartialTarget(it);
        return null;
    }

    public void filtrarReusProvisorios(ActionEvent actionEvent) {
        listaReusProvisoriosFiltrada = new ArrayList<ReuDTO>();
        listaReusProvisoriosFiltrada.addAll(secaoReus.getListaSubSecoes()
                                                     .get(0)
                                                     .getListaReus());
        for (ReuDTO reu : secaoReus.getListaSubSecoes()
                                   .get(0)
                                   .getListaReus()) {
            if (!reu.getNomeReuProvisorio()
                    .toUpperCase()
                    .contains(filtroReuProvisorio.toUpperCase())) {
                listaReusProvisoriosFiltrada.remove(reu);
            }
        }
    }


    public void setListaReusProvisoriosFiltrada(List<ReuDTO> listaReusProvisoriosFiltrada) {
        this.listaReusProvisoriosFiltrada = listaReusProvisoriosFiltrada;
    }

    public List<ReuDTO> getListaReusProvisoriosFiltrada() {
        return listaReusProvisoriosFiltrada;
    }

    public void setPopupRemoverProcessoConclusoForaDoPeriodo(RichPopup popupRemoverProcessoConclusoForaDoPeriodo) {
        this.popupRemoverProcessoConclusoForaDoPeriodo = popupRemoverProcessoConclusoForaDoPeriodo;
    }

    public RichPopup getPopupRemoverProcessoConclusoForaDoPeriodo() {
        return popupRemoverProcessoConclusoForaDoPeriodo;
    }

    public void setInconsistenciaRemoverProcessoConcluso(String inconsistenciaRemoverProcessoConcluso) {
        this.inconsistenciaRemoverProcessoConcluso = inconsistenciaRemoverProcessoConcluso;
    }

    public String getInconsistenciaRemoverProcessoConcluso() {
        return inconsistenciaRemoverProcessoConcluso;
    }

    public void cancelarRemoverSubSecao(PopupCanceledEvent popupCanceledEvent) {
        // Add event code here...
    }

    private boolean isPreencherDtDataBaixa() {
        boolean colocarDtDataBaixa = false;
        if (reu.getDataBaixa() == null && reuCopia.getDataBaixa() == null) // se os dois forem nulos, não teve alteraçao
            colocarDtDataBaixa = false;
        else if (reuCopia.getDataBaixa() ==
                 null) // se o nulo for o 'reu.getDataBaixa()' só, é um problema na interface, nao deveria
            colocarDtDataBaixa = true;
        else if (reu.getDataBaixa() != null && reuCopia.getDataBaixa() != null) { // se o nulo for o 'reu.getDataBaixa()' só, é um problema na interface, nao deveria
            if (reu.getDataBaixa().equals(reuCopia.getDataBaixa()))
                colocarDtDataBaixa = false;
            else if ((new SimpleDateFormat("dd/MM/yyyy")).format(reu.getDataBaixa())
                     .equalsIgnoreCase((new SimpleDateFormat("dd/MM/yyyy")).format(reuCopia.getDataBaixa())))
                colocarDtDataBaixa = true;
            else
                colocarDtDataBaixa = false;
        }
        return colocarDtDataBaixa;
    }

    public void setFuncaoRetificacao(boolean funcaoRetificacao) {
        this.funcaoRetificacao = funcaoRetificacao;
    }

    public boolean isFuncaoRetificacao() {
        return funcaoRetificacao;
    }

    public void setSubSecaoProcessoConclusoDTO(SubSecaoDTO subSecaoProcessoConclusoDTO) {
        this.subSecaoProcessoConclusoDTO = subSecaoProcessoConclusoDTO;
    }

    public SubSecaoDTO getSubSecaoProcessoConclusoDTO() {
        return subSecaoProcessoConclusoDTO;
    }

    private void verificaProcessoConclusoUnidade() {
        ProcessoConclusoDTO processoJaNaUnidade = null;
        if (subSecaoProcessoConclusoDTO != null && subSecaoProcessoConclusoDTO.getListaProcessosConclusos() != null &&
            !subSecaoProcessoConclusoDTO.getListaProcessosConclusos().isEmpty()) {
            for (ProcessoConclusoDTO pcNaUnidade : subSecaoProcessoConclusoDTO.getListaProcessosConclusos()) {
                if (pcNaUnidade.getNumeroProcesso().longValue() ==
                    processoConclusoDTO.getNumeroProcesso().longValue()) {
                    processoJaNaUnidade = pcNaUnidade;
                }
            }
        }

        if (processoJaNaUnidade != null) {
            processoJaNaUnidade.setIdMagistradoProcesso(this.idMagistrado);
            processoJaNaUnidade.setDataBaixa(processoConclusoDTO.getDataBaixa());
            processoConclusoDTO.setId(((ProcessoConclusoDTO) processoJaNaUnidade).getId());
        }
    }

    public void setProcessoConclusoUnidadeBoolean(boolean processoConclusoUnidadeBoolean) {
        this.processoConclusoUnidadeBoolean = processoConclusoUnidadeBoolean;
    }

    public boolean isProcessoConclusoUnidadeBoolean() {
        return processoConclusoUnidadeBoolean;
    }

    public void setPopupDataFimDesignacao(RichPopup popupDataFimDesignacao) {
        this.popupDataFimDesignacao = popupDataFimDesignacao;
    }

    public RichPopup getPopupDataFimDesignacao() {
        return popupDataFimDesignacao;
    }

    /*public void setUltimoDiaMesReferenciaAnteriorCemDias(Calendar ultimoDiaMesReferenciaAnteriorCemDias) {
        this.ultimoDiaMesReferenciaAnteriorCemDias = ultimoDiaMesReferenciaAnteriorCemDias;
    }

    public Calendar getUltimoDiaMesReferenciaAnteriorCemDias() {
        return ultimoDiaMesReferenciaAnteriorCemDias;
    }*/

    public void setPeriodoProcessoConclusoInicioCalendar(Calendar periodoProcessoConclusoInicioCalendar) {
        this.periodoProcessoConclusoInicioCalendar = periodoProcessoConclusoInicioCalendar;
    }

    public Date getPeriodoProcessoConclusoInicioCalendar() {
        return periodoProcessoConclusoInicioCalendar.getTime();
    }

    public void setPeriodoProcessoConclusoFimCalendar(Calendar periodoProcessoConclusoFimCalendar) {
        this.periodoProcessoConclusoFimCalendar = periodoProcessoConclusoFimCalendar;
    }

    public Date getPeriodoProcessoConclusoFimCalendar() {
        return periodoProcessoConclusoFimCalendar.getTime();
    }

    public boolean isVisivelConformeTipoRegra(CampoDTO campo) {
        Map<Long, Boolean> tiposRegrasFomrulario = getTiposRegraFormulario();

        if (campo == null || tiposRegrasFomrulario == null || tiposRegrasFomrulario.size() == 0)
            return true;

        //boolean retorno = isVisivelConformeTipoRegra(campo.getCampoPai());
        boolean retorno = true;

        if (retorno) {
            for (Map.Entry<Long, Boolean> entry : tiposRegrasFomrulario.entrySet()) {
                if (campo.getTipoRegraDTO() == null)
                    break;

                if (entry.getKey() == campo.getTipoRegraDTO().getId()) {
                    if (campo.getTipoRegraDTO().getId() != 8) { // diferente da regra primeiro preenchimento
                        retorno = !campo.getTipoRegraDTO().isInverterRegra();
                    } else {
                        retorno =
                            (!existeFormularioPreenchidoAnteriormente && !campo.getTipoRegraDTO().isInverterRegra());
                    }

                    break;
                } else {
                    retorno = false;
                }
            }
        }

        return retorno;
    }

    private FormularioDTO recuperarFormulario(FormularioDTO form) {
        //this.existeFormularioPreenchidoAnteriormente = formularioService.existeFomrularioMesAnterior(form.getIdMetadadosFormulario(), form.getMes(), form.getAno());
        form = formularioService.recuperarFormularioDTOPorIdFormulario(form.getIdFormulario());
        return form;
    }

    private void recalcularFormulasAnteriorEatual() {
        formularioMesAnterior = formularioService.recuperarFormularioMesAnterior(entidadePersistencia);
        entidadePersistencia = FormulaCalculo.calcularFormulasDoFormulario(entidadePersistencia, formularioMesAnterior);
    }

    private boolean validaNomeReuEmaeReu() {
        int numeroRegEncontrados = 0;        
        int numeroRegPermitidos = 0;
        
        if (reu.getIdReuProvisorio() == null)
            numeroRegPermitidos = 0;
        else
            numeroRegPermitidos = 1;
        
        for (ReuDTO r : secaoReus.getListaSubSecoes()
                                 .get(0)
                                 .getListaReus()) {
            if ((r.getNomeMaeReuProvisorio()
                  .trim()
                  .equalsIgnoreCase(reu.getNomeMaeReuProvisorio().trim())) &&
                (r.getNomeReuProvisorio()
                                                                                .trim()
                                                                                .equalsIgnoreCase(reu.getNomeReuProvisorio()
                                                                                                  .trim())))
                numeroRegEncontrados++;
        }
        
        return (numeroRegEncontrados <= numeroRegPermitidos);
    }
}
