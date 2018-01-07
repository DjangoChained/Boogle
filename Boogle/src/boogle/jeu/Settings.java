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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Gestionnaire de configuration du jeu.
 *
 * @author waxinp
 */
public class Settings {

    private Properties propertiesLoader = null;
    private String filePath;

    /**
     * Charger la configuration depuis un fichier.
     *
     * @param filePath Chemin du fichier à utiliser.
     * @throws IOException Erreur lors du chargement du fichier.
     */
    public void loadFile(String filePath) throws IOException {
        this.filePath = filePath;
        this.propertiesLoader = new Properties();
        this.propertiesLoader.load(new FileInputStream(filePath));
    }

    /**
     * Obtenir le chemin d'accès du fichier utilisé pour la configuration.
     *
     * @return Chemin d'accès du fichier utilisé pour la configuration.
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Obtenir un paramètre par son nom.
     *
     * @param varName Nom du paramètre.
     * @return Valeur du paramètre, ou null si le paramètre n'existe pas.
     */
    public String get(String varName) {
        return this.propertiesLoader.getProperty(varName);
    }

    /**
     * Obtenir un paramètre par son nom, et renvoyer une valeur par défaut s'il
     * n'existe pas.
     *
     * @param varName Nom du paramètre.
     * @param defaultValue Valeur par défaut en cas de paramètre inexistant.
     * @return Valeur du paramètre, ou la valeur par défaut s'il n'existe pas.
     */
    public String get(String varName, String defaultValue) {
        return this.propertiesLoader.getProperty(varName, defaultValue);
    }

    /**
     * Définir un paramètre.
     *
     * @param varName Nom du paramètre.
     * @param var Nouvelle valeur du paramètre.
     * @throws IOException Erreur lors de l'enregistrement du paramètre dans un fichier.
     */
    public void set(String varName, Object var) throws IOException {
        if (this.propertiesLoader == null) {
            return;
        }
        this.propertiesLoader.put(varName, var.toString());
        FileOutputStream fos = new FileOutputStream(filePath);
        this.propertiesLoader.store(fos, null);
    }

    /**
     * Obtenir la taille minimale autorisée d'un mot.
     *
     * @return Taille minimale autorisée d'un mot.
     */
    public int getWordMinSize() {
        return Integer.parseInt(get("minimum-size", "3"));
    }

    /**
     * Obtenir l'emplacement du fichier de dés.
     *
     * @return Emplacement du fichier de dés.
     */
    public String getDicesLocation() {
        return get("dices");
    }

    /**
     * Obtenir l'emplacement du fichier de dictionnaire.
     *
     * @return Emplacement du fichier de dictionnaire.
     */
    public String getDictionaryLocation() {
        return get("dictionary");
    }

    /**
     * Obtenir l'emplacement du fichier de meilleurs scores.
     *
     * @return Emplacement du fichier de meilleurs scores.
     */
    public String getHighscoresLocation() {
        return get("highscores");
    }

    /**
     * Obtenir les points associés aux longueurs des mots.
     *
     * @return Tableau du nombre de points par longueur de mot.
     */
    public int[] getPoints() {
        return Arrays.stream(get("points").split(",")).mapToInt(i -> Integer.parseInt(i)).toArray();
    }
}
