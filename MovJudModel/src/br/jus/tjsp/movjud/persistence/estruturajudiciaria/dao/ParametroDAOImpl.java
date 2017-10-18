package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Parametro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class ParametroDAOImpl extends BaseDAOImpl<Parametro> implements ParametroDAO {

    public Class<Parametro> getPersistentClass() {
        return Parametro.class;
    }


    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Parametro filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        filter.setFlagVisivel(ConstantesMovjud.FLAG_SITUACAO_ATIVA);

        parametros.add(new ParametroOperacao("nomeParametro", filter.getNomeParametro(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("flagVisivel", filter.getFlagVisivel(), SQLOperatorType.Equal));
                
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Parametro filter) {
        return Collections.emptyList();
    }
}
