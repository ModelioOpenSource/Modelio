/* 
 * Copyright 2013-2018 Modeliosoft
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
package org.modelio.metamodel.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * ActivityEdge v0.0.9054
 * 
 * 
 * ActivityEdge is an abstract class for the connections along which tokens flow between activity nodes. It covers control and data flow edges. 
 * 
 * Activity edges can control token flow.
 */
@objid ("00270a1a-c4bf-1fd8-97fe-001ec947cd2a")
public interface ActivityEdge extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("86fc8a24-d929-49b1-ac99-7ffd0af72f70")
    public static final String MNAME = "ActivityEdge";

    /**
     * The metaclass qualified name.
     */
    @objid ("654989d6-362c-4110-90df-5d5f7bbffc42")
    public static final String MQNAME = "Standard.ActivityEdge";

    /**
     * Getter for attribute 'ActivityEdge.Guard'
     * 
     * Metamodel description:
     * <i>Specification evaluated at runtime to determine if the edge can be traversed. </i>
     */
    @objid ("1c1d862e-62d0-49a2-bd68-0255cd773bcb")
    String getGuard();

    /**
     * Setter for attribute 'ActivityEdge.Guard'
     * 
     * Metamodel description:
     * <i>Specification evaluated at runtime to determine if the edge can be traversed. </i>
     */
    @objid ("7a4298ae-c76f-46bd-bee6-8ebcd6b316b5")
    void setGuard(String value);

    /**
     * Getter for attribute 'ActivityEdge.Weight'
     * 
     * Metamodel description:
     * <i>Number of tokens consumed from the source node on each traversal.</i>
     */
    @objid ("c5239dbe-1843-42f8-b7a9-0832d6da205c")
    String getWeight();

    /**
     * Setter for attribute 'ActivityEdge.Weight'
     * 
     * Metamodel description:
     * <i>Number of tokens consumed from the source node on each traversal.</i>
     */
    @objid ("90b2f773-ffa9-4b6a-ae53-5bc78a8d34bb")
    void setWeight(String value);

    /**
     * Getter for relation 'ActivityEdge->Target'
     * 
     * Metamodel description:
     * <i>Node to which tokens are put when they traverse the edge.</i>
     */
    @objid ("3b9e6485-10bb-41dc-a4b5-2e4d050ce98c")
    ActivityNode getTarget();

    /**
     * Setter for relation 'ActivityEdge->Target'
     * 
     * Metamodel description:
     * <i>Node to which tokens are put when they traverse the edge.</i>
     */
    @objid ("ea02e10d-dc72-4146-9e63-7008d99beb80")
    void setTarget(ActivityNode value);

    /**
     * Getter for relation 'ActivityEdge->Source'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b22358e4-9995-4008-b4cb-2fd92455a274")
    ActivityNode getSource();

    /**
     * Setter for relation 'ActivityEdge->Source'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fd552e24-8ecb-4da9-b0e2-c5d354e72dc8")
    void setSource(ActivityNode value);

    /**
     * Getter for relation 'ActivityEdge->Interrupts'
     * 
     * Metamodel description:
     * <i>Region that the edge can interrupt.</i>
     */
    @objid ("293e4ca5-42c2-44d7-be6e-d2eebaa53f1f")
    InterruptibleActivityRegion getInterrupts();

    /**
     * Setter for relation 'ActivityEdge->Interrupts'
     * 
     * Metamodel description:
     * <i>Region that the edge can interrupt.</i>
     */
    @objid ("a026a71d-2a0d-4795-956c-f4a0fbc4fabe")
    void setInterrupts(InterruptibleActivityRegion value);

    /**
     * Getter for relation 'ActivityEdge->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("90f1cb58-8242-4480-8565-36b34020e6c3")
    EList<InformationFlow> getRealizedInformationFlow();

    /**
     * Filtered Getter for relation 'ActivityEdge->RealizedInformationFlow'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8202b523-94e7-43f5-9adc-2a3bdaf5db74")
    <T extends InformationFlow> List<T> getRealizedInformationFlow(java.lang.Class<T> filterClass);

}
