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

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0069a7f8-ec87-1098-b22e-001ec947cd2a")
public class PropertyTableData extends ElementData {
    @objid ("146b722e-3397-4ea7-8efa-fd9eb5686718")
    Object mName = "";

    @objid ("6506f4e8-b37c-41a1-905e-b3fa1bc0c314")
    Object mContent = "";

    @objid ("85d1fe8b-75d0-419d-9d96-1d5a6a396167")
    SmObjectImpl mOwnerValDef;

    @objid ("c0ae397d-b128-4dfa-9de3-71c70b34314f")
    SmObjectImpl mOwnerQuery;

    @objid ("08987e11-7618-4ff8-acc3-0e796946395e")
    SmObjectImpl mOwner;

    @objid ("9124c7f5-94b3-4449-9ec5-40a8a1653a4f")
    public  PropertyTableData(PropertyTableSmClass smClass) {
        super(smClass);
    }

}
