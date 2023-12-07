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

package org.modelio.metamodel.impl.impact;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("f1080505-52a8-4f93-a180-fddc182cfd72")
public class ImpactLinkData extends ModelElementData {
    @objid ("b3de42f9-dff0-4e21-91c5-63039fb06fa2")
    SmObjectImpl mDependsOn;

    @objid ("e1015643-485b-46c8-ae6f-07dd756beb0f")
    SmObjectImpl mImpacted;

    @objid ("ed33a59b-a13c-465a-ab84-af64679267e9")
    List<SmObjectImpl> mCauses = null;

    @objid ("2a0ce801-cad1-44ae-b72e-2241004958fe")
    SmObjectImpl mOwner;

    @objid ("4cc716ce-d071-44e0-a43f-5440729af629")
    public  ImpactLinkData(ImpactLinkSmClass smClass) {
        super(smClass);
    }

}
