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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008a4260-c4be-1fd8-97fe-001ec947cd2a")
public class NoteData extends ModelElementData {
    @objid ("1e3fe554-7625-496f-a9ff-d5f155aff290")
    Object mContent = "";

    @objid ("1f07be5d-2348-4068-81e1-c252ec50b030")
    Object mMimeType = "";

    @objid ("8f35028a-8396-46ba-826c-5f0b40fc53c3")
    SmObjectImpl mModel;

    @objid ("93e6ba36-462b-4d25-b8ef-81f85482979a")
    SmObjectImpl mSubject;

    @objid ("ee36833a-5c41-41ba-b174-f255ecfaaeab")
    public  NoteData(NoteSmClass smClass) {
        super(smClass);
    }

}
