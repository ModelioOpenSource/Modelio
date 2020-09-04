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
package org.modelio.metamodel.bpmn.activities;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;

/**
 * BpmnTask v0.0.9054
 * 
 * 
 * A Task is an atomic Activity within a Process flow. A Task is used when the work in the Process cannot be broken down to a finer level of detail. Generally, an end-user and/or applications are used to perform the Task when it is executed.
 */
@objid ("0084bb4c-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnTask extends BpmnActivity {
    /**
     * The metaclass simple name.
     */
    @objid ("2b879090-5d50-40b7-9419-ecf030b534c5")
    public static final String MNAME = "BpmnTask";

    /**
     * The metaclass qualified name.
     */
    @objid ("2597b151-0b58-4cf9-9d8e-d533f9a3cd31")
    public static final String MQNAME = "Standard.BpmnTask";

    /**
     * Getter for attribute 'BpmnTask.IsGlobal'
     * 
     * Metamodel description:
     * <i>A Global Task is a reusable, atomic Task definition that can be called from within any Process by a Call Activity.</i>
     */
    @objid ("530cb6be-8c2f-4c25-bc21-eb29f4abd4ba")
    boolean isIsGlobal();

    /**
     * Setter for attribute 'BpmnTask.IsGlobal'
     * 
     * Metamodel description:
     * <i>A Global Task is a reusable, atomic Task definition that can be called from within any Process by a Call Activity.</i>
     */
    @objid ("04b0aeb4-94fb-49d5-9f23-ef15b83e121f")
    void setIsGlobal(boolean value);

    /**
     * Getter for relation 'BpmnTask->Caller'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b60e4f0b-3b4d-42d5-ba69-9ae7e06c34bd")
    EList<BpmnCallActivity> getCaller();

    /**
     * Filtered Getter for relation 'BpmnTask->Caller'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cc227ebc-4811-463a-8dab-68d45de7b20f")
    <T extends BpmnCallActivity> List<T> getCaller(java.lang.Class<T> filterClass);

}
