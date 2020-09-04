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
package org.modelio.metamodel.impl.bpmn.rootElements;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0078e650-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnArtifactData extends BpmnBaseElementData {
    @objid ("d86ea989-fd11-43d8-8408-1911b18692dc")
     SmObjectImpl mSubProcess;

    @objid ("7d84dfb3-21ad-44c5-89e1-b05ade6e703b")
     SmObjectImpl mCollaboration;

    @objid ("2a3db355-b0e2-479f-86ea-4b7f02a76948")
     SmObjectImpl mProcess;

    @objid ("9a77cf73-6bc4-4e6f-b814-d1964c404730")
    public BpmnArtifactData(BpmnArtifactSmClass smClass) {
        super(smClass);
    }

}
