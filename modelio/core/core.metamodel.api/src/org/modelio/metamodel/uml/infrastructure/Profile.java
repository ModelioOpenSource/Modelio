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

package org.modelio.metamodel.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;

/**
 * Profile v3.6.00
 * 
 * 
 * 
 * 
 * 
 */
@objid ("008c74a4-c4be-1fd8-97fe-001ec947cd2a")
public interface Profile extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("1061a008-77fe-4829-8327-e8e92018d8ed")
    public static final String MNAME = "Profile";

    /**
     * The metaclass qualified name.
     */
    @objid ("ad52dba1-8536-4f3f-b081-0b7c08871f0e")
    public static final String MQNAME = "Infrastructure.Profile";

    /**
     * Getter for relation 'Profile->DefinedStereotype'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("9e91d3c5-2925-4fe8-9716-155cfb8c37ba")
    EList<Stereotype> getDefinedStereotype();

    /**
     * Filtered Getter for relation 'Profile->DefinedStereotype'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("9724e26c-1d2d-4cc8-8544-cca65b413fdb")
    <T extends Stereotype> List<T> getDefinedStereotype(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Profile->OwnedReference'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("46ed1e26-1889-4db1-afc0-44569a788348")
    EList<MetaclassReference> getOwnedReference();

    /**
     * Filtered Getter for relation 'Profile->OwnedReference'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("57dc8cb3-7252-4989-b1f6-d45f9af62b60")
    <T extends MetaclassReference> List<T> getOwnedReference(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Profile->OwnerModule'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("75611bcb-1779-4a3d-b270-27b99396e231")
    ModuleComponent getOwnerModule();

    /**
     * Setter for relation 'Profile->OwnerModule'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("7bd651e5-f2c6-476f-959d-8e14e52d384c")
    void setOwnerModule(ModuleComponent value);

    /**
     * Getter for relation 'Profile->DefinedType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("7ff8622a-5471-4b36-8a91-7cba16ea0b6a")
    EList<PropertyType> getDefinedType();

    /**
     * Filtered Getter for relation 'Profile->DefinedType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("a430cc91-d487-42f6-9d24-26388723d79d")
    <T extends PropertyType> List<T> getDefinedType(java.lang.Class<T> filterClass);
}

