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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * PackageImport v0.0.9054
 * 
 * 
 * A PackageImport is defined as a directed relationship that identifies a Package whose members are to be imported by a NameSpace.  
 * 
 * A PackageImport is a relationship between an importing NameSpace and a Package, indicating that the importing NameSpace adds the names of the members of the Package to its own NameSpace. 
 * 
 * Conceptually, a PackageImport is equivalent to having an ElementImport to each individual member of the imported NameSpace, unless there is already a separately defined ElementImport.
 * 
 * 
 */
@objid ("00160b66-c4bf-1fd8-97fe-001ec947cd2a")
public interface PackageImport extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("2abecd69-aa64-41fd-a2c5-4e8f5e19d930")
    public static final String MNAME = "PackageImport";

    /**
     * The metaclass qualified name.
     */
    @objid ("1ec74fc1-87a7-47ef-932c-6806720a213d")
    public static final String MQNAME = "Standard.PackageImport";

    /**
     * Getter for attribute 'PackageImport.Visibility'
     * 
     * Metamodel description:
     * <i>Member visibility (public or private).</i>
     * 
     */
    @objid ("cf8eace4-57b1-4476-9206-c39dd5c4d96d")
    VisibilityMode getVisibility();

    /**
     * Setter for attribute 'PackageImport.Visibility'
     * 
     * Metamodel description:
     * <i>Member visibility (public or private).</i>
     * 
     */
    @objid ("e6a98e65-fd8c-4d5d-a129-af625d349350")
    void setVisibility(VisibilityMode value);

    /**
     * Getter for relation 'PackageImport->ImportingOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("100b2c4d-495b-4944-b176-774be730dbfa")
    Operation getImportingOperation();

    /**
     * Setter for relation 'PackageImport->ImportingOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6c2cd227-7108-4065-8418-08fc4965cff8")
    void setImportingOperation(Operation value);

    /**
     * Getter for relation 'PackageImport->ImportingNameSpace'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8edc8ad8-e372-412a-8ad1-84d428b8b909")
    NameSpace getImportingNameSpace();

    /**
     * Setter for relation 'PackageImport->ImportingNameSpace'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("d8f3a223-8aad-40c2-876c-9be8b2ffeb84")
    void setImportingNameSpace(NameSpace value);

    /**
     * Getter for relation 'PackageImport->ImportedPackage'
     * 
     * Metamodel description:
     * <i>Specifies the Package whose members are imported into a Namespace. Subsets DirectedRelationship:: target.</i>
     * 
     */
    @objid ("23bcb486-39e3-4cdc-be59-6a97b2b5ad82")
    Package getImportedPackage();

    /**
     * Setter for relation 'PackageImport->ImportedPackage'
     * 
     * Metamodel description:
     * <i>Specifies the Package whose members are imported into a Namespace. Subsets DirectedRelationship:: target.</i>
     * 
     */
    @objid ("bcaff679-3c51-4b64-870a-e39276b3638f")
    void setImportedPackage(Package value);
}

