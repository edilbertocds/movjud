package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.entity.ReuProvisorio;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ReuProvisorioDAO extends BaseDAO<ReuProvisorio>{
    
    List<ReuProvisorio> listarReusProvisoriosUnidade(ReuProvisorio reuProvisorio, Integer ano, Integer mes);
    
    void deletarReuProvisorio(ReuProvisorio reuProvisorio);
}
