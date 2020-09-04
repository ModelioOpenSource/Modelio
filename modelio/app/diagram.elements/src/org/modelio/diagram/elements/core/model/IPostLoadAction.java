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

package org.modelio.diagram.elements.core.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPartViewer;

/**
 * Post loading action.
 * <p>
 * Action run after the diagram is loaded and all edit parts are activated.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("9a61f975-1ddb-4bc5-8392-5389b38ce276")
public interface IPostLoadAction {
    /**
     * Run this action
     * @param viewer the edit part viewer.
     */
    @objid ("9d382761-aeda-410a-84c3-32ae3671a5a3")
    void run(EditPartViewer viewer);

}
