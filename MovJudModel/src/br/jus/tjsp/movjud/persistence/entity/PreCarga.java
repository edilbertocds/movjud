package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.io.Serializable;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_PRE_CARGA")
public class PreCarga extends BaseEntity<Long> {
    private static final long serialVersionUID = 1904758110612002183L;
    @Id
    @Column(name = "ID_CAD_PRE_CARGA", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAD_PRE_CARGA")
    private Long idPreCarga;
    @Column(name = "NR_ANO", nullable = false)
    private Integer ano;
    @Column(name = "CD_DOMINIO_BI", nullable = false, length = 50)
    private String codigoDominioBi;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO")
    private Date dataInclusao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    @Column(name = "DS_SRC_FORMULARIO", nullable = false, length = 25)
    private String descricaoSrcFormulario;
    @Column(name = "NR_MES", nullable = false)
    private Integer mes;
    @ManyToOne
    @JoinColumn(name = "FK_UNIDADE")
    private Unidade unidade;
    @Column(name = "VL_CAMPO")
    private Long valorCampo;
    @ManyToOne
    @JoinColumn(name = "FK_CAD_USUARIO")
    private Usuario usuario;

    public PreCarga() {
    }

    public PreCarga(Integer ano, String codigoDominioBi, Date dataInclusao, String descricaoSrcFormulario,
                    Long idPreCarga, Integer mes, Unidade unidade, Long valorCampo, Usuario usuario) {
        this.ano = ano;
        this.codigoDominioBi = codigoDominioBi;
        this.dataInclusao = dataInclusao;
        this.descricaoSrcFormulario = descricaoSrcFormulario;
        this.idPreCarga = idPreCarga;
        this.mes = mes;
        this.unidade = unidade;
        this.valorCampo = valorCampo;
        this.usuario = usuario;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCodigoDominioBi() {
        return codigoDominioBi;
    }

    public void setCodigoDominioBi(String codigoDominioBi) {
        this.codigoDominioBi = codigoDominioBi;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getDescricaoSrcFormulario() {
        return descricaoSrcFormulario;
    }

    public void setDescricaoSrcFormulario(String descricaoSrcFormulario) {
        this.descricaoSrcFormulario = descricaoSrcFormulario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Long getIdPreCarga() {
        return idPreCarga;
    }

    public void setIdPreCarga(Long idPreCarga) {
        this.idPreCarga = idPreCarga;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Long getValorCampo() {
        return valorCampo;
    }

    public void setValorCampo(Long valorCampo) {
        this.valorCampo = valorCampo;
    }


    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    @Override
    public void setId(Long id) {
        setIdPreCarga(idPreCarga);
    }

    @Override
    public Long getId() {
        return getIdPreCarga();
    }
}
