/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;

@objid ("67cdef1f-48e8-42b5-ac72-125480a25dc1")
public class StateMachineCreator {
    @objid ("efda2562-f77a-44dc-a9e2-787bd2b6b6d4")
    private State selectedState;

    @objid ("ff5a8f2b-b15f-470d-9613-c4d9ab983310")
    public StateMachineCreator(final State selectedElement) {
        this.selectedState = selectedElement;
    }

    @objid ("ffe70f0a-2c69-4c64-948b-427cef9a00c6")
    public void stateMachineSmartCreation() {
        List<ExitPointPseudoState> exits = new ArrayList<>();
        List<EntryPointPseudoState> entries =  new ArrayList<>();
        Region topRegion = this.selectedState.getParent();
        StateMachine stateMachineParent = topRegion.getRepresented();
        NameSpace creationDestination = stateMachineParent.getOwner();
        StateMachine stateMachine = null;
        IModelingSession session = ModelerModuleModule.getInstance().getModuleContext().getModelingSession();
        
        // create the state machine
        stateMachine = session.getModel().createStateMachine();
        creationDestination.getOwnedBehavior().add(stateMachine);
        stateMachine.setName(this.selectedState.getName());
        this.selectedState.setSubMachine(stateMachine);
        
        Region topRegionSM = stateMachine.getTop();
        List<ConnectionPointReference> points = this.selectedState.getConnection();
        for (ConnectionPointReference connectionPointReference : points) {
        
            List<Transition> incomingTransition = connectionPointReference.getIncoming();
            if (incomingTransition.size() != 0) {
                EntryPointPseudoState entry = session.getModel().createEntryPointPseudoState();
                entry.setName(connectionPointReference.getName());
                topRegionSM.getSub().add(entry);
                connectionPointReference.setEntry(entry);
            } else {
                List<Transition> outgoingTransition = connectionPointReference.getOutGoing();
                if (outgoingTransition.size() != 0) {
                    ExitPointPseudoState exit = session.getModel().createExitPointPseudoState();
                    exit.setName(connectionPointReference.getName());
                    topRegionSM.getSub().add(exit);
                    connectionPointReference.setExit(exit);
                }
            }
        }
        
        createDiagram(session, stateMachine, exits, entries);
    }

    @objid ("c407eaa1-ec28-41f3-984a-20cee47a5ef9")
    public void createDiagram(final IModelingSession session, final StateMachine stateMachine, final List<ExitPointPseudoState> exits, final List<EntryPointPseudoState> entries) {
        StateMachineDiagram diagram = session.getModel().createStateMachineDiagram(stateMachine.getName(), stateMachine, null);
        
        try (IDiagramHandle rep = ModelerModuleModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getDiagramHandle(diagram)) {
        
            unmaskInLine(entries, 50, rep);
            unmaskInLine(exits, 600, rep);
        
            rep.save();
            rep.close();
        }
    }

    @objid ("cfd98a38-4f23-4eb4-a76a-b4b6faa7342e")
    public void unmaskInLine(final List<? extends StateVertex> vertexes, final int x, final IDiagramHandle rep) {
        int currentX = x;
        int currentY = 50;
        
        for (StateVertex vertex : vertexes) {
            List<IDiagramGraphic> graphics = rep.unmask(vertex, currentX, currentY);
            for (IDiagramGraphic graphic : graphics){
                if (graphic instanceof IDiagramNode){
                    ((IDiagramNode) graphic).setBounds(new Rectangle(currentX, currentY, 50, 50));
                    break;
                }
            }
                        
            currentY += 100;                        
        }
    }

}
