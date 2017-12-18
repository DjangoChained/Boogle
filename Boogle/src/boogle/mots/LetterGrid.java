/*
 * Copyright (C) 2017 lucidiot
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
package boogle.mots;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Représente la grille de lettres du jeu.
 * @author rouchete
 */
public class LetterGrid {
    private final char[][] grid;
    
    /**
     * Instancier une grille de lettres avec un nombre de lettres donné.
     * @param n Nombre de lettres du côté de la grille.
     */
    public LetterGrid(int n) {
        grid = new char[n][n];
    }

    /**
     * Générer aléatoirement une grille à partir de dés.
     * @param dices Dés à utiliser.
     */
    public void generate(Dice[] dices) {
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[i].length; j++)
                grid[i][j] = dices[(grid.length-1)*i+j].roll();
    }

    /**
     * Obtenir la taille d'un côté de la grille.
     * @return Taille d'un côté de la grille.
     */
    public int getSize() {
        return grid.length;
    }

    /**
     * Déterminer si un mot est valide selon les règles du Boggle.
     * @param w Mot à rechercher.
     * @return True si la grille contient le mot recherché selon les règles du Boggle.
     */
    public boolean validWord(String w) {
        String s = w.toUpperCase();
        if (s.length() < getSize() - 1) return false;
        for(int i = 0; i < getSize(); i++) for(int j = 0; j < getSize(); j++)
            if(grid[j][i] == s.charAt(0) && findSequence(s, i, j)) return true;
        return false;
    }

    /**
     * Recherche récursive d'une séquence de lettres dans la grille en partant d'une coordonnée.
     * @param s Séquence de lettres à rechercher
     * @param i Coordonnée X de départ
     * @param j Coordonnée Y de départ
     * @return True si la séquence de lettres existe dans la grille et peut former un mot.
     */
    private boolean findSequence(String s, int i, int j) {
        if(s.length() < 1) return true;
        if(s.length() == 1) return containsLetter(s.charAt(0));
        char c = grid[j][i];
        grid[j][i] = ' ';
        for(int u = -1; u <= 1; u++) for(int v = -1; v <= 1; v++)
            if(safeLetterAt(i+u, j+v) == s.charAt(1) && findSequence(s.substring(1), i+u, j+v)) {
                grid[j][i] = c; return true;
            }
        grid[j][i] = c;
        return false;
    }
    
    /**
     * Sécurisation de coordonnées hors de la grille.
     * @param i Coordonnée X de la lettre.
     * @param j Coordonnée Y de la lettre.
     * @return Lettre, ou '*' si les coordonnées sont hors de la grille.
     */
    public char safeLetterAt(int i, int j) {
        try { return grid[j][i]; }
        catch (ArrayIndexOutOfBoundsException a) { return '*'; }
    }
    
    /**
     * Sécurisation de coordonnées hors de la grille.
     * @param index Index de la lettre à rechercher
     * @return Lettre, ou '*' si les coordonnées sont hors de la grille.
     */
    public char safeLetterAt(int index) {
        return safeLetterAt(index / 4, index % 4);
    }
    
    /**
     * Déterminer si une lettre sur la grille est à côté d'une autre lettre.
     * @param index Index de la lettre dont les voisins devraient contenir la lettre recherchée
     * @param c Lettre à rechercher
     * @return True si les lettres sont côte-à-côte et peuvent être utilisées pour définir un mot valide du Boggle.
     */
    public boolean nextTo(int index, char c) {
        return lettersNextTo(index).contains(c);
    }

    /**
     * Obtenir toutes les lettres à côté d'une lettre donnée par son index.
     * @param index Index de la lettre.
     * @return Liste de lettres correspondant aux voisins de cette lettre.
     */
    public List<Character> lettersNextTo(int index) {
        return Arrays.stream(new int[] {index - 1, index + 1, index - grid[0].length, index + grid[0].length}).mapToObj(i -> letterAt(i)).collect(Collectors.toList());
    }
    
    /**
     * Rechercher toutes les positions d'une lettre dans la grille. 
     * @param c Lettre à rechercher.
     * @return Tableau d'entiers, vide si la lettre n'existe nulle part.
     */
    private int[] findLetter(char c) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < grid.length; i++) for(int j = 0; j < grid[i].length; j++)
            if(grid[i][j] == c) result.add((grid.length-1)*i+j);
        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Déterminer si la grille contient une lettre donnée.
     * @param c Lettre à rechercher.
     * @return True si la grille contient la lettre recherchée.
     */
    private boolean containsLetter(char c) {
        for(char[] line : grid) for(char l : line) if (l == c) return true;
        return false;
    }

    /**
     * Obtenir la lettre située à l'index donné.
     * @param index Index où rechercher la lettre.
     * @return Lettre.
     */
    public char letterAt(int index) {
        return letterAt(index / 4, index % 4);
    }
    
    /**
     * Obtenir la lettre située aux coordonnées données.
     * @param x Coordonnée X de la lettre.
     * @param y Coordonnée Y de la lettre.
     * @return Lettre aux coordonnées données.
     */
    public char letterAt(int x, int y) {
        return grid[x][y];
    }
    
    /**
     * Représentation textuelle de la grille.
     * @return Représentation textuelle de la grille.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(char[] s : grid) sb.append(s).append("\n");
        return sb.toString();        
    }
    
}
