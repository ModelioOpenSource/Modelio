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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00718aa4-ec87-1098-b22e-001ec947cd2a")
public class EnumeratedPropertyTypeData extends PropertyTypeData {
    @objid ("5584ea93-b9cd-481c-9689-e63031b41e3c")
     List<SmObjectImpl> mLitteral = null;

    @objid ("4318f5f2-bbd4-449c-9f53-2a2d34974bf0")
     List<SmObjectImpl> mOccurenceConfigParam = null;

    @objid ("be006dda-2a8e-4208-aa4d-055dbfdc842b")
    public EnumeratedPropertyTypeData(EnumeratedPropertyTypeSmClass smClass) {
        super(smClass);
    }

}
