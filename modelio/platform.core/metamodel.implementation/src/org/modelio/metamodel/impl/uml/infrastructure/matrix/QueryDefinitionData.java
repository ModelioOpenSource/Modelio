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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("8fe79750-6ab9-4493-b4ef-2e199f7d4dea")
public class QueryDefinitionData extends ElementData {
    @objid ("e7c0b67b-106d-4d7a-9bf9-c9fa597cb09d")
     Object mUsingAdditions = true;

    @objid ("25dfc423-ee42-4981-a8d9-fd2bb1bcc575")
     List<SmObjectImpl> mAdded = null;

    @objid ("d53cdad0-8184-4af1-aa82-a482da7a7ecf")
     SmObjectImpl mProcessor;

    @objid ("1c5a3327-c240-4458-8c88-100b936afeb5")
     SmObjectImpl mParameters;

    @objid ("0ac7a514-2c96-4c77-9960-1c031a2e7dbd")
     SmObjectImpl mOwnerAsLine;

    @objid ("4c44a1f2-b480-4ae4-ac71-bd2bbd03d1a9")
     SmObjectImpl mOwnerAsCol;

    @objid ("76507f4b-1f6a-4813-a505-87c0f416c4f0")
     SmObjectImpl mOwnerAsDepth;

    @objid ("f26533c4-0565-4b82-8c89-d58567a0bb7d")
    public QueryDefinitionData(QueryDefinitionSmClass smClass) {
        super(smClass);
    }

}
