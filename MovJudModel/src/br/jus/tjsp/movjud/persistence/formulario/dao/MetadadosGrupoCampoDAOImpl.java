package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.MetadadosGrupoCampo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class MetadadosGrupoCampoDAOImpl extends BaseDAOImpl<MetadadosGrupoCampo> implements MetadadosGrupoCampoDAO{
    public MetadadosGrupoCampoDAOImpl() {
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(MetadadosGrupoCampo filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        if(filter.getMetadadosCampo()!=null)parametros.add(new ParametroOperacao("metadadosCampo.nomeCampo", filter.getMetadadosCampo().getNomeCampo(), SQLOperatorType.LikeFullNoCase));
        
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(MetadadosGrupoCampo filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<MetadadosGrupoCampo> getPersistentClass() {
        return MetadadosGrupoCampo.class;
    }
}
