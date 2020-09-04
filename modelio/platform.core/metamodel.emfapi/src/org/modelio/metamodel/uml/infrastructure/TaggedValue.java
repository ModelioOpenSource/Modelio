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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
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
    @objid ("98d548a9-be9d-44b5-bde4-3bab08dc7e08")
    public static final String MNAME = "TaggedValue";

    /**
     * The metaclass qualified name.
     */
    @objid ("0083f444-8291-44cc-8546-8fa7f80968ce")
    public static final String MQNAME = "Infrastructure.TaggedValue";

    /**
     * Getter for relation 'TaggedValue->Actual'
     * 
     * Metamodel description:
     * <i>Parameters of the TaggedValue.</i>
     */
    @objid ("48528f8d-c2cb-48fd-b500-8701689cc8bb")
    EList<TagParameter> getActual();

    /**
     * Filtered Getter for relation 'TaggedValue->Actual'
     * 
     * Metamodel description:
     * <i>Parameters of the TaggedValue.</i>
     */
    @objid ("909c01e3-ddc6-4e9e-995f-367648428c5b")
    <T extends TagParameter> List<T> getActual(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'TaggedValue->Qualifier'
     * 
     * Metamodel description:
     * <i>Current Qualifier of the TaggedValue. A qualifier is a parameter that is placed in first place Tag:Qualifier (Parameters).</i>
     */
    @objid ("3e41fc94-60bf-4fd2-8f21-9a12fda43d8a")
    TagParameter getQualifier();

    /**
     * Setter for relation 'TaggedValue->Qualifier'
     * 
     * Metamodel description:
     * <i>Current Qualifier of the TaggedValue. A qualifier is a parameter that is placed in first place Tag:Qualifier (Parameters).</i>
     */
    @objid ("49b31314-bffd-476d-ac5b-c5bf01c210a8")
    void setQualifier(TagParameter value);

    /**
     * Getter for relation 'TaggedValue->Definition'
     * 
     * Metamodel description:
     * <i>Determines the TagType that is the model of the current TaggedValue.</i>
     */
    @objid ("ecf8b27f-cd24-4313-8b5e-dad7c93f52b6")
    TagType getDefinition();

    /**
     * Setter for relation 'TaggedValue->Definition'
     * 
     * Metamodel description:
     * <i>Determines the TagType that is the model of the current TaggedValue.</i>
     */
    @objid ("0a20285d-819c-4dc4-88e3-644943fcbfa7")
    void setDefinition(TagType value);

    /**
     * Getter for relation 'TaggedValue->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d67fe76d-d1d6-474e-aba9-d0dc0cd4ffd9")
    ModelElement getAnnoted();

    /**
     * Setter for relation 'TaggedValue->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("54f989ef-c77b-49de-94ad-d520a05c0c29")
    void setAnnoted(ModelElement value);

}
