
package boogle.jeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe utilisant un fichier texte pour stocker les 10 meilleurs scores du jeu
 * ainsi que les noms des joueurs qui y sont associés.
 * 
 * @author waxinp
 */
public class HighscoresManager {
    
    private static final int MAX_HIGHSCORE = 5;
    private static ArrayList<Player> bestPlayers = new ArrayList<>();
    /**
     * Charger les meilleurs scores enregistrés.
     * @param pathToScores chemin vers le fichier contenant les meilleurs scores
     * @throws java.io.IOException
     */
    public static void loadBestPlayers(String pathToScores) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(pathToScores));
        String line;
        while ((line = br.readLine()) != null && bestPlayers.size() <= MAX_HIGHSCORE) {
            String[] parts = line.split(",");
            bestPlayers.add(new Player(parts[0], Integer.parseInt(parts[1])));
        }
    }
    
    /**
     * Ecrire les meilleurs scores dans un fichier spécifié en paramètre
     * @param pathToScores chemin vers le fichier contenant les meilleurs scores
     * @param players liste des joeurs ayant les meilleurs scores
     * @throws java.io.IOException
     */
    public static void writeBestPlayers(String pathToScores) throws IOException{
        PrintWriter pw = new PrintWriter(new FileWriter(pathToScores));
        Collections.sort(bestPlayers, Collections.reverseOrder());
        for(Player p : bestPlayers)
            pw.write(p.getName()+","+p.getScore()+"\n");
	pw.close();
    }
    
    /**
     * Indique si un score peut être intégré aux meilleurs scores
     * @param score le score à comparer
     * @return True si le score est un nouveau record, false sinon
     */
    public static boolean isHighEnough(int score){
        for(Player p : bestPlayers){
            if(score > p.getScore())
                return true;
        }
        return false;
    }
    
    /**
     * Ajoute un nouveau meilleur score à la liste, remplace le moins grand
     * des meilleurs scores par le nouveau si la liste est pleine.
     * @param p le joueur dont le score va être ajouté aux meilleurs scores
     */
    public static void addNewHighScore(Player p){
        String result = "Félicitation à "+p.getName();
        if (bestPlayers.size() == MAX_HIGHSCORE){
            Player oldHighScore = removeLowestHighScore();
            result += " qui bat "+oldHighScore.getName()+" ("+oldHighScore.getScore()+" points)";
        }
        else result += " qui inscrit un nouveau record";
        System.out.println(result + " avec "+p.getScore()+" points !");
        bestPlayers.add(p);
    }
    
    /**
     * Supprime le moins grand des meilleurs scores.
     * @return le joueur dont le score a été supprimé
     */
    public static Player removeLowestHighScore(){
        Player minimumScore = bestPlayers.get(0);
        for(Player bp : bestPlayers){
            if(bp.getScore() < minimumScore.getScore()) minimumScore = bp;
        }
        bestPlayers.remove(minimumScore);
        return minimumScore;
    }
    
    /**
     * Obtenir les meilleurs joueurs
     * @return Liste des meilleurs joueurs
     */
    public static ArrayList<Player> getMasterRace() {
	return bestPlayers;
    }
}
