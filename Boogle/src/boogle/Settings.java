

package boogle;

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
    private FileOutputStream fos;
    private Properties propertiesLoader = null;
    private String filePath;
    
    public Settings(String filePath) { this.filePath = filePath; }
    
    public void loadFile(String filePath) throws IOException {
        
        this.fis = new FileInputStream(filePath);
        this.fos = new FileOutputStream(filePath);
        this.propertiesLoader = new Properties();
        this.propertiesLoader.load(fis);
        this.minSize = Integer.parseInt(propertiesLoader.getProperty("minimum-size"));
        this.dicesLocation = propertiesLoader.getProperty("dices");
        this.dictionaryLocation = propertiesLoader.getProperty("dictionary");
        String[] tokens = propertiesLoader.getProperty("points").split(",");
        for (int i = 0; i < points.length; i++){
            this.points[i] = Integer.parseInt(tokens[i]);
        }
        this.setProperty("test", "test");
    }
    
    public String getfilePath(){return this.filePath;}
    
    public void setProperty(String varName, Object var) throws IOException {
        if(this.propertiesLoader != null){
            propertiesLoader.setProperty(varName, var.toString());
            propertiesLoader.store(fos, null);
        }
    }
    
    public static void main (String[] args){
        Settings set = new Settings("rules-4x4.properties");
        try {
            set.loadFile(set.getfilePath());
        } catch (IOException ex) {
            System.err.println("Une erreur est survenue lors du chargement du fichier de configuration : "+ex);
        }
    }
}
