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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.impl.diagrams;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("006e66a8-c4bf-1fd8-97fe-001ec947cd2a")
public class DiagramSetData extends ModelElementData {
    @objid ("0845393c-7d44-4998-9556-8aa7dd25512f")
    List<SmObjectImpl> mSub = null;

    @objid ("599554b4-301d-48e3-bf86-bfc22909ed4d")
    SmObjectImpl mParent;

    @objid ("a21b6d76-462d-4e86-8fa6-8fe1df33e93d")
    List<SmObjectImpl> mReferencedDiagram = null;

    @objid ("30aa8352-470a-4f10-bf83-2fce586bff67")
    SmObjectImpl mOwner;

    @objid ("0e7d5c04-c1be-43be-9eed-117ee2af856b")
    public  DiagramSetData(DiagramSetSmClass smClass) {
        super(smClass);
    }

}
