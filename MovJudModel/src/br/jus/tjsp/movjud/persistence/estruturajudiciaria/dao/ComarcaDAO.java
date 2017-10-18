package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Circunscricao;
import br.jus.tjsp.movjud.persistence.entity.Comarca;

import java.util.List;

import javax.ejb.Local;
@Local
public interface ComarcaDAO extends BaseDAO<Comarca> {
    public List<Comarca> listarComarcaOrdenadoPorNome(Comarca comarca, Paginacao paginacao);
}
