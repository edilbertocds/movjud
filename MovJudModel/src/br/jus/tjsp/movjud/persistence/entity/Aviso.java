package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.DateUtils;
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
@Table(name = "CAD_AVISO")
public class Aviso extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -2742629723401103841L;
    
    @Id
    @Column(name = "ID_CAD_AVISO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_AVISO")    
    @SequenceGenerator(name = "SEQ_CAD_AVISO", sequenceName = "SEQ_CAD_AVISO", allocationSize = 1)
    private Long idAviso;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_CONFIG_AVISO")
    private ConfiguracaoAviso configuracaoAviso;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_FORMULARIO")
    private Formulario formulario;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_USUARIO")
    private Usuario usuario;
    
    @Column(name = "VL_VARIAVEIS")
    private String valorVariaveis;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ENVIO")
    private Date dataEnvio;
    
    @Column(name = "FL_LIDO")
    private String flagLido;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_LIDO")
    private Date dataLido;
    
    @Column(name = "FL_EXCLUIDO")
    private String flagExcluido;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_EXCLUIDO")
    private Date dataExcluido;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    public Aviso() {
    }

    public Aviso(Long idAviso, ConfiguracaoAviso configuracaoAviso, Formulario formulario, Usuario usuario,
                 String valorVariaveis, Date dataEnvio, String flagLido, Date dataLido, String flagExcluido,
                 Date dataExcluido, String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idAviso = idAviso;
        this.configuracaoAviso = configuracaoAviso;
        this.formulario = formulario;
        this.usuario = usuario;
        this.valorVariaveis = valorVariaveis;
        this.dataEnvio = dataEnvio;
        this.flagLido = flagLido;
        this.dataLido = dataLido;
        this.flagExcluido = flagExcluido;
        this.dataExcluido = dataExcluido;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public Long getId() {
        return getIdAviso();
    }

    @Override
    public void setId(Long id) {
        setIdAviso(id);
    }

    public void setIdAviso(Long idAviso) {
        this.idAviso = idAviso;
    }

    public Long getIdAviso() {
        return idAviso;
    }

    public void setConfiguracaoAviso(ConfiguracaoAviso configuracaoAviso) {
        this.configuracaoAviso = configuracaoAviso;
    }

    public ConfiguracaoAviso getConfiguracaoAviso() {
        return configuracaoAviso;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setValorVariaveis(String valorVariaveis) {
        this.valorVariaveis = valorVariaveis;
    }

    public String getValorVariaveis() {
        return valorVariaveis;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setFlagLido(String flagLido) {
        this.flagLido = flagLido;
    }

    public String getFlagLido() {
        return flagLido;
    }

    public void setDataLido(Date dataLido) {
        this.dataLido = dataLido;
    }

    public Date getDataLido() {
        return dataLido;
    }

    public void setFlagExcluido(String flagExcluido) {
        this.flagExcluido = flagExcluido;
    }

    public String getFlagExcluido() {
        return flagExcluido;
    }

    public void setDataExcluido(Date dataExcluido) {
        this.dataExcluido = dataExcluido;
    }

    public Date getDataExcluido() {
        return dataExcluido;
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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Aviso)) {
            return false;
        }
        final Aviso other = (Aviso) object;
        if (!(configuracaoAviso == null ? other.configuracaoAviso == null :
              configuracaoAviso.equals(other.configuracaoAviso))) {
            return false;
        }
        if (!(usuario == null ? other.usuario == null : usuario.equals(other.usuario))) {
            return false;
        }
        if (!(dataEnvio == null ? other.dataEnvio == null : equals(dataEnvio,other.dataEnvio))) {
            return false;
        }
        return true;
    }

    private boolean equals(Date dataEnvioA, Date dataEnvioB) {
        boolean status = false;	
	if(dataEnvioA != null && dataEnvioB != null) {
	    Date dateEnvioATrunc = DateUtils.removerTime(dataEnvioA);
	    Date dateEnvioBTrunc = DateUtils.removerTime(dataEnvioB);    
	    
	    status = dateEnvioATrunc.equals(dateEnvioBTrunc);
	}
	return status;	
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((configuracaoAviso == null) ? 0 : configuracaoAviso.hashCode());
        result = PRIME * result + ((usuario == null) ? 0 : usuario.hashCode());
        result = PRIME * result + ((dataEnvio == null) ? 0 : dataEnvio.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("ID Aviso = ");
        sb.append(idAviso);
        sb.append("\n");
        
        if (configuracaoAviso != null) {
            sb.append("Configuracao = ");
            sb.append(configuracaoAviso.getNomeTitulo());
            sb.append("\n");
        }
        
        if (usuario != null) {
            sb.append("Usuario = ");
            sb.append(usuario.getNome());
            sb.append("\n");
        }
        
        sb.append("Valor Variáveis = ");
        sb.append(valorVariaveis);
        sb.append("\n");
        
        if (dataEnvio != null) {
            sb.append("Data Envio = ");
            sb.append(ModelUtils.formatarDataToStr(dataEnvio));
            sb.append("\n");
        }
        
        sb.append("Flag Lido = ");
        sb.append(flagLido);
        sb.append("\n");
        
        if (dataLido != null) {
            sb.append("Data Lido = ");
            sb.append(ModelUtils.formatarDataToStr(dataLido));
            sb.append("\n");
        }    
        
        sb.append("Flag Excluido = ");
        sb.append(flagExcluido);
        sb.append("\n");
        
        if (dataExcluido != null) {
            sb.append("Data Excluido = ");
            sb.append(ModelUtils.formatarDataToStr(dataExcluido));
            sb.append("\n");
        }
        
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
