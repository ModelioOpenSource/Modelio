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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0046e4ac-c4bf-1fd8-97fe-001ec947cd2a")
public class InteractionData extends BehaviorData {
    @objid ("4fed5001-afca-44ef-b0a3-3ac78129f041")
     List<SmObjectImpl> mFormalGate = null;

    @objid ("b51d0401-90fc-4a74-9aa1-db04609d31d9")
     List<SmObjectImpl> mFragment = null;

    @objid ("07431e48-b4eb-4513-ae24-f533978a305d")
     List<SmObjectImpl> mOwnedLine = null;

    @objid ("a063b186-e31d-4cee-b7b4-5d56b122be4a")
     List<SmObjectImpl> mReferedUse = null;

    @objid ("aad9d49b-613f-4eb5-bcb0-e883f4cd53f0")
    public InteractionData(InteractionSmClass smClass) {
        super(smClass);
    }

}
