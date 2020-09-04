/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.xmi.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * @author ebrosse
 */
@objid ("5d528d09-2c0e-4d6b-9248-8eb2638e0255")
public enum FormatExport {
    /**
     * OMG UML2.1.1
     */
    UML211,
    /**
     * OMG UML2.2
     */
    UML22,
    /**
     * OMG UML2.3
     */
    UML23,
    /**
     * OMG UML2.4.1
     */
    UML241,
    /**
     * EMF UML2
     */
    EMF300;
}
