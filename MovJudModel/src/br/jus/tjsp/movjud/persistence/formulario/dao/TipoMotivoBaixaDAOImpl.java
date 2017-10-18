package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.entity.TipoMotivoBaixa;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class TipoMotivoBaixaDAOImpl  extends BaseDAOImpl<TipoMotivoBaixa> implements TipoMotivoBaixaDAO{
    
    
    
    public TipoMotivoBaixaDAOImpl() {
        super();
    }

    @Override
    public List<TipoMotivoBaixa> listarTipoMotivoBaixaOrdenadoPorNome() {
        return listarComOrdenacao("descricaoTipoMotivoBaixa", true);
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoMotivoBaixa filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoMotivoBaixa filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<TipoMotivoBaixa> getPersistentClass() {
        return TipoMotivoBaixa.class;
    }
}
