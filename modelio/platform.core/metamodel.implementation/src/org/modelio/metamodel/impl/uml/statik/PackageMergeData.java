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

@objid ("00177474-c4bf-1fd8-97fe-001ec947cd2a")
public class PackageMergeData extends UmlModelElementData {
    @objid ("be0889c4-2833-4b10-875e-fea7446c89f9")
     SmObjectImpl mMergedPackage;

    @objid ("ef384b4e-476b-4e52-8ea7-cebff0c17fd6")
     SmObjectImpl mReceivingPackage;

    @objid ("106dd862-c04c-411f-8b25-bd1bcf3c2134")
    public PackageMergeData(PackageMergeSmClass smClass) {
        super(smClass);
    }

}
