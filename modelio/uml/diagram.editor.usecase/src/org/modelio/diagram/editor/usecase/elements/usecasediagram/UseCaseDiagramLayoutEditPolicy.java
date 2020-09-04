/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.usecase.elements.usecasediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.editor.usecase.elements.system.GmSystem;
import org.modelio.diagram.editor.usecase.elements.usecase.GmUseCase;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramEditLayoutPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * LayoutPolicy that is specific to UseCase Diagram background.
 * <p>
 * Deals with the System boundaries, which is optional.
 * </p>
 */
@objid ("5e88bfbe-55b7-11e2-877f-002564c97630")
public class UseCaseDiagramLayoutEditPolicy extends DiagramEditLayoutPolicy {
    @objid ("38034cc3-1b0f-4347-b35a-796fd44d2c4c")
    private static final Dimension USE_CASE_DEFAULT_SIZE = new Dimension(200, 75);

    @objid ("5e88bfc4-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(final CreateRequest request) {
        final ModelioCreationContext ctx = (ModelioCreationContext) request.getNewObject();
        if (UseCase.class == ctx.getJavaClass()) {
            GmUseCaseDiagram diagramGm = (GmUseCaseDiagram) getHost().getModel();
            MObject diagramContext = diagramGm.getRepresentedElement().getOrigin();
            MObject elementToUnmask = ctx.getElementToUnmask();
            boolean systemShouldBeUsed = diagramContext != null && diagramGm.getDisplayedStyle().getBoolean(GmUseCaseDiagramStyleKeys.SHOW_SYSTEM);
            if (systemShouldBeUsed && (elementToUnmask == null || isInSystem(diagramContext, elementToUnmask))) {
                // UseCase must be in the system boundaries.
                GmSystem systemGm = diagramGm.getSystem();
                if (systemGm.getLayoutData() == null || !systemGm.isVisible()) {
                    // System is not visible yet: compute its initial position.
                    CompoundCommand command = new CompoundCommand();
                    command.add(new InitializeSystemBoundariesCommand(systemGm, ((Rectangle) getConstraintFor(request)).setSize(-1, -1)));
                    Point locationSave = request.getLocation();
                    request.setLocation(new Point(0, 0));
                    command.add(super.getCreateCommand(request));
                    request.setLocation(locationSave);
                    return command.unwrap();
                } else {
                    // System is already visible: expand it if necessary.
                    GraphicalEditPart systemEditPart = (GraphicalEditPart) getHost().getViewer()
                            .getEditPartRegistry()
                            .get(systemGm);
                    GraphicalEditPart systemBodyEditPart = (GraphicalEditPart) getHost().getViewer()
                            .getEditPartRegistry()
                            .get(systemGm.getBody());
                    IFigure systemBodyFigure = systemBodyEditPart.getFigure();
                    Rectangle bodyBoundsOrig = systemBodyFigure.getBounds().getCopy();
                    systemBodyFigure.translateToAbsolute(bodyBoundsOrig);
                    Rectangle bodyBoundsResized = bodyBoundsOrig.getCopy();
                    bodyBoundsResized.union(request.getLocation());
                    if (request.getSize() != null) {
                        bodyBoundsResized.union(request.getLocation().getTranslated(request.getSize()));
                    } else {
                        bodyBoundsResized.union(request.getLocation()
                                .getTranslated(UseCaseDiagramLayoutEditPolicy.USE_CASE_DEFAULT_SIZE));
                    }
                    Point moveDelta = bodyBoundsResized.getLocation()
                            .getTranslated(bodyBoundsOrig.getLocation()
                                    .getNegated());
                    Dimension sizeDelta = bodyBoundsResized.getSize().getShrinked(bodyBoundsOrig.getSize());
                    ChangeBoundsRequest sizeRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
                    sizeRequest.setEditParts(systemEditPart);
                    sizeRequest.setMoveDelta(moveDelta);
                    sizeRequest.setSizeDelta(sizeDelta);
                    CompoundCommand command = new CompoundCommand();
                    command.add(systemEditPart.getCommand(sizeRequest));
                    command.add(systemBodyEditPart.getTargetEditPart(request).getCommand(request));
                    return command.unwrap();
                }
            }
        }
        return super.getCreateCommand(request);
    }

    @objid ("5e88bfcb-55b7-11e2-877f-002564c97630")
    private boolean isInSystem(final MObject systemElement, final MObject theUseCase) {
        return theUseCase.isValid() && theUseCase.getCompositionOwner().equals(systemElement);
    }

    @objid ("5e88bfd7-55b7-11e2-877f-002564c97630")
    @Override
    protected Command createAddCommand(final ChangeBoundsRequest request, final EditPart child, final Object constraint) {
        Object childModel = child.getModel();
        if (childModel instanceof GmUseCase) {
            GmUseCase useCaseGm = (GmUseCase) childModel;
            MObject useCaseElement = useCaseGm.getRelatedElement();
            GmUseCaseDiagram diagramGm = (GmUseCaseDiagram) getHost().getModel();
            MObject diagramContextElement = diagramGm.getRepresentedElement().getOrigin();
            boolean systemShouldBeUsed = diagramContextElement != null && diagramGm.getDisplayedStyle().getBoolean(GmUseCaseDiagramStyleKeys.SHOW_SYSTEM);
            if (useCaseElement != null && systemShouldBeUsed && isInSystem(diagramContextElement, useCaseElement)) {
                // UseCase must be in the system boundaries.
                GmSystem systemGm = diagramGm.getSystem();
                if (systemGm.getLayoutData() == null || !systemGm.isVisible()) {
                    // System is not visible yet: compute its initial position.
                    CompoundCommand command = new CompoundCommand();
                    Rectangle systemBounds = ((Rectangle) constraint).getCopy();
                    systemBounds.setSize(-1, -1);
                    command.add(new InitializeSystemBoundariesCommand(systemGm, systemBounds));
                    Point locationSave = request.getLocation();
                    request.setLocation(new Point(0, 0));
                    command.add(super.createAddCommand(request, child, constraint));
                    request.setLocation(locationSave);
                    return command.unwrap();
                } else {
                    // System is already visible: let it handle everything!.
                    ChangeBoundsRequest moveRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE_CHILDREN);
                    moveRequest.setEditParts(child);
                    moveRequest.setMoveDelta(request.getMoveDelta());
                    moveRequest.setResizeDirection(request.getResizeDirection());
                    GraphicalEditPart systemBodyEditPart = (GraphicalEditPart) getHost().getViewer()
                            .getEditPartRegistry()
                            .get(systemGm.getBody());
                    return systemBodyEditPart.getCommand(moveRequest);
                }
            }
        }
        return super.createAddCommand(request, child, constraint);
    }

    @objid ("5e8a463a-55b7-11e2-877f-002564c97630")
    private static class InitializeSystemBoundariesCommand extends Command {
        @objid ("7b6756a4-5eff-11e2-b9cc-001ec947c8cc")
        private GmSystem system;

        @objid ("9a41d7f4-8651-4302-9b9a-821ab8b1a629")
        private Object layoutdata;

        @objid ("5e8a463e-55b7-11e2-877f-002564c97630")
        public InitializeSystemBoundariesCommand(final GmSystem system, final Object layoutdata) {
            this.system = system;
            this.layoutdata = layoutdata;
        }

        @objid ("5e8a4644-55b7-11e2-877f-002564c97630")
        @Override
        public void execute() {
            this.system.setLayoutData(this.layoutdata);
        }

    }

}
