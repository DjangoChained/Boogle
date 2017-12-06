
package boogle.mots;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 *
 * @author waxinp
 */
public class Dice {
    private char[] faces = new char[6];
    
    public Dice(String faces){
        int pos = 0;
        for (int i = 0; i <= faces.length(); i += 2){
            this.faces[pos++] = faces.charAt(i);
        }
    }
    
    public static Dice[] loadDices(String pathToDices, int nbDices) throws IOException {
        int pos = 0;
        Dice[] dices = new Dice[nbDices];
        List<String> lines = Files.readAllLines(Paths.get(pathToDices));
        for (String line : lines){
            dices[pos++] = new Dice(line);
        }
        return dices;
    }
    
    public String toString(){
        return String.valueOf(faces);
    }
}
