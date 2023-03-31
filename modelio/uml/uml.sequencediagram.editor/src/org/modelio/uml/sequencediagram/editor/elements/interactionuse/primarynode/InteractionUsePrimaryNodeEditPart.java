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
package org.modelio.uml.sequencediagram.editor.elements.interactionuse.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.uml.sequencediagram.editor.elements.combinedfragment.primarynode.OperatorEditPart;
import org.modelio.uml.sequencediagram.editor.elements.common.node.AbstractSequenceNodeEditPart;

/**
 * EditPart for primary node of InteractionUse.
 * 
 * @author fpoyer
 */
@objid ("d9282841-55b6-11e2-877f-002564c97630")
public class InteractionUsePrimaryNodeEditPart extends AbstractSequenceNodeEditPart {
    @objid ("d929ae9c-55b6-11e2-877f-002564c97630")
    private FigureUpdater figureUpdater = new FigureUpdater(this);

    @objid ("d929ae9d-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(final Request request) {
        Command command = super.getCommand(request);
        if (RequestConstants.REQ_RESIZE.equals(request.getType()) &&
                (((ChangeBoundsRequest) request).getMoveDelta().y != 0 || ((ChangeBoundsRequest) request).getSizeDelta().height != 0)) {
            // FIXME: test if this is compatible with the gates, if not return a non executable command instead
            Command updateModelCommand = new Command() {
                @Override
                public void execute() {
                    GmInteractionUsePrimaryNode model = (GmInteractionUsePrimaryNode) InteractionUsePrimaryNodeEditPart.this.getModel();
                    Rectangle bounds = InteractionUsePrimaryNodeEditPart.this.getFigure().getBounds();
                    int startTime = bounds.y + ((ChangeBoundsRequest) request).getMoveDelta().y;
                    int finishTime = bounds.bottom() +
                            ((ChangeBoundsRequest) request).getMoveDelta().y +
                            ((ChangeBoundsRequest) request).getSizeDelta().height;
        
                    InteractionUse interactionUse = (InteractionUse) model.getRelatedElement();
                    if (startTime != interactionUse.getLineNumber()) {
                        interactionUse.setLineNumber(startTime);
                    }
                    if (finishTime != interactionUse.getEndLineNumber()) {
                        interactionUse.setEndLineNumber(finishTime);
                    }
        
                }
            };
            command = updateModelCommand.chain(command);
        }
        return command;
    }

    @objid ("d929aea4-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("d929aeae-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        if (childEditPart instanceof OperatorEditPart) {
            getContentPane().add(child, BorderLayout.TOP, index);
        } else {
            getContentPane().add(child, BorderLayout.CENTER, index);
        }
        
    }

    @objid ("d929aeb5-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        RectangularFigure fig = new RectangularFigure();
        // Set style independent properties.
        fig.setLayoutManager(new BorderLayout());
        fig.setOpaque(true);
        // Set style dependent properties.
        IStyle style = ((GmAbstractObject) getModel()).getDisplayedStyle();
        refreshFromStyle(fig, style);
        return fig;
    }

    @objid ("d929aeba-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        getFigure().getParent().setConstraint(getFigure(), ((GmAbstractObject) getModel()).getLayoutData());
        
    }

    @objid ("d929aebd-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
    }

}
