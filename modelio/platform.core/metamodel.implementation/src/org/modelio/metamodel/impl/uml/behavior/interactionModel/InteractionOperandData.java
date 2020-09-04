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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("0047edde-c4bf-1fd8-97fe-001ec947cd2a")
public class InteractionOperandData extends InteractionFragmentData {
    @objid ("7b27af27-7316-45ae-b1e7-93388f5e4fb3")
     Object mGuard = "";

    @objid ("e7640fa6-bb27-4f25-9811-3d4879023346")
     Object mEndLineNumber = 0;

    @objid ("dbb81258-26fd-49c9-b9b0-b74f61ec5c85")
     List<SmObjectImpl> mFragment = null;

    @objid ("bfe810f5-aaf2-448e-b9f4-97426fdca1a7")
     SmObjectImpl mOwnerFragment;

    @objid ("f052ccfe-a11c-4cf3-a812-2b2acf548ed8")
    public InteractionOperandData(InteractionOperandSmClass smClass) {
        super(smClass);
    }

}
