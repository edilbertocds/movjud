package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.base.dto.BaseObject;
import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "CAD_AUDITORIA")
public class Auditoria extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 5989025198104094621L;
    
    @Id
    @Column(name = "ID_AUDITORIA", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_AUDITORIA")        
    @SequenceGenerator(name = "SEQ_CAD_AUDITORIA", sequenceName = "SEQ_CAD_AUDITORIA", allocationSize = 1)
    private Long idAuditoria;
    
    @Column(name = "NM_DOMINIO", length = 30)
    private String dominio;
    
    @Column(name = "NM_ACAO", nullable = false)
    private String acao;
    
    @Lob
    @Column(name = "DE")
    private String de;
    
    @Lob
    @Column(name = "PARA")
    private String para;
    
    @Column(name = "NM_USUARIO", length = 25)
    private String usuario;
    
    @Column(name = "NM_ENTIDADE", length = 70)
    private String entidade;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Transient
    private transient Date dataFiltroInicio;
    
    @Transient
    private transient Date dataFiltroFim;


    public Auditoria() {
    }

    public Auditoria(Long idAuditoria, String dominio, String acao, String de, String para, String usuario,
                     String entidade, String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idAuditoria = idAuditoria;
        this.dominio = dominio;
        this.acao = acao;
        this.de = de;
        this.para = para;
        this.usuario = usuario;
        this.entidade = entidade;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdAuditoria(Long idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public Long getIdAuditoria() {
        return idAuditoria;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getDominio() {
        return dominio;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getAcao() {
        return acao;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getDe() {
        return de;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getPara() {
        return para;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getEntidade() {
        return entidade;
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
    public Long getId() {
        return getIdAuditoria();
    }

    @Override
    public void setId(Long id) {
        setIdAuditoria(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("ID Auditoria = ");
        sb.append(idAuditoria);
        sb.append("\n");
        
        sb.append("Dominio = ");
        sb.append(dominio);
        sb.append("\n");
        
        sb.append("Acao = ");
        sb.append(acao);
        sb.append("\n");
        
        sb.append("De = ");
        sb.append(de);
        sb.append("\n");
        
        sb.append("Para = ");
        sb.append(para);
        sb.append("\n");
        
        sb.append("Usuario = ");
        sb.append(usuario);
        sb.append("\n");
        
        sb.append("Entidade = ");
        sb.append(entidade);
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

    public void setDataFiltroInicio(Date dataFiltroInicio) {
        this.dataFiltroInicio = dataFiltroInicio;
    }

    public Date getDataFiltroInicio() {
        return dataFiltroInicio;
    }

    public void setDataFiltroFim(Date dataFiltroFim) {
        this.dataFiltroFim = dataFiltroFim;
    }

    public Date getDataFiltroFim() {
        return dataFiltroFim;
    }
}
