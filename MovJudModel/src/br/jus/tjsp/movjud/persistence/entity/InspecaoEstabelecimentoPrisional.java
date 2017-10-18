package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.persistence.base.annotation.Audit;

import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@NamedQueries({
              @NamedQuery(name = "InspecaoEstabelecimentoPrisional.findAll",
                          query = "select o from InspecaoEstabelecimentoPrisional o") })
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "CAD_INSPECAO_ESTAB")
public class InspecaoEstabelecimentoPrisional extends BaseEntity<Long> {
    @Id
    @Column(name = "ID_CAD_INSPECAO_ESTAB", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_INSPECAO_ESTAB")  
    @SequenceGenerator(name = "SEQ_CAD_INSPECAO_ESTAB", sequenceName = "SEQ_CAD_INSPECAO_ESTAB", allocationSize = 1)
    private Long idInspecaoEstabelecimento;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INSPECAO")
    private Date dataInspecao;
    
    @Column(name = "DS_NAO_INSPECAO", length = 4000)
    private String descricaoNaoInspecao;
    
    @Column(name = "FL_NAO_INSPECAO_REALIZADA")
    private String flagInspecaoNaoRealizada;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_ESTAB_PRISIONAL", nullable = false)
    private EstabelecimentoPrisional estabelecimentoPrisional;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_SECAO")
    private Secao secao;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_USUARIO", nullable = false)
    private Usuario usuario;

    public InspecaoEstabelecimentoPrisional() {
    }

    public InspecaoEstabelecimentoPrisional(Date dataAtualizacao, Date dataInclusao, Date dataInspecao,
                                            String descricaoNaoInspecao, String flagInspecaoNaoRealizada,
                                            String flagTipoSituacao, EstabelecimentoPrisional estabelecimentoPrisional,
                                            Long idInspecaoEstabelecimento, Secao secao, Usuario usuario) {
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.dataInspecao = dataInspecao;
        this.descricaoNaoInspecao = descricaoNaoInspecao;
        this.flagInspecaoNaoRealizada = flagInspecaoNaoRealizada;
        this.flagTipoSituacao = flagTipoSituacao;
        this.estabelecimentoPrisional = estabelecimentoPrisional;
        this.idInspecaoEstabelecimento = idInspecaoEstabelecimento;
        this.secao = secao;
        this.usuario = usuario;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataInspecao() {
        return dataInspecao;
    }

    public void setDataInspecao(Date dataInspecao) {
        this.dataInspecao = dataInspecao;
    }

    public String getDescricaoNaoInspecao() {
        return descricaoNaoInspecao;
    }

    public void setDescricaoNaoInspecao(String descricaoNaoInspecao) {
        this.descricaoNaoInspecao = descricaoNaoInspecao;
    }

    public String getFlagInspecaoNaoRealizada() {
        return flagInspecaoNaoRealizada;
    }

    public void setFlagInspecaoNaoRealizada(String flagInspecaoNaoRealizada) {
        this.flagInspecaoNaoRealizada = flagInspecaoNaoRealizada;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }


    public Long getIdInspecaoEstabelecimento() {
        return idInspecaoEstabelecimento;
    }

    public void setIdInspecaoEstabelecimento(Long idInspecaoEstabelecimento) {
        this.idInspecaoEstabelecimento = idInspecaoEstabelecimento;
    }

    public void setEstabelecimentoPrisional(EstabelecimentoPrisional estabelecimentoPrisional) {
        this.estabelecimentoPrisional = estabelecimentoPrisional;
    }

    public EstabelecimentoPrisional getEstabelecimentoPrisional() {
        return estabelecimentoPrisional;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }


    @Override
    public void setId(Long id) {
         setIdInspecaoEstabelecimento(id);
    }

    @Override
    public Long getId() {
        return getIdInspecaoEstabelecimento();
    }
}
