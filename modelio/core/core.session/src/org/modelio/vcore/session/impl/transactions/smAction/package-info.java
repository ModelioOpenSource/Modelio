/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
/**
 * Package regroupant la gestion des actions. Permet le m�canisme du undo/redo.
 * Les actions sont regroup�es dans des transactions, on peut valider, aborter une transaction. Quand une transaction est termnin�e elle est sauvegard�e dans la pile des transactions que l'on peut annuler "undo".
 * Quand une transaction � �t� annul�e on la range dans la pile des actions rejouables "redo".
 * Chaque type action est repr�sent� par une classe qui h�rite de la classe Action.
 */
package org.modelio.vcore.session.impl.transactions.smAction;