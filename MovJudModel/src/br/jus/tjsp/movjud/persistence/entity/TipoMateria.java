package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@NamedQueries({ @NamedQuery(name = "TipoMateria.findAll", query = "select o from TipoMateria o") })
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "TIPO_MATERIA")
public class TipoMateria extends BaseEntity<Long> {
    private static final long serialVersionUID = -8126130298074466983L;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    @Column(name = "DS_MATERIA", length = 100)
    private String descricaoMateria;
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    @Id
    @Column(name = "ID_TIPO_MATERIA", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_MATERIA")    
    @SequenceGenerator(name = "SEQ_TIPO_MATERIA", sequenceName = "SEQ_TIPO_MATERIA", allocationSize = 1)
    private Long idTipoMateria;

    public TipoMateria() {
    }

    public TipoMateria(Date dataAtualizacao, Date dataInclusao, String descricaoMateria, String flagTipoSituacao,
                       Long idTipoMateria) {
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.descricaoMateria = descricaoMateria;
        this.flagTipoSituacao = flagTipoSituacao;
        this.idTipoMateria = idTipoMateria;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        if(dataInclusao==null){
            dataInclusao = new Date();
        }
        this.dataInclusao = dataInclusao;
    }

    public String getDescricaoMateria() {
        return descricaoMateria;
    }

    public void setDescricaoMateria(String descricaoMateria) {
        this.descricaoMateria = descricaoMateria;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public Long getIdTipoMateria() {
        return idTipoMateria;
    }

    public void setIdTipoMateria(Long idTipoMateria) {
        this.idTipoMateria = idTipoMateria;
    }

    @Override
    public void setId(Long id) {
        setIdTipoMateria(id);
    }

    @Override
    public Long getId() {
        return getIdTipoMateria();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Tipo Matéria = ");
        sb.append(idTipoMateria);
        sb.append("\n");
        
        sb.append("Descrição Matéria = ");
        sb.append(descricaoMateria);
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
