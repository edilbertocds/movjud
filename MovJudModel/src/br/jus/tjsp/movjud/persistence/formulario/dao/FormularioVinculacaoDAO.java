package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.entity.FormularioVinculacao;
import br.jus.tjsp.movjud.persistence.entity.TipoSegmento;

import br.jus.tjsp.movjud.persistence.entity.Unidade;

import java.util.List;

import javax.ejb.Local;

@Local
public interface FormularioVinculacaoDAO extends BaseDAO<FormularioVinculacao> {
    
    Long countTotalFormulariosVinculados();
    
    Long countTotalUnidadesVinculadas();
    
}
