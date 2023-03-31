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

package org.modelio.metamodel.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * TemplateParameterSubstitution v0.0.9054
 * 
 * 
 * A TemplateParameterSubstitution associates one or more actual Parameters with a formal template Parameter within the context of a TemplateBinding.  
 * 
 * In Modelio, a TemplateParameterSubstitution cannot own its actual value. The actual value can be referenced by the Actual dependency or defined by the StringValue attribute.
 * 
 * 
 */
@objid ("001dff92-c4bf-1fd8-97fe-001ec947cd2a")
public interface TemplateParameterSubstitution extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("958a8add-811d-46ca-8e24-2d19946ee530")
    public static final String MNAME = "TemplateParameterSubstitution";

    /**
     * The metaclass qualified name.
     */
    @objid ("79da44d6-ad0f-4ce4-8fd9-6d5a23d3a93b")
    public static final String MQNAME = "Standard.TemplateParameterSubstitution";

    /**
     * Getter for attribute 'TemplateParameterSubstitution.Value'
     * 
     * Metamodel description:
     * <i>The value that is the actual parameter for this substitution. It is used for value template parameters.</i>
     * 
     */
    @objid ("e44a6286-80c4-4608-ac42-73c7515c04f8")
    String getValue();

    /**
     * Setter for attribute 'TemplateParameterSubstitution.Value'
     * 
     * Metamodel description:
     * <i>The value that is the actual parameter for this substitution. It is used for value template parameters.</i>
     * 
     */
    @objid ("16ec43a5-1072-4206-bed9-83cb77d6a934")
    void setValue(String value);

    /**
     * Getter for relation 'TemplateParameterSubstitution->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d5acb8a6-de1a-4741-af00-16fdbf222057")
    TemplateBinding getOwner();

    /**
     * Setter for relation 'TemplateParameterSubstitution->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("43218a7e-b513-4fdf-942c-92f4bc228e98")
    void setOwner(TemplateBinding value);

    /**
     * Getter for relation 'TemplateParameterSubstitution->Actual'
     * 
     * Metamodel description:
     * <i>The elements that are the actual parameters for this substitution.</i>
     * 
     */
    @objid ("8809dd57-f9a6-4d01-b2e0-38866a9defc5")
    UmlModelElement getActual();

    /**
     * Setter for relation 'TemplateParameterSubstitution->Actual'
     * 
     * Metamodel description:
     * <i>The elements that are the actual parameters for this substitution.</i>
     * 
     */
    @objid ("61194abf-e5f3-49cf-9757-cbc1a3de1392")
    void setActual(UmlModelElement value);

    /**
     * Getter for relation 'TemplateParameterSubstitution->FormalParameter'
     * 
     * Metamodel description:
     * <i>The formal template parameter that is associated with this substitution.</i>
     * 
     */
    @objid ("7486bd00-2dc5-46fc-bec5-93550ca5f2c4")
    TemplateParameter getFormalParameter();

    /**
     * Setter for relation 'TemplateParameterSubstitution->FormalParameter'
     * 
     * Metamodel description:
     * <i>The formal template parameter that is associated with this substitution.</i>
     * 
     */
    @objid ("0483c38b-1d4d-4920-b3fe-1fa7f57dbad0")
    void setFormalParameter(TemplateParameter value);
}

