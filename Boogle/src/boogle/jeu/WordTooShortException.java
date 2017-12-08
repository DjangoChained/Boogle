
package boogle.jeu;

/**
 * Exception levée lorsque le mot saisi est trop court
 * 
 * @author waxinp
 */
public class WordTooShortException extends Exception{
    public WordTooShortException(){}
    
    public WordTooShortException(String message){
        super(message);
    }
}
