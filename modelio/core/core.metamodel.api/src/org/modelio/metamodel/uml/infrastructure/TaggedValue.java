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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;

/**
 * TaggedValue v0.0.9054
 * 
 * 
 * In Modelio, TaggedValues can have parameters, and must comply with TagTypes that define which kind of TaggedValues may exist. 
 * 
 * TaggedValues belong to their annotated ModelElement, or to their annotated Note.
 * 
 * 
 * 
 */
@objid ("0090006a-c4be-1fd8-97fe-001ec947cd2a")
public interface TaggedValue extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("c3c49ce5-be7d-4602-b99c-44e56f61c738")
    public static final String MNAME = "TaggedValue";

    /**
     * The metaclass qualified name.
     */
    @objid ("87aff37d-2717-4dbd-aa56-1a21e9714289")
    public static final String MQNAME = "Infrastructure.TaggedValue";

    /**
     * Getter for relation 'TaggedValue->Actual'
     * 
     * Metamodel description:
     * <i>Parameters of the TaggedValue.</i>
     * 
     */
    @objid ("d2a3485a-ceed-4434-aef9-7971665630ac")
    EList<TagParameter> getActual();

    /**
     * Filtered Getter for relation 'TaggedValue->Actual'
     * 
     * Metamodel description:
     * <i>Parameters of the TaggedValue.</i>
     * 
     */
    @objid ("12f9452a-ab52-4b02-88b9-5ad5d53d313d")
    <T extends TagParameter> List<T> getActual(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'TaggedValue->Qualifier'
     * 
     * Metamodel description:
     * <i>Current Qualifier of the TaggedValue. A qualifier is a parameter that is placed in first place Tag:Qualifier (Parameters).</i>
     * 
     */
    @objid ("dfc6a4f3-32d6-42ad-9130-89c7b603ed37")
    TagParameter getQualifier();

    /**
     * Setter for relation 'TaggedValue->Qualifier'
     * 
     * Metamodel description:
     * <i>Current Qualifier of the TaggedValue. A qualifier is a parameter that is placed in first place Tag:Qualifier (Parameters).</i>
     * 
     */
    @objid ("99bed391-d6dc-4807-b477-bfa438b85187")
    void setQualifier(TagParameter value);

    /**
     * Getter for relation 'TaggedValue->Definition'
     * 
     * Metamodel description:
     * <i>Determines the TagType that is the model of the current TaggedValue.</i>
     * 
     */
    @objid ("23aa03bb-b350-4448-aab6-75b91cabda30")
    TagType getDefinition();

    /**
     * Setter for relation 'TaggedValue->Definition'
     * 
     * Metamodel description:
     * <i>Determines the TagType that is the model of the current TaggedValue.</i>
     * 
     */
    @objid ("a33c5695-1905-4d3a-9081-83138e1d9501")
    void setDefinition(TagType value);

    /**
     * Getter for relation 'TaggedValue->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f114b4e7-061c-4e93-9969-bb29f6e5cac5")
    ModelElement getAnnoted();

    /**
     * Setter for relation 'TaggedValue->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("b215155d-56b7-4026-b97b-8ed3b40dcb3a")
    void setAnnoted(ModelElement value);
}

