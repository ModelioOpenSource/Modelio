/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.styles.editingsupport.text;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.viewer.StyleEditPanelUIData;

/**
 * Provide checkbox label
 */
@objid ("85b7a95c-1926-11e2-92d2-001ec947c8cc")
public class TextLabelProvider extends ColumnLabelProvider {
    @objid ("0c43052f-2095-49f4-8304-d8aea58b594c")
    private ColumnViewer viewer;

    @objid ("85b7a95f-1926-11e2-92d2-001ec947c8cc")
    public TextLabelProvider(ColumnViewer viewer) {
        this.viewer = viewer;
    }

    @objid ("85b7a962-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Image getImage(Object element) {
        return null;
    }

    @objid ("85b7a968-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
    }

    @objid ("85b7a96c-1926-11e2-92d2-001ec947c8cc")
    @Override
    public String getText(Object element) {
        ISymbolViewItem item = (ISymbolViewItem) element;
        return String.valueOf(item.getValue(getEditedStyle()));
    }

    @objid ("72f9f45b-648c-4589-8fbe-4197bf8fc78c")
    private IStyle getEditedStyle() {
        return ((StyleEditPanelUIData) this.viewer.getInput()).getStyleData();
    }

}
