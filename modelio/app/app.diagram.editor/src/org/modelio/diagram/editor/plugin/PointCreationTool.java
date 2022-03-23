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

}
