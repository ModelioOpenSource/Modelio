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

package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;

/**
 * MetaclassReference v2.1.00
 * 
 * 
 * 
 * 
 * 
 */
@objid ("0091820a-c4be-1fd8-97fe-001ec947cd2a")
public interface MetaclassReference extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("e620b70a-76d6-47ad-9968-8e5c7b2adeda")
    public static final String MNAME = "MetaclassReference";

    /**
     * The metaclass qualified name.
     */
    @objid ("53e46687-2438-4575-b8ae-269e3cd2b23d")
    public static final String MQNAME = "Infrastructure.MetaclassReference";

    @objid ("99c26a04-ad8c-4a3f-88f6-bdd14c15489a")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'MetaclassReference.ReferencedClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("66e34c4f-de54-427c-9365-3be6763206a2")
    String getReferencedClassName();

    /**
     * Setter for attribute 'MetaclassReference.ReferencedClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("9ffbf192-f263-4581-8986-71395b4cac61")
    void setReferencedClassName(String value);

    /**
     * Getter for relation 'MetaclassReference->DefinedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("adb77f42-699b-436f-9398-01a0a0267a77")
    PropertyTableDefinition getDefinedTable();

    /**
     * Setter for relation 'MetaclassReference->DefinedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f259f6f4-6b25-490f-a6c7-e2168373bc58")
    void setDefinedTable(PropertyTableDefinition value);

    /**
     * Getter for relation 'MetaclassReference->DefinedNoteType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("7a8afc43-79a3-4434-aa10-2491a5ce7a53")
    EList<NoteType> getDefinedNoteType();

    /**
     * Filtered Getter for relation 'MetaclassReference->DefinedNoteType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("2fc44558-babc-4f31-af2d-923a120ee1be")
    <T extends NoteType> List<T> getDefinedNoteType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'MetaclassReference->DefinedResourceType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("c77fde32-e956-499f-9d01-60178dd0a0fa")
    EList<ResourceType> getDefinedResourceType();

    /**
     * Filtered Getter for relation 'MetaclassReference->DefinedResourceType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("bc432a85-5468-424c-9a0e-7d13de1e5020")
    <T extends ResourceType> List<T> getDefinedResourceType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'MetaclassReference->OwnerProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("14b63c96-043d-4cb3-a30a-2a4c78bce8b7")
    Profile getOwnerProfile();

    /**
     * Setter for relation 'MetaclassReference->OwnerProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("69a359ac-c8ab-4c48-8e1a-8148e686c905")
    void setOwnerProfile(Profile value);

    /**
     * Getter for relation 'MetaclassReference->DefinedTagType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("3db2f582-11e0-4f32-b25b-e5f95eb03a46")
    EList<TagType> getDefinedTagType();

    /**
     * Filtered Getter for relation 'MetaclassReference->DefinedTagType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("b8bc47fd-af4b-475b-a15b-be536e137b23")
    <T extends TagType> List<T> getDefinedTagType(java.lang.Class<T> filterClass);
}

