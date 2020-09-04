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

@objid ("006e66a8-c4bf-1fd8-97fe-001ec947cd2a")
public class DiagramSetData extends ModelElementData {
    @objid ("8a8fe4a9-bd2c-4377-af5f-61daf831a358")
     List<SmObjectImpl> mSub = null;

    @objid ("5c5b7aad-b3ea-437a-88e0-a1b7f8833efc")
     SmObjectImpl mParent;

    @objid ("ee5c6d42-564c-4544-ae87-3b8cc0c8e75a")
     List<SmObjectImpl> mReferencedDiagram = null;

    @objid ("feb7b654-3390-4fe4-b73f-d21888610666")
     SmObjectImpl mOwner;

    @objid ("56ad3083-759c-4481-89ff-c012b4f386d2")
    public DiagramSetData(DiagramSetSmClass smClass) {
        super(smClass);
    }

}
