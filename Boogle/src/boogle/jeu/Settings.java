

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
    
    public int[] getPoints() throws IOException {
        return Arrays.stream(get("points").split(",")).mapToInt(i -> Integer.parseInt(i)).toArray();
    }
}
