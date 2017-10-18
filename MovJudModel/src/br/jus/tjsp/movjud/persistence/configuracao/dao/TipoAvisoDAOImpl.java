package br.jus.tjsp.movjud.persistence.configuracao.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.entity.TipoAviso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class TipoAvisoDAOImpl extends BaseDAOImpl<TipoAviso> implements TipoAvisoDAO {

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoAviso filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
        
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoAviso filter) {
        return Collections.emptyList();
    }

    @Override
    public Class<TipoAviso> getPersistentClass() {
        return TipoAviso.class;
    }
}
