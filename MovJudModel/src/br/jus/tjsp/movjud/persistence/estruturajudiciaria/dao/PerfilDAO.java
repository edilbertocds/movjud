package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;


import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.entity.Perfil;

import java.util.List;

import javax.ejb.Local;


@Local
public interface PerfilDAO extends BaseDAO<Perfil> {
    
    List<Perfil> listarPerfilOrdenadoPorNome();  

}
