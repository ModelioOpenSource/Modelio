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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0067b7c2-ec87-1098-b22e-001ec947cd2a")
public class PropertyTableDefinitionData extends ModelElementData {
    @objid ("ce448000-5e45-4cfa-96e5-9c7e012d539f")
     List<SmObjectImpl> mTypedTable = null;

    @objid ("f9ff1ccb-f704-4a76-abc4-d97b341b31fb")
     SmObjectImpl mOwnerReference;

    @objid ("d5f38651-7596-484a-aca9-f3c8d672d3ec")
     SmObjectImpl mOwnerStereotype;

    @objid ("17ec244d-f68d-4185-a64a-34c20ce4fcd7")
     List<SmObjectImpl> mOwned = null;

    @objid ("6c84d21c-fe4e-49a6-bf55-a7f8688b61b5")
    public PropertyTableDefinitionData(PropertyTableDefinitionSmClass smClass) {
        super(smClass);
    }

}
