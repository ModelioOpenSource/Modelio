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
package org.modelio.diagram.elements.common.image;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.ImageServices;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.elements.core.policies.SimpleModeDeferringCreateNodePolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Default edit part for handling a node in Image representation mode. An Image only. If a Label is needed, use
 * {@link LabelledImageEditPart} instead.
 * 
 * @author fpoyer
 */
@objid ("7e844780-1dec-11e2-8cad-001ec947c8cc")
public class ImageEditPart extends AbstractNodeEditPart {
    @objid ("b438662a-683c-475a-ac40-210d9f92d063")
    private IImageableNode imageProvider;

    @objid ("7e844782-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
        
    }

    @objid ("7e844789-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SimpleModeDeferringCreateNodePolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                          new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
    }

    @objid ("7e84478c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        GmNodeModel inode = getModel();
        ImageFigure fig = (ImageFigure) getFigure();
        
        fig.setImage(getImage());
        fig.getParent().setConstraint(fig, inode.getLayoutData());
        
    }

    @objid ("7e84478f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        // Create the figure
        ImageFigure fig = new ImageFigure();
        
        // set style independent properties
        //fig.setPreferredSize(48, 60);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // Initialise image.
        fig.setImage(getImage());
        
        // return the figure
        return fig;
    }

    @objid ("7e86a9dd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<Object> getModelChildren() {
        return Collections.emptyList();
    }

    /**
     * Set the image provider.
     * @param imageProv the new image provider.
     */
    @objid ("94d8a5ac-ea83-4c59-ae71-784aa5d24b1d")
    public void setImageProvider(IImageableNode imageProv) {
        this.imageProvider = imageProv;
    }

    /**
     * Get the image provider.
     * <p>
     * Returns by default the {@link #getModel()} unless a custom image provider
     * has been set with {@link #setImageProvider(IImageableNode)}.
     * @return the image provider.
     */
    @objid ("d7e36d5f-177c-4538-963f-a96cb4d3398d")
    protected IImageableNode getImageProvider() {
        if (this.imageProvider != null) {
            return this.imageProvider;
        } else if (getModel() instanceof IImageableNode ) {
            return (IImageableNode) getModel();
        } else {
            return null;
        }
        
    }

    /**
     * Get the image to display.
     * <p>
     * Returns a default "no image" image if no image is defined.
     * @return the image to display.
     */
    @objid ("6602866d-78f1-424a-b9ec-e33082ad3b4b")
    protected Image getImage() {
        Image image = null;
        IImageableNode prov = getImageProvider();
        if (prov != null) {
            image = prov.getImage();
        }
        
        if (image == null) {
            // Use default image
            image = ImageServices.getNoImageImage();
        }
        return image;
    }

}
