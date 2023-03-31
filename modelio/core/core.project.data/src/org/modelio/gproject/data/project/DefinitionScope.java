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
package org.modelio.gproject.data.project;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Scope of an IGPart (model fragment, module, parameter and so on.) definition.
 * <p>
 * When declared in a GProject configuration, an IGPart definition may be either LOCAL or SHARED:
 * <ul>
 * <li>a LOCAL definition is a definition that can modified locally by the end user. For example, a LOCAL module definition means that the end used can remove the module from the project</li>
 * <li>a SHARED defintion is a definition that is set on the server side and that cannot be modified locally. A SHARED module for example cannot be removed or disabled locally by the end user</li>
 * </ul>
 * Note that this definition scope concept applies to others configuration elements such as module parameter values for example. Fixing a module parameter value definition as SHARED is typically really usefull when a given methodology or particular configuration has to be
 * guaranteed in a project for all its members.
 */
@objid ("59b03ab2-2ff2-11e2-8f81-001ec947ccaf")
public enum DefinitionScope {
    /**
     * Local element.
     */
    @objid ("5e58239a-2ff2-11e2-8f81-001ec947ccaf")
    LOCAL,
    /**
     * Element shared by a server.
     */
    @objid ("62782bd0-2ff2-11e2-8f81-001ec947ccaf")
    SHARED;

}
