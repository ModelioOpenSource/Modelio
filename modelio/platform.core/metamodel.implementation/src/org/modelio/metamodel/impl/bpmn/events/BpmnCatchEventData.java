/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.impl.bpmn.events;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008909ea-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnCatchEventData extends BpmnEventData {
    @objid ("210e7490-b3f6-41c5-a686-6890f979b653")
     Object mParallelMultiple = false;

    @objid ("9bca1b30-438e-4f95-94e9-d763376c5326")
     List<SmObjectImpl> mDataOutputAssociation = null;

    @objid ("94382c6a-2e65-40f1-b379-5e1fd628dfe2")
     SmObjectImpl mDataOutput;

    @objid ("340c2200-2df1-41e5-97e3-e2d9c505e8c1")
    public BpmnCatchEventData(BpmnCatchEventSmClass smClass) {
        super(smClass);
    }

}
