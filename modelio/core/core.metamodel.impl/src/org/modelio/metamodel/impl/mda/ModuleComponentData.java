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

package org.modelio.metamodel.impl.mda;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0065c782-c4bf-1fd8-97fe-001ec947cd2a")
public class ModuleComponentData extends AbstractProjectData {
    @objid ("56a35cb0-0f6a-4f09-9030-47880a0fc678")
    Object mLicenseKey = 0;

    @objid ("bb636d97-857d-4b90-8d6c-b7478a9bce50")
    Object mMajVersion = 0;

    @objid ("73898681-0371-4309-ad48-a636c2c78c6e")
    Object mMinVersion = 0;

    @objid ("207c9ba6-24ef-4601-9dfc-266e439e742f")
    Object mMinMinVersion = "";

    @objid ("d9f4846a-4a67-4bab-bf0f-3a2fc53fc54d")
    Object mMinBinVersionCompatibility = "";

    @objid ("5767ff9c-2d50-4add-b5f5-1a7b43aa5c87")
    Object mJavaClassName = "";

    @objid ("d84da840-b63e-42f3-8011-4e29b4d3859f")
    List<SmObjectImpl> mDefinedPropertyType = null;

    @objid ("ab0ee003-5c69-43d2-9634-73844f4548db")
    List<SmObjectImpl> mOwnedProfile = null;

    @objid ("88a1c4f3-7676-4206-990b-8cad71f45b18")
    List<SmObjectImpl> mModuleParameter = null;

    @objid ("b0fd3d1c-b806-4408-8ccd-d238faa8a436")
    List<SmObjectImpl> mDependsOn = null;

    @objid ("505fd3a3-d1b5-4194-bd78-743feecc260b")
    List<SmObjectImpl> mImpacted = null;

    @objid ("0cd5be69-238c-4bb6-abdb-28d63c1e0d3d")
    public  ModuleComponentData(ModuleComponentSmClass smClass) {
        super(smClass);
    }

}
