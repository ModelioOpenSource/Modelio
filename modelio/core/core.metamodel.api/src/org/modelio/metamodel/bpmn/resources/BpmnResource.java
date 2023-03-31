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

package org.modelio.metamodel.bpmn.resources;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;

/**
 * BpmnResource v0.0.9054
 * 
 * 
 * <p>The Resource class is used to specify resources that can be referenced by Activities or processes or other BPMN elements such as datastores. These Resources can be Human Resources as well as any other resource assigned to Activities during Process execution time.</p><p>The definition of a Resource is &quot;abstract&quot;,&nbsp;because it only defines the Resource, without detailing how e.g., actual user IDs are associated at runtime. Multiple Activities can utilize the same Resource.</p><p>Every Resource can define a set of ResourceParameters. These parameters can be used at runtime to define query e.g., into an Organizational Directory. Every Activity referencing a parameterized Resource can bind values available in the scope of the Activity to these parameters.</p><p>&nbsp;</p>
 * 
 * 
 * 
 */
@objid ("00093d64-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnResource extends BpmnSharedElement {
    /**
     * The metaclass simple name.
     */
    @objid ("477e999e-9cf7-471b-9026-80709bd104bf")
    public static final String MNAME = "BpmnResource";

    /**
     * The metaclass qualified name.
     */
    @objid ("a7989f0d-7de9-48d2-a191-a2568813e397")
    public static final String MQNAME = "Standard.BpmnResource";

    /**
     * Getter for relation 'BpmnResource->ResourceroleRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f9cab9fb-2901-4948-b775-fa88de1db184")
    EList<BpmnResourceRole> getResourceroleRefs();

    /**
     * Filtered Getter for relation 'BpmnResource->ResourceroleRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d093cc32-18f9-42b5-ba90-d6ade665da19")
    <T extends BpmnResourceRole> List<T> getResourceroleRefs(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnResource->Parameter'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("7ce7b95e-806f-4f38-a78e-2ffe3ebe9d7e")
    EList<BpmnResourceParameter> getParameter();

    /**
     * Filtered Getter for relation 'BpmnResource->Parameter'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c8465658-e7c3-4c6e-9242-2937a691a30d")
    <T extends BpmnResourceParameter> List<T> getParameter(java.lang.Class<T> filterClass);
}

