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
import boogle.mots.Dice;
import boogle.mots.LetterGrid;
import boogle.mots.LexicalTreeNode;
import java.io.IOException;
import java.util.ArrayList;

public class Engine {
    private boolean initialized = false;
    private boolean running = false;
    private boolean finished = false;
    private final ArrayList<Player> players;
    private final Settings settings;
    private Dice[] dices;
    private LetterGrid letterGrid;
    private LexicalTreeNode dict;
    private int currentPlayer, minWordSize;
    private int[] points;

    public Engine() {
	this.players = new ArrayList<>();
        this.settings = new Settings();
    }

    /**
     * Initialiser le moteur de jeu, le dictionnaire, les dés, etc.
     * @param configPath Chemin d'accès du fichier de configuration
     * @throws IOException Problème lors de la lecture du fichier de configuration
     */
    public void initialize(String configPath) throws IOException {
        settings.loadFile(configPath);
        this.dices = Dice.loadDices(settings.getDicesLocation());
        this.dict = LexicalTreeNode.load(settings.getDictionaryLocation());
        this.minWordSize = settings.getWordMinSize();
        this.points = settings.getPoints();
        HighscoresManager.loadBestPlayers(settings.getHighscoresLocation());
        this.initialized = true;
    }
    
    /**
     * Tester si le moteur de jeu a été initialisé.
     * @return État d'initialisation du moteur de jeu.
     */
    public boolean isInitialized() {
	return initialized;
    }
    
    /**
     * Lancer une nouvelle partie.
     * @param gridSize Taille de la grille de lettres.
     */
    public void newGame(int gridSize) {
        assert 0 < players.size() && players.size() < 5;
	this.letterGrid = new LetterGrid(gridSize);
        this.currentPlayer = -1;
	running = true;
        endTurn();
}
    
    /**
     * Tester si une partie est en cours.
     * @return État en cours de la partie.
     */
    public boolean isGameRunning() {
	return running;
    }
    
    /**
     * Tester si une partie est terminée.
     * @return État de fin de partie.
     */
    public boolean isGameFinished() {
	return finished;
    }
    
    /**
     * Obtenir la grille de lettres actuelle.
     * @return Grille de lettres de la partie.
     */
    public LetterGrid getLetterGrid() {
	return this.letterGrid;
    }
    
    /**
     * Créer un joueur à partir de son nom.
     * @param name Nom du joueur.
     */
    public void createPlayer(String name) {
	addPlayer(new Player(name));
    }
    
    /**
     * Ajouter un joueur.
     * @param p Joueur à ajouter.
     */
    public void addPlayer(Player p) {
	players.add(p);
    }
    
    /**
     * Obtenir tous les joueurs.
     * @return Liste des joueurs.
     */
    public ArrayList<Player> getPlayers() {
	return players;
    }
    
    
    
    /**
     * Effacer tous les joueurs.
     */
    public void clearPlayers() {
        players.clear();
    }
    
    /**
     * Obtenir le joueur dont c'est actuellement le tour.
     * @return Joueur dont c'est actuellement le tour, null s'il n'y a pas de tour en cours.
     */
    public Player getCurrentPlayer() {
	return players.get(this.currentPlayer);
    }
    
    /**
     * Prendre en compte une saisie utilisateur.
     * @param word Mot saisi.
     * @throws WordTooShortException Le mot saisi est trop court pour être valable.
     * @throws WordNotInDictionaryException Le mot n'est pas dans le dictionnaire.
     * @throws WordNotInLetterGridException Le mot ne fait pas partie de la grille.
     * @throws WordAlreadyFoundException Le mot a déjà été trouvé par le joueur.
     */
    public void wordInput(String word) throws WordTooShortException,
                                              WordNotInDictionaryException,
                                              WordNotInLetterGridException,
                                              WordAlreadyFoundException {
        if(word.length() < this.minWordSize) throw new WordTooShortException(word);
        if(getCurrentPlayer().isAlreadyFound(word)) throw new WordAlreadyFoundException(word);
        if(!letterGrid.validWord(word)) throw new WordNotInLetterGridException(word);
        if(!this.dict.contains(word)) throw new WordNotInDictionaryException(word);
        this.getCurrentPlayer().newWordFound(word, getScore(word));
    }
    
    /**
     * Obtenir le score correspondant à un mot.
     * @param word Mot à calculer.
     * @return Score obtenu pour ce mot.
     */
    public int getScore(String word) {
        return word.length() < this.minWordSize ? 0
                : this.points[Math.min(word.length() - this.minWordSize, points.length - 1)];   
    }
    
    /**
     * Terminer le tour du joueur et passer au joueur suivant ou terminer la partie.
     */
    public void endTurn() {
        if(!running) return;
        this.currentPlayer++;
        if(currentPlayer >= players.size()) {
            this.running = false; this.finished = true; letterGrid = null;
        } else this.letterGrid.generate(dices);
    }
}
