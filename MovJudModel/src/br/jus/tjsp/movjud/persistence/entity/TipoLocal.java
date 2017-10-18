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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "TIPO_LOCAL")
public class TipoLocal extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -2244171462890535489L;
    
    @Id
    @Column(name = "ID_TIPO_LOCAL", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_LOCAL")   
    @SequenceGenerator(name = "SEQ_TIPO_LOCAL", sequenceName = "SEQ_TIPO_LOCAL", allocationSize = 1)    
    private Long idTipoLocal;
    
    @Column(name = "NM_LOCAL", length = 100)
    private String nomeLocal;
    
    @Column(name = "FL_UNIDADE_PRISIONAL", nullable = false)
    private String flagUnidadePrisional;
    
    @Column(name = "FL_COLEGIO_RECURSAL", nullable = false)
    private String flagColegioRecursal;
    
    @Column(name = "FL_ANEXO", nullable = false)
    private String flagAnexo;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    public TipoLocal() {
    }

    public TipoLocal(Long idTipoLocal, String nomeLocal, String flagUnidadePrisional, String flagColegioRecursal,
                     String flagAnexo, String flagTipoSituacao, Date dataAtualizacao, Date dataInclusao) {
        super();
        this.idTipoLocal = idTipoLocal;
        this.nomeLocal = nomeLocal;
        this.flagUnidadePrisional = flagUnidadePrisional;
        this.flagColegioRecursal = flagColegioRecursal;
        this.flagAnexo = flagAnexo;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    public void setIdTipoLocal(Long idTipoLocal) {
        this.idTipoLocal = idTipoLocal;
    }

    public Long getIdTipoLocal() {
        return idTipoLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setFlagUnidadePrisional(String flagUnidadePrisional) {
        this.flagUnidadePrisional = flagUnidadePrisional;
    }

    public String getFlagUnidadePrisional() {
        return flagUnidadePrisional;
    }

    public void setFlagColegioRecursal(String flagColegioRecursal) {
        this.flagColegioRecursal = flagColegioRecursal;
    }

    public String getFlagColegioRecursal() {
        return flagColegioRecursal;
    }

    public void setFlagAnexo(String flagAnexo) {
        this.flagAnexo = flagAnexo;
    }

    public String getFlagAnexo() {
        return flagAnexo;
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
        return getIdTipoLocal();
    }

    @Override
    public void setId(Long id) {
        setIdTipoLocal(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Tipo Local = ");
        sb.append(idTipoLocal);
        sb.append("\n");
        
        sb.append("Nome Local = ");
        sb.append(nomeLocal);
        sb.append("\n");
 
        sb.append("Flag Unidade Prisional = ");
        sb.append(flagUnidadePrisional);
        sb.append("\n");
        
        sb.append("Flag Colegio Recursal = ");
        sb.append(flagColegioRecursal);
        sb.append("\n");
        
        sb.append("Flag Anexo = ");
        sb.append(flagAnexo);
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
