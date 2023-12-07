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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("8fe79750-6ab9-4493-b4ef-2e199f7d4dea")
public class QueryDefinitionData extends ElementData {
    @objid ("1289b33d-0dc4-4618-8305-9a21b915461f")
    Object mUsingAdditions = true;

    @objid ("e4bd1fcf-6924-4e89-98c4-1934496e92a9")
    List<SmObjectImpl> mAdded = null;

    @objid ("7c67419f-a181-4f4b-a1ba-3f6aa0b535f3")
    SmObjectImpl mProcessor;

    @objid ("38cf9069-7915-4f62-b86e-c8db999a5f1d")
    SmObjectImpl mParameters;

    @objid ("65bc252f-bdb5-4f82-af86-cc96b3f5ca34")
    SmObjectImpl mOwnerAsLine;

    @objid ("803c3f19-2fe5-405b-80bc-f85953c38cc9")
    SmObjectImpl mOwnerAsCol;

    @objid ("f8a0de5a-b033-4654-92bf-c45ab7fd64ef")
    SmObjectImpl mOwnerAsDepth;

    @objid ("bfc8b527-234c-4f42-8147-3631affa06c7")
    public  QueryDefinitionData(QueryDefinitionSmClass smClass) {
        super(smClass);
    }

}
