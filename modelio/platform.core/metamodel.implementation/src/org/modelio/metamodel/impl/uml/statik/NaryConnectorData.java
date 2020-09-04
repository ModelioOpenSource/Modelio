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

@objid ("0006480c-c4bf-1fd8-97fe-001ec947cd2a")
public class NaryConnectorData extends NaryLinkData {
    @objid ("8a3d4fb9-c0d7-42d4-a7d3-0c7cbc6ac9f2")
     List<SmObjectImpl> mRepresentation = null;

    @objid ("69e48de1-9ace-467c-a0d8-047602e67e6d")
     SmObjectImpl mRepresentedFeature;

    @objid ("6657df1b-3352-4645-b14a-632f1e492689")
    public NaryConnectorData(NaryConnectorSmClass smClass) {
        super(smClass);
    }

}
