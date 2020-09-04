/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.module.propertytab.ui.panel.treeview.editingsupport.empty;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.ui.UIColor;

/**
 * Provide checkbox label
 */
@objid ("04256101-1ebb-11e2-9382-bc305ba4815c")
public class EmptyLabelProvider extends ColumnLabelProvider {
    @objid ("04258810-1ebb-11e2-9382-bc305ba4815c")
    public EmptyLabelProvider() {
    }

    @objid ("04258812-1ebb-11e2-9382-bc305ba4815c")
    @Override
    public Image getImage(Object element) {
        return null;
    }

    @objid ("0425af21-1ebb-11e2-9382-bc305ba4815c")
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

    @objid ("0425d630-1ebb-11e2-9382-bc305ba4815c")
    @Override
    public String getText(Object element) {
        return "";
    }

}
