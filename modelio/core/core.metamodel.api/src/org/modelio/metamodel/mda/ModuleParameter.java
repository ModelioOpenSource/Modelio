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

package org.modelio.metamodel.mda;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;

/**
 * ModuleParameter v0.0.9054
 * 
 * 
 * Definition of a module parameter.
 * 
 * 
 */
@objid ("0064e9a2-c4bf-1fd8-97fe-001ec947cd2a")
public interface ModuleParameter extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("e3168c19-fb9a-4583-984f-19a7be65dddf")
    public static final String MNAME = "ModuleParameter";

    /**
     * The metaclass qualified name.
     */
    @objid ("974ea178-386e-4529-a2f2-2d5df35f789a")
    public static final String MQNAME = "Infrastructure.ModuleParameter";

    /**
     * Getter for attribute 'ModuleParameter.GroupName'
     * 
     * Metamodel description:
     * <i>Name of the parameter group.
     * 
     * Allow module parameters to be sorted by groups.</i>
     * 
     */
    @objid ("a7c4b7c3-c78f-4527-b724-41505681c377")
    String getGroupName();

    /**
     * Setter for attribute 'ModuleParameter.GroupName'
     * 
     * Metamodel description:
     * <i>Name of the parameter group.
     * 
     * Allow module parameters to be sorted by groups.</i>
     * 
     */
    @objid ("d010f09d-cfab-467f-bb11-4e5ef22340d1")
    void setGroupName(String value);

    /**
     * Getter for attribute 'ModuleParameter.Type'
     * 
     * Metamodel description:
     * <i>Type of the parameter. If the type is TYPE_PARAM_ENUM, the 'EnumType' association must refer to an Enumeration.</i>
     * 
     */
    @objid ("9e79ac33-9e1e-4b2f-97cb-d9e43acebe57")
    ModuleParameterType getType();

    /**
     * Setter for attribute 'ModuleParameter.Type'
     * 
     * Metamodel description:
     * <i>Type of the parameter. If the type is TYPE_PARAM_ENUM, the 'EnumType' association must refer to an Enumeration.</i>
     * 
     */
    @objid ("37cc54ab-1cab-4c48-af7b-f45fef2eca0c")
    void setType(ModuleParameterType value);

    /**
     * Getter for attribute 'ModuleParameter.IsUserRead'
     * 
     * Metamodel description:
     * <i>If true, the parameter is visible in the module configuration dialog.</i>
     * 
     */
    @objid ("335a97c4-aa00-4cea-b32c-f40a7ac020ee")
    boolean isIsUserRead();

    /**
     * Setter for attribute 'ModuleParameter.IsUserRead'
     * 
     * Metamodel description:
     * <i>If true, the parameter is visible in the module configuration dialog.</i>
     * 
     */
    @objid ("2df469d4-e397-4cb7-beee-63d3301ca05c")
    void setIsUserRead(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.IsUserWrite'
     * 
     * Metamodel description:
     * <i>If true and IsUserRead is true, the parameter is modifiable in the module configuration dialog.</i>
     * 
     */
    @objid ("c945575f-14b8-4193-8cdd-47b20f3de30f")
    boolean isIsUserWrite();

    /**
     * Setter for attribute 'ModuleParameter.IsUserWrite'
     * 
     * Metamodel description:
     * <i>If true and IsUserRead is true, the parameter is modifiable in the module configuration dialog.</i>
     * 
     */
    @objid ("af9aaf21-3013-4b6a-92c7-7711c8a49dd9")
    void setIsUserWrite(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.IsApiRead'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be read with the modules API.</i>
     * 
     */
    @objid ("d39e29c6-23ca-471f-9553-0195672ece5a")
    boolean isIsApiRead();

    /**
     * Setter for attribute 'ModuleParameter.IsApiRead'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be read with the modules API.</i>
     * 
     */
    @objid ("8cd3af48-c97b-404e-898d-a725712d0aef")
    void setIsApiRead(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.IsApiWrite'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be set with the modules API.</i>
     * 
     */
    @objid ("c51e8ec7-1748-45f6-ac70-3019ea964bb6")
    boolean isIsApiWrite();

    /**
     * Setter for attribute 'ModuleParameter.IsApiWrite'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be set with the modules API.</i>
     * 
     */
    @objid ("7eb0a32e-3f19-41b5-9216-8258bc50609f")
    void setIsApiWrite(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.DefaultValue'
     * 
     * Metamodel description:
     * <i><p>Default value for the module parameter, written as a string.</p>
     * </i>
     * 
     */
    @objid ("be93e1f8-11bf-4c9d-b539-760dc0cd5280")
    String getDefaultValue();

    /**
     * Setter for attribute 'ModuleParameter.DefaultValue'
     * 
     * Metamodel description:
     * <i><p>Default value for the module parameter, written as a string.</p>
     * </i>
     * 
     */
    @objid ("98a247a4-930b-4ef8-9a2b-27494862f078")
    void setDefaultValue(String value);

    /**
     * Getter for relation 'ModuleParameter->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ecfd28b4-854d-4016-a30c-d0ba9d7977f7")
    ModuleComponent getOwner();

    /**
     * Setter for relation 'ModuleParameter->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("51e56ed3-bfd4-4913-a837-96f89d740b3d")
    void setOwner(ModuleComponent value);

    /**
     * Getter for relation 'ModuleParameter->EnumType'
     * 
     * Metamodel description:
     * <i>Enumeration used by the parameter. when the attribute "Type" is TYPE_PARAM_ENUM.</i>
     * 
     */
    @objid ("7d22b3e6-1992-4a1c-a410-c37937440439")
    EnumeratedPropertyType getEnumType();

    /**
     * Setter for relation 'ModuleParameter->EnumType'
     * 
     * Metamodel description:
     * <i>Enumeration used by the parameter. when the attribute "Type" is TYPE_PARAM_ENUM.</i>
     * 
     */
    @objid ("3674dc06-1cc1-4dcb-9d88-3df95cfb72e4")
    void setEnumType(EnumeratedPropertyType value);
}

