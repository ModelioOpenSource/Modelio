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
package org.modelio.metamodel.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;

/**
 * ActivityAction v0.0.9054
 * 
 * 
 * The execution of an action represents some transformation or processing in the modeled system, be it a computer system or otherwise.
 * 
 * An action may have sets of incoming and outgoing activity edges that specify control flow and data flow to and from other nodes. An action will not begin execution until all of its input conditions are satisfied. The completion of the execution of an action may enable the execution of a set of successor nodes and actions that take their inputs from the outputs of the action.
 * 
 * Action can have pre- and post-conditions (using constraints).
 */
@objid ("002688d8-c4bf-1fd8-97fe-001ec947cd2a")
public interface ActivityAction extends ActivityNode {
    /**
     * The metaclass simple name.
     */
    @objid ("3015ae7e-e06d-4733-ac81-f80b0f43d661")
    public static final String MNAME = "ActivityAction";

    /**
     * The metaclass qualified name.
     */
    @objid ("34f5dfe2-50d1-42f6-b75c-723a3a825ac3")
    public static final String MQNAME = "Standard.ActivityAction";

    /**
     * Getter for attribute 'ActivityAction.IsMultipleInstance'
     * 
     * Metamodel description:
     * <i>BPMN: Expresses if several action instances can be run in parallel.</i>
     */
    @objid ("904572c3-0f61-41a4-8dbc-e3e6278323b3")
    boolean isIsMultipleInstance();

    /**
     * Setter for attribute 'ActivityAction.IsMultipleInstance'
     * 
     * Metamodel description:
     * <i>BPMN: Expresses if several action instances can be run in parallel.</i>
     */
    @objid ("5774d5a3-aadf-4cab-ae70-0498929916b6")
    void setIsMultipleInstance(boolean value);

    /**
     * Getter for attribute 'ActivityAction.IsCompensation'
     * 
     * Metamodel description:
     * <i>BPMN : Some activities produce complex effects or specific outputs. If the outcome is determined to be undesirable by some specified criteria (such as an order being cancelled), then it will be necessary to "undo" the activities. Compensation activities are activities that "undo" the effect of other activities, in reaction to a compensation event.
     * The Compensation Activity is special in that it does not follow the normal Sequence Flow rules--as mentioned, it is outside the Normal Flow of the Process. This activity cannot have any incoming or outgoing Sequence Flows. The Compensation marker (as is in the Compensation Intermediate Event) will be displayed in the bottom center of the Activity to show this status of the activity.</i>
     */
    @objid ("c420fd97-f6f1-451b-b366-37c49a649091")
    boolean isIsCompensation();

    /**
     * Setter for attribute 'ActivityAction.IsCompensation'
     * 
     * Metamodel description:
     * <i>BPMN : Some activities produce complex effects or specific outputs. If the outcome is determined to be undesirable by some specified criteria (such as an order being cancelled), then it will be necessary to "undo" the activities. Compensation activities are activities that "undo" the effect of other activities, in reaction to a compensation event.
     * The Compensation Activity is special in that it does not follow the normal Sequence Flow rules--as mentioned, it is outside the Normal Flow of the Process. This activity cannot have any incoming or outgoing Sequence Flows. The Compensation marker (as is in the Compensation Intermediate Event) will be displayed in the bottom center of the Activity to show this status of the activity.</i>
     */
    @objid ("4f4ea145-5af7-442d-baa7-cfb2e2fe3b77")
    void setIsCompensation(boolean value);

    /**
     * Getter for relation 'ActivityAction->Output'
     * 
     * Metamodel description:
     * <i>Output pins connected to the Action. The action places its results onto pins in this set.</i>
     */
    @objid ("ebfa8633-b257-4839-9f31-a9d885cd2e49")
    EList<OutputPin> getOutput();

    /**
     * Filtered Getter for relation 'ActivityAction->Output'
     * 
     * Metamodel description:
     * <i>Output pins connected to the Action. The action places its results onto pins in this set.</i>
     */
    @objid ("27b09494-f0bd-4e21-9b64-dc7dfae6297b")
    <T extends OutputPin> List<T> getOutput(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ActivityAction->Input'
     * 
     * Metamodel description:
     * <i>Input pins connected to the Action. These are among the total set of inputs. </i>
     */
    @objid ("5c4a7dd9-d8d5-4c53-a7bd-f9bb026078cf")
    EList<InputPin> getInput();

    /**
     * Filtered Getter for relation 'ActivityAction->Input'
     * 
     * Metamodel description:
     * <i>Input pins connected to the Action. These are among the total set of inputs. </i>
     */
    @objid ("d1fb1dbf-7c61-47a6-842c-9c508d2f7a81")
    <T extends InputPin> List<T> getInput(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ActivityAction->Handler'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("466ae311-7dda-4cff-bf2c-901516b00f5a")
    EList<ExceptionHandler> getHandler();

    /**
     * Filtered Getter for relation 'ActivityAction->Handler'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("db6f5bd2-7fd8-46fb-ae93-d173dd31fe3b")
    <T extends ExceptionHandler> List<T> getHandler(java.lang.Class<T> filterClass);

}
