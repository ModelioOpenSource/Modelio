/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0091fc9e-c4be-1fd8-97fe-001ec947cd2a")
public class MetaclassReferenceData extends ElementData {
    @objid ("9d1b6c54-1352-4700-ac8c-09ea6d42ebb3")
     Object mReferencedClassName = "";

    @objid ("e765bf00-5a46-4bec-8e82-0769169e1fcd")
     SmObjectImpl mDefinedTable;

    @objid ("45555a9c-3904-4124-8ea2-893c81a1f60f")
     List<SmObjectImpl> mDefinedNoteType = null;

    @objid ("09b07d3d-a49f-441c-a343-8977ca9b2e1f")
     List<SmObjectImpl> mDefinedResourceType = null;

    @objid ("51897df4-f241-4e43-bcd9-93feedfa2cf3")
     SmObjectImpl mOwnerProfile;

    @objid ("7ab1a957-f60d-4cbd-9b6a-2a98ed196f14")
     List<SmObjectImpl> mDefinedTagType = null;

    @objid ("b7c8f421-713e-4bc1-b6ae-16f894a90d97")
    public MetaclassReferenceData(MetaclassReferenceSmClass smClass) {
        super(smClass);
    }

}
