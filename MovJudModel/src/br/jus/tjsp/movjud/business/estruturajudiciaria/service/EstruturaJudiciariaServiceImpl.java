package br.jus.tjsp.movjud.business.estruturajudiciaria.service;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.core.exception.ModelException;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Acao;
import br.jus.tjsp.movjud.persistence.entity.Circunscricao;
import br.jus.tjsp.movjud.persistence.entity.Comarca;
import br.jus.tjsp.movjud.persistence.entity.DominioBI;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.Foro;
import br.jus.tjsp.movjud.persistence.entity.Parametro;
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.PermissaoUnidadeTemporaria;
import br.jus.tjsp.movjud.persistence.entity.Regiao;
import br.jus.tjsp.movjud.persistence.entity.TipoEntrancia;
import br.jus.tjsp.movjud.persistence.entity.TipoLocal;
import br.jus.tjsp.movjud.persistence.entity.TipoSituacao;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.UnidadeEstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.AcaoDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.CircunscricaoDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.ComarcaDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.DominioBIDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.EstabelecimentoPrisionalDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.ForoDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.ParametroDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.PerfilDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.PermissaoUnidadeTemporariaDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.RegiaoDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.TipoEntranciaDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.TipoLocalDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.UnidadeDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.UsuarioDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.TipoSituacaoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

@Stateless(name = "EstruturaJudiciariaService", mappedName = "MovJudModel")
public class EstruturaJudiciariaServiceImpl implements EstruturaJudiciariaService {
    final static Logger logger = Logger.getLogger(EstruturaJudiciariaServiceImpl.class);

    @EJB
    private UsuarioDAO usuarioDAO;

    @EJB
    private PerfilDAO perfilDAO;

    @EJB
    private AcaoDAO acaoDAO;

    @EJB
    private UnidadeDAO unidadeDAO;

    @EJB
    private RegiaoDAO regiaoDAO;

    @EJB
    private CircunscricaoDAO circunscricaoDAO;

    @EJB
    private ComarcaDAO comarcaDAO;

    @EJB
    private ForoDAO foroDAO;

    @EJB
    private ParametroDAO parametroDAO;

    @EJB
    private TipoEntranciaDAO tipoEntranciaDAO;

    @EJB
    private TipoLocalDAO tipoLocalDAO;

    @EJB
    private PermissaoUnidadeTemporariaDAO permissaoUnidadeTemporariaDAO;

    @EJB
    private EstabelecimentoPrisionalDAO estabelecimentoPrisionalDAO;

    @EJB
    private TipoSituacaoDAO tipoSituacaoDAO;
    
