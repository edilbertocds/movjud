package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Comarca;
import br.jus.tjsp.movjud.persistence.entity.Foro;

import java.util.List;

import javax.ejb.Local;
@Local
public interface ForoDAO extends BaseDAO<Foro>{
    public List<Foro> listarForoOrdenadoPorNome(Foro foro, Paginacao paginacao);
}
