package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Foro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class ForoDAOImpl extends BaseDAOImpl<Foro> implements ForoDAO{
    public ForoDAOImpl() {
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Foro filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("nomeForo", filter.getNomeForo(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));
        if(filter.getTipoEntrancia() != null)
        parametros.add(new ParametroOperacao("nomeEntrancia", filter.getTipoEntrancia().getNomeEntrancia(), SQLOperatorType.LikeFullNoCase));
        if(filter.getComarca() != null){
            parametros.add(new ParametroOperacao("comarca.nomeComarca", filter.getComarca().getNomeComarca(), SQLOperatorType.LikeFullNoCase));
            if(filter.getComarca().getCircunscricao() != null){
                parametros.add(new ParametroOperacao("comarca.circunscricao.nomeCircunscricao", filter.getComarca().getCircunscricao().getNomeCircunscricao(), SQLOperatorType.LikeFullNoCase));
                if(filter.getComarca().getCircunscricao().getRegiao() != null)
                    parametros.add(new ParametroOperacao("comarca.circunscricao.regiao.nomeRegiao", filter.getComarca().getCircunscricao().getRegiao().getNomeRegiao(), SQLOperatorType.LikeFullNoCase));
            }
        }
        if(filter.getIdForo()!=null){
            parametros.add(new ParametroOperacao("idForo", filter.getIdForo(), SQLOperatorType.Equal));
        }
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Foro filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<Foro> getPersistentClass() {
        return Foro.class;
    }

    @Override
    public List<Foro> listarForoOrdenadoPorNome(Foro foro, Paginacao paginacao) {
        return listarComFiltroOrdenacao(foro, "nomeForo", true, paginacao);
    }
}
