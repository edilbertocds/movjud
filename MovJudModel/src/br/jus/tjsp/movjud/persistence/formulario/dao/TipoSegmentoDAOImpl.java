package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.entity.TipoArea;
import br.jus.tjsp.movjud.persistence.entity.TipoCompetencia;

import br.jus.tjsp.movjud.persistence.entity.TipoSegmento;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class TipoSegmentoDAOImpl extends BaseDAOImpl<TipoSegmento> implements TipoSegmentoDAO {
    public TipoSegmentoDAOImpl() {
    }

    @Override
    public List<TipoSegmento> listarTipoSegmenoOrdenadoPorNome() {
        return listarComOrdenacao("descricaoSegmento", true);
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoSegmento filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoSegmento filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<TipoSegmento> getPersistentClass() {
        return TipoSegmento.class;
    }
}
