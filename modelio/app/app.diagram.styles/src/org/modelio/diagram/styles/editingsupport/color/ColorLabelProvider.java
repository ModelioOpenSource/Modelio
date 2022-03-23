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
package org.modelio.diagram.styles.editingsupport.color;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.editingsupport.IOwnerDrawLabelProvider;
import org.modelio.diagram.styles.viewer.StyleEditPanelUIData;

/**
 * Draws the color in a rectangle .
 */
@objid ("859183d7-1926-11e2-92d2-001ec947c8cc")
public class ColorLabelProvider extends ColumnLabelProvider implements IOwnerDrawLabelProvider {
    @objid ("c85a40f0-21d7-4c79-9b42-96876a726791")
    private final ColumnViewer viewer;

    @objid ("859183e0-1926-11e2-92d2-001ec947c8cc")
    public  ColorLabelProvider(ColumnViewer viewer) {
        this.viewer = viewer;
    }

    @objid ("859183e3-1926-11e2-92d2-001ec947c8cc")
    @Override
    public String getText(Object element) {
        // StyleKey skey = (StyleKey) element;
        // Color color = this.data.getColor(skey);
        // return color.getRGB().toString();
        return null;
    }

    @objid ("859183e9-1926-11e2-92d2-001ec947c8cc")
    @Override
    public Color getBackground(Object element) {
        return null;
    }

    @objid ("b23cbf97-14a5-4f6b-a1a7-108216e91986")
    private IStyle getEditedStyle() {
        return ((StyleEditPanelUIData) this.viewer.getInput()).getStyleData();
    }

    @objid ("8d49ab05-3db1-400f-a3d5-d1bab6cd8845")
    @Override
    public void measure(Event event, Object element) {
        // nothing
    }

    @objid ("29e2456f-189a-41c4-9949-0eafd1c21476")
    @Override
    public void paint(Event event, Object element) {
        Rectangle bounds;
        if (event.item instanceof TableItem) {
            bounds = ((TableItem) event.item).getTextBounds(event.index);
        } else {
            bounds = ((TreeItem) event.item).getTextBounds(event.index);
        }
        
        Color oldBackground = event.gc.getBackground();
        Color oldForeground = event.gc.getForeground();
        
        event.gc.setBackground(getColorValue(element));
        event.gc.setForeground(event.display.getSystemColor(SWT.COLOR_WIDGET_BORDER));
        event.gc.fillRectangle(bounds.x + 2, bounds.y + 2, bounds.width - 6, bounds.height - 6);
        event.gc.drawRectangle(bounds.x + 2, bounds.y + 2, bounds.width - 6, bounds.height - 6);
        
        event.gc.setBackground(oldBackground);
        event.gc.setForeground(oldForeground);
        
    }

    @objid ("7c076d1c-cb61-499c-97a7-15d31778393a")
    private Color getColorValue(Object element) {
        if (element instanceof ISymbolViewItem) {
            ISymbolViewItem item = (ISymbolViewItem) element;
            return (Color) item.getValue(getEditedStyle());
        } else {
            return null;
        }
        
    }

}
