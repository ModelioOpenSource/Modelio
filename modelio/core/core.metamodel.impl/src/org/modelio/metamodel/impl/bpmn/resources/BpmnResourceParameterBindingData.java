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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000b8d44-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnResourceParameterBindingData extends BpmnBaseElementData {
    @objid ("128c1abd-68d5-4c08-a027-60c308ef246b")
     Object mExpression = "";

    @objid ("5fd25839-175c-4b3e-b6f6-004f37d156fe")
     SmObjectImpl mResourceRole;

    @objid ("75c3721c-6938-4e41-8941-8aa10a1cb251")
     SmObjectImpl mParameterRef;

    @objid ("36ed2353-610e-49af-a701-5dea606237d2")
    public BpmnResourceParameterBindingData(BpmnResourceParameterBindingSmClass smClass) {
        super(smClass);
    }

}
