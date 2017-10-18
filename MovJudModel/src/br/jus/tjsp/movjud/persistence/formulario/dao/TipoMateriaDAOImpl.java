package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.entity.TipoMateria;
import br.jus.tjsp.movjud.persistence.entity.TipoSegmento;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class TipoMateriaDAOImpl extends BaseDAOImpl<TipoMateria> implements TipoMateriaDAO{
    
    
    public TipoMateriaDAOImpl() {
        super();
    }

    @Override
    public List<TipoMateria> listarTipoMateriaOrdenadoPorNome() {
        return listarComOrdenacao("descricaoMateria", true);
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoMateria filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoMateria filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<TipoMateria> getPersistentClass() {
        return TipoMateria.class;
    }
}
