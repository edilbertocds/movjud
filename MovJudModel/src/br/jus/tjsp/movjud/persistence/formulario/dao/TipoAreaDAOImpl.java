package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.entity.TipoArea;
import br.jus.tjsp.movjud.persistence.entity.TipoCompetencia;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class TipoAreaDAOImpl extends BaseDAOImpl<TipoArea> implements TipoAreaDAO{
    public TipoAreaDAOImpl() {
    }

    @Override
    public List<TipoArea> listarTipoAreaOrdenadoPorNome() {
        return listarComOrdenacao("descricaoArea", true);
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoArea filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoArea filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<TipoArea> getPersistentClass() {
        return TipoArea.class;
    }
}
