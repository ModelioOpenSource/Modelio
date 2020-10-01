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
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("003e80d2-c4bf-1fd8-97fe-001ec947cd2a")
public class StructuredActivityNodeData extends ActivityActionData {
    @objid ("693d8c3c-673f-4161-ae66-fdd9d71ea7a4")
     Object mMustIsolate = false;

    @objid ("c33246b6-c160-4bf8-8413-eff0191411a2")
     List<SmObjectImpl> mBody = null;

    @objid ("f044775f-a259-4020-8d9f-a3223e889a07")
    public StructuredActivityNodeData(StructuredActivityNodeSmClass smClass) {
        super(smClass);
    }

}
