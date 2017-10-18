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
@Audit(dominio = DominioType.CONTROLE_FORMULARIOS)
@Table(name = "MD_TIPO_CAMPO")
public class MetadadosTipoCampo extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 8770880880256066532L;
    
    @Id
    @Column(name = "ID_MD_TIPO_CAMPO", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_TIPO_CAMPO")        
    @SequenceGenerator(name = "SEQ_MD_TIPO_CAMPO", sequenceName = "SEQ_MD_TIPO_CAMPO", allocationSize = 1)    
    private Long idMetadadosTipoCampo;
    
    @Column(name = "DS_TIPO_CAMPO", nullable = false, length = 100)
    private String descricaoTipoCampo;   
    
    @Column(name = "DS_MASCARA", length = 100)
    private String descricaoMascara;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String tipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    public MetadadosTipoCampo() {
    }

    public MetadadosTipoCampo(Long idMetadadosTipoCampo, String descricaoTipoCampo, String descricaoMascara,
                              String tipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idMetadadosTipoCampo = idMetadadosTipoCampo;
        this.descricaoTipoCampo = descricaoTipoCampo;
        this.descricaoMascara = descricaoMascara;
        this.tipoSituacao = tipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdMetadadosTipoCampo(Long idMetadadosTipoCampo) {
        this.idMetadadosTipoCampo = idMetadadosTipoCampo;
    }

    public Long getIdMetadadosTipoCampo() {
        return idMetadadosTipoCampo;
    }

    public void setDescricaoTipoCampo(String descricaoTipoCampo) {
        this.descricaoTipoCampo = descricaoTipoCampo;
    }

    public String getDescricaoTipoCampo() {
        return descricaoTipoCampo;
    }

    public void setDescricaoMascara(String descricaoMascara) {
        this.descricaoMascara = descricaoMascara;
    }

    public String getDescricaoMascara() {
        return descricaoMascara;
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

    @Override
    public Long getId() {
        return getIdMetadadosTipoCampo();
    }

    @Override
    public void setId(Long id) {
        setIdMetadadosTipoCampo(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Metadados Tipo Campo = ");
        sb.append(idMetadadosTipoCampo);
        sb.append("\n");
        
        sb.append("Descricao Tipo Campo = ");
        sb.append(descricaoTipoCampo);
        sb.append("\n");
        
        sb.append("Descricao Mascara = ");
        sb.append(descricaoMascara);
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
