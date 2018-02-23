package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;
import br.jus.tjsp.movjud.business.formulario.types.ProcessoConclusoType;
import br.jus.tjsp.movjud.business.formulario.types.TipoJuizType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubSecaoDTO extends BaseDTO<Long> implements Comparable<SubSecaoDTO>{
    @SuppressWarnings("compatibility:3824523259394202894")
    private static final long serialVersionUID = -5970542879714251546L;

    private String codigoSubSecao;
    private Long idMetadadosSecao;
    private Long idSecao;
    private Long idSubSecao;
    private String codigoMagistrado;
    private String labelMagistrado;
    private String informativoMagistrado;
    private boolean conclusos;
    private boolean totalizadores;
    private String codigoCNJ;
    private boolean tabelaProcessos;
    private boolean reus;
    private String tituloReus;
    private String textoInformativo;
    private String labelReus;
    private boolean tipoInternacao;
    private boolean tipoPrisional;
    private String labelSecao;
    private Long ordemSubSecao;
    private String nomeMagistrado;
    private Long idMagistrado;
    private TipoJuizType tipoJuiz;
    private List<GrupoDTO> listaGrupos;
    private TipoMateriaDTO tipoMateria;
    private List<ProcessoConclusoDTO> listaProcessosConclusos;
    private List<ProcessoConclusoDTO> listaProcessosConclusosDeletarSubsequentes;
    private List<ProcessoConclusoDTO> listaProcessosConclusosDeletarAtualESubsequentes;
    private List<EstabelecimentoEntidadeDTO> listaEstabelecimentosEntidade;
    private List<ReuDTO> listaReus;
    private List<ReuDTO> listaReusHistoricoDeletar;
    private List<ForoOrigemDTO> listaForosOrigem;
    private SecaoDTO secao;
    private String filtroProcessoConcluso;
    private List<ProcessoConclusoDTO> listaFiltradaProcessosConclusos;
    
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
    
    @Override
    public int compareTo(SubSecaoDTO o) {
        // <epr> 0.7.6 - correção para ordenamento problema em formulário cível 05/17
        // return idSubSecao.compareTo(o.idSubSecao);
        return ordemSubSecao.compareTo(o.ordemSubSecao);
        // </epr>
    }
    
    public SubSecaoDTO() {
        listaGrupos = new ArrayList<GrupoDTO>();
        listaProcessosConclusos = new ArrayList<ProcessoConclusoDTO>();
        listaReus = new ArrayList<ReuDTO>();
    }
    
    public SubSecaoDTO(SecaoDTO secao) {
        this();
        this.idMetadadosSecao = secao.getIdMetadadosSecao();
        this.idSecao = secao.getIdSecao();
        this.codigoSubSecao = secao.getCodigoSecao();
        this.codigoMagistrado = secao.getCodigoMagistrado();
        this.labelMagistrado = secao.getLabelMagistrado();
        this.informativoMagistrado = secao.getInformativoMagistrado();
        this.conclusos = secao.isConclusos();
        this.totalizadores = secao.isTotalizadores();
        this.codigoCNJ = secao.getCodigoCNJ();
        this.tabelaProcessos = secao.isTabelaProcessos();
        this.reus = secao.isReus();
        this.tituloReus = secao.getTituloReus();
        this.labelReus = secao.getLabelReus();
        this.tipoInternacao = secao.isTipoInternacao();
        this.tipoPrisional = secao.isTipoPrisional();
        this.labelSecao = secao.getLabelSecao();
        this.ordemSubSecao = secao.getOrdemSecao();        
        this.listaGrupos.addAll(recuperarGruposComValores(secao.getListaGrupos(), this));
        this.textoInformativo = secao.getTextoInformativo();
        this.secao = secao;
    }
    
    public static List<GrupoDTO> recuperarGruposComValoresDeCamposVazios(List<GrupoDTO> listaGrupos, SubSecaoDTO subSecaoDTO){
        List<GrupoDTO> novaListaGrupos = new ArrayList<GrupoDTO>();
        for(GrupoDTO grupo : listaGrupos){
            GrupoDTO novoGrupo = new GrupoDTO(grupo);
            List<CampoDTO> listaCampos = new ArrayList<CampoDTO>();
            listaCampos.addAll(recuperarCamposValoresVazios(grupo.getListaCampos(), grupo));
            novoGrupo.setListaCampos(listaCampos);
            novoGrupo.setSubSecao(subSecaoDTO);
            novaListaGrupos.add(novoGrupo);
        }
        return novaListaGrupos;
    }
    
    public static List<GrupoDTO> recuperarGruposComValores(List<GrupoDTO> listaGrupos, SubSecaoDTO subSecaoDTO){
        List<GrupoDTO> novaListaGrupos = new ArrayList<GrupoDTO>();
        for(GrupoDTO grupo : listaGrupos){
            GrupoDTO novoGrupo = new GrupoDTO(grupo);
            List<CampoDTO> listaCampos = new ArrayList<CampoDTO>();
            listaCampos.addAll(recuperarCamposValores(grupo.getListaCampos(), novoGrupo));
            novoGrupo.setListaCampos(listaCampos);
            novoGrupo.setSubSecao(subSecaoDTO);
            novaListaGrupos.add(novoGrupo);
        }
        return novaListaGrupos;
    }
    
    public static List<CampoDTO> recuperarCamposValoresVazios(List<CampoDTO> listaCampos, GrupoDTO grupo){
        List<CampoDTO> novaListaCampos = new ArrayList<CampoDTO>();
        for(CampoDTO campo : listaCampos){
            CampoDTO novoCampo = new CampoDTO(campo);
            novoCampo.setValorCampo(null);
            novoCampo.setListaCampos(recuperarCamposValoresVazios(campo.getListaCampos(), campo));
            novoCampo.setGrupo(grupo);
            novaListaCampos.add(novoCampo);
        }
        return novaListaCampos;
    }
    
    public static List<CampoDTO> recuperarCamposValoresVazios(List<CampoDTO> listaCampos, CampoDTO campoPai){
        List<CampoDTO> novaListaCampos = new ArrayList<CampoDTO>();
        for(CampoDTO campo : listaCampos){
            CampoDTO novoCampo = new CampoDTO(campo);
            novoCampo.setValorCampo(null);
            novoCampo.setListaCampos(recuperarCamposValoresVazios(campo.getListaCampos(), campoPai));
            novoCampo.setCampoPai(campoPai);
            novaListaCampos.add(novoCampo);
        }
        return novaListaCampos;
    }
    
    public static List<CampoDTO> recuperarCamposValores(List<CampoDTO> listaCampos, GrupoDTO grupo){
        List<CampoDTO> novaListaCampos = new ArrayList<CampoDTO>();
        for(CampoDTO campo : listaCampos){
            CampoDTO novoCampo = new CampoDTO(campo);
            novoCampo.setListaCampos(recuperarCamposValores(campo.getListaCampos(), novoCampo));
            novoCampo.setGrupo(grupo);
            novaListaCampos.add(novoCampo);
        }
        return novaListaCampos;
    }
    
    public static List<CampoDTO> recuperarCamposValores(List<CampoDTO> listaCampos, CampoDTO campoPai){
        List<CampoDTO> novaListaCampos = new ArrayList<CampoDTO>();
        for(CampoDTO campo : listaCampos){
            CampoDTO novoCampo = new CampoDTO(campo);
            novoCampo.setListaCampos(recuperarCamposValores(campo.getListaCampos(), novoCampo));
            novoCampo.setCampoPai(campoPai);
            novaListaCampos.add(novoCampo);
        }
        return novaListaCampos;
    }
    
    public static SubSecaoDTO gerarNovaSubSecao(SecaoDTO secaoDTO){
        SubSecaoDTO subSecao = new SubSecaoDTO(secaoDTO);
        List<GrupoDTO> listaGrupos = new ArrayList<GrupoDTO>();
        listaGrupos.addAll(recuperarGruposComValoresDeCamposVazios(subSecao.getListaGrupos(), subSecao));
        subSecao.setListaGrupos(listaGrupos);
        return subSecao;
    }

    public void setValues(SubSecaoDTO secao) {
        this.codigoSubSecao = secao.codigoSubSecao;
        this.idMetadadosSecao = secao.idMetadadosSecao;
        this.codigoMagistrado = secao.codigoMagistrado;
        this.labelMagistrado = secao.labelMagistrado;
        this.informativoMagistrado = secao.informativoMagistrado;
        this.conclusos = secao.conclusos;
        this.totalizadores = secao.totalizadores;
        this.codigoCNJ = secao.codigoCNJ;
        this.tabelaProcessos = secao.tabelaProcessos;
        this.reus = secao.reus;
        this.tituloReus = secao.tituloReus;
        this.labelReus = secao.labelReus;
        this.tipoInternacao = secao.tipoInternacao;
        this.tipoPrisional = secao.tipoPrisional;
        this.labelSecao = secao.labelSecao;
        this.ordemSubSecao = secao.ordemSubSecao;
        this.listaGrupos.addAll(secao.listaGrupos);
        this.tipoMateria = secao.tipoMateria;
        this.tipoJuiz = secao.tipoJuiz;
        this.listaProcessosConclusos.addAll(secao.getListaProcessosConclusos());
        this.textoInformativo = secao.textoInformativo;
        this.listaReus.addAll(secao.getListaReus());
        this.listaEstabelecimentosEntidade.addAll(secao.listaEstabelecimentosEntidade);
    }
    
    public int getTotalProcessos(){
        return listaProcessosConclusos.size();   
    }
    
    public int getTotalProcessosSentenca(){
        int i = 0;
        for(ProcessoConclusoDTO processoConclusoDTO : listaProcessosConclusos){
            if(processoConclusoDTO.getTipoConclusoDTO().getTipoProcessoConcluso().equals(ProcessoConclusoType.SENTENCA)){
                i++;
            }
        }
        return i;   
    }
    
    public int getTotalProcessosDespacho(){
        int i = 0;
        for(ProcessoConclusoDTO processoConclusoDTO : listaProcessosConclusos){
            if(processoConclusoDTO.getTipoConclusoDTO().getTipoProcessoConcluso().equals(ProcessoConclusoType.DESPACHO)){
                i++;
            }
        }
        return i;  
    }
    
    public int getTotalProcessosDecisao(){
        int i = 0;
        for(ProcessoConclusoDTO processoConclusoDTO : listaProcessosConclusos){
            if(processoConclusoDTO.getTipoConclusoDTO().getTipoProcessoConcluso().equals(ProcessoConclusoType.DECISAO)){
                i++;
            }
        }
        return i;   
    }

    public void setCodigoSubSecao(String codigoSubSecao) {
        this.codigoSubSecao = codigoSubSecao;
    }

    public String getCodigoSubSecao() {
        return codigoSubSecao;
    }

    public void setIdMetadadosSecao(Long idEntidade) {
        this.idMetadadosSecao = idEntidade;
    }

    public void setListaEstabelecimentosEntidade(List<EstabelecimentoEntidadeDTO> listaEstabelecimentosEntidade) {
        this.listaEstabelecimentosEntidade = listaEstabelecimentosEntidade;
    }

    public List<EstabelecimentoEntidadeDTO> getListaEstabelecimentosEntidade() {
        if(listaEstabelecimentosEntidade == null){
            listaEstabelecimentosEntidade = new ArrayList<EstabelecimentoEntidadeDTO>();
        }
        return listaEstabelecimentosEntidade;
    }

    public Long getIdMetadadosSecao() {
        return idMetadadosSecao;
    }

    public void setCodigoMagistrado(String codigoMagistrado) {
        this.codigoMagistrado = codigoMagistrado;
    }

    public String getCodigoMagistrado() {
        return codigoMagistrado;
    }

    public void setNomeMagistrado(String nomeMagistrado) {
        this.nomeMagistrado = nomeMagistrado;
    }

    public String getNomeMagistrado() {
        return nomeMagistrado;
    }

    public void setSecao(SecaoDTO secao) {
        this.secao = secao;
    }

    public SecaoDTO getSecao() {
        return secao;
    }

    public void setIdMagistrado(Long idMagistrado) {
        this.idMagistrado = idMagistrado;
    }

    public Long getIdMagistrado() {
        return idMagistrado;
    }

    public void setListaForosOrigem(List<ForoOrigemDTO> listaForosOrigem) {
        this.listaForosOrigem = listaForosOrigem;
    }

    public List<ForoOrigemDTO> getListaForosOrigem() {
        if(listaForosOrigem==null){
            listaForosOrigem = new ArrayList<ForoOrigemDTO>();
        }
        return listaForosOrigem;
    }

    public void setListaReusHistoricoDeletar(List<ReuDTO> listaReusHistoricoDeletar) {
        this.listaReusHistoricoDeletar = listaReusHistoricoDeletar;
    }

    public List<ReuDTO> getListaReusHistoricoDeletar() {
        if(listaReusHistoricoDeletar == null){
            listaReusHistoricoDeletar = new ArrayList<ReuDTO>();
        }
        return listaReusHistoricoDeletar;
    }

    public void setLabelMagistrado(String labelMagistrado) {
        this.labelMagistrado = labelMagistrado;
    }

    public String getLabelMagistrado() {
        return labelMagistrado;
    }

    public void setInformativoMagistrado(String informativoMagistrado) {
        this.informativoMagistrado = informativoMagistrado;
    }

    public String getInformativoMagistrado() {
        return informativoMagistrado;
    }

    public void setConclusos(boolean conclusos) {
        this.conclusos = conclusos;
    }

    public boolean isConclusos() {
        return conclusos;
    }

    public void setIdSecao(Long idSecao) {
        this.idSecao = idSecao;
    }

    public Long getIdSecao() {
        return idSecao;
    }

    public void setTipoJuiz(TipoJuizType tipoJuiz) {
        this.tipoJuiz = tipoJuiz;
    }

    public TipoJuizType getTipoJuiz() {
        return tipoJuiz;
    }

    public void setIdSubSecao(Long idSubSecao) {
        this.idSubSecao = idSubSecao;
    }

    public Long getIdSubSecao() {
        return idSubSecao;
    }


    public void setTotalizadores(boolean totalizadores) {
        this.totalizadores = totalizadores;
    }

    public boolean isTotalizadores() {
        return totalizadores;
    }

    public void setCodigoCNJ(String codigoCNJ) {
        this.codigoCNJ = codigoCNJ;
    }

    public String getCodigoCNJ() {
        return codigoCNJ;
    }

    public void setTabelaProcessos(boolean tabelaProcessos) {
        this.tabelaProcessos = tabelaProcessos;
    }

    public boolean isTabelaProcessos() {
        return tabelaProcessos;
    }

    public void setReus(boolean reus) {
        this.reus = reus;
    }

    public boolean isReus() {
        return reus;
    }

    public void setTituloReus(String tituloReus) {
        this.tituloReus = tituloReus;
    }

    public String getTituloReus() {
        return tituloReus;
    }

    public void setTextoInformativo(String textoInformativo) {
        this.textoInformativo = textoInformativo;
    }

    public String getTextoInformativo() {
        return textoInformativo;
    }

    public void setLabelReus(String labelReus) {
        this.labelReus = labelReus;
    }

    public String getLabelReus() {
        return labelReus;
    }

    public void setTipoInternacao(boolean tipoInternacao) {
        this.tipoInternacao = tipoInternacao;
    }

    public boolean isTipoInternacao() {
        return tipoInternacao;
    }

    public void setTipoPrisional(boolean tipoPrisional) {
        this.tipoPrisional = tipoPrisional;
    }

    public boolean isTipoPrisional() {
        return tipoPrisional;
    }

    public void setLabelSecao(String labelSecao) {
        this.labelSecao = labelSecao;
    }

    public String getLabelSecao() {
        return labelSecao;
    }

    public void setListaReus(List<ReuDTO> listaReus) {
        if(listaReus==null){
            listaReus = new ArrayList<ReuDTO>();
        }
        this.listaReus = listaReus;
    }

    public List<ReuDTO> getListaReus() {
        if(listaReus==null){
            listaReus = new ArrayList<ReuDTO>();
        }
        return listaReus;
    }

    public void setOrdemSubSecao(Long ordemSubSecao) {
        this.ordemSubSecao = ordemSubSecao;
    }

    public Long getOrdemSubSecao() {
        return ordemSubSecao;
    }

    public void setListaGrupos(List<GrupoDTO> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<GrupoDTO> getListaGrupos() {
        return listaGrupos;
    }

    public void setTipoMateria(TipoMateriaDTO tipoMateria){
        this.tipoMateria = tipoMateria;
    }

    public TipoMateriaDTO getTipoMateria() {
        return tipoMateria;
    }

    public void setListaProcessosConclusos(List<ProcessoConclusoDTO> listaProcessosConclusos) {
        if(listaProcessosConclusos == null){
            listaProcessosConclusos = new ArrayList<ProcessoConclusoDTO>();
        }
        this.listaProcessosConclusos = listaProcessosConclusos;
    }

    public List<ProcessoConclusoDTO> getListaProcessosConclusos() {
        if(listaProcessosConclusos == null){
            listaProcessosConclusos = new ArrayList<ProcessoConclusoDTO>();
        }
        return listaProcessosConclusos;
    }

    public void setListaProcessosConclusosDeletarSubsequentes(List<ProcessoConclusoDTO> listaProcessosConclusosDeletarSubsequentes) {
        this.listaProcessosConclusosDeletarSubsequentes = listaProcessosConclusosDeletarSubsequentes;
    }

    public List<ProcessoConclusoDTO> getListaProcessosConclusosDeletarSubsequentes() {
        if(listaProcessosConclusosDeletarSubsequentes == null){
            listaProcessosConclusosDeletarSubsequentes = new ArrayList<ProcessoConclusoDTO>();
        }
        return listaProcessosConclusosDeletarSubsequentes;
    }


    public void setListaProcessosConclusosDeletarAtualESubsequentes(List<ProcessoConclusoDTO> listaProcessosConclusosDeletarAtualESubsequentes) {
        this.listaProcessosConclusosDeletarAtualESubsequentes = listaProcessosConclusosDeletarAtualESubsequentes;
    }

    public List<ProcessoConclusoDTO> getListaProcessosConclusosDeletarAtualESubsequentes() {
        if(listaProcessosConclusosDeletarAtualESubsequentes == null){
            listaProcessosConclusosDeletarAtualESubsequentes = new ArrayList<ProcessoConclusoDTO>();
        }
        return listaProcessosConclusosDeletarAtualESubsequentes;
    }

    @Override
    public void setId(Long id) {
        setIdSubSecao(id);
    }

    @Override
    public Long getId() {
        return getIdSubSecao();
    }

    public void setFiltroProcessoConcluso(String filtroProcessoConcluso) {
        this.filtroProcessoConcluso = filtroProcessoConcluso;
        if(listaProcessosConclusos != null && filtroProcessoConcluso != null && !filtroProcessoConcluso.isEmpty()) {
            listaFiltradaProcessosConclusos = listaProcessosConclusos
                .stream()
                .filter(p->p.getNumeroProcesso() != null && p.getNumeroProcesso().toString().startsWith(filtroProcessoConcluso))
                .collect(Collectors.toList());
        } else {
            listaFiltradaProcessosConclusos = listaProcessosConclusos;
        }
    }

    public String getFiltroProcessoConcluso() {
        return filtroProcessoConcluso;
    }

    public void setListaFiltradaProcessosConclusos(List<ProcessoConclusoDTO> listaFiltradaProcessosConclusos) {
        this.listaFiltradaProcessosConclusos = listaFiltradaProcessosConclusos;
    }

    public List<ProcessoConclusoDTO> getListaFiltradaProcessosConclusos() {
        if(listaFiltradaProcessosConclusos != null && filtroProcessoConcluso != null && !filtroProcessoConcluso.isEmpty())
            return listaFiltradaProcessosConclusos;
        else
            return listaProcessosConclusos;
    }

    public void setTtProcessosSemMovimentacao(Integer ttProcessosSemMovimentacao) {
        this.ttProcessosSemMovimentacao = ttProcessosSemMovimentacao;
    }

    public Integer getTtProcessosSemMovimentacao() {
        return ttProcessosSemMovimentacao;
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
}
