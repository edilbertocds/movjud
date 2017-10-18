package br.jus.tjsp.movjud.persistence.configuracao.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.AvisoEstrutura;

import java.util.List;

import javax.ejb.Local;

@Local
public interface AvisoEstruturaDAO extends BaseDAO<AvisoEstrutura> {
    
    List<AvisoEstrutura> listarAvisoEstruturaOrdenadoPorNome();
    
    List<AvisoEstrutura> listarAvisoEstruturaOrdenadoPorNome(AvisoEstrutura avisoEstrutura, Paginacao paginacao);
}
