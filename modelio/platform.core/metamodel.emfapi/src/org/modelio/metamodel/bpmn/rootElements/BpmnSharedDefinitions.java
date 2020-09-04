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
package org.modelio.metamodel.bpmn.rootElements;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;

/**
 * BpmnSharedDefinitions v2.2.0
 * 
 * 
 * Definitions BPMN metaclass.
 */
@objid ("b3eec24c-5799-4134-abbe-1afb8df80c12")
public interface BpmnSharedDefinitions extends Behavior {
    /**
     * The metaclass simple name.
     */
    @objid ("859f44fd-10a6-42a1-a330-0b52929d3efd")
    public static final String MNAME = "BpmnSharedDefinitions";

    /**
     * The metaclass qualified name.
     */
    @objid ("a4aa0807-86bd-479f-bb7b-ef2fe3be3b98")
    public static final String MQNAME = "Standard.BpmnSharedDefinitions";

    /**
     * Getter for relation 'BpmnSharedDefinitions->RootElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("dabf4df0-7906-4b63-9010-4d0e11397bf8")
    EList<BpmnSharedElement> getRootElement();

    /**
     * Filtered Getter for relation 'BpmnSharedDefinitions->RootElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5755c11a-293c-4b6c-9ac6-04ab96b887bf")
    <T extends BpmnSharedElement> List<T> getRootElement(java.lang.Class<T> filterClass);

}
