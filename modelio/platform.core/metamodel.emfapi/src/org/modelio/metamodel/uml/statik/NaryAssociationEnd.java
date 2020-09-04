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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NaryAssociation;

/**
 * NaryAssociationEnd v0.0.9054
 * 
 * 
 * null
 */
@objid ("00209ca2-c4bf-1fd8-97fe-001ec947cd2a")
public interface NaryAssociationEnd extends StructuralFeature {
    /**
     * The metaclass simple name.
     */
    @objid ("59f1be32-578b-4a33-9992-7588deb1d8db")
    public static final String MNAME = "NaryAssociationEnd";

    /**
     * The metaclass qualified name.
     */
    @objid ("5bbaf58c-cc35-4c99-a6d6-f7b3c6e4c053")
    public static final String MQNAME = "Standard.NaryAssociationEnd";

    /**
     * Getter for relation 'NaryAssociationEnd->NaryAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("712f826a-1344-4afa-a744-03ade442ac48")
    NaryAssociation getNaryAssociation();

    /**
     * Setter for relation 'NaryAssociationEnd->NaryAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("102edf47-8af0-4321-b4b8-7bf0b3dc1ea8")
    void setNaryAssociation(NaryAssociation value);

    /**
     * Getter for relation 'NaryAssociationEnd->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("64b2c945-cc89-4ab0-9d9d-beb5db28e249")
    Classifier getOwner();

    /**
     * Setter for relation 'NaryAssociationEnd->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("71fc2d52-49b5-478d-8995-85c8afb00f40")
    void setOwner(Classifier value);

}
