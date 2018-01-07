/*
 * Copyright (C) 2017 rouchete et waxinp
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

/**
 * Décrit un joueur ordinateur utilisant une intelligence artificielle.
 *
 * @author rouchete
 */
public class AIPlayer extends Player {

    protected AIPlayerBrain brain;

    /**
     * Instancier un joueur IA.
     *
     * @param name Nom du joueur.
     * @param brain Intelligence artificielle à utiliser.
     */
    public AIPlayer(String name, AIPlayerBrain brain) {
        super(name);
        this.brain = brain;
    }

    /**
     * Demander la saisie d'un mot à l'IA.
     *
     * @param e Moteur de jeu où récupérer les informations.
     * @return Mot saisi par le joueur IA, ou null pour déclarer la fin du tour.
     */
    public String askForInput(Engine e) {
        return this.brain.getInput(this, e);
    }
}
