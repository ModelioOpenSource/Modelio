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

package org.modelio.gproject.data.project;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Scope of a fragment, module or parameter.
 * <p>
 * A fragment, module or parameter may be local to the machine or shared by a server.
 * Shared elements are not modifiable or removeable except on the server.
 */
@objid ("59b03ab2-2ff2-11e2-8f81-001ec947ccaf")
public enum DefinitionScope {
    /**
     * Local element.
     */
    LOCAL,
    /**
     * Element shared by a server.
     */
    SHARED;
}
