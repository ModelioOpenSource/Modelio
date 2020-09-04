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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00011508-c4bf-1fd8-97fe-001ec947cd2a")
public class BindableInstanceData extends InstanceData {
    @objid ("f44d6336-c212-4ebd-9b90-5ee77a2ebfd3")
     SmObjectImpl mCluster;

    @objid ("bfd72c45-73e0-49a9-8138-9bddbb0d392a")
     SmObjectImpl mInternalOwner;

    @objid ("0ec1ea84-7bce-47ba-bb96-49ca2bb6be6d")
     List<SmObjectImpl> mRepresentation = null;

    @objid ("316c75dc-5d84-492d-9e6c-be2eb266927e")
     SmObjectImpl mRepresentedFeature;

    @objid ("ce6544af-c8cb-48ab-8d69-821bc369a579")
    public BindableInstanceData(BindableInstanceSmClass smClass) {
        super(smClass);
    }

}
