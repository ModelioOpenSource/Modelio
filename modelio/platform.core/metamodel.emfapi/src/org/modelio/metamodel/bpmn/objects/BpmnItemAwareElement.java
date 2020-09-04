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
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;

/**
 * BpmnItemAwareElement v0.0.9054
 * 
 * 
 * <p>Several elements in BPMN are subject to store or convey items during process execution. These elements are referenced generally as &quot;item-aware elements&quot;.&nbsp;This is similar to the variable construct common to many languages. As with variables, these elements have a ItemDefinition.</p><p>The data structure these elements hold is specified using an associated ItemDefinition. An item-aware element may be underspecified, meaning that the structure attribute of its ItemDefinition is optional if the modeler does not wish to define the structure of the associated data.</p><p>Ownership<br />
 * An ItemAwareElement belongs to a Flow Element COntainer or a SubProcess.</p>
 */
@objid ("0006fc34-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnItemAwareElement extends BpmnFlowElement {
    /**
     * The metaclass simple name.
     */
    @objid ("2fe549f8-3f6c-4165-affa-9c833b04554a")
    public static final String MNAME = "BpmnItemAwareElement";

    /**
     * The metaclass qualified name.
     */
    @objid ("92737e58-71fd-4d9b-9c7e-04063f527768")
    public static final String MQNAME = "Standard.BpmnItemAwareElement";

    /**
     * Getter for relation 'BpmnItemAwareElement->TargetOfDataAssociation'
     * 
     * Metamodel description:
     * <i>Data associations that computes the value of this element.</i>
     */
    @objid ("ef92d9d1-93c5-49d4-9ec8-65c4eb806567")
    EList<BpmnDataAssociation> getTargetOfDataAssociation();

    /**
     * Filtered Getter for relation 'BpmnItemAwareElement->TargetOfDataAssociation'
     * 
     * Metamodel description:
     * <i>Data associations that computes the value of this element.</i>
     */
    @objid ("3390963b-d6b2-4509-8979-45543db839db")
    <T extends BpmnDataAssociation> List<T> getTargetOfDataAssociation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnItemAwareElement->ItemSubjectRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("dba9c7e2-545f-4c12-9866-f5f1258ee9f9")
    BpmnItemDefinition getItemSubjectRef();

    /**
     * Setter for relation 'BpmnItemAwareElement->ItemSubjectRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("63776972-d780-48de-b4bd-5d34faa2cbf1")
    void setItemSubjectRef(BpmnItemDefinition value);

    /**
     * Getter for relation 'BpmnItemAwareElement->DataState'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("331f64c4-884c-4de9-a104-ba68088d9e2b")
    BpmnDataState getDataState();

    /**
     * Setter for relation 'BpmnItemAwareElement->DataState'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("dec04375-0805-4a30-ba61-5edb1bdcf44a")
    void setDataState(BpmnDataState value);

    /**
     * Getter for relation 'BpmnItemAwareElement->SourceOfDataAssociation'
     * 
     * Metamodel description:
     * <i>Data associations that use this element to compute a value.</i>
     */
    @objid ("a721af22-ba64-4339-a50c-349201da2a69")
    EList<BpmnDataAssociation> getSourceOfDataAssociation();

    /**
     * Filtered Getter for relation 'BpmnItemAwareElement->SourceOfDataAssociation'
     * 
     * Metamodel description:
     * <i>Data associations that use this element to compute a value.</i>
     */
    @objid ("41478043-fa77-40f4-a16f-953b85cd578a")
    <T extends BpmnDataAssociation> List<T> getSourceOfDataAssociation(java.lang.Class<T> filterClass);

}
