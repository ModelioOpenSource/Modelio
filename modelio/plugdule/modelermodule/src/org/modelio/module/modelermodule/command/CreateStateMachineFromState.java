/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.module.modelermodule.command;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.module.modelermodule.engine.StateUpdater;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create a bus state machine from a state having entry/exit points.
 */
@objid ("3ae138ad-d6e8-461d-96a2-f0f7f8ac6e09")
public class CreateStateMachineFromState extends DefaultModuleCommandHandler {
    @objid ("3eb3303b-4e23-4add-861a-72a59bfbc7b8")
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("CreateStateMachineFromState")) {
            State state = (State) selectedElements.get(0);
            StateUpdater stateWizard = new StateUpdater();
            stateWizard.createSubStateMachineFromCompositeState(session, state);
        
            transaction.commit();
        }
        
    }

    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @objid ("81d6e7b7-556f-44c5-9965-1adc33e5d04e")
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        
        if (selectedElements.size() != 1) {
            return false;
        }
        
        State selectedState = (State) selectedElements.get(0);
        StateMachine stateMachine = selectedState.getSubMachine();
        return (stateMachine == null);
    }

    /**
     * This method presizes if a command has to be desactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @objid ("3807c01a-bde0-42cf-9d7f-567be7dd19f7")
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}
