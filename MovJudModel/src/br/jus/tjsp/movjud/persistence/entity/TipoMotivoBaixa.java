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
@Table(name = "TIPO_MOTIVO_BAIXA")
public class TipoMotivoBaixa extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 7553440069259965369L;
    
    @Id
    @Column(name = "ID_TIPO_MOTIVO_BAIXA", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_MOTIVO_BAIXA")    
    @SequenceGenerator(name = "SEQ_TIPO_MOTIVO_BAIXA", sequenceName = "SEQ_TIPO_MOTIVO_BAIXA", allocationSize = 1)
    private Long idTipoMotivoBaixa;
    
    @Column(name = "DS_TIPO_MOTIVO_BAIXA", nullable = false, length = 100)
    private String descricaoTipoMotivoBaixa;
    
    @Column(name = "NM_FLUXO", length = 40)
    private String nomeFluxo;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String tipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @OneToMany(mappedBy = "tipoMotivoBaixa", cascade = { CascadeType.PERSIST, CascadeType.MERGE } ) 
    private List<ReuProvisorio> reusProvisorios;

    public TipoMotivoBaixa() {
    }
    
    public TipoMotivoBaixa(Long id) {
        this.idTipoMotivoBaixa = id;
    }

    public TipoMotivoBaixa(Long idTipoMotivoBaixa, String descricaoTipoMotivoBaixa, String nomeFluxo,
                           String tipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idTipoMotivoBaixa = idTipoMotivoBaixa;
        this.descricaoTipoMotivoBaixa = descricaoTipoMotivoBaixa;
        this.nomeFluxo = nomeFluxo;
        this.tipoSituacao = tipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdTipoMotivoBaixa(Long idTipoMotivoBaixa) {
        this.idTipoMotivoBaixa = idTipoMotivoBaixa;
    }

    public Long getIdTipoMotivoBaixa() {
        return idTipoMotivoBaixa;
    }

    public void setDescricaoTipoMotivoBaixa(String descricaoTipoMotivoBaixa) {
        this.descricaoTipoMotivoBaixa = descricaoTipoMotivoBaixa;
    }

    public String getDescricaoTipoMotivoBaixa() {
        return descricaoTipoMotivoBaixa;
    }

    public void setNomeFluxo(String nomeFluxo) {
        this.nomeFluxo = nomeFluxo;
    }

    public String getNomeFluxo() {
        return nomeFluxo;
    }

    public void setTipoSituacao(String tipoSituacao) {
        this.tipoSituacao = tipoSituacao;
    }

    public String getTipoSituacao() {
        return tipoSituacao;
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

    public void setReusProvisorios(List<ReuProvisorio> reusProvisorios) {
        this.reusProvisorios = reusProvisorios;
    }

    public List<ReuProvisorio> getReusProvisorios() {
        return reusProvisorios;
    }

    @Override
    public Long getId() {
        return getIdTipoMotivoBaixa();
    }

    @Override
    public void setId(Long id) {
        setIdTipoMotivoBaixa(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Tipo Motivo Baixa = ");
        sb.append(idTipoMotivoBaixa);
        sb.append("\n");
   
        sb.append("Descricao Tipo Motivo Baixa = ");
        sb.append(descricaoTipoMotivoBaixa);
        sb.append("\n");
        
        sb.append("Nome Fluxo = ");
        sb.append(nomeFluxo);
        sb.append("\n");
   
        sb.append("Flag Tipo Situacao = ");
        sb.append(tipoSituacao);
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
