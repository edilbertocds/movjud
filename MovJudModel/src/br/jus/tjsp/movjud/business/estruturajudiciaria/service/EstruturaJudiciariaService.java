package br.jus.tjsp.movjud.business.estruturajudiciaria.service;

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

import java.util.List;

import javax.ejb.Local;

@Local
public interface EstruturaJudiciariaService {
    
    List<Usuario>listarUsuarios();
    
    List<Usuario>listarUsuariosComPaginacao(Paginacao paginacao);
    
    List<Usuario>listarUsuariosComFiltro(Usuario usuario);
    
    List<Usuario>listarUsuariosComFiltroPaginacao(Usuario usuario, Paginacao paginacao);  
    
    List<Usuario>listarUsuarioOrdenadoPorNomeComPaginacao(Usuario usuario, Paginacao paginacao);  
    
    Usuario salvarUsuario(Usuario usuario) throws ModelException;
    
    Usuario atualizarUsuario(Usuario usuario);
    
    void excluirUsuario(Usuario usuario);
    
    List<Perfil>listarPerfis();
    
    List<Perfil>listarPerfisComPaginacao(Paginacao paginacao);
    
    List<Perfil>listarPerfisComFiltro(Perfil perfil);
    
    List<Perfil>listarPerfisComFiltroPaginacao(Perfil perfil, Paginacao paginacao);  
    
    Perfil salvarPerfil(Perfil perfil);
    
    Perfil atualizarPerfil(Perfil perfil);
    
    void excluirPerfil(Perfil perfil);
    
    List<Acao> listarAcaoOrdenadoPorNome();
    
    List<Perfil> listarPerfilOrdenadoPorNome();
    
    List<Acao> listarAcaoPermissoesUsuario(Usuario usuario);
    
    Usuario procurarUsuarioPorCodigoUsuario(Usuario usuario) throws ModelException;
    
    List<Unidade> listarUnidadesComFiltroPaginacao(Unidade unidade, Paginacao paginacao);
    
    List<Unidade> listarUnidadesComFiltro(Unidade unidade);

    Regiao salvarRegiao(Regiao regiao);
    
    List<Regiao> listarRegioesComFiltro(Regiao regiao);
    
    List<Regiao> listarRegioesComFiltroPaginacao(Regiao regiao, Paginacao paginacao);
    
    Circunscricao salvarCircunscricao(Circunscricao circunscricao);
    
    void excluirCircunscricao(Circunscricao circunscricao);
    
    List<Circunscricao> listarCircunscricoesComFiltro(Circunscricao circunscricao);
    
    List<Circunscricao> listarCircunscricoesComFiltroPaginacao(Circunscricao circunscricao, Paginacao paginacao);
    
    Comarca salvarComarca(Comarca comarca);
    
    void excluirComarca(Comarca comarca);
    
    List<Comarca> listarComarcasComFiltro(Comarca comarca);
    
    List<Comarca> listarComarcaComFiltroPaginacao(Comarca comarca, Paginacao paginacao);
    
    Foro salvarForo(Foro foro);
    
    void excluirForo(Foro foro);
    
    List<Foro> listarForoComFiltroPaginacao(Foro foro, Paginacao paginacao);
    
    List<Foro> listarForoComFiltro(Foro foro);
    
    List<Regiao> listarRegioesOrdenadoPorNome(Regiao regiao, Paginacao paginacao);
    
    List<Parametro> listarParametro();
    
    List<Parametro> listarParametroComFiltroPaginacao(Parametro parametro, Paginacao paginacao);
    
    Parametro salvarParametro(Parametro parametro);
    
    void excluirParametro(Parametro parametro);
    
    void excluirRegiao(Regiao regiao);
    
    List<TipoEntrancia> listarTipoEntrancia();
    
    List<TipoLocal> listarTipoLocal();
    
    Unidade salvarUnidade(Unidade unidade);

    void excluirUnidade(Unidade unidade);
    
    List<PermissaoUnidadeTemporaria> listarPermissaoUnidadeTemporariaOrdenadaPorNomeComPaginacao(PermissaoUnidadeTemporaria filter, Paginacao paginacao);
    
    PermissaoUnidadeTemporaria salvarPermissaoUnidadeTemporaria(PermissaoUnidadeTemporaria permissaoUnidadeTemporaria); 
    
    // <edilberto item 199>
    UnidadeEstabelecimentoPrisional obterVinculoMaisRecenteComUnidade(EstabelecimentoPrisional estabelecimentoPrisional);
    // </edilberto item 199>
    
    List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisOrdenadoPorNome(EstabelecimentoPrisional estabelecimentoPrisional, Paginacao paginacao);
    
    List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisComFiltro(EstabelecimentoPrisional estabelecimentoPrisional);
    
    EstabelecimentoPrisional salvarEstabelecimentoPrisional(EstabelecimentoPrisional estabelecimentoPrisional);
    
    void excluirEstabelecimentoPrisional(EstabelecimentoPrisional estabelecimentoPrisional);
    
    List<Unidade> listarUnidadesCodigoDescricao();

    List<Unidade> listarUnidadesCodigoDescricaoPorForo(Foro foro);
    
    List<Unidade> listarUnidadesCodigoDescricaoPorForoEUsuario(Foro foro, Usuario usuario);
    
    List<Unidade> listarUnidadesCodigoDescricaoPorUsuario(Usuario usuario);
    
    Unidade procurarUnidadePorCodigoUnidade(Unidade unidade);
    
    Usuario procurarUsuarioPorNomeUsuario(Usuario usuario);
         
    List<PermissaoUnidadeTemporaria> listarPermissaoUnidadeTemporaria();
      
    void excluirPermissaoTemporaria(PermissaoUnidadeTemporaria permissao);
        
    List<Usuario> listarUsuariosPorPerfilPermissaoTemporaria(Usuario usuario, Perfil perfil, Paginacao paginacao);
    
    List<TipoSituacao> listarTipoSituacaoFiltro(TipoSituacao tipoSituacao);
    
    List<TipoSituacao> listarTipoSituacao();
    
    List<PermissaoUnidadeTemporaria> listarPermissaoUnidadeTemporariaPorFiltro(PermissaoUnidadeTemporaria permissao);
    
    List<DominioBI> listarDominioBI();
    
    List<DominioBI> listarDominioBIComFiltroPaginacao(DominioBI filtro, Paginacao paginacao);
    
    DominioBI salvarDominioBI(DominioBI dominioBI);
    
    void excluirDominioBI(DominioBI dominioBI);
    
}
