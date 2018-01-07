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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Décrit un joueur de Boggle.
 *
 * @author waxinp
 */
public class Player implements Comparator<Player>, Comparable<Player> {

    protected final String name;
    protected int score;
    protected final ArrayList<String> foundWords;

    /**
     * Instancier un nouveau joueur de Boggle.
     *
     * @param name Nom du joueur.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.foundWords = new ArrayList<>();
    }

    /**
     * Instancier un nouveau joueur de Boggle.
     *
     * @param name Nom du joueur.
     * @param score Score à attribuer au joueur.
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
        this.foundWords = null;
    }

    /**
     * Ajouter un mot trouvé par le joueur.
     *
     * @param word Mot à ajouter.
     * @param points Score associé au mot.
     */
    public void newWordFound(String word, int points) {
        foundWords.add(word);
        score += points;
    }

    /**
     * Obtenir le nom du joueur.
     *
     * @return Nom du joueur.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtenir le score actuel du joueur.
     *
     * @return Score du joueur.
     */
    public int getScore() {
        return score;
    }

    /**
     * Obtenir les mots trouvés par le joueur.
     *
     * @return Mots trouvés par le joueur.
     */
    public List<String> getFoundWords() {
        return foundWords;
    }

    /**
     * Tester si un mot a déjà été trouvé par le joueur.
     *
     * @param word Mot à vérifier.
     * @return True si le mot a déjà été trouvé.
     */
    public boolean isAlreadyFound(String word) {
        return foundWords.contains(word);
    }

    /**
     * Remettre à zéro les informations de ce joueur.
     */
    public void reset() {
        foundWords.clear();
        score = 0;
    }

    /**
     * Représentation textuelle du joueur pour une interface textuelle.
     *
     * @return Représentation textuelle du joueur.
     */
    @Override
    public String toString() {
        return getName() + ", " + getScore() + " points";
    }

    /**
     * Comparer le joueur à un autre en termes de score.
     *
     * @param otherPlayer Autre joueur à comparer.
     * @return Comparaison des scores.
     */
    @Override
    public int compareTo(Player otherPlayer) {
        return Integer.compare(this.score, otherPlayer.getScore());
    }

    /**
     * Comparer un joueur à un autre en termes de score.
     *
     * @param player1 Premier joueur à comparer.
     * @param player2 Second joueur à comparer.
     * @return Comparaison des scores.
     */
    @Override
    public int compare(Player player1, Player player2) {
        return player1.getScore() - player2.getScore();
    }
}
