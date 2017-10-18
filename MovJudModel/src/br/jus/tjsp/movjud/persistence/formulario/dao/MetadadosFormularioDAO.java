package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.MetadadosFormulario;

import java.util.List;

import javax.ejb.Local;

@Local
public interface MetadadosFormularioDAO extends BaseDAO<MetadadosFormulario>{
    
    List<MetadadosFormulario> listarMetadadosFormularioOrdenadoPorDescricaoSource(MetadadosFormulario filter, Paginacao paginacao);
    
    List<MetadadosFormulario> listarMetadadosFormularioOrdenadoPorDescricaoNome(MetadadosFormulario filter);
    
    Long countTotalMetadadosFormulariosEmUso();
    
    MetadadosFormulario recuperarMetadadosFormularioEmUso(MetadadosFormulario filter);
}
