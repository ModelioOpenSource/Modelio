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

@objid ("009084b8-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnLinkEventDefinitionData extends BpmnEventDefinitionData {
    @objid ("6e01a8fd-9e77-49db-8019-34bf964e8ecd")
     List<SmObjectImpl> mSource = null;

    @objid ("4b88053a-dc3f-4318-83e6-1b5b4544cbcf")
     SmObjectImpl mTarget;

    @objid ("8a3bc921-ff5b-4938-849a-03e07e91d2a8")
    public BpmnLinkEventDefinitionData(BpmnLinkEventDefinitionSmClass smClass) {
        super(smClass);
    }

}
