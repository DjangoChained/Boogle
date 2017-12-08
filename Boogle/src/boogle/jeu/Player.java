package boogle.jeu;

import java.util.ArrayList;

/**
 *
 * @author waxinp
 */
public class Player {
    private String name;
    private int score;
    ArrayList<String> foundWords;
    
    public Player(String name){
        this.name = name;
        this.score = 0;
        this.foundWords = new ArrayList<String>();
    }
    
    public void newWordFound(String word, int points){
        foundWords.add(word);
        score += points;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<String> getFoundWords() {
        return foundWords;
    }
    
    public boolean isAlreadyFound(String word){
        return foundWords.contains(word);
    }
    
    public void reset(){
        foundWords.clear();
        score = 0;
    }
}
