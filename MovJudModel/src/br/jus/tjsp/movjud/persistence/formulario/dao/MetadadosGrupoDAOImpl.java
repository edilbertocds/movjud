package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.MetadadosGrupo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class MetadadosGrupoDAOImpl extends BaseDAOImpl<MetadadosGrupo> implements MetadadosGrupoDAO{
    public MetadadosGrupoDAOImpl() {
        super();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(MetadadosGrupo filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        parametros.add(new ParametroOperacao("codigoDominioBI", filter.getCodigoDominioBI(), SQLOperatorType.EqualNoCase));
        return parametros;     
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(MetadadosGrupo filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<MetadadosGrupo> getPersistentClass() {
        return MetadadosGrupo.class;
    }
}
