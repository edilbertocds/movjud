package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.entity.MetadadosGrupoCampo;
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.TipoCompetencia;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class TipoCompetenciaDAOImpl extends BaseDAOImpl<TipoCompetencia> implements TipoCompetenciaDAO{
    public TipoCompetenciaDAOImpl() {
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoCompetencia filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoCompetencia filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<TipoCompetencia> getPersistentClass() {
        return TipoCompetencia.class;
    }

    @Override
    public List<TipoCompetencia> listarTipoCompetenciaOrdenadoPorNome() {
        return listarComOrdenacao("descricaoCompetenciaSaj", true);
    }
}
