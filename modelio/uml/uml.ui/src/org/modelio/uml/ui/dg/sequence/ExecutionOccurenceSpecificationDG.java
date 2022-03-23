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
package org.modelio.uml.ui.dg.sequence;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'ExecutionOccurenceSpecification' element.
 */
@objid ("c1341eda-b8e5-4a6f-85f7-80f363345eb5")
public class ExecutionOccurenceSpecificationDG extends DiagramNode {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("102f84fd-872e-41f7-a3c0-cc4bb5884b19")
    public  ExecutionOccurenceSpecificationDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("f69a7b38-9df4-44d0-8877-4d6d9022cbc9")
    @Override
    public List<IDiagramNode> getNodes() {
        return Collections.emptyList();
    }

    @objid ("70f55144-55e9-4ca3-8670-28386ed53094")
    @Override
    public void setBounds(final Rectangle newBounds) {
        // Do nothing
    }

    @objid ("ec17bfba-a8f6-40ff-b6f3-4edc1720763e")
    @Override
    public Rectangle getBounds() {
        return new Rectangle();
    }

    @objid ("f5a43a1f-8c29-49e4-a806-ca53b2dee443")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        return Collections.emptyList();
    }

}
