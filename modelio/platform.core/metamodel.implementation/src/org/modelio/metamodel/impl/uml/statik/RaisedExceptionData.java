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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("001afd60-c4bf-1fd8-97fe-001ec947cd2a")
public class RaisedExceptionData extends UmlModelElementData {
    @objid ("39c98477-4930-4682-958f-ceb5d42d1744")
     SmObjectImpl mThrownType;

    @objid ("5aaec33e-7e9b-4f03-a45d-d681ff3f354e")
     SmObjectImpl mThrower;

    @objid ("52836773-dc1e-4527-ac97-38aee9f43ec1")
    public RaisedExceptionData(RaisedExceptionSmClass smClass) {
        super(smClass);
    }

}
