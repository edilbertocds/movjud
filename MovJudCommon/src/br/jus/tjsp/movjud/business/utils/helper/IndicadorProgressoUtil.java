package br.jus.tjsp.movjud.business.utils.helper;

import java.io.Serializable;


public class IndicadorProgressoUtil implements Serializable{
    @SuppressWarnings("compatibility:2097354891002182026")
    private static final long serialVersionUID = 1L;

    public static final long FIM_PROCESSAMENTO = -1;
    public static final long VALOR_DEFAULT = 0;
    public static final long VALOR_INTERVALO = 1000;
    public static final long VALOR_TIMEOUT = 600000;
    
    private long valorAtual;
    private long valorMaximo;
    private long intervalo;
    private long timeOut;
    private boolean erroProcessamento;
    private boolean finalizouProcessamento;
    private boolean atualizouInformacoes;
    

    
    public IndicadorProgressoUtil() {
        init();
    }
    
    public void init() {
        valorAtual              = VALOR_DEFAULT;
        valorMaximo             = VALOR_DEFAULT;
        intervalo               = VALOR_INTERVALO;
        timeOut                 = VALOR_TIMEOUT;
        erroProcessamento       = false;
        finalizouProcessamento  = false;
        atualizouInformacoes    = false;
    }

    public void setValorAtual(long valorAtual) {
        this.valorAtual = valorAtual;
    }

    public long getValorAtual() {
        return valorAtual;
    }

    public void setValorMaximo(long valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public long getValorMaximo() {
        return valorMaximo;
    }

    public void setIntervalo(long intervalo) {
        this.intervalo = intervalo;
    }

    public long getIntervalo() {
        return intervalo;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setErroProcessamento(boolean erroProcessamento) {
        this.erroProcessamento = erroProcessamento;
    }

    public boolean isErroProcessamento() {
        return erroProcessamento;
    }

    public void setFinalizouProcessamento(boolean finalizouProcessamento) {
        this.finalizouProcessamento = finalizouProcessamento;
    }

    public boolean isFinalizouProcessamento() {
        return finalizouProcessamento;
    }

    public void setAtualizouInformacoes(boolean atualizouInformacoes) {
        this.atualizouInformacoes = atualizouInformacoes;
    }

    public boolean isAtualizouInformacoes() {
        return atualizouInformacoes;
    }
}
