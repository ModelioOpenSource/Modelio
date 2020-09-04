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
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

/**
 * Edit policy that shows a feedback when hovering the host figure.
 */
@objid ("daad80fd-afb0-4ce1-97e7-68404d0cdbca")
public class HoverFeedbackEditPolicy extends GraphicalEditPolicy {
    @objid ("a36bd359-21a9-4d7d-9b64-cff8154fe12e")
    private IFigure feedback;

    @objid ("455227c7-98f5-4577-b3a4-8cbe2594baec")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_SELECTION.equals(request.getType())) {
            return getHost();
        }
        return null;
    }

    @objid ("d5af4176-f53f-4e99-8791-4e3025ec7168")
    @Override
    public void showTargetFeedback(Request request) {
        if (REQ_SELECTION.equals(request.getType())) {
            if (this.feedback == null) {
                this.feedback = createFeedbackFigure();
                addFeedback(this.feedback);
            }
        
            IFigure hostFigure = getHostFigure();
            Rectangle bounds = hostFigure.getBounds().getCopy();
            hostFigure.translateToAbsolute(bounds);
            this.feedback.translateToRelative(bounds);
            this.feedback.setBounds(bounds);
            this.feedback.validate();
        }
    }

    /**
     * Create the hover feedback figure.
     * 
     * @return the hover feedback figure.
     */
    @objid ("7a81d425-e077-4292-a2b6-009366c00573")
    protected IFigure createFeedbackFigure() {
        RectangleFigure fb = new RectangleFigure();
        fb.setForegroundColor(getHost().isSelectable() ? ColorConstants.yellow : ColorConstants.gray);
        fb.setOpaque(false);
        fb.setFill(false);
        fb.setAlpha(140);
        fb.setLineWidth(1);
        fb.setOutlineXOR(true);
        return fb;
    }

    @objid ("5dfa2a7b-e7ee-4df7-82fd-3d9baf141607")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (REQ_SELECTION.equals(request.getType())) {
            if (this.feedback != null) {
                removeFeedback(this.feedback);
                this.feedback = null;
            }
        }
    }

    @objid ("1d82b4d1-3b74-4fa4-84b0-b1302652f182")
    @Override
    public void deactivate() {
        if (this.feedback != null) {
            removeFeedback(this.feedback);
            this.feedback = null;
        }
        
        super.deactivate();
    }

}
