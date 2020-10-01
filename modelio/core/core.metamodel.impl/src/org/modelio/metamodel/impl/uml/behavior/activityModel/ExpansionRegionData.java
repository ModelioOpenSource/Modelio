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
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionKind;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00322346-c4bf-1fd8-97fe-001ec947cd2a")
public class ExpansionRegionData extends StructuredActivityNodeData {
    @objid ("df4e68e8-61e8-4909-a4af-812bfc2c6b22")
     Object mMode = ExpansionKind.ITERATIVE;

    @objid ("e94e337d-45ed-464c-a557-287e1bcaa0c9")
     List<SmObjectImpl> mOutputElement = null;

    @objid ("742d9909-d315-4b81-be85-6b3c0ee3010d")
     List<SmObjectImpl> mInputElement = null;

    @objid ("8a85034e-6edb-430a-a92e-4d50ccacffec")
    public ExpansionRegionData(ExpansionRegionSmClass smClass) {
        super(smClass);
    }

}
