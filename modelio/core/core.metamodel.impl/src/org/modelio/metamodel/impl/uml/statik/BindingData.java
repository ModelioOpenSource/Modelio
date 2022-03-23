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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0001ce8a-c4bf-1fd8-97fe-001ec947cd2a")
public class BindingData extends UmlModelElementData {
    @objid ("255d135f-edc0-483c-99b5-ab3575e7b801")
    SmObjectImpl mConnectorEndRole;

    @objid ("af58354f-b61f-4647-9e85-6cb3155c7889")
    SmObjectImpl mConnectorRole;

    @objid ("22ab0cc6-273e-40fb-a30c-cabdcc673260")
    SmObjectImpl mRole;

    @objid ("aa1ccbdd-75d2-4e45-a427-20c8bc5d6c82")
    SmObjectImpl mRepresentedFeature;

    @objid ("72309319-16cf-43ec-b1d7-479d31354dd1")
    SmObjectImpl mOwner;

    @objid ("bbb4c439-53d6-4ac3-bde5-3bf1bbf8bfa3")
    public  BindingData(BindingSmClass smClass) {
        super(smClass);
    }

}
