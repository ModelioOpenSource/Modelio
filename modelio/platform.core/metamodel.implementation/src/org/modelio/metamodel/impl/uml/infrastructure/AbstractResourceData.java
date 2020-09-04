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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("12ba785b-8cd0-43d6-b472-cb571e989c4b")
public class AbstractResourceData extends ModelElementData {
    @objid ("bbce31e0-fa13-4fc5-b1dc-37d10488fa5f")
     Object mMimeType = "";

    @objid ("231f78a0-fc85-445e-89b1-67c9cb82848b")
     Object mStorageInfo = "";

    @objid ("a4176631-a870-4a8f-a8c1-befbbb4a8b94")
     SmObjectImpl mType;

    @objid ("87febf6f-6553-4b26-9658-d1d82e68e9b9")
     SmObjectImpl mSubject;

    @objid ("c2b703c2-e7cd-479a-ba62-be03ed9adf79")
    public AbstractResourceData(AbstractResourceSmClass smClass) {
        super(smClass);
    }

}
