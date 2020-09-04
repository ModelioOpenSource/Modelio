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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.AdHocOrdering;

@objid ("007e3c68-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnAdHocSubProcessData extends BpmnSubProcessData {
    @objid ("e32adb80-8efe-4350-a22a-1a8b2e290c16")
     Object mOrdering = AdHocOrdering.PARALLELORDERING;

    @objid ("cfe74fad-8f10-4f4e-a4e4-a7a19224fd41")
     Object mCancelRemainingInstances = true;

    @objid ("ea5acf70-f84b-4af5-b578-01703bee5a08")
     Object mCompletionCondition = "";

    @objid ("82ee5630-e32b-4ff6-ba56-198d6e020aea")
    public BpmnAdHocSubProcessData(BpmnAdHocSubProcessSmClass smClass) {
        super(smClass);
    }

}
