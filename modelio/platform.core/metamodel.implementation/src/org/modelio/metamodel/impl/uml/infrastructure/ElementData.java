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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0086e58e-c4be-1fd8-97fe-001ec947cd2a")
public abstract class ElementData extends SmObjectData {
    @objid ("dad0833d-ed91-4997-b1e9-747e458bec25")
     List<SmObjectImpl> mDiagramElement = null;

    @objid ("733fd795-66ac-41f5-b77c-decbbbd73761")
     List<SmObjectImpl> mAddedToQuery = null;

    @objid ("ab6bd510-c09a-4142-8548-1e4be3c1e811")
     List<SmObjectImpl> mCausedImpact = null;

    @objid ("b9206fa6-82d2-4d12-84e1-ba4708c9e306")
    public ElementData(ElementSmClass smClass) {
        super(smClass);
    }

}
