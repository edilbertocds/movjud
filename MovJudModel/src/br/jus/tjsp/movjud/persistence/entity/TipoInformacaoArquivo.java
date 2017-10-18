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
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "TIPO_INF_ARQUIVO")
public class TipoInformacaoArquivo extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -5368243878634012525L;
    
    @Id
    @Column(name = "ID_TIPO_INF_ARQUIVO", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_INF_ARQUIVO")     
    @SequenceGenerator(name = "SEQ_TIPO_INF_ARQUIVO", sequenceName = "SEQ_TIPO_INF_ARQUIVO", allocationSize = 1)
    private Long idTipoInformacaoArquivo;
    
    @Column(name = "DS_TIPO_INFORMACAO_ARQ", nullable = false, length = 100)
    private String descricaoTipoInformacaoArquivo;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    public TipoInformacaoArquivo() {
    }

    public TipoInformacaoArquivo(Long idTipoInformacaoArquivo, String descricaoTipoInformacaoArquivo,
                                 String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idTipoInformacaoArquivo = idTipoInformacaoArquivo;
        this.descricaoTipoInformacaoArquivo = descricaoTipoInformacaoArquivo;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdTipoInformacaoArquivo(Long idTipoInformacaoArquivo) {
        this.idTipoInformacaoArquivo = idTipoInformacaoArquivo;
    }

    public Long getIdTipoInformacaoArquivo() {
        return idTipoInformacaoArquivo;
    }

    public void setDescricaoTipoInformacaoArquivo(String descricaoTipoInformacaoArquivo) {
        this.descricaoTipoInformacaoArquivo = descricaoTipoInformacaoArquivo;
    }

    public String getDescricaoTipoInformacaoArquivo() {
        return descricaoTipoInformacaoArquivo;
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
        return getIdTipoInformacaoArquivo();
    }

    @Override
    public void setId(Long id) {
       setIdTipoInformacaoArquivo(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Tipo Informacao Arquivo = ");
        sb.append(idTipoInformacaoArquivo);
        sb.append("\n");
 
        sb.append("Descricao Tipo Informacao Arquivo = ");
        sb.append(descricaoTipoInformacaoArquivo);
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
