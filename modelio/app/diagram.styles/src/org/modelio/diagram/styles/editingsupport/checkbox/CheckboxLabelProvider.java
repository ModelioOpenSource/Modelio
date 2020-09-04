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

package org.modelio.diagram.styles.editingsupport.checkbox;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.diagram.styles.viewer.StyleEditPanelUIData;

/**
 * Provide checkbox label
 */
@objid ("858cbf02-1926-11e2-92d2-001ec947c8cc")
public class CheckboxLabelProvider extends ColumnLabelProvider {
    @objid ("ecaf6906-e792-44de-84be-804a33fc053d")
    private ColumnViewer viewer;

    @objid ("ff2b9461-9e76-47a9-8b86-3342c1853f56")
    private static final Image CHECKED = DiagramStyles.getImageDescriptor("icons/checked.png").createImage();

    @objid ("f5cd43c9-9f6d-427f-87a0-d4b25f9cd9fd")
    private static final Image UNCHECKED = DiagramStyles.getImageDescriptor("icons/unchecked.png").createImage();

    @objid ("858cbf09-1926-11e2-92d2-001ec947c8cc")
    public CheckboxLabelProvider(ColumnViewer viewer) {
        this.viewer = viewer;
    }

    @objid ("858cbf0c-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Image getImage(Object element) {
        ISymbolViewItem item = (ISymbolViewItem) element;
        
        if ((Boolean) item.getValue(getEditedStyle())) {
            return CHECKED;
        } else {
            return UNCHECKED;
        }
    }

    @objid ("858cbf11-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
    }

    @objid ("858cbf15-1926-11e2-92d2-001ec947c8cc")
    @Override
    public String getText(Object element) {
        return null;
    }

    @objid ("791fd922-34d4-402f-82ac-d7cedfbfb4d8")
    private IStyle getEditedStyle() {
        return ((StyleEditPanelUIData) this.viewer.getInput()).getStyleData();
    }

}
