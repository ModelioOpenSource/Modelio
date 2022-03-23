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
@objid ("197d9172-c34a-4de4-ae2d-a2827b3a8a01")
public class BpmnDiagramElementDropEditPolicy extends DiagramElementDropEditPolicy {
    @objid ("88054654-c176-41af-b13f-6f6bb82994ed")
    @Override
    public Command createDropCommandForLink(final Point dropLocation, final IGmLink link) {
        return new UnmaskLinkCommand(link, (AbstractDiagramEditPart) getHost(), dropLocation, new BpmnSourceAnchorRefResolver(), new BpmnTargetAnchorRefResolver());
    }

    @objid ("6693c841-4eb0-4728-b90e-fec6d74359b4")
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
    @objid ("3acf2d4d-9003-4e2f-8fb0-3be3c6fbdde7")
    public static class BpmnSourceAnchorRefResolver implements IAnchorRefResolver {
        @objid ("e6f5d467-6b0f-4a96-a655-e8bec7bbae8d")
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
        @objid ("62b3eb15-36c3-4077-8a52-9fc77e28e9fc")
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
        @objid ("0817c0a8-11a0-49b0-8738-2764aaf5a995")
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
    @objid ("552fa964-1bd9-4136-9cac-1657eee7368a")
    public static class BpmnTargetAnchorRefResolver implements IAnchorRefResolver {
        @objid ("f278cb28-6b6e-4316-b1ab-29c38cbc91bd")
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
        @objid ("c8e58d99-e403-4887-acc2-c7a70e507861")
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
        @objid ("d8ea98b7-0eaf-4502-8ac3-421149cc1bf3")
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
