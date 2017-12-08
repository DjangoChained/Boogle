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

/**
 * Décrit une intelligence artificielle utilisable par AIPlayer.
 * @author rouchete
 */
public interface AIPlayerBrain {
    /**
     * Obtenir un mot valide saisi par l'IA.
     * Si "null" est renvoyé, l'IA termine son tour.
     * @param p Joueur IA concerné.
     * @param e Moteur de jeu concerné.
     * @return Mot valide, ou null pour terminer le tour.
     */
    public String getInput(AIPlayer p, Engine e);
}
