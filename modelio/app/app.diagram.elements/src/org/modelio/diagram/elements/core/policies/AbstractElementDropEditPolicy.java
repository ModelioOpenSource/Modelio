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
package org.modelio.diagram.elements.core.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.figures.FigureUtilities2.HighlightType;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;

/**
 * A default drop policy implementation that can process ModelElementDropRequest
 * requests.
 */
@objid ("b8d393d6-e143-4aeb-8682-d50a71cde7d1")
public abstract class AbstractElementDropEditPolicy extends GraphicalEditPolicy {
    @objid ("e5d04997-0a8b-4104-8388-388cfd2685ca")
    protected HighlightType highlightType;

    @objid ("34115f81-ac98-4875-abd9-6fb921bd349b")
    protected IFigure highlight;

    /**
     * Redefined to handle {@link ModelElementDropRequest} request by calling
     * {@link #getDropCommand(ModelElementDropRequest)} or {@link #getSmartDropCommand(ModelElementDropRequest)}
     * in case of smart interaction.
     */
    @objid ("94ebe443-e6ee-4f59-ae9c-c15e1d4d633a")
    @Override
    public final Command getCommand(Request request) {
        if (request.getType().equals(ModelElementDropRequest.TYPE)) {
            // Start by looking for a "smart" behaviour.
            final Command command = getSmartDropCommand((ModelElementDropRequest) request);
            if (command == null) {
                // If none found, look for a "regular" behaviour.
                return getDropCommand((ModelElementDropRequest) request);
            }
            return command;
        }
        return null;
    }

    /**
     * Redefined to handle {@link ModelElementDropRequest} request by calling
     * {@link #getDropTargetEditPart(ModelElementDropRequest)}. <br>
     */
    @objid ("28f9b442-6c4a-4a37-8ad2-5d7f011aea59")
    @Override
    public final EditPart getTargetEditPart(Request request) {
        if (request.getType().equals(ModelElementDropRequest.TYPE)) {
            return getDropTargetEditPart((ModelElementDropRequest) request);
        }
        // else
        return null;
    }

    @objid ("5da9c317-4c14-4bae-bc23-3f924fb4ff9f")
    @Override
    public void showTargetFeedback(Request request) {
        if (request.getType().equals(ModelElementDropRequest.TYPE)) {
            // compute highlight type
            final Command c = getCommand(request);
            HighlightType newHighlightType = FigureUtilities2.HighlightType.INFO;
            if (c == null) {
                newHighlightType = FigureUtilities2.HighlightType.ERROR;
            } else if (c.canExecute()) {
                newHighlightType = FigureUtilities2.HighlightType.SUCCESS;
            } else {
                newHighlightType = FigureUtilities2.HighlightType.WARNING;
            }
        
            if (newHighlightType != this.highlightType) {
                // configure the highlight figure
                this.highlightType = newHighlightType;
        
                // create a highlight figure if it does not exist
                if (this.highlight == null) {
                    // create a highlight figure
                    this.highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(), getHostFigure(),
                            this.highlightType);
                    // add the highlight figure to the feedback layer
                    getFeedbackLayer().add(this.highlight);
                }
                FigureUtilities2.updateHighlightType(this.highlight, this.highlightType);
            }
        
        }
        super.showTargetFeedback(request);
        
    }

    @objid ("8e320550-5798-4440-beb6-85130024e1cb")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (request.getType().equals(ModelElementDropRequest.TYPE)) {
            removeFeedback();
        }
        
        super.eraseTargetFeedback(request);
        
    }

    /**
     * Returns the edit part the DROP is to be processed on. If the Gm
     * corresponding to the host edit part knows how to unmask each dropped
     * element, the host is returned. Otherwise, <code>null</code> is returned.
     * <p>
     * Subclasses should redefine this method to provide "smart interactions".
     * </p>
     * @param request the drop request
     * @return the host if all dropped elements can be unmasked by the Gm,
     * <code>null</code> otherwise.
     */
    @objid ("991fc953-ddec-4688-a9f6-cf6e54e3a73b")
    protected abstract EditPart getDropTargetEditPart(ModelElementDropRequest request);

    /**
     * Creates the Command to handle a ModelElementDropRequest.
     * @param request The drop request.
     * @return the created command.
     */
    @objid ("64c5e2cd-f443-47f7-bc5c-61c56d30c738")
    protected abstract Command getDropCommand(ModelElementDropRequest request);

    /**
     * Creates the Command to "smartly" handle a {@link ModelElementDropRequest}.
     * <p>
     * Subclasses must redefine this method to provide "smart interactions".
     * </p>
     * @param request The drop request.
     * @return the created command, or <code>null</code> when there is no smart interaction for this request.
     */
    @objid ("beaa9edf-28ce-4821-a333-4b5d33c3de58")
    protected abstract Command getSmartDropCommand(ModelElementDropRequest request);

    @objid ("526b634b-0097-4522-8d7f-dba9247a16e5")
    @Override
    public void deactivate() {
        // Remove all remaining feedback
        removeFeedback();
        
        super.deactivate();
        
    }

    @objid ("47fcee01-86f8-4f00-b1ff-25cbb7e7e0bf")
    private void removeFeedback() {
        if (this.highlight != null) {
            removeFeedback(this.highlight);
            this.highlight = null;
            this.highlightType = null;
        }
        
    }

}
