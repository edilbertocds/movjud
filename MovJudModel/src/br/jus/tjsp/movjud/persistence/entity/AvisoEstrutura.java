package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONFIGURACAO)
@Table(name = "CAD_AVISO_ESTRUTURA")
public class AvisoEstrutura extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 709958095489655739L;
    
    @Id
    @Column(name = "ID_CAD_AVISO_ESTRUTURA", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_AVISO_ESTRUTURA")  
    @SequenceGenerator(name = "SEQ_CAD_AVISO_ESTRUTURA", sequenceName = "SEQ_CAD_AVISO_ESTRUTURA", allocationSize = 1)
    private Long idAvisoEstrutura;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_AVISO")
    private TipoAviso tipoAviso;
    
    @Column(name = "NM_AVISO_ESTRUTURA", length = 100)
    private String nomeAvisoEstrutura;
    
    @Column(name = "DS_CONTEUDO", length = 4000)
    private String descricaoConteudo;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    public AvisoEstrutura() {
    }

    public AvisoEstrutura(Long idAvisoEstrutura, TipoAviso tipoAviso, String nomeAvisoEstrutura,
                          String descricaoConteudo, String flagTipoSituacao, Date dataAtualizacao, Date dataInclusao) {
        super();
        this.idAvisoEstrutura = idAvisoEstrutura;
        this.tipoAviso = tipoAviso;
        this.nomeAvisoEstrutura = nomeAvisoEstrutura;
        this.descricaoConteudo = descricaoConteudo;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    @Override
    public Long getId() {
        return getIdAvisoEstrutura();
    }

    @Override
    public void setId(Long id) {
        setIdAvisoEstrutura(id);
    }

    public void setIdAvisoEstrutura(Long idAvisoEstrutura) {
        this.idAvisoEstrutura = idAvisoEstrutura;
    }

    public Long getIdAvisoEstrutura() {
        return idAvisoEstrutura;
    }

    public void setTipoAviso(TipoAviso tipoAviso) {
        this.tipoAviso = tipoAviso;
    }

    public TipoAviso getTipoAviso() {
        return tipoAviso;
    }

    public void setNomeAvisoEstrutura(String nomeAvisoEstrutura) {
        this.nomeAvisoEstrutura = nomeAvisoEstrutura;
    }

    public String getNomeAvisoEstrutura() {
        return nomeAvisoEstrutura;
    }

    public void setDescricaoConteudo(String descricaoConteudo) {
        this.descricaoConteudo = descricaoConteudo;
    }

    public String getDescricaoConteudo() {
        return descricaoConteudo;
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Aviso Estrutura = ");
        sb.append(idAvisoEstrutura);
        sb.append("\n");

        if (tipoAviso != null) {
            sb.append("Tipo Aviso = ");
            sb.append(tipoAviso.getDescricaoTipoAviso());
            sb.append("\n");
        }
        
        sb.append("Nome Aviso Estrutura = ");
        sb.append(nomeAvisoEstrutura);
        sb.append("\n");
        
        sb.append("Descricao Conteudo = ");
        sb.append(descricaoConteudo);
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
