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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.VisibilityMode;

/**
 * ElementImport v0.0.9054
 * 
 * 
 * ModelElement.Name: Specifies the name that should be added to the namespace of the importing Package in lieu of the name of the imported PackagableElement. The aliased name must not clash with any other member name in the importing Package. By default, no alias is used.
 */
@objid ("0008034a-c4bf-1fd8-97fe-001ec947cd2a")
public interface ElementImport extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("6004b15e-2464-4bf1-a30c-f79fbbaea418")
    public static final String MNAME = "ElementImport";

    /**
     * The metaclass qualified name.
     */
    @objid ("57accb27-0cd3-4103-b1f8-a5bd42bd6d6d")
    public static final String MQNAME = "Standard.ElementImport";

    /**
     * Getter for attribute 'ElementImport.Visibility'
     * 
     * Metamodel description:
     * <i>Specifies the visibility of the imported PackageableElement within the importing Package. The default visibility is the same as that of the imported element. If the imported element does not have a visibility, it is possible to add visibility to the element import.
     * 
     * The visibility of an ElementImport is either public or private.</i>
     */
    @objid ("f333d29b-5325-47d6-aab0-b62acb358a92")
    VisibilityMode getVisibility();

    /**
     * Setter for attribute 'ElementImport.Visibility'
     * 
     * Metamodel description:
     * <i>Specifies the visibility of the imported PackageableElement within the importing Package. The default visibility is the same as that of the imported element. If the imported element does not have a visibility, it is possible to add visibility to the element import.
     * 
     * The visibility of an ElementImport is either public or private.</i>
     */
    @objid ("b67e4a2c-03c9-49b5-bb2e-7341523c73e9")
    void setVisibility(VisibilityMode value);

    /**
     * Getter for relation 'ElementImport->ImportingNameSpace'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ef612157-25da-48f2-ba02-a1d55a872482")
    NameSpace getImportingNameSpace();

    /**
     * Setter for relation 'ElementImport->ImportingNameSpace'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6c658a02-e297-40dd-bd6a-0fa942bc8d11")
    void setImportingNameSpace(NameSpace value);

    /**
     * Getter for relation 'ElementImport->ImportedElement'
     * 
     * Metamodel description:
     * <i>Specifies the PackageableElement whose name is to be added to a Namespace. Subsets DirectedRelationship::target.</i>
     */
    @objid ("b3357fbf-7f97-47dd-a3b5-a58275f9db9c")
    NameSpace getImportedElement();

    /**
     * Setter for relation 'ElementImport->ImportedElement'
     * 
     * Metamodel description:
     * <i>Specifies the PackageableElement whose name is to be added to a Namespace. Subsets DirectedRelationship::target.</i>
     */
    @objid ("acbf821d-3fb7-430c-91be-b961d882f3b1")
    void setImportedElement(NameSpace value);

    /**
     * Getter for relation 'ElementImport->ImportingOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("eb74ad35-0103-409f-abb3-29767873c37c")
    Operation getImportingOperation();

    /**
     * Setter for relation 'ElementImport->ImportingOperation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("59fe75d5-f031-47ab-a236-ecc0193dde4a")
    void setImportingOperation(Operation value);

}
