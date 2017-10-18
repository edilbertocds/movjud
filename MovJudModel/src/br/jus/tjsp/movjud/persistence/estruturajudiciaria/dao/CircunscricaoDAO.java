package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Circunscricao;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CircunscricaoDAO extends BaseDAO<Circunscricao>{
    
    public List<Circunscricao> listarCircunscricaoOrdenadoPorNome(Circunscricao circunscricao, Paginacao paginacao);
}
