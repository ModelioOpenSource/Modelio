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

package org.modelio.metamodel.impl.uml.infrastructure.matrix;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("efa02e20-bfde-4080-8f8e-4890ce002c4f")
public class MatrixDefinitionData extends ModelElementData {
    @objid ("3386959b-ab23-4f35-9138-db75b2724a1d")
    SmObjectImpl mLinesDefinition;

    @objid ("27d2f7d9-1aca-4492-aed4-694d952ad96c")
    SmObjectImpl mColumnsDefinition;

    @objid ("5ba6da60-aebf-47cc-bad6-413d2b0ee974")
    SmObjectImpl mValuesDefinition;

    @objid ("4ad8b72e-f9ef-40e2-8535-0ff5b4702457")
    SmObjectImpl mDepthDefinition;

    @objid ("223a9dbb-9439-4fee-b405-cc3d392998f0")
    SmObjectImpl mOwner;

    @objid ("dabb2f2b-9b7b-4285-8834-742d39f6c158")
    public  MatrixDefinitionData(MatrixDefinitionSmClass smClass) {
        super(smClass);
    }

}
