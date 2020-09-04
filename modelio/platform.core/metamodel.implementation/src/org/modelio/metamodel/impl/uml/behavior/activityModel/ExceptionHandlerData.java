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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0030e0d0-c4bf-1fd8-97fe-001ec947cd2a")
public class ExceptionHandlerData extends UmlModelElementData {
    @objid ("ff824a88-0860-49e3-b694-dad322ad77b3")
     Object mGuard = "";

    @objid ("4d9d46a0-d6ca-46c0-9e34-ca513bb6b23d")
     Object mWeight = "1";

    @objid ("5fec1ddd-e949-4b40-97c2-8aab3393e8e4")
     SmObjectImpl mProtectedNode;

    @objid ("e230d0eb-1073-48d7-b1e8-6dc5cb50617f")
     SmObjectImpl mExceptionInput;

    @objid ("38b64178-ee5a-4fb7-a889-0bbf5e97c18a")
     List<SmObjectImpl> mExceptionType = null;

    @objid ("703c5ece-3605-47c7-a541-fa68b22f0fd0")
    public ExceptionHandlerData(ExceptionHandlerSmClass smClass) {
        super(smClass);
    }

}
