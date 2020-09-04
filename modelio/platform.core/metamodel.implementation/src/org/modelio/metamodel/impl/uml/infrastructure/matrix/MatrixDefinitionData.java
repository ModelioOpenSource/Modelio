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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.matrix;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("efa02e20-bfde-4080-8f8e-4890ce002c4f")
public class MatrixDefinitionData extends ModelElementData {
    @objid ("9ffdd8a2-8305-4e41-9d95-f860c973631e")
     SmObjectImpl mLinesDefinition;

    @objid ("d1ab8e20-9c51-4709-a5b4-059fa1c48047")
     SmObjectImpl mColumnsDefinition;

    @objid ("7e403f77-b6bd-4d86-b387-f2e10d293062")
     SmObjectImpl mValuesDefinition;

    @objid ("a7478862-7e31-4d83-8cc1-5ca4bc40384a")
     SmObjectImpl mDepthDefinition;

    @objid ("f7f74689-cfaf-42a1-a9c4-47847216c7f6")
     SmObjectImpl mOwner;

    @objid ("f4b86166-3b73-4cb8-9d19-84ca643ab041")
    public MatrixDefinitionData(MatrixDefinitionSmClass smClass) {
        super(smClass);
    }

}
