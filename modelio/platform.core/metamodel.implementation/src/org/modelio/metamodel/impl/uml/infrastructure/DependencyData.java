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
package org.modelio.metamodel.impl.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0086407a-c4be-1fd8-97fe-001ec947cd2a")
public class DependencyData extends ModelElementData {
    @objid ("5e31016e-519f-4524-9508-7ae9d1a40494")
     SmObjectImpl mImpacted;

    @objid ("6f42224a-6721-47d7-bb3f-2bc8cecd352c")
     SmObjectImpl mDependsOn;

    @objid ("2d5421f6-e6df-40ce-81cc-95c7649636b3")
    public DependencyData(DependencySmClass smClass) {
        super(smClass);
    }

}
