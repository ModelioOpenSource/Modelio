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
package org.modelio.diagram.elements.umlcommon.diagramview;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.ui.UIColor;

/**
 * EditPart for {@link GmDiagramViewBody}.
 * <p>
 * Instantiate the preview image of the represented diagram.
 * </p>
 */
@objid ("9a7196b8-a881-4b67-900f-dd65f277d98a")
public class DiagramViewBodyEditPart extends AbstractNodeEditPart {
    @objid ("2991d44c-dc85-4cce-8764-fbaf7c5af137")
    private int uiDataVersion;

    @objid ("1fafeec9-4017-4d98-827c-f025a1f5b998")
    @Override
    protected IFigure createFigure() {
        // Create the figure
        final ResizableImageFigure fig = new ResizableImageFigure();
        return fig;
    }

    @objid ("ab85acf2-a07f-4032-a5ee-be2f0815d306")
    @Override
    protected void refreshVisuals() {
        ResizableImageFigure fig = getFigure();
        
        updatePreviewImage(fig);
        
    }

    @objid ("8db7c96a-e8d2-45e7-a556-4a2e878ad05d")
    private void updatePreviewImage(ResizableImageFigure fig) {
        GmDiagramViewBody node = getModel();
        
        // Make sure there is preview data
        AbstractDiagram viewedDiagram = node.getRelatedElement();
        if (viewedDiagram == null || viewedDiagram.getPreviewData() == null) {
            return;
        }
        
        // Check if this image is already loaded
        int viewedDiagramVersion = viewedDiagram.getUiDataVersion();
        if (viewedDiagramVersion == this.uiDataVersion) {
            return;
        }
        
        String previewData = viewedDiagram.getPreviewData().replace("data:image/png;base64,", "");
        if (previewData.isEmpty()) {
            return;
        }
        
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(previewData));
        ImageLoader imgLoader = new ImageLoader();
        ImageData imageData = imgLoader.load(bais)[0];
        
        // Make the background transparent
        Color color = UIColor.WHITE;
        int pixelColor = (color.getRed() << 16) + (color.getGreen() << 8) + color.getBlue();
        
        if (true) {
            // Try to boost performances by using bulk ImageData methods
            // and operating directly in the array.
            // This may also allow JIT to compile this using SIMD processor capabilities.
            final int pixelscount = imageData.width * imageData.height;
            final int[] pixels = new int[pixelscount];
            final byte[] alphas = new byte[pixelscount];
            imageData.getPixels(0, 0, pixelscount, pixels, 0);
            for (int i = 0; i < pixelscount; i++) {
                if (pixels[i] != pixelColor) {
                    alphas[i] = (byte) 255;
                }
            }
            imageData.setPixels(0, 0, pixelscount, pixels, 0);
            imageData.alphaData = alphas;
        } else {
            // old code
            for (int y = 0; y < imageData.height; y++) {
                for (int x = 0; x < imageData.width; x++) {
                    if (imageData.getPixel(x, y) != pixelColor) {
                        imageData.setAlpha(x, y, 255);
                    }
                }
            }
        }
        
        Image previousImage = fig.getImage();
        fig.setImage(new Image(null, imageData));
        if (previousImage != null) {
            previousImage.dispose();
        }
        
    }

    @objid ("ba674edc-576b-49ea-8b78-8757af84dfcd")
    @Override
    public void deactivate() {
        Image previousImage = getFigure().getImage();
        if (previousImage != null) {
            previousImage.dispose();
        }
        
        super.deactivate();
        
    }

    @objid ("31c7dd04-e573-4f12-b52f-acf4eee73c9b")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("9b2e1bdb-19d2-499f-b688-34c0973b9640")
    @Override
    public ResizableImageFigure getFigure() {
        return (ResizableImageFigure) super.getFigure();
    }

    @objid ("80ddb709-964a-46ba-9662-d67474adb88f")
    @Override
    public GmDiagramViewBody getModel() {
        return (GmDiagramViewBody) super.getModel();
    }

}
