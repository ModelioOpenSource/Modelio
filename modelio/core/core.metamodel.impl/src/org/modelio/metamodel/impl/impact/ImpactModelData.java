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

@objid ("a9897a98-88cc-414d-9b1f-e2abe48375ab")
public class ImpactModelData extends ModelElementData {
    @objid ("43cad766-709d-42b7-a8d9-a0a638acd227")
    SmObjectImpl mProject;

    @objid ("8f813951-734b-4336-b348-2fdcc5aeb2e2")
    List<SmObjectImpl> mOwnedLinks = null;

    @objid ("5b068493-9d0d-44ba-9e53-14657574a4ee")
    public  ImpactModelData(ImpactModelSmClass smClass) {
        super(smClass);
    }

}
