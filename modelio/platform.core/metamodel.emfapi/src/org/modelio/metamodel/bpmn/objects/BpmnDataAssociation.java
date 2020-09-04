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
package org.modelio.metamodel.bpmn.objects;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnDataAssociation v0.0.9054
 * 
 * 
 * <p>Data Associations are used to move data between Data Objects, Properties, and inputs and outputs of Activities, Processes, and GlobalTasks.</p><p>Tokens do not flow along a Data Association, and as a result they have no direct effect on the flow of the Process. The purpose of retrieving data from Data Objects or Process Data Inputs is to fill the Activities inputs and later push the output values from the execution of the Activity back into Data Objects or Process Data Outputs.</p><p>The DataAssociation class is a BaseElement contained by an Activity or Event, used to model how data is pushed into or pulled from item-aware elements.</p><p>DataAssociation elements may have one or more sources and a target; the source of the association is copied into the target.</p>
 */
@objid ("00047c84-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnDataAssociation extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("3114b499-4e10-4b1d-a8a7-545562b367ce")
    public static final String MNAME = "BpmnDataAssociation";

    /**
     * The metaclass qualified name.
     */
    @objid ("3b568d76-77ef-418a-836f-1d753aedfc42")
    public static final String MQNAME = "Standard.BpmnDataAssociation";

    /**
     * Getter for attribute 'BpmnDataAssociation.Assignment'
     * 
     * Metamodel description:
     * <i>Specifies one or more data elements Assignments. By using an Assignment, single data structure elements can be assigned from the source structure to the target structure.</i>
     */
    @objid ("5977ab5b-a618-450e-b7d7-d1819bf6b8f6")
    String getAssignment();

    /**
     * Setter for attribute 'BpmnDataAssociation.Assignment'
     * 
     * Metamodel description:
     * <i>Specifies one or more data elements Assignments. By using an Assignment, single data structure elements can be assigned from the source structure to the target structure.</i>
     */
    @objid ("20899dff-09be-4d22-9741-e6149b934b53")
    void setAssignment(String value);

    /**
     * Getter for attribute 'BpmnDataAssociation.Transfomation'
     * 
     * Metamodel description:
     * <i>Specifies an optional transformation Expression. The actual scope of visible data for that Expression is defined by the source and target of the specific data association types.</i>
     */
    @objid ("36ed96e6-f380-4e56-befa-ae5a6737aade")
    String getTransfomation();

    /**
     * Setter for attribute 'BpmnDataAssociation.Transfomation'
     * 
     * Metamodel description:
     * <i>Specifies an optional transformation Expression. The actual scope of visible data for that Expression is defined by the source and target of the specific data association types.</i>
     */
    @objid ("d31e3bde-12d3-493d-92bf-75504fbc8db8")
    void setTransfomation(String value);

    /**
     * Getter for attribute 'BpmnDataAssociation.Language'
     * 
     * Metamodel description:
     * <i>Language used for the assignment expression.</i>
     */
    @objid ("c0cdd31c-f314-4417-a99d-d61e11c42485")
    String getLanguage();

    /**
     * Setter for attribute 'BpmnDataAssociation.Language'
     * 
     * Metamodel description:
     * <i>Language used for the assignment expression.</i>
     */
    @objid ("4d83c1f6-bc15-4d27-a608-b9abc6dc29e1")
    void setLanguage(String value);

    /**
     * Getter for relation 'BpmnDataAssociation->SourceRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2f56d9bc-24e3-468a-b8d4-c7403dfcf3b7")
    EList<BpmnItemAwareElement> getSourceRef();

    /**
     * Filtered Getter for relation 'BpmnDataAssociation->SourceRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5eed70e0-127e-4409-bb78-b65a0ec658cd")
    <T extends BpmnItemAwareElement> List<T> getSourceRef(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnDataAssociation->TargetRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1c1e8963-bed9-4603-833e-4dbcd284ca38")
    BpmnItemAwareElement getTargetRef();

    /**
     * Setter for relation 'BpmnDataAssociation->TargetRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b54e8e6b-2b68-4d4a-a845-6074ee407535")
    void setTargetRef(BpmnItemAwareElement value);

    /**
     * Getter for relation 'BpmnDataAssociation->EndingActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ab8200ba-3e37-4d86-b51e-038d7811c4e7")
    BpmnActivity getEndingActivity();

    /**
     * Setter for relation 'BpmnDataAssociation->EndingActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4325234b-8483-44a6-bd63-54fa8c7a22af")
    void setEndingActivity(BpmnActivity value);

    /**
     * Getter for relation 'BpmnDataAssociation->StartingActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9beb2d84-78a4-47bd-87df-284e2633dc5e")
    BpmnActivity getStartingActivity();

    /**
     * Setter for relation 'BpmnDataAssociation->StartingActivity'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b342387a-3a7b-41ff-b1bc-1c130c38ce27")
    void setStartingActivity(BpmnActivity value);

    /**
     * Getter for relation 'BpmnDataAssociation->StartingEvent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c5ef17fa-25b9-496a-865d-7f8c56d9c6c4")
    BpmnThrowEvent getStartingEvent();

    /**
     * Setter for relation 'BpmnDataAssociation->StartingEvent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8eae2ce4-08d2-446a-986c-e1968d2224b3")
    void setStartingEvent(BpmnThrowEvent value);

    /**
     * Getter for relation 'BpmnDataAssociation->VisualShortCut'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7acce163-46d7-4dff-ba1e-cf4d2280a677")
    EList<BpmnSequenceFlowDataAssociation> getVisualShortCut();

    /**
     * Filtered Getter for relation 'BpmnDataAssociation->VisualShortCut'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e507e2a6-4de5-401b-afe4-7024f63d1076")
    <T extends BpmnSequenceFlowDataAssociation> List<T> getVisualShortCut(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnDataAssociation->EndingEvent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7a747b5c-a243-4522-98dc-d88783876766")
    BpmnCatchEvent getEndingEvent();

    /**
     * Setter for relation 'BpmnDataAssociation->EndingEvent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a2ac3587-1152-409d-83ba-3b060ecc0c21")
    void setEndingEvent(BpmnCatchEvent value);

}
