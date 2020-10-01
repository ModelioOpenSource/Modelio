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
package org.modelio.metamodel.impl.bpmn.processCollaboration;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0075e3f6-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnParticipantData extends BpmnBaseElementData {
    @objid ("a7323a1f-0e83-42ec-8259-6ff0227c7ca0")
     Object mMultiplicityMin = 0;

    @objid ("245469ad-641f-426d-956f-a9e63d655036")
     Object mMultiplicityMax = 0;

    @objid ("c1fc62ac-6608-443b-8e2d-ab7f2d18c33a")
     SmObjectImpl mProcess;

    @objid ("fef296fd-9241-49a6-9ed7-88d3fa677333")
     SmObjectImpl mContainer;

    @objid ("2e09d1d3-6b27-4b63-8d14-fe531afcabd7")
     List<SmObjectImpl> mEndPointRefs = null;

    @objid ("9905aca7-d645-420f-942d-c2a19b16a5d1")
     List<SmObjectImpl> mInterfaceRefs = null;

    @objid ("d0b6d5c3-c802-4c28-8865-e6c2a098ca04")
    public BpmnParticipantData(BpmnParticipantSmClass smClass) {
        super(smClass);
    }

}
