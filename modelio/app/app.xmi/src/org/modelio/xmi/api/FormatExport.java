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
@objid ("5d528d09-2c0e-4d6b-9248-8eb2638e0255")
public enum FormatExport {
    /**
     * OMG UML2.1.1
     */
    @objid ("97f38b25-a281-43f2-8981-18b5c07f87fb")
    UML211,
    /**
     * OMG UML2.2
     */
    @objid ("dc48c7a6-4ab4-4e0f-a13a-7c6ba2bf8b02")
    UML22,
    /**
     * OMG UML2.3
     */
    @objid ("809c756f-2b88-4e7d-ae7e-182db37e93e3")
    UML23,
    /**
     * OMG UML2.4.1
     */
    @objid ("82765a61-a5f0-4734-8952-0cbaa0672d27")
    UML241,
    /**
     * EMF UML2
     */
    @objid ("4c2e37fb-b6d6-437d-8dc4-80bed1541c19")
    EMF300;

}
