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
package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("007984ac-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnFlowElementData extends BpmnBaseElementData {
    @objid ("9bb40f80-f5b5-4347-92ed-295c7b97f12d")
     Object mTriggeredByEvent = false;

    @objid ("87e7bd40-1ad9-4109-bced-b8220af50d0a")
     List<SmObjectImpl> mGroups = null;

    @objid ("2a06f051-1fb9-49a4-b4f0-92533fa35b66")
     SmObjectImpl mSubProcess;

    @objid ("29ecebef-66c3-4134-8f60-a728e924224b")
     List<SmObjectImpl> mLane = null;

    @objid ("f1d6ee8e-9e31-480b-ae5f-839b562ae64a")
     SmObjectImpl mContainer;

    @objid ("cc5823a1-1e26-43b8-9fcf-ca111306bbc7")
    public BpmnFlowElementData(BpmnFlowElementSmClass smClass) {
        super(smClass);
    }

}
