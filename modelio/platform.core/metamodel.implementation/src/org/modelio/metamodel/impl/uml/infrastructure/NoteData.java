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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008a4260-c4be-1fd8-97fe-001ec947cd2a")
public class NoteData extends ModelElementData {
    @objid ("387d4ba8-0a12-4740-8b4b-cbeb6c99274d")
     Object mContent = "";

    @objid ("9885e5ed-ab24-4f7e-bdf1-a0f9113abf32")
     Object mMimeType = "";

    @objid ("22dddc08-e83d-48f9-9746-fed53c955ffe")
     SmObjectImpl mModel;

    @objid ("288f903c-af97-4b60-a2d7-ae5af3845612")
     SmObjectImpl mSubject;

    @objid ("f9561f5a-c89b-4e0b-b7a8-e5c907488148")
    public NoteData(NoteSmClass smClass) {
        super(smClass);
    }

}
