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
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00907a4a-c4be-1fd8-97fe-001ec947cd2a")
public class TaggedValueData extends ModelElementData {
    @objid ("e24a9152-db4f-42e2-9b4a-104c5ee99485")
     List<SmObjectImpl> mActual = null;

    @objid ("90442ea9-3158-4787-a7c3-a943bd0d2a9f")
     SmObjectImpl mQualifier;

    @objid ("bbcc2673-5f77-451c-838b-ed22fa1576cc")
     SmObjectImpl mDefinition;

    @objid ("07ff44a3-58b0-4021-86d7-7c708c5d022e")
     SmObjectImpl mAnnoted;

    @objid ("be80494c-eeaf-43c2-a455-f11f8c4f401e")
    public TaggedValueData(TaggedValueSmClass smClass) {
        super(smClass);
    }

}
