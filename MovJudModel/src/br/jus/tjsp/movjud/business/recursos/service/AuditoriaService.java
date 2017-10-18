package br.jus.tjsp.movjud.business.recursos.service;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Auditoria;

import java.io.Serializable;

import java.util.List;

import javax.ejb.Local;

@Local
public interface AuditoriaService extends Serializable{
    
    List<Auditoria> listarAuditoriasComFiltroPaginacao(Auditoria auditoria, Paginacao paginacao);
    
    List<String> listarUsuariosAuditoria();   
    
    List<String> listarEntidadesAuditoria();
}
