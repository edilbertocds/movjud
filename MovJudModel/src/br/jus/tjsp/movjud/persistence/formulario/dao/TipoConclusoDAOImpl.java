package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;

import br.jus.tjsp.movjud.persistence.entity.TipoConcluso;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class TipoConclusoDAOImpl extends BaseDAOImpl<TipoConcluso> implements TipoConclusoDAO{
    public TipoConclusoDAOImpl() {
        super();
    }

    @Override
    public List<TipoConcluso> listarTipoConclusoPorDescricao() {
        return listarComOrdenacao("descricaoTipoConcluso", true);
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoConcluso filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoConcluso filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<TipoConcluso> getPersistentClass() {
        // TODO Implement this method
        return TipoConcluso.class;
    }
}
