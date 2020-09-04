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

package org.modelio.core.ui.swt.multistring;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;

@objid ("8dca8c68-c068-11e1-8c0a-002564c97630")
class StringAdapterEditorActivationStrategy extends ColumnViewerEditorActivationStrategy {
    @objid ("8dca8c69-c068-11e1-8c0a-002564c97630")
     int time = 0;

    @objid ("2a31f4a5-4744-4b7f-adbd-2f160a8c54af")
     String selectedElement = null;

    @objid ("8dca8c6b-c068-11e1-8c0a-002564c97630")
    public StringAdapterEditorActivationStrategy(ColumnViewer viewer) {
        super(viewer);
    }

    @objid ("8dca8c6e-c068-11e1-8c0a-002564c97630")
    @Override
    protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
        if (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.F2) {
            return true;
        } else if (event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC) {
            return true;
        } else if (event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION) {
            // Retrieve selected element:
            //---------------------------
            String sourceElement = null;
        
            Object eventSource = event.getSource();
            if (eventSource instanceof ViewerCell) {
                ViewerCell sourceCell = (ViewerCell) eventSource;
                Object sourceObject = sourceCell.getElement();
                if (sourceObject instanceof String) {
                    sourceElement = (String)sourceObject;
                }
            }
        
            if (sourceElement == null) {
                return false;
            }
        
            if (this.time == 0) {
                this.time = event.time;
                this.selectedElement = sourceElement;
                return false;
            } else {
                int delta = event.time - this.time;
        
                if (delta > 300 && delta < 1000 && this.selectedElement == sourceElement) {
                    this.time = 0;
                    this.selectedElement = sourceElement;
                    return true;
                } else {
                    this.time = event.time;
                    this.selectedElement = sourceElement;
                    return false;
                }
            }
        }
        return false;
    }

}
