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
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0067b7c2-ec87-1098-b22e-001ec947cd2a")
public class PropertyTableDefinitionData extends ModelElementData {
    @objid ("ef084381-ea72-4dd2-a0fe-34f651a55f53")
     List<SmObjectImpl> mTypedTable = null;

    @objid ("6fdef107-aa38-4e84-89f7-e9388d632f09")
     SmObjectImpl mOwnerReference;

    @objid ("13b8a4df-e795-4952-bb46-d3b69cf7c45a")
     SmObjectImpl mOwnerStereotype;

    @objid ("4dfc9776-b2e0-4b29-81fe-2295609aba0b")
     List<SmObjectImpl> mOwned = null;

    @objid ("340f3676-afa1-413a-a140-7f5aa0eef889")
    public PropertyTableDefinitionData(PropertyTableDefinitionSmClass smClass) {
        super(smClass);
    }

}
