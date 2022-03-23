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
package org.modelio.xmi.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * @author ebrosse
 */
@objid ("d4a5318f-362b-4a54-89e4-5c86b76379fd")
public enum XMIExtension {
    /**
     * .xmi extension
     */
    @objid ("b2b683d8-93fe-4557-98cd-16a888d561e0")
    XMI,
    /**
     * .uml extension
     */
    @objid ("29da1853-3b3f-4662-bb42-d4ab192b32f6")
    UML;

}
