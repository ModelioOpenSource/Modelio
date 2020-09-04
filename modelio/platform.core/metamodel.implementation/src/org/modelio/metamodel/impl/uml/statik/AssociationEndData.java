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
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0097b0da-c4be-1fd8-97fe-001ec947cd2a")
public class AssociationEndData extends StructuralFeatureData {
    @objid ("81dd7398-35b3-496e-945f-ed9318fcb287")
     Object mAggregation = AggregationKind.KINDISASSOCIATION;

    @objid ("d058ebb8-ff1b-47b5-87a6-cdcc3d14bfb5")
     Object mIsChangeable = true;

    @objid ("6924d170-32ad-4f51-bb2b-d66db8dc5444")
     SmObjectImpl mTarget;

    @objid ("22c0cb57-fc0e-49d1-baa3-895f781e1166")
     SmObjectImpl mOppositeOwner;

    @objid ("68a1a394-7c2a-4ff9-9286-7e883259dbae")
     SmObjectImpl mSource;

    @objid ("bd27e36d-f1c2-44b8-afc0-261058753fb0")
     List<SmObjectImpl> mOccurence = null;

    @objid ("3f127ad1-463a-4afa-b713-0609cfee9130")
     List<SmObjectImpl> mSent = null;

    @objid ("1403297f-26ad-4f00-9fc5-e2e8271e67cc")
     List<SmObjectImpl> mQualifier = null;

    @objid ("5adf7119-7e1b-4ba2-ae55-fce07ea8b5fc")
     SmObjectImpl mOpposite;

    @objid ("e5d3eece-3f05-44a3-9000-226794e7eb11")
     List<SmObjectImpl> mRepresentingObjectNode = null;

    @objid ("1419bf00-a60c-46ad-8531-1119c27b57f3")
     SmObjectImpl mAssociation;

    @objid ("8baa1f1c-b35d-4635-9d31-fa97028af4f9")
    public AssociationEndData(AssociationEndSmClass smClass) {
        super(smClass);
    }

}
