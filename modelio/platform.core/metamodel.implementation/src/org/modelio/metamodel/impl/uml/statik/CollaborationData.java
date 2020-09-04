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

@objid ("00044bf6-c4bf-1fd8-97fe-001ec947cd2a")
public class CollaborationData extends NameSpaceData {
    @objid ("e4bbce98-e4f5-4031-bf9b-56f6232389cd")
     Object mIsConcurrent = false;

    @objid ("9a11833e-b10b-49ab-b994-7ea4b8aede5e")
     SmObjectImpl mORepresented;

    @objid ("9239ee91-3b39-4c0e-983d-5ad4668e0cb5")
     SmObjectImpl mBRepresented;

    @objid ("da5361fd-471f-4dd3-8fa3-f1d8010d6fde")
     List<SmObjectImpl> mOccurrence = null;

    @objid ("3a227096-e99a-465e-8a29-37967eed161f")
    public CollaborationData(CollaborationSmClass smClass) {
        super(smClass);
    }

}
