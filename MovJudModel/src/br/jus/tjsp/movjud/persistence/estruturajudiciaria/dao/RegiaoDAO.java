package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Regiao;

import java.util.List;

import javax.ejb.Local;

@Local
public interface RegiaoDAO extends BaseDAO<Regiao>{
    
    List<Regiao> listarRegioesOrdenadoPorNome(Regiao regiao,Paginacao paginacao);
 
}
