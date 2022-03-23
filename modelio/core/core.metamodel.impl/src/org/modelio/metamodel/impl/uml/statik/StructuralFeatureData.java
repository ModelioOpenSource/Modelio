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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("001f7f70-c4bf-1fd8-97fe-001ec947cd2a")
public class StructuralFeatureData extends FeatureData {
    @objid ("ba76928c-95b5-49ed-9486-0048b34e89e7")
    Object mChangeable = KindOfAccess.READWRITE;

    @objid ("7675d266-b1d7-45f2-93ae-2dea44bb7014")
    Object mIsDerived = false;

    @objid ("7fb3a510-4892-4f01-9b99-f9a96e933ad8")
    Object mIsOrdered = false;

    @objid ("963b607d-abd5-4bba-9f1b-0f8a434f3bd5")
    Object mIsUnique = false;

    @objid ("935d5b1f-1952-4535-bd6a-c81264e84ee6")
    Object mMultiplicityMin = "0";

    @objid ("6aa2fc4a-d4f7-4ce3-8ae3-b0ab2d9a2001")
    Object mMultiplicityMax = "1";

    @objid ("18f4fa7c-d79f-4c21-887c-5f4567d11003")
    List<SmObjectImpl> mRealizedInformationFlow = null;

    @objid ("cc5da560-8601-4822-adce-faf7ad2e2e53")
    public  StructuralFeatureData(StructuralFeatureSmClass smClass) {
        super(smClass);
    }

}
