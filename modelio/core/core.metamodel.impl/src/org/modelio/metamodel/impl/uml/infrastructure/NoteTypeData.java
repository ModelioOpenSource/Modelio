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

@objid ("008c3692-c4be-1fd8-97fe-001ec947cd2a")
public class NoteTypeData extends ModelElementData {
    @objid ("dfbd33ef-6c7d-4bf4-a653-4e88f73ddfc3")
    Object mIsHidden = false;

    @objid ("9f9adb0b-0100-42cb-8dfb-f48db0c473a0")
    Object mLabelKey = "";

    @objid ("ddab031f-c7cb-4882-948f-9bb6d793c207")
    Object mMimeType = "";

    @objid ("9d49c819-4bba-4a28-8b4c-c1d991e4b1f1")
    List<SmObjectImpl> mElement = null;

    @objid ("d59ed088-d304-42b9-9ed0-4023961f599a")
    SmObjectImpl mOwnerStereotype;

    @objid ("491b1ba5-5ee9-4641-aa4d-f98ae7bb6219")
    SmObjectImpl mOwnerReference;

    @objid ("cd28d5b2-3475-4d15-9996-b582cdadb6a0")
    public  NoteTypeData(NoteTypeSmClass smClass) {
        super(smClass);
    }

}
