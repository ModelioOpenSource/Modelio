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

package org.modelio.metamodel.bpmn.objects;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnSequenceFlowDataAssociation v0.0.9054
 * 
 * 
 * <p>Data Objects may be directly associated with a Sequence Flow connector to represent two data associations. This is a visual short cut that normalizes two Data Associations: one from a item-aware element (e.g., an Activity) contained by the source of the Sequence Flow, connecting to the Data Object; and the other from the Data Object connecting to a item-aware element contained by the target of the Sequence Flow.</p><p>This is an extension of Modelio to the BPMN standard metamodel.</p><p>Note: See ownership, this could be a&nbsp;process/Subprocess</p>
 * 
 * 
 * 
 */
@objid ("00051888-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnSequenceFlowDataAssociation extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("b2eff8ff-1316-418d-92d4-ec00088d3bf4")
    public static final String MNAME = "BpmnSequenceFlowDataAssociation";

    /**
     * The metaclass qualified name.
     */
    @objid ("5d49f463-8993-4694-8145-426722345ebc")
    public static final String MQNAME = "Standard.BpmnSequenceFlowDataAssociation";

    /**
     * Getter for relation 'BpmnSequenceFlowDataAssociation->Connected'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c9b37fc2-b506-43fe-abdf-32f4deecfa56")
    BpmnSequenceFlow getConnected();

    /**
     * Setter for relation 'BpmnSequenceFlowDataAssociation->Connected'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("188659ee-0606-4e4f-9d5c-135852c9892d")
    void setConnected(BpmnSequenceFlow value);

    /**
     * Getter for relation 'BpmnSequenceFlowDataAssociation->DataAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d550cb61-d9e4-4d6f-9102-4681c588de3f")
    EList<BpmnDataAssociation> getDataAssociation();

    /**
     * Filtered Getter for relation 'BpmnSequenceFlowDataAssociation->DataAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("3102f475-ed4f-4890-b893-394092f20915")
    <T extends BpmnDataAssociation> List<T> getDataAssociation(java.lang.Class<T> filterClass);
}

