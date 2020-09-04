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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("000c3550-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class GeneralClassData extends ClassifierData {
    @objid ("9a009842-67dd-44bb-9825-1c441ffc1ff3")
     Object mIsElementary = false;

    @objid ("b6fb650d-f708-4eda-bec5-4dce75cbc894")
     List<SmObjectImpl> mOccurence = null;

    @objid ("4e45cc48-2b76-4bb0-acad-d16a1358ef74")
     SmObjectImpl mExceptionInput;

    @objid ("18110298-15fd-4262-8e78-8d7ff357cad7")
     List<SmObjectImpl> mObject = null;

    @objid ("00e05d80-b78a-4591-8ca2-61944356360c")
     List<SmObjectImpl> mSRepresentation = null;

    @objid ("1d0584cb-fd91-433b-89ca-91f38a3b2ce3")
     List<SmObjectImpl> mOccurenceObjectNode = null;

    @objid ("6508c5f0-6c1a-4af4-bdcd-fc2a1493c496")
    public GeneralClassData(GeneralClassSmClass smClass) {
        super(smClass);
    }

}
