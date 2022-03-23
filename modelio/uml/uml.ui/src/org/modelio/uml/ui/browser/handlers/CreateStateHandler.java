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

import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.stateMachineModel.DeepHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ShallowHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.browser.view.handlers.create.CreateElementHandler;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("899ecbe9-c9d8-11e1-b479-001ec947c8cc")
public class CreateStateHandler extends CreateElementHandler {
    @objid ("43bcdea8-649e-4002-8a33-b9ca7a2d3729")
    private static final List<Class<?>> allowedMetaclasses = Arrays.asList(EntryPointPseudoState.class, ExitPointPseudoState.class,  ShallowHistoryPseudoState.class, DeepHistoryPseudoState.class);

    @objid ("4e4bf6eb-ccde-11e1-97e5-001ec947c8cc")
    private static StateMachine getEnclosingStateMachine(State s) {
        State state = s;
        Region region = null;
        while ((state != null)) {
            // Get the region holding the state:
            // either it is a direct child of the StateMachine
            // or it is a child of a parent State.
            region = state.getParent();
            if (region.getRepresented() == null) {
                state = region.getParent();
            } else {
                return region.getRepresented();
            }
        }
        return null;
    }

    @objid ("4e4bf6f0-ccde-11e1-97e5-001ec947c8cc")
    private boolean isAllowed(MClass metaclass) {
        return allowedMetaclasses.contains(metaclass.getJavaInterface());
    }

    @objid ("00535e6c-d035-1006-9c1d-001ec947cd2a")
    @Override
    protected boolean doCanExecute(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype) {
        if (owner instanceof State) {
            StateMachine stateMachine = getEnclosingStateMachine((State) owner);
            return (stateMachine != null) && (stateMachine.getKind() == KindOfStateMachine.PROTOCOL)
                    && isAllowed(metaclass) && super.doCanExecute(owner, metaclass, dependency, stereotype);
        
        }
        return super.doCanExecute(owner, metaclass, dependency, stereotype);
    }

}
