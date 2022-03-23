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

@objid ("000f6478-c4bf-1fd8-97fe-001ec947cd2a")
public class InterfaceRealizationData extends UmlModelElementData {
    @objid ("869e2cd2-2e4c-459a-83fa-d882655f5bfe")
    SmObjectImpl mImplemented;

    @objid ("2c01f285-efa9-44e7-9514-f4be1426b020")
    SmObjectImpl mImplementer;

    @objid ("28b2631f-d5bf-4ad0-9421-7552ad938409")
    public  InterfaceRealizationData(InterfaceRealizationSmClass smClass) {
        super(smClass);
    }

}
