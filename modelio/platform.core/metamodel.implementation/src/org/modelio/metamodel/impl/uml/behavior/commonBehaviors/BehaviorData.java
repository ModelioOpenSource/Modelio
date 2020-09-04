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
package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0040ed22-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BehaviorData extends UmlModelElementData {
    @objid ("c022fe13-dac6-4a05-956c-9bdfddf841fa")
     Object mIsReentrant = false;

    @objid ("9b7063b7-83d5-4d82-b6b5-18dc5de2ca63")
     SmObjectImpl mOwner;

    @objid ("3e3c2b05-5c86-458a-9bef-bea3ec501f9c")
     List<SmObjectImpl> mParameter = null;

    @objid ("9f17492d-aa02-45f2-9ffc-ff6c734b066a")
     SmObjectImpl mOwnerOperation;

    @objid ("14a956e9-4720-4e2b-8c15-517e533465b5")
     List<SmObjectImpl> mOwnedCollaboration = null;

    @objid ("d6a9e389-7526-40dd-ac79-b9e3f43e8aa8")
     List<SmObjectImpl> mCaller = null;

    @objid ("3f44d0e7-f67a-4716-ba9f-162be7c2a194")
     List<SmObjectImpl> mEComponent = null;

    @objid ("ed4d62bf-b79b-47b8-8463-da8859f6c3a3")
     List<SmObjectImpl> mEffectOf = null;

    @objid ("91ffa94a-5060-487a-8d33-3cdc061cd20b")
    public BehaviorData(BehaviorSmClass smClass) {
        super(smClass);
    }

}
