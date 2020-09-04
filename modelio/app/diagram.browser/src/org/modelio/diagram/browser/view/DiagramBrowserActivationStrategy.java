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

package org.modelio.diagram.browser.view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

@objid ("0010de98-0d4f-10c6-842f-001ec947cd2a")
public class DiagramBrowserActivationStrategy extends ColumnViewerEditorActivationStrategy implements MouseListener {
    @objid ("0010e82a-0d4f-10c6-842f-001ec947cd2a")
    private int time = 0;

    @objid ("0010f14e-0d4f-10c6-842f-001ec947cd2a")
    private Object selectedObject = null;

    @objid ("0010f824-0d4f-10c6-842f-001ec947cd2a")
    private ColumnViewer viewer;

    @objid ("0010fe00-0d4f-10c6-842f-001ec947cd2a")
    public DiagramBrowserActivationStrategy(ColumnViewer viewer) {
        super(viewer);
        this.viewer = viewer;
        viewer.getControl().addMouseListener(this);
    }

    @objid ("00111138-0d4f-10c6-842f-001ec947cd2a")
    @Override
    protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
        if (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.F2) {
            return true;
        } else if (event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC) {
            return true;
        }
        return false;
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
     */
    @objid ("00113a00-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void mouseDoubleClick(MouseEvent e) {
        // Nothing to do
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
     */
    @objid ("0011608e-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void mouseDown(MouseEvent e) {
        // Nothing to do
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
     */
    @objid ("00118762-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void mouseUp(MouseEvent e) {
        // Retrieve selected element:
        Object sourceObject = null;
        
        Object eventSource = e.getSource();
        
        if (eventSource instanceof Tree) {
            Tree tree = (Tree) eventSource;
        
            TreeItem clickedItem = tree.getItem(new Point(e.x, e.y));
        
            TreeItem[] items = tree.getSelection();
            if (items.length == 1) {
                if (clickedItem != null) {
                    sourceObject = items[0].getData();
                }
            }
        }
        
        if (sourceObject == null) {
            return;
        }
        
        boolean leftButtonClicked = false;
        
        // Report from Eclipse doc:
        // Note: The button field is an integer that represents the mouse button number. This is not the same as the SWT mask constants BUTTONx.
        // the button that was pressed or released; 1 for the first button, 2 for the second button, and 3 for the third button, etc. 
        if (e.button == 1) {
            leftButtonClicked = true;
        }
        
        // We enter edition only when left button is pressed 
        if (this.time == 0 && leftButtonClicked) {
            this.time = e.time;
            this.selectedObject = sourceObject;
            return;
        } else if (leftButtonClicked) {
            int delta = e.time - this.time;
        
            if (delta > 300 && delta < 1000 && this.selectedObject == sourceObject) {
                this.time = 0;
                this.selectedObject = sourceObject;
                this.viewer.editElement(sourceObject, 0);
                return;
            } else {
                this.time = e.time;
                this.selectedObject = sourceObject;
                return;
            }
        }
    }

}
