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

package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorData;
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00546122-c4bf-1fd8-97fe-001ec947cd2a")
public class StateMachineData extends BehaviorData {
    @objid ("8b30ac9f-f5f7-43da-874e-fb399d71a497")
    Object mKind = KindOfStateMachine.DYNAMIC;

    @objid ("b4bcffee-3619-47c8-abf5-89647abf8710")
    SmObjectImpl mTop;

    @objid ("13bb9514-7b87-4b12-a268-a37a4e14b678")
    List<SmObjectImpl> mSubmachineState = null;

    @objid ("4070a087-5738-47cc-8414-9b7a96aaf695")
    List<SmObjectImpl> mEntryPoint = null;

    @objid ("2f4e8d89-3776-4c61-82f1-489eea3f4b05")
    List<SmObjectImpl> mExitPoint = null;

    @objid ("e982b032-f76c-48c3-93cd-85d7621ca5db")
    public  StateMachineData(StateMachineSmClass smClass) {
        super(smClass);
    }

}
