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

package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("001be5d6-c4bf-1fd8-97fe-001ec947cd2a")
public class RequiredInterfaceData extends UmlModelElementData {
    @objid ("3b0bf084-4e46-4504-81a8-12a5bf7c16f5")
    List<SmObjectImpl> mRequiredElement = null;

    @objid ("ef91beb3-bc4f-49f1-8c92-069df33fa92a")
    List<SmObjectImpl> mProvider = null;

    @objid ("4b0f55d9-396f-48b2-97c7-34584d7b3ca0")
    SmObjectImpl mRequiring;

    @objid ("3133b67f-e932-4236-94c4-dca050b3b474")
    List<SmObjectImpl> mNaryProvider = null;

    @objid ("14c8140a-0e72-47e3-8e09-27efb16853b9")
    public  RequiredInterfaceData(RequiredInterfaceSmClass smClass) {
        super(smClass);
    }

}
