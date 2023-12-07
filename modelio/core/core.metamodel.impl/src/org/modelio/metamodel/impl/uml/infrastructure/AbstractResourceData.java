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

package org.modelio.metamodel.impl.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("12ba785b-8cd0-43d6-b472-cb571e989c4b")
public class AbstractResourceData extends ModelElementData {
    @objid ("18b9b16e-33bd-4cd8-81ab-823662b32069")
    Object mMimeType = "";

    @objid ("ca15cb9b-bd06-42bc-a988-0c7ac850b35f")
    Object mStorageInfo = "";

    @objid ("a3435624-48d3-46aa-a0bf-7ac689ccd24b")
    SmObjectImpl mType;

    @objid ("1f87a060-1a8a-4d3e-adf4-5b461b86f5b8")
    SmObjectImpl mSubject;

    @objid ("278d9701-281c-4478-8186-a2c2503cff77")
    public  AbstractResourceData(AbstractResourceSmClass smClass) {
        super(smClass);
    }

}
