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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.uml.informationFlow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;

/**
 * DataFlow v0.0.9054
 * 
 * 
 * DataFlows are the representation of all types of information that can be transmitted between elements. For example, DataFlows can be objects or requests. 
 * 
 * A DataFlow between elements expresses that the kind of information that it represents (defined through its ModelSignal) can circulate between the connected elements. This can provide high level (system level) information exchange diagrams.
 * 
 * 
 */
@objid ("00645dc0-c4bf-1fd8-97fe-001ec947cd2a")
public interface DataFlow extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("31739a3f-07b1-4781-a059-0f73c62a55e8")
    public static final String MNAME = "DataFlow";

    /**
     * The metaclass qualified name.
     */
    @objid ("85632579-4280-4520-8174-53d11999f109")
    public static final String MQNAME = "Standard.DataFlow";

    /**
     * Getter for relation 'DataFlow->Destination'
     * 
     * Metamodel description:
     * <i>Designates the NameSpaces (Packages, Classes, and so on) that are targeted by the DataFlow.</i>
     * 
     */
    @objid ("cb85610a-2b7a-4fe6-82b8-265f1181c182")
    NameSpace getDestination();

    /**
     * Setter for relation 'DataFlow->Destination'
     * 
     * Metamodel description:
     * <i>Designates the NameSpaces (Packages, Classes, and so on) that are targeted by the DataFlow.</i>
     * 
     */
    @objid ("f686905d-0f36-42e1-b528-731b7e19b571")
    void setDestination(NameSpace value);

    /**
     * Getter for relation 'DataFlow->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("bdb182d5-020e-427e-8a2c-4c2cfd51b425")
    NameSpace getOrigin();

    /**
     * Setter for relation 'DataFlow->Origin'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e165e2fd-e42c-4eb2-9d6c-6ea0fc1bc25d")
    void setOrigin(NameSpace value);

    /**
     * Getter for relation 'DataFlow->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("10216a1f-a118-4a0c-92f5-02c495339c5d")
    NameSpace getOwner();

    /**
     * Setter for relation 'DataFlow->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c01a778a-dfb1-42eb-8feb-b336860cdd3d")
    void setOwner(NameSpace value);

    /**
     * Getter for relation 'DataFlow->SModel'
     * 
     * Metamodel description:
     * <i>Defines the DataFlow as being an instance of the associated Signal.</i>
     * 
     */
    @objid ("80909ac5-15f1-432f-92cc-4d9a6f266f52")
    Signal getSModel();

    /**
     * Setter for relation 'DataFlow->SModel'
     * 
     * Metamodel description:
     * <i>Defines the DataFlow as being an instance of the associated Signal.</i>
     * 
     */
    @objid ("2a253688-e37c-47a0-a1e4-0bcc85b4fd4c")
    void setSModel(Signal value);
}

