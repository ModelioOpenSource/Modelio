/* 
 * Copyright 2013-2019 Modeliosoft
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

package com.sun.star.comp.beans;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This exception is thrown when a method is called which is only defined for not already having an established
 * connection.
 * 
 * @since OOo 2.0.0
 */
@objid ("a3f4a0b4-bcdf-4d88-8612-2a3558cddd69")
public class HasConnectionException extends Exception {
    @objid ("ebc54fa3-ec41-4aa8-b752-0e72404cc8cf")
    private static final long serialVersionUID = 1L;

}
