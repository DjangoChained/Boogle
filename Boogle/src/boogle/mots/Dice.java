
package boogle.mots;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 *
 * @author waxinp
 */
public class Dice {
    private char[] faces;
    
    public Dice(String faces) {
        this.faces = new char[faces.length()];
        int pos = 0;
        for (int i = 0; i < faces.length(); i++) {
            this.faces[pos++] = faces.charAt(i);
        }
    }
    
    public static Dice[] loadDices(String pathToDices) throws IOException {
        int pos = 0;
        List<String> lines = Files.readAllLines(Paths.get(pathToDices));
        Dice[] dices = new Dice[lines.size()];
        for (String line : lines)
            dices[pos++] = new Dice(line.replaceAll("[;,\t]", ""));
        return dices;
    }

    public char roll() {
        return faces[new Random().nextInt(faces.length)];
    }
    
    public String toString() {
        return String.valueOf(faces);
    }
}
