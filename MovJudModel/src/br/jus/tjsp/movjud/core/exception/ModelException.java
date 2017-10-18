package br.jus.tjsp.movjud.core.exception;


/**
 * Classe que ira encapsular as exceptions de banco de dados.
 * 
 * @author cds
 */
public class ModelException extends RuntimeException {
    @SuppressWarnings("compatibility:-740256322778165669")
    private static final long serialVersionUID = 1L;

    /**
     * Mantem o serialVersionUID.
     */


    /**
     * Construtor com parâmetro Throwable.
     * 
     * @param e.
     */
    public ModelException(Throwable e) {
        super(e);
    }

    /**
     * Construtor com parâmetro de mensagem.
     * 
     * @param message.
     */
    public ModelException(String message) {
        super(message);
    }
    
    
    public ModelException(String message, Throwable e) {
        
        super(message, e);
    }
}
