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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.impact;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("f1080505-52a8-4f93-a180-fddc182cfd72")
public class ImpactLinkData extends ModelElementData {
    @objid ("0769a101-1bae-4d22-ae3c-d6408c560c0a")
     SmObjectImpl mDependsOn;

    @objid ("18d91343-8fee-4bb9-8dd6-a8e865ef5ba1")
     SmObjectImpl mImpacted;

    @objid ("1ed76d4d-08a7-4ad5-ab4f-3d4f3fb3875d")
     List<SmObjectImpl> mCauses = null;

    @objid ("a138000e-1424-4951-a628-fe67085b114a")
     SmObjectImpl mOwner;

    @objid ("7a38412d-53d3-4e0f-a98f-05497089170a")
    public ImpactLinkData(ImpactLinkSmClass smClass) {
        super(smClass);
    }

}
