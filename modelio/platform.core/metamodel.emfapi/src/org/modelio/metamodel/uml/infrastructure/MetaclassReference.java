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
package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;

/**
 * MetaclassReference v2.1.00
 */
@objid ("0091820a-c4be-1fd8-97fe-001ec947cd2a")
public interface MetaclassReference extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("07ca4698-4b04-40a6-8a59-1d8a15a03e06")
    public static final String MNAME = "MetaclassReference";

    /**
     * The metaclass qualified name.
     */
    @objid ("bc8d9d22-68be-485f-bd72-85617d365e30")
    public static final String MQNAME = "Infrastructure.MetaclassReference";

    /**
     * Getter for attribute 'MetaclassReference.ReferencedClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("94600e31-6bd7-4df3-8c64-eb42dbd27585")
    String getReferencedClassName();

    /**
     * Setter for attribute 'MetaclassReference.ReferencedClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e55c7596-6d13-44b8-832c-a2cd79640d0a")
    void setReferencedClassName(String value);

    /**
     * Getter for relation 'MetaclassReference->DefinedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0542363a-ada9-4709-b3c5-17c51cd0a09e")
    PropertyTableDefinition getDefinedTable();

    /**
     * Setter for relation 'MetaclassReference->DefinedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d50b240e-7f50-4f14-8470-c0a15d79d034")
    void setDefinedTable(PropertyTableDefinition value);

    /**
     * Getter for relation 'MetaclassReference->DefinedNoteType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("c8de6707-ee53-4bde-bda1-f3303ad95370")
    EList<NoteType> getDefinedNoteType();

    /**
     * Filtered Getter for relation 'MetaclassReference->DefinedNoteType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("3426ce0b-3b71-4507-b8e8-57683c05dcd6")
    <T extends NoteType> List<T> getDefinedNoteType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'MetaclassReference->DefinedResourceType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("34d80486-6da4-40c5-a434-210e9e3a3225")
    EList<ResourceType> getDefinedResourceType();

    /**
     * Filtered Getter for relation 'MetaclassReference->DefinedResourceType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("a5d521aa-5f78-405d-975e-5e6a5278cbd0")
    <T extends ResourceType> List<T> getDefinedResourceType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'MetaclassReference->OwnerProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b087a16e-62d4-4979-871e-8dde9875b3df")
    Profile getOwnerProfile();

    /**
     * Setter for relation 'MetaclassReference->OwnerProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ad7fca5a-0ed0-4031-8120-01c6706b69cc")
    void setOwnerProfile(Profile value);

    /**
     * Getter for relation 'MetaclassReference->DefinedTagType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("9f6e3bd7-5f14-4bca-af52-cd9d46f14721")
    EList<TagType> getDefinedTagType();

    /**
     * Filtered Getter for relation 'MetaclassReference->DefinedTagType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("710a4fe8-32f1-4ed3-8615-46b6526206c5")
    <T extends TagType> List<T> getDefinedTagType(java.lang.Class<T> filterClass);

}
