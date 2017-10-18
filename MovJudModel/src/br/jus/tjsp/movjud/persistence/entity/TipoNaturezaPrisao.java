package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "TIPO_NATUREZA_PRISAO")
public class TipoNaturezaPrisao extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -8843288278085992571L;
    
    @Id
    @Column(name = "ID_TIPO_NATUREZA_PRISAO", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_NATUREZA_PRISAO") 
    @SequenceGenerator(name = "SEQ_TIPO_NATUREZA_PRISAO", sequenceName = "SEQ_TIPO_NATUREZA_PRISAO", allocationSize = 1)    
    private Long idTipoNaturezaPrisao;
    
    @Column(name = "DS_TIPO_NATUREZA", length = 100)
    private String descricaoTipoNatureza;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    public TipoNaturezaPrisao() {
    }
    
    public TipoNaturezaPrisao(Long id) {
        this.idTipoNaturezaPrisao = id;
    }
    
    public TipoNaturezaPrisao(String flag) {
        this.flagTipoSituacao = flag;
    }

    public TipoNaturezaPrisao(Long idTipoNaturezaPrisao, String descricaoTipoNatureza, String flagTipoSituacao,
                              Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idTipoNaturezaPrisao = idTipoNaturezaPrisao;
        this.descricaoTipoNatureza = descricaoTipoNatureza;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdTipoNaturezaPrisao(Long idTipoNaturezaPrisao) {
        this.idTipoNaturezaPrisao = idTipoNaturezaPrisao;
    }

    public Long getIdTipoNaturezaPrisao() {
        return idTipoNaturezaPrisao;
    }

    public void setDescricaoTipoNatureza(String descricaoTipoNatureza) {
        this.descricaoTipoNatureza = descricaoTipoNatureza;
    }

    public String getDescricaoTipoNatureza() {
        return descricaoTipoNatureza;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }


    @Override
    public Long getId() {
        return getIdTipoNaturezaPrisao();
    }

    @Override
    public void setId(Long id) {
        setIdTipoNaturezaPrisao(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Tipo Natureza Prisao = ");
        sb.append(idTipoNaturezaPrisao);
        sb.append("\n");

        sb.append("Descricao Tipo Natureza Prisao = ");
        sb.append(descricaoTipoNatureza);
        sb.append("\n");

        sb.append("Flag Tipo Situacao = ");
        sb.append(flagTipoSituacao);
        sb.append("\n");
        
        if (dataAtualizacao != null) {
            sb.append("Data Atualizacao = ");
            sb.append(ModelUtils.formatarDataToStr(dataAtualizacao));
            sb.append("\n");
        }
        
        if (dataInclusao != null) {
            sb.append("Data Inclusao = ");
            sb.append(ModelUtils.formatarDataToStr(dataInclusao));
        }
        
        return sb.toString();
    }
}
