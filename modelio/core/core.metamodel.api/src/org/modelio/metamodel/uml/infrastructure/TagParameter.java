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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * TagParameter v0.0.9054
 * 
 * 
 * TaggedValues are a powerful mechanism in Modelio. They can have Parameters that must conform to the TagType structure. 
 * 
 * TagParameters belong to their TaggedValue.
 * 
 * 
 */
@objid ("008e8d34-c4be-1fd8-97fe-001ec947cd2a")
public interface TagParameter extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("261d25c5-321b-4789-b059-2ec9b7e35337")
    public static final String MNAME = "TagParameter";

    /**
     * The metaclass qualified name.
     */
    @objid ("e6b92f73-75b0-45e4-a436-41c65f364ca6")
    public static final String MQNAME = "Infrastructure.TagParameter";

    /**
     * Getter for attribute 'TagParameter.Value'
     * 
     * Metamodel description:
     * <i>Parameter value.</i>
     * 
     */
    @objid ("0b63ac51-719e-4c79-a1b4-946cc0255dad")
    String getValue();

    /**
     * Setter for attribute 'TagParameter.Value'
     * 
     * Metamodel description:
     * <i>Parameter value.</i>
     * 
     */
    @objid ("84f6b1dd-1d12-4a9d-9416-6287f75fd8de")
    void setValue(String value);

    /**
     * Getter for relation 'TagParameter->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("9f8aca52-fc51-41a6-94cf-feb5b6c8a48d")
    TaggedValue getAnnoted();

    /**
     * Setter for relation 'TagParameter->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("91091be6-491a-45e9-bb02-62b41147acf5")
    void setAnnoted(TaggedValue value);

    /**
     * Getter for relation 'TagParameter->Qualified'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("64a850ad-cf92-4640-a376-5d29f822bd62")
    TaggedValue getQualified();

    /**
     * Setter for relation 'TagParameter->Qualified'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("427f1b56-8205-482e-83ab-cb974c7f347a")
    void setQualified(TaggedValue value);
}

