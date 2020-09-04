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

package org.modelio.diagram.api.dg.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.model.IGmLink;

/**
 * This class represents the DiagramGraphic of a 'Usage' element.
 */
@objid ("c220baeb-1fa7-4c73-b6a0-3048738cb769")
public class UsageDG extends DependencyDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The gm link represented by this class.
     */
    @objid ("90e7915d-f819-4745-9995-c33fe969d210")
    public UsageDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

}
