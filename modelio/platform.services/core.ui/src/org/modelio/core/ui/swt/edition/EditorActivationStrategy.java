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

package org.modelio.core.ui.swt.edition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;

/**
 * This class is responsible to determine if a cell selection event triggers an editor activation.
 * <p/>
 * In this implementation, edition can be triggered:
 * <ul>
 * <li>programmatically</li>
 * <li>by pressing F2</li>
 * <li>with two clicks on the same object in a duration comprised between 0.3 and 1 second</li>
 * </ul>
 */
@objid ("0e4e1c34-1de3-11e2-bcbe-002564c97630")
public class EditorActivationStrategy extends ColumnViewerEditorActivationStrategy implements MouseListener {
    @objid ("14544994-1a71-473f-8d44-bc97be3f33fc")
    private final int deltaMax;

    @objid ("ec223e5c-a6fc-4f83-a7f8-3c866bb1a12d")
    private final int deltaMin;

    @objid ("1fc23824-1de3-11e2-bcbe-002564c97630")
    private int time;

    @objid ("1fc23825-1de3-11e2-bcbe-002564c97630")
    private Object selectedObject;

    @objid ("1fc23826-1de3-11e2-bcbe-002564c97630")
    private final ColumnViewer viewer;

    /**
     * Instantiates the strategy.
     * <p>
     * Edition will be triggered with two clicks on the same object in a duration comprised between 0.3 and 1 second.
     * @param viewer the column viewer
     */
    @objid ("1fc23827-1de3-11e2-bcbe-002564c97630")
    public EditorActivationStrategy(ColumnViewer viewer) {
        this (viewer, true);
    }

    /**
     * Instantiates the strategy.
     * <p>
     * If <i>withTimeDelta</i> is <i>true</i>, edition will be triggered with two clicks on the same object in a duration comprised between 0.3 and 1 second.<br>
     * If <i>withTimeDelta</i> is <i>false</i> edition will be triggered on second click on the same object whatever the delay.
     * @param viewer the column viewer
     * @param withTimeDelta whether to test time delta on second click to activate the editor.
     * @since 3.7.1
     */
    @objid ("df27e739-0ae5-4268-8026-846706f614fa")
    public EditorActivationStrategy(ColumnViewer viewer, boolean withTimeDelta) {
        super(viewer);
        this.viewer = viewer;
        if (withTimeDelta) {
            this.deltaMax = 1000;
            this.deltaMin = 300;
        } else {
            this.deltaMin = 0;
            this.deltaMax = Integer.MAX_VALUE;
        }
        
        viewer.getControl().addMouseListener(this);
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(org.eclipse.swt.events.MouseEvent)
     */
    @objid ("1fc23830-1de3-11e2-bcbe-002564c97630")
    @Override
    public void mouseDoubleClick(MouseEvent e) {
        // Nothing to do
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.swt.events.MouseListener#mouseDown(org.eclipse.swt.events.MouseEvent)
     */
    @objid ("1fc23835-1de3-11e2-bcbe-002564c97630")
    @Override
    public void mouseDown(MouseEvent e) {
        // Nothing to do
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.swt.events.MouseListener#mouseUp(org.eclipse.swt.events.MouseEvent)
     */
    @objid ("1fc2383a-1de3-11e2-bcbe-002564c97630")
    @Override
    public void mouseUp(MouseEvent e) {
        ViewerCell cell = this.viewer.getCell(new Point(e.x, e.y));
        if (cell == null) {
            return;
        }
        
        // Retrieve selected object
        Object sourceObject = cell.getElement();
        
        if (sourceObject == null) {
            return;
        }
        
        // Report from Eclipse doc about MouseEvent.button :
        // Note: The MouseEvent.button field is an integer that represents the mouse button number. 
        // This is not the same as the SWT mask constants BUTTONx.
        // The button that was pressed or released: 1 for the first button, 2 for the second button, and 3 for the third button, etc.
        
        // We enter edition only when left button is pressed
        if (e.button != 1) {
            return;
        } 
        
        if (this.time == 0) {
            this.time = e.time;
            this.selectedObject = sourceObject;
            return;
        } else {
            int delta = e.time - this.time;
        
            if (delta > this.deltaMin && delta < this.deltaMax && this.selectedObject == sourceObject) {
                this.time = 0;
                this.selectedObject = sourceObject;
                this.viewer.editElement(sourceObject, cell.getColumnIndex());
                return;
            } else {
                this.time = e.time;
                this.selectedObject = sourceObject;
                return;
            }
        }
    }

    @objid ("1fc2382a-1de3-11e2-bcbe-002564c97630")
    @Override
    protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
        if (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.F2) {
            return true;
        } else if (event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC) {
            return true;
        }
        return false;
    }

}
