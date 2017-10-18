package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONFIGURACAO)
@Table(name = "TIPO_AVISO")
public class TipoAviso extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 6450624774847458472L;
    
    @Id
    @Column(name = "ID_TIPO_AVISO", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_AVISO")   
    @SequenceGenerator(name = "SEQ_TIPO_AVISO", sequenceName = "SEQ_TIPO_AVISO", allocationSize = 1)
    private Long idTipoAviso;
    
    @Column(name = "DS_TIPO_AVISO", length = 100)
    private String descricaoTipoAviso;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CAD_VARIAVEL_TIPO_AVISO",               
               joinColumns = {@JoinColumn(name = "FK_TIPO_AVISO", referencedColumnName = "ID_TIPO_AVISO")},
               inverseJoinColumns = {@JoinColumn(name = "FK_CAD_VARIAVEL", referencedColumnName = "ID_CAD_VARIAVEL")})
    private List<Variavel> variaveis;

    public TipoAviso() {
    }

    public TipoAviso(Long idTipoAviso, String descricaoTipoAviso, String flagTipoSituacao, Date dataInclusao,
                     Date dataAtualizacao) {
        super();
        this.idTipoAviso = idTipoAviso;
        this.descricaoTipoAviso = descricaoTipoAviso;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdTipoAviso(Long idTipoAviso) {
        this.idTipoAviso = idTipoAviso;
    }

    public Long getIdTipoAviso() {
        return idTipoAviso;
    }

    public void setDescricaoTipoAviso(String descricaoTipoAviso) {
        this.descricaoTipoAviso = descricaoTipoAviso;
    }

    public String getDescricaoTipoAviso() {
        return descricaoTipoAviso;
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
    
    public void setVariaveis(List<Variavel> variaveis) {
        this.variaveis = variaveis;
    }

    public List<Variavel> getVariaveis() {
        return variaveis;
    }

    @Override
    public Long getId() {
        return getIdTipoAviso();
    }

    @Override
    public void setId(Long id) {
        setIdTipoAviso(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Tipo Aviso = ");
        sb.append(idTipoAviso);
        sb.append("\n");
        
        sb.append("Descricao Tipo Aviso = ");
        sb.append(descricaoTipoAviso);
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
