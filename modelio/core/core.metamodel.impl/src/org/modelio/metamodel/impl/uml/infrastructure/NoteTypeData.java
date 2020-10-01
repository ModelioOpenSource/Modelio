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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008c3692-c4be-1fd8-97fe-001ec947cd2a")
public class NoteTypeData extends ModelElementData {
    @objid ("4c62cf0d-6ad8-4021-943f-ff44a65e8ade")
     Object mIsHidden = false;

    @objid ("ec8646e5-f3ef-48bc-a193-740801f32017")
     Object mLabelKey = "";

    @objid ("96a80735-c8ec-4dcd-80bf-b3a78627b595")
     Object mMimeType = "";

    @objid ("c82094d8-1abf-4c44-b84b-5d354d23052f")
     List<SmObjectImpl> mElement = null;

    @objid ("8250de4d-2f36-40d0-9225-202171c83943")
     SmObjectImpl mOwnerStereotype;

    @objid ("4d2d31a7-6967-40eb-b396-69c0da2b92fd")
     SmObjectImpl mOwnerReference;

    @objid ("5158e781-1c82-4db2-b667-3bca1d553caf")
    public NoteTypeData(NoteTypeSmClass smClass) {
        super(smClass);
    }

}
