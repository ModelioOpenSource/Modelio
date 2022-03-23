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
package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.vcore.model.api.MTools;

/**
 * Command to change a link extension size.
 * 
 * @since Modelio 3.3
 * @author cmarin
 */
@objid ("02627775-7f81-4627-803c-b62f365ab53c")
public class ChangeExtensionSizeCommand extends Command {
    @objid ("1bba299c-c36b-4b68-808b-ce479744763f")
    private IFigure figure;

    @objid ("4530eae2-b2c4-4395-b6c6-b709fc1e7d61")
    private Dimension newSize;

    @objid ("0ed1affa-91db-4919-9c42-29ed37c2014c")
    private IGmObject gmExtension;

    @objid ("a25d40f8-953f-4fb2-9f1f-cc67494ac89c")
    private IGmLink gmLink;

    /**
     * @param figure the figure to resize
     * @param gmLink the link model
     * @param gmExtension the figure locator model
     * @param size the new size
     */
    @objid ("7194783d-4c1b-41f5-ad9e-728b08fd909c")
    public  ChangeExtensionSizeCommand(IFigure figure, IGmLink gmLink, IGmObject gmExtension, Dimension size) {
        this.figure = figure;
        this.gmLink = gmLink;
        this.gmExtension = gmExtension;
        this.newSize = size;
        
    }

    @objid ("4947b0f4-6104-4421-8c2f-c737b57be050")
    @Override
    public void execute() {
        IGmLocator layoutContraint = this.gmLink.getLayoutContraint(this.gmExtension);
        if (layoutContraint != null) {
            IGmLocator locator = layoutContraint.copy();
        
            Dimension constraint = computeNewConstraint(this.figure, this.newSize);
            locator.setHeightConstraint(constraint.height);
            locator.setWidthConstraint(constraint.width);
        
            this.gmLink.setLayoutConstraint(this.gmExtension, locator);
        }
        
    }

    @objid ("ed22fb90-3a83-4742-99d6-99805b084bab")
    @Override
    public boolean canExecute() {
        final IGmDiagram diagram = this.gmExtension.getDiagram();
        return (MTools.getAuthTool().canModify(diagram.getRelatedElement()));
    }

    /**
     * Get the new bounds of the figure if the command matching the request was executed.
     * @param hostFigure the host figure
     * @param request the request
     * @return the new bounds in host figure coordinates .
     */
    @objid ("afeb2e27-57e6-4602-9c67-e0affc061356")
    private static Dimension computeNewConstraint(IFigure hostFigure, ChangeBoundsRequest request) {
        PrecisionRectangle newRect = new PrecisionRectangle(hostFigure.getBounds());
        hostFigure.translateToAbsolute(newRect);
        newRect.resize(request.getSizeDelta());
        newRect.translate(request.getMoveDelta());
        hostFigure.translateToRelative(newRect);
        
        Dimension constraint = computeNewConstraint(hostFigure, newRect.getSize());
        return constraint;
    }

    /**
     * Show the feedback of a resize link extension.
     * @param request the request
     * @param feedback the feedback figure to use
     * @param hostFigure the target link extension figure of the request
     */
    @objid ("5749716b-26c3-4d22-8610-e4cf6325f11e")
    public static void showFeedback(ChangeBoundsRequest request, IFigure feedback, IFigure hostFigure) {
        Dimension newConstraint = computeNewConstraint(hostFigure, request);
        
        // Compute and set feedback size
        Dimension feedbackSize = new PrecisionDimension(hostFigure.getPreferredSize(newConstraint.width(), newConstraint.height()));
        hostFigure.translateToAbsolute(feedbackSize);
        feedback.translateToRelative(feedbackSize);
        feedback.setPreferredSize(feedbackSize);
        
        // let the Locator tell where the resized feedback figure will lie
        IFigure connFig = hostFigure.getParent();
        IResizableFigureLocator loc = (IResizableFigureLocator) connFig.getLayoutManager().getConstraint(hostFigure);
        
        loc.setWidthConstraint(newConstraint.width());
        loc.setHeightConstraint(newConstraint.height());
        
        loc.relocate(feedback);
        
        feedback.validate();
        
    }

    @objid ("f8349cb9-7a58-4005-962e-a17f9b6dc854")
    private static Dimension computeNewConstraint(IFigure hostFigure, Dimension askedSize) {
        Dimension minSize = hostFigure.getMinimumSize();
        Dimension constraint = new Dimension();
        
        if (minSize.width() <= askedSize.width() && minSize.height() <= askedSize.height() ) {
            constraint.width = (-1);
        } else {
            constraint.width = (askedSize.width());
        }
        
        if (minSize.height() <= askedSize.height()) {
            constraint.height = (-1);
        } else {
            constraint.height = (askedSize.width());
        }
        return constraint;
    }

}
