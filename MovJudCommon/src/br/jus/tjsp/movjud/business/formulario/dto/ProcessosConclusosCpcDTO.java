package br.jus.tjsp.movjud.business.formulario.dto;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessosConclusosCpcDTO implements Serializable{
    @SuppressWarnings("compatibility:-7112520832089924443")
    private static final long serialVersionUID = 1L;

    public ProcessosConclusosCpcDTO() {
        super();
    }
    
    private List<ProcessoConclusoDTO> listaProcessosConclusos = new ArrayList<ProcessoConclusoDTO>();
    private List<TipoFilaProcessoDTO> listaTipoFilaProcessoDTO = new ArrayList<TipoFilaProcessoDTO>();
    private Map<String, Integer> listaTipoFilaProcesso = new HashMap<String, Integer>();/*{{
        listaTipoFilaProcesso.put("teste01", 12);                                                                         
        listaTipoFilaProcesso.put("teste02", 14);
        listaTipoFilaProcesso.put("teste03", 16);
    }}*/

    // POCK CPC
    /*
        Total de processos que se encontram há mais de 60 dias úteis sem movimentação: 0
        
        Inicial aguardando análise de cartório: 2
        Petições ag. cadastro/juntada: 0
        Petição juntada ag. Análise: 1
        Aguardando minuta: 0
        Aguardando análise de cartório: 0
        Ag. análise do subfluxo despacho: 0
        Ag. análise do subfluxo decisão: 0
        Ag. análise do subfluxo sentança: 0
        Decurso de Prazo - Documentos: 0
     */
    private Integer ttProcessosSemMovimentacao = 0;
    
    
    private Integer inicialAguardandoAnaliseDeCartorio = 0;
    private Integer peticoesAgCadastroJuntada = 0;
    private Integer peticaoJuntadaAgAnalise = 0;
    private Integer aguardandoMinuta = 0;
    private Integer aguardandoAnaliseDeCartorio = 0;
    private Integer agAnaliseDoSubfluxoDespacho = 0;
    private Integer agAnaliseDoSubfluxoDecisao = 0;
    private Integer agAnaliseDoSubfluxoSentanca = 0;
    private Integer decursoDePrazoDocumentos = 0;
    
    // FIM - POCK CPC

    public void setTtProcessosSemMovimentacao(Integer ttProcessosSemMovimentacao) {
        this.ttProcessosSemMovimentacao = ttProcessosSemMovimentacao;
    }

    public Integer getTtProcessosSemMovimentacao() {
        return ttProcessosSemMovimentacao;
    }

    public void setListaProcessosConclusos(List<ProcessoConclusoDTO> listaProcessosConclusos) {
        this.listaProcessosConclusos = listaProcessosConclusos;
    }

    public List<ProcessoConclusoDTO> getListaProcessosConclusos() {
        return listaProcessosConclusos;
    }

    public void setListaTipoFilaProcesso(Map<String, Integer> listaTipoFilaProcesso) {
        this.listaTipoFilaProcesso = listaTipoFilaProcesso;
    }

    public Map<String, Integer> getListaTipoFilaProcesso() {
        return listaTipoFilaProcesso;
    }

    public void setInicialAguardandoAnaliseDeCartorio(Integer inicialAguardandoAnaliseDeCartorio) {
        this.inicialAguardandoAnaliseDeCartorio = inicialAguardandoAnaliseDeCartorio;
    }

    public Integer getInicialAguardandoAnaliseDeCartorio() {
        return inicialAguardandoAnaliseDeCartorio;
    }

    public void setPeticoesAgCadastroJuntada(Integer peticoesAgCadastroJuntada) {
        this.peticoesAgCadastroJuntada = peticoesAgCadastroJuntada;
    }

    public Integer getPeticoesAgCadastroJuntada() {
        return peticoesAgCadastroJuntada;
    }

    public void setPeticaoJuntadaAgAnalise(Integer peticaoJuntadaAgAnalise) {
        this.peticaoJuntadaAgAnalise = peticaoJuntadaAgAnalise;
    }

    public Integer getPeticaoJuntadaAgAnalise() {
        return peticaoJuntadaAgAnalise;
    }

    public void setAguardandoMinuta(Integer aguardandoMinuta) {
        this.aguardandoMinuta = aguardandoMinuta;
    }

    public Integer getAguardandoMinuta() {
        return aguardandoMinuta;
    }

    public void setAguardandoAnaliseDeCartorio(Integer aguardandoAnaliseDeCartorio) {
        this.aguardandoAnaliseDeCartorio = aguardandoAnaliseDeCartorio;
    }

    public Integer getAguardandoAnaliseDeCartorio() {
        return aguardandoAnaliseDeCartorio;
    }

    public void setAgAnaliseDoSubfluxoDespacho(Integer agAnaliseDoSubfluxoDespacho) {
        this.agAnaliseDoSubfluxoDespacho = agAnaliseDoSubfluxoDespacho;
    }

    public Integer getAgAnaliseDoSubfluxoDespacho() {
        return agAnaliseDoSubfluxoDespacho;
    }

    public void setAgAnaliseDoSubfluxoDecisao(Integer agAnaliseDoSubfluxoDecisao) {
        this.agAnaliseDoSubfluxoDecisao = agAnaliseDoSubfluxoDecisao;
    }

    public Integer getAgAnaliseDoSubfluxoDecisao() {
        return agAnaliseDoSubfluxoDecisao;
    }

    public void setAgAnaliseDoSubfluxoSentanca(Integer agAnaliseDoSubfluxoSentanca) {
        this.agAnaliseDoSubfluxoSentanca = agAnaliseDoSubfluxoSentanca;
    }

    public Integer getAgAnaliseDoSubfluxoSentanca() {
        return agAnaliseDoSubfluxoSentanca;
    }

    public void setDecursoDePrazoDocumentos(Integer decursoDePrazoDocumentos) {
        this.decursoDePrazoDocumentos = decursoDePrazoDocumentos;
    }

    public Integer getDecursoDePrazoDocumentos() {
        return decursoDePrazoDocumentos;
    }

    public void setListaTipoFilaProcessoDTO(List<TipoFilaProcessoDTO> listaTipoFilaProcessoDTO) {
        this.listaTipoFilaProcessoDTO = listaTipoFilaProcessoDTO;
    }

    public List<TipoFilaProcessoDTO> getListaTipoFilaProcessoDTO() {
        return listaTipoFilaProcessoDTO;
    }
}
