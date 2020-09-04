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
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0069a7f8-ec87-1098-b22e-001ec947cd2a")
public class PropertyTableData extends ElementData {
    @objid ("4d088ab4-3e84-41f1-9668-bc4098573f0a")
     Object mName = "";

    @objid ("9f105371-6371-4768-b80d-2419cb417cd3")
     Object mContent = "";

    @objid ("b745d7b7-0af1-40b0-a5d9-12902c96cc49")
     SmObjectImpl mOwnerValDef;

    @objid ("8c1ee5ca-4044-49aa-87d9-8c8ac140f6ff")
     SmObjectImpl mOwnerQuery;

    @objid ("a0af11b2-61e6-4c29-aeaa-1728c63e4ddb")
     SmObjectImpl mOwner;

    @objid ("a707d967-f057-453e-a770-137ed9e7e033")
    public PropertyTableData(PropertyTableSmClass smClass) {
        super(smClass);
    }

}
