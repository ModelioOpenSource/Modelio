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
package org.modelio.metamodel.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;

/**
 * PropertyType v0.0.9054
 * 
 * 
 * Type of property. Default PropertyTypes already exist in the requirements project: "Text", "MultiText", "Integer", "Real", "Date", "Boolean". No other PropertyTypes should be created in a requirement project. All property types belong to the PropertyContainer.
 */
@objid ("006e6798-ec87-1098-b22e-001ec947cd2a")
public interface PropertyType extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("09043919-cca4-4a3e-a428-395e1fc38efb")
    public static final String MNAME = "PropertyType";

    /**
     * The metaclass qualified name.
     */
    @objid ("7dddb9cc-9988-43b4-bf6a-581efc3b065f")
    public static final String MQNAME = "Infrastructure.PropertyType";

    /**
     * Getter for attribute 'PropertyType.BaseType'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2fc71587-d947-4d92-a50d-3c689dcad6ae")
    PropertyBaseType getBaseType();

    /**
     * Setter for attribute 'PropertyType.BaseType'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5bd40f51-4d7c-4c50-afff-cc6c424bf2e4")
    void setBaseType(PropertyBaseType value);

    /**
     * Getter for relation 'PropertyType->ModuleOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("70347715-6d36-46d8-b2b7-3ffa40c0492b")
    ModuleComponent getModuleOwner();

    /**
     * Setter for relation 'PropertyType->ModuleOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8232904b-75ef-4dbf-8861-9bf7fd60e42e")
    void setModuleOwner(ModuleComponent value);

    /**
     * Getter for relation 'PropertyType->Typed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("024bf063-8726-4c40-8614-4b83d31c35f7")
    EList<PropertyDefinition> getTyped();

    /**
     * Filtered Getter for relation 'PropertyType->Typed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("62f5e52f-014b-4151-9605-aac480494a37")
    <T extends PropertyDefinition> List<T> getTyped(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'PropertyType->AnalystOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("745293f9-dba7-4fde-906f-2b2c2f77a569")
    Profile getAnalystOwner();

    /**
     * Setter for relation 'PropertyType->AnalystOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("2d4db8d0-5fec-42a9-8e61-73d677f18cf7")
    void setAnalystOwner(Profile value);

}
