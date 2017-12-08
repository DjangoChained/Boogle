
package boogle.jeu;

/**
 * Exception levée lorsque le mot saisi a déjà été saisi
 * 
 * @author waxinp
 */
public class WordAlreadyFoundException extends Exception {
    public WordAlreadyFoundException() {}
    
    public WordAlreadyFoundException(String message){
        super(message);
    }
}
