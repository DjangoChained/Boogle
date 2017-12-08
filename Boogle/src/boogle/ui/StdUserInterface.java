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
     * Démarrer l'interface utilisateur standard.
     */
    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
