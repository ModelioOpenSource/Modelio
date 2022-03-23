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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * TemplateBinding v0.0.9054
 * 
 * 
 * A TemplateBinding specifies the Substitution of actual Parameters by the formal Parameters of the template. 
 * 
 * A TemplateBinding is a directed relationship from a bound templateable element to the template signature of the target template. 
 * 
 * A TemplateBinding owns a set of TemplateParameterSubstitutions.
 */
@objid ("001c3784-c4bf-1fd8-97fe-001ec947cd2a")
public interface TemplateBinding extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("1b6575f0-4385-4c63-8021-a937a010378e")
    public static final String MNAME = "TemplateBinding";

    /**
     * The metaclass qualified name.
     */
    @objid ("c7dd0812-d94b-4409-8927-d88fb714ceb2")
    public static final String MQNAME = "Standard.TemplateBinding";

    /**
     * Getter for relation 'TemplateBinding->ParameterSubstitution'
     * 
     * Metamodel description:
     * <i>The ParameterSubstitutions owned by this TemplateBinding.</i>
     */
    @objid ("3700592c-0e6a-44e6-a711-13f4470fc630")
    EList<TemplateParameterSubstitution> getParameterSubstitution();

    /**
     * Filtered Getter for relation 'TemplateBinding->ParameterSubstitution'
     * 
     * Metamodel description:
     * <i>The ParameterSubstitutions owned by this TemplateBinding.</i>
     */
    @objid ("c8b3f99c-7414-45cb-bf0d-8605de0ae5fd")
    <T extends TemplateParameterSubstitution> List<T> getParameterSubstitution(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'TemplateBinding->BoundOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("95fe74a6-beaa-4d7e-9272-502f86bd9548")
    Operation getBoundOperation();

    /**
     * Setter for relation 'TemplateBinding->BoundOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e4c8213c-75d6-43f8-947f-d6df89d6614f")
    void setBoundOperation(Operation value);

    /**
     * Getter for relation 'TemplateBinding->InstanciatedTemplateOperation'
     * 
     * Metamodel description:
     * <i>The element that is bound by this Binding.</i>
     */
    @objid ("609fb3fd-9499-4578-804b-b1fe68449daf")
    Operation getInstanciatedTemplateOperation();

    /**
     * Setter for relation 'TemplateBinding->InstanciatedTemplateOperation'
     * 
     * Metamodel description:
     * <i>The element that is bound by this Binding.</i>
     */
    @objid ("ea58ef31-e063-4901-be8f-52763144b785")
    void setInstanciatedTemplateOperation(Operation value);

    /**
     * Getter for relation 'TemplateBinding->InstanciatedTemplate'
     * 
     * Metamodel description:
     * <i>The template element that is the target of the Binding.</i>
     */
    @objid ("6ab20e06-9507-434d-b63c-fbf510de2559")
    NameSpace getInstanciatedTemplate();

    /**
     * Setter for relation 'TemplateBinding->InstanciatedTemplate'
     * 
     * Metamodel description:
     * <i>The template element that is the target of the Binding.</i>
     */
    @objid ("87add2fb-af99-4ec0-bbd4-97d0c4f6a639")
    void setInstanciatedTemplate(NameSpace value);

    /**
     * Getter for relation 'TemplateBinding->BoundElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5cc3abd2-87b4-4f94-b51a-7c059f0d34d6")
    NameSpace getBoundElement();

    /**
     * Setter for relation 'TemplateBinding->BoundElement'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("955d57ed-fc57-4b12-adcc-82a515cfa61b")
    void setBoundElement(NameSpace value);

}
