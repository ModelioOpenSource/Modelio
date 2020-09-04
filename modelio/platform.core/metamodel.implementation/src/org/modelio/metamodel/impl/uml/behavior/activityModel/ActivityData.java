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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("002659ee-c4bf-1fd8-97fe-001ec947cd2a")
public class ActivityData extends BehaviorData {
    @objid ("9d52bf98-cd69-40b4-b099-071959254e1b")
     Object mIsSingleExecution = false;

    @objid ("d63128a7-706a-4da9-a103-3fd06b33985d")
     Object mIsReadOnly = false;

    @objid ("cc7b0ddf-6967-4380-a48c-2c7670261f6a")
     List<SmObjectImpl> mOwnedGroup = null;

    @objid ("5743db60-1150-4cf0-9705-f52951518a29")
     List<SmObjectImpl> mOwnedNode = null;

    @objid ("33575ae5-6249-4611-a414-b2c77f98b71f")
    public ActivityData(ActivitySmClass smClass) {
        super(smClass);
    }

}
