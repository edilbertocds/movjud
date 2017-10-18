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
@Table(name = "CAD_PERMISSAO_UNID_TEMP")
public class PermissaoUnidadeTemporaria extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 3863942785728164334L;
    
    @Id
    @Column(name = "ID_CAD_PERMISSAO_UNID_TEMP", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_PERMISSAO_UNID_TEMP")      
    @SequenceGenerator(name = "SEQ_CAD_PERMISSAO_UNID_TEMP", sequenceName = "SEQ_CAD_PERMISSAO_UNID_TEMP", allocationSize = 1)    
    private Long idPermissaoUnidadeTemporaria;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_UNIDADE")
    private Unidade unidade;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_USUARIO")
    private Usuario usuario;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INICIO")
    private Date dataInicio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FIM")
    private Date dataValidade;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    public PermissaoUnidadeTemporaria() {
	usuario = new Usuario();
	unidade = new Unidade(); 
	
    }

    public PermissaoUnidadeTemporaria(Long idPermissaoUnidadeTemporaria, Unidade unidade, Usuario usuario,
                                        Date dataValidade, String flagTipoSituacao, Date dataAtualizacao,
                                        Date dataInclusao, Date dataInicio) {
        super();
        this.idPermissaoUnidadeTemporaria = idPermissaoUnidadeTemporaria;
        this.unidade = unidade;
        this.usuario = usuario;
        this.dataValidade = dataValidade;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.dataInicio = dataInicio;
    }

    public void setIdPermissaoUnidadeTemporaria(Long idPermissaoUnidadeTemporaria) {
        this.idPermissaoUnidadeTemporaria = idPermissaoUnidadeTemporaria;
    }

    public Long getIdPermissaoUnidadeTemporaria() {
        return idPermissaoUnidadeTemporaria;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Date getDataValidade() {
        return dataValidade;
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

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    @Override
    public Long getId() {
        return getIdPermissaoUnidadeTemporaria();
    }

    @Override
    public void setId(Long id) {
        setIdPermissaoUnidadeTemporaria(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Permissao Unidade Temporaria = ");
        sb.append(idPermissaoUnidadeTemporaria);
        sb.append("\n");
        
        if (unidade != null) {
            sb.append("Unidade = ");
            sb.append(unidade.getNomeUnidade());
            sb.append("\n");
        }
        
        if (usuario != null) {
            sb.append("Usuario = ");
            sb.append(usuario.getNome());
            sb.append("\n");
        }
        
        if (dataValidade != null) {
            sb.append("Data Validade = ");
            sb.append(ModelUtils.formatarDataToStr(dataValidade));
            sb.append("\n");
        }
        
        sb.append("Tipo Situacao = ");
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
        
        if (dataInicio != null) {
            sb.append("Data Inicio = ");
            sb.append(ModelUtils.formatarDataToStr(dataInicio));
        }
        return sb.toString();
    }
}
