package br.jus.tjsp.movjud.persistence.base.dao;


import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe auxiliar para buscas com paginacao.
 *
 * @author cds
 */
public class Paginacao implements Serializable {
    private static final long serialVersionUID = 12121214345678L;

    /**
     * Mantem o indice da pagina.
     */
    private int indice = 0;
    
    /**
     * Mantem o ultimo indice disponivel (calculado com base no tamanho da lista).
     */
    private int ultimoIndice = 0;
    
    /**
     * Mantem a quantidade de itens po pagina.
     */
    private int qtdItensPorPagina = 10;
    
    /** Mantem a quantidade de indice de pagina.
    */
    private int qtdIndicePaginas = 5;
    
    /** Mantem a quantidade de indice numerais para proximas paginas.
    */
    private int qtdIndiceProximasPaginas = 2;

    /**
     * Mantem a lista da consulta.
     */
    private List lista;
    
    /**
     * Mantem o tamanho maximo para a consulta.
     */
    private int tamanhoMaximo;
    
    /**
     * Mantem o campo utilizado para a ordenacao.
     */
    private String campoOrdenavel;
    
    /**
     * Mantem o tipoValidacao de ordenacao.
     */
    private String tipoOrdenacao;
    
    /**
     * Mantem a flag para habilitar/desabilitar o botao primeiro.
     */
    private boolean disabledPrimeiro = true;
    
    /**
     * Mantem a flag para habilitar/desabilitar o botao ultimo.
     */
    private boolean disabledUltimo = true;
    
    /**
     * Mantem a flag para habilitar/desabilitar o botao proximo.
     */
    private boolean disabledProximo = true;
    
    /**
     * Mantem a flag para habilitar/desabilitar o botao anterior.
     */
    private boolean disabledAnterior = true;

    /**
     * Obtem a lista.
     * 
     * @param <T>
     * @return lista.
     */
    public <T> List<T> getLista() {
        return this.lista;
    }

    /**
     * Define a lista.
     * 
     * @param <T>.
     * @param list.
     */
    public <T> void setLista(List<T> list) {
        this.lista = list;
        calcularIndices(list);
        atualizarStatusBotoes();
    }

    /**
     * Obtem o tamanho maximo.
     * 
     * @return tamanho maximo.
     */
    public int getTamanhoMaximo() {
        return tamanhoMaximo;
    }

    /**
     * Define o tamanho maximo.
     * 
     * @param maxLength.
     */
    public void setTamanhoMaximo(int maxLength) {
        this.tamanhoMaximo = maxLength;
    }

    /**
     * Define o campo a ser utilizado para a ordenacao.
     * 
     * @param sortField.
     */
    public void setCampoOrdenavel(String sortField) {
        this.campoOrdenavel = sortField;
    }

    /**
     * Obtem o campo utilizado para ordenacao.
     * 
     * @return campo utilizado para a ordenacao.
     */
    public String getCampoOrdenavel() {
        return campoOrdenavel;
    }

    /**
     * Define o tipoValidacao de ordenacao.
     *
     * @param sortOrdem .
     */
    public void setTipoOrdenacao(String sortOrdem) {
        this.tipoOrdenacao = sortOrdem;
    }

    /**
     * Obtem o tipoValidacao de ordenacao.
     *
     * @return tipoValidacao de ordenacao.
     */
    public String getTipoOrdenacao() {
        return tipoOrdenacao;
    }

    /**
     * Define o indice da pagina.
     * 
     * @param indice.
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**
     * Obtem o indice da pagina.
     * 
     * @return indice.
     */
    public int getIndice() {
        return indice;
    }

    /**
     * Define o ultimo indice.
     * 
     * @param ultimoIndice.
     */
    public void setUltimoIndice(int ultimoIndice) {
        this.ultimoIndice = ultimoIndice;
    }

    /**
     * Obtem o ultimo indice da pagina.
     * 
     * @return ultimoIndice.
     */
    public int getUltimoIndice() {
        return ultimoIndice;
    }

    /**
     * Define a quantidade de itens por pagina.
     * 
     * @param itensPorPagina.
     */
    public void setQtdItensPorPagina(int itensPorPagina) {
        this.qtdItensPorPagina = itensPorPagina;
    }

    /**
     * Obtem a quantidade de itens por pagina.
     * 
     * @return quantidade de itens por pagina.
     */
    public int getQtdItensPorPagina() {
        return qtdItensPorPagina;
    }

    /**
     * Metodo responsavel por calcular o indice inicial e final para a paginacao.
     * 
     * @param list.
     */
    private void calcularIndices(List list) {
        if (list != null) {
            final int qtdTotal = tamanhoMaximo;
            final int resto = qtdTotal % qtdItensPorPagina;
            final int qtdPaginas = qtdTotal / qtdItensPorPagina;
            
            // obtem o ultimo indice
            if (resto >= 1) {
                ultimoIndice = qtdPaginas;
            } else if (resto == 0) {
                ultimoIndice = qtdPaginas - 1;
            }
        }
    }
    
    /**
     * Metodo responsavel por recalcular os indices com base na lista.
     */
    public void recalcularIndices() {
        calcularIndices(getLista());
        atualizarStatusBotoes();
    }
    
    /**
     * Muda para o proximo indice.
     */
    public void proximo() {
        if (indice < ultimoIndice) {
            indice ++;
        }
        atualizarStatusBotoes();
    }
    
