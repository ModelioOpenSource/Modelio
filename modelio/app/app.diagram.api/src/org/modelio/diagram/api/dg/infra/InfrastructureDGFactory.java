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
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.IDGFactory;
import org.modelio.diagram.api.dg.common.LabelDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.common.genericlink.GmGenericLink;
import org.modelio.diagram.elements.common.genericnode.GmGenericNode;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.umlcommon.dependency.GmDependency;
import org.modelio.diagram.elements.umlcommon.diagramview.GmDiagramView;
import org.modelio.diagram.elements.umlcommon.namespaceuse.GmNamespaceUse;
import org.modelio.diagram.elements.umlcommon.note.GmNote;
import org.modelio.diagram.elements.umlcommon.usage.GmUsage;
import org.modelio.metamodel.diagrams.GraphDiagram;

/**
 * Diagram graphic factory for the Infrastructure metamodel fragment.
 */
@objid ("2e064e32-40dd-41ce-8587-e00052ca32e1")
public class InfrastructureDGFactory implements IDGFactory {
    @objid ("449353da-2ccd-410b-b6a2-f00d23e9350b")
    @Override
    public IDiagramLink getDiagramLink(IDiagramHandle diagramHandle, IGmLink gmLink) {
        // GmDependency
        if (gmLink instanceof GmDependency) {
            return new DependencyDG((DiagramHandle) diagramHandle, gmLink);
        }
        
        // GmImpactLink
        if (gmLink instanceof GmNamespaceUse) {
            return new ImpactLinkDG((DiagramHandle) diagramHandle, gmLink);
        }
        
        // GmUsage
        if (gmLink instanceof GmUsage) {
            return new UsageDG((DiagramHandle) diagramHandle, gmLink);
        }
        
        // GmGenericLink
        if (gmLink instanceof GmGenericLink) {
            return new GenericLinkDG((DiagramHandle) diagramHandle, gmLink);
        }
        return null;
    }

    @objid ("7c0a8f2b-42cc-438d-93fd-8c0e98503c31")
    @Override
    public IDiagramNode getDiagramNode(IDiagramHandle diagramHandle, GmNodeModel gmNodeModel) {
        // GmNote
        if (gmNodeModel instanceof GmNote) {
            return new NoteDG((DiagramHandle) diagramHandle, gmNodeModel);
        }
        
        // GmDiagramView
        if (gmNodeModel instanceof GmDiagramView) {
            return new DiagramHolderDG((DiagramHandle) diagramHandle, gmNodeModel);
        }
        
        // Generic labels
        if (gmNodeModel instanceof GmModelElementHeader) {
            return new LabelDG((DiagramHandle) diagramHandle, gmNodeModel);
        }
        
        // GmElementLabel
        if (gmNodeModel instanceof GmElementLabel) {
            return new LabelDG((DiagramHandle) diagramHandle, gmNodeModel);
        }
        
        // GmGenericNode
        if (gmNodeModel instanceof GmGenericNode) {
            return new GenericNodeDG((DiagramHandle) diagramHandle, gmNodeModel);
        }
        
        // GmGraphDiagram
        if (gmNodeModel.getRelatedElement() instanceof GraphDiagram) {
            return new GraphDiagramDG((DiagramHandle) diagramHandle, gmNodeModel);
        }
        return null;
    }

}
