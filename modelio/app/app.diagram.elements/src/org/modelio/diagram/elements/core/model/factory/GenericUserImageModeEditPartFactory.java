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
package org.modelio.diagram.elements.core.model.factory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.jface.resource.ImageRegistry;
import org.modelio.diagram.elements.common.image.ImageEditPart;
import org.modelio.diagram.elements.common.image.UserDefinedImageProvider;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.ImageServices;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;

/**
 * EditPart factory for node models in {@link RepresentationMode#USER_IMAGE} mode.
 * <p>
 * Delegates {@link EditPart} construction to a {@link RepresentationMode#IMAGE} mode {@link EditPartFactory}, switching the image provider.
 * </p>
 */
@objid ("7ff57d7b-94e0-40fe-bf2a-e12db7ea2c49")
public class GenericUserImageModeEditPartFactory implements EditPartFactory {
    @objid ("25ff258f-898f-4cd3-b3a7-bf43159527b1")
    private EditPartFactory imageModeEditPartFactory;

    /**
     * C'tor.
     * @param imageModeEditPartFactory a {@link RepresentationMode#IMAGE} mode {@link EditPartFactory}.
     */
    @objid ("fc6f218f-06ac-49e1-bbc5-bc9ec4cc7aa4")
    public  GenericUserImageModeEditPartFactory(EditPartFactory imageModeEditPartFactory) {
        this.imageModeEditPartFactory = imageModeEditPartFactory;
    }

    @objid ("5146997e-5eb0-4551-91ec-d3c1dfd7675d")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart ret = this.imageModeEditPartFactory.createEditPart(context, model);
        if (ret instanceof ImageEditPart) {
            IImageableNode imageProv = createImageProvider(context, model);
            ((ImageEditPart) ret).setImageProvider(imageProv);
        }
        return ret;
    }

    @objid ("30d9df9c-d39d-4680-83a7-c476a0e6d677")
    protected UserDefinedImageProvider createImageProvider(EditPart context, Object model) {
        ImageRegistry imageRegistry = ImageServices.getImageRegistry(context);
        return new UserDefinedImageProvider((GmModel) model, imageRegistry);
    }

}
