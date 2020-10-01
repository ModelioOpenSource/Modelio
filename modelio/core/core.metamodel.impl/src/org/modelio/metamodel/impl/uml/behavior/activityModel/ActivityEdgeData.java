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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00275f10-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class ActivityEdgeData extends UmlModelElementData {
    @objid ("1a65e95c-e64e-4019-b276-48e3d3869bbf")
     Object mGuard = "";

    @objid ("3c9163e5-6cde-4f1d-aef1-67b32707d82a")
     Object mWeight = "1";

    @objid ("1350f12e-9429-42d1-9233-6856d3adc2e7")
     SmObjectImpl mTarget;

    @objid ("a8768a39-738c-480f-b6ed-b7ae633d6dfd")
     SmObjectImpl mSource;

    @objid ("3fb5bbd6-3675-4996-ba96-33dba38fb147")
     SmObjectImpl mInterrupts;

    @objid ("03966403-ec52-4ea3-a505-6ea71e7ccfc2")
     List<SmObjectImpl> mRealizedInformationFlow = null;

    @objid ("e5338438-cd05-48ee-bbcf-bc64af42ef1c")
    public ActivityEdgeData(ActivityEdgeSmClass smClass) {
        super(smClass);
    }

}
