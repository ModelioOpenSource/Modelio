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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;

/**
 * TaggedValue v0.0.9054
 * 
 * 
 * In Modelio, TaggedValues can have parameters, and must comply with TagTypes that define which kind of TaggedValues may exist. 
 * 
 * TaggedValues belong to their annotated ModelElement, or to their annotated Note.
 */
@objid ("0090006a-c4be-1fd8-97fe-001ec947cd2a")
public interface TaggedValue extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("96bea112-41f4-4579-8e28-ccd2d32e105f")
    public static final String MNAME = "TaggedValue";

    /**
     * The metaclass qualified name.
     */
    @objid ("7223f505-de8d-41ea-bf1b-89326e70d5b0")
    public static final String MQNAME = "Infrastructure.TaggedValue";

    /**
     * Getter for relation 'TaggedValue->Actual'
     * 
     * Metamodel description:
     * <i>Parameters of the TaggedValue.</i>
     */
    @objid ("7e43f945-d3f3-4af7-81a1-70a6e1b84642")
    EList<TagParameter> getActual();

    /**
     * Filtered Getter for relation 'TaggedValue->Actual'
     * 
     * Metamodel description:
     * <i>Parameters of the TaggedValue.</i>
     */
    @objid ("93bebbeb-973d-4ad5-8755-d3edc4b70236")
    <T extends TagParameter> List<T> getActual(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'TaggedValue->Qualifier'
     * 
     * Metamodel description:
     * <i>Current Qualifier of the TaggedValue. A qualifier is a parameter that is placed in first place Tag:Qualifier (Parameters).</i>
     */
    @objid ("b4bfa642-b1d6-49c4-b00f-dc656fbb8e36")
    TagParameter getQualifier();

    /**
     * Setter for relation 'TaggedValue->Qualifier'
     * 
     * Metamodel description:
     * <i>Current Qualifier of the TaggedValue. A qualifier is a parameter that is placed in first place Tag:Qualifier (Parameters).</i>
     */
    @objid ("dd41680c-59bb-459c-badc-49239d8758d5")
    void setQualifier(TagParameter value);

    /**
     * Getter for relation 'TaggedValue->Definition'
     * 
     * Metamodel description:
     * <i>Determines the TagType that is the model of the current TaggedValue.</i>
     */
    @objid ("3c732532-7e95-48cf-995e-2fd0204f5798")
    TagType getDefinition();

    /**
     * Setter for relation 'TaggedValue->Definition'
     * 
     * Metamodel description:
     * <i>Determines the TagType that is the model of the current TaggedValue.</i>
     */
    @objid ("08bc124d-338f-47a7-a5fb-dacca29a9683")
    void setDefinition(TagType value);

    /**
     * Getter for relation 'TaggedValue->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("60890134-3506-40b9-96b0-bf696675eb11")
    ModelElement getAnnoted();

    /**
     * Setter for relation 'TaggedValue->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f8620d67-19ad-4e50-b148-3b9c86a5c0da")
    void setAnnoted(ModelElement value);

}
