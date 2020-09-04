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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("12ba785b-8cd0-43d6-b472-cb571e989c4b")
public class AbstractResourceData extends ModelElementData {
    @objid ("9cce04a1-d170-44e4-99f2-ae98a6521c3d")
     Object mMimeType = "";

    @objid ("e870853f-04ff-441b-b4b3-48c1b55c85a0")
     Object mStorageInfo = "";

    @objid ("45fdd158-a53b-476b-b415-9c907b295079")
     SmObjectImpl mType;

    @objid ("44497710-a032-4a88-ad10-64b62b8bc953")
     SmObjectImpl mSubject;

    @objid ("13a25590-b315-4ec0-aded-97d1b023a9b7")
    public AbstractResourceData(AbstractResourceSmClass smClass) {
        super(smClass);
    }

}
