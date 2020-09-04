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
package org.modelio.metamodel.impl.uml.behavior.stateMachineModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0054f060-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class StateVertexData extends UmlModelElementData {
    @objid ("8d139667-d3e5-42f2-9b56-ca3e6a6d7a71")
     List<SmObjectImpl> mOutGoing = null;

    @objid ("65af7567-5a8f-4487-be69-3f4096334f4e")
     List<SmObjectImpl> mIncoming = null;

    @objid ("959597ba-b7ab-44e3-a65c-22841f477eb3")
     SmObjectImpl mParent;

    @objid ("71c5b60c-656f-446a-8bee-ca487df34783")
    public StateVertexData(StateVertexSmClass smClass) {
        super(smClass);
    }

}
