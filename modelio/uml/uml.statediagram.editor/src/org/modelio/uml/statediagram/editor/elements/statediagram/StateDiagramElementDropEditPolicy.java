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
package org.modelio.uml.statediagram.editor.elements.statediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.UnmaskLinkCommand;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.anchors.IAnchorRefResolver;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Extension of {@link DiagramElementDropEditPolicy} handling InternalTransition as nodes instead of links.
 */
@objid ("d192e5e2-f75c-4ebc-bf2d-e24448ac9b25")
class StateDiagramElementDropEditPolicy extends DiagramElementDropEditPolicy {
    @objid ("7271cf3c-4ca8-4fce-91b3-d2a972b9235a")
    @Override
    protected Command getSmartDropCommand(ModelElementDropRequest request) {
        final CompoundCommand command = new CompoundCommand();
        
        Point dropLocation = request.getDropLocation();
        
        for (final MObject toUnmask : request.getDroppedElements()) {
            if (toUnmask instanceof InternalTransition) {
                // Unmask InternalTransition as nodes instead of links
                command.add(createDropCommandForElement(dropLocation, toUnmask));
            } else if (toUnmask != null) {
                // Deal with 'non-smart' drops
                command.add(createSubCommand(request, dropLocation, toUnmask));
            }
            // Introduce some offset, so that all elements are not totally
            // on top of each other.
            dropLocation = dropLocation.getTranslated(20, 20);
        }
        return command;
    }

    /**
     * Create a standard unmask request
     */
    @objid ("53abbed5-2649-49d6-b68f-e6cb512834e9")
    private Command createSubCommand(final ModelElementDropRequest request, final Point dropLocation, final MObject toUnmask) {
        final ModelElementDropRequest subReq = new ModelElementDropRequest();
        subReq.setDroppedElements(new MObject[] { toUnmask });
        subReq.setExtendedData(request.getExtendedData());
        subReq.setLocation(dropLocation);
        subReq.setSmart(request.isSmart());
        return getDropCommand(subReq);
    }

    @objid ("c1575c9b-b7ae-4557-998e-97a609c6abfb")
    @Override
    protected UnmaskLinkCommand createUnmaskCommandForLink(Point dropLocation, GmLink link) {
        UnmaskLinkCommand unmaskCommand = super.createUnmaskCommandForLink(dropLocation, link);
        unmaskCommand.setSourceAnchorResolver(new StateSourceAnchorRefResolver());
        unmaskCommand.setTargetAnchorResolver(new StateTargetAnchorRefResolver());
        return unmaskCommand;
    }

    @objid ("a9ce9fe6-d4a6-4033-9ba6-d6132423c020")
    @Override
    public Command createDropCommandForLink(final Point dropLocation, final IGmLink link) {
        return new UnmaskLinkCommand(link, (AbstractDiagramEditPart) getHost(), dropLocation, new StateSourceAnchorRefResolver(), new StateTargetAnchorRefResolver());
    }

    /**
     * Get the default source anchor reference point for link creation request in State diagrams.
     */
    @objid ("599a0404-e301-4896-b45c-472a45068007")
    private static class StateSourceAnchorRefResolver implements IAnchorRefResolver {
        @objid ("6c1a1c99-92c7-4d11-9823-541d187665c7")
        @Override
        public Point resolveAnchorRef(AbstractGraphicalEditPart sourceEditPart, AbstractGraphicalEditPart targetEditPart, MObject linkElement) {
            IFigure sourceFig = sourceEditPart.getFigure();
            sourceFig.getUpdateManager().performValidation();
            Rectangle sourceBounds = sourceFig.getBounds();
            
            IFigure targetFig = targetEditPart.getFigure();
            targetFig.getUpdateManager().performValidation();
            Rectangle targetBounds = targetFig.getBounds();
            
            final Point ret;
            if (sourceBounds.right() > targetBounds.x() && sourceBounds.y() > targetBounds.bottom()) {
                ret = sourceBounds.getTop();
            } else if (sourceBounds.right() > targetBounds.x() && sourceBounds.bottom() < targetBounds.y()) {
                ret = sourceBounds.getBottom();
            } else {
                ret = sourceBounds.getRight();
            }
            
            sourceFig.translateToAbsolute(ret);
            return ret;
        }

    }

    /**
     * Get the default target anchor reference point for link creation request in State diagrams.
     */
    @objid ("8daea993-8d20-4e76-b295-91ef32c1f4c8")
    private static class StateTargetAnchorRefResolver implements IAnchorRefResolver {
        @objid ("dff0eaed-f936-434b-8ac1-3fda3fb29e38")
        @Override
        public Point resolveAnchorRef(AbstractGraphicalEditPart sourceEditPart, AbstractGraphicalEditPart targetEditPart, MObject linkElement) {
            IFigure sourceFig = sourceEditPart.getFigure();
            sourceFig.getUpdateManager().performValidation();
            Rectangle sourceBounds = sourceFig.getBounds();
            
            IFigure targetFig = targetEditPart.getFigure();
            targetFig.getUpdateManager().performValidation();
            Rectangle targetBounds = targetFig.getBounds();
            
            final Point ret;
            if (sourceBounds.right() > targetBounds.x() && sourceBounds.y() > targetBounds.bottom()) {
                ret = targetBounds.getBottom();
            } else if (sourceBounds.right() > targetBounds.x() && sourceBounds.bottom() < targetBounds.y()) {
                ret = targetBounds.getTop();
            } else {
                ret = targetBounds.getLeft();
            }
            
            targetFig.translateToAbsolute(ret);
            return ret;
        }

    }

}
