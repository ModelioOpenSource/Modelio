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
package org.modelio.diagram.editor.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

/**
 * Modelio node creation tool .
 * <p>
 * Same as {@link CreationTool} with :<ul>
 * <li> Enforcement of square/circle on SHIFT key.
 * <li> The figure is centered on the first mouse click location if CTRL key is pressed.
 * </ul>
 */
@objid ("58b26b3d-9b3f-4913-9ce5-e7c693bcbaf0")
public class NodeCreationTool extends CreationTool {
    @objid ("c3f4f339-fa88-4fe3-a4e5-179ecfc94b8a")
    @Override
    protected void enforceConstraintsForSizeOnDropCreate(CreateRequest request) {
        if (getTargetEditPart() == null) {
            return;
        }
        
        super.enforceConstraintsForSizeOnDropCreate(request);
        
        final Dimension reqSize = request.getSize();
        if (getCurrentInput().isModKeyDown(SWT.SHIFT)) {
            // Enforce square / circle
            int size = Math.max(reqSize.height, reqSize.width);
            reqSize.setSize(size, size);
        }
        
        if (getCurrentInput().isModKeyDown(SWT.CTRL)) {
            // Set center on first click
            reqSize.scale(2);
            Point newStart = getStartLocation().getTranslated(-reqSize.width / 2, -reqSize.height / 2);
            request.setLocation(newStart);
            
        }
        
    }

    @objid ("a8843052-7286-4808-a274-3238d8c90cd9")
    @Override
    protected boolean handleKeyDown(KeyEvent e) {
        if (e.keyCode == SWT.SHIFT || e.keyCode == SWT.CTRL) {
            // The shift modifier is not set when pressing shift ...
            e.stateMask |= e.keyCode;
            getCurrentInput().setInput(e);
        
            // Will recompute all
            handleMove();
        }
        return super.handleKeyDown(e);
    }

    @objid ("2e2e6631-2472-4c62-8e43-1899eecf12ed")
    @Override
    protected boolean handleKeyUp(KeyEvent e) {
        if (e.keyCode == SWT.SHIFT  || e.keyCode == SWT.CTRL) {
            // The shift modifier is not reset when pressing shift ...
            e.stateMask &= ~e.keyCode;
            getCurrentInput().setInput(e);
            
            // Will recompute all
            handleMove();
        }
        return super.handleKeyUp(e);
    }

}
