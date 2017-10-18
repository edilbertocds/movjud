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
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_FORMULARIO_HIST")
public class FormularioHistorico extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 1323061518446267027L;
    
    @Id
    @Column(name = "ID_CAD_FORMULARIO_HIST", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_FORMULARIO_HIST")  
    @SequenceGenerator(name = "SEQ_CAD_FORMULARIO_HIST", sequenceName = "SEQ_CAD_FORMULARIO_HIST", allocationSize = 1)
    private Long idFormularioHistorico;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_FORMULARIO")
    private Formulario formulario;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_SITUACAO")
    private TipoSituacao tipoSituacao;
    
    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_CRIACAO", nullable = false)
    private Date dataCriacao;
    
    @Column(name = "XML_ANTERIOR")
    private String xmlAnterior;
    
    @Column(name = "XML_NOVO")
    private String xmlNovo;
    
    @Column(name = "DS_COMENTARIO", length = 4000)
    private String descricaoComentario;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    public FormularioHistorico() {
    }

    public FormularioHistorico(Long idFormularioHistorico, Formulario formulario, TipoSituacao tipoSituacao,
                               Usuario usuario, Date dataCriacao, String xmlAnterior, String xmlNovo,
                               String descricaoComentario, String flagTipoSituacao, Date dataAtualizacao,
                               Date dataInclusao) {
        super();
        this.idFormularioHistorico = idFormularioHistorico;
        this.formulario = formulario;
        this.tipoSituacao = tipoSituacao;
        this.usuario = usuario;
        this.dataCriacao = dataCriacao;
        this.xmlAnterior = xmlAnterior;
        this.xmlNovo = xmlNovo;
        this.descricaoComentario = descricaoComentario;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    public void setIdFormularioHistorico(Long idFormularioHistorico) {
        this.idFormularioHistorico = idFormularioHistorico;
    }

    public Long getIdFormularioHistorico() {
        return idFormularioHistorico;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setTipoSituacao(TipoSituacao tipoSituacao) {
        this.tipoSituacao = tipoSituacao;
    }

    public TipoSituacao getTipoSituacao() {
        return tipoSituacao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setXmlAnterior(String xmlAnterior) {
        this.xmlAnterior = xmlAnterior;
    }

    public String getXmlAnterior() {
        return xmlAnterior;
    }

    public void setXmlNovo(String xmlNovo) {
        this.xmlNovo = xmlNovo;
    }

    public String getXmlNovo() {
        return xmlNovo;
    }

    public void setDescricaoComentario(String descricaoComentario) {
        this.descricaoComentario = descricaoComentario;
    }

    public String getDescricaoComentario() {
        return descricaoComentario;
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
        return getIdFormularioHistorico();
    }

    @Override
    public void setId(Long id) {
        setIdFormularioHistorico(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Formulario Historico = ");
        sb.append(idFormularioHistorico);
        sb.append("\n");
        
        if (formulario != null && formulario.getMetadadosFormulario() != null) {
            sb.append("Formulario = ");
            sb.append(formulario.getMetadadosFormulario().getDescricaoNome());
            sb.append("\n");
        }
        
        if (tipoSituacao != null) {
            sb.append("Tipo Situacao = ");
            sb.append(tipoSituacao.getDescricaoSituacao());
            sb.append("\n");
        }
        
        if (usuario != null) {
            sb.append("Usuario = ");
            sb.append(usuario.getNome());
            sb.append("\n");
        }
        
        if (dataCriacao != null) {
            sb.append("Data Criacao = ");
            sb.append(ModelUtils.formatarDataToStr(dataCriacao));
            sb.append("\n");
        }
        
        sb.append("Descricao Comentario = ");
        sb.append(descricaoComentario);
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
