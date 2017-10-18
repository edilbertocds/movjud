package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;


import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.entity.Acao;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.List;

import javax.ejb.Local;


@Local
public interface AcaoDAO extends BaseDAO<Acao> {
    List<Acao> listarAcaoOrdenadoPorNome(); 
    List<Acao> listarAcaoPermissoesUsuario(Usuario usuario);
}
