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
package org.modelio.metamodel.impl.mda;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0065c782-c4bf-1fd8-97fe-001ec947cd2a")
public class ModuleComponentData extends AbstractProjectData {
    @objid ("2360b3fc-1edb-4c2e-9e37-c3eae4fe8752")
     Object mLicenseKey = 0;

    @objid ("7249ea81-e4c2-4955-8551-7efb1f63aba1")
     Object mMajVersion = 0;

    @objid ("fba2fe06-6a08-4ac9-a867-245ae2b12c59")
     Object mMinVersion = 0;

    @objid ("c40a68b7-f675-440f-8af9-21b021886b0f")
     Object mMinMinVersion = "";

    @objid ("5f687826-cf6f-4ebe-b23c-5e19adb412de")
     Object mMinBinVersionCompatibility = "";

    @objid ("64dcaa13-d49f-4e8e-b068-cbc712792b86")
     Object mJavaClassName = "";

    @objid ("f4dcdb31-fdb1-4dc1-9191-68693cbc83a0")
     List<SmObjectImpl> mDefinedPropertyType = null;

    @objid ("f605c0db-8680-4822-911e-4634354f18cc")
     List<SmObjectImpl> mOwnedProfile = null;

    @objid ("d70dded5-d087-464f-afe4-fe0e9bc46b0a")
     List<SmObjectImpl> mModuleParameter = null;

    @objid ("f3c9a6c1-d7bc-42be-b62d-33007e2b71f0")
     List<SmObjectImpl> mDependsOn = null;

    @objid ("0dd5b1ea-f208-4cec-9363-05c756a0d88f")
     List<SmObjectImpl> mImpacted = null;

    @objid ("b6cd9fc7-f0c9-42b0-b0be-9a35ab0a710e")
    public ModuleComponentData(ModuleComponentSmClass smClass) {
        super(smClass);
    }

}
