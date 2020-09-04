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

package org.modelio.module.propertytab.ui.panel.treeview.editingsupport.key;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.module.propertytab.model.ModuleProperty;
import org.modelio.module.propertytab.ui.panel.treeview.ModulePropertyContentProvider.Category;
import org.modelio.ui.UIColor;
import org.modelio.ui.UIImages;

@objid ("c8951e50-1eba-11e2-9382-bc305ba4815c")
public class KeyLabelProvider extends StyledCellLabelProvider {
    @objid ("68073dd2-353e-4a0d-9527-270e31ba70d8")
    private static final Image TRANSPARENT_IMAGE = createTransparentImage();

    @objid ("c8951e51-1eba-11e2-9382-bc305ba4815c")
    private static final Image createTransparentImage() {
        // allocate an image data
        ImageData imageData = UIImages.PLACEHOLDER.getImageData();
        ImageData imData = new ImageData(imageData.width, imageData.height, imageData.depth, new PaletteData(0xff0000, 0x00ff00, 0x0000ff));
        imData.setAlpha(0, 0, 0); // just to force alpha array allocation with the right size
        Arrays.fill(imData.alphaData, (byte) 0); // set whole image as transparent
        
        // Initialize image from transparent image data
        return new Image(Display.getDefault(), imData);
    }

    @objid ("c8951e53-1eba-11e2-9382-bc305ba4815c")
    @Override
    public void update(ViewerCell cell) {
        if (cell.getElement() instanceof ModuleProperty) {
            ModuleProperty element = (ModuleProperty) cell.getElement();
        
            String label = element.getLabel();
        
            if (element.getCategory() != null) {
                label = label.substring(label.indexOf("]") + 1);
            }
        
            cell.setText(label);
        } else if (cell.getElement() instanceof Category) {
            Category element = (Category) cell.getElement();
            cell.setText(element.label);
        }
        
        if (cell.getItem() instanceof TreeItem) {
            TreeItem item = (TreeItem) cell.getItem();
            if (item.getParent().indexOf(item) % 2 == 0) {
                cell.setBackground(UIColor.TABLE_EVENROW_BG);
            } else {
                cell.setBackground(UIColor.TABLE_ODDROW_BG);
            }
        }
        // Use a transparent placeholder to force lines to have a proper height
        cell.setImage(KeyLabelProvider.TRANSPARENT_IMAGE);
        return;
    }

}
