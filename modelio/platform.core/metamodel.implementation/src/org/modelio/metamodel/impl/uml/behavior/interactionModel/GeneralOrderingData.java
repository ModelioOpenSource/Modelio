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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0046463c-c4bf-1fd8-97fe-001ec947cd2a")
public class GeneralOrderingData extends ElementData {
    @objid ("cbc74461-dc56-46fe-a0c9-4b3a6fa78794")
     SmObjectImpl mBefore;

    @objid ("9ed25b48-8ca8-48d7-a150-7755b733412b")
     SmObjectImpl mAfter;

    @objid ("56f0646f-1e09-4e29-b27d-dfeb31df16d5")
    public GeneralOrderingData(GeneralOrderingSmClass smClass) {
        super(smClass);
    }

}
