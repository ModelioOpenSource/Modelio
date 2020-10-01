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

package org.modelio.uml.sequencediagram.editor.elements.interactionoperand.primarynode;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
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
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;

/**
 * EditPart for primary node of interation operand.
 * 
 * @author fpoyer
 */
@objid ("d90cb0d2-55b6-11e2-877f-002564c97630")
public class InteractionOperandPrimaryNodeEditPart extends AbstractNodeEditPart {
    @objid ("d90cb0d6-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        RectangularFigure fig = new InteractionOperandPrimaryNodeFigure();
        fig.setOpaque(false);
        // fig.setBackgroundColor(ColorConstants.green);
        fig.setLayoutManager(new BorderLayout());
        fig.setLinePattern(LinePattern.LINE_DASH);
        IFigure placeholder = new Figure();
        placeholder.setOpaque(false);
        // placeholder.setBackgroundColor(ColorConstants.lightBlue);
        placeholder.setLayoutManager(new BorderLayout());
        fig.add(placeholder, BorderLayout.CENTER);
        IStyle style = ((GmAbstractObject) getModel()).getDisplayedStyle();
        refreshFromStyle(fig, style);
        return fig;
    }

    @objid ("d90cb0db-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // Allow links creation
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy(false));
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("d90cb0de-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        getFigure().getParent().setConstraint(getFigure(), ((GmAbstractObject) getModel()).getLayoutData());
    }

    @objid ("d90cb0e1-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        // Guard label.
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        ((GmAbstractObject) childEditPart.getModel()).setLayoutData(BorderLayout.TOP);
        getContentPane().add(child, BorderLayout.TOP, index);
    }

    @objid ("d90cb0e8-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("d90cb0ed-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(final Request request) {
        Command command = super.getCommand(request);
        if (RequestConstants.REQ_RESIZE.equals(request.getType())) {
            Command updateModelCommand = new UpdateInteractionOperandHeightCommand((ChangeBoundsRequest) request);
            
            command = updateModelCommand.chain(command);
        }
        return command;
    }

    @objid ("f16293c7-8d50-4a02-977b-c6c39a12a8f3")
    private final class UpdateInteractionOperandHeightCommand extends Command {
        @objid ("948f5620-4a00-4ec4-b8aa-ce26b33adf74")
        private final ChangeBoundsRequest request;

        @objid ("25840fe0-fa65-4698-bbc0-257435504cc4")
        private UpdateInteractionOperandHeightCommand(ChangeBoundsRequest request) {
            this.request = request;
        }

        @objid ("0cd3c018-4309-43c0-b435-d58b47b99e82")
        @Override
        public void execute() {
            IFigure intOpPrimNodeFigure = getFigure();
            GmInteractionOperandPrimaryNode model = (GmInteractionOperandPrimaryNode) getModel();
            
            if( ! model.getDiagram().getModelManager().getModelingSession().getTransactionSupport().hasCurrentTransaction()) {
                return;
            }
            
            InteractionOperand operand = (InteractionOperand) model.getRelatedElement();
            int newStartTime = intOpPrimNodeFigure.getBounds().y + this.request.getMoveDelta().y;
            int newEndTime = intOpPrimNodeFigure
                    .getBounds()
                    .bottom() +
                    this.request.getMoveDelta().y +
                    this.request.getSizeDelta().height;
                  
            CombinedFragment combinedFragment = operand.getOwnerFragment();
            List<InteractionOperand> allOperands = combinedFragment.getOperand();
            int index = allOperands.indexOf(operand);
            if (newStartTime != operand.getLineNumber()) {
                // Move lineNumber of this operand AND endLineNumber of previous operand (or line number of containing fragment if no previous)
                if (index > 0) {
                    InteractionOperand previousOperand = allOperands.get(index - 1);
                    previousOperand.setEndLineNumber(newStartTime - 1);
                } else {
                    combinedFragment.setLineNumber(newStartTime);
                }
                operand.setLineNumber(newStartTime);
            }
            if (newEndTime != operand.getEndLineNumber()) {
                // Move endLineNumber of this operand AND lineNumber of nextOperand in same containing fragment if any).
                if (index + 1 < allOperands.size()) {
                    InteractionOperand nextOperand = allOperands.get(index + 1);
                    nextOperand.setLineNumber(newEndTime + 1);
                }
                operand.setEndLineNumber(newEndTime);
            }
        }

    }

}
