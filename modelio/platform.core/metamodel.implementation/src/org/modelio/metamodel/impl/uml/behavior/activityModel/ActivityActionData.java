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
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0026da86-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class ActivityActionData extends ActivityNodeData {
    @objid ("19b9cbba-6072-4fad-9fcb-d78056be37ab")
     Object mIsMultipleInstance = false;

    @objid ("47187b25-b3a5-4606-a0d2-72071cfae824")
     Object mIsCompensation = false;

    @objid ("eed76c91-0c66-4af8-a9f5-c2726c254ec2")
     List<SmObjectImpl> mOutput = null;

    @objid ("869915d8-7c12-4d67-9365-a3b7be29192a")
     List<SmObjectImpl> mInput = null;

    @objid ("2a302a60-945c-4ff3-86a2-88adfb7d91d2")
     List<SmObjectImpl> mHandler = null;

    @objid ("e2554043-3577-4519-8341-12ae171e3295")
    public ActivityActionData(ActivityActionSmClass smClass) {
        super(smClass);
    }

}
