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

@objid ("0091fc9e-c4be-1fd8-97fe-001ec947cd2a")
public class MetaclassReferenceData extends ElementData {
    @objid ("97df1419-3504-41d0-b151-2643ec95aae5")
    Object mReferencedClassName = "";

    @objid ("83b819d5-8edd-4c87-a5c7-d7c55efadada")
    SmObjectImpl mDefinedTable;

    @objid ("76a1c04b-ba6f-4190-92fb-03808927b844")
    List<SmObjectImpl> mDefinedNoteType = null;

    @objid ("098ecf80-088c-4266-afcc-5d722f3b83f1")
    List<SmObjectImpl> mDefinedResourceType = null;

    @objid ("d48dbad7-08e8-44fe-9fa8-361f588d33e8")
    SmObjectImpl mOwnerProfile;

    @objid ("86542c55-9701-4c91-bfc2-dcddfbec20f8")
    List<SmObjectImpl> mDefinedTagType = null;

    @objid ("0cb41c7e-ffaf-44be-9567-d5a3f3b9c7ed")
    public  MetaclassReferenceData(MetaclassReferenceSmClass smClass) {
        super(smClass);
    }

}
