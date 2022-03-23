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

package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.statik.ParameterData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00416784-c4bf-1fd8-97fe-001ec947cd2a")
public class BehaviorParameterData extends ParameterData {
    @objid ("1c2840fb-2d07-4e1f-93ca-b07d03a156fd")
    List<SmObjectImpl> mRepresentingObjectNode = null;

    @objid ("f8c8331c-1a4e-4b53-b646-800e1a0a32a0")
    SmObjectImpl mOwner;

    @objid ("60cb4ce8-ee96-475b-a1ef-15bb9a20d85d")
    SmObjectImpl mMapped;

    @objid ("2f6ff0db-9897-457d-b161-a4fe3d99b416")
    public  BehaviorParameterData(BehaviorParameterSmClass smClass) {
        super(smClass);
    }

}
