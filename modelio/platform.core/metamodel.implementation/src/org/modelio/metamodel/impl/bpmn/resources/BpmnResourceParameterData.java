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
package org.modelio.metamodel.impl.bpmn.resources;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000a49ca-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnResourceParameterData extends BpmnBaseElementData {
    @objid ("6c2cad24-eaa5-4f0a-8645-0ad5a9aeb334")
     Object mIsRequired = false;

    @objid ("4ce53337-10bf-4e8b-950c-93bc5404c0a4")
     SmObjectImpl mResource;

    @objid ("c511e07b-140f-4494-81a2-d3ec89dd2a11")
     SmObjectImpl mType;

    @objid ("94903290-23fe-4445-b1de-a20b9e1cebaf")
     List<SmObjectImpl> mParameterBindingRefs = null;

    @objid ("16ee6488-227b-4aec-a0d3-fd1d6b5b9d68")
    public BpmnResourceParameterData(BpmnResourceParameterSmClass smClass) {
        super(smClass);
    }

}
