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

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0067b7c2-ec87-1098-b22e-001ec947cd2a")
public class PropertyTableDefinitionData extends ModelElementData {
    @objid ("2c45feac-12cd-4904-86e7-5608f5474820")
    List<SmObjectImpl> mTypedTable = null;

    @objid ("48766be1-41d1-44e6-8610-b4df2c2bffd9")
    SmObjectImpl mOwnerReference;

    @objid ("12416046-4f5f-4191-8b32-94322566c304")
    SmObjectImpl mOwnerStereotype;

    @objid ("9cb3f374-fe1d-4d31-9928-0d9a3144c47f")
    List<SmObjectImpl> mOwned = null;

    @objid ("c2f583f8-1c1c-41be-8a00-59d0705bca01")
    public  PropertyTableDefinitionData(PropertyTableDefinitionSmClass smClass) {
        super(smClass);
    }

}
