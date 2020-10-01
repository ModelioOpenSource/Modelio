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
package org.modelio.metamodel.impl.bpmn.resources;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000aeda8-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnResourceRoleData extends BpmnBaseElementData {
    @objid ("60558124-2a6c-4826-8078-372c17eb3ad1")
     SmObjectImpl mResourceRef;

    @objid ("e37b7996-6dd8-4ebe-bd5b-c2248bae6c1b")
     SmObjectImpl mAnnotated;

    @objid ("14435903-f1e8-4c2d-aeb3-3d26b73e490f")
     List<SmObjectImpl> mResourceParameterBinding = null;

    @objid ("3cdeb906-93ee-47b3-9158-7cc124d7807c")
     SmObjectImpl mProcess;

    @objid ("dc1c2fa7-364e-4672-a75a-bab7d6b6a5e0")
    public BpmnResourceRoleData(BpmnResourceRoleSmClass smClass) {
        super(smClass);
    }

}
