
package boogle.jeu;

/**
 * Exception levée lorsque le mot saisi n'est pas dans la grille
 * 
 * @author waxinp
 */
public class WordNotInLetterGridException extends Exception{
    
    public WordNotInLetterGridException() {}
    
    public WordNotInLetterGridException(String message) {
        super(message);
    }
}
