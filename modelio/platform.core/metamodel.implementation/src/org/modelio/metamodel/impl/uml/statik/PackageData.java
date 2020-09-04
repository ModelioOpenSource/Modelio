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
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0015b9f4-c4bf-1fd8-97fe-001ec947cd2a")
public class PackageData extends NameSpaceData {
    @objid ("bbd464f4-b833-4cdc-b293-f9a9b0afe947")
     Object mIsInstantiable = false;

    @objid ("29e0bc03-c7be-43be-a61e-61e55a1d2a61")
     List<SmObjectImpl> mReceivingMerge = null;

    @objid ("017ab171-33f8-4b75-82eb-8e97d849d699")
     SmObjectImpl mRepresented;

    @objid ("bbef0579-2550-423c-9005-e3b21600d281")
     List<SmObjectImpl> mMerge = null;

    @objid ("2dd94131-0ecb-45d7-bb64-f4c10d3c4485")
     List<SmObjectImpl> mPackageImporting = null;

    @objid ("c5811dd2-82b1-44b7-93b7-797a4c8019d1")
    public PackageData(PackageSmClass smClass) {
        super(smClass);
    }

}
