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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008489ba-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnSubProcessData extends BpmnActivityData {
    @objid ("d6fe3b6f-83b8-4bb2-bad7-500b577d938e")
     List<SmObjectImpl> mArtifact = null;

    @objid ("fb8352c3-630e-41d9-b8f8-74dcbb4ad2d4")
     List<SmObjectImpl> mFlowElement = null;

    @objid ("02ebc443-f4ee-4adb-9fcc-ca5864cf6deb")
     SmObjectImpl mLaneSet;

    @objid ("1d606cdf-8713-49dd-b2c2-72e52c87ec54")
    public BpmnSubProcessData(BpmnSubProcessSmClass smClass) {
        super(smClass);
    }

}
