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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.metamodel.uml.behavior.commonBehaviors.ParameterEffectKind;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0018552e-c4bf-1fd8-97fe-001ec947cd2a")
public class ParameterData extends UmlModelElementData {
    @objid ("4a2119a5-f760-4084-a946-80a5582de6e2")
     Object mParameterPassing = PassingMode.IN;

    @objid ("6914ec98-c88a-4d4a-b6c3-c2b14461cdf3")
     Object mMultiplicityMin = "1";

    @objid ("7640317e-570c-4e4c-9ca0-07039b63799c")
     Object mMultiplicityMax = "1";

    @objid ("928f42fb-1f6d-4b56-b1ea-df8947ac779a")
     Object mTypeConstraint = "";

    @objid ("ed00bca1-2d48-4772-afe2-a55a89e8b17c")
     Object mDefaultValue = "";

    @objid ("140a182f-c5cf-4286-927b-d57a7db9c829")
     Object mIsOrdered = false;

    @objid ("b2746263-0f0a-4bff-bc6d-70bd4e9a62ec")
     Object mIsUnique = false;

    @objid ("02df78ba-7824-48fd-a1d7-474a6ad0310c")
     Object mIsException = false;

    @objid ("06bb8380-0311-4dfd-9252-a2d6a8463fc3")
     Object mIsStream = false;

    @objid ("84d7d610-cb3a-409e-9820-9f7a3bc482f6")
     Object mEffect = ParameterEffectKind.READEFFECT;

    @objid ("72d20f3c-4142-4a99-bd6a-96389ff88a5e")
     SmObjectImpl mType;

    @objid ("31e62d13-7917-49ee-9c88-45cc8d68a229")
     SmObjectImpl mComposed;

    @objid ("09d5f205-ef42-452d-bbb9-177f35ded019")
     List<SmObjectImpl> mMatching = null;

    @objid ("1c35e0bb-8703-4cd5-86ba-4573b2500fde")
     List<SmObjectImpl> mSRepresentation = null;

    @objid ("7d239079-e1b4-4fd9-a2af-d6036c8f6677")
     SmObjectImpl mReturned;

    @objid ("93a8b366-d630-484b-a872-92c6c498c808")
     List<SmObjectImpl> mBehaviorParam = null;

    @objid ("5ba42652-538e-4fa4-9fcc-b0bf9ecb0edc")
    public ParameterData(ParameterSmClass smClass) {
        super(smClass);
    }

}