    /**
     * Muda para o indice anterior.
     */
    public void anterior() {
        if (indice > 0) {
            indice --;
        }
        atualizarStatusBotoes();
    }
    
    /**
     * Muda para o primeiro indice.
     */
    public void primeiro() {
       indice = 0;
        atualizarStatusBotoes();
    }
    
    /**
     * Muda o indice para o ultimo.
     */
    public void ultimo() {
        indice = ultimoIndice;
        atualizarStatusBotoes();
    }
    
    /**
     * Metodo responsavel pelas flag que habilita/desabilita os botoes.
     */
    private void atualizarStatusBotoes() {
        // status primeiro
        if (indice == 0) {
            disabledPrimeiro = true;
            disabledAnterior = true;
        } else {
            disabledPrimeiro = false;
            disabledAnterior = false;
        }
        // status ultimo
        if (indice == ultimoIndice) {
            disabledUltimo = true;
            disabledProximo = true;
        } else {
            disabledUltimo = false;
            disabledProximo = false;
        }
        // caso seja vazio desabilita os botoes
        if (lista == null || lista.isEmpty()) {
            disabledAnterior = true;
            disabledPrimeiro = true;
            disabledProximo = true;
            disabledUltimo = true;
        }
    }
    
    public void atualizarIndice(int indice){
        this.indice = indice;
        atualizarStatusBotoes();
    }

    /**
     * Define a flag para habilitar/desabilitar o botao primeiro.
     * 
     * @param exibePrimeiro.
     */
    public void setDisabledPrimeiro(boolean exibePrimeiro) {
        this.disabledPrimeiro = exibePrimeiro;
    }

    /**
     * Obtem a flag que habilita/desabilita o botao primeiro.
     * 
     * @return true se esta habilitado, false caso contrario.
     */
    public boolean isDisabledPrimeiro() {
        return disabledPrimeiro;
    }

    /**
     * Define a flag para habilitar/desabilitar o botao ultimo.
     * 
     * @param exibeUltimo.
     */
    public void setDisabledUltimo(boolean exibeUltimo) {
        this.disabledUltimo = exibeUltimo;
    }

    /**
     * Obtem a flag que habilita/desabilita o botao ultimo.
     * 
     * @return true se esta habilitado, false caso contrario.
     */
    public boolean isDisabledUltimo() {
        return disabledUltimo;
    }

    /**
     * Define a flag para habilitar/desabilitar o botao proximo.
     * 
     * @param exibeProximo.
     */
    public void setDisabledProximo(boolean exibeProximo) {
        this.disabledProximo = exibeProximo;
    }

    /**
     * Obtem a flag que habilita/desabilita o botao proximo.
     * 
     * @return true se esta habilitado, false caso contrario.
     */
    public boolean isDisabledProximo() {
        return disabledProximo;
    }

    /**
     * Define a flag para habilitar/desabilitar o botao anterior.
     * 
     * @param exibeAnterior.
     */
    public void setDisabledAnterior(boolean exibeAnterior) {
        this.disabledAnterior = exibeAnterior;
    }

    /**
     * Obtem a flag que habilita/desabilita o botao anterior.
     * 
     * @return true se esta habilitado, false caso contrario.
     */
    public boolean isDisabledAnterior() {
        return disabledAnterior;
    }
    
    /**
     * Obtem o indice do registro da paginacao.
     * 
     * @return indice do registro.
     */
    public int getIndiceRegistro() {
        return qtdItensPorPagina * indice;
    }
    
    public void setQtdIndicePaginas(int qtdIndicePaginas) {
        this.qtdIndicePaginas = qtdIndicePaginas;
    }

    public int getQtdIndicePaginas() {
        return qtdIndicePaginas;
    }
    
    public List<Integer> getIndicesPaginacao() {
        List<Integer> indicesPaginacao = new ArrayList<Integer>();      
        int minRange = 0;
        int maxRange = ultimoIndice;
        
        if(indice - (qtdIndicePaginas-qtdIndiceProximasPaginas) >= 0)
            minRange = indice - (qtdIndicePaginas-qtdIndiceProximasPaginas) + 1;
        
        if(minRange + (qtdIndicePaginas-qtdIndiceProximasPaginas) <= ultimoIndice)
            maxRange = minRange + (qtdIndicePaginas-qtdIndiceProximasPaginas) - 1;

        for(int i=minRange; i<=maxRange; i++){
            indicesPaginacao.add(i);
            for(int c=0;c<qtdIndiceProximasPaginas;c++){
                if(i==maxRange && i<ultimoIndice-c){
                    indicesPaginacao.add(i+(c+1));
                }
            }
        }
        
        return indicesPaginacao;
    }
    
    public static void main(String[] args) {
        List<Usuario> lista = new ArrayList<Usuario>();       
        for(int i=0; i<100; i++) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario((long)i);
            usuario.setNome("Usuario_"+i);
            
            lista.add(usuario);         
        }
        
        Paginacao paginacao = new Paginacao();
        paginacao.setTamanhoMaximo(lista.size());
        paginacao.setLista(lista);
        paginacao.setIndice(9);
        paginacao.recalcularIndices();
        List<Integer> indicesPaginacao = paginacao.getIndicesPaginacao();
        
        System.out.println("Qtde Indices Paginas: " + paginacao.getQtdIndicePaginas()); 
        for(Integer i: indicesPaginacao) {     
           System.out.println("indice: " + i);    
        }

    }

}
