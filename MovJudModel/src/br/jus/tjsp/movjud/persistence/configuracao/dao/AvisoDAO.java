package br.jus.tjsp.movjud.persistence.configuracao.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Aviso;

import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.List;

import javax.ejb.Local;

@Local
public interface AvisoDAO extends BaseDAO<Aviso> {

    List<Aviso> listarAvisosExistentesDoUsuario(Usuario usuario);
    
    List<Aviso> listarAvisosExistentesDoUsuario(Usuario usuario, Paginacao paginacao);
    
    Long countAvisosNaoLidos(Usuario usuario);

}
