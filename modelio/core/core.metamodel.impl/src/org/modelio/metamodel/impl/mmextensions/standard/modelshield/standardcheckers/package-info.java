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
 * The checkers error codes must be set in the range 200-999
 * 
 * Reminder:  
 *           0                   => no error
 *          1-99              => unused
 *          100-199       => synchronous blocking controls in metamodel
 *          200-999      => blocking core audit rules
 *          1000-9999 => standard audit
 */
package org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers;