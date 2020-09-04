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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.diagrams;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00678cde-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class AbstractDiagramData extends ModelElementData {
    @objid ("47a73f69-7fc1-4580-928e-4b6cadf7db42")
     Object mUiDataVersion = 0;

    @objid ("44e2921e-a712-4225-8986-20fa3337c84d")
     Object mUiData = "";

    @objid ("9fc17f8e-4d37-4dc5-b0fb-bf4640b8557e")
     List<SmObjectImpl> mRepresented = null;

    @objid ("02f8430f-5174-4028-8516-3169cd8ce7ab")
     List<SmObjectImpl> mReferencingSet = null;

    @objid ("efc221c5-6be2-4eed-9e15-89ee20473ebd")
     SmObjectImpl mOrigin;

    @objid ("f5dbe082-f0fd-40a6-a013-0b835d63c722")
    public AbstractDiagramData(AbstractDiagramSmClass smClass) {
        super(smClass);
    }

}
