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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008c3692-c4be-1fd8-97fe-001ec947cd2a")
public class NoteTypeData extends ModelElementData {
    @objid ("7411c119-88c0-43f9-a75e-2a0d42a5d5e0")
     Object mIsHidden = false;

    @objid ("795813b4-6010-4ae2-a0cd-651dcedcb415")
     Object mLabelKey = "";

    @objid ("b38d2644-caba-40c6-be21-137c2263e1dc")
     Object mMimeType = "";

    @objid ("2510d406-f083-4d58-b2cf-233aaee17695")
     List<SmObjectImpl> mElement = null;

    @objid ("38d01c4e-797e-4c5f-a16d-a7a8bd176ac0")
     SmObjectImpl mOwnerStereotype;

    @objid ("c019fb61-3cbb-4395-a09d-05f75c757332")
     SmObjectImpl mOwnerReference;

    @objid ("257d7259-318d-4e63-8fdd-fab44b0815ea")
    public NoteTypeData(NoteTypeSmClass smClass) {
        super(smClass);
    }

}
