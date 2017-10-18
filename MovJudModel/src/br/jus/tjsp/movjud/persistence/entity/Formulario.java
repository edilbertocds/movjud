package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_FORMULARIO")
public class Formulario extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 4165007055648849182L;
    
    @Id
    @Column(name = "ID_CAD_FORMULARIO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_FORMULARIO")       
    @SequenceGenerator(name = "SEQ_CAD_FORMULARIO", sequenceName = "SEQ_CAD_FORMULARIO", allocationSize = 1)
    private Long idFormulario;
    
    @ManyToOne
    @JoinColumn(name = "FK_UNIDADE")
    private Unidade unidade;
    
    @ManyToOne
    @JoinColumn(name = "FK_USUARIO_APROV")
    private Usuario usuarioAprovacao;
    
    @ManyToOne
    @JoinColumn(name = "FK_USUARIO_PREENC")
    private Usuario usuarioPreenchimento;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_SITUACAO")
    private TipoSituacao tipoSituacao;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_FORMULARIO")
    private MetadadosFormulario metadadosFormulario;
    
    @Column(name = "NR_ANO")
    private Long ano;
    
    @Column(name = "NR_MES")
    private Long mes;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FECHAMENTO")
    private Date dataFechamento;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_APROVACAO")
    private Date dataAprovacao;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @OneToMany(mappedBy = "formulario", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Secao> secoes;    
    
    @OneToMany(mappedBy = "formulario", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<FormularioHistorico> formulariosHistorico;
    

    
    @ManyToMany
    @JoinTable(name = "CAD_FORMULARIO_ARQUIVO",
               joinColumns = { @JoinColumn(name = "FK_CAD_FORMULARIO",
                                           referencedColumnName =
                                           "ID_CAD_FORMULARIO") },
               inverseJoinColumns =
               { @JoinColumn(name = "FK_CAD_ARQ_INTEGRACAO", referencedColumnName =
                             "ID_ARQ_INTEGRACAO_CNJ") })
    private List<ArquivoIntegracaoCnj> arquivosIntegracaoCnj;
    
    @ManyToMany
    @JoinTable(name = "CAD_FORM_TIPO_REGRA",
               joinColumns = { @JoinColumn(name = "FK_CAD_FORMULARIO",
                                           referencedColumnName =
                                           "ID_CAD_FORMULARIO") },
               inverseJoinColumns =
               { @JoinColumn(name = "FK_MD_TIPO_REGRA", referencedColumnName =
                             "ID_MD_TIPO_REGRA") })
    private List<MetadadosTipoRegra> listaMetadadosTipoRegra;

    public Formulario() {
    }
    
    public Formulario(Long id) {
        this.idFormulario = id;
    }
    
    public Formulario(MetadadosFormulario metadadoFormulario, Unidade unidade, Long ano, Long mes) {
        this.metadadosFormulario = metadadoFormulario;
        this.unidade = unidade;
        this.ano = ano;
        this.mes = mes;
    }
    
    public Formulario(MetadadosFormulario metadadoFormulario, Unidade unidade) {
	this.metadadosFormulario = metadadoFormulario;
	this.unidade = unidade;
    }


    public Formulario(Long idFormulario, Unidade unidade, Usuario usuarioAprovacao, Usuario usuarioPreenchimento,
                      TipoSituacao tipoSituacao, MetadadosFormulario metadadosFormulario, Long ano,
                      Long mes, Date dataFechamento, Date dataAprovacao, String flagTipoSituacao, Date dataAtualizacao,
                      Date dataInclusao) {
        super();
        this.idFormulario = idFormulario;
        this.unidade = unidade;
        this.usuarioAprovacao = usuarioAprovacao;
        this.usuarioPreenchimento = usuarioPreenchimento;
        this.tipoSituacao = tipoSituacao;
        this.metadadosFormulario = metadadosFormulario;
        this.ano = ano;
        this.mes = mes;
        this.dataFechamento = dataFechamento;
        this.dataAprovacao = dataAprovacao;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    public void setIdFormulario(Long idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Long getIdFormulario() {
        return idFormulario;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUsuarioAprovacao(Usuario usuarioAprovacao) {
        this.usuarioAprovacao = usuarioAprovacao;
    }

    public Usuario getUsuarioAprovacao() {
        return usuarioAprovacao;
    }

    public void setUsuarioPreenchimento(Usuario usuarioPreenchimento) {
        this.usuarioPreenchimento = usuarioPreenchimento;
    }

    public Usuario getUsuarioPreenchimento() {
        return usuarioPreenchimento;
    }

    public void setTipoSituacao(TipoSituacao tipoSituacao) {
        this.tipoSituacao = tipoSituacao;
    }

    public TipoSituacao getTipoSituacao() {
        return tipoSituacao;
    }

    public void setMetadadosFormulario(MetadadosFormulario metadadosFormulario) {
        this.metadadosFormulario = metadadosFormulario;
    }

    public MetadadosFormulario getMetadadosFormulario() {
        return metadadosFormulario;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    public Long getAno() {
        return ano;
    }

    public void setMes(Long mes) {
        this.mes = mes;
    }

    public Long getMes() {
        return mes;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataAprovacao(Date dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public Date getDataAprovacao() {
        return dataAprovacao;
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

    public void setSecoes(List<Secao> secoes) {
        this.secoes = secoes;
    }

    public List<Secao> getSecoes() {
        if(secoes==null){
            secoes = new ArrayList<Secao>();
        }
        return secoes;
    }

    public void setFormulariosHistorico(List<FormularioHistorico> formulariosHistorico) {
        this.formulariosHistorico = formulariosHistorico;
    }

    public List<FormularioHistorico> getFormulariosHistorico() {
        if(formulariosHistorico==null){
            formulariosHistorico = new ArrayList<FormularioHistorico>();
        }
        return formulariosHistorico;
    }

    public void setArquivosIntegracaoCnj(List<ArquivoIntegracaoCnj> arquivosIntegracaoCnj) {
        this.arquivosIntegracaoCnj = arquivosIntegracaoCnj;
    }

    public List<ArquivoIntegracaoCnj> getArquivosIntegracaoCnj() {
        return arquivosIntegracaoCnj;
    }

    public void setListaMetadadosTipoRegra(List<MetadadosTipoRegra> listaMetadadosTipoRegra) {
        this.listaMetadadosTipoRegra = listaMetadadosTipoRegra;
    }

    public List<MetadadosTipoRegra> getListaMetadadosTipoRegra() {
        return listaMetadadosTipoRegra;
    }

    public Secao addSecao(Secao secao) {
        getSecoes().add(secao);
        secao.setFormulario(this);
        return secao;
    }

    public Secao removeSecao(Secao secao) {
        getSecoes().remove(secao);
        secao.setFormulario(null);
        return secao;
    }

    public FormularioHistorico addFormularioHistorico(FormularioHistorico formularioHistorico) {
        getFormulariosHistorico().add(formularioHistorico);
        formularioHistorico.setFormulario(this);
        return formularioHistorico;
    }

    public FormularioHistorico removeFormularioHistorico(FormularioHistorico formularioHistorico) {
        getFormulariosHistorico().remove(formularioHistorico);
        formularioHistorico.setFormulario(null);
        return formularioHistorico;
    }


    @Override
    public Long getId() {
        return getIdFormulario();
    }

    @Override
    public void setId(Long id) {
        setIdFormulario(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Formulario = ");
        sb.append(idFormulario);
        sb.append("\n");
        
        if (metadadosFormulario != null) {
            sb.append("Nome Formulario = ");
            sb.append(metadadosFormulario.getDescricaoNome());
            sb.append("\n");
        }
        
        if (unidade != null) {
            sb.append("Unidade = ");
            sb.append(unidade.getNomeUnidade());
            sb.append("\n");
        }
        
        if (usuarioAprovacao != null) {
            sb.append("Usuario Aprovacao = ");
            sb.append(usuarioAprovacao.getNome());
            sb.append("\n");
        }
        
        if (usuarioPreenchimento != null) {
            sb.append("Usuario Preenchimento = ");
            sb.append(usuarioPreenchimento.getNome());
            sb.append("\n");
        }
        
        if (tipoSituacao != null) {
            sb.append("Tipo Situacao = ");
            sb.append(tipoSituacao.getTipoSituacao());
            sb.append("\n");
        }
        
        sb.append("Ano = ");
        sb.append(ano);
        sb.append("\n");
        
        sb.append("Mes = ");
        sb.append(mes);
        sb.append("\n");
 
        if (dataFechamento != null) {
            sb.append("Data Fechamento = ");
            sb.append(ModelUtils.formatarDataToStr(dataFechamento));
            sb.append("\n");
        }
        
        if (dataAprovacao != null) {
            sb.append("Data Aprovacao = ");
            sb.append(ModelUtils.formatarDataToStr(dataAprovacao));
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
