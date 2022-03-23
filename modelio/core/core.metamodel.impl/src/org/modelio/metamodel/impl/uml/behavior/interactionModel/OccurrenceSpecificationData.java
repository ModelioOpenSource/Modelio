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

package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("004ab5e6-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class OccurrenceSpecificationData extends InteractionFragmentData {
    @objid ("2b7243fa-fe80-40f1-a3fe-0667a7771d6b")
    List<SmObjectImpl> mToAfter = null;

    @objid ("546e42fc-84a2-4c2b-9c71-eee9093d7d8b")
    List<SmObjectImpl> mToBefore = null;

    @objid ("50db8b77-5116-46ba-b800-bbf746dda9a8")
    public  OccurrenceSpecificationData(OccurrenceSpecificationSmClass smClass) {
        super(smClass);
    }

}
