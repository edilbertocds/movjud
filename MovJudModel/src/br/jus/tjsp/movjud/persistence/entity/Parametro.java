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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONFIGURACAO)
@Table(name = "CAD_PARAMETRO")
@SequenceGenerator(name = "SEQ_CAD_PARAMETRO", sequenceName = "SEQ_CAD_PARAMETRO", allocationSize = 1)
public class Parametro extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -5113611174534616650L;
    
    @Id
    @Column(name = "ID_CAD_PARAMETRO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAD_PARAMETRO")
    private Long idParametro;
    
    @Column(name = "NM_PARAMETRO", nullable = false, length = 100)
    private String nomeParametro;
    
    @Column(name = "VL_PARAMETRO")
    private String valorParametro;
    
    @Column(name = "DS_PARAMETRO", nullable = false, length = 100)
    private String descricaoParametro;
    
    @Column(name = "FL_VISIVEL", nullable = false)
    private String flagVisivel;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    public Parametro() {
    }

    public Parametro(Long idParametro, String nomeParametro, String valorParametro, String descricaoParametro,
                     String flagVisivel, String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idParametro = idParametro;
        this.nomeParametro = nomeParametro;
        this.valorParametro = valorParametro;
        this.descricaoParametro = descricaoParametro;
        this.flagVisivel = flagVisivel;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setNomeParametro(String nomeParametro) {
        this.nomeParametro = nomeParametro;
    }

    public String getNomeParametro() {
        return nomeParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setDescricaoParametro(String descricaoParametro) {
        this.descricaoParametro = descricaoParametro;
    }

    public String getDescricaoParametro() {
        return descricaoParametro;
    }

    public void setFlagVisivel(String flagVisivel) {
        this.flagVisivel = flagVisivel;
    }

    public String getFlagVisivel() {
        return flagVisivel;
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
        return getIdParametro();
    }

    @Override
    public void setId(Long id) {
        setIdParametro(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Parametro = ");
        sb.append(idParametro);
        sb.append("\n");
        
        sb.append("Nome Parametro = ");
        sb.append(nomeParametro);
        sb.append("\n");
        
        sb.append("Valor Parametro = ");
        sb.append(valorParametro);
        sb.append("\n");
        
        sb.append("Descricao Parametro = ");
        sb.append(descricaoParametro);
        sb.append("\n");

        sb.append("Flag Visivel = ");
        sb.append(flagVisivel);
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
