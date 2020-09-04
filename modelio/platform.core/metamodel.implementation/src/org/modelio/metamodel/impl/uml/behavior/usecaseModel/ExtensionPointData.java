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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.behavior.usecaseModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0059d030-c4bf-1fd8-97fe-001ec947cd2a")
public class ExtensionPointData extends UmlModelElementData {
    @objid ("7dfbecac-7abb-4425-8da2-a8bb14acb9d8")
     Object mVisibility = VisibilityMode.PUBLIC;

    @objid ("86f4cadb-48ef-48a1-932f-f443e4abe341")
     List<SmObjectImpl> mExtended = null;

    @objid ("20743b4b-e4b3-444f-97f5-3ddc50a70b88")
     SmObjectImpl mOwner;

    @objid ("138b07cf-1a3b-48ff-a27d-ed39b48401c3")
    public ExtensionPointData(ExtensionPointSmClass smClass) {
        super(smClass);
    }

}
