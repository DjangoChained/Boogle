

package boogle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author waxinp
 */
public class Settings {
    private int minSize;
    private String dicesLocation, dictionaryLocation;
    private int[] points = new int[6];
    private FileInputStream fis;
    private Properties propertiesLoader = null;
    private String filePath;
    
    public Settings(String filePath) { this.filePath = filePath; }
    
    public void loadFile(String filePath) throws IOException {
        
        this.fis = new FileInputStream(filePath);
        this.propertiesLoader = new Properties();
        this.propertiesLoader.load(fis);
        this.minSize = Integer.parseInt(propertiesLoader.getProperty("minimum-size"));
        this.dicesLocation = propertiesLoader.getProperty("dices");
        this.dictionaryLocation = propertiesLoader.getProperty("dictionary");
        String[] tokens = propertiesLoader.getProperty("points").split(",");
        for (int i = 0; i < points.length; i++){
            this.points[i] = Integer.parseInt(tokens[i]);
        }
        this.setProperty("rules-4x4.properties", "truc", "truc");
    }
    
    public String getfilePath(){return this.filePath;}
    
    public void setProperty(String filePath, String varName, Object var) throws IOException {
        if (this.propertiesLoader != null){
            this.propertiesLoader.put(varName, var.toString());
            FileOutputStream fos = new FileOutputStream(filePath);
            this.propertiesLoader.store(fos, null);
        }
    }
}
