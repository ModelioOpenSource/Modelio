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
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008d9532-c4be-1fd8-97fe-001ec947cd2a")
public class StereotypeData extends ModelElementData {
    @objid ("2a48319a-ab33-4cc3-b42a-5d3809997213")
    Object mImage = "";

    @objid ("81881e84-6e75-4fbb-a2cf-0f28d1bb9c79")
    Object mIcon = "";

    @objid ("19cef74d-bafc-4724-af9c-74d213713453")
    Object mIsHidden = false;

    @objid ("8ac1e865-535a-4aca-bc93-4538952b48f3")
    Object mIsAbstract = false;

    @objid ("33c88642-e32f-41a9-8d0c-92727ec91793")
    Object mLabelKey = "";

    @objid ("6f2a57de-f944-4ec8-9faa-4476ae372917")
    Object mBaseClassName = "";

    @objid ("daf02181-d097-406a-946a-be29697806f8")
    SmObjectImpl mDefinedTable;

    @objid ("89f4e79f-562f-4590-afdd-ad1dfc770c8c")
    List<SmObjectImpl> mDefinedResourceType = null;

    @objid ("d86bdb9e-9c6e-4b58-b41e-eeece98847af")
    SmObjectImpl mOwner;

    @objid ("e2100dbd-0ede-4b9c-b7de-db8114efa71d")
    SmObjectImpl mParent;

    @objid ("599576a2-1c44-4e45-a136-e3fa7fa665b6")
    List<SmObjectImpl> mDefinedTagType = null;

    @objid ("b5e98e97-e1b8-4784-b3f6-f3c932660cff")
    List<SmObjectImpl> mChild = null;

    @objid ("a95ad195-018a-478f-893d-7f0b248b07ed")
    List<SmObjectImpl> mDefinedNoteType = null;

    @objid ("bad9024d-5f0f-43d7-828e-2f8eb8e94e53")
    List<SmObjectImpl> mExtendedElement = null;

    @objid ("0cb96898-c9bf-4f71-aa6a-60fcf67d095a")
    public  StereotypeData(StereotypeSmClass smClass) {
        super(smClass);
    }

}
