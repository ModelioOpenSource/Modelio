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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("001037c2-c4bf-1fd8-97fe-001ec947cd2a")
public class LinkEndData extends UmlModelElementData {
    @objid ("47ae3c94-4e72-4682-90c0-44ca76675572")
     Object mIsOrdered = false;

    @objid ("044d8c25-4edb-4c6f-ba50-7cb7fdfeeb2c")
     Object mIsUnique = false;

    @objid ("b571ad5d-cc9e-4072-9367-553605b5d4ae")
     Object mMultiplicityMax = "1";

    @objid ("ae25d2fb-96aa-4d10-be24-bb79fccc13f7")
     Object mMultiplicityMin = "0";

    @objid ("9551d34a-1761-4c85-a6a8-da3f1841a990")
     SmObjectImpl mLink;

    @objid ("0742d624-4b3b-4169-a4f5-6ffd9cc2c1e0")
     SmObjectImpl mTarget;

    @objid ("9e2ea27b-3ba2-456b-b3ef-be5abab52d31")
     SmObjectImpl mOppositeOwner;

    @objid ("927deda4-0276-4ac0-bc79-fb7fb0318c84")
     List<SmObjectImpl> mRealizedInformationFlow = null;

    @objid ("f8cf6a11-cf58-4511-9e32-3f51b73fc4e6")
     SmObjectImpl mModel;

    @objid ("ee4c15b2-cfeb-4c0e-a92a-1d7e31ff326b")
     SmObjectImpl mConsumer;

    @objid ("c06c58c7-dafa-49d4-bdc4-738ec90e45c7")
     SmObjectImpl mOpposite;

    @objid ("14db5c5b-d380-41ea-ab91-9a996e5e20bc")
     SmObjectImpl mSource;

    @objid ("ed919be7-6b1d-4ca8-a483-84115c1321a0")
     SmObjectImpl mProvider;

    @objid ("d1cdd9ca-0afc-4d21-b69b-1ff1f3fab235")
    public LinkEndData(LinkEndSmClass smClass) {
        super(smClass);
    }

}
