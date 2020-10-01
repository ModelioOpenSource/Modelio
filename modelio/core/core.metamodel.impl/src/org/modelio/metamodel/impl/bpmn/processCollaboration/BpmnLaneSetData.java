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

@objid ("00754d60-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnLaneSetData extends BpmnBaseElementData {
    @objid ("b0cea86e-f99a-430f-937d-021b7f92be0a")
     List<SmObjectImpl> mLane = null;

    @objid ("711c8473-5873-43ea-a8d3-aaca71cb6b0e")
     SmObjectImpl mProcess;

    @objid ("96491ded-89de-4086-8e8e-1b411dd043c6")
     SmObjectImpl mParentLane;

    @objid ("a3262b63-ea92-491f-b63d-d9e4fea0d3ac")
     SmObjectImpl mSubProcess;

    @objid ("e6ddf02d-26f5-4d3a-921d-0d38abed3822")
    public BpmnLaneSetData(BpmnLaneSetSmClass smClass) {
        super(smClass);
    }

}
