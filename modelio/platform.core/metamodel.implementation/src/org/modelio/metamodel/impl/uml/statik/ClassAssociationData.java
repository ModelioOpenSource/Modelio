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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000325f0-c4bf-1fd8-97fe-001ec947cd2a")
public class ClassAssociationData extends UmlModelElementData {
    @objid ("f030ff36-aebc-48ad-9f62-bd514e24dd77")
     SmObjectImpl mNaryAssociationPart;

    @objid ("4bfd6eae-9de4-4aad-a563-e2b8886696b7")
     SmObjectImpl mClassPart;

    @objid ("6eae2fe4-036e-42cc-98e5-79f0c14279b0")
     SmObjectImpl mAssociationPart;

    @objid ("50856220-9360-4235-a596-670a85121dcc")
    public ClassAssociationData(ClassAssociationSmClass smClass) {
        super(smClass);
    }

}
