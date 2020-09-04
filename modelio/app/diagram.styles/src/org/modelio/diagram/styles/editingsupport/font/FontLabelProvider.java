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

package org.modelio.diagram.styles.editingsupport.font;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.viewer.StyleEditPanelUIData;

@objid ("85a496c5-1926-11e2-92d2-001ec947c8cc")
public class FontLabelProvider extends ColumnLabelProvider {
    @objid ("b83789d7-3d87-491e-a48e-847339bbb9dd")
    private ColumnViewer viewer;

    @objid ("85a496c7-1926-11e2-92d2-001ec947c8cc")
    @Override
    public String getText(Object element) {
        String text = "";
        if (element != null) {
            ISymbolViewItem item = (ISymbolViewItem) element;
            return FontService.getFontLabel((Font) item.getValue(getEditedStyle()));
        }
        return text;
    }

    @objid ("85a496cd-1926-11e2-92d2-001ec947c8cc")
    public FontLabelProvider(ColumnViewer viewer) {
        this.viewer = viewer;
    }

    @objid ("85a496d0-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
    }

    @objid ("ee66cffa-3b2f-47d4-b50c-90ea196b057d")
    private IStyle getEditedStyle() {
        return ((StyleEditPanelUIData) this.viewer.getInput()).getStyleData();
    }

}
