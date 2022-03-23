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

package org.modelio.metamodel.impl.bpmn.rootElements;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociationDirection;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00779282-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnAssociationData extends BpmnArtifactData {
    @objid ("295f8bb5-b33d-4bc1-9d0c-471d22b0272c")
    Object mAssociationDirection = BpmnAssociationDirection.NONEDIRECTION;

    @objid ("4ad50ca2-c066-42de-9a07-29f9fc79479e")
    SmObjectImpl mTargetRef;

    @objid ("0a0e2a52-3581-4235-a445-58bf9f9cc8b0")
    SmObjectImpl mSourceRef;

    @objid ("308f85bc-7e8c-41ab-b16e-3b7a2a6189c9")
    public  BpmnAssociationData(BpmnAssociationSmClass smClass) {
        super(smClass);
    }

}
