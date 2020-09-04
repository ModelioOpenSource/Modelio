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
package org.modelio.metamodel.mda;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameterType;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;

/**
 * ModuleParameter v0.0.9054
 * 
 * 
 * Definition of a module parameter.
 */
@objid ("0064e9a2-c4bf-1fd8-97fe-001ec947cd2a")
public interface ModuleParameter extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("344bac36-aa6d-46bc-bd07-a93ddf00bef2")
    public static final String MNAME = "ModuleParameter";

    /**
     * The metaclass qualified name.
     */
    @objid ("c77aa07a-bf5c-4c54-87dc-b1cf1184b509")
    public static final String MQNAME = "Infrastructure.ModuleParameter";

    /**
     * Getter for attribute 'ModuleParameter.GroupName'
     * 
     * Metamodel description:
     * <i>Name of the parameter group.
     * 
     * Allow module parameters to be sorted by groups.</i>
     */
    @objid ("2435e8e4-7c2d-4cad-a841-3483ffc62d7e")
    String getGroupName();

    /**
     * Setter for attribute 'ModuleParameter.GroupName'
     * 
     * Metamodel description:
     * <i>Name of the parameter group.
     * 
     * Allow module parameters to be sorted by groups.</i>
     */
    @objid ("19437e1d-640a-4c7c-aed9-24d30cf97cbe")
    void setGroupName(String value);

    /**
     * Getter for attribute 'ModuleParameter.Type'
     * 
     * Metamodel description:
     * <i>Type of the parameter. If the type is TYPE_PARAM_ENUM, the 'EnumType' association must refer to an Enumeration.</i>
     */
    @objid ("f94c297f-b66b-4908-a441-bfa0b2b1f070")
    ModuleParameterType getType();

    /**
     * Setter for attribute 'ModuleParameter.Type'
     * 
     * Metamodel description:
     * <i>Type of the parameter. If the type is TYPE_PARAM_ENUM, the 'EnumType' association must refer to an Enumeration.</i>
     */
    @objid ("c81a5b26-00c8-4515-8f92-af271e7ef3c9")
    void setType(ModuleParameterType value);

    /**
     * Getter for attribute 'ModuleParameter.IsUserRead'
     * 
     * Metamodel description:
     * <i>If true, the parameter is visible in the module configuration dialog.</i>
     */
    @objid ("abfddb57-05a9-4d21-b4b2-c2eba9034b12")
    boolean isIsUserRead();

    /**
     * Setter for attribute 'ModuleParameter.IsUserRead'
     * 
     * Metamodel description:
     * <i>If true, the parameter is visible in the module configuration dialog.</i>
     */
    @objid ("1fa70565-3966-427c-9d49-e214eacdd74a")
    void setIsUserRead(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.IsUserWrite'
     * 
     * Metamodel description:
     * <i>If true and IsUserRead is true, the parameter is modifiable in the module configuration dialog.</i>
     */
    @objid ("6daa7012-126f-416f-862f-65f956c28663")
    boolean isIsUserWrite();

    /**
     * Setter for attribute 'ModuleParameter.IsUserWrite'
     * 
     * Metamodel description:
     * <i>If true and IsUserRead is true, the parameter is modifiable in the module configuration dialog.</i>
     */
    @objid ("9dbbe8a8-b9fb-40b7-8df5-e4a09f90e65b")
    void setIsUserWrite(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.IsApiRead'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be read with the modules API.</i>
     */
    @objid ("220d0a30-7daf-4148-b246-a5eb5ce97027")
    boolean isIsApiRead();

    /**
     * Setter for attribute 'ModuleParameter.IsApiRead'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be read with the modules API.</i>
     */
    @objid ("1ba55eac-77ec-4730-bc70-8769cb5c6093")
    void setIsApiRead(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.IsApiWrite'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be set with the modules API.</i>
     */
    @objid ("b1e794e6-f3f6-4df4-b524-8883cc0b72a2")
    boolean isIsApiWrite();

    /**
     * Setter for attribute 'ModuleParameter.IsApiWrite'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be set with the modules API.</i>
     */
    @objid ("1a9164d8-c1f9-4025-bef5-6f209eeecc3c")
    void setIsApiWrite(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.DefaultValue'
     * 
     * Metamodel description:
     * <i><p>Default value for the module parameter, written as a string.</p>
     * </i>
     */
    @objid ("783231bf-b5e6-47ca-ad5d-90a1616fa897")
    String getDefaultValue();

    /**
     * Setter for attribute 'ModuleParameter.DefaultValue'
     * 
     * Metamodel description:
     * <i><p>Default value for the module parameter, written as a string.</p>
     * </i>
     */
    @objid ("17aea010-4c0d-45ad-898f-44ad069ed166")
    void setDefaultValue(String value);

    /**
     * Getter for relation 'ModuleParameter->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b103ee2c-c118-461c-aa19-1dde2b1b1ea4")
    ModuleComponent getOwner();

    /**
     * Setter for relation 'ModuleParameter->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d7b7675f-4ac2-4210-85d3-563fd0f4614a")
    void setOwner(ModuleComponent value);

    /**
     * Getter for relation 'ModuleParameter->EnumType'
     * 
     * Metamodel description:
     * <i>Enumeration used by the parameter. when the attribute "Type" is TYPE_PARAM_ENUM.</i>
     */
    @objid ("09ee2c71-96ff-4863-87b7-6ead44df9ae7")
    EnumeratedPropertyType getEnumType();

    /**
     * Setter for relation 'ModuleParameter->EnumType'
     * 
     * Metamodel description:
     * <i>Enumeration used by the parameter. when the attribute "Type" is TYPE_PARAM_ENUM.</i>
     */
    @objid ("12630e33-813a-4edc-86e9-86cfec69d1cb")
    void setEnumType(EnumeratedPropertyType value);

}
