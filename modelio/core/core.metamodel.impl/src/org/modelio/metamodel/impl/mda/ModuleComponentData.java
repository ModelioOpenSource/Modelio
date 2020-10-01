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
package org.modelio.metamodel.impl.mda;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0065c782-c4bf-1fd8-97fe-001ec947cd2a")
public class ModuleComponentData extends AbstractProjectData {
    @objid ("5671f28b-59e4-442f-9009-9df79239b5a7")
     Object mLicenseKey = 0;

    @objid ("e604e2b0-a678-4843-9ca0-3c1e6177b219")
     Object mMajVersion = 0;

    @objid ("2bc14ea7-3ffb-4eb4-9e12-2d1df7a7b094")
     Object mMinVersion = 0;

    @objid ("e2f339a4-124a-4fa9-835e-71acae5783d0")
     Object mMinMinVersion = "";

    @objid ("7ebf0c62-360d-43cd-a75e-13bf349fbb2e")
     Object mMinBinVersionCompatibility = "";

    @objid ("323a2357-e1a8-4f61-8ed9-1f69577f5772")
     Object mJavaClassName = "";

    @objid ("e850b77d-adf7-4e05-857a-3ccacd5340df")
     List<SmObjectImpl> mDefinedPropertyType = null;

    @objid ("4b414730-68a4-4907-ae78-bae4d7575f14")
     List<SmObjectImpl> mOwnedProfile = null;

    @objid ("46cec4b9-5591-46ac-b46c-e05591e3921d")
     List<SmObjectImpl> mModuleParameter = null;

    @objid ("44a01450-c6e7-4b1b-918d-b011326e833c")
     List<SmObjectImpl> mDependsOn = null;

    @objid ("82451fc4-6868-47bf-9dce-f4966178852e")
     List<SmObjectImpl> mImpacted = null;

    @objid ("dbb78097-74f9-4a6d-8c9e-4b02cc0dace8")
    public ModuleComponentData(ModuleComponentSmClass smClass) {
        super(smClass);
    }

}
