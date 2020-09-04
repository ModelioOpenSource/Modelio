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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Package;

/**
 * PackageMerge v0.0.9054
 * 
 * 
 * A PackageMerge is a relationship between two Packages, where the contents of the target Package (the one pointed at) is merged with the contents of the source Package through specialization and redefinition, where applicable. This is a mechanism that should be used when elements of the same name are intended to represent the same concept, regardless of the Package in which they are defined. 
 * 
 * A merging Package will take elements of the same kind with the same name from one or more Packages and merge them together into a single element using Generalization and redefinitions. 
 * 
 * It should be noted that a PackageMerge can be viewed as a short-hand way of explicitly defining those Generalizations and redefinitions. The merged Packages are still available, and the elements in those Packages can be separately qualified.
 */
@objid ("0016e662-c4bf-1fd8-97fe-001ec947cd2a")
public interface PackageMerge extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("a8ea7889-f35a-4d66-b5ac-4fef5cae2698")
    public static final String MNAME = "PackageMerge";

    /**
     * The metaclass qualified name.
     */
    @objid ("0a9f2169-378e-4793-9215-311192fae216")
    public static final String MQNAME = "Standard.PackageMerge";

    /**
     * Getter for relation 'PackageMerge->MergedPackage'
     * 
     * Metamodel description:
     * <i>Package that is merged with the current Package.</i>
     */
    @objid ("6b66d823-79c7-4a53-8eec-0e66b0514052")
    Package getMergedPackage();

    /**
     * Setter for relation 'PackageMerge->MergedPackage'
     * 
     * Metamodel description:
     * <i>Package that is merged with the current Package.</i>
     */
    @objid ("f476eec0-d544-4250-85b2-83669e74ef1b")
    void setMergedPackage(Package value);

    /**
     * Getter for relation 'PackageMerge->ReceivingPackage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("90cdc17f-175b-4424-ae42-9f723acfdeed")
    Package getReceivingPackage();

    /**
     * Setter for relation 'PackageMerge->ReceivingPackage'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("751c3528-751f-4eca-a5f9-d63d5a21ba02")
    void setReceivingPackage(Package value);

}
