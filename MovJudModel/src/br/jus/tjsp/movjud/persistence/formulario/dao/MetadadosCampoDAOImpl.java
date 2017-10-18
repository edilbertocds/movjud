package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.MetadadosCampo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class MetadadosCampoDAOImpl extends BaseDAOImpl<MetadadosCampo> implements MetadadosCampoDAO{
    public MetadadosCampoDAOImpl() {
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(MetadadosCampo filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        parametros.add(new ParametroOperacao("nomeCampo", filter.getNomeCampo(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("codigoDominioBI", filter.getCodigoDominioBI(), SQLOperatorType.EqualNoCase));
        return parametros;      
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(MetadadosCampo filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<MetadadosCampo> getPersistentClass() {
        return MetadadosCampo.class;
    }

    @Override
    public Long countUtilizacaoCampo(MetadadosCampo metadadosCampo) {      
        Long total = 0L;
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select count(metadadosCampoFilho) from MetadadosCampoCampo metadadosCampoCampo");
        jpaQl.append(" inner join metadadosCampoCampo.metadadosCampoFilho metadadosCampoFilho");
        jpaQl.append(" where metadadosCampoFilho.idMetadadosCampo = ?1");
        Long totalCampoCampo = getPersistenceManager().procurarPorJPQLSingleResult(jpaQl, metadadosCampo.getIdMetadadosCampo());

        StringBuilder jpaQl2 = new StringBuilder();
        jpaQl2.append("select count(metadadosCampo) from MetadadosGrupoCampo metadadosGrupoCampo");
        jpaQl2.append(" inner join metadadosGrupoCampo.metadadosCampo metadadosCampo");
        jpaQl2.append(" where metadadosCampo.idMetadadosCampo = ?1");
        Long totalGrupoCampo = getPersistenceManager().procurarPorJPQLSingleResult(jpaQl2, metadadosCampo.getIdMetadadosCampo());
        
        if(totalCampoCampo != null && totalGrupoCampo != null){
            total = totalCampoCampo+totalGrupoCampo;
        }else if(totalCampoCampo != null){
            total = totalCampoCampo;
        }else if(totalGrupoCampo != null){
            total = totalGrupoCampo;
        }
        
        return total;
    }

    @Override
    public List<MetadadosCampo> recuperarCampoParaReaproveitamento(MetadadosCampo metadadosCampo, Paginacao paginacao,
                                                             List<Long> listaCampos) {
        
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select metadadosCampo from MetadadosCampo metadadosCampo, ");
        jpaQl.append("( select max(metadadosCampo.idMetadadosCampo), metadadosCampo.nomeCampo from MetadadosCampo metadadosCampo");
        jpaQl.append(" where UPPER(metadadosCampo.nomeCampo) like UPPER(?1) group by metadadosCampo.nomeCampo ) metadadosCampo2");
        jpaQl.append(" where metadadosCampo.idMetadadosCampo =  metadadosCampo2.idMetadadosCampo");
        //if(listaCampos!=null)notInList(jpaQl, "metadadosCampo.idMetadadosCampo", listaCampos);
        List<MetadadosCampo> listaCamposRetorno = getPersistenceManager().listarPorJPQL(jpaQl, "%" + metadadosCampo.getNomeCampo() + "%");
        return listaCamposRetorno;
    }
}
