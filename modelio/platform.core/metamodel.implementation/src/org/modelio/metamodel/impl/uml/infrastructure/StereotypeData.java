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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008d9532-c4be-1fd8-97fe-001ec947cd2a")
public class StereotypeData extends ModelElementData {
    @objid ("69368bad-8a5c-4f9c-bcb2-ebf87950e53b")
     Object mImage = "";

    @objid ("6aa93bc4-e069-4518-a8e5-581f50ad6b1e")
     Object mIcon = "";

    @objid ("3bd4b0ea-4ced-4ffc-be74-e2611e45b853")
     Object mIsHidden = false;

    @objid ("a3cb1fa2-fae2-4e4c-b27d-0b94b3eb81c8")
     Object mIsAbstract = false;

    @objid ("8323f3b9-820a-451d-8a85-ef285c737c32")
     Object mLabelKey = "";

    @objid ("d3eea9dc-8bcc-44e6-8769-275919f98add")
     Object mBaseClassName = "";

    @objid ("3b81cf72-c2d7-47d5-8795-be12787040ce")
     SmObjectImpl mDefinedTable;

    @objid ("4fa7b545-5139-4d21-a290-feab5e200428")
     List<SmObjectImpl> mDefinedResourceType = null;

    @objid ("2d3a237f-77bf-419a-bd4e-6eb395b0c9b7")
     SmObjectImpl mOwner;

    @objid ("62d76454-d25d-41e6-91ac-585ab777ca99")
     SmObjectImpl mParent;

    @objid ("dde05eb0-dacd-41d9-8e0e-457585fd4cab")
     List<SmObjectImpl> mDefinedTagType = null;

    @objid ("e48f297f-e998-4cf9-ab47-1116c5dbf39e")
     List<SmObjectImpl> mChild = null;

    @objid ("8427045e-39c6-4c89-8874-0c475f91d65f")
     List<SmObjectImpl> mDefinedNoteType = null;

    @objid ("9fc4a6df-99d0-4075-a70f-c8ba7352e1d4")
     List<SmObjectImpl> mExtendedElement = null;

    @objid ("05101f9f-e837-40cb-8967-11b2c9a11a25")
    public StereotypeData(StereotypeSmClass smClass) {
        super(smClass);
    }

}
