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
package org.modelio.metamodel.impl.bpmn.activities;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00852208-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnTaskData extends BpmnActivityData {
    @objid ("971d2557-c8f9-4904-b9a0-9291533cf344")
     Object mIsGlobal = false;

    @objid ("cc7ed777-3595-42e4-b8bb-f6b3d327a05c")
     List<SmObjectImpl> mCaller = null;

    @objid ("360cd8e4-ce75-4792-bad4-540bd07294bd")
    public BpmnTaskData(BpmnTaskSmClass smClass) {
        super(smClass);
    }

}
