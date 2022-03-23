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

package org.modelio.metamodel.bpmn.activities;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnCallActivity v0.0.9054
 * 
 * 
 * <p>A Call Activity identifies a point in the Process where a global Process or a Global Task is used. The Call Activity acts as a &quot;wrapper&quot;&nbsp;for the invocation of a global Process or Global Task within the execution. The activation of a call Activity results in the transfer of control to the called global Process or Global Task.</p>
 */
@objid ("007f01c0-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnCallActivity extends BpmnActivity {
    /**
     * The metaclass simple name.
     */
    @objid ("4927b9a8-c736-4b70-ad5a-0ecad459473c")
    public static final String MNAME = "BpmnCallActivity";

    /**
     * The metaclass qualified name.
     */
    @objid ("0f2d86ef-33cd-411b-ba85-69d336098566")
    public static final String MQNAME = "Standard.BpmnCallActivity";

    /**
     * Getter for relation 'BpmnCallActivity->CalledGlobalTask'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ca351e75-0198-4d4f-8d23-a9b172121edc")
    BpmnTask getCalledGlobalTask();

    /**
     * Setter for relation 'BpmnCallActivity->CalledGlobalTask'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ac294e14-1aa3-4064-929f-fd37039d8c6a")
    void setCalledGlobalTask(BpmnTask value);

}
