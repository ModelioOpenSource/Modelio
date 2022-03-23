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
package org.modelio.uml.ui.dg.deployment;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.PortContainerDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactPrimaryNode;

/**
 * This class represents the DiagramGraphic of a 'Artifact' element.
 */
@objid ("cd3b1d79-c7c3-4651-9e88-d7ee74681bf6")
public class ArtifactDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("76d851c9-b2ae-4ce1-94bc-9a2635509358")
    public  ArtifactDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("342acb64-0d08-4f8a-9ba6-75065d7190d5")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        List<IDiagramNode> nodes = new ArrayList<>();
        
        // Inner nodes
        GmArtifactPrimaryNode mainNode = (GmArtifactPrimaryNode) getPrimaryNode();
        
        // Attributes
        GmCompositeNode attributeZone = mainNode.getCompositeFor(Attribute.class);
        if (attributeZone != null) {
            nodes.addAll(DGFactory.getInstance().getDiagramNodes(this.diagramHandle, attributeZone.getVisibleChildren()));
        }
        
        // Instances
        GmCompositeNode instanceZone = mainNode.getCompositeFor(Instance.class);
        if (instanceZone != null) {
            nodes.addAll(DGFactory.getInstance().getDiagramNodes(this.diagramHandle, instanceZone.getVisibleChildren()));
        }
        
        // Operations
        GmCompositeNode operationZone = mainNode.getCompositeFor(Operation.class);
        if (operationZone != null) {
            nodes.addAll(DGFactory.getInstance().getDiagramNodes(this.diagramHandle, operationZone.getVisibleChildren()));
        }
        
        // Namespaces
        GmCompositeNode namespaceZone = mainNode.getCompositeFor(NameSpace.class);
        if (namespaceZone != null) {
            nodes.addAll(DGFactory.getInstance().getDiagramNodes(this.diagramHandle, namespaceZone.getVisibleChildren()));
        }
        return nodes;
    }

}
