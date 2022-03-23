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
package org.modelio.module.modelermodule.engine;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.gui.InputDialog;
import org.modelio.module.modelermodule.gui.ShellHelper;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Service class managing synchronization between states and sub state machines.
 */
@objid ("bf322a60-5f61-42fb-b659-dca164ed0060")
public class StateUpdater {
    /**
     * Create a sub state machine from a composite state having entry/exit points.
     * @param session The modeling session
     * @param state The source state
     * @return : The created StateMachine
     */
    @objid ("a24c8a86-26a8-420c-9bdb-152a041dc288")
    public StateMachine createSubStateMachineFromCompositeState(final IModelingSession session, final State state) {
        if ((state.getSubMachine() == null)
                && ((state.getOwnedRegion().size() == 0)
                        || ((state.getOwnedRegion().size() ==1 )
                                &&  (state.getOwnedRegion().get(0).getSub().size() == 0)))){
        
            // Choose a name for the new state machine
            String newName = InputDialog.showInputDialog(ShellHelper.findActiveShell(),
                    I18nMessageService.getString("module.gui.process.stateMachineName"),
                    I18nMessageService.getString("module.gui.process.chooseName"),
                    state.getName());
        
            if (newName == null || newName.equals("")) {
                return null;
            }
        
        
            Region region = state.getParent();
            MObject element = region.getCompositionOwner();
        
            while (!(element instanceof StateMachine)) {
                element = element.getCompositionOwner().getCompositionOwner();
            }
        
            NameSpace creationDestination = ((StateMachine) element).getOwner();
        
            StateMachine subMachine = session.getModel().createStateMachine();
            creationDestination.getOwnedBehavior().add(subMachine);
            subMachine.setName(newName);
            state.setSubMachine(subMachine);
        
            // delete owned region if exits
            if (state.getOwnedRegion().size() ==1)
                state.getOwnedRegion().get(0).delete();
        
            Region topRegion = subMachine.getTop();
            if (topRegion == null){
                topRegion = session.getModel().createRegion();
                subMachine.setTop(topRegion);
            }
        
            for (ConnectionPointReference connect : new ArrayList<>(state.getConnection())){
                connect.delete();
            }
        
            for (EntryPointPseudoState entry : state.getEntryPoint()) {
        
                EntryPointPseudoState newEntry = createEntryPoint(session, entry, topRegion);
        
                ConnectionPointReference connectionPoint = createConnectionPoint(session, state, newEntry);
        
                for (Transition transition : entry.getIncoming()){
                    transition.setTarget(connectionPoint);
                }
        
                for (Transition transition : new ArrayList<>(entry.getOutGoing())){
                    transition.delete();
                }
        
                entry.delete();
        
            }
        
            for (ExitPointPseudoState exit : new ArrayList<>(state.getExitPoint())) {
        
                ExitPointPseudoState newExit = createExitPoint(session, exit, topRegion);
        
                ConnectionPointReference connectionPoint = createConnectionPoint(session, state, newExit);
        
                for (Transition transition : new ArrayList<>(exit.getIncoming())){
                    transition.delete();
                }
        
                for (Transition transition : exit.getOutGoing()){
                    transition.setSource(connectionPoint);
                }
        
                exit.delete();
            }
        
        
            return subMachine;
        }
        return null;
    }

    @objid ("edf390e7-b13a-4448-8cff-e3abebe88ce7")
    private EntryPointPseudoState createEntryPoint(final IModelingSession session, final EntryPointPseudoState entry, final Region topRegion) {
        EntryPointPseudoState result = session.getModel().createEntryPointPseudoState();
        result.setParent(topRegion);
        result.setName(entry.getName());
        return result;
    }

    @objid ("87ba62f5-9902-4f42-9c13-947df0733536")
    private ExitPointPseudoState createExitPoint(final IModelingSession session, final ExitPointPseudoState exit, final Region topRegion) {
        ExitPointPseudoState result = session.getModel().createExitPointPseudoState();
        result.setParent(topRegion);
        result.setName(exit.getName());
        return result;
    }

