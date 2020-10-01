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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.impact;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("a9897a98-88cc-414d-9b1f-e2abe48375ab")
public class ImpactModelData extends ModelElementData {
    @objid ("94510771-85dd-4070-b908-83e753e0de23")
     SmObjectImpl mProject;

    @objid ("af34fa57-143e-4a3a-9cb8-ce362451c7a2")
     List<SmObjectImpl> mOwnedLinks = null;

    @objid ("f3e52871-68ed-42cd-88f5-1c9a255d2346")
    public ImpactModelData(ImpactModelSmClass smClass) {
        super(smClass);
    }

}
