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

package org.modelio.propertyview.vtabfolder;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

@objid ("197cf93e-3bf9-484c-999f-fdb925d825c9")
public class VTabFolderLayout extends Layout {
    @objid ("200dc5f1-5562-4bca-8c20-7b8f03e28d9c")
    @Override
    protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
        VTabFolder folder = (VTabFolder) composite;
        VTabItem[] items = folder.getItems();
        VTabFolderRenderer renderer = folder.getRenderer();
        // preferred height of tab area to show all tabs
        int tabH = 0;
        int selectedIndex = folder.getSelectedIndex();
        if (selectedIndex == -1)
            selectedIndex = 0;
        GC gc = new GC(folder);
        for (int i = 0; i < items.length; i++) {
            if (folder.isSingle()) {
                tabH = Math.max(tabH, renderer.computeSize(i, SWT.SELECTED, gc, SWT.DEFAULT, SWT.DEFAULT).y);
            } else {
                int state = 0;
                if (i == selectedIndex)
                    state |= SWT.SELECTED;
                tabH += renderer.computeSize(i, state, gc, SWT.DEFAULT, SWT.DEFAULT).y;
            }
        }
        
        //int width = 0, wrapHeight = 0;
        int height = 0, wrapWidth = 0;
        //boolean leftControl = false, rightControl = false;
        boolean topControl = false, bottomControl = false;
        if (hHint == SWT.DEFAULT) {
            for (int i = 0; i < folder.controls.length; i++) {
                Control control = folder.controls[i];
                if (!control.isDisposed() && control.getVisible()) {
                    if ((folder.controlAlignments[i] & SWT.LEAD) != 0) {
                        topControl = true;
                    } else {
                        bottomControl = true;
                    }
                    height += control.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
                }
            }
        } else {
            Point size = new Point(wHint, hHint);
            boolean[][] positions = new boolean[1][];
            Rectangle[] rects = folder.computeControlBounds(size, positions);
            //int minY = Integer.MAX_VALUE, maxY = 0;
            int minX = Integer.MAX_VALUE, maxX = 0;
            for (int i = 0; i < rects.length; i++) {
                if (positions[0][i]) {
                    minX = Math.min(minX, rects[i].x);
                    maxX = Math.max(maxX, rects[i].x + rects[i].width);
                    wrapWidth = maxX - minX;
                } else {
                    if ((folder.controlAlignments[i] & SWT.LEAD) != 0) {
                        topControl = true;
                    } else {
                        bottomControl = true;
                    }
                    height += rects[i].height;
                }
            }
        }
        if (topControl)
            height += VTabFolder.SPACING * 2;
        if (bottomControl)
            height += VTabFolder.SPACING * 2;
        tabH += height;
        
        gc.dispose();
        
        int controlW = 0;
        int controlH = 0;
        // preferred size of controls in tab items
        for (int i = 0; i < items.length; i++) {
            Control control = items[i].control;
            if (control != null && !control.isDisposed()) {
                Point size = control.computeSize(wHint, hHint, flushCache);
                controlW = Math.max(controlW, size.x);
                controlH = Math.max(controlH, size.y);
            }
        }
        
        //        int minWidth = Math.max(tabW, controlW + folder.marginWidth);
        //        int minHeight = (folder.minimized) ? 0 : controlH + wrapHeight;
        
        int minHeight = Math.max(tabH, controlH + folder.marginHeight);
        int minWidth =  controlW + wrapWidth;
        
        
        
        
        if (minWidth == 0)
            minWidth = VTabFolder.DEFAULT_WIDTH;
        if (minHeight == 0)
            minHeight = VTabFolder.DEFAULT_HEIGHT;
        
        if (wHint != SWT.DEFAULT)
            minWidth = wHint;
        if (hHint != SWT.DEFAULT)
            minHeight = hHint;
        return new Point(minWidth, minHeight);
    }

    @objid ("6b4dc234-6506-4442-9636-fc1df10164cd")
    @Override
    protected void layout(Composite composite, boolean flushCache) {
        VTabFolder folder = (VTabFolder) composite;
        // resize content
        if (folder.getSelectedIndex() != -1) {
            Control control = folder.getItems()[folder.getSelectedIndex()].control;
            if (control != null && !control.isDisposed()) {
                control.setBounds(folder.getClientArea());
            }
        }
    }

    @objid ("dc184cf5-00d3-4d64-bd80-e4225d5818bc")
    @Override
    protected boolean flushCache(Control control) {
        return true;
    }

}
