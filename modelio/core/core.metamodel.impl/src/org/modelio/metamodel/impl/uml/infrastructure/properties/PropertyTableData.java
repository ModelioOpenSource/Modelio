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

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0069a7f8-ec87-1098-b22e-001ec947cd2a")
public class PropertyTableData extends ElementData {
    @objid ("46bfca5d-baa0-470a-b157-1fa12123bdb5")
    Object mName = "";

    @objid ("fbddf057-a420-4496-b68d-f3960eef521c")
    Object mContent = "";

    @objid ("9845a3b8-ff24-4452-b903-7e32b75d1d1d")
    SmObjectImpl mOwnerValDef;

    @objid ("b82c814f-5e14-42f5-8285-d65df8cce5e2")
    SmObjectImpl mOwnerQuery;

    @objid ("d19747cc-66c4-4762-a4ee-0aa927aef5e9")
    SmObjectImpl mOwner;

    @objid ("5f23d958-4579-4a19-938b-3969132d416a")
    public  PropertyTableData(PropertyTableSmClass smClass) {
        super(smClass);
    }

}
