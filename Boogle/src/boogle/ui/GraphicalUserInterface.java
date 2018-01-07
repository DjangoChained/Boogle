/*
 * Copyright (C) 2018 rouchete et waxinp
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
import boogle.jeu.HighscoresManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JOptionPane;

/**
 * Interface utilisateur graphique de Boogle.
 *
 * @author rouchete
 */
public class GraphicalUserInterface extends UserInterface {

    private final MenuForm menuForm;
    private final ScoresForm scoresForm;
    private final MasterRaceForm masterRaceForm;
    private final GameForm gameForm;

    /**
     * Instancier une nouvelle interface graphique.
     *
     * @param e Moteur de jeu à utiliser.
     */
    public GraphicalUserInterface(Engine e) {
        super(e);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScoresForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.menuForm = new MenuForm(e);
        this.masterRaceForm = new MasterRaceForm();
        this.scoresForm = new ScoresForm(e);
        this.gameForm = new GameForm(e);
        this.menuForm.onReadyToPlay(() -> {
            engine.newGame(4);
            gameForm.setVisible(true);
        });
        this.gameForm.onGameEnd(() -> {
            try {
                HighscoresManager.LookForNewHighscores(engine.getSettings().getHighscoresLocation(), engine.getPlayers());
            } catch (java.io.IOException i) {
            }
            this.scoresForm.setVisible(true);
        });
        this.scoresForm.onHighscoresButton(() -> this.masterRaceForm.setVisible(true));
        this.scoresForm.onBackButton(() -> this.menuForm.setVisible(true));
    }

    /**
     * Démarrer l'interface graphique.
     */
    @Override
    public void start() {
        try {
            try {
                this.engine.initialize("rules-4x4.properties");
            } catch (IOException i) {
                JOptionPane.showMessageDialog(null, i.getMessage(), "Erreur de chargement", JOptionPane.ERROR_MESSAGE);
                return;
            }
            menuForm.setVisible(true);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.err.println(sw.toString());
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage() + "\n" + sw.toString(), "Erreur inattendue", JOptionPane.ERROR_MESSAGE);
        }
    }

}
