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
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000e976e-c4bf-1fd8-97fe-001ec947cd2a")
public class InterfaceData extends GeneralClassData {
    @objid ("52322b5b-b656-4126-bede-f1d7e3c736bd")
     List<SmObjectImpl> mRequiring = null;

    @objid ("b0e3a777-2d98-4c57-acd1-a4963779c536")
     List<SmObjectImpl> mImplementedLink = null;

    @objid ("5c9ace6f-a066-4695-a0cf-1097be238835")
     List<SmObjectImpl> mProviding = null;

    @objid ("71f3575f-7880-4298-bc1f-bdcdf89aef2a")
    public InterfaceData(InterfaceSmClass smClass) {
        super(smClass);
    }

}
