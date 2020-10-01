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
package org.modelio.metamodel.impl.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008e4b26-c4be-1fd8-97fe-001ec947cd2a")
public class SubstitutionData extends UmlModelElementData {
    @objid ("f91bcb35-afa1-4dc1-bc88-bef5ee62cb48")
     SmObjectImpl mContract;

    @objid ("012e0b5c-309d-4136-a8be-b419dac64548")
     SmObjectImpl mSubstitutingClassifier;

    @objid ("ab26d66d-9763-4c80-adc2-8a9eee7678cd")
    public SubstitutionData(SubstitutionSmClass smClass) {
        super(smClass);
    }

}
