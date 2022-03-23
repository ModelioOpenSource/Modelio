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
package org.modelio.diagram.api.dg.infra;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.dg.common.LeafNodeDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'Note' element.
 */
@objid ("856753cb-5629-444f-90d1-fca78c2c24db")
public class NoteDG extends LeafNodeDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("e7a046eb-d6d2-44ff-afaf-4a9814bc8429")
    public  NoteDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

}
