
package boogle.jeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe utilisant un fichier texte pour stocker les 10 meilleurs scores du jeu
 * ainsi que les noms des joueurs qui y sont associés.
 * 
 * @author waxinp
 */
public class HighscoresManager {
    
    /**
     * Charger les meilleurs scores enregistrés.
     * @param pathToScores chemin vers le fichier contenant les meilleurs scores.
     * @return la liste des meilleurs joueurs avec leur score
     * @throws java.io.IOException
     */
    public static ArrayList<Player> loadBestPlayers(String pathToScores) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(pathToScores));
        ArrayList<Player> players = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            players.add(new Player(parts[0], Integer.parseInt(parts[1])));
        }
        return players;
    }
    
    /**
     * Indique si un score peut être intégré aux meilleurs scores
     * @return True si le score est un nouveau record, false sinon
     */
    public static boolean isHighEnough(){
        return false;
    }
}
