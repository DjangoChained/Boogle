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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Gestionnaire de configuration du jeu.
 * @author waxinp
 */
public class Settings {
    private Properties propertiesLoader = null;
    private String filePath;
    
    public void loadFile(String filePath) throws IOException {
        this.filePath = filePath;
        this.propertiesLoader = new Properties();
        this.propertiesLoader.load(new FileInputStream(filePath));
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    public String get(String varName) throws IOException {
        return this.propertiesLoader.getProperty(varName);
    }
    
    public String get(String varName, String defaultValue) throws IOException {
        return this.propertiesLoader.getProperty(varName, defaultValue);
    }
    
    public void set(String varName, Object var) throws IOException {
        if (this.propertiesLoader == null) return;
        this.propertiesLoader.put(varName, var.toString());
        FileOutputStream fos = new FileOutputStream(filePath);
        this.propertiesLoader.store(fos, null);
    }
    
    public int getWordMinSize() throws IOException {
        return Integer.parseInt(get("minimum-size", "3"));
    }
    
    public String getDicesLocation() throws IOException {
        return get("dices");
    }
    
    public String getDictionaryLocation() throws IOException {
        return get("dictionary");
    }
    
    public String getHighscoresLocation() throws IOException {
        return get("highscores");
    }
    
    public int[] getPoints() throws IOException {
        return Arrays.stream(get("points").split(",")).mapToInt(i -> Integer.parseInt(i)).toArray();
    }
}
