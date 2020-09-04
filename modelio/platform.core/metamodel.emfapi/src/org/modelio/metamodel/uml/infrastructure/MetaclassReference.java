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
    @objid ("feee3bb2-bf20-4cf6-931d-4c53135d412f")
    public static final String MNAME = "MetaclassReference";

    /**
     * The metaclass qualified name.
     */
    @objid ("698f65dc-3507-49b9-b255-e17537b50772")
    public static final String MQNAME = "Infrastructure.MetaclassReference";

    /**
     * Getter for attribute 'MetaclassReference.ReferencedClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f3f8fb41-47e8-4136-9889-fc34aac3d3f9")
    String getReferencedClassName();

    /**
     * Setter for attribute 'MetaclassReference.ReferencedClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("59808615-06b0-48b7-a027-38d78fc10bd5")
    void setReferencedClassName(String value);

    /**
     * Getter for relation 'MetaclassReference->DefinedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("818dee09-2959-443b-9223-8a8a24ff4879")
    PropertyTableDefinition getDefinedTable();

    /**
     * Setter for relation 'MetaclassReference->DefinedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e6f7930d-e513-40f5-a7a6-044fb7df6be5")
    void setDefinedTable(PropertyTableDefinition value);

    /**
     * Getter for relation 'MetaclassReference->DefinedNoteType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("4c85b6a2-3431-4b71-8fba-640038bf6503")
    EList<NoteType> getDefinedNoteType();

    /**
     * Filtered Getter for relation 'MetaclassReference->DefinedNoteType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("c0f45f25-c014-4565-b8f7-097143d498b4")
    <T extends NoteType> List<T> getDefinedNoteType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'MetaclassReference->DefinedResourceType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("5292c322-23d0-4a99-9d8c-234433b5b53c")
    EList<ResourceType> getDefinedResourceType();

    /**
     * Filtered Getter for relation 'MetaclassReference->DefinedResourceType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("be13c135-d5bf-4f14-9794-2c2fbdc9e900")
    <T extends ResourceType> List<T> getDefinedResourceType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'MetaclassReference->OwnerProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d7af9849-7951-4020-8339-9057fcec6b89")
    Profile getOwnerProfile();

    /**
     * Setter for relation 'MetaclassReference->OwnerProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6680b948-597e-4df9-b9a2-de8dc499acb3")
    void setOwnerProfile(Profile value);

    /**
     * Getter for relation 'MetaclassReference->DefinedTagType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("753c2248-3bab-43d5-85ef-45491e275ea2")
    EList<TagType> getDefinedTagType();

    /**
     * Filtered Getter for relation 'MetaclassReference->DefinedTagType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("a7278f67-4bb6-43b3-b137-cb7f649fd404")
    <T extends TagType> List<T> getDefinedTagType(java.lang.Class<T> filterClass);

}
