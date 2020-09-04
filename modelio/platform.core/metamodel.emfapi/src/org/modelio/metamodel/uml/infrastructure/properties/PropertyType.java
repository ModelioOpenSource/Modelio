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
    @objid ("8a706a62-2722-40e9-a900-6f6ef08be566")
    public static final String MNAME = "PropertyType";

    /**
     * The metaclass qualified name.
     */
    @objid ("750533c2-e918-48ca-89bf-3986a4c12390")
    public static final String MQNAME = "Infrastructure.PropertyType";

    /**
     * Getter for attribute 'PropertyType.BaseType'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("08d6506e-75a2-43c6-b839-f9362b4c0770")
    PropertyBaseType getBaseType();

    /**
     * Setter for attribute 'PropertyType.BaseType'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a8137cea-ac3a-4d73-8fd5-6a62618877d5")
    void setBaseType(PropertyBaseType value);

    /**
     * Getter for relation 'PropertyType->ModuleOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c98b8cee-b503-442d-abbd-ba6ad777efb9")
    ModuleComponent getModuleOwner();

    /**
     * Setter for relation 'PropertyType->ModuleOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9c4d714c-0b6f-4546-a15a-9b60fbd793f3")
    void setModuleOwner(ModuleComponent value);

    /**
     * Getter for relation 'PropertyType->Typed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("874c5c06-0ff6-4aa1-bc32-0ef676b165fa")
    EList<PropertyDefinition> getTyped();

    /**
     * Filtered Getter for relation 'PropertyType->Typed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e20833a4-e26d-44f4-b3d4-e4e2e45b5005")
    <T extends PropertyDefinition> List<T> getTyped(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'PropertyType->AnalystOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a88dff11-af4b-4371-9ba3-eee756318721")
    Profile getAnalystOwner();

    /**
     * Setter for relation 'PropertyType->AnalystOwner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("72080b2e-6db9-4091-b424-8f526a9c07e5")
    void setAnalystOwner(Profile value);

}