    @EJB
    private DominioBIDAO dominioBIDAO;


    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> listarUsuariosComPaginacao(Paginacao paginacao) {
        return usuarioDAO.listar(paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> listarUsuariosComFiltro(Usuario usuario) {
        return usuarioDAO.listarComFiltro(usuario);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> listarUsuariosComFiltroPaginacao(Usuario usuario, Paginacao paginacao) {
        return usuarioDAO.listarComFiltro(usuario, paginacao);
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) throws ModelException{
        return usuarioDAO.salvar(usuario);
    }

    @Override
    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioDAO.atualizar(usuario);
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        usuarioDAO.excluir(usuario);
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Perfil> listarPerfis() {
        return perfilDAO.listar();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Perfil> listarPerfisComPaginacao(Paginacao paginacao) {
        return perfilDAO.listar(paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Perfil> listarPerfisComFiltro(Perfil perfil) {
        return perfilDAO.listarComFiltro(perfil);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Perfil> listarPerfisComFiltroPaginacao(Perfil perfil, Paginacao paginacao) {
        return perfilDAO.listarComFiltro(perfil, paginacao);
    }

    @Override
    public Perfil salvarPerfil(Perfil perfil) {
        return perfilDAO.salvar(perfil);
    }

    @Override
    public Perfil atualizarPerfil(Perfil perfil) {
        return perfilDAO.atualizar(perfil);
    }

    @Override
    public void excluirPerfil(Perfil perfil) {
        perfilDAO.excluir(perfil);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Acao> listarAcaoOrdenadoPorNome() {
        return acaoDAO.listarAcaoOrdenadoPorNome();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Perfil> listarPerfilOrdenadoPorNome() {
        return perfilDAO.listarPerfilOrdenadoPorNome().stream().filter(p->p.getId() != null && p.getId() > 0).collect(Collectors.toList());
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Acao> listarAcaoPermissoesUsuario(Usuario usuario) {
        return acaoDAO.listarAcaoPermissoesUsuario(usuario);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Usuario procurarUsuarioPorCodigoUsuario(Usuario usuario) throws ModelException{
        Usuario usuarioResult = null;
        if (usuario != null) {
            usuarioResult = usuarioDAO.procurarComFiltro(usuario);
        }

        return usuarioResult;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Unidade> listarUnidadesComFiltroPaginacao(Unidade unidade, Paginacao paginacao) {
        String[] campoOrdenacao = {"foro.comarca.circunscricao.regiao.nomeRegiao","foro.comarca.circunscricao.nomeCircunscricao","foro.comarca.nomeComarca","foro.nomeForo","nomeUnidade"};
        Boolean[] ascOrdenacao = {true,true,true,true,true};
        return //unidadeDAO.listarComFiltro(unidade, paginacao);
            unidadeDAO.listarComFiltroOrdenacao(unidade, campoOrdenacao, ascOrdenacao, paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Unidade> listarUnidadesComFiltro(Unidade unidade) {
        return unidadeDAO.listarUnidadesOrdenadoPorNome(unidade);
    }

    @Override
    public Regiao salvarRegiao(Regiao regiao) {
        return regiaoDAO.salvar(regiao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Regiao> listarRegioesComFiltro(Regiao regiao) {
        return regiaoDAO.listarComFiltro(regiao);
    }

    @Override
    public List<Regiao> listarRegioesComFiltroPaginacao(Regiao regiao, Paginacao paginacao) {

        return regiaoDAO.listarRegioesOrdenadoPorNome(regiao, paginacao);
    }

    @Override
    public Circunscricao salvarCircunscricao(Circunscricao circunscricao) {
        return circunscricaoDAO.salvar(circunscricao);
    }

    @Override
    public void excluirCircunscricao(Circunscricao circunscricao) {
        circunscricaoDAO.excluir(circunscricao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Circunscricao> listarCircunscricoesComFiltro(Circunscricao circunscricao) {
        return circunscricaoDAO.listarComFiltro(circunscricao);
    }

    @Override
    public Comarca salvarComarca(Comarca comarca) {
        return comarcaDAO.salvar(comarca);
    }

    @Override
    public void excluirComarca(Comarca comarca) {
        comarcaDAO.excluir(comarca);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Comarca> listarComarcasComFiltro(Comarca comarca) {
        return comarcaDAO.listarComFiltro(comarca);
    }

    @Override
    public Foro salvarForo(Foro foro) {
        return foroDAO.salvar(foro);
    }

    @Override
    public void excluirForo(Foro foro) {
        foroDAO.excluir(foro);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Regiao> listarRegioesOrdenadoPorNome(Regiao regiao, Paginacao paginacao) {
        return regiaoDAO.listarRegioesOrdenadoPorNome(regiao, paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Parametro> listarParametro() {
        return parametroDAO.listar();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Parametro> listarParametroComFiltroPaginacao(Parametro parametro, Paginacao paginacao) {
        return parametroDAO.listarComFiltro(parametro, paginacao);
    }

    @Override
    public Parametro salvarParametro(Parametro parametro) {
        return parametroDAO.salvar(parametro);
    }

    @Override
    public void excluirParametro(Parametro parametro) {
        parametroDAO.excluir(parametro);
    }

    @Override
    public void excluirRegiao(Regiao regiao) {
        regiaoDAO.excluir(regiao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Circunscricao> listarCircunscricoesComFiltroPaginacao(Circunscricao circunscricao,
                                                                      Paginacao paginacao) {
        String[] campoOrdenacao = {"regiao.nomeRegiao","nomeCircunscricao"};
        Boolean[] ascOrdenacao = {true,true};
        return //circunscricaoDAO.listarCircunscricaoOrdenadoPorNome(circunscricao, paginacao);
        circunscricaoDAO.listarComFiltroOrdenacao(circunscricao, campoOrdenacao, ascOrdenacao, paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Comarca> listarComarcaComFiltroPaginacao(Comarca comarca, Paginacao paginacao) {
        String[] campoOrdenacao = {"circunscricao.regiao.nomeRegiao","circunscricao.nomeCircunscricao","nomeComarca"};
        Boolean[] ascOrdenacao = {true,true,true};
        return //comarcaDAO.listarComarcaOrdenadoPorNome(comarca, paginacao);
        comarcaDAO.listarComFiltroOrdenacao(comarca, campoOrdenacao, ascOrdenacao, paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Foro> listarForoComFiltroPaginacao(Foro foro, Paginacao paginacao) {
        String[] campoOrdenacao = {"comarca.circunscricao.regiao.nomeRegiao","comarca.circunscricao.nomeCircunscricao","comarca.nomeComarca","nomeForo"};
        Boolean[] ascOrdenacao = {true,true,true,true};
        return //foroDAO.listarForoOrdenadoPorNome(foro, paginacao);
        foroDAO.listarComFiltroOrdenacao(foro, campoOrdenacao, ascOrdenacao, paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Foro> listarForoComFiltro(Foro foro) {
        return foroDAO.listarComFiltroOrdenacao(foro, "nomeForo", true);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<TipoEntrancia> listarTipoEntrancia() {
        return tipoEntranciaDAO.listar();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<TipoLocal> listarTipoLocal() {
        return tipoLocalDAO.listarComOrdenacao("nomeLocal", true);
    }

    @Override
    public Unidade salvarUnidade(Unidade unidade) {
        return unidadeDAO.salvar(unidade);
    }

    @Override
    public void excluirUnidade(Unidade unidade) {
        unidadeDAO.excluir(unidade);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> listarUsuarioOrdenadoPorNomeComPaginacao(Usuario usuario, Paginacao paginacao) {
        return usuarioDAO.listarUsuarioOrdenadoPorNomeComPaginacao(usuario, paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<PermissaoUnidadeTemporaria> listarPermissaoUnidadeTemporariaOrdenadaPorNomeComPaginacao(PermissaoUnidadeTemporaria filter,
                                                                                                        Paginacao paginacao) {
        return permissaoUnidadeTemporariaDAO.listarPermissaoUnidadeTemporariaOrdenadaPorNomeComPaginacao(filter,
                                                                                                         paginacao);
    }

    @Override
    public PermissaoUnidadeTemporaria salvarPermissaoUnidadeTemporaria(PermissaoUnidadeTemporaria permissaoUnidadeTemporaria) {
        return permissaoUnidadeTemporariaDAO.salvar(permissaoUnidadeTemporaria);
    }
    
    // <edilberto item 199>
    @Override
    public UnidadeEstabelecimentoPrisional obterVinculoMaisRecenteComUnidade(EstabelecimentoPrisional estabelecimentoPrisional) {
        return estabelecimentoPrisionalDAO.obterVinculoMaisRecenteComUnidade(estabelecimentoPrisional);
    }
    // </edilberto item 199>

    @Override
    public List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisOrdenadoPorNome(EstabelecimentoPrisional estabelecimentoPrisional,
                                                                                          Paginacao paginacao) {
        
        return estabelecimentoPrisionalDAO.listarEstabelecimentosPrisionaisOrdenadoPorNome(estabelecimentoPrisional,
                                                                                           paginacao);
        /*
        return estabelecimentoPrisionalDAO.listarEstabelecimentosPrisionaisSemUnidadeAtivoPorNome(estabelecimentoPrisional, paginacao);
        */
    }
    
    @Override 
    public List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisSemUnidadeAtivoPorNome(EstabelecimentoPrisional estabelecimentoPrisional,
                                                                                                            Paginacao paginacao) {
        return estabelecimentoPrisionalDAO.listarEstabelecimentosPrisionaisSemUnidadeAtivoPorNome(estabelecimentoPrisional, paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisComFiltro(EstabelecimentoPrisional estabelecimentoPrisional) {
        return estabelecimentoPrisionalDAO.listarComFiltro(estabelecimentoPrisional);
    }

    @Override
    public EstabelecimentoPrisional salvarEstabelecimentoPrisional(EstabelecimentoPrisional estabelecimentoPrisional) {
        return estabelecimentoPrisionalDAO.salvar(estabelecimentoPrisional);
    }

    @Override
    public void excluirEstabelecimentoPrisional(EstabelecimentoPrisional estabelecimentoPrisional) {
        estabelecimentoPrisionalDAO.excluir(estabelecimentoPrisional);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Unidade> listarUnidadesCodigoDescricaoPorUsuario(Usuario usuario) {
        return unidadeDAO.listarUnidadesCodigoDescricaoPorUsuario(usuario);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Unidade> listarUnidadesCodigoDescricao() {
        return unidadeDAO.listarUnidadesCodigoDescricao();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Unidade> listarUnidadesCodigoDescricaoPorForo(Foro foro) {
        return unidadeDAO.listarUnidadesCodigoDescricaoPorForo(foro);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Unidade> listarUnidadesCodigoDescricaoPorForoEUsuario(Foro foro, Usuario usuario) {
        if (ConstantesMovjud.PERFIL_COD_FUNCIONARIO.equals(usuario.getPerfil().getCodigoPerfil()) ||
            ConstantesMovjud.PERFIL_COD_RESPONSAVEL.equals(usuario.getPerfil().getCodigoPerfil())) {
            return unidadeDAO.listarUnidadesCodigoDescricaoPorForoEUsuario(foro, usuario);
        }
        return unidadeDAO.listarUnidadesCodigoDescricaoPorForo(foro);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<PermissaoUnidadeTemporaria> listarPermissaoUnidadeTemporaria() {
        return permissaoUnidadeTemporariaDAO.listar();
    }

    @Override
    public void excluirPermissaoTemporaria(PermissaoUnidadeTemporaria permissao) {
        permissaoUnidadeTemporariaDAO.excluir(permissao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Unidade procurarUnidadePorCodigoUnidade(Unidade unidade) {
        return unidadeDAO.procurarUnidadePorCodigoUnidade(unidade);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Usuario procurarUsuarioPorNomeUsuario(Usuario usuario) {
        return null;
    }

    public List<Usuario> listarUsuariosPorPerfilPermissaoTemporaria(Usuario usuario, Perfil perfil,
                                                                    Paginacao paginacao) {
        if (ConstantesMovjud.PERFIL_COD_RESPONSAVEL.equals(perfil.getCodigoPerfil()) || ConstantesMovjud.PERFIL_COD_ADMIN.equals(perfil.getCodigoPerfil())) {
            return usuarioDAO.listarUsuariosPorPerfilPermissaoTemporaria(usuario, perfil, paginacao);
        }
        return new ArrayList<Usuario>();
    }

    @Override
    public List<TipoSituacao> listarTipoSituacaoFiltro(TipoSituacao tipoSituacao) {
        return tipoSituacaoDAO.listarComFiltro(tipoSituacao);
    }

    @Override
    public List<TipoSituacao> listarTipoSituacao() {
        return tipoSituacaoDAO.listar();
    }

    @Override
    public List<PermissaoUnidadeTemporaria> listarPermissaoUnidadeTemporariaPorFiltro(PermissaoUnidadeTemporaria permissao) {
        return permissaoUnidadeTemporariaDAO.listarComFiltro(permissao);
    }
    
    
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<DominioBI> listarDominioBI() {
        return dominioBIDAO.listar();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<DominioBI> listarDominioBIComFiltroPaginacao(DominioBI parametro, Paginacao paginacao) {
        return dominioBIDAO.listarComFiltro(parametro, paginacao);
    }

    @Override
    public DominioBI salvarDominioBI(DominioBI parametro) {
        return dominioBIDAO.salvar(parametro);
    }

    @Override
    public void excluirDominioBI(DominioBI parametro) {
        dominioBIDAO.excluir(parametro);
    }
}

