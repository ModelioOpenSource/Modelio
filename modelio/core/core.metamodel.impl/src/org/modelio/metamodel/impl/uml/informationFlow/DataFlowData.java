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
package org.modelio.metamodel.impl.uml.informationFlow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0064a956-c4bf-1fd8-97fe-001ec947cd2a")
public class DataFlowData extends UmlModelElementData {
    @objid ("a40a4f51-4fdc-4412-ab5f-3827b7345afe")
     SmObjectImpl mDestination;

    @objid ("263628a1-c0b2-4b95-8d22-f7af2c000d49")
     SmObjectImpl mOrigin;

    @objid ("147bbe12-36d8-40e5-88e8-0f33f5f9600d")
     SmObjectImpl mOwner;

    @objid ("65b9e056-5de3-475c-a566-581a4f6e5dea")
     SmObjectImpl mSModel;

    @objid ("32a0abee-b736-426b-9b4a-93a499b5fd9b")
    public DataFlowData(DataFlowSmClass smClass) {
        super(smClass);
    }

}
