/*
 * Copyright (C) 2017 rouchete et waxinp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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

    private static final int MAX_HIGHSCORE = 10;
    private static ArrayList<Player> bestPlayers = new ArrayList<>();

    /**
     * Charger les meilleurs scores enregistrés.
     *
     * @param pathToScores chemin vers le fichier contenant les meilleurs scores
     * @throws java.io.IOException
     */
    public static void loadBestPlayers(String pathToScores) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(pathToScores));
        String line;
        while ((line = br.readLine()) != null && bestPlayers.size() <= MAX_HIGHSCORE) {
            String[] parts = line.split(",");
            bestPlayers.add(new Player(parts[0], Integer.parseInt(parts[1])));
        }
    }

    /**
     * Ecrire les meilleurs scores dans un fichier spécifié en paramètre
     *
     * @param pathToScores chemin vers le fichier contenant les meilleurs scores
     * @throws java.io.IOException
     */
    public static void writeBestPlayers(String pathToScores) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(pathToScores))) {
            Collections.sort(bestPlayers, Collections.reverseOrder());
            bestPlayers.forEach((p) -> pw.write(p.getName() + "," + p.getScore() + "\n"));
        }
    }

    /**
     * Indique si un score peut être intégré aux meilleurs scores
     *
     * @param score le score à comparer
     * @return True si le score est un nouveau record, false sinon
     */
    public static boolean isHighEnough(int score) {
        return bestPlayers.stream().anyMatch((p) -> (score > p.getScore()));
    }

    /**
     * Ajoute un nouveau meilleur score à la liste, remplace le moins grand des
     * meilleurs scores par le nouveau si la liste est pleine.
     *
     * @param p le joueur dont le score va être ajouté aux meilleurs scores
     * @return Ligne à afficher comme résultat.
     */
    public static String addNewHighScore(Player p) {
        String result = "Félicitations à " + p.getName();
        if (bestPlayers.size() == MAX_HIGHSCORE) {
            Player oldHighScore = removeLowestHighScore();
            result += " qui bat " + oldHighScore.getName() + " (" + oldHighScore.getScore() + " points)";
        } else {
            result += " qui inscrit un nouveau record";
        }
        bestPlayers.add(p);
        return result + " avec " + p.getScore() + " points !\n";
    }

    public static String LookForNewHighscores(String pathToScores, ArrayList<Player> players) throws IOException {
        String changes = "";
        for (Player p : players) {
            if (HighscoresManager.isHighEnough(p.getScore())) {
                changes += HighscoresManager.addNewHighScore(p);
            }
        }
        return changes;
    }

    /**
     * Supprime le moins grand des meilleurs scores.
     *
     * @return Joueur dont le score a été supprimé.
     */
    public static Player removeLowestHighScore() {
        Player minimumScore = bestPlayers.get(0);
        for (Player bp : bestPlayers) {
            if (bp.getScore() < minimumScore.getScore()) {
                minimumScore = bp;
            }
        }
        bestPlayers.remove(minimumScore);
        return minimumScore;
    }

    /**
     * Obtenir les meilleurs joueurs.
     *
     * @return Liste des meilleurs joueurs.
     */
    public static ArrayList<Player> getMasterRace() {
        return bestPlayers;
    }
}
