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
    @objid ("059736cb-c7ca-4104-aebb-5e07623851a5")
    public static final String MNAME = "Profile";

    /**
     * The metaclass qualified name.
     */
    @objid ("f0072f03-1583-4db7-b402-0420b7bf3fb2")
    public static final String MQNAME = "Infrastructure.Profile";

    /**
     * Getter for relation 'Profile->DefinedStereotype'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("5ebeef61-7e64-4990-a5c7-eb2be756a20e")
    EList<Stereotype> getDefinedStereotype();

    /**
     * Filtered Getter for relation 'Profile->DefinedStereotype'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("b3345e82-045f-44d7-ace3-941fb3aeacbe")
    <T extends Stereotype> List<T> getDefinedStereotype(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Profile->OwnedReference'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("9e1641fe-f93c-4f2a-89dc-cf7467b9aae5")
    EList<MetaclassReference> getOwnedReference();

    /**
     * Filtered Getter for relation 'Profile->OwnedReference'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("d04ccc58-84a7-4246-8703-423522fb1e93")
    <T extends MetaclassReference> List<T> getOwnedReference(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Profile->OwnerModule'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("102a308d-38be-4ef9-a595-50774b4783f6")
    ModuleComponent getOwnerModule();

    /**
     * Setter for relation 'Profile->OwnerModule'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c4bca63e-1a81-4a2b-a0cc-d01c1916f1a6")
    void setOwnerModule(ModuleComponent value);

    /**
     * Getter for relation 'Profile->DefinedType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("82dcd783-4100-4e8d-8d07-6b8930b90de8")
    EList<PropertyType> getDefinedType();

    /**
     * Filtered Getter for relation 'Profile->DefinedType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("4a19124c-b1b5-40a6-8d6e-8c0fd0ff5afe")
    <T extends PropertyType> List<T> getDefinedType(java.lang.Class<T> filterClass);
}

