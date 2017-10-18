package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "CAD_USUARIO_ACAO")
@SequenceGenerator(name = "SEQ_CAD_USUARIO_ACAO", sequenceName = "SEQ_CAD_USUARIO_ACAO", allocationSize = 1)    
public class UsuarioAcao extends BaseEntity<Long> {

    private static final long serialVersionUID = -1823599273902453759L;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_ACAO")
    private Acao acao;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "FK_CAD_USUARIO")
    private Usuario usuario;
    
    @Column(name = "FL_PERMITIDO")
    private String flagPermitido;
   
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    public UsuarioAcao() {
        this.flagTipoSituacao = ConstantesMovjud.FLAG_SITUACAO_ATIVA;
    }

    public UsuarioAcao(Long idUsuarioAcao, String flagPermitido, String flagTipoSituacao, Acao acao, Usuario usuario, 
                       Date dataAtualizacao, Date dataInclusao) {
//        this.idUsuarioAcao = idUsuarioAcao;
        this.flagPermitido = flagPermitido;
        this.flagTipoSituacao = flagTipoSituacao;
        this.acao = acao;
        this.usuario = usuario;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    public void setFlagPermitido(String flagPermitido) {
        this.flagPermitido = flagPermitido;
    }

    public String getFlagPermitido() {
        return flagPermitido;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setTipoAcao(Acao acao) {
        this.acao = acao;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        if (acao != null) {
            sb.append("Acao = ");
            sb.append(acao.getDescricaoAcao());
            sb.append("\n");
        }
        
        if (usuario != null) {
            sb.append("Usuario = ");
            sb.append(usuario.getNome());
            sb.append("\n");
        }
        
        sb.append("Flag Permitido = ");
        sb.append(flagPermitido);
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

    @Override
    public void setId(Long id) {
        // TODO Implement this method
    }

    @Override
    public Long getId() {
        // TODO Implement this method
        return null;
    }
}
