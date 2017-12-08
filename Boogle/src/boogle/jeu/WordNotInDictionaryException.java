
package boogle.jeu;

/**
 * Exception levée lorsque le mot saisi n'est pas dans le dictionnaire
 * 
 * @author waxinp
 */
public class WordNotInDictionaryException extends Exception {
    public WordNotInDictionaryException() {}
    
    public WordNotInDictionaryException(String message){
        super(message);
    }
}
