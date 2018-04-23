package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FormularioDTO extends BaseDTO<String>{
    @SuppressWarnings("compatibility:8925175784384573926")
    private static final long serialVersionUID = 4893371525241109136L;
    //@SuppressWarnings("compatibility:86229101127150386")
    //private static final long serialVersionUID = 1L;

    private String codigoFormulario; //codigo
    private String nomeFormulario; //nome
    private Long idMetadadosFormulario; //No sistema novo precisa inserir o id do relacionamento com o md formularios 
    private Long idFormulario; // id da cad formulario
    private Long idUnidade; // UNIDADE_ID que esta na tabela FORMULARIO_MENSAL
    private String nomeUnidade; // nome da unidade, utilizada apenas para apresntacao no novo movjud (nao precisa preencher)
    private List<SecaoDTO> listaSecoes; // Secoes do formulario diferenciadas pelo codigoSecao (codigos no Enum SecaoType)
    private String instancia; //instancia
    private String observacaoHistorico; // nao precisa preencher
    private Long versao; // vs
    private String aviso; // avisoFormulario
    private List<CompetenciaDTO> listaCompetencias; //Competencias estao relacionadas com o MD_FORMULARIO (Consultar tabela de compotencias no sistema antigo)
    private MateriaDTO area; //tabela relacional no sistema antigo
    private SegmentoDTO segmento; //tabela relacional no sistema antigo
    private Date dataCriacao; // DT_CRIACAO na formulario mensal
    private Date dataConclusao; // DT_FECHAMENTO na formulaario mensal
    private SituacaoFormularioDTO situacaoFormularioDTO; // SITUACAO  na tabela FORMULARIO_MENSAL - No sistema novo existe uma tabela de dominio TIPO_SITUACAO
    private MetadadoSituacaoFormularioDTO metadadoSituacaoFormularioDTO; // STATUS do XML de metadados (xml modelo). No sistema novo existe a tabela MD_TIPO_SITUACAO_FORMULARIO com os status ("em uso", "em edicao" e "historico")
    private Long idForo; // N�o precisa preencher
    private String nomeForo; // N�o precisa preencher
    private Long ano; //ano
    private Long mes; //mes
    private Long idMagistrado; //USUARIO_APROV da tabela FORMULARIO_MENSAL
    private Long idUsuarioPreenchimento; // USUARIO_PREENC da tabela FORMULARIO_MENSAL
    private String nomeMagistrado; // N�o precisa preencher
    private String nomeUsuarioPreenchimento;// N�o precisa preencher
    private SituacaoFormularioDTO novaSituacaoFormulario;// N�o precisa preencher
    private List<HistoricoFormularioDTO> listaHistoricoFormulario; // TABELA de formul�rios hist�ricos
    private List<TipoRegraDTO> listaTipoRegrasFormulario; // Acredito que seja uma tabela de vinculo (NxN) de formulario x tipo_regra
    private String aprovarRetificacao;// N�o precisa preencher
    private boolean flagRetificacao;// N�o precisa preencher
    private String perfilConsulta;// N�o precisa preencher
    private Long idUsuarioAlteracao;

    private transient Future<List<SecaoDTO>> futureListaSecoes;
    private transient Future<List<HistoricoFormularioDTO>> futureListaHistoricoFormulario;
        
    public FormularioDTO() {
    }
    
    public FormularioDTO(String codigoFormulario, Long idUnidade, Long ano, Long mes) {
        this.codigoFormulario = codigoFormulario;
        this.idUnidade = idUnidade;
        this.ano = ano;
        this.mes = mes;
    }
    
    public FormularioDTO(FormularioDTO formularioDTO){
        this.codigoFormulario = formularioDTO.codigoFormulario;
        this.nomeFormulario = formularioDTO.nomeFormulario;
        this.idMetadadosFormulario = formularioDTO.idMetadadosFormulario;
        this.listaSecoes = formularioDTO.listaSecoes;
        this.instancia = formularioDTO.instancia;
        this.versao = formularioDTO.versao;
        this.aviso = formularioDTO.aviso;
        this.listaCompetencias = formularioDTO.listaCompetencias;
        this.area = formularioDTO.area;
        this.segmento = formularioDTO.segmento;
        this.dataCriacao = formularioDTO.dataCriacao;
        this.situacaoFormularioDTO = formularioDTO.situacaoFormularioDTO;
        this.idUnidade = formularioDTO.idUnidade;
        this.nomeUnidade = formularioDTO.nomeUnidade;
        setDataInclusao(formularioDTO.getDataInclusao());
        setSituacao(formularioDTO.getSituacao());
        this.perfilConsulta = formularioDTO.getPerfilConsulta();
    }
    
    public void setArea(MateriaDTO area) {
        this.area = area;
    }

    public MateriaDTO getArea() {
        return area;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setSegmento(SegmentoDTO segmento) {
        this.segmento = segmento;
    }

    public SegmentoDTO getSegmento() {
        return segmento;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setAprovarRetificacao(String aprovarRetificacao) {
        this.aprovarRetificacao = aprovarRetificacao;
    }

    public String getAprovarRetificacao() {
        return aprovarRetificacao;
    }

    public void setListaHistoricoFormulario(List<HistoricoFormularioDTO> listaHistoricoFormulario) {
        this.listaHistoricoFormulario = listaHistoricoFormulario;
    }

    public List<HistoricoFormularioDTO> getListaHistoricoFormulario() {
        if (listaHistoricoFormulario == null) {
            if (futureListaHistoricoFormulario == null) {
                listaHistoricoFormulario = new ArrayList<HistoricoFormularioDTO>();
            } else {
                try {
                    listaHistoricoFormulario = futureListaHistoricoFormulario.get();
                } catch (InterruptedException | ExecutionException e) {
                    return null;
                }
            }
        }
        return listaHistoricoFormulario;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }

    public Long getVersao() {
        return versao;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public String getAviso() {
        return aviso;
    }

    public void setObservacaoHistorico(String observacaoHistorico) {
        this.observacaoHistorico = observacaoHistorico;
    }

    public String getObservacaoHistorico() {
        return observacaoHistorico;
    }

    public void setListaCompetencias(List<CompetenciaDTO> listaCompetencias) {
        this.listaCompetencias = listaCompetencias;
    }

    public List<CompetenciaDTO> getListaCompetencias() {
        if(listaCompetencias==null){
            listaCompetencias = new ArrayList<CompetenciaDTO>();
        }
        return listaCompetencias;
    }

    public void setCodigoFormulario(String codigoFormulario) {
        this.codigoFormulario = codigoFormulario;
    }

    public String getCodigoFormulario() {
        return codigoFormulario;
    }

    public void setNomeFormulario(String nomeFormulario) {
        this.nomeFormulario = nomeFormulario;
    }

    public String getNomeFormulario() {
        return nomeFormulario;
    }

    public void setNovaSituacaoFormulario(SituacaoFormularioDTO novaSituacaoFormulario) {
        this.novaSituacaoFormulario = novaSituacaoFormulario;
    }

    public SituacaoFormularioDTO getNovaSituacaoFormulario() {
        return novaSituacaoFormulario;
    }

    public void setListaSecoes(List<SecaoDTO> listaSecoes) {
        this.listaSecoes = listaSecoes;
    }

    public List<SecaoDTO> getListaSecoes() {
        if (listaSecoes == null) {
            if (futureListaSecoes == null) {
                listaSecoes = new ArrayList<SecaoDTO>();
            } else {
                try {
                    listaSecoes = futureListaSecoes.get();
                } catch (InterruptedException | ExecutionException e) {
                    listaSecoes = null;
                }
            }
        }
        return listaSecoes;
    }

    public void setIdMetadadosFormulario(Long idFormularioEntidade) {
        this.idMetadadosFormulario = idFormularioEntidade;
    }

    public Long getIdMetadadosFormulario() {
        return idMetadadosFormulario;
    }

    @Override
    public void setId(String id) {
        this.codigoFormulario = id;
    }

    @Override
    public String getId() {
        return codigoFormulario;
    }

    public void setInstancia(String instancia) {
        this.instancia = instancia;
    }

    public String getInstancia() {
        return instancia;
    }

    public void setSituacaoFormularioDTO(SituacaoFormularioDTO situacaoFormularioDTO) {
        this.situacaoFormularioDTO = situacaoFormularioDTO;
    }

    public SituacaoFormularioDTO getSituacaoFormularioDTO() {
        return situacaoFormularioDTO;
    }

    public void setIdFormulario(Long idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Long getIdFormulario() {
        return idFormulario;
    }

    public void setIdForo(Long idForo) {
        this.idForo = idForo;
    }

    public Long getIdForo() {
        return idForo;
    }

    public void setNomeForo(String nomeForo) {
        this.nomeForo = nomeForo;
    }

    public String getNomeForo() {
        return nomeForo;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    public Long getAno() {
        return ano;
    }

    public void setMes(Long mes) {
        this.mes = mes;
    }

    public Long getMes() {
        return mes;
    }

    public void setNomeMagistrado(String nomeMagistrado) {
        this.nomeMagistrado = nomeMagistrado;
    }

    public String getNomeMagistrado() {
        return nomeMagistrado;
    }

    public void setMetadadoSituacaoFormularioDTO(MetadadoSituacaoFormularioDTO metadadoSituacaoFormularioDTO) {
        this.metadadoSituacaoFormularioDTO = metadadoSituacaoFormularioDTO;
    }

    public MetadadoSituacaoFormularioDTO getMetadadoSituacaoFormularioDTO() {
        return metadadoSituacaoFormularioDTO;
    }

    public void setNomeUsuarioPreenchimento(String nomeUsuarioPreenchimento) {
        this.nomeUsuarioPreenchimento = nomeUsuarioPreenchimento;
    }

    public String getNomeUsuarioPreenchimento() {
        return nomeUsuarioPreenchimento;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }
    
    public String getDataConclusaoFormatada() {
        String data = "";
        if(listaHistoricoFormulario!=null && !listaHistoricoFormulario.isEmpty()){
            data = listaHistoricoFormulario.get(0).getDataCriacaoFormatada();
        }
        return data;
    }
    
    public String getObservacaoUltimoHistorico(){
        String observacao = "";
        if(listaHistoricoFormulario!=null && !listaHistoricoFormulario.isEmpty()){
            observacao = listaHistoricoFormulario.get(0).getDescricaoComentario();
        }
        return observacao;
    }

    public void setIdMagistrado(Long idMagistrado) {
        this.idMagistrado = idMagistrado;
    }

    public Long getIdMagistrado() {
        return idMagistrado;
    }

    public void setIdUsuarioPreenchimento(Long idUsuarioPreenchimento) {
        this.idUsuarioPreenchimento = idUsuarioPreenchimento;
    }

    public Long getIdUsuarioPreenchimento() {
        return idUsuarioPreenchimento;
    }
    
    public void setListaTipoRegrasFormulario(List<TipoRegraDTO> listaTipoRegrasFormulario) {
        this.listaTipoRegrasFormulario = listaTipoRegrasFormulario;
    }

    public void setFlagRetificacao(boolean flagRetificacao) {
        this.flagRetificacao = flagRetificacao;
    }

    public boolean isFlagRetificacao() {
        return flagRetificacao;
    }

    public List<TipoRegraDTO> getListaTipoRegrasFormulario() {
        return listaTipoRegrasFormulario;
    }

    public void setPerfilConsulta(String perfilConsulta) {
        this.perfilConsulta = perfilConsulta;
    }

    public String getPerfilConsulta() {
        return perfilConsulta;
    }

    public void setIdUsuarioAlteracao(Long idUsuarioAlteracao) {
        this.idUsuarioAlteracao = idUsuarioAlteracao;
    }

    public Long getIdUsuarioAlteracao() {
        return idUsuarioAlteracao;
    }

    public void setFutureListaSecoes(Future<List<SecaoDTO>> listaSecoes) {
        this.futureListaSecoes = listaSecoes;
    }
    
    public void setFutureListaHistoricoFormulario(Future<List<HistoricoFormularioDTO>> listaHistoricoFormulario) {
        this.futureListaHistoricoFormulario = listaHistoricoFormulario;
    }
}
