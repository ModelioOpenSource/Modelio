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

@objid ("00006a72-c4bf-1fd8-97fe-001ec947cd2a")
public class AttributeLinkData extends UmlModelElementData {
    @objid ("0ab3f83c-321b-441c-9084-4084f289055a")
    Object mValue = "";

    @objid ("ae35b588-1ef7-407a-9b8a-fcf705f1af54")
    SmObjectImpl mAttributed;

    @objid ("344d84b1-32c0-42c4-a7a1-94ad267f09be")
    SmObjectImpl mBase;

    @objid ("3995aef0-297c-49f3-8d83-4325aa9964dc")
    public  AttributeLinkData(AttributeLinkSmClass smClass) {
        super(smClass);
    }

}
