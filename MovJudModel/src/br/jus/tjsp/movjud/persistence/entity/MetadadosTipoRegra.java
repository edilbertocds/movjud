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
@Audit(dominio = DominioType.CONTROLE_FORMULARIOS)
@Table(name = "MD_TIPO_REGRA")
public class MetadadosTipoRegra extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 3202752952921799439L;
    
    @Id
    @Column(name = "ID_MD_TIPO_REGRA", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_TIPO_REGRA")        
    @SequenceGenerator(name = "SEQ_MD_TIPO_REGRA", sequenceName = "SEQ_MD_TIPO_REGRA", allocationSize = 1)    
    private Long idMetadadosTipoRegra;
    
    @Column(name = "DS_NOME", nullable = false, length = 40)
    private String descricaoNome;
    
    @Column(name = "DS_TIPO_REGRA", nullable = false, length = 25)
    private String descricaoTipoRegra;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO", nullable = false)
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @OneToMany(mappedBy = "metadadosTipoRegra", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<MetadadosCampo> metadadosCampo;
    
    @OneToMany(mappedBy = "metadadosTipoRegra", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<MetadadosGrupo> metadadosGrupos;

    public MetadadosTipoRegra() {
    }


    public void setIdMetadadosTipoRegra(Long idMetadadosTipoRegra) {
        this.idMetadadosTipoRegra = idMetadadosTipoRegra;
    }

    public Long getIdMetadadosTipoRegra() {
        return idMetadadosTipoRegra;
    }

    public void setDescricaoNome(String descricaoNome) {
        this.descricaoNome = descricaoNome;
    }

    public String getDescricaoNome() {
        return descricaoNome;
    }

    public void setDescricaoTipoRegra(String descricaoTipoRegra) {
        this.descricaoTipoRegra = descricaoTipoRegra;
    }

    public String getDescricaoTipoRegra() {
        return descricaoTipoRegra;
    }
    

    public void setMetadadosGrupos(List<MetadadosGrupo> metadadosGrupos) {
        this.metadadosGrupos = metadadosGrupos;
    }

    public List<MetadadosGrupo> getMetadadosGrupos() {
        return metadadosGrupos;
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

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }


    public void setMetadadosCampo(List<MetadadosCampo> metadadosCampo) {
        this.metadadosCampo = metadadosCampo;
    }

    public List<MetadadosCampo> getMetadadosCampo() {
        return metadadosCampo;
    }

    @Override
    public Long getId() {
        return getIdMetadadosTipoRegra();
    }

    @Override
    public void setId(Long id) {
        setIdMetadadosTipoRegra(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Metadados Tipo Regra = ");
        sb.append(idMetadadosTipoRegra);
        sb.append("\n");
        
        sb.append("Descricao Nome = ");
        sb.append(descricaoNome);
        sb.append("\n");
        
        sb.append("Descricao Tipo Regra = ");
        sb.append(descricaoTipoRegra);
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
