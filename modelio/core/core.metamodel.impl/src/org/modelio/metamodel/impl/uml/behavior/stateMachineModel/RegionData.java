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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("005749aa-c4bf-1fd8-97fe-001ec947cd2a")
public class RegionData extends UmlModelElementData {
    @objid ("51d7573c-c9dc-4c94-9a97-afe3a33c1a6d")
    SmObjectImpl mParent;

    @objid ("e71510d7-85f3-4394-ad0a-ad61a937f23a")
    SmObjectImpl mRepresented;

    @objid ("952190d9-b3a0-4d0e-ad96-c542760e7785")
    List<SmObjectImpl> mSub = null;

    @objid ("373ad844-fe0d-45c3-9356-08fe2a9162e8")
    public  RegionData(RegionSmClass smClass) {
        super(smClass);
    }

}
