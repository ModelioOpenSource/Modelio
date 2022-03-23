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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008a4260-c4be-1fd8-97fe-001ec947cd2a")
public class NoteData extends ModelElementData {
    @objid ("591d4e95-f722-47c7-9d88-a7145d061f96")
    Object mContent = "";

    @objid ("02ebf877-cb59-4e06-94ef-dd68c6ca6c35")
    Object mMimeType = "";

    @objid ("b57061e2-485d-4a97-afd7-9f1fd91ecb93")
    SmObjectImpl mModel;

    @objid ("f00c9fff-95dd-40c3-a19f-e99c1b5d7cab")
    SmObjectImpl mSubject;

    @objid ("577d69b9-0eec-464d-832d-fed2041b49d6")
    public  NoteData(NoteSmClass smClass) {
        super(smClass);
    }

}
