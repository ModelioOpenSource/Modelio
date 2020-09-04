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
    @objid ("95f67209-1900-47d3-81dc-c60b0c1647e2")
    public static final String MNAME = "ModuleParameter";

    /**
     * The metaclass qualified name.
     */
    @objid ("99291a25-4e1b-490d-b76c-c519396173ad")
    public static final String MQNAME = "Infrastructure.ModuleParameter";

    /**
     * Getter for attribute 'ModuleParameter.GroupName'
     * 
     * Metamodel description:
     * <i>Name of the parameter group.
     * 
     * Allow module parameters to be sorted by groups.</i>
     */
    @objid ("e2817930-cd58-45d3-aace-f3c5970ee2cd")
    String getGroupName();

    /**
     * Setter for attribute 'ModuleParameter.GroupName'
     * 
     * Metamodel description:
     * <i>Name of the parameter group.
     * 
     * Allow module parameters to be sorted by groups.</i>
     */
    @objid ("86570587-f9f5-4e09-9f2c-4cc6684bcc96")
    void setGroupName(String value);

    /**
     * Getter for attribute 'ModuleParameter.Type'
     * 
     * Metamodel description:
     * <i>Type of the parameter. If the type is TYPE_PARAM_ENUM, the 'EnumType' association must refer to an Enumeration.</i>
     */
    @objid ("0727707a-f1dc-4abe-b6c1-44a0539ba666")
    ModuleParameterType getType();

    /**
     * Setter for attribute 'ModuleParameter.Type'
     * 
     * Metamodel description:
     * <i>Type of the parameter. If the type is TYPE_PARAM_ENUM, the 'EnumType' association must refer to an Enumeration.</i>
     */
    @objid ("dc02ad8d-8379-49d0-a756-4c36ef6fb30d")
    void setType(ModuleParameterType value);

    /**
     * Getter for attribute 'ModuleParameter.IsUserRead'
     * 
     * Metamodel description:
     * <i>If true, the parameter is visible in the module configuration dialog.</i>
     */
    @objid ("69633e7a-25ca-42b5-9acd-6298468452e0")
    boolean isIsUserRead();

    /**
     * Setter for attribute 'ModuleParameter.IsUserRead'
     * 
     * Metamodel description:
     * <i>If true, the parameter is visible in the module configuration dialog.</i>
     */
    @objid ("8b376a21-7ff2-4547-a571-57b3f5c87c10")
    void setIsUserRead(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.IsUserWrite'
     * 
     * Metamodel description:
     * <i>If true and IsUserRead is true, the parameter is modifiable in the module configuration dialog.</i>
     */
    @objid ("8b5794d9-2791-4451-b273-d0a66c37e9a4")
    boolean isIsUserWrite();

    /**
     * Setter for attribute 'ModuleParameter.IsUserWrite'
     * 
     * Metamodel description:
     * <i>If true and IsUserRead is true, the parameter is modifiable in the module configuration dialog.</i>
     */
    @objid ("b12bd379-8be3-40da-89f6-f174ba393955")
    void setIsUserWrite(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.IsApiRead'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be read with the modules API.</i>
     */
    @objid ("c6a6c974-aa46-4153-98f7-3f73782895fd")
    boolean isIsApiRead();

    /**
     * Setter for attribute 'ModuleParameter.IsApiRead'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be read with the modules API.</i>
     */
    @objid ("984d05ec-4bea-410f-a02d-053306c1a73a")
    void setIsApiRead(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.IsApiWrite'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be set with the modules API.</i>
     */
    @objid ("2a8d3c5c-159e-4358-8e97-62631b408c4c")
    boolean isIsApiWrite();

    /**
     * Setter for attribute 'ModuleParameter.IsApiWrite'
     * 
     * Metamodel description:
     * <i>if true, the parameter can be set with the modules API.</i>
     */
    @objid ("805674bd-74d0-411c-ab91-3db4cb646626")
    void setIsApiWrite(boolean value);

    /**
     * Getter for attribute 'ModuleParameter.DefaultValue'
     * 
     * Metamodel description:
     * <i><p>Default value for the module parameter, written as a string.</p>
     * </i>
     */
    @objid ("d4b4cd10-0862-4450-812c-4b7ad4edddd4")
    String getDefaultValue();

    /**
     * Setter for attribute 'ModuleParameter.DefaultValue'
     * 
     * Metamodel description:
     * <i><p>Default value for the module parameter, written as a string.</p>
     * </i>
     */
    @objid ("36a1ab6e-d9f5-436a-9182-11740dbb6d57")
    void setDefaultValue(String value);

    /**
     * Getter for relation 'ModuleParameter->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a5b11ae5-e322-4b46-8153-5818f719d4b5")
    ModuleComponent getOwner();

    /**
     * Setter for relation 'ModuleParameter->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0b8a435c-8546-4b1d-a6e0-55025ca3bdc8")
    void setOwner(ModuleComponent value);

    /**
     * Getter for relation 'ModuleParameter->EnumType'
     * 
     * Metamodel description:
     * <i>Enumeration used by the parameter. when the attribute "Type" is TYPE_PARAM_ENUM.</i>
     */
    @objid ("c8129680-b3cc-4f82-bebb-fbe539f2e63e")
    EnumeratedPropertyType getEnumType();

    /**
     * Setter for relation 'ModuleParameter->EnumType'
     * 
     * Metamodel description:
     * <i>Enumeration used by the parameter. when the attribute "Type" is TYPE_PARAM_ENUM.</i>
     */
    @objid ("9ad4e832-bceb-4d01-9600-3c2cac35d16f")
    void setEnumType(EnumeratedPropertyType value);

}
