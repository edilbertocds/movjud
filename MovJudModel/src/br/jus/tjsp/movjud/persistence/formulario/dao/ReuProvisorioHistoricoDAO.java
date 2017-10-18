package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorioHistorico;

import javax.ejb.Local;


@Local
public interface ReuProvisorioHistoricoDAO extends BaseDAO<ReuProvisorioHistorico>{
    
    Long countReusHistoricoMesesAnterior(ReuProvisorioHistorico reuHist);
    
    void deletarHistoricoReu(ReuProvisorioHistorico reuHistorico);
}
