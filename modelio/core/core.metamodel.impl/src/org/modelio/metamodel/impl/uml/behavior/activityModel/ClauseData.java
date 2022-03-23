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

package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("002d1d10-c4bf-1fd8-97fe-001ec947cd2a")
public class ClauseData extends UmlModelElementData {
    @objid ("77487532-53a8-440b-8307-a40939368faf")
    Object mTest = "";

    @objid ("1a4d1d8c-da33-46d1-8c6a-f603fc4450de")
    List<SmObjectImpl> mBody = null;

    @objid ("a786cae0-204f-4b9d-89e1-7cb5d392bb3c")
    SmObjectImpl mOwner;

    @objid ("b0e8340b-95bb-4d79-9750-d3237abfc7fd")
    public  ClauseData(ClauseSmClass smClass) {
        super(smClass);
    }

}
