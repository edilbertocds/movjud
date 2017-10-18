package br.jus.tjsp.movjud.persistence.configuracao.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.entity.TipoAbrangenciaAviso;
import br.jus.tjsp.movjud.persistence.entity.TipoAviso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class TipoAbrangenciaAvisoDAOImpl extends BaseDAOImpl<TipoAbrangenciaAviso> implements TipoAbrangenciaAvisoDAO {

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoAbrangenciaAviso filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
        
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoAbrangenciaAviso filter) {
        return Collections.emptyList();
    }

    @Override
    public Class<TipoAbrangenciaAviso> getPersistentClass() {
        return TipoAbrangenciaAviso.class;
    }
}
