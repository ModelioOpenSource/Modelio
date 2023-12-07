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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0086e58e-c4be-1fd8-97fe-001ec947cd2a")
public abstract class ElementData extends SmObjectData {
    @objid ("21b877c3-f0c0-4bc0-bc9d-822026237ff5")
    List<SmObjectImpl> mDiagramElement = null;

    @objid ("ace4fbbf-5d0d-493a-b634-f27f7b1e20c3")
    List<SmObjectImpl> mAddedToQuery = null;

    @objid ("d7a55db2-534b-4a9a-a0a4-eab3573ffcef")
    List<SmObjectImpl> mCausedImpact = null;

    @objid ("617dc91c-5ffe-4a99-a806-58f043e555ed")
    public  ElementData(ElementSmClass smClass) {
        super(smClass);
    }

}
