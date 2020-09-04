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
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0004d404-c4bf-1fd8-97fe-001ec947cd2a")
public class CollaborationUseData extends UmlModelElementData {
    @objid ("3a104ca7-cd81-49cc-ac59-2fd1c3cab501")
     SmObjectImpl mType;

    @objid ("66123fc2-56b2-4471-9744-07de91cf2f3b")
     SmObjectImpl mNRepresented;

    @objid ("6e8e62a8-c1b8-41d6-9af6-d97a2b808263")
     SmObjectImpl mORepresented;

    @objid ("f6ac34ce-993a-43a4-b398-4da994371e8b")
     List<SmObjectImpl> mRoleBinding = null;

    @objid ("db2673fd-f3fe-434e-bce4-279ecd739aa4")
    public CollaborationUseData(CollaborationUseSmClass smClass) {
        super(smClass);
    }

}
