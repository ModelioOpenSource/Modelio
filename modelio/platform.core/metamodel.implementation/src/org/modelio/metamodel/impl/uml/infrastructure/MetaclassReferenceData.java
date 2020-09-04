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

@objid ("0091fc9e-c4be-1fd8-97fe-001ec947cd2a")
public class MetaclassReferenceData extends ElementData {
    @objid ("1a4a859c-ac7b-4e5e-8a00-36734c734a20")
     Object mReferencedClassName = "";

    @objid ("a1fd1a90-a42a-4214-b88f-a2d9a9d9f07e")
     SmObjectImpl mDefinedTable;

    @objid ("76100cae-1ed1-4234-9f24-eaafe783a167")
     List<SmObjectImpl> mDefinedNoteType = null;

    @objid ("60440ff4-63bb-444b-a538-a31ccae8d09e")
     List<SmObjectImpl> mDefinedResourceType = null;

    @objid ("b7a86c80-193a-44ec-b50b-22095cb7edf5")
     SmObjectImpl mOwnerProfile;

    @objid ("5138e2c0-5672-43c0-a6c2-cd287bd953ff")
     List<SmObjectImpl> mDefinedTagType = null;

    @objid ("536b703f-4392-4caa-b6b3-14fa597e0b67")
    public MetaclassReferenceData(MetaclassReferenceSmClass smClass) {
        super(smClass);
    }

}
