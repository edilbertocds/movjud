package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;


import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.List;

import javax.ejb.Local;


@Local
public interface UsuarioDAO extends BaseDAO<Usuario> {
    List<Usuario> listarUsuarioOrdenadoPorNomeComPaginacao(Usuario filter, Paginacao paginacao);
    
    List<Usuario> listarUsuarioMagistradoPorNomeComPaginacao(Usuario filter, Paginacao paginacao);
    
    List<Usuario> listarUsuariosPorPerfilPermissaoTemporaria(Usuario usuario, Perfil perfil, Paginacao paginacao);
}
