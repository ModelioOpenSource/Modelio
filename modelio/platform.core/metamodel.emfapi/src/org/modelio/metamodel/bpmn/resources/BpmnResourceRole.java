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
package org.modelio.metamodel.bpmn.resources;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;

/**
 * BpmnResourceRole v0.0.9054
 * 
 * 
 * Resource allocated to a model element. The occurence provides values to resource parameters through resources binding
 */
@objid ("000a8606-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnResourceRole extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("558b8c33-5c2b-4103-8b34-7f7772c9d5b3")
    public static final String MNAME = "BpmnResourceRole";

    /**
     * The metaclass qualified name.
     */
    @objid ("18dafda6-51a5-4378-baca-91fdf9865d2d")
    public static final String MQNAME = "Standard.BpmnResourceRole";

    /**
     * Getter for relation 'BpmnResourceRole->ResourceRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e2492d1d-2ef5-40d1-bd7c-68712bba66ba")
    BpmnResource getResourceRef();

    /**
     * Setter for relation 'BpmnResourceRole->ResourceRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8f0eaf96-1777-434c-aeab-96f5685fd3be")
    void setResourceRef(BpmnResource value);

    /**
     * Getter for relation 'BpmnResourceRole->Annotated'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5fd2a34e-6ac1-4808-9506-e39760a33214")
    BpmnFlowNode getAnnotated();

    /**
     * Setter for relation 'BpmnResourceRole->Annotated'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1c80b424-c6f7-4678-a89e-2aa415e6eada")
    void setAnnotated(BpmnFlowNode value);

    /**
     * Getter for relation 'BpmnResourceRole->ResourceParameterBinding'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("86339485-7d93-42bd-b658-e5cdcb121726")
    EList<BpmnResourceParameterBinding> getResourceParameterBinding();

    /**
     * Filtered Getter for relation 'BpmnResourceRole->ResourceParameterBinding'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("93ebebf9-a861-40a8-8321-2a09fc7a90ac")
    <T extends BpmnResourceParameterBinding> List<T> getResourceParameterBinding(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnResourceRole->Process'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8e639dcb-3e9b-4fdd-87d0-e18c31998a6b")
    BpmnProcess getProcess();

    /**
     * Setter for relation 'BpmnResourceRole->Process'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("10a372bf-3409-4eae-83cc-e2870408d5ce")
    void setProcess(BpmnProcess value);

}
