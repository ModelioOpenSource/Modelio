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

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmNodeModel;

@objid ("536d8761-2c75-44d8-a213-85123cdc83c5")
public class LeafPortContainerDG extends PortContainerDG {
    @objid ("163f3ccb-e75d-4546-be7b-5089773fbe31")
    public LeafPortContainerDG(DiagramHandle diagramHandle, GmNodeModel gmNode) {
        super(diagramHandle, gmNode);
    }

    @objid ("2938cae2-0726-43a3-8cd9-c7e26088c746")
    @Override
    protected final List<IDiagramNode> getPrimaryChildrenNodes() {
        return Collections.emptyList();
    }

}
