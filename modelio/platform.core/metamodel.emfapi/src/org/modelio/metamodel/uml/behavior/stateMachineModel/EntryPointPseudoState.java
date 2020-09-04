/* 
 * Copyright 2013-2019 Modeliosoft
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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.behavior.stateMachineModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;

/**
 * EntryPointPseudoState v0.0.9054
 * 
 * 
 * An entry point pseudostate is an entry point of a state machine or composite state. In each region of the state machine or composite state it has a single transition to a vertex within the same region.
 */
@objid ("004f3f3a-c4bf-1fd8-97fe-001ec947cd2a")
public interface EntryPointPseudoState extends AbstractPseudoState {
    /**
     * The metaclass simple name.
     */
    @objid ("14ee4bc5-bf18-420d-bbd1-574788c5839a")
    public static final String MNAME = "EntryPointPseudoState";

    /**
     * The metaclass qualified name.
     */
    @objid ("7623c375-0abb-4cf2-94d7-d986e3b9b749")
    public static final String MQNAME = "Standard.EntryPointPseudoState";

    /**
     * Getter for relation 'EntryPointPseudoState->EntryOf'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("fd0d685f-8647-4fc5-a031-cfd51217871f")
    State getEntryOf();

    /**
     * Setter for relation 'EntryPointPseudoState->EntryOf'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("05d93fc3-ead4-420c-aca2-ac3753cb7778")
    void setEntryOf(State value);

    /**
     * Getter for relation 'EntryPointPseudoState->Connection'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("c167c1b7-9cdb-4017-98ca-df52d4166cf7")
    EList<ConnectionPointReference> getConnection();

    /**
     * Filtered Getter for relation 'EntryPointPseudoState->Connection'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("23e41fac-58c2-49c5-8193-c5f6aba6afda")
    <T extends ConnectionPointReference> List<T> getConnection(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'EntryPointPseudoState->EntryOfMachine'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0ed5acc1-2d43-43cd-90cd-7856c5db3cbb")
    StateMachine getEntryOfMachine();

    /**
     * Setter for relation 'EntryPointPseudoState->EntryOfMachine'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6411fe8b-376e-4eec-b4dc-6528ebc81a19")
    void setEntryOfMachine(StateMachine value);

}
