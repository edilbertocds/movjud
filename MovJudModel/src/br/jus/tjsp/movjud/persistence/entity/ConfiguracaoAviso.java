package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;


import java.util.Date;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONFIGURACAO)
@Table(name = "CAD_CONFIG_AVISO")
public class ConfiguracaoAviso extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -7245615195121200956L;
    
    @Id
    @Column(name = "ID_CAD_CONFIG_AVISO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_CONFIG_AVISO")     
    @SequenceGenerator(name = "SEQ_CAD_CONFIG_AVISO", sequenceName = "SEQ_CAD_CONFIG_AVISO", allocationSize = 1)
    private Long idConfiguracaoAviso;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_AVISO_ESTRUTURA")
    private AvisoEstrutura avisoEstrutura;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_PERIODICIDADE")
    private TipoPeriodicidade tipoPeriodicidade;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_ABRANGENCIA")
    private TipoAbrangenciaAviso tipoAbrangenciaAviso;
    
    @Column(name = "NM_TITULO", length = 100)
    private String nomeTitulo;
    
    @Column(name = "DS_RESUMO")
    private String descricaoResumo;
    
    @Column(name = "VL_PERIODICIDADE", length = 100)
    private String valorPeriodicidade;
    
    @Column(name = "FL_GERAR_HIST_FORM")
    private String flagGerarHistoricoFormulario;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Transient
    private String valorAbrangenciaEnvio;
    
    @ManyToMany
    @JoinTable(name = "CAD_CONFIG_AVISO_USUARIO",
               joinColumns = { @JoinColumn(name = "FK_CAD_CONFIG_AVISO",
                             referencedColumnName = "ID_CAD_CONFIG_AVISO") },
               inverseJoinColumns = { @JoinColumn(name = "FK_CAD_USUARIO", 
                             referencedColumnName = "ID_CAD_USUARIO") })
    private Set<Usuario> usuariosAbrangencia;
    
    @ManyToMany
    @JoinTable(name = "CAD_CONFIG_AVISO_PERFIL",
               joinColumns = { @JoinColumn(name = "FK_CAD_CONFIG_AVISO",
                             referencedColumnName = "ID_CAD_CONFIG_AVISO") },
               inverseJoinColumns = { @JoinColumn(name = "FK_CAD_PERFIL", 
                             referencedColumnName = "ID_CAD_PERFIL") })
    private Set<Perfil> perfisAbrangencia;
    
    public ConfiguracaoAviso() {
    }

    public ConfiguracaoAviso(Long idConfiguracaoAviso, AvisoEstrutura avisoEstrutura,
                             TipoPeriodicidade tipoPeriodicidade, TipoAbrangenciaAviso tipoAbrangenciaAviso,
                             String nomeTitulo, String descricaoResumo, String valorPeriodicidade,
                             String valorAbrangenciaEnvio, String flagGerarHistoricoFormulario, String flagTipoSituacao,
                             Date dataAtualizacao, Date dataInclusao) {
        super();
        this.idConfiguracaoAviso = idConfiguracaoAviso;
        this.avisoEstrutura = avisoEstrutura;
        this.tipoPeriodicidade = tipoPeriodicidade;
        this.tipoAbrangenciaAviso = tipoAbrangenciaAviso;
        this.nomeTitulo = nomeTitulo;
        this.descricaoResumo = descricaoResumo;
        this.valorPeriodicidade = valorPeriodicidade;
        this.flagGerarHistoricoFormulario = flagGerarHistoricoFormulario;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    public void setIdConfiguracaoAviso(Long idConfiguracaoAviso) {
        this.idConfiguracaoAviso = idConfiguracaoAviso;
    }

    public Long getIdConfiguracaoAviso() {
        return idConfiguracaoAviso;
    }

    public void setAvisoEstrutura(AvisoEstrutura avisoEstrutura) {
        this.avisoEstrutura = avisoEstrutura;
    }

    public AvisoEstrutura getAvisoEstrutura() {
        return avisoEstrutura;
    }

    public void setTipoPeriodicidade(TipoPeriodicidade tipoPeriodicidade) {
        this.tipoPeriodicidade = tipoPeriodicidade;
    }

    public TipoPeriodicidade getTipoPeriodicidade() {
        return tipoPeriodicidade;
    }

    public void setTipoAbrangenciaAviso(TipoAbrangenciaAviso tipoAbrangenciaAviso) {
        this.tipoAbrangenciaAviso = tipoAbrangenciaAviso;
    }

    public TipoAbrangenciaAviso getTipoAbrangenciaAviso() {
        return tipoAbrangenciaAviso;
    }

    public void setNomeTitulo(String nomeTitulo) {
        this.nomeTitulo = nomeTitulo;
    }

    public String getNomeTitulo() {
        return nomeTitulo;
    }

    public void setDescricaoResumo(String descricaoResumo) {
        this.descricaoResumo = descricaoResumo;
    }

    public String getDescricaoResumo() {
        return descricaoResumo;
    }

    public void setValorPeriodicidade(String valorPeriodicidade) {
        this.valorPeriodicidade = valorPeriodicidade;
    }

    public String getValorPeriodicidade() {
        return valorPeriodicidade;
    }

    public void setFlagGerarHistoricoFormulario(String flagGerarHistoricoFormulario) {
        this.flagGerarHistoricoFormulario = flagGerarHistoricoFormulario;
    }

    public String getFlagGerarHistoricoFormulario() {
        return flagGerarHistoricoFormulario;
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
    public Long getId() {
        return getIdConfiguracaoAviso();
    }

    @Override
    public void setId(Long id) {
        setIdConfiguracaoAviso(id);
    }
    
    public String getValorAbrangenciaEnvio() {
        StringBuilder valorAbrangenciaEnvioTemp = new StringBuilder();
        if (usuariosAbrangencia != null && !usuariosAbrangencia.isEmpty()) {
            int i=0;
            for (Usuario usuario : usuariosAbrangencia) {
                valorAbrangenciaEnvioTemp.append(usuario.getNome());
                if (i < (usuariosAbrangencia.size() -1)) {
                    valorAbrangenciaEnvioTemp.append(", ");
                }
                i++;
            }
        } else if (perfisAbrangencia != null && !perfisAbrangencia.isEmpty()) {
            int i=0;
            for (Perfil perfil : perfisAbrangencia) {
                valorAbrangenciaEnvioTemp.append(perfil.getNomePerfil());
                if (i < (perfisAbrangencia.size() -1)) {
                    valorAbrangenciaEnvioTemp.append(", ");
                }
                i++;
            }
        } 
        valorAbrangenciaEnvio = valorAbrangenciaEnvioTemp.toString();
        return valorAbrangenciaEnvio;
    }

    public void setUsuariosAbrangencia(Set<Usuario> usuariosAbrangencia) {
        this.usuariosAbrangencia = usuariosAbrangencia;
    }

    public Set<Usuario> getUsuariosAbrangencia() {
        return usuariosAbrangencia;
    }

    public void setPerfisAbrangencia(Set<Perfil> perfisAbrangencia) {
        this.perfisAbrangencia = perfisAbrangencia;
    }

    public Set<Perfil> getPerfisAbrangencia() {
        return perfisAbrangencia;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Configuração Aviso = ");
        sb.append(idConfiguracaoAviso);
        sb.append("\n");
        
        if (avisoEstrutura != null) {
            sb.append("Aviso Estrutura = ");
            sb.append(avisoEstrutura.getNomeAvisoEstrutura());
            sb.append("\n");
        }
        
        if (tipoPeriodicidade != null) {
            sb.append("Tipo Periodicidade = ");
            sb.append(tipoPeriodicidade.getDescricaoPeriodicidade());
            sb.append("\n");
        }    
        
        if (tipoAbrangenciaAviso != null) {
            sb.append("Tipo Abrangencia Aviso = ");
            sb.append(tipoAbrangenciaAviso.getDescricaoAbrangenciaAviso());
            sb.append("\n");
        }
        
        sb.append("Nome Titulo = ");
        sb.append(nomeTitulo);
        sb.append("\n");
        
        sb.append("Descricao Resumo = ");
        sb.append(descricaoResumo);
        sb.append("\n");
        
        sb.append("Valor Periodicidade = ");
        sb.append(valorPeriodicidade);
        sb.append("\n");
        
        sb.append("Flag Gerar Historico Formulario = ");
        sb.append(flagGerarHistoricoFormulario);
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
