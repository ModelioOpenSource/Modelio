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

package org.modelio.uml.ui.dg.activity;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.dg.common.PortContainerDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.uml.activitydiagram.editor.elements.conditional.GmConditionalPrimaryNode;

/**
 * This class represents the DiagramGraphic of a 'ConditionalNode' element.
 */
@objid ("e98a2e05-f138-4556-b723-39a8ac8e5eb7")
public class ConditionalNodeDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("a8164150-31bd-472a-b401-cd46607245db")
    public ConditionalNodeDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("26bb8714-7143-40ff-81cd-625d01c113b2")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        // Inner nodes
        GmConditionalPrimaryNode mainNode = (GmConditionalPrimaryNode) getPrimaryNode();
        
        GmCompositeNode clauseZone = mainNode.getCompositeFor(Clause.class);
        if (clauseZone != null) {
            return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, clauseZone.getVisibleChildren());
        }
        return Collections.emptyList();
    }

}
