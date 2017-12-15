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
package boogle.ui;

import boogle.jeu.Engine;
import boogle.jeu.Player;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

/**
 * Interface utilisateur exploitant les entrées et sorties standard.
 * @author waxinp
 */
public class StdUserInterface extends UserInterface {

    private final Scanner reader;
    /**
     * Instancier une interface utilisateur standard.
     * @param engine Moteur de jeu à utiliser
     */
    public StdUserInterface(Engine engine) {
        super(engine);
        reader = new Scanner(System.in);
    }
    
    /**
     * Récupérer la liste des joueurs
     */
    public void getPlayers(){
        int nbPlayers = -1;
        
        while(nbPlayers < 0 || nbPlayers > 5){
            System.out.print("Combien de joueurs ? (1 à 5) : ");
            nbPlayers = reader.nextInt();
        }
        for(int i = 1; i < nbPlayers+1; i++){
            String countEnd = (i>1)?"ème ":"er";
            System.out.print("Entrez le nom du "+i+countEnd+" joueur : ");
            /* Le code compris entre les commentaires suivants est immonde,
            mais si j'essaye de le compacter de cette manière:
            engine.addPlayer(new Player(reader.nextLine()), le 1er joueur est vide,
            problème de buffer ?
            */
            String name = "";
            while(name.equals(""))
                name = reader.nextLine();
            engine.addPlayer(new Player(name));
            // fin de l'immondice
        }
    }
    
    /**
     * Démarrer l'interface utilisateur standard.
     */
    @Override
    public void start() {
        System.out.println("  ____                    _      \n" +
" |  _ \\                  | |     \n" +
" | |_) | ___   ___   __ _| | ___ \n" +
" |  _ < / _ \\ / _ \\ / _` | |/ _ \\\n" +
" | |_) | (_) | (_) | (_| | |  __/\n" +
" |____/ \\___/ \\___/ \\__, |_|\\___|\n" +
"                     __/ |       \n" +
"                    |___/        \n" +
"----------------------------------\n");
        try {
            this.engine.initialize("rules-4x4.properties");
            getPlayers();
            this.engine.newGame(4);
        } catch(IOException ex){
            System.out.println(ex);
            System.out.println("Un des fichiers de configuration n'a pas pu être chargé.");
        }
    }
    
    public void nextTurn() {
    	if (engine.isInitialized()) {
            Scanner reader = new Scanner(System.in);
            System.out.println(engine.getCurrentPlayer().getName()+", c'est à vous de jouer !\n"
                                             + engine.getLetterGrid());
            String answer = "";
            while(!answer.equals("stop")) {
                System.out.print("Proposez un mot ou \"stop\" pour arrêter votre tour : ");
                answer = reader.nextLine();

                try {
                    engine.wordInput(answer);
                    System.out.println("Bravo, vous gagnez "+engine.getScore(answer)+" points !\n");
                } catch (Exception ex) {
                    System.out.println(ex);
                    ex.printStackTrace();
                }
            }
            engine.endTurn();
    	}
    }
    
    public void end() {
    	System.out.println("La partie est terminée !\nVoici le palmarès : ");
    	Collections.sort(engine.getPlayers(), Collections.reverseOrder());
    	int count = 1;
    	for(Player p : engine.getPlayers()) {
    		System.out.println(count++ + ". "+p.getName()+" avec "+p.getScore()+" points et "
    						  +p.getFoundWords().size()+" mots trouvés");
    	}
        reader.close();
    }
    
    /**
     * Savoir si une partie est terminée
     */
    public boolean isFinished() {
    	return engine.isGameFinished();
    }
    
    /**
     * Savoir si une partie est en cours
     */
    public boolean isRunning() {
    	return engine.isGameRunning();
    }
    
}
