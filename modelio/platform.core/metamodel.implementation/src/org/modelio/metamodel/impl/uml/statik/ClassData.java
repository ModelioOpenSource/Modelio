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
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00027d80-c4bf-1fd8-97fe-001ec947cd2a")
public class ClassData extends GeneralClassData {
    @objid ("c0970d91-f662-426e-a84a-05729b5110b6")
     Object mIsActive = false;

    @objid ("ef790702-2443-4f49-ae41-0a4791695be0")
     Object mIsMain = false;

    @objid ("272904e8-69c9-40f0-87b9-ab7703a42019")
     SmObjectImpl mLinkToAssociation;

    @objid ("dd47904c-6d40-4764-b5f3-195ef82e2935")
    public ClassData(ClassSmClass smClass) {
        super(smClass);
    }

}
