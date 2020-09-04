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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelTreeData;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0011d8de-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class NameSpaceData extends ModelTreeData {
    @objid ("8dfc1181-8797-456d-ae31-3c83fa5abb6c")
     Object mIsAbstract = false;

    @objid ("60d36a84-f508-4483-bdf5-b10d76b8fa46")
     Object mIsLeaf = false;

    @objid ("abb42799-0c0a-4e16-af6c-f79694010698")
     Object mIsRoot = false;

    @objid ("d48a2372-1067-4acb-bb5d-bd1e9388e5ac")
     Object mVisibility = VisibilityMode.PUBLIC;

    @objid ("5788ef01-f407-4625-9294-b5fc22af575b")
     List<SmObjectImpl> mParent = null;

    @objid ("1951947e-e76e-4d08-850d-60d5b0bbfe02")
     List<SmObjectImpl> mTemplateInstanciation = null;

    @objid ("83f8b57e-d8b7-4229-b5a1-865d08bf1aa7")
     List<SmObjectImpl> mRepresenting = null;

    @objid ("07105857-97dd-44a2-b40f-f6d458b31376")
     List<SmObjectImpl> mOwnedBehavior = null;

    @objid ("1583a430-605c-4f04-9088-58c796e5ec78")
     List<SmObjectImpl> mReceived = null;

    @objid ("65aa8973-2864-4c1a-b142-bd4a1bfcd66a")
     List<SmObjectImpl> mOwnedInformationFlow = null;

    @objid ("59c194dd-aeef-4c42-ab06-8539bfe252f7")
     List<SmObjectImpl> mImporting = null;

    @objid ("b8fa5f55-8fc1-48dd-86b8-bf1de08c235c")
     List<SmObjectImpl> mSent = null;

    @objid ("f5eeba6a-50ec-461f-abba-731f9d182a77")
     List<SmObjectImpl> mOwnedDataFlow = null;

    @objid ("6cf040e9-1835-4836-b8ab-241ed72e6d49")
     List<SmObjectImpl> mOwnedCollaborationUse = null;

    @objid ("b55d02af-e227-4845-87f9-c33ebb4592c8")
     List<SmObjectImpl> mOwnedPackageImport = null;

    @objid ("eb731475-74c1-4ff0-9129-ce0d0f55731a")
     List<SmObjectImpl> mTemplate = null;

    @objid ("8b4b2be5-8fb1-471c-ab45-5107f85ec164")
     List<SmObjectImpl> mSpecialization = null;

    @objid ("561958e4-a1b6-4f3f-b781-0994ec4a8899")
     List<SmObjectImpl> mRealized = null;

    @objid ("566edf0b-ee92-45be-bb14-b95518d7ed04")
     List<SmObjectImpl> mDeclared = null;

    @objid ("82d5c6d1-e65f-407d-a0f3-e672eda29b08")
     List<SmObjectImpl> mInstanciatingBinding = null;

    @objid ("3594f332-3395-4bfc-80ca-1a5e4d4fded3")
     List<SmObjectImpl> mOwnedImport = null;

    @objid ("0ebdba0b-5580-4898-a20f-befd41ec2634")
    public NameSpaceData(NameSpaceSmClass smClass) {
        super(smClass);
    }

}
