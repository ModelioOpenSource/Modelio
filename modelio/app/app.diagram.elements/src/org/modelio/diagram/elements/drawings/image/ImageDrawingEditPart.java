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
package org.modelio.diagram.elements.drawings.image;

import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.diagram.elements.common.image.ImageFigure;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.drawings.core.node.NodeDrawingEditPart;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;

/**
 * Edit part for {@link GmImageDrawing}.
 */
@objid ("eba33161-bca2-4b28-ab96-0e9011fbd911")
public class ImageDrawingEditPart extends NodeDrawingEditPart {
    @objid ("3f0a7309-dc83-4b34-8e99-00cd231ec5d5")
    private int alpha = 255;

    @objid ("1b3577dd-2771-47f3-b5a5-a2a0511ec95d")
    @Override
    public void performRequest(Request req) {
        if (RequestConstants.REQ_OPEN.equals(req.getType()) || RequestConstants.REQ_DIRECT_EDIT.equals(req.getType())) {
            FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell());
            String file =  dialog.open();
            if(file != null && !"".equals(file)) {
                getModel().setLabel(ImageSerialiser.serialise(new ImageData(file)));
                refreshVisuals();
            }
        } else {
            super.performRequest(req);
        }
        
    }

    @objid ("0fcff2eb-4d2f-4511-a7af-89d5e0f98d42")
    public void setImage(ImageDrawingFigure figure, String val, int alpha) {
        if (val != null && !"".equals(val)) {
            ImageData data = ImageSerialiser.deserialise(val);
        
            if (data != null) {
                data.setAlpha(0, 0, this.alpha);
                Arrays.fill(data.alphaData,  (byte)this.alpha);
        
                if(figure.getIcon() != null) {
                    figure.getIcon().dispose();
                }
                figure.setIcon(new Image(Display.getCurrent(), data));
                figure.setSize(data.width, data.height);
            }
        }
        
    }

    @objid ("38fc04bc-54c2-44f8-ba1f-872af49d3edf")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (getModel().getStyleKey(MetaKey.FILLALPHA) != null) {
           this.alpha = style.getInteger(getModel().getStyleKey(MetaKey.FILLALPHA));
           refreshVisuals();
        }
        
        super.refreshFromStyle(aFigure, style);
        
    }

    @objid ("eaaaef82-9946-4ac2-b0ac-43d831bf32cb")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            refreshVisuals();
        } else if (evt.getPropertyName().equals(IGmObject.PROPERTY_LINK_TARGET)) {
            super.propertyChange(evt);
        } else {
            super.propertyChange(evt);
        }
        
    }

    /**
     * Get the image figure.
     * @return The image figure.
     */
    @objid ("7ebe5418-adbf-4e4b-91e5-d4aa788b4e25")
    protected final ImageDrawingFigure getImageDrawingFigure() {
        return (ImageDrawingFigure) getFigure();
    }

    @objid ("8f185b93-727c-43b5-b1bf-3808efce9a9e")
    @Override
    protected IFigure createFigure() {
        return new ImageDrawingFigure();
    }

    @objid ("7f8fab82-eb28-42e5-beb7-06ff1f45cd6d")
    @Override
    protected void refreshVisuals() {
        final ImageDrawingFigure imageFigure = (ImageDrawingFigure) getFigure();
        final GmImageDrawing model = (GmImageDrawing) getModel();
        imageFigure.getParent().setConstraint(imageFigure, model.getLayoutData());
        setImage(imageFigure, model.getLabel(),this.alpha);
        super.refreshVisuals();
        
    }

    @objid ("044648c3-8990-4456-a290-76ba54e1e9cd")
    public static final class ImageDrawingFigure extends ImageFigure {
        @objid ("cff3b2ad-2211-475d-9113-3ef4288d4063")
        public  ImageDrawingFigure() {
            super();
            setImage(AbstractUIPlugin.imageDescriptorFromPlugin(DiagramElements.PLUGIN_ID, "images/no_image48x48.png").createImage());
            
        }

    }

}
