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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

/**
 * BpmnResourceParameterBinding v0.0.9054
 * 
 * 
 * Valuates the value of a resource parameter for a value occurence.
 */
@objid ("000b28b8-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnResourceParameterBinding extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("3be87cf8-b08d-43fe-b650-29396dcb088c")
    public static final String MNAME = "BpmnResourceParameterBinding";

    /**
     * The metaclass qualified name.
     */
    @objid ("e47f3455-8af6-4f55-ae94-4c2588d37190")
    public static final String MQNAME = "Standard.BpmnResourceParameterBinding";

    /**
     * Getter for attribute 'BpmnResourceParameterBinding.Expression'
     * 
     * Metamodel description:
     * <i>expression of the value of the parameter</i>
     */
    @objid ("37eefc51-92c2-4067-830c-2612c0c534e1")
    String getExpression();

    /**
     * Setter for attribute 'BpmnResourceParameterBinding.Expression'
     * 
     * Metamodel description:
     * <i>expression of the value of the parameter</i>
     */
    @objid ("769dfbb6-1000-45be-b077-943d099710a8")
    void setExpression(String value);

    /**
     * Getter for relation 'BpmnResourceParameterBinding->ResourceRole'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4eccd614-128d-4793-8297-78078285e317")
    BpmnResourceRole getResourceRole();

    /**
     * Setter for relation 'BpmnResourceParameterBinding->ResourceRole'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d431c78c-7d9c-4f40-8bf0-6704fa3ca2e3")
    void setResourceRole(BpmnResourceRole value);

    /**
     * Getter for relation 'BpmnResourceParameterBinding->ParameterRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3be02b5c-d363-48cc-bbd7-3376e070af04")
    BpmnResourceParameter getParameterRef();

    /**
     * Setter for relation 'BpmnResourceParameterBinding->ParameterRef'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("17478665-8ed7-4d3e-83eb-f96a3a3381e8")
    void setParameterRef(BpmnResourceParameter value);

}
