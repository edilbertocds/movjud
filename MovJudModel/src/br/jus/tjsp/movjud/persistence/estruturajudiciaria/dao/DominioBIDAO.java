package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.dao.PersistenceManager;
import br.jus.tjsp.movjud.persistence.entity.DominioBI;

import java.io.Serializable;

import java.util.List;

public interface DominioBIDAO extends Serializable {
    DominioBI salvar(DominioBI dominioBI) throws RuntimeException;
    
    DominioBI atualizar(DominioBI dominioBI) throws RuntimeException;
    
    DominioBI procurarPorId(Serializable t);

    void excluir(DominioBI dominioBI);

    List<DominioBI> listar();

    List<DominioBI> listar(Paginacao paginacao);

    List<DominioBI> listarComOrdenacao();
    
    List<DominioBI> listarComOrdenacao(Paginacao paginacao);

    List<DominioBI> listarComFiltro(DominioBI filter);

    List<DominioBI> listarComFiltro(DominioBI filter, Paginacao paginacao);
    
    void refresh(DominioBI t);

    Class<DominioBI> getPersistentClass();

    PersistenceManager getPersistenceManager();
}
