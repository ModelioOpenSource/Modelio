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
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.objects.BpmnItemKind;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00080bd8-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnItemDefinitionData extends BpmnSharedElementData {
    @objid ("df8de909-871b-4a43-b8af-9ef18fe30d28")
     Object mItemKind = BpmnItemKind.INFORMATION;

    @objid ("b4b10a2e-460d-4919-9a0f-01ec06ea2ba8")
     Object mIsCollection = false;

    @objid ("0f7747fc-e792-4940-adca-2a7de1b90d0a")
     List<SmObjectImpl> mTypedMessage = null;

    @objid ("393bf8df-b146-4dc8-b9c2-7c2fd16e9975")
     List<SmObjectImpl> mTypedItem = null;

    @objid ("eb7ad988-ca82-4e83-ba76-b1ea93e82b25")
     List<SmObjectImpl> mTypedResourceParameter = null;

    @objid ("849d98e3-8681-4492-905d-7fdcb1f184cc")
    public BpmnItemDefinitionData(BpmnItemDefinitionSmClass smClass) {
        super(smClass);
    }

}
