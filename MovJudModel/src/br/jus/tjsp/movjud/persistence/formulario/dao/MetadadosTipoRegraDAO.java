package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.entity.MetadadosTipoRegra;

import java.util.List;

import javax.ejb.Local;

@Local
public interface MetadadosTipoRegraDAO extends BaseDAO<MetadadosTipoRegra>{
    List<MetadadosTipoRegra> listarMetadadosTipoRegraPorNome();
    
    List<MetadadosTipoRegra> listarMetadadosFormularioOrdenadoPorDescricao(MetadadosTipoRegra filter);
}
