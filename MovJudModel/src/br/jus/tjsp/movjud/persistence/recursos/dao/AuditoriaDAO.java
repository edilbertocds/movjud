package br.jus.tjsp.movjud.persistence.recursos.dao;


import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Auditoria;

import java.util.List;

import javax.ejb.Local;


@Local
public interface AuditoriaDAO extends BaseDAO<Auditoria> {
    List<String> listarUsuariosAuditoria();
    
    List<String> listarEntidadesAuditoria();
    
    List<Auditoria> listarAuditoriasComFiltroPaginacao(Auditoria auditoria, Paginacao paginacao);

}
