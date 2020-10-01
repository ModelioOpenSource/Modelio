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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;

/**
 * Package v1.1.1
 * 
 * 
 * The Package is the main structuring unit in a model. It defines a hierarchy that breaks down a Model. 
 * 
 * Packages can contain Packages, Classifiers, and so on. 
 * 
 * A Package belongs to its parent Package, represented as a NameSpace in the metamodel, except for the rootPackage, which belongs to a Project.
 */
@objid ("001529ee-c4bf-1fd8-97fe-001ec947cd2a")
public interface Package extends NameSpace {
    /**
     * The metaclass simple name.
     */
    @objid ("4794c8c2-dbb9-4791-a7eb-df14bcd1d27d")
    public static final String MNAME = "Package";

    /**
     * The metaclass qualified name.
     */
    @objid ("ed29c02a-2de9-4d2f-89cf-636d8cf6760d")
    public static final String MQNAME = "Standard.Package";

    /**
     * Getter for attribute 'Package.IsInstantiable'
     * 
     * Metamodel description:
     * <i>This attribute should be discarded. It remains for backward compatibility reasons. A package is not instanciable in UML 2.0.</i>
     */
    @objid ("fbb52665-83fe-4c33-85c2-24b3432d5677")
    boolean isIsInstantiable();

    /**
     * Setter for attribute 'Package.IsInstantiable'
     * 
     * Metamodel description:
     * <i>This attribute should be discarded. It remains for backward compatibility reasons. A package is not instanciable in UML 2.0.</i>
     */
    @objid ("e0b51a34-dbf1-4e2c-8a88-f118c0cd78b2")
    void setIsInstantiable(boolean value);

    /**
     * Getter for relation 'Package->ReceivingMerge'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5b8fa925-d690-4b67-80b8-dbfeb5da1f82")
    EList<PackageMerge> getReceivingMerge();

    /**
     * Filtered Getter for relation 'Package->ReceivingMerge'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6aacb1d3-fa69-49b3-8026-2cbfddd6f7ef")
    <T extends PackageMerge> List<T> getReceivingMerge(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Package->Represented'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3d174b4a-964b-4370-8c10-8c573f08a5e0")
    Project getRepresented();

    /**
     * Setter for relation 'Package->Represented'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d3f923cc-b256-428d-8a53-8c4e7b5a42cb")
    void setRepresented(Project value);

    /**
     * Getter for relation 'Package->Merge'
     * 
     * Metamodel description:
     * <i>Merge relation between Packages. Corresponds to UML 2.0 merge semantics.</i>
     */
    @objid ("cd2353a3-bfcf-4116-baa3-141092cfabcb")
    EList<PackageMerge> getMerge();

    /**
     * Filtered Getter for relation 'Package->Merge'
     * 
     * Metamodel description:
     * <i>Merge relation between Packages. Corresponds to UML 2.0 merge semantics.</i>
     */
    @objid ("bf3df55a-7ac8-4dfb-b425-94eeadffdd77")
    <T extends PackageMerge> List<T> getMerge(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Package->PackageImporting'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3353e559-a290-4405-a4b1-7abc40f30249")
    EList<PackageImport> getPackageImporting();

    /**
     * Filtered Getter for relation 'Package->PackageImporting'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a55ff977-4973-4572-9984-3cc947e3d5ed")
    <T extends PackageImport> List<T> getPackageImporting(java.lang.Class<T> filterClass);

}
