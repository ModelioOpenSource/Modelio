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

package org.modelio.uml.ui.browser.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("8b63cab5-c9d8-11e1-b479-001ec947c8cc")
public class CreateProtocolStateMachineHandler extends CreateStateMachineHandler {
    @objid ("4e4e5956-ccde-11e1-97e5-001ec947c8cc")
    @Override
    protected void postCreationStep(MObject createdElement, IMModelServices mmServices) {
        StateMachine stateMachine = (StateMachine) createdElement;
        // set the state machine as "protocol"
        stateMachine.setKind(KindOfStateMachine.PROTOCOL);
        super.postCreationStep(createdElement, mmServices);
    }

}
