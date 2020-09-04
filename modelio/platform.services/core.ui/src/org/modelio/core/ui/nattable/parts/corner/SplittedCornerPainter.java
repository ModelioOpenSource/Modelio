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

package org.modelio.core.ui.nattable.parts.corner;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.painter.cell.BackgroundPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.modelio.core.ui.plugin.CoreUi;

/**
 * Paints an image. If no image is provided, it will attempt to look up an image from the cell style.
 */
@objid ("6db3f760-eba7-49db-b1df-8921be19da9c")
public class SplittedCornerPainter extends BackgroundPainter {
    @objid ("ca230f98-8d37-4278-b388-e37506c08800")
    private boolean showBottomImage;

    @objid ("e84950be-4e8d-46a6-afa3-d26d1e4255f6")
    private boolean showRightImage;

    @objid ("496dd62a-9dbc-4931-a717-0a9ab0f5e07c")
    private static final Image bottomImage = CoreUi.getImageDescriptor("icons/bottomcorner.png").createImage();

    @objid ("8e20570b-fbb2-4762-9107-5e474404eb49")
    private static final Image rightImage = CoreUi.getImageDescriptor("icons/rightcorner.png").createImage();

    /**
     * Build a new corner painter.
     * 
     * @param showBottomImage Flag to configure whether the image on the bottom should be shown or not.
     * @param showRightImage Flag to configure whether the image on the right should be shown or not.
     */
    @objid ("4058875b-6fff-4967-87ad-e27527dbc5d1")
    public SplittedCornerPainter(boolean showBottomImage, boolean showRightImage) {
        this.showBottomImage = showBottomImage;
        this.showRightImage = showRightImage;
    }

    @objid ("b96ad195-8e79-45bf-9090-e7da250852ba")
    @Override
    public ICellPainter getCellPainterAt(int x, int y, ILayerCell cell, GC gc, Rectangle bounds, IConfigRegistry configRegistry) {
        return super.getCellPainterAt(x, y, cell, gc, bounds, configRegistry);
    }

    @objid ("151d46ac-e638-48c1-885e-6796d874c235")
    @Override
    public void paintCell(ILayerCell cell, GC gc, Rectangle bounds, IConfigRegistry configRegistry) {
        super.paintCell(cell, gc, bounds, configRegistry);
        
        if (SplittedCornerPainter.bottomImage != null && this.showBottomImage) {
            Rectangle bottomImageBounds = SplittedCornerPainter.bottomImage.getBounds();
            gc.drawImage(SplittedCornerPainter.bottomImage, bounds.x, bounds.height + bounds.y - bottomImageBounds.height);
        }
        
        if (SplittedCornerPainter.rightImage != null && this.showRightImage) {
            Rectangle rightImageBounds = SplittedCornerPainter.rightImage.getBounds();
            gc.drawImage(SplittedCornerPainter.rightImage, bounds.x + bounds.width - rightImageBounds.width, bounds.y);
        }
        
        gc.drawLine(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height);
    }

}
