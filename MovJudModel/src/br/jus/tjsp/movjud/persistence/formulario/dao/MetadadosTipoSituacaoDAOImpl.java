package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.MetadadosTipoSituacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class MetadadosTipoSituacaoDAOImpl extends BaseDAOImpl<MetadadosTipoSituacao> implements MetadadosTipoSituacaoDAO{
    public MetadadosTipoSituacaoDAOImpl() {
        super();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(MetadadosTipoSituacao filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        parametros.add(new ParametroOperacao("idMetadadosTipoSituacao", filter.getIdMetadadosTipoSituacao(), SQLOperatorType.Equal));
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(MetadadosTipoSituacao filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<MetadadosTipoSituacao> getPersistentClass() {
        return MetadadosTipoSituacao.class;
    }
}
