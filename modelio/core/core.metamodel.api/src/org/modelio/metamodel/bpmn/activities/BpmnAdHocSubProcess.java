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
 * BpmnAdHocSubProcess v0.0.9054
 * 
 * 
 * An Ad-Hoc Sub-Process is a specialized type of Sub-Process that is a group of Activities that have no required sequence relationships. A set of Activities can be defined for the Process, but the sequence and number of performances for the Activities is determined by the performers of the Activities.
 */
@objid ("007df21c-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnAdHocSubProcess extends BpmnSubProcess {
    /**
     * The metaclass simple name.
     */
    @objid ("62530293-9308-406c-a2f4-242824b65677")
    public static final String MNAME = "BpmnAdHocSubProcess";

    /**
     * The metaclass qualified name.
     */
    @objid ("a2b9bf31-df28-4f92-98fe-5587616ccbe9")
    public static final String MQNAME = "Standard.BpmnAdHocSubProcess";

    /**
     * Getter for attribute 'BpmnAdHocSubProcess.Ordering'
     * 
     * Metamodel description:
     * <i>This attribute defines if the Activities within the Process can be performed in parallel or must be performed sequentially. The default setting is parallel and the setting of sequential is a restriction on the performance that may be required due to shared resources. When the setting is sequential, then only one Activity can be performed at a time. When the setting is parallel, then zero (0) to all the Activities of the Sub-Process can be performed in parallel.</i>
     */
    @objid ("de9db047-35b5-47c4-9039-8ce42b0f1025")
    AdHocOrdering getOrdering();

    /**
     * Setter for attribute 'BpmnAdHocSubProcess.Ordering'
     * 
     * Metamodel description:
     * <i>This attribute defines if the Activities within the Process can be performed in parallel or must be performed sequentially. The default setting is parallel and the setting of sequential is a restriction on the performance that may be required due to shared resources. When the setting is sequential, then only one Activity can be performed at a time. When the setting is parallel, then zero (0) to all the Activities of the Sub-Process can be performed in parallel.</i>
     */
    @objid ("1a3f4914-60a4-4ede-bfbc-52775de4fcec")
    void setOrdering(AdHocOrdering value);

    /**
     * Getter for attribute 'BpmnAdHocSubProcess.CancelRemainingInstances'
     * 
     * Metamodel description:
     * <i>This attribute is used only if ordering is parallel. It determines whether running instances are cancelled when the completionCondition becomes true.</i>
     */
    @objid ("b1d32dc2-302e-47ab-916d-858abab68b58")
    boolean isCancelRemainingInstances();

    /**
     * Setter for attribute 'BpmnAdHocSubProcess.CancelRemainingInstances'
     * 
     * Metamodel description:
     * <i>This attribute is used only if ordering is parallel. It determines whether running instances are cancelled when the completionCondition becomes true.</i>
     */
    @objid ("3741835b-f40a-4019-97f6-55e9eb271f77")
    void setCancelRemainingInstances(boolean value);

    /**
     * Getter for attribute 'BpmnAdHocSubProcess.CompletionCondition'
     * 
     * Metamodel description:
     * <i>This Expression defines the conditions when the Process will end. When the Expression is evaluated to true, the Process will be terminated.</i>
     */
    @objid ("ddf7325d-9bc3-4a18-b3b3-be0d1ad945b8")
    String getCompletionCondition();

    /**
     * Setter for attribute 'BpmnAdHocSubProcess.CompletionCondition'
     * 
     * Metamodel description:
     * <i>This Expression defines the conditions when the Process will end. When the Expression is evaluated to true, the Process will be terminated.</i>
     */
    @objid ("ac50b2d4-5746-404e-b769-6fa3166a2091")
    void setCompletionCondition(String value);

}
