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
package org.modelio.diagram.editor.popup.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This specific handler creates a PseudoState (Initial, History, Entry, Exit...) under a Region or under the top Region
 * of a StateMachine.
 */
@objid ("668d70ad-33f7-11e2-95fe-001ec947c8cc")
public class CreatePseudoStateHandler extends AbstractDiagramCreateHandler {
    @objid ("668d70af-33f7-11e2-95fe-001ec947c8cc")
    private StateMachine getEnclosingStateMachine(State s) {
        State state = s;
        Region region = null;
        while ((state != null)) {
            // Get the region holding the state:
            // either it is a direct child of the StateMachine
            // or it is a child of a parent State.
            region = state.getParent();
            if (region.getRepresented() == null)
                state = region.getParent();
            else
                return region.getRepresented();
        }
        return null;
    }

    @objid ("668fd2d5-33f7-11e2-95fe-001ec947c8cc")
    private StateMachine getEnclosingStateMachine(Region region) {
        if (region.getRepresented() != null)
            return region.getRepresented();
        // else
        return getEnclosingStateMachine(region.getParent());
    }

    @objid ("0755e898-5e26-11e2-a8be-00137282c51b")
    @Override
    public boolean canExecute(@Named("org.eclipse.ui.selection") ISelection selection) {
        if (super.canExecute(selection)) {
            // Now lets have a look a some specific things:
            MObject selectedElement = getSelectedElement();
            StateMachine stateMachine = null;
            if (selectedElement.getClass() == State.class) {
                stateMachine = getEnclosingStateMachine((State) selectedElement);
            } else if (selectedElement.getClass() == Region.class) {
                stateMachine = getEnclosingStateMachine((Region) selectedElement);
            } else if (selectedElement.getClass() == StateMachine.class) {
                stateMachine = (StateMachine) selectedElement;
            }
            if ((stateMachine != null) && (stateMachine.getKind() == KindOfStateMachine.PROTOCOL)) {
                // Protocol StateMachines should not allow their states
                // to have entry or exit points.
                // also they should not have shallow and deep history pseudo states
                // oci: I will suppose that means any state, i.e., no shallow or
                // deep PSs should be available for any state in a PSM.
                return (!("EntryPointPseudoState".equals(this.metaclass) ||
                                 "ExitPointPseudoState".equals(this.metaclass) ||
                                 "ShallowHistoryPseudoState".equals(this.metaclass) || "DeepHistoryPseudoState".equals(this.metaclass)));
            
            }
        }
        return false;
    }

}
