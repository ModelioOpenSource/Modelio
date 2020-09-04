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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.matrix;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("efa02e20-bfde-4080-8f8e-4890ce002c4f")
public class MatrixDefinitionData extends ModelElementData {
    @objid ("7ba05b00-e804-4486-8d75-68656c4267eb")
     SmObjectImpl mLinesDefinition;

    @objid ("6969bc7c-79e3-4e41-83d6-6d98a649ee20")
     SmObjectImpl mColumnsDefinition;

    @objid ("87c92316-fb03-412e-a38f-c8f93d6873d5")
     SmObjectImpl mValuesDefinition;

    @objid ("66f2436e-529b-4fa7-a1cb-c1a573f1fb9b")
     SmObjectImpl mDepthDefinition;

    @objid ("41c50394-1f55-4e4e-8e09-1a0e3983e4d1")
     SmObjectImpl mOwner;

    @objid ("a9827f8b-1de1-45f5-ac96-172925cffb55")
    public MatrixDefinitionData(MatrixDefinitionSmClass smClass) {
        super(smClass);
    }

}
