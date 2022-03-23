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
package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Convenience abstract class providing some defaults to help the implementation of {@link IDiagramElementDropEditPolicyExtension}
 */
@objid ("3090ea47-071a-4dd8-aaf6-16691aaa453a")
public abstract class AbstractDiagramElementDropEditPolicyExtension implements IDiagramElementDropEditPolicyExtension {
    /**
     * Default implementation of {@link IDiagramElementDropEditPolicyExtension#getUnmaskCommandFor(DiagramElementDropEditPolicy, MObject, Point)} that will unmask droppedElement either as a node hierarchy or as a link.
     */
    @objid ("02307174-9fda-4178-aa63-cda0a7a2d978")
    @Override
    public Command getUnmaskCommandFor(DiagramElementDropEditPolicy dropPolicy, MObject droppedElement, Point dropLocation) {
        final IGmLink gmLink = ((IGmDiagram) dropPolicy.getHost().getModel()).unmaskLink(droppedElement);
        Command cmd;
        if (gmLink != null) {
            cmd = dropPolicy.createDropCommandForLink(dropLocation, gmLink);
        } else {
            cmd = dropPolicy.createDropCommandForNodeHierarchy(dropLocation, droppedElement);
        }
        return cmd;
    }

    /**
     * By default always accept CMS node candidates in the hierarchy
     */
    @objid ("49945b96-34ea-45e1-a1be-534874e661ee")
    @Override
    public boolean isToBeAddedToHierarchy(IGmDiagram context, Deque<MObject> hierarchy, MObject candidate) {
        final MObject lastInHierarchy = hierarchy.peek();
        return lastInHierarchy == null || !lastInHierarchy.getMClass().isCmsNode();
    }

    /**
     * This default implementation simply follows the model composition graph.
     */
    @objid ("e155d170-e03c-4b17-bc78-57631bc87324")
    @Override
    public MObject getParentInGraphicalHierarchy(IGmDiagram context, MObject element) {
        return element.getCompositionOwner();
    }

    /**
     * This default implementation does not unmask anything.
     */
    @objid ("034a2262-0cf2-48f4-8fa4-09a40c7f28bc")
    @Override
    public boolean canUnmask(DiagramElementDropEditPolicy dropPolicy, MObject candidate) {
        return false;
    }

}
