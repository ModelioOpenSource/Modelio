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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00290914-c4bf-1fd8-97fe-001ec947cd2a")
public abstract class ActivityNodeData extends UmlModelElementData {
    @objid ("f61128ce-7f01-477a-89f0-76329e0f9c6f")
    SmObjectImpl mOwner;

    @objid ("7eefb430-e5e0-48d2-80f5-aa3fee36cff7")
    SmObjectImpl mOwnerPartition;

    @objid ("7bbb49ed-823a-4ece-a4ee-e80d65c67a6c")
    List<SmObjectImpl> mIncoming = null;

    @objid ("b3eaba17-6dc8-4130-b3c6-935d495e5772")
    SmObjectImpl mOwnerClause;

    @objid ("7d21c9cb-5060-44c1-b3a7-c8db75d02c98")
    SmObjectImpl mOwnerNode;

    @objid ("8b829f16-3f3f-4daf-a0ff-95c23fe85e79")
    List<SmObjectImpl> mOutgoing = null;

    @objid ("d848b4f2-7b06-4983-a1c3-07083e003947")
    public  ActivityNodeData(ActivityNodeSmClass smClass) {
        super(smClass);
    }

}
