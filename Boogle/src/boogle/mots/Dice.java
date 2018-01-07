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
package boogle.mots;

import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Décrit un dé.
 *
 * @author waxinp
 */
public class Dice {

    private final char[] faces;

    /**
     * Créer un nouveau dé.
     *
     * @param faces Chaîne de caractères représentant les faces du dé.
     */
    public Dice(String faces) {
        this.faces = new char[faces.length()];
        int pos = 0;
        for (int i = 0; i < faces.length(); i++) {
            this.faces[pos++] = faces.charAt(i);
        }
    }

    /**
     * Charger les dés depuis un fichier de dés.
     *
     * @param pathToDices Chemin d'accès vers le fichier de dés.
     * @return Tableau de dés chargés via le fichier.
     * @throws IOException
     */
    public static Dice[] loadDices(String pathToDices) throws IOException {
        int pos = 0;
        List<String> lines = Files.readAllLines(Paths.get(pathToDices));
        Dice[] dices = new Dice[lines.size()];
        for (String line : lines) {
            dices[pos++] = new Dice(line.replaceAll("[;,\t]", ""));
        }
        return dices;
    }

    /**
     * Effectuer un lancer de dé.
     *
     * @return Face obtenue par le lancer du dé.
     */
    public char roll() {
        return faces[new Random().nextInt(faces.length)];
    }

    /**
     * Représentation textuelle d'un dé destinée au déboguage.
     *
     * @return Représentation textuelle du dé.
     */
    @Override
    public String toString() {
        return String.valueOf(faces);
    }
}
