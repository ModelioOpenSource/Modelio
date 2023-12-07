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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00907a4a-c4be-1fd8-97fe-001ec947cd2a")
public class TaggedValueData extends ModelElementData {
    @objid ("b073a771-a7f2-430f-8bf7-9134b6cad9ac")
    List<SmObjectImpl> mActual = null;

    @objid ("83bfe656-71b0-4a87-9fe9-6a96fdcd4c28")
    SmObjectImpl mQualifier;

    @objid ("2664affd-4265-42ff-bfc1-399a71340a43")
    SmObjectImpl mDefinition;

    @objid ("35dc1058-8a3b-46cc-9d59-b16d34ea14d3")
    SmObjectImpl mAnnoted;

    @objid ("27e242b9-41c4-414c-a2d0-aed49cbd2d1b")
    public  TaggedValueData(TaggedValueSmClass smClass) {
        super(smClass);
    }

}
