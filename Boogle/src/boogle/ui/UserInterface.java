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
package boogle.ui;

import boogle.jeu.Engine;

/**
 * Décrit une interface utilisateur.
 *
 * @author rouchete
 */
public abstract class UserInterface {

    /**
     * Moteur de jeu utilisé par l'interface utilisateur.
     */
    protected Engine engine;

    /**
     * Instancier une nouvelle interface utilisateur pour un moteur de jeu.
     *
     * @param engine Moteur de jeu à utiliser.
     */
    public UserInterface(Engine engine) {
        this.engine = engine;
    }

    /**
     * Démarrer l'interface utilisateur.
     */
    public abstract void start();
}
