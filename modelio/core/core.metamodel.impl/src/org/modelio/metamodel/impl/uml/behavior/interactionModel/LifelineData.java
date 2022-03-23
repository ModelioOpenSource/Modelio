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

package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00490d86-c4bf-1fd8-97fe-001ec947cd2a")
public class LifelineData extends UmlModelElementData {
    @objid ("b0d2ec2b-ae81-4852-9a99-79c210a9f11b")
    Object mSelector = "";

    @objid ("5c175ea1-fe40-40af-814e-3b07b58da7e2")
    List<SmObjectImpl> mCoveredBy = null;

    @objid ("adf6ffcc-89fb-4cf8-b129-7c7314392da4")
    SmObjectImpl mDecomposedAs;

    @objid ("505f8ff4-5849-4c0c-8154-5fe558c4fda4")
    SmObjectImpl mOwner;

    @objid ("b4e2355b-38e6-40b6-92c1-507104c2bb1a")
    SmObjectImpl mRepresented;

    @objid ("83ef0cfc-2e74-4a47-9ab0-5ea022fd4fca")
    public  LifelineData(LifelineSmClass smClass) {
        super(smClass);
    }

}
