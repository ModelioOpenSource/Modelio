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

package org.modelio.uml.ui.dg.state;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.PortContainerDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.uml.statediagram.editor.elements.state.GmStatePrimaryNode;

/**
 * This class represents the DiagramGraphic of a 'State' element.
 */
@objid ("33eef3f4-4d81-43ab-be4e-b479c11e3af7")
public class StateDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("bc6af573-a6ab-4dfd-88f7-fdc4d59f5f0f")
    public StateDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("70aa400a-d7ca-4290-880e-2d8d893f5ff7")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        List<IDiagramNode> nodes = new ArrayList<>();
        
        // Inner nodes
        GmStatePrimaryNode mainNode = (GmStatePrimaryNode) getPrimaryNode();
        
        // InternalTransition
        GmCompositeNode transitionZone = mainNode.getCompositeFor(InternalTransition.class);
        if (transitionZone != null) {
            nodes.addAll(DGFactory.getInstance().getDiagramNodes(this.diagramHandle, transitionZone.getVisibleChildren()));
        }
        
        // Region
        GmCompositeNode regionZone = mainNode.getCompositeFor(Region.class);
        if (regionZone != null) {
            nodes.addAll(DGFactory.getInstance().getDiagramNodes(this.diagramHandle, regionZone.getVisibleChildren()));
        }
        
        // StateVertex
        GmCompositeNode stateVertexZone = mainNode.getCompositeFor(StateVertex.class);
        if (stateVertexZone != null) {
            nodes.addAll(DGFactory.getInstance().getDiagramNodes(this.diagramHandle, stateVertexZone.getVisibleChildren()));
        }
        return nodes;
    }

}
