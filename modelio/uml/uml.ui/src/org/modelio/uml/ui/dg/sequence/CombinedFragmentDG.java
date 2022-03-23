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

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.PortContainerDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;

/**
 * This class represents the DiagramGraphic of a 'CombinedFragment' element.
 */
@objid ("e252c35e-0446-4ec0-a647-8fcb39267184")
public class CombinedFragmentDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("d79958b2-0e3d-4cb7-834f-45d34b486e95")
    public  CombinedFragmentDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("8b06f7fd-1c56-42f0-8681-61121689844d")
    @Override
    public void setBounds(final Rectangle newBounds) {
        final Rectangle currentBounds = getBounds();
        super.setBounds(new Rectangle(newBounds.x(),
                                      currentBounds.y(),
                                      newBounds.width(),
                                      currentBounds.height()));
        
    }

    @objid ("7591de47-2a3f-4812-85bc-109ddb55e789")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        // Inner nodes
        GmCompositeNode mainNode = (GmCompositeNode) getPrimaryNode();
        
        GmCompositeNode operandZone = mainNode.getCompositeFor(InteractionOperand.class);
        if (operandZone != null) {
            return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, operandZone.getVisibleChildren());
        } else {
            return Collections.emptyList();
        }
        
    }

}
