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
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;

/**
 * PropertyTableDefinition v0.0.9054
 * 
 * 
 * Definition of a typed property table.
 */
@objid ("00669770-ec87-1098-b22e-001ec947cd2a")
public interface PropertyTableDefinition extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("b7bb423f-4f21-4774-ac78-8767f5e878c6")
    public static final String MNAME = "PropertyTableDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("9586f8b1-8224-4d76-9edb-02ff3213f400")
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
     */
    @objid ("c2b7fd02-a6ba-4b71-b1c7-79a7bb12e655")
    EList<TypedPropertyTable> getTypedTable();

    /**
     * Filtered Getter for relation 'PropertyTableDefinition->TypedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3cf73342-2dbc-4a7c-b622-1dc177f0911f")
    <T extends TypedPropertyTable> List<T> getTypedTable(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'PropertyTableDefinition->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("62c19a47-73da-4816-82e4-3911b5a2ce27")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'PropertyTableDefinition->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1e384d8e-76fd-4a5d-beac-2d67ef84131b")
    void setOwnerReference(MetaclassReference value);

    /**
     * Getter for relation 'PropertyTableDefinition->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("88683fc2-3c56-4e08-9e3e-cbae1f805089")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'PropertyTableDefinition->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8b38e862-f09f-4a46-b0a1-2ed8f4b4edf6")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'PropertyTableDefinition->Owned'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("92fd73fd-1358-4f39-887d-0ce264a32f35")
    EList<PropertyDefinition> getOwned();

    /**
     * Filtered Getter for relation 'PropertyTableDefinition->Owned'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("99129f89-42a0-4ca1-9539-d065963ad28d")
    <T extends PropertyDefinition> List<T> getOwned(java.lang.Class<T> filterClass);

}
