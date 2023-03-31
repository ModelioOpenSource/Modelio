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
package org.modelio.diagram.editor.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.tools.CreationTool;
import org.modelio.diagram.elements.core.requests.RequestTypes;

/**
 * Specialised Creation Tool that create with a single point (does not use a Rectangle but a Point).
 */
@objid ("667336a6-33f7-11e2-95fe-001ec947c8cc")
public class PointCreationTool extends CreationTool {
    @objid ("667336a8-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleButtonDown(int button) {
        if (button != 1) {
            setState(STATE_INVALID);
            handleInvalidInput();
            return true;
        }
        if (stateTransition(STATE_INITIAL, STATE_TERMINAL)) {
            eraseTargetFeedback();
            unlockTargetEditPart();
            performCreation(button);
        }
        
        setState(STATE_TERMINAL);
        handleFinished();
        return true;
    }

    /**
     * Change Create Request Type if ControlKey is pressed. Initiate an "Unmask or Create children" request.
     */
    @objid ("dc11587c-6b6a-4ffa-94a1-a3d9f94b6934")
    @Override
    protected void updateTargetRequest() {
        super.updateTargetRequest();
        if(getCurrentInput().isControlKeyDown()) {
          getTargetRequest().setType(RequestTypes.UNMASK_OR_CREATE_CHILDREN);
        }else {
            getTargetRequest().setType(REQ_CREATE);
        }
        
    }

}
