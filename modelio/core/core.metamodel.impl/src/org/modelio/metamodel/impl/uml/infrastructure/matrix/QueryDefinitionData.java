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

package org.modelio.metamodel.impl.uml.infrastructure.matrix;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.ElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("8fe79750-6ab9-4493-b4ef-2e199f7d4dea")
public class QueryDefinitionData extends ElementData {
    @objid ("e4fe2980-50a9-4800-985f-8b8d29395eb2")
    Object mUsingAdditions = true;

    @objid ("163e6466-952e-49e9-81d3-a755b20386f2")
    List<SmObjectImpl> mAdded = null;

    @objid ("c529643c-52a8-492b-924f-a5e7f7fa2922")
    SmObjectImpl mProcessor;

    @objid ("4b897b33-f26e-406e-ba89-b96ce747ad72")
    SmObjectImpl mParameters;

    @objid ("6cc87115-b424-4f31-b2ac-cef46d85af5b")
    SmObjectImpl mOwnerAsLine;

    @objid ("d38ca28f-c78a-4aac-a423-c38eed3f15f4")
    SmObjectImpl mOwnerAsCol;

    @objid ("3c98d2d8-a721-479c-aca2-ae07040caf95")
    SmObjectImpl mOwnerAsDepth;

    @objid ("ec845a9e-0acc-46bc-a4e7-511a71f898e4")
    public  QueryDefinitionData(QueryDefinitionSmClass smClass) {
        super(smClass);
    }

}
