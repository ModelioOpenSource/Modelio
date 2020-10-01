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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("457e11b9-2856-4155-a811-15448dd394f1")
    public static final String MNAME = "TagParameter";

    /**
     * The metaclass qualified name.
     */
    @objid ("9b278438-3270-4a54-8be1-f14b8835cc20")
    public static final String MQNAME = "Infrastructure.TagParameter";

    /**
     * Getter for attribute 'TagParameter.Value'
     * 
     * Metamodel description:
     * <i>Parameter value.</i>
     */
    @objid ("70e774e4-492a-4775-b94b-ddb2b0df05e0")
    String getValue();

    /**
     * Setter for attribute 'TagParameter.Value'
     * 
     * Metamodel description:
     * <i>Parameter value.</i>
     */
    @objid ("88eae0bc-e11f-4eee-8de8-f6c3d97020cc")
    void setValue(String value);

    /**
     * Getter for relation 'TagParameter->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("39b31746-59ef-4716-bbb8-0581999842ee")
    TaggedValue getAnnoted();

    /**
     * Setter for relation 'TagParameter->Annoted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e91753f3-5b81-4f89-9b0e-33ec46ed3840")
    void setAnnoted(TaggedValue value);

    /**
     * Getter for relation 'TagParameter->Qualified'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("85f056c8-135f-4568-a5c8-e579d752ae51")
    TaggedValue getQualified();

    /**
     * Setter for relation 'TagParameter->Qualified'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("805d490f-1297-4269-a330-684e1c431315")
    void setQualified(TaggedValue value);

}
