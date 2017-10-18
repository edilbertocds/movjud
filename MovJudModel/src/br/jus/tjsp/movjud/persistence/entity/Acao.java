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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "TIPO_ACAO")
@SequenceGenerator(name = "SEQ_TIPO_ACAO", sequenceName = "SEQ_TIPO_ACAO", allocationSize = 1)
public class Acao extends BaseEntity<Long> {
    private static final long serialVersionUID = 5465302679858449697L;
    
    @Id
    @Column(name = "ID_TIPO_ACAO", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_ACAO")    
    private Long idAcao;  

    @Column(name = "SRC_ACAO", nullable = false, length = 20)
    private String codigoAcao;    
        
    @Column(name = "NM_ACAO", nullable = false, length = 200)
    private String nomeAcao;   

    @Column(name = "DS_ACAO", length = 100)
    private String descricaoAcao;

    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @ManyToMany(mappedBy = "acoes")
    private List<Perfil> perfis;  

    public Acao() {
    }

    public Acao(Long idAcao, String codigoAcao, String nomeAcao, String descricaoAcao, String flagTipoSituacao, Date dataAtualizacao, Date dataInclusao) {
        this.idAcao = idAcao;
        this.codigoAcao = codigoAcao;
        this.nomeAcao = nomeAcao;
        this.descricaoAcao = descricaoAcao;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }
   
    @Override
    public Long getId() {
        return getIdAcao();
    }

    @Override
    public void setId(Long id) {
        setIdAcao(id);
    }


    public void setIdAcao(Long idAcao) {
        this.idAcao = idAcao;
    }

    public Long getIdAcao() {
        return idAcao;
    }

    public void setCodigoAcao(String codigoAcao) {
        this.codigoAcao = codigoAcao;
    }

    public String getCodigoAcao() {
        return codigoAcao;
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }

    public void setDescricaoAcao(String descricaoAcao) {
        this.descricaoAcao = descricaoAcao;
    }

    public String getDescricaoAcao() {
        return descricaoAcao;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }
    
    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("ID Acao = ");
        sb.append(idAcao);
        sb.append("\n");
        
        sb.append("Codigo Acao = ");
        sb.append(codigoAcao);
        sb.append("\n");
        
        sb.append("Nome Acao = ");
        sb.append(nomeAcao);
        sb.append("\n");
        
        sb.append("Descricao Acao = ");
        sb.append(descricaoAcao);
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
