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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0042dd58-c4bf-1fd8-97fe-001ec947cd2a")
public class EventData extends UmlModelElementData {
    @objid ("8b97fead-4b36-4c29-81d5-e467a283e35e")
     Object mExpression = "";

    @objid ("445f16eb-d87d-477f-be8a-51cd14fec7ae")
     Object mKind = EventType.SIGNALEVENT;

    @objid ("679fc2bd-89fc-4659-8c75-301c09b57357")
     List<SmObjectImpl> mTriggered = null;

    @objid ("24b4a6e8-26e8-4f1d-b667-9d39f6fa9130")
     SmObjectImpl mModel;

    @objid ("1bfaf8e5-f52b-402e-b63b-3ab8cd6443f4")
     List<SmObjectImpl> mOrigin = null;

    @objid ("0ed71705-6814-4a23-a0fd-eb59102996c9")
     SmObjectImpl mCalled;

    @objid ("f94a55a2-dd57-4452-91b5-ab2f4acc1a64")
     SmObjectImpl mComposed;

    @objid ("2aa5c8eb-c5f3-49c9-9a7b-aca77516c87a")
    public EventData(EventSmClass smClass) {
        super(smClass);
    }

}
