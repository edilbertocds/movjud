package br.jus.tjsp.movjud.persistence.configuracao.dao;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.AvisoEstrutura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class AvisoEstruturaDAOImpl extends BaseDAOImpl<AvisoEstrutura> implements AvisoEstruturaDAO {

    @Override
    public Class<AvisoEstrutura> getPersistentClass() {
        return AvisoEstrutura.class;
    }
    
    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(AvisoEstrutura filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("nomeAvisoEstrutura", filter.getNomeAvisoEstrutura(), SQLOperatorType.LikeFullNoCase));
        if(filter.getTipoAviso() != null) {
            parametros.add(new ParametroOperacao("tipoAviso.idTipoAviso", filter.getTipoAviso().getIdTipoAviso(), SQLOperatorType.Equal));
        }    
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));
        
        return parametros; 
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(AvisoEstrutura filter) {
        return Collections.emptyList();
    }
    
    @Override
    public List<AvisoEstrutura> listarAvisoEstruturaOrdenadoPorNome(AvisoEstrutura avisoEstrutura, Paginacao paginacao) {
        return listarComFiltroOrdenacao(avisoEstrutura, "nomeAvisoEstrutura", true, paginacao);
    }
    
    @Override
    public List<AvisoEstrutura> listarAvisoEstruturaOrdenadoPorNome() {
        AvisoEstrutura avisoEstrutura = new AvisoEstrutura();
        avisoEstrutura.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        return listarComFiltroOrdenacao(avisoEstrutura, "nomeAvisoEstrutura", true, null);
    }
}
