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
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExpansionNodeData;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00314c5a-c4bf-1fd8-97fe-001ec947cd2a")
public class ExpansionNodeImpl extends ObjectNodeImpl implements ExpansionNode {
    @objid ("8e3faf68-2fe2-4b6d-912e-c5d487dc494c")
    @Override
    public ExpansionRegion getRegionAsOutput() {
        Object obj = getDepVal(((ExpansionNodeSmClass)getClassOf()).getRegionAsOutputDep());
        return (obj instanceof ExpansionRegion)? (ExpansionRegion)obj : null;
    }

    @objid ("ecfba8bf-06f7-4cf8-aad9-11a6fc9f3eec")
    @Override
    public void setRegionAsOutput(ExpansionRegion value) {
        appendDepVal(((ExpansionNodeSmClass)getClassOf()).getRegionAsOutputDep(), (SmObjectImpl)value);
    }

    @objid ("0f876df2-8acf-4832-a5b0-b0192d9bb321")
    @Override
    public ExpansionRegion getRegionAsInput() {
        Object obj = getDepVal(((ExpansionNodeSmClass)getClassOf()).getRegionAsInputDep());
        return (obj instanceof ExpansionRegion)? (ExpansionRegion)obj : null;
    }

    @objid ("a561529c-a746-4f48-a65f-7d018715f183")
    @Override
    public void setRegionAsInput(ExpansionRegion value) {
        appendDepVal(((ExpansionNodeSmClass)getClassOf()).getRegionAsInputDep(), (SmObjectImpl)value);
    }

    @objid ("a57a2960-4a02-42a7-ac02-ad8d761678d1")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // RegionAsOutput
        obj = (SmObjectImpl)this.getDepVal(((ExpansionNodeSmClass)getClassOf()).getRegionAsOutputDep());
        if (obj != null)
          return obj;
        // RegionAsInput
        obj = (SmObjectImpl)this.getDepVal(((ExpansionNodeSmClass)getClassOf()).getRegionAsInputDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("734e7a0f-2332-46a9-85b5-08b63d907e24")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // RegionAsOutput
        dep = ((ExpansionNodeSmClass)getClassOf()).getRegionAsOutputDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // RegionAsInput
        dep = ((ExpansionNodeSmClass)getClassOf()).getRegionAsInputDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("fc622c00-dc42-4a63-b9f2-3739080dd0d8")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitExpansionNode(this);
    }

}
