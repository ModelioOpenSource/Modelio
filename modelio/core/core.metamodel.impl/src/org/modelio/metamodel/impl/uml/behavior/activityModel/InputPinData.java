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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("003580cc-c4bf-1fd8-97fe-001ec947cd2a")
public class InputPinData extends PinData {
    @objid ("5349709c-406f-4053-8751-349607ff5e7c")
     Object mIsSelf = false;

    @objid ("f5ffdda1-78e2-43bd-a250-f3f63d7b77b0")
     List<SmObjectImpl> mHandler = null;

    @objid ("3d47182b-7c71-4f9a-aa7b-d7c3188e76bc")
     SmObjectImpl mInputing;

    @objid ("1ce5eef9-3590-4dc1-9e89-38eced73832c")
    public InputPinData(InputPinSmClass smClass) {
        super(smClass);
    }

}
