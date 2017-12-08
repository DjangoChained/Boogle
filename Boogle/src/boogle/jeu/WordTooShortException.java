
package boogle.jeu;

/**
 * Exception levée lorsque le mot saisi est trop court
 * 
 * @author waxinp
 */
public class WordTooShortException extends WordInputException {
    public WordTooShortException() { }
    
    public WordTooShortException(String message){
        super(message);
    }
}
