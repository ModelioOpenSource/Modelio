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

package org.modelio.metamodel.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;

/**
 * PropertyType v0.0.9054
 * 
 * 
 * Type of property. Default PropertyTypes already exist in the requirements project: "Text", "MultiText", "Integer", "Real", "Date", "Boolean". No other PropertyTypes should be created in a requirement project. All property types belong to the PropertyContainer.
 * 
 * 
 */
@objid ("006e6798-ec87-1098-b22e-001ec947cd2a")
public interface PropertyType extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("c9a12494-a9a1-4aaa-93ca-d5e731833fa4")
    public static final String MNAME = "PropertyType";

    /**
     * The metaclass qualified name.
     */
    @objid ("5dc130f0-fee4-4fe6-a48b-9d6f0db472fc")
    public static final String MQNAME = "Infrastructure.PropertyType";

    /**
     * Getter for attribute 'PropertyType.BaseType'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("659df5ea-a3a5-4760-8714-097a082ec901")
    PropertyBaseType getBaseType();

    /**
     * Setter for attribute 'PropertyType.BaseType'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("65fdb874-252c-4352-a8a1-be87d5b51ef0")
    void setBaseType(PropertyBaseType value);

    /**
     * Getter for relation 'PropertyType->ModuleOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("47ae6c1b-794e-4c25-8341-7abda61acbcf")
    ModuleComponent getModuleOwner();

    /**
     * Setter for relation 'PropertyType->ModuleOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("08b93a30-a482-4a0b-b78f-daed897eac3a")
    void setModuleOwner(ModuleComponent value);

    /**
     * Getter for relation 'PropertyType->Typed'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("3da20f78-cf5a-417e-b952-0cd2665a78eb")
    EList<PropertyDefinition> getTyped();

    /**
     * Filtered Getter for relation 'PropertyType->Typed'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("89575393-487c-4fdc-bc9a-b3c1d145da4c")
    <T extends PropertyDefinition> List<T> getTyped(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'PropertyType->AnalystOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("cd8e0980-cd9e-4b94-bc6b-9de4624ed82d")
    Profile getAnalystOwner();

    /**
     * Setter for relation 'PropertyType->AnalystOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("23878237-5f54-4702-9930-0ad750c77702")
    void setAnalystOwner(Profile value);
}

