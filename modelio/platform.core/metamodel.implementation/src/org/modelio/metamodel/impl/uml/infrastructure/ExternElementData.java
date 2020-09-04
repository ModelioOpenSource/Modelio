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
package org.modelio.metamodel.impl.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("b77438d0-b4b9-436b-ae73-660cf660e733")
public class ExternElementData extends ModelElementData {
    @objid ("16ccc998-d23c-4cd4-be99-4a1cf59f9536")
     Object mProvider = "";

    @objid ("5acad07d-0544-451e-8bea-0a92f14a57fb")
     Object mExternId = "";

    @objid ("5d9f3921-d9d4-4754-95fa-397a3ea13fe2")
     Object mLocation = "";

    @objid ("784f4495-874d-4b2f-aacc-9a159dd03323")
     SmObjectImpl mOwner;

    @objid ("9947640b-30f3-4a4f-b055-8aef231fe1e6")
    public ExternElementData(ExternElementSmClass smClass) {
        super(smClass);
    }

}
