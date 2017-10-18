package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "CAD_UNIDADE")
public class Unidade extends BaseEntity<Long> {

    private static final long serialVersionUID = 1246982312664169387L;

    @Id
    @Column(name = "ID_CAD_UNIDADE", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_UNIDADE")
    @SequenceGenerator(name = "SEQ_CAD_UNIDADE", sequenceName = "SEQ_CAD_UNIDADE", allocationSize = 1)
    private Long idUnidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_CAD_FORO", nullable = false)
    private Foro foro;

    @ManyToOne
    @JoinColumn(name = "FK_TIPO_LOCAL", nullable = false)
    private TipoLocal tipoLocal;

    @ManyToOne
    @JoinColumn(name = "FK_CAD_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "NM_UNIDADE", length = 100, nullable = false)
    private String nomeUnidade;

    @Column(name = "CD_CNJ", length = 50)
    private String codigoCnj;

    @Column(name = "SAJPG_CDFORO_ID")
    private Long codigoForoSaj;

    @Column(name = "SAJPG_CDVARA_ID")
    private Long codigoVaraSaj;

    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;

    @Column(name = "DS_TELEFONE_INST")
    private String telefoneInstitucional;

    @Column(name = "NM_EMAIL_INST")
    private String emailInstitucional;
    
    @Column(name = "DS_MOTIVO")
    private String dsMotivo;
    
    @Column(name = "DS_OBSERVACAO")
    private String descricaoObservacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_CRIACAO", nullable = false)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FIM")
    private Date dataFim;
    
    @OneToMany(mappedBy = "unidade", cascade = { CascadeType.ALL },orphanRemoval = true)
    private List<FormularioForoOrigem> formularioForosOrigem;

    @OneToMany(mappedBy = "unidade", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Formulario> formularios;

    @OneToMany(mappedBy = "unidade", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<PermissaoUnidadeTemporaria> permissoesUnidadeTemporaria;

    @OneToMany(mappedBy = "unidade", cascade =  { CascadeType.ALL},orphanRemoval = true)
    private List<FormularioVinculacao> formulariosVinculacao;

    @OneToMany(mappedBy = "unidade", cascade = { CascadeType.ALL},orphanRemoval = true)
    private List<UnidadeEstabelecimentoPrisional> unidadeEstabelecimentosPrisionais;
    
    @OneToMany(mappedBy = "unidade", cascade = { CascadeType.ALL },orphanRemoval = true)
    private List<ReuProvisorio> reusProvisorios;

    @ManyToMany(cascade = { CascadeType.MERGE})
    @JoinTable(name = "CAD_UNIDADE_FORO_RECURSAL", joinColumns = {
               @JoinColumn(name = "FK_CAD_UNIDADE", referencedColumnName = "ID_CAD_UNIDADE")
        }, inverseJoinColumns = { @JoinColumn(name = "FK_CAD_FORO", referencedColumnName = "ID_CAD_FORO") })
    private List<Foro> forosRecursais;

    @ManyToMany(cascade = { CascadeType.MERGE})
    @JoinTable(name = "CAD_UNIDADE_ANEXA", joinColumns = {
               @JoinColumn(name = "FK_CAD_UNIDADE", referencedColumnName = "ID_CAD_UNIDADE")
        }, inverseJoinColumns = { @JoinColumn(name = "FK_CAD_UNIDADE_ANEXA", referencedColumnName = "ID_CAD_UNIDADE") })
    private List<Unidade> unidadesAnexa;
    
    @Transient
    private String formulariosPendentes;

    public Unidade() {
        super();
        formularios = new ArrayList<Formulario>();
        permissoesUnidadeTemporaria = new ArrayList<PermissaoUnidadeTemporaria>();
        formulariosVinculacao = new ArrayList<FormularioVinculacao>();
        unidadeEstabelecimentosPrisionais = new ArrayList<UnidadeEstabelecimentoPrisional>();
        forosRecursais = new ArrayList<Foro>();
        unidadesAnexa = new ArrayList<Unidade>();

    }
    
    public Unidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }
    
    public Unidade(Long idUnidade, String nomeUnidade, Foro foro) {
        this.idUnidade = idUnidade;
        this.nomeUnidade = nomeUnidade;
        this.foro = foro;
    }

    public Unidade(Long idUnidade, Foro foro, TipoLocal tipoLocal, Usuario usuario, String nomeUnidade,
                   String codigoCnj, Long codigoForoSaj, Long codigoVaraSaj, String flagTipoSituacao,
                   String telefoneInstitucional, String emailInstitucional, String dsMotivo, Date dataInclusao, Date dataAtualizacao,
                   Date dataCriacao, Date dataFim) {
        this();
        this.idUnidade = idUnidade;
        this.foro = foro;
        this.tipoLocal = tipoLocal;
        this.usuario = usuario;
        this.nomeUnidade = nomeUnidade;
        this.codigoCnj = codigoCnj;
        this.codigoForoSaj = codigoForoSaj;
        this.codigoVaraSaj = codigoVaraSaj;
        this.flagTipoSituacao = flagTipoSituacao;
        this.telefoneInstitucional = telefoneInstitucional;
        this.emailInstitucional = emailInstitucional;
        this.dsMotivo=dsMotivo;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataCriacao = dataCriacao;
        this.dataFim = dataFim;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }

    public Foro getForo() {
        return foro;
    }

    public void setTipoLocal(TipoLocal tipoLocal) {
        this.tipoLocal = tipoLocal;
    }

    public TipoLocal getTipoLocal() {
        return tipoLocal;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setDescricaoObservacao(String descricaoObservacao) {
        this.descricaoObservacao = descricaoObservacao;
    }

    public String getDescricaoObservacao() {
        return descricaoObservacao;
    }

    public void setFormularioForosOrigem(List<FormularioForoOrigem> formularioForosOrigem) {
        this.formularioForosOrigem = formularioForosOrigem;
    }

    public List<FormularioForoOrigem> getFormularioForosOrigem() {
        if(formularioForosOrigem == null){
            formularioForosOrigem = new ArrayList<FormularioForoOrigem>();
        }
        return formularioForosOrigem;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setCodigoCnj(String codigoCnj) {
        this.codigoCnj = codigoCnj;
    }

    public String getCodigoCnj() {
        return codigoCnj;
    }

    public void setCodigoForoSaj(Long codigoForoSaj) {
        this.codigoForoSaj = codigoForoSaj;
    }

    public Long getCodigoForoSaj() {
        return codigoForoSaj;
    }

    public void setCodigoVaraSaj(Long codigoVaraSaj) {
        this.codigoVaraSaj = codigoVaraSaj;
    }

    public Long getCodigoVaraSaj() {
        return codigoVaraSaj;
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

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setFormularios(List<Formulario> formularios) {
        this.formularios = formularios;
    }

    public List<Formulario> getFormularios() {
        return formularios;
    }

    public void setPermissoesUnidadeTemporaria(List<PermissaoUnidadeTemporaria> permissoesUnidadeTemporaria) {
        this.permissoesUnidadeTemporaria = permissoesUnidadeTemporaria;
    }

    public List<PermissaoUnidadeTemporaria> getPermissoesUnidadeTemporaria() {
        return permissoesUnidadeTemporaria;
    }

    public void setFormulariosVinculacao(List<FormularioVinculacao> formulariosVinculacao) {
        this.formulariosVinculacao = formulariosVinculacao;
    }

    public List<FormularioVinculacao> getFormulariosVinculacao() {
        return formulariosVinculacao;
    }

    public void setEstabelecimentosPrisionais(List<UnidadeEstabelecimentoPrisional> unidadeEstabelecimentosPrisionais) {
        this.unidadeEstabelecimentosPrisionais = unidadeEstabelecimentosPrisionais;
    }

    public List<UnidadeEstabelecimentoPrisional> getUnidadeEstabelecimentosPrisionais() {
        if(unidadeEstabelecimentosPrisionais == null){
            unidadeEstabelecimentosPrisionais= new ArrayList<UnidadeEstabelecimentoPrisional>();
        }
        return unidadeEstabelecimentosPrisionais;
    }

    public void setUnidadesAnexa(List<Unidade> unidadesAnexa) {
        this.unidadesAnexa = unidadesAnexa;
    }

    public List<Unidade> getUnidadesAnexa() {
        if(unidadesAnexa == null){
            unidadesAnexa = new ArrayList<Unidade>();
        }
        return unidadesAnexa;
    }

    public void setForosRecursais(List<Foro> forosRecursais) {
        this.forosRecursais = forosRecursais;
    }

    public List<Foro> getForosRecursais() {
        return forosRecursais;
    }

    public Formulario addFormulario(Formulario formulario) {
        getFormularios().add(formulario);
        formulario.setUnidade(this);
        return formulario;
    }

    public Formulario removeFormulario(Formulario formulario) {
        getFormularios().remove(formulario);
        formulario.setUnidade(null);
        return formulario;
    }

    public PermissaoUnidadeTemporaria addPermissaoUnididadeTemporaria(PermissaoUnidadeTemporaria permissaoUnididadeTemporaria) {
        getPermissoesUnidadeTemporaria().add(permissaoUnididadeTemporaria);
        permissaoUnididadeTemporaria.setUnidade(this);
        return permissaoUnididadeTemporaria;
    }

    public PermissaoUnidadeTemporaria removePermissaoUnididadeTemporaria(PermissaoUnidadeTemporaria permissaoUnididadeTemporaria) {
        getPermissoesUnidadeTemporaria().remove(permissaoUnididadeTemporaria);
        permissaoUnididadeTemporaria.setUnidade(null);
        return permissaoUnididadeTemporaria;
    }

    public FormularioVinculacao addFormularioVinculacao(FormularioVinculacao formularioVinculacao) {
        getFormulariosVinculacao().add(formularioVinculacao);
        formularioVinculacao.setUnidade(this);
        return formularioVinculacao;
    }

    public FormularioVinculacao removeFormularioVinculacao(FormularioVinculacao formularioVinculacao) {
        getFormulariosVinculacao().remove(formularioVinculacao);
        formularioVinculacao.setUnidade(null);
        return formularioVinculacao;
    }

    public UnidadeEstabelecimentoPrisional addEstabelecimentoPrisional(UnidadeEstabelecimentoPrisional unidadeEstabelecimentosPrisionais) {
        getUnidadeEstabelecimentosPrisionais().add(unidadeEstabelecimentosPrisionais);
        unidadeEstabelecimentosPrisionais.setUnidade(this);
        return unidadeEstabelecimentosPrisionais;
    }

    public UnidadeEstabelecimentoPrisional removeEstabelecimentoPrisional(UnidadeEstabelecimentoPrisional unidadeEstabelecimentosPrisionais) {
        getUnidadeEstabelecimentosPrisionais().remove(unidadeEstabelecimentosPrisionais);
        unidadeEstabelecimentosPrisionais.setUnidade(null);
        return unidadeEstabelecimentosPrisionais;
    }

    public Unidade addUnidadeAnexa(Unidade unidadeAnexa) {
        getUnidadesAnexa().add(unidadeAnexa);
        return unidadeAnexa;
    }

    public Unidade removeUnidadeAnexa(Unidade unidadeAnexa) {
        getUnidadesAnexa().remove(unidadeAnexa);
        return unidadeAnexa;
    }


    public void setReusProvisorios(List<ReuProvisorio> reusProvisorios) {
        this.reusProvisorios = reusProvisorios;
    }

    public List<ReuProvisorio> getReusProvisorios() {
        if(reusProvisorios==null){
            reusProvisorios = new ArrayList<ReuProvisorio>();
        }
        return reusProvisorios;
    }

    @Override
    public Long getId() {
        return getIdUnidade();
    }

    @Override
    public void setId(Long id) {
        setIdUnidade(id);
    }

    public void setTelefoneInstitucional(String telefoneInstitucional) {
        this.telefoneInstitucional = telefoneInstitucional;
    }

    public String getTelefoneInstitucional() {
        return telefoneInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setDsMotivo(String dsMotivo) {
        this.dsMotivo = dsMotivo;
    }

    public String getDsMotivo() {
        return dsMotivo;
    }

    public void setFormulariosPendentes(String formulariosPendentes) {
        this.formulariosPendentes = formulariosPendentes;
    }

    public String getFormulariosPendentes() {
        return formulariosPendentes;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID Unidade = ");
        sb.append(idUnidade);
        sb.append("\n");

        sb.append("Nome Unidade = ");
        sb.append(nomeUnidade);
        sb.append("\n");

        if (foro != null) {
            sb.append("Foro = ");
            sb.append(foro.getNomeForo());
            sb.append("\n");
        }

        if (tipoLocal != null) {
            sb.append("Tipo Local = ");
            sb.append(tipoLocal.getNomeLocal());
            sb.append("\n");
        }

        if (usuario != null) {
            sb.append("Usuario = ");
            sb.append(usuario.getNome());
            sb.append("\n");
        }

        sb.append("Codigo CNJ = ");
        sb.append(codigoCnj);
        sb.append("\n");

        sb.append("Codigo Foro SAJ = ");
        sb.append(codigoForoSaj);
        sb.append("\n");

        sb.append("Codigo Vara SAJ = ");
        sb.append(codigoVaraSaj);
        sb.append("\n");

        if (dataCriacao != null) {
            sb.append("Data Criacao = ");
            sb.append(ModelUtils.formatarDataToStr(dataCriacao));
            sb.append("\n");
        }

        if (dataFim != null) {
            sb.append("Data Fim = ");
            sb.append(ModelUtils.formatarDataToStr(dataFim));
            sb.append("\n");
        }

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