    /**
     * Updates a sub state machine(entry, exit points)
     * @param session the Modelio modeling session
     * @param state the state to update.
     */
    @objid ("5fd16fba-2952-4ca5-a4cb-b55bbcc9451a")
    public void updateStateFromStateMachine(final IModelingSession session, final State state) throws ModelerModuleException {
        StateMachine stateMachine = state.getSubMachine();
        if (stateMachine != null){
            updateSubState(session, state, stateMachine);
        }else{
            // Choose a class name
            String smName = InputDialog.showInputDialog(ShellHelper.findActiveShell(),
                    I18nMessageService.getString("module.gui.process.stateMachineName"),
                    I18nMessageService.getString("module.gui.process.chooseExistingName"),
                    state.getName());
        
            if (smName != null && !(smName.equals(""))) {
                // Get the package container
                Package container = getContainerPackage(state);
                if (container == null) {
                    throw new ModelerModuleException(
                            I18nMessageService.getString("module.error.updateStateFromSM.rolePackage", state.getName()));
                }
        
                List<Behavior> cl = new ArrayList<>();
                for (Behavior behavior : container.getOwnedBehavior()) {
                    if (smName.equals(behavior.getName())) {
                        cl.add(behavior);
                    }
                }
                if (cl.size() > 0 && cl.get(0) instanceof StateMachine) {
                    StateMachine newStateMachine = (StateMachine) cl.get(0);
                    state.setSubMachine(newStateMachine);
        
                    // Do the update
                    updateSubState(session, state, newStateMachine);
                } else {
                    throw new ModelerModuleException(
                            I18nMessageService.getString("module.error.updatePartFromInstanciedClass.find", smName));
                }
            }
        
        
        }
        
    }

    @objid ("c5913403-e5a8-414f-a4a3-8f99f75ce430")
    private Package getContainerPackage(final State state) {
        MObject container = state.getCompositionOwner();
        while(!(container instanceof Package)){
            container = container.getCompositionOwner();
        }
        return null;
    }

    @objid ("c8a8c196-0905-4b34-bfa2-096dec3afa14")
    private void updateSubState(final IModelingSession session, final State state, final StateMachine stateMachine) {
        ArrayList<EntryPointPseudoState> existingEntries = new ArrayList<>(stateMachine.getEntryPoint());
        ArrayList<ExitPointPseudoState> existingExits = new ArrayList<>(stateMachine.getExitPoint());
        
        //searching existing match
        for (ConnectionPointReference pointReference : new ArrayList<>(state.getConnection())) {
            //entry point case
            EntryPointPseudoState entry = pointReference.getEntry();
            if (entry != null) {
                if (entry.getEntryOfMachine().equals(stateMachine)) {
                    pointReference.setName(entry.getName());
                    existingEntries.remove(entry);
                    break;
                } else {
                    for (EntryPointPseudoState existingEntry : stateMachine.getEntryPoint()) {
                        if (existingEntry.getName().equals(entry.getName())) {
                            pointReference.setEntry(existingEntry);
                            existingEntries.remove(existingEntry);
                            break;
                        }
                    }
                }
            }
        
            //exit point case
            ExitPointPseudoState exit = pointReference.getExit();
            if (exit != null) {
                if (exit.getExitOfMachine().equals(stateMachine)) {
                    pointReference.setName(exit.getName());
                    existingExits.remove(exit);
                    break;
                } else {
                    for (ExitPointPseudoState existingExit : stateMachine.getExitPoint()) {
                        if (existingExit.getName().equals(exit.getName())) {
                            pointReference.setExit(existingExit);
                            existingExits.remove(existingExit);
                            break;
                        }
                    }
                }
        
            }
        
            pointReference.delete();
        
        }
        
        //create missing connection point
        for (EntryPointPseudoState existingEntry : existingEntries) {
            createConnectionPoint(session, state, existingEntry);
        }
        
        for (ExitPointPseudoState existingExit : existingExits) {
            createConnectionPoint(session, state, existingExit);
        }
        
        
        //delete
        for (EntryPointPseudoState existingEntry : stateMachine.getEntryPoint()) {
            boolean exist = false;
            for(ConnectionPointReference connectPoint: new ArrayList<>(existingEntry.getConnection())){
                if (connectPoint.getOwnerState().equals(state)){
                    if (exist)
                        connectPoint.delete();
                    else
                        exist = true;
                }
            }
        }
        
        for (ExitPointPseudoState existingExit : stateMachine.getExitPoint()) {
            boolean exist = false;
            for(ConnectionPointReference connectPoint: existingExit.getConnection()){
                if (connectPoint.getOwnerState().equals(state)){
                    if (exist) {
                        connectPoint.delete();
                    } else {
                        exist = true;
                    }
                }
            }
        }
        
    }

    @objid ("36de419c-95dc-4628-b9db-401499599520")
    private ConnectionPointReference createConnectionPoint(final IModelingSession session, final State state, final ExitPointPseudoState exit) {
        ConnectionPointReference newPoint = session.getModel().createConnectionPointReference();
        state.getConnection().add(newPoint);
        newPoint.setExit(exit);
        newPoint.setName(exit.getName());
        return newPoint;
    }

    @objid ("162e81b3-8d65-4aa8-9391-4b26e191cee3")
    private ConnectionPointReference createConnectionPoint(final IModelingSession session, final State state, final EntryPointPseudoState entry) {
        ConnectionPointReference newPoint = session.getModel().createConnectionPointReference();
        state.getConnection().add(newPoint);
        newPoint.setOwnerState(state);
        newPoint.setEntry(entry);
        newPoint.setName(entry.getName());
        return newPoint;
    }

}
