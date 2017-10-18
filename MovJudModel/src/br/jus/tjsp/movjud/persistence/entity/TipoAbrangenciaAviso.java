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
@Audit(dominio = DominioType.CONFIGURACAO)
@Table(name = "TIPO_ABRANGENCIA_AVISO")
public class TipoAbrangenciaAviso extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 618455674293693543L;
    
    @Id
    @Column(name = "ID_TIPO_ABRANGENCIA_AVISO", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_ABRANGENCIA_AVISO")       
    @SequenceGenerator(name = "SEQ_TIPO_ABRANGENCIA_AVISO", sequenceName = "SEQ_TIPO_ABRANGENCIA_AVISO", allocationSize = 1)
    private Long idTipoAbrangenciaAviso;
    
    @Column(name = "CD_ABRANGENCIA_AVISO", length = 20)
    private String codigoAbrangenciaAviso;
    
    @Column(name = "DS_ABRANGENCIA_AVISO", length = 100)
    private String descricaoAbrangenciaAviso;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @OneToMany(mappedBy = "tipoAbrangenciaAviso", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<ConfiguracaoAviso> configuracoesAviso;

    public TipoAbrangenciaAviso() {
    }

    public TipoAbrangenciaAviso(Long idTipoAbrangenciaAviso, String descricaoAbrangenciaAviso,
                                String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        this.idTipoAbrangenciaAviso = idTipoAbrangenciaAviso;
        this.descricaoAbrangenciaAviso = descricaoAbrangenciaAviso;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdTipoAbrangenciaAviso(Long idTipoAbrangenciaAviso) {
        this.idTipoAbrangenciaAviso = idTipoAbrangenciaAviso;
    }

    public Long getIdTipoAbrangenciaAviso() {
        return idTipoAbrangenciaAviso;
    }

    public void setCodigoAbrangenciaAviso(String codigoAbrangenciaAviso) {
        this.codigoAbrangenciaAviso = codigoAbrangenciaAviso;
    }

    public String getCodigoAbrangenciaAviso() {
        return codigoAbrangenciaAviso;
    }

    public void setDescricaoAbrangenciaAviso(String descricaoAbrangenciaAviso) {
        this.descricaoAbrangenciaAviso = descricaoAbrangenciaAviso;
    }

    public String getDescricaoAbrangenciaAviso() {
        return descricaoAbrangenciaAviso;
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

    public void setConfiguracoesAviso(List<ConfiguracaoAviso> configuracoesAviso) {
        this.configuracoesAviso = configuracoesAviso;
    }

    public List<ConfiguracaoAviso> getConfiguracoesAviso() {
        return configuracoesAviso;
    }

    public ConfiguracaoAviso addConfiguracaoAviso(ConfiguracaoAviso configuracaoAviso) {
        getConfiguracoesAviso().add(configuracaoAviso);
        configuracaoAviso.setTipoAbrangenciaAviso(this);
        return configuracaoAviso;
    }

    public ConfiguracaoAviso removeConfiguracaoAviso(ConfiguracaoAviso configuracaoAviso) {
        getConfiguracoesAviso().remove(configuracaoAviso);
        configuracaoAviso.setTipoAbrangenciaAviso(null);
        return configuracaoAviso;
    }
    
    @Override
    public Long getId() {
        return getIdTipoAbrangenciaAviso();
    }

    @Override
    public void setId(Long id) {
        setIdTipoAbrangenciaAviso(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Tipo Abrangencia Aviso = ");
        sb.append(idTipoAbrangenciaAviso);
        sb.append("\n");
 
        sb.append("Código Abrangencia Aviso = ");
        sb.append(codigoAbrangenciaAviso);
        sb.append("\n");
 
        sb.append("Descricao Abrangencia Aviso = ");
        sb.append(descricaoAbrangenciaAviso);
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
