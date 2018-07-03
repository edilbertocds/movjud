package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SubSecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoMateriaDTO;
import br.jus.tjsp.movjud.business.formulario.types.SecaoType;
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

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONTROLE_FORMULARIOS)
@Table(name = "MD_SECAO")
public class MetadadosSecao extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -58713052914718143L;
    
    @Id
    @Column(name = "ID_MD_SECAO", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_SECAO")     
    @SequenceGenerator(name = "SEQ_MD_SECAO", sequenceName = "SEQ_MD_SECAO", allocationSize = 1)    
    private Long idMetadadosSessao;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_FORMULARIO")
    private MetadadosFormulario metadadosFormulario;
    
    @Column(name = "CD_SIGLA", nullable = false, length = 25)
    private String codigoSigla;
    
    @Column(name = "DS_NOME", nullable = false, length = 100)
    private String descricaoNome;
    
    @Column(name = "DS_TXT_INFORMATIVO", nullable = false, length = 4000)
    private String descricaoTextoInformativo;
    
    @Column(name = "DS_LABEL_MAGISTRADO", nullable = false, length = 1000)
    private String descricaoLabelSecaoMagistrado;
    
    @Column(name = "CD_SIGLA_MAGISTRADO", nullable = false, length = 100)
    private String indiceSecaoMagistrado;
    
    @Column(name = "DS_TXT_INFORMATIVO_MAGISTRADO", nullable = false, length = 1000)
    private String descricaoTextoInformativoSecaoMagistrado;
    
    @Column(name = "NR_ORDEM")
    private Long numeroOrdem;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Column(name = "FL_TEM_PROCESSO")
    private String flagTemProcesso;
    
    @Column(name = "FL_CONCLUSO_PARA")
    private String flagConclusoPara;
    
    @Column(name = "FL_EXIBE_TOTAIS")
    private String flagExibeTotais;
    
    @Column(name = "FL_PRISIONAL")
    private String flagPrisional;

    @Column(name = "FL_INTERNACAO")
    private String flagInternacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @OneToMany(mappedBy = "metadadosSecao", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<MetadadosGrupo> metadadosGrupos;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "MD_SECAO_MATERIA",
               joinColumns = { @JoinColumn(name = "FK_MD_SECAO",
                                           referencedColumnName =
                                           "ID_MD_SECAO") },
               inverseJoinColumns =
               { @JoinColumn(name = "FK_TIPO_MATERIA", referencedColumnName =
                             "ID_TIPO_MATERIA") })
    private List<TipoMateria> tiposMateria;

    public MetadadosSecao() {
    }

    public MetadadosSecao(Long idMetadadosSessao, MetadadosFormulario metadadosFormulario,
                          String descricaoNome, Date dataAtualizacao,
                          Date dataInclusao, String flagTipoSituacao, String codigoSigla,
                          String descricaoTextoInformativo, String flagTemProcesso, String flagConclusoPara,
                          String flagExibeTotais, String flagPrisional, String flagInternacao) {
        super();
        this.idMetadadosSessao = idMetadadosSessao;
        this.metadadosFormulario = metadadosFormulario;
        this.descricaoNome = descricaoNome;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.flagTipoSituacao = flagTipoSituacao;
        this.codigoSigla = codigoSigla;
        this.descricaoTextoInformativo = descricaoTextoInformativo;
        this.flagTemProcesso = flagTemProcesso;
        this.flagConclusoPara = flagConclusoPara;
        this.flagExibeTotais = flagExibeTotais;
        this.flagPrisional = flagPrisional;
        this.flagInternacao = flagInternacao;
    }

    public void setIdMetadadosSessao(Long idMetadadosSessao) {
        this.idMetadadosSessao = idMetadadosSessao;
    }

    public Long getIdMetadadosSessao() {
        return idMetadadosSessao;
    }

    public void setDescricaoNome(String descricaoNome) {
        this.descricaoNome = descricaoNome;
    }

    public String getDescricaoNome() {
        return descricaoNome;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataInclusao(Date dataInclusao) {
        if(dataInclusao == null){
            dataInclusao = new Date();
        }
        this.dataInclusao = dataInclusao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }
    
    public void setCodigoSigla(String codigoSigla) {
        this.codigoSigla = codigoSigla;
    }

    public String getCodigoSigla() {
        return codigoSigla;
    }

    public void setDescricaoLabelSecaoMagistrado(String descricaoLabelSecaoMagistrado) {
        this.descricaoLabelSecaoMagistrado = descricaoLabelSecaoMagistrado;
    }

    public String getDescricaoLabelSecaoMagistrado() {
        return descricaoLabelSecaoMagistrado;
    }

    public void setIndiceSecaoMagistrado(String indiceSecaoMagistrado) {
        this.indiceSecaoMagistrado = indiceSecaoMagistrado;
    }

    public String getIndiceSecaoMagistrado() {
        return indiceSecaoMagistrado;
    }

    public void setDescricaoTextoInformativoSecaoMagistrado(String descricaoTextoInformativoSecaoMagistrado) {
        this.descricaoTextoInformativoSecaoMagistrado = descricaoTextoInformativoSecaoMagistrado;
    }

    public String getDescricaoTextoInformativoSecaoMagistrado() {
        return descricaoTextoInformativoSecaoMagistrado;
    }

    public void setDescricaoTextoInformativo(String descricaoTextoInformativo) {
        this.descricaoTextoInformativo = descricaoTextoInformativo;
    }

    public String getDescricaoTextoInformativo() {
        return descricaoTextoInformativo;
    }

    public void setNumeroOrdem(Long numeroOrdem) {
        this.numeroOrdem = numeroOrdem;
    }

    public Long getNumeroOrdem() {
        return numeroOrdem;
    }

    public void setFlagTemProcesso(String flagTemProcesso) {
        this.flagTemProcesso = flagTemProcesso;
    }

    public String getFlagTemProcesso() {
        return flagTemProcesso;
    }

    public void setFlagConclusoPara(String flagConclusoPara) {
        this.flagConclusoPara = flagConclusoPara;
    }

    public String getFlagConclusoPara() {
        return flagConclusoPara;
    }

    public void setFlagExibeTotais(String flagExibeTotais) {
        this.flagExibeTotais = flagExibeTotais;
    }

    public String getFlagExibeTotais() {
        return flagExibeTotais;
    }

    public void setFlagPrisional(String flagPrisional) {
        this.flagPrisional = flagPrisional;
    }

    public String getFlagPrisional() {
        return flagPrisional;
    }

    public void setFlagInternacao(String flagInternacao) {
        this.flagInternacao = flagInternacao;
    }

    public String getFlagInternacao() {
        return flagInternacao;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setMetadadosGrupos(List<MetadadosGrupo> metadadosGrupos) {
        this.metadadosGrupos = metadadosGrupos;
    }

    public List<MetadadosGrupo> getMetadadosGrupos() {
        return metadadosGrupos;
    }

    public void setMetadadosFormulario(MetadadosFormulario metadadosFormulario) {
        this.metadadosFormulario = metadadosFormulario;
    }

    public MetadadosFormulario getMetadadosFormulario() {
        return metadadosFormulario;
    }

    public MetadadosGrupo addMetadadosGrupo(MetadadosGrupo metadadosGrupo) {
        getMetadadosGrupos().add(metadadosGrupo);
        metadadosGrupo.setMetadadosSecao(this);
        return metadadosGrupo;
    }

    public MetadadosGrupo removeMetadadosGrupo(MetadadosGrupo metadadosGrupo) {
        getMetadadosGrupos().remove(metadadosGrupo);
        metadadosGrupo.setMetadadosSecao(null);
        return metadadosGrupo;
    }


    public void setTiposMateria(List<TipoMateria> tiposMateria) {
        this.tiposMateria = tiposMateria;
    }

    public List<TipoMateria> getTiposMateria() {
        return tiposMateria;
    }

    @Override
    public Long getId() {
        return getIdMetadadosSessao();
    }

    @Override
    public void setId(Long id) {
        setIdMetadadosSessao(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Metadados Secao = ");
        sb.append(idMetadadosSessao);
        sb.append("\n");
        
        if (metadadosFormulario != null) {
            sb.append("Formulario = ");
            sb.append(metadadosFormulario.getDescricaoNome());
            sb.append("\n");
        }
        
        sb.append("Descricao Nome = ");
        sb.append(descricaoNome);
        sb.append("\n");
        
        sb.append("Flag Tipo Situação = ");
        sb.append(flagTipoSituacao);
        sb.append("\n");
        
        sb.append("Código Sigla = ");
        sb.append(codigoSigla);
        sb.append("\n");
        
        
        sb.append("Descrição Texto Informativo = ");
        sb.append(descricaoTextoInformativo);
        sb.append("\n");    
        
            
        sb.append("Flag Tem Processo = ");
        sb.append(flagTemProcesso);
        sb.append("\n");
        
            
        sb.append("Flag Concluso Para = ");
        sb.append(flagConclusoPara);
        sb.append("\n");
        
        
        sb.append("Flag Exibe Totais = ");
        sb.append(flagExibeTotais);
        sb.append("\n");
        
            
        sb.append("Flag Prisional = ");
        sb.append(flagPrisional);
        sb.append("\n"); 
        
            
        sb.append("Flag Internação = ");
        sb.append(flagInternacao);
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
    
    public SecaoDTO createSecaoDTO(FormularioDTO formularioDTO){
        SecaoDTO result = new SecaoDTO();
        result.setCodigoSecao(this.getCodigoSigla());
        result.setIdMetadadosSecao(this.getIdMetadadosSessao());
        result.setLabelSecao(this.getDescricaoNome());
        result.setTextoInformativo(this.getDescricaoTextoInformativo());
        result.setTotalizadores((this.getFlagExibeTotais().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                   true : false));
        result.setConclusos((this.getFlagConclusoPara().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ? true :
                               false));
        result.setTipoInternacao((this.getFlagInternacao().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                    true : false));
        result.setTipoPrisional((this.getFlagPrisional().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ? true :
                                   false));
        result.setTabelaProcessos((this.getFlagTemProcesso().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                     true : false));
        result.setOrdemSecao(this.getNumeroOrdem());
        result.setDataInclusao(this.getDataInclusao());
        result.setSituacao(this.getFlagTipoSituacao());
        result.setCodigoMagistrado(this.getIndiceSecaoMagistrado());
        result.setLabelMagistrado(this.getDescricaoLabelSecaoMagistrado());
        result.setInformativoMagistrado(this.getDescricaoTextoInformativoSecaoMagistrado());
        
        int size = this.getTiposMateria().size();
        List<TipoMateriaDTO> listaTipoMateriaDTO = new ArrayList<TipoMateriaDTO>();
        TipoMateria tipoMateria;
        for(int j = 1; j <= size ; j++){
            tipoMateria = this.getTiposMateria().get(j);
            listaTipoMateriaDTO.add(tipoMateria.createTipoMateriaDTO());
        }
        result.setListaMaterias(listaTipoMateriaDTO);
        if (this.getMetadadosGrupos() != null){
            size = this.getMetadadosGrupos().size();
            for(int j = 0; j < size ; j++){
                result.getListaGrupos().add(this.getMetadadosGrupos().get(j).createGrupoDTO(result));
            }  
        }
        result.setFormulario(formularioDTO);
        if (this.getCodigoSigla().equals(SecaoType.DADOS_UNIDADES.getCodigoSecao())) {
            result.getListaSubSecoes().add(new SubSecaoDTO(result));
        } else if (this.getCodigoSigla().equals(SecaoType.ESTABELECIMENTOS_PRISIONAIS.getCodigoSecao())) {
            result.getListaSubSecoes().add(new SubSecaoDTO(result));
        } else if (this.getCodigoSigla().equals(SecaoType.REUS.getCodigoSecao())) {
            result.getListaSubSecoes().add(new SubSecaoDTO(result));
        }
        
        return result;
    }
    
    
}
