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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;

/**
 * TagParameter v0.0.9054
 * 
 * 
 * TaggedValues are a powerful mechanism in Modelio. They can have Parameters that must conform to the TagType structure. 
 * 
 * TagParameters belong to their TaggedValue.
 */
@objid ("008e8d34-c4be-1fd8-97fe-001ec947cd2a")
public interface TagParameter extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("c50a802a-4840-4d90-a7b7-a9e1b2929384")
    public static final String MNAME = "TagParameter";

    /**
     * The metaclass qualified name.
     */
    @objid ("9968c81f-107d-485a-a94f-fafba36e73b6")
    public static final String MQNAME = "Infrastructure.TagParameter";

    /**
     * Getter for attribute 'TagParameter.Value'
     * 
     * Metamodel description:
     * <i>Parameter value.</i>
     */
    @objid ("9a1574a7-bcc2-4119-af28-8dbcb4d17795")
    String getValue();

    /**
     * Setter for attribute 'TagParameter.Value'
     * 
     * Metamodel description:
     * <i>Parameter value.</i>
     */
    @objid ("b4f520a8-bfd6-4b61-b20c-23ea55b4c182")
    void setValue(String value);

    /**
     * Getter for relation 'TagParameter->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3951d673-fb6f-49b6-96c7-313baa6278b6")
    TaggedValue getAnnoted();

    /**
     * Setter for relation 'TagParameter->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bdc40b9b-3f7a-4ec1-aa5d-0b3d419e6e9b")
    void setAnnoted(TaggedValue value);

    /**
     * Getter for relation 'TagParameter->Qualified'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a73fdfff-68b5-40ba-b97d-110437ba9938")
    TaggedValue getQualified();

    /**
     * Setter for relation 'TagParameter->Qualified'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("814d3a04-4b1a-4040-a1ae-f4f8ecade259")
    void setQualified(TaggedValue value);

}
