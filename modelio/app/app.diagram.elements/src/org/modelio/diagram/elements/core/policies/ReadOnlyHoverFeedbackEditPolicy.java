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
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that shows a feedback when hovering the host figure.
 */
@objid ("bc29db51-b705-412d-b38c-eb7417b5dddf")
public class ReadOnlyHoverFeedbackEditPolicy extends GraphicalEditPolicy {
    @objid ("edad63bd-d715-4100-9caa-bab1f6e8875a")
    private IFigure feedback;

    @objid ("13281b52-d65b-46c2-b1de-f2f59767669e")
    private static Image lockImage = AbstractUIPlugin.imageDescriptorFromPlugin(DiagramElements.PLUGIN_ID, "icons/lock.png")
			.createImage();

    @objid ("f6ad27fc-c05f-4609-b82d-a823b5b2a4f8")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_SELECTION.equals(request.getType())) {
            return getHost();
        }
        return null;
    }

    @objid ("7930753a-9664-4b05-a105-5931968eb33c")
    @Override
    public void showTargetFeedback(Request request) {
        if (REQ_SELECTION.equals(request.getType())) {
        
            IGmModelRelated model = (IGmModelRelated) getHost().getModel();
            MObject element = model.getRepresentedElement();
        
            boolean isCmsNode = (element != null && element.getMClass().isCmsNode());
            boolean isEditable = model.isUserEditable() && (element != null && element.isModifiable());
        
            if (isCmsNode && !isEditable) {
                if (this.feedback == null) {
                    this.feedback = createFeedbackFigure();
                    addFeedback(this.feedback);
                }
        
                IFigure hostFigure = getHostFigure();
                Rectangle bounds = hostFigure.getBounds().getCopy();
                hostFigure.translateToAbsolute(bounds);
                this.feedback.translateToRelative(bounds);
                this.feedback.setBounds(new Rectangle(bounds.x + bounds.width/2 - lockImage.getBounds().width/2, 
                                                      bounds.y + bounds.height/2 - lockImage.getBounds().height/2, 
                                                      lockImage.getBounds().width, 
                                                      lockImage.getBounds().height));
                
                this.feedback.validate();
            }
        }
    }

    /**
     * Create the hover feedback figure.
     * 
     * @return the hover feedback figure.
     */
    @objid ("e5852761-aec8-4a9c-a032-691b17a0ff42")
    protected IFigure createFeedbackFigure() {
        ImageFigure fb = new ImageFigure(lockImage);
        
        // RectangleFigure fb = new RectangleFigure();
        // fb.setForegroundColor(ColorConstants.red);
        // fb.setOpaque(false);
        // fb.setFill(false);
        // fb.setAlpha(140);
        // fb.setLineWidth(1);
        // fb.setOutlineXOR(true);
        return fb;
    }

    @objid ("ebd7b9e3-1790-4679-8390-38189b71e389")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (REQ_SELECTION.equals(request.getType())) {
            if (this.feedback != null) {
                removeFeedback(this.feedback);
                this.feedback = null;
            }
        }
    }

    @objid ("ece171b7-b6e3-40d5-8484-8b76b884a934")
    @Override
    public void deactivate() {
        if (this.feedback != null) {
            removeFeedback(this.feedback);
            this.feedback = null;
        }
        
        super.deactivate();
    }

}
