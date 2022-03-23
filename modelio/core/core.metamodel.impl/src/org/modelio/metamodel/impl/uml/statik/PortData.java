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
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00193926-c4bf-1fd8-97fe-001ec947cd2a")
public class PortData extends BindableInstanceData {
    @objid ("b9a72b7d-2de3-4525-bd7f-8b0d00e10f6e")
    Object mIsBehavior = false;

    @objid ("f0102afd-3c25-40d6-8a86-bc3e70ec6d45")
    Object mIsService = false;

    @objid ("b34b650e-bd81-4fd8-872d-517b846ba1d2")
    Object mIsConjugated = false;

    @objid ("e413ded6-faf0-4f70-b083-16b565ba5ea8")
    Object mDirection = PortOrientation.NONE;

    @objid ("e6690fe6-340a-43a0-8ae3-3bcd57b80979")
    List<SmObjectImpl> mProvided = null;

    @objid ("3573321e-6b06-41c1-8f59-14b00fd7d0dc")
    List<SmObjectImpl> mRequired = null;

    @objid ("fa9991b6-a8a0-43f4-b630-cde16dc46117")
    public  PortData(PortSmClass smClass) {
        super(smClass);
    }

}
