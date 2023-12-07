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
    @objid ("0c457f18-7f06-4f2f-96e9-0520fc5d907e")
    public static final String MNAME = "PropertyTableDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("1bf5b078-caf3-4233-8551-2e3838051380")
    public static final String MQNAME = "Infrastructure.PropertyTableDefinition";

    /**
     * Getter for relation 'PropertyTableDefinition->Owned' Return the {@link PropertyDefinition} named 'propName' or
     * <code>null</code>.
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("52247448-83e6-4569-89fe-5e7b0dd2e9e8")
    PropertyDefinition getOwned(String propName);

    @objid ("5778ae33-8940-4874-8d96-0b11553d2f8b")
    ModuleComponent getModule();

    /**
     * Getter for relation 'PropertyTableDefinition->TypedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("5240f9e6-fae2-4559-9408-ebe408870bff")
    EList<TypedPropertyTable> getTypedTable();

    /**
     * Filtered Getter for relation 'PropertyTableDefinition->TypedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e69982ff-b578-4246-9d03-2acb669394b7")
    <T extends TypedPropertyTable> List<T> getTypedTable(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'PropertyTableDefinition->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ec7ec5cc-b43d-4598-9be5-d77f940fdbe3")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'PropertyTableDefinition->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e2bf2486-d7de-4c9a-bacd-374894123f1c")
    void setOwnerReference(MetaclassReference value);

    /**
     * Getter for relation 'PropertyTableDefinition->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8338daa2-4bec-43c0-804c-c2ea2a9f941c")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'PropertyTableDefinition->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6ebd5868-d55c-49ed-87e6-6aa45741617c")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'PropertyTableDefinition->Owned'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("67acbe34-11da-4bcb-9ff5-d576c2a882a3")
    EList<PropertyDefinition> getOwned();

    /**
     * Filtered Getter for relation 'PropertyTableDefinition->Owned'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e8d794fa-b346-4fc7-b93c-0447a84dc6f2")
    <T extends PropertyDefinition> List<T> getOwned(java.lang.Class<T> filterClass);
}

