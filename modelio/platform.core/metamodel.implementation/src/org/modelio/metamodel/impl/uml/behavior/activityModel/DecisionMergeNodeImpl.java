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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.behavior.activityModel;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.activityModel.DecisionMergeNodeData;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionNodeKind;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("002ff30a-c4bf-1fd8-97fe-001ec947cd2a")
public class DecisionMergeNodeImpl extends ControlNodeImpl implements DecisionMergeNode {
    @objid ("fc766a14-4667-4bb3-a4a4-3c34f331babe")
    @Override
    public DecisionNodeKind getDecisionKind() {
        return (DecisionNodeKind) getAttVal(((DecisionMergeNodeSmClass)getClassOf()).getDecisionKindAtt());
    }

    @objid ("7b9a63c3-0bf6-4216-856b-60876310fe93")
    @Override
    public void setDecisionKind(DecisionNodeKind value) {
        setAttVal(((DecisionMergeNodeSmClass)getClassOf()).getDecisionKindAtt(), value);
    }

    @objid ("ff9cd877-2d52-43f1-83b1-284c8cab1033")
    @Override
    public String getDecisionInputBehavior() {
        return (String) getAttVal(((DecisionMergeNodeSmClass)getClassOf()).getDecisionInputBehaviorAtt());
    }

    @objid ("05528ef2-1ae7-4ed5-8e55-80b235245801")
    @Override
    public void setDecisionInputBehavior(String value) {
        setAttVal(((DecisionMergeNodeSmClass)getClassOf()).getDecisionInputBehaviorAtt(), value);
    }

    @objid ("d7d45e3f-f45f-408b-9be5-389251a02342")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("4e763dbf-3f89-489f-b8f1-d9c481776fe1")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("3d001163-2dad-411a-8b3b-a027cfec5465")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitDecisionMergeNode(this);
    }

}
