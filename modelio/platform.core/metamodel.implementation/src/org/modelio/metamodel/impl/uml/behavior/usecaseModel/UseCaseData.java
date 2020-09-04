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
package org.modelio.metamodel.impl.uml.behavior.usecaseModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.statik.GeneralClassData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0058d388-c4bf-1fd8-97fe-001ec947cd2a")
public class UseCaseData extends GeneralClassData {
    @objid ("63817627-6ed5-4681-9f6c-a82e0d5c1390")
     List<SmObjectImpl> mUsed = null;

    @objid ("651ff9ab-8b75-409a-921e-bac233f6039f")
     List<SmObjectImpl> mOwnedExtension = null;

    @objid ("4cb6314d-cc50-4889-b0e4-af9902f432d2")
     List<SmObjectImpl> mUser = null;

    @objid ("7bb50bcd-99c7-4a26-ada4-73d5651b17ec")
    public UseCaseData(UseCaseSmClass smClass) {
        super(smClass);
    }

}
