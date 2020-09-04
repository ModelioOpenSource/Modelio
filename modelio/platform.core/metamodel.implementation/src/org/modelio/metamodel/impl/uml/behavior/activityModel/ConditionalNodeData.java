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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("002db2e8-c4bf-1fd8-97fe-001ec947cd2a")
public class ConditionalNodeData extends StructuredActivityNodeData {
    @objid ("16529b7b-5b6d-45a3-a1b2-999d0bb957ea")
     Object mIsDeterminate = false;

    @objid ("6874c15f-1a2a-4899-9976-4e11d097e7c1")
     Object mIsAssured = false;

    @objid ("e6b60d68-fad7-4ab5-a3d6-454b5ae8dde7")
     List<SmObjectImpl> mOwnedClause = null;

    @objid ("93e3ca3e-b2db-4847-bb8e-68a03ea808cc")
    public ConditionalNodeData(ConditionalNodeSmClass smClass) {
        super(smClass);
    }

}
