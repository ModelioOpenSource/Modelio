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

@objid ("00907a4a-c4be-1fd8-97fe-001ec947cd2a")
public class TaggedValueData extends ModelElementData {
    @objid ("30b48c57-3285-4886-acd6-4a7aa5582505")
     List<SmObjectImpl> mActual = null;

    @objid ("02f48247-b57c-471d-96c7-ef7cbc9b33d9")
     SmObjectImpl mQualifier;

    @objid ("41db5818-4b9d-40c1-b52f-02485749bd5c")
     SmObjectImpl mDefinition;

    @objid ("9b60de83-ed72-448f-94b9-27bb3bb8ed06")
     SmObjectImpl mAnnoted;

    @objid ("69aa41a4-837e-48df-a027-c337cbf2c02f")
    public TaggedValueData(TaggedValueSmClass smClass) {
        super(smClass);
    }

}
