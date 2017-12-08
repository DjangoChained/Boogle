/*
 * Copyright (C) 2017 rouchete and waxinp
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
import java.util.List;

/**
 * Décrit un joueur de Boggle.
 * @author waxinp
 */
public class Player {
    private final String name;
    private int score;
    ArrayList<String> foundWords;
    
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.foundWords = new ArrayList<String>();
    }
    
    public void newWordFound(String word, int points) {
        foundWords.add(word);
        score += points;
    }

    public int getScore() {
        return score;
    }

    public List<String> getFoundWords() {
        return foundWords;
    }
    
    public boolean isAlreadyFound(String word) {
        return foundWords.contains(word);
    }
    
    public void reset() {
        foundWords.clear();
        score = 0;
    }
}
