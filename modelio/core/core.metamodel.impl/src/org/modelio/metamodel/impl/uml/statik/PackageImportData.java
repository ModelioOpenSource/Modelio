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
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00169568-c4bf-1fd8-97fe-001ec947cd2a")
public class PackageImportData extends UmlModelElementData {
    @objid ("37e228aa-f768-4f1c-a143-251e9af10948")
     Object mVisibility = VisibilityMode.PRIVATE;

    @objid ("c0cdc1de-32d6-4cc3-ab2c-320900920f91")
     SmObjectImpl mImportingOperation;

    @objid ("631c5fa4-3ffc-4d5b-a9fa-ed4c179f59dd")
     SmObjectImpl mImportingNameSpace;

    @objid ("13903c71-6ef7-4c70-af67-e1f63e01ef21")
     SmObjectImpl mImportedPackage;

    @objid ("175a4e04-31db-4fa0-98f4-5328afa8ea88")
    public PackageImportData(PackageImportSmClass smClass) {
        super(smClass);
    }

}
