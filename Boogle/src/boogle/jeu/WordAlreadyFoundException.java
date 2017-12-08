
package boogle.jeu;

/**
 * Exception levée lorsque le mot saisi a déjà été saisi
 * 
 * @author waxinp
 */
public class WordAlreadyFoundException extends WordInputException {
    public WordAlreadyFoundException() { }
    
    public WordAlreadyFoundException(String message) {
        super(message);
    }
}
