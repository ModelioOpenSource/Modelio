/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.CompoundBorder;
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
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.AutoFitToContentEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;

/**
 * EditPart for primary node of InteractionUse.
 * 
 * @author fpoyer
 */
@objid ("d8c98d3a-55b6-11e2-877f-002564c97630")
public class CombinedFragmentPrimaryNodeEditPart extends AbstractNodeEditPart {
    @objid ("be98c9d4-d1f4-401b-91fc-30d9be86a83a")
    private FigureUpdater figureUpdater = new FigureUpdater(this);

    @objid ("d8c98d3f-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(final Request request) {
        Command command = super.getCommand(request);
        if (RequestConstants.REQ_RESIZE.equals(request.getType())) {
            Command updateModelCommand = new Command() {
                @Override
                public void execute() {
                    GmCombinedFragmentPrimaryNode model = (GmCombinedFragmentPrimaryNode) CombinedFragmentPrimaryNodeEditPart.this.getModel();
                    CombinedFragment combinedFragment = (CombinedFragment) model.getRelatedElement();
                    if (combinedFragment.isValid()) {
                        int newTime = ((IFigure) CombinedFragmentPrimaryNodeEditPart.this.getFigure()
                                .getChildren()
                                .get(1)).getBounds().y +
                                ((ChangeBoundsRequest) request).getMoveDelta().y;
        
                        int newSize = ((IFigure) CombinedFragmentPrimaryNodeEditPart.this.getFigure()
                                .getChildren()
                                .get(1)).getBounds().height +
                                ((ChangeBoundsRequest) request).getSizeDelta().height;
                        int actualFragmentSize = 0;
                        if (combinedFragment.getOperand().size() > 0) {
                            for (InteractionOperand operand : combinedFragment.getOperand()) {
                                ++actualFragmentSize;
                                actualFragmentSize += operand.getEndLineNumber() - operand.getLineNumber();
                            }
                            --actualFragmentSize;
        
                        }
        
                        // Make sure a transaction is active before fixing invalid line numbers in the model
                        boolean isModelModifiable = model.getDiagram().getModelManager().getModelingSession().getTransactionSupport().hasCurrentTransaction();
                        if (isModelModifiable) {
                            if (newTime != combinedFragment.getLineNumber() || newSize != actualFragmentSize) {
                                int lineNumber = newTime;
                                if (combinedFragment.getLineNumber() != lineNumber) {
                                    combinedFragment.setLineNumber(lineNumber);
                                }
                                for (InteractionOperand operand : combinedFragment.getOperand()) {
                                    int size = operand.getEndLineNumber() - operand.getLineNumber();
                                    double ratio = (double) size / (double) actualFragmentSize;
                                    if (operand.getLineNumber() != lineNumber) {
                                        operand.setLineNumber(lineNumber);
                                    }
                                    lineNumber += (ratio * newSize);
                                    if (operand.getEndLineNumber() != lineNumber) {
                                        operand.setEndLineNumber(lineNumber);
                                    }
                                    ++lineNumber;
                                }
                            }
                        }
                    }
                }
            };
            command = updateModelCommand.chain(command);
        }
        return command;
    }

    @objid ("d8c98d46-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("d8c98d4b-55b6-11e2-877f-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LAYOUTDATA)) {
            // Add the post layout listener (and make sure it is only once in the list of listeners).
            getFigure().removeLayoutListener(this.figureUpdater);
            getFigure().addLayoutListener(this.figureUpdater);
            getFigure().invalidate();
        }
        super.propertyChange(evt);
    }

    @objid ("d8c98d50-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure fig = new CombinedFragmentPrimaryNodeFigure();
        fig.setOpaque(false);
        // Remove the margin border, only keep the line border.
        fig.setBorder(((CompoundBorder) fig.getBorder()).getOuterBorder());
        // Define properties not specific to style.
        fig.setLayoutManager(new BorderLayout());
        
        // Define properties specific to style
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("d8c98d55-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        getFigure().getParent().setConstraint(getFigure(), ((GmAbstractObject) getModel()).getLayoutData());
    }

    @objid ("d8c98d58-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        if (childEditPart.getModel() instanceof GmOperatorLabel) {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getContentPane().add(child, BorderLayout.TOP, index);
        } else {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getContentPane().add(child, BorderLayout.CENTER, index);
        }
    }

    @objid ("d8c98d5f-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy(false));
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new AutoFitToContentEditPolicy(true, true));
    }

}
