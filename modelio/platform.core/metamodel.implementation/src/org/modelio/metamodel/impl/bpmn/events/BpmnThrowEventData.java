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

@objid ("009614d2-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class BpmnThrowEventData extends BpmnEventData {
    @objid ("dc7709ea-455f-48de-9d55-c289a91fa8d0")
     List<SmObjectImpl> mDataInputAssociation = null;

    @objid ("c966c84f-40ba-49e9-8639-832394c8b26a")
     SmObjectImpl mDataInput;

    @objid ("fbad17a2-ae9c-4219-8c53-51ae22a8bd43")
    public BpmnThrowEventData(BpmnThrowEventSmClass smClass) {
        super(smClass);
    }

}
