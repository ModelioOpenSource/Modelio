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
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0021f11a-c4bf-1fd8-97fe-001ec947cd2a")
public class NaryAssociationData extends UmlModelElementData {
    @objid ("b671d465-3e89-473d-9d93-eb56de3e815f")
     List<SmObjectImpl> mOccurence = null;

    @objid ("e70b4ae0-c570-4422-be5d-fa1e8579f671")
     List<SmObjectImpl> mNaryEnd = null;

    @objid ("2966e167-95e6-4214-821d-ad38f3e706bf")
     SmObjectImpl mLinkToClass;

    @objid ("dbe3988f-c30a-48e3-93d8-5cf2beefc507")
    public NaryAssociationData(NaryAssociationSmClass smClass) {
        super(smClass);
    }

}
