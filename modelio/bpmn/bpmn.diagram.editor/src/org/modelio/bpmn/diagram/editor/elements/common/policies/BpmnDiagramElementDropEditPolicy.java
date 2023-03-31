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
package org.modelio.bpmn.diagram.editor.elements.common.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskLinkCommand;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.anchors.IAnchorRefResolver;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Extension of {@link DiagramElementDropEditPolicy} setting anchor ref resolvers for links.
 */
@objid ("534c32a0-c711-43e3-8720-6761bdf8d179")
public class BpmnDiagramElementDropEditPolicy extends DiagramElementDropEditPolicy {
    @objid ("3d04258c-0903-43d7-8e80-1f4ffd553af5")
    @Override
    public Command createDropCommandForLink(final Point dropLocation, final IGmLink link) {
        return new UnmaskLinkCommand(link, (AbstractDiagramEditPart) getHost(), dropLocation, new BpmnSourceAnchorRefResolver(), new BpmnTargetAnchorRefResolver());
    }

    @objid ("fbc13eae-a9f1-4e3b-a711-79e8348d4a12")
    @Override
    protected UnmaskLinkCommand createUnmaskCommandForLink(Point dropLocation, GmLink link) {
        UnmaskLinkCommand unmaskCommand = super.createUnmaskCommandForLink(dropLocation, link);
        unmaskCommand.setSourceAnchorResolver(new BpmnSourceAnchorRefResolver());
        unmaskCommand.setTargetAnchorResolver(new BpmnTargetAnchorRefResolver());
        return unmaskCommand;
    }

    /**
     * Get the default source anchor reference point for link creation request in BPMN diagrams.
     */
    @objid ("a74fc481-1da0-4b56-a8d8-a45af88a331c")
    public static class BpmnSourceAnchorRefResolver implements IAnchorRefResolver {
        @objid ("90272231-0ae2-48eb-922c-d9e673153a1e")
        @Override
        public Point resolveAnchorRef(AbstractGraphicalEditPart sourceEditPart, AbstractGraphicalEditPart targetEditPart, MObject linkElement) {
            IFigure sourceFig = sourceEditPart.getFigure();
            IFigure targetFig = targetEditPart.getFigure();
            
            sourceFig.getUpdateManager().performValidation();
            
            Rectangle sourceBounds = sourceFig.getBounds().getCopy();
            Rectangle targetBounds = targetFig.getBounds().getCopy();
            
            sourceFig.translateToAbsolute(sourceBounds);
            targetFig.translateToAbsolute(targetBounds);
            
            final Point ret;
            if (linkElement instanceof BpmnMessageFlow) {
                ret = getMessageFlowAnchorRef(sourceBounds, targetBounds);
            } else {
                ret = getDefaultAnchorRef(sourceBounds, targetBounds);
            }
            return ret;
        }

        /**
         * Default strategy is to start on right, top or bottom.
         */
        @objid ("cca4422d-727d-4916-bf01-00da12577110")
        private Point getDefaultAnchorRef(Rectangle sourceBounds, Rectangle targetBounds) {
            final Point ret;
            if (sourceBounds.right() > targetBounds.x() && sourceBounds.y() > targetBounds.bottom()) {
                ret = sourceBounds.getTop();
            } else if (sourceBounds.right() > targetBounds.x() && sourceBounds.bottom() < targetBounds.y()) {
                ret = sourceBounds.getBottom();
            } else {
                ret = sourceBounds.getRight();
            }
            return ret;
        }

        /**
         * Default strategy is to start preferably on the left/right side, or top/bottom otherwise.
         */
        @objid ("b1e334d3-e79c-41b5-9434-190a578e5910")
        private Point getMessageFlowAnchorRef(Rectangle sourceBounds, Rectangle targetBounds) {
            final Point ret;
            if (sourceBounds.x() > targetBounds.right()) {
                ret = sourceBounds.getLeft();
            } else if (sourceBounds.right() < targetBounds.x()) {
                ret = sourceBounds.getRight();
            } else if (sourceBounds.y() > targetBounds.bottom()) {
                ret = sourceBounds.getTop();
            } else if (sourceBounds.bottom() < targetBounds.y()) {
                ret = sourceBounds.getBottom();
            } else {
                // Nodes are overlapping, let the anchor provider choose something
                ret = sourceBounds.getCenter();
            }
            return ret;
        }

    }

    /**
     * Get the default target anchor reference point for link creation request in BPMN diagrams.
     */
    @objid ("6df00f2c-5226-4d27-98f2-1cddcf311094")
    public static class BpmnTargetAnchorRefResolver implements IAnchorRefResolver {
        @objid ("eecf2b07-40da-4cfe-b37b-f01a63e11498")
        @Override
        public Point resolveAnchorRef(AbstractGraphicalEditPart sourceEditPart, AbstractGraphicalEditPart targetEditPart, MObject linkElement) {
            IFigure sourceFig = sourceEditPart.getFigure();
            IFigure targetFig = targetEditPart.getFigure();
            
            sourceFig.getUpdateManager().performValidation();
            
            Rectangle sourceBounds = sourceFig.getBounds().getCopy();
            Rectangle targetBounds = targetFig.getBounds().getCopy();
            sourceFig.translateToAbsolute(sourceBounds);
            targetFig.translateToAbsolute(targetBounds);
            
            final Point ret;
            if (linkElement instanceof BpmnMessageFlow) {
                ret = getMessageFlowAnchorRef(sourceBounds, targetBounds);
            } else {
                ret = getDefaultAnchorRef(sourceBounds, targetBounds);
            }
            return ret;
        }

        /**
         * Default strategy is to end on left, top or bottom.
         */
        @objid ("5f8bafb8-3c14-4447-99fe-3d2091497bc0")
        private Point getDefaultAnchorRef(Rectangle sourceBounds, Rectangle targetBounds) {
            final Point ret;
            if (sourceBounds.right() > targetBounds.x() && sourceBounds.y() > targetBounds.bottom()) {
                ret = targetBounds.getBottom();
            } else if (sourceBounds.right() > targetBounds.x() && sourceBounds.bottom() < targetBounds.y()) {
                ret = targetBounds.getTop();
            } else {
                ret = targetBounds.getLeft();
            }
            return ret;
        }

        /**
         * Default strategy is to end preferably on the left/right side, or top/bottom otherwise.
         */
        @objid ("0b796aa5-8243-4c38-aa0a-52bdcb27982e")
        private Point getMessageFlowAnchorRef(Rectangle sourceBounds, Rectangle targetBounds) {
            final Point ret;
            if (sourceBounds.x() > targetBounds.right()) {
                ret = targetBounds.getRight();
            } else if (sourceBounds.right() < targetBounds.x()) {
                ret = targetBounds.getLeft();
            } else if (sourceBounds.y() > targetBounds.bottom()) {
                ret = targetBounds.getBottom();
            } else if (sourceBounds.bottom() < targetBounds.y()) {
                ret = targetBounds.getTop();
            } else {
                // Nodes are overlapping, let the anchor provider choose something
                ret = targetBounds.getCenter();
            }
            return ret;
        }

    }

}
