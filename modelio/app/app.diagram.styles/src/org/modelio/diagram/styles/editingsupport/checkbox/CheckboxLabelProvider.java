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

package org.modelio.diagram.styles.editingsupport.checkbox;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.viewer.StyleEditPanelUIData;
import org.modelio.platform.ui.UIImages;

/**
 * Provide checkbox label
 */
@objid ("858cbf02-1926-11e2-92d2-001ec947c8cc")
public class CheckboxLabelProvider extends ColumnLabelProvider {
    @objid ("ecaf6906-e792-44de-84be-804a33fc053d")
    private final ColumnViewer viewer;

    @objid ("858cbf09-1926-11e2-92d2-001ec947c8cc")
    public CheckboxLabelProvider(final ColumnViewer viewer) {
        this.viewer = viewer;
    }

    @objid ("858cbf0c-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Image getImage(final Object element) {
        final ISymbolViewItem item = (ISymbolViewItem) element;
        
        if ((Boolean) item.getValue(getEditedStyle())) {
            return UIImages.CHECKED;
        } else {
            return UIImages.UNCHECKED;
        }
    }

    @objid ("858cbf11-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void update(final ViewerCell cell) {
        super.update(cell);
    }

    @objid ("858cbf15-1926-11e2-92d2-001ec947c8cc")
    @Override
    public String getText(final Object element) {
        return null;
    }

    @objid ("791fd922-34d4-402f-82ac-d7cedfbfb4d8")
    private IStyle getEditedStyle() {
        return ((StyleEditPanelUIData) this.viewer.getInput()).getStyleData();
    }

}
