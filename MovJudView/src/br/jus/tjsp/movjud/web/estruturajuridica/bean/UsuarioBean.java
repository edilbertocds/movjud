package br.jus.tjsp.movjud.web.estruturajuridica.bean;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.types.MovJudErrorType;
import br.jus.tjsp.movjud.core.types.UsuarioAcaoType;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.entity.Acao;
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.persistence.entity.UsuarioAcao;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import org.apache.log4j.Logger;


@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean extends BaseBean<Usuario> {
    final static Logger logger = Logger.getLogger(UsuarioBean.class);
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;

    private EstruturaJudiciariaService estruturaJudiciaria;

    private List<Perfil> listaPerfis;
    private List<Acao> listaAcoes;

    private List<Perfil> listaPerfisUsuarioSelecionado;

    private transient UsuarioAcaoType acaoCorrente;
    private transient List<UsuarioAcaoType> listaAcoesSelecionadasComMarcacao;

    private boolean direitosEspeciais;


    public UsuarioBean() {
        estruturaJudiciaria = getBean(EstruturaJudiciariaService.class);
    }

    /* PostConstruct executa logo apos o managedBean ser instanciado  */
    @PostConstruct
    public void init() {
        super.init();
        listaPerfis = estruturaJudiciaria.listarPerfilOrdenadoPorNome();
        listaAcoes = estruturaJudiciaria.listarAcaoOrdenadoPorNome();
    }

    /* Metodos */
    @Override
    public String pesquisarEntidade() {
        listaEntidade =
            estruturaJudiciaria.listarUsuarioOrdenadoPorNomeComPaginacao(getEntidadeFiltro(), getPaginacao());
        return null;
    }


    public void popularAcoesCadastro(ValueChangeEvent valueChangeEvent) {
        setDireitosEspeciais(false);

        if (getListaPerfis().contains(valueChangeEvent.getNewValue())) {
            entidadePersistencia.setPerfil((Perfil) valueChangeEvent.getNewValue());
            listaAcoesSelecionadasComMarcacao =
                UsuarioAcaoType.recuperarListaPermissoes(getListaAcoes(), entidadePersistencia.getPerfil().getAcoes(),
                                                         null, isDireitosEspeciais());
        } else {
            listaAcoesSelecionadasComMarcacao =
                UsuarioAcaoType.recuperarListaPermissoes(getListaAcoes(), null, null, isDireitosEspeciais());
        }

        if (!ConstantesMovjud.PERFIL_COD_MAGISTRADO.equals(entidadePersistencia.getPerfil().getCodigoPerfil())) {
            entidadePersistencia.setCodigoUsuarioSaj(null);
        }
    }

    public boolean isCodigoSajRequired() {
        return ((entidadePersistencia.getPerfil() != null) &&
                (ConstantesMovjud.PERFIL_COD_MAGISTRADO.equals(entidadePersistencia.getPerfil().getCodigoPerfil()) ||
                 ConstantesMovjud.PERFIL_COD_DESEMBARGADOR.equals(entidadePersistencia.getPerfil().getCodigoPerfil()) ||
                 ConstantesMovjud.PERFIL_COD_MASSESCORR.equals(entidadePersistencia.getPerfil().getCodigoPerfil())));
    }

    public void alterarPermissaoAcao(ValueChangeEvent valueChangeEvent) {
        if (entidadePersistencia != null) {
            acaoCorrente =
                UsuarioAcaoType.alterarTipoPermissao(entidadePersistencia.getPerfil(), acaoCorrente,
                                                     (Boolean) valueChangeEvent.getNewValue(), isDireitosEspeciais());
            atualizarComponenteDeTela(Arrays.asList("painelAcoesIncluir", "painelAcoesManter"));
        }
    }

    public void alterarDireitosEspeciais(ValueChangeEvent valueChangeEvent) {
        Boolean valorDireitosEspeciais = (Boolean) valueChangeEvent.getNewValue();

        if (valorDireitosEspeciais == false) {
            listaAcoesSelecionadasComMarcacao =
                UsuarioAcaoType.recuperarListaPermissoes(getListaAcoes(), entidadePersistencia.getPerfil().getAcoes(),
                                                         null, isDireitosEspeciais());
        }

        for (UsuarioAcaoType acao : listaAcoesSelecionadasComMarcacao) {
            acao.setHabilitado(valorDireitosEspeciais);
        }
    }


    private static List<UsuarioAcao> criarListaUsuarioAcao(List<UsuarioAcaoType> listaAcoesSelecionadasComMarcacao,
                                                           Usuario usuarioCriar) {
        List<UsuarioAcao> listaUsuarioAcao = new ArrayList<UsuarioAcao>();
        for (UsuarioAcaoType type : listaAcoesSelecionadasComMarcacao) {
            if (type.getPermissaoAcao().equals(UsuarioAcaoType.PermissaoAcaoType.CONCEDIDO)) {
                listaUsuarioAcao.add(novoUsuarioAcao(type.getAcao(), usuarioCriar, ConstantesMovjud.FLAG_SITUACAO_SIM));
            } else if (type.getPermissaoAcao().equals(UsuarioAcaoType.PermissaoAcaoType.REMOVIDO)) {
                listaUsuarioAcao.add(novoUsuarioAcao(type.getAcao(), usuarioCriar, ConstantesMovjud.FLAG_SITUACAO_NAO));
            }
        }
        return listaUsuarioAcao;
    }

    private static UsuarioAcao novoUsuarioAcao(Acao acao, Usuario usuario, String flag) {
        UsuarioAcao usuarioAcao = new UsuarioAcao();
        usuarioAcao.setTipoAcao(acao);
        usuarioAcao.setUsuario(usuario);
        usuarioAcao.setFlagPermitido(flag);
        return usuarioAcao;
    }

    @Override
    public void excluirEntidade(DialogEvent dialogEvent) {
        try {
            estruturaJudiciaria.excluirUsuario(entidadePersistencia);
            pesquisarEntidade();
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e, dialogEvent.getComponent().getParent());
        }
    }

    @Override
    public Class<Usuario> getClasseEntidade() {
        return Usuario.class;
    }

    @Override
    public void initDialogInserir(PopupFetchEvent popupFetchEvent) {
        super.initDialogInserir(popupFetchEvent);
        listaAcoesSelecionadasComMarcacao =
            UsuarioAcaoType.recuperarListaPermissoes(getListaAcoes(), null, null, isDireitosEspeciais());
    }

    @Override
    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        super.initDialogAlterar(popupFetchEvent);
        listaAcoesSelecionadasComMarcacao =
            UsuarioAcaoType.recuperarListaPermissoes(getListaAcoes(), entidadePersistencia.getPerfil().getAcoes(),
                                                     entidadePersistencia.getAcoesUsuario(), isDireitosEspeciais());
        setDireitosEspeciais(entidadePersistencia.getAcoesUsuario() != null &&
                             entidadePersistencia.getAcoesUsuario().size() > 0 ? true : false);
    }

    @Override
    public void initDialogVisualizar(PopupFetchEvent popupFetchEvent) {
        super.initDialogVisualizar(popupFetchEvent);
        listaAcoesSelecionadasComMarcacao =
            UsuarioAcaoType.recuperarListaPermissoes(getListaAcoes(), entidadePersistencia.getPerfil().getAcoes(),
                                                     entidadePersistencia.getAcoesUsuario(), isDireitosEspeciais());
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        logger.info(AppBundleProperties.getString("msg.usuario.log") + entidadePersistencia.getNome());

        try {
            entidadePersistencia.setAcoesUsuario(criarListaUsuarioAcao(listaAcoesSelecionadasComMarcacao,
                                                                       entidadePersistencia));
            estruturaJudiciaria.salvarUsuario(entidadePersistencia);

            if (dialogEvent.getComponent()
                           .getClientId()
                           .toString()
                           .contains("incluirUsuarioDialog")) {
                popUpConfirmacao("popupInfoInclusao");
            }
            if (dialogEvent.getComponent()
                           .getClientId()
                           .toString()
                           .contains("alterarUsuarioDialog")) {
                popUpConfirmacao("popupInfoAlterado");
            }
            pesquisarEntidade();
        } catch (Exception e) {
            MovJudErrorType errorType = MovJudErrorMessage.getErrorType(e);
            if (errorType.getCodigoErro().equals("ORA-00001")) {
                MovJudErrorMessage.gerarErroMovjud(logger, e, "Usuário já cadastrado");
            } else {
                MovJudErrorMessage.gerarErroMovjud(logger, e);
            }
        }
    }


    /* Gets e Sets */
    public void setListaPerfis(List<Perfil> listaPerfis) {
        this.listaPerfis = listaPerfis;
    }

    public List<Perfil> getListaPerfis() {
        return listaPerfis;
    }

    public void setListaPerfisUsuarioSelecionado(List<Perfil> listaPerfisUsuarioSelecionado) {
        this.listaPerfisUsuarioSelecionado = listaPerfisUsuarioSelecionado;
    }

    public List<Perfil> getListaPerfisUsuarioSelecionado() {
        return listaPerfisUsuarioSelecionado;
    }

    public void setListaAcoes(List<Acao> listaAcoes) {
        this.listaAcoes = listaAcoes;
    }

    public List<Acao> getListaAcoes() {
        return listaAcoes;
    }

    public void setListaAcoesSelecionadasComMarcacao(List<UsuarioAcaoType> listaAcoesSelecionadasComMarcacao) {
        this.listaAcoesSelecionadasComMarcacao = listaAcoesSelecionadasComMarcacao;
    }

    public List<UsuarioAcaoType> getListaAcoesSelecionadasComMarcacao() {
        return listaAcoesSelecionadasComMarcacao;
    }

    public void setAcaoCorrente(UsuarioAcaoType acaoCorrente) {
        this.acaoCorrente = acaoCorrente;
    }

    public UsuarioAcaoType getAcaoCorrente() {
        return acaoCorrente;
    }

    public void setDireitosEspeciais(boolean direitosEspeciais) {
        this.direitosEspeciais = direitosEspeciais;
    }

    public boolean isDireitosEspeciais() {
        return direitosEspeciais;
    }

    @Override
    public String excluirEntidade() {
        // TODO Implement this method
        return null;
    }

}
