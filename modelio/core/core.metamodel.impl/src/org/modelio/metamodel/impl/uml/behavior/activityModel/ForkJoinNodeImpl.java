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

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0033e794-c4bf-1fd8-97fe-001ec947cd2a")
public class ForkJoinNodeImpl extends ControlNodeImpl implements ForkJoinNode {
    @objid ("795d5abe-e9a0-4e7b-a807-fc3f76211062")
    @Override
    public boolean isIsCombineDuplicate() {
        return (Boolean) getAttVal(((ForkJoinNodeSmClass)getClassOf()).getIsCombineDuplicateAtt());
    }

    @objid ("4ba26ae2-650b-4e29-83f8-8f531fdb3948")
    @Override
    public void setIsCombineDuplicate(boolean value) {
        setAttVal(((ForkJoinNodeSmClass)getClassOf()).getIsCombineDuplicateAtt(), value);
    }

    @objid ("23a2f397-f9f3-4957-911d-2fb2c85a139b")
    @Override
    public String getJoinSpec() {
        return (String) getAttVal(((ForkJoinNodeSmClass)getClassOf()).getJoinSpecAtt());
    }

    @objid ("77ad92ac-a2c0-4d0d-ae8f-857e78870028")
    @Override
    public void setJoinSpec(String value) {
        setAttVal(((ForkJoinNodeSmClass)getClassOf()).getJoinSpecAtt(), value);
    }

    @objid ("146732a4-130d-41b0-9783-f823d44760dc")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("859b3c44-cc67-4b25-ae97-47ca8f8ae967")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("7957efeb-a132-4c1f-ae04-fff1fc25315c")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitForkJoinNode(this);
    }

}
