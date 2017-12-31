
package boogle.jeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Classe utilisant un fichier texte pour stocker les 10 meilleurs scores du jeu
 * ainsi que les noms des joueurs qui y sont associés.
 * 
 * @author waxinp
 */
public class HighscoresManager {
    
    private static ArrayList<Player> bestPlayers = new ArrayList<>();
    /**
     * Charger les meilleurs scores enregistrés.
     * @param pathToScores chemin vers le fichier contenant les meilleurs scores
     * @throws java.io.IOException
     */
    public static void loadBestPlayers(String pathToScores) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(pathToScores));
        String line;
        while ((line = br.readLine()) != null) {
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
        for(Player p : bestPlayers)
            pw.write(p.getName()+","+p.getScore()+"\n");
	pw.close();
    }
    
    /**
     * Indique si un score peut être intégré aux meilleurs scores
     * @return True si le score est un nouveau record, false sinon
     */
    public static boolean isHighEnough(){
        return false;
    }
    
    /**
     * Obtenir les meilleurs joueurs
     * @return Liste des meilleurs joueurs
     */
    public static ArrayList<Player> getMasterRace() {
	return bestPlayers;
    }
}
