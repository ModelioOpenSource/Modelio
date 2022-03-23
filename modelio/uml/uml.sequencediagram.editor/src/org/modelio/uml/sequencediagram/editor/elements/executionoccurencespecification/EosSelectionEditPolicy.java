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
package org.modelio.uml.sequencediagram.editor.elements.executionoccurencespecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

/**
 * Selection edit policy to show the "blue square" when the mouse is over it : it helps the user knowing the existence of this invisible figure.
 * @author cmarin
 * @since Modelio 3.4
 */
@objid ("cfccae94-77ea-453f-9e0a-42d168e073d1")
class EosSelectionEditPolicy extends GraphicalEditPolicy {
    @objid ("7e42aee2-2874-4043-ac39-058996b9c78f")
    private RectangleFigure feedback;

    @objid ("a0889532-7d3d-43ce-ac68-af8914198760")
    @Override
    public void showTargetFeedback(Request request) {
        if (request.getType().equals(REQ_SELECTION)) {
            if (this.feedback == null) {
                // Create the feedback figure
                this.feedback = new RectangleFigure();
                this.feedback.setForegroundColor(ColorConstants.blue);
                this.feedback.setBackgroundColor(null);
                this.feedback.setFill(false);
        
                // Set bounds once : the figure won't move
                IFigure hostFigure = getHostFigure();
                Rectangle b = hostFigure.getBounds().getCopy();
                hostFigure.translateToAbsolute(b);
                getFeedbackLayer().translateToRelative(b);
                this.feedback.setBounds(b);
        
                getFeedbackLayer().add(this.feedback);
            }
        
        }
        
    }

    @objid ("8e8a10de-defb-4e65-96cc-c2df2b15c18d")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (request.getType().equals(REQ_SELECTION)) {
        
            if (this.feedback != null) {
                getFeedbackLayer().remove(this.feedback);
                this.feedback = null;
            }
        
        }
        
    }

}
