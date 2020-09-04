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

package org.modelio.vcore.smkernel.mapi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface defines the services available to programmers outside 'vcore' for handling SmAttribute instances.
 * <p>
 * The MAttribute services are obviously a subset of those provided by SmAttribute. This is because external programmers are not
 * expected to modify the metamodel and therefore only limited 'get-like' accessors are provided.
 * 
 * @author phv
 */
@objid ("0022480e-25a9-1ffc-8433-001ec947cd2a")
public interface MAttribute {
    /**
     * @return the attribute name.
     */
    @objid ("0033c502-2ef8-1ffc-8433-001ec947cd2a")
    String getName();

    /**
     * @return the attribute type.
     */
    @objid ("0033ce44-2ef8-1ffc-8433-001ec947cd2a")
    Class<?> getType();

}
