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

package org.modelio.diagram.editor.sequence.elements.interactionuse.gate;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;

/**
 * EditPart for the main node of a Gate on InteractionUse.
 * 
 * @author fpoyer
 */
@objid ("d912cb7b-55b6-11e2-877f-002564c97630")
public class GateOnInteractionUsePrimaryNodeEditPart extends AbstractNodeEditPart {
    @objid ("d912cb7f-55b6-11e2-877f-002564c97630")
    private FigureUpdater figureUpdater = new FigureUpdater(this);

    @objid ("d91451d9-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(final Request request) {
        Command command = super.getCommand(request);
        if (RequestConstants.REQ_RESIZE.equals(request.getType()) &&
                (((ChangeBoundsRequest) request).getMoveDelta().y != 0)) {
            Command updateModelCommand = new Command() {
                @Override
                public void execute() {
                    GmGateOnInteractionUsePrimaryNode model = (GmGateOnInteractionUsePrimaryNode) GateOnInteractionUsePrimaryNodeEditPart.this.getModel();
                    Gate gate = (Gate) model.getRelatedElement();
                    int newTime = GateOnInteractionUsePrimaryNodeEditPart.this.getFigure()
                            .getBounds()
                            .getCenter().y +
                            ((ChangeBoundsRequest) request).getMoveDelta().y;
        
                    if (newTime != gate.getLineNumber()) {
                        gate.setLineNumber(newTime);
                    }
                }
            };
            command = updateModelCommand.chain(command);
        }
        return command;
    }

    @objid ("d91451e0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("d91451e5-55b6-11e2-877f-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LAYOUTDATA)) {
            // Add the post layout listener (and make sure it is only once in the list of listeners).
            this.getFigure().removeLayoutListener(this.figureUpdater);
            this.getFigure().addLayoutListener(this.figureUpdater);
            getFigure().invalidate();
        }
        super.propertyChange(evt);
    }

    @objid ("d91451ea-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("d91451ed-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        RectangularFigure fig = new RectangularFigure();
        // style independent
        fig.setOpaque(true);
        fig.setSize(12, 12);
        fig.setMinimumSize(new Dimension(12, 12));
        // Style dependent
        IStyle style = ((GmAbstractObject) getModel()).getDisplayedStyle();
        refreshFromStyle(fig, style);
        return fig;
    }

    @objid ("d91451f2-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        getFigure().getParent().setConstraint(getFigure(), ((GmAbstractObject) getModel()).getLayoutData());
    }

    @objid ("d91451f5-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        if (aFigure instanceof RectangularFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        }
    }

}
