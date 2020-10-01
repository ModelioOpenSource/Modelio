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

package org.modelio.module.propertytab.ui.panel.treeview.editingsupport.element;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.module.propertytab.model.ModuleProperty;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.platform.ui.UIColor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Provide {@link MObject} label
 */
@objid ("484ed6b4-eda8-41a6-9894-4851939704cd")
public class ElementLabelProvider extends ColumnLabelProvider {
    @objid ("34088c45-ba60-4c5d-954c-562d98188d75")
    @Override
    public Image getImage(Object element) {
        ModuleProperty property = (ModuleProperty) element;
        return property.getValue() instanceof MObject ? ElementImageService.getIcon((MObject) property.getValue()) : null;
    }

    @objid ("b1f408ca-c13a-4262-a516-e7fa30bc6262")
    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
        
        if (cell.getItem() instanceof TreeItem) {
            TreeItem item = (TreeItem) cell.getItem();
            if (item.getParent().indexOf(item) % 2 == 0) {
                cell.setBackground(UIColor.TABLE_EVENROW_BG);
            } else {
                cell.setBackground(UIColor.TABLE_ODDROW_BG);
            }
        }
    }

    @objid ("59b448fe-66d3-4ded-be33-9421b398870c")
    @Override
    public String getText(Object element) {
        ModuleProperty property = (ModuleProperty) element;
        return property.getValue() instanceof MObject ? ((MObject) property.getValue()).getName() : "";
    }

}
