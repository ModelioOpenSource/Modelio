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
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0009a7d6-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnResourceData extends BpmnSharedElementData {
    @objid ("30e47c98-c46a-42d2-853f-3a9345e6050f")
    List<SmObjectImpl> mResourceroleRefs = null;

    @objid ("cbf17583-884d-4b32-bfb2-6a44a44b1ec6")
    List<SmObjectImpl> mParameter = null;

    @objid ("7aba616e-8011-4fef-87b8-5e3103661d7e")
    public  BpmnResourceData(BpmnResourceSmClass smClass) {
        super(smClass);
    }

}
