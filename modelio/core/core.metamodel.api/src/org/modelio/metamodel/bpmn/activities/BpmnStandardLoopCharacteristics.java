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
 * BpmnStandardLoopCharacteristics v0.0.9054
 * 
 * 
 * null
 * 
 * 
 */
@objid ("00839f78-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnStandardLoopCharacteristics extends BpmnLoopCharacteristics {
    /**
     * The metaclass simple name.
     */
    @objid ("3a55df78-71b5-4371-a012-869919f2d600")
    public static final String MNAME = "BpmnStandardLoopCharacteristics";

    /**
     * The metaclass qualified name.
     */
    @objid ("45107bed-9acc-42f8-a30e-2647c59b6841")
    public static final String MQNAME = "Standard.BpmnStandardLoopCharacteristics";

    /**
     * Getter for attribute 'BpmnStandardLoopCharacteristics.TestBefore'
     * 
     * Metamodel description:
     * <i>Flag that controls whether the loop condition is evaluated at the beginning (testBefore = true) or at the end (testBefore = false) of the
     * loop iteration.</i>
     * 
     */
    @objid ("b7760519-b3e5-48b3-b44e-022cd353760b")
    boolean isTestBefore();

    /**
     * Setter for attribute 'BpmnStandardLoopCharacteristics.TestBefore'
     * 
     * Metamodel description:
     * <i>Flag that controls whether the loop condition is evaluated at the beginning (testBefore = true) or at the end (testBefore = false) of the
     * loop iteration.</i>
     * 
     */
    @objid ("3042aa90-232d-4ec9-9ea8-00b3b1f73326")
    void setTestBefore(boolean value);

    /**
     * Getter for attribute 'BpmnStandardLoopCharacteristics.LoopCondition'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("aac3d07d-1cbf-4cbf-ac36-10725dfd01bb")
    String getLoopCondition();

    /**
     * Setter for attribute 'BpmnStandardLoopCharacteristics.LoopCondition'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("74d6b1e4-4645-4a4b-9e90-fe336cbb1ab5")
    void setLoopCondition(String value);

    /**
     * Getter for attribute 'BpmnStandardLoopCharacteristics.LoopMaximum'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("924fd35c-397a-4f6f-bba7-928d5af72cc9")
    String getLoopMaximum();

    /**
     * Setter for attribute 'BpmnStandardLoopCharacteristics.LoopMaximum'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1c3377e3-ccc9-4da5-a978-41ff468c81ef")
    void setLoopMaximum(String value);
}

