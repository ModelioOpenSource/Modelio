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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("003cf4e2-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class PinData extends ObjectNodeData {
    @objid ("3bb64c3f-e0d5-44d2-bad6-875f086c1700")
     Object mIsControl = false;

    @objid ("0273a228-2ec3-4046-8a96-a0d252f4d069")
     Object mIsExpansion = false;

    @objid ("f9b26929-0c57-4a2c-af9d-ee57bc4a7070")
     SmObjectImpl mMatched;

    @objid ("43586ece-c3ad-4480-bcbf-cf6cdca407d6")
    public PinData(PinSmClass smClass) {
        super(smClass);
    }

}
