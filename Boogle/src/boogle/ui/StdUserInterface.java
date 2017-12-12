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
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interface utilisateur exploitant les entrées et sorties standard.
 * @author lucidiot
 */
public class StdUserInterface extends UserInterface {

    /**
     * Instancier une interface utilisateur standard.
     * @param engine Moteur de jeu à utiliser
     */
    public StdUserInterface(Engine engine) {
        super(engine);
    }
    
    /**
     * Récupérer la liste des joueurs
     */
    public void getPlayers(){
        int nbPlayers = -1;
        
        Scanner reader = new Scanner(System.in);
        while(nbPlayers < 0 || nbPlayers > 5){
            System.out.print("Combien de joueurs ? (1 à 5) : ");
            nbPlayers = reader.nextInt();
        }
        for(int i = 1; i < nbPlayers+1; i++){
            String countEnd = (i>1)?"ème ":"er";
            System.out.print("Entrez le nom du "+i+countEnd+" utilisateur : ");
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
        reader.close();
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
            engine.initialize("rules-4x4.properties");
            StdUserInterface userInterface = new StdUserInterface(engine);
            this.engine.newGame(4);
            getPlayers();
            for(Player p: engine.getPlayers())
                System.out.println(p.getName());
        } catch(IOException ex){
            System.out.println(ex);
            System.out.println("Un des fichiers de configuration n'a pas pu être chargé.");
        }
    }
    
}
