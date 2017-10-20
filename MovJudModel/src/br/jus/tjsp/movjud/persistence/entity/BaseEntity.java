package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.base.dto.BaseObject;

import java.util.Date;


/**
 * Define as classe pai para as entidades.
 *
 * @author cds
 */
public abstract class BaseEntity<T> extends BaseObject<T> {

    public BaseEntity(){
        /*
        <epr> 0.7.7.debug resolver problema de alteração indevida da de dt_inclusao
        setDataInclusao(new Date());
        </epr> resolver problema de alteração indevida da de dt_inclusao
        */
        /*
        <epr 0.7.43+> resolver o problema da alteração sem alteração pela modificação incondicional de dt-atualizaco
        A data de atualização passa a ser atualizada pela classe AuditListener, somente para registros efetivamente modificados.
        setDataAtualizacao(new Date());
        </epr 0.7.43+>
        */
    }

    public abstract void setDataAtualizacao(Date dataAtualizacao);
    public abstract void setDataInclusao(Date dataInclusao);
}
