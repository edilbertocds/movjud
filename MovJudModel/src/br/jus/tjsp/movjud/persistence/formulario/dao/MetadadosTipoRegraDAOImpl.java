package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.MetadadosTipoRegra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class MetadadosTipoRegraDAOImpl extends BaseDAOImpl<MetadadosTipoRegra> implements MetadadosTipoRegraDAO{
    public MetadadosTipoRegraDAOImpl() {
        super();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(MetadadosTipoRegra filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(MetadadosTipoRegra filter) {
	List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
	parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));
	
	return parametros; 
    }

    @Override
    public Class<MetadadosTipoRegra> getPersistentClass() {
        return MetadadosTipoRegra.class;
    }

    @Override
    public List<MetadadosTipoRegra> listarMetadadosTipoRegraPorNome() {
        return listarComOrdenacao("descricaoNome", true);
    }
    
    @Override 
    public List<MetadadosTipoRegra> listarMetadadosFormularioOrdenadoPorDescricao(MetadadosTipoRegra filter) {
	return listarComFiltroOrdenacao(filter, "descricaoNome", true);
    }
}
