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

package org.modelio.metamodel.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

/**
 * PropertyTableDefinition v0.0.9054
 * 
 * 
 * Definition of a typed property table.
 * 
 * 
 */
@objid ("00669770-ec87-1098-b22e-001ec947cd2a")
public interface PropertyTableDefinition extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("b35df7b0-3ebc-4da2-b979-363d1922bf42")
    public static final String MNAME = "PropertyTableDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("225f5ed7-3de5-405f-b31d-5f388d818b22")
    public static final String MQNAME = "Infrastructure.PropertyTableDefinition";

    /**
     * Getter for relation 'PropertyTableDefinition->Owned' Return the {@link PropertyDefinition} named 'propName' or
     * <code>null</code>.
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("52247448-83e6-4569-89fe-5e7b0dd2e9e8")
    PropertyDefinition getOwned(String propName);

    /**
     * Getter for relation 'PropertyTableDefinition->TypedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("68665ced-fc04-4cbc-9d82-781b934ecb4c")
    EList<TypedPropertyTable> getTypedTable();

    /**
     * Filtered Getter for relation 'PropertyTableDefinition->TypedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("4a57fa89-bd5f-446c-89e5-40fd11b58794")
    <T extends TypedPropertyTable> List<T> getTypedTable(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'PropertyTableDefinition->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("7c573276-3cec-42ef-a158-555b34627e5c")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'PropertyTableDefinition->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("7a3c1476-78f4-4e77-aeac-5aa3de729832")
    void setOwnerReference(MetaclassReference value);

    /**
     * Getter for relation 'PropertyTableDefinition->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("be419eaa-b951-4045-a7a0-cc4ea7a19057")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'PropertyTableDefinition->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("049ec8ab-03dc-42d3-bbf5-9f36a607dd0a")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'PropertyTableDefinition->Owned'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("3f87d97c-964b-48de-9bbb-ae5936dcd816")
    EList<PropertyDefinition> getOwned();

    /**
     * Filtered Getter for relation 'PropertyTableDefinition->Owned'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("fa620c26-5647-4122-8495-0c34d8fe0ace")
    <T extends PropertyDefinition> List<T> getOwned(java.lang.Class<T> filterClass);

    @objid ("5778ae33-8940-4874-8d96-0b11553d2f8b")
    ModuleComponent getModule();
}

