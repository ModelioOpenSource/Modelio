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
 * BpmnScriptTask v0.0.9054
 * 
 * 
 * A Script Task is executed by a business process engine. The modeler or implementer defines a script in a language that the engine can interpret. When the Task is ready to start, the engine will execute the script. When the script is completed, the Task will also be completed.
 */
@objid ("0082028a-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnScriptTask extends BpmnTask {
    /**
     * The metaclass simple name.
     */
    @objid ("32f775ab-a78e-41a7-a070-4561bd4dd586")
    public static final String MNAME = "BpmnScriptTask";

    /**
     * The metaclass qualified name.
     */
    @objid ("4a9f3707-e74b-45d9-b5e8-cb60ed37659a")
    public static final String MQNAME = "Standard.BpmnScriptTask";

    /**
     * Getter for attribute 'BpmnScriptTask.ScriptLanguage'
     * 
     * Metamodel description:
     * <i>Defines the script language. The script language MUST be provided if a
     * script is provided.</i>
     */
    @objid ("390d420a-8989-4eea-9ca4-6baf8588114d")
    String getScriptLanguage();

    /**
     * Setter for attribute 'BpmnScriptTask.ScriptLanguage'
     * 
     * Metamodel description:
     * <i>Defines the script language. The script language MUST be provided if a
     * script is provided.</i>
     */
    @objid ("25a46978-9618-4430-a73d-bc26cec744c9")
    void setScriptLanguage(String value);

    /**
     * Getter for attribute 'BpmnScriptTask.Script'
     * 
     * Metamodel description:
     * <i>The modeler MAY include a script that can be run when the Task is performed.
     * If a script is not included, then the Task will act as the equivalent of an Abstract Task</i>
     */
    @objid ("f8b9a678-e65e-4758-8333-67356be8e0b7")
    String getScript();

    /**
     * Setter for attribute 'BpmnScriptTask.Script'
     * 
     * Metamodel description:
     * <i>The modeler MAY include a script that can be run when the Task is performed.
     * If a script is not included, then the Task will act as the equivalent of an Abstract Task</i>
     */
    @objid ("2078b130-132e-4450-a61c-09a6078f061b")
    void setScript(String value);

}
