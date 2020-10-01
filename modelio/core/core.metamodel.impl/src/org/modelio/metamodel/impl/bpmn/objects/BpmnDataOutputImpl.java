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
package org.modelio.metamodel.impl.bpmn.objects;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataOutputData;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0005e9a2-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnDataOutputImpl extends BpmnItemAwareElementImpl implements BpmnDataOutput {
    @objid ("abe2c209-54ab-4e6a-86a3-0289787b9af1")
    @Override
    public boolean isIsCollection() {
        return (Boolean) getAttVal(((BpmnDataOutputSmClass)getClassOf()).getIsCollectionAtt());
    }

    @objid ("a4149f3c-1e95-4b1b-b849-b6dd0b54a838")
    @Override
    public void setIsCollection(boolean value) {
        setAttVal(((BpmnDataOutputSmClass)getClassOf()).getIsCollectionAtt(), value);
    }

    @objid ("11df23bb-3fa3-415f-b1eb-ce627bda3758")
    @Override
    public BpmnActivity getOwnerActivity() {
        Object obj = getDepVal(((BpmnDataOutputSmClass)getClassOf()).getOwnerActivityDep());
        return (obj instanceof BpmnActivity)? (BpmnActivity)obj : null;
    }

    @objid ("da0a75f8-2510-4fc5-a126-df9441335a41")
    @Override
    public void setOwnerActivity(BpmnActivity value) {
        appendDepVal(((BpmnDataOutputSmClass)getClassOf()).getOwnerActivityDep(), (SmObjectImpl)value);
    }

    @objid ("885c02e9-eddb-4f74-a8fc-d1621ca96a19")
    @Override
    public BpmnCatchEvent getCatched() {
        Object obj = getDepVal(((BpmnDataOutputSmClass)getClassOf()).getCatchedDep());
        return (obj instanceof BpmnCatchEvent)? (BpmnCatchEvent)obj : null;
    }

    @objid ("48871aa1-c42c-4e7a-ba2b-a449d6f910db")
    @Override
    public void setCatched(BpmnCatchEvent value) {
        appendDepVal(((BpmnDataOutputSmClass)getClassOf()).getCatchedDep(), (SmObjectImpl)value);
    }

    @objid ("413b18d3-f5ea-41b9-b935-97917b8f4809")
    @Override
    public BpmnMultiInstanceLoopCharacteristics getOwnerLoopCharacteristics() {
        Object obj = getDepVal(((BpmnDataOutputSmClass)getClassOf()).getOwnerLoopCharacteristicsDep());
        return (obj instanceof BpmnMultiInstanceLoopCharacteristics)? (BpmnMultiInstanceLoopCharacteristics)obj : null;
    }

    @objid ("ae9ce54f-62d0-4e6e-8267-36607c19ee94")
    @Override
    public void setOwnerLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics value) {
        appendDepVal(((BpmnDataOutputSmClass)getClassOf()).getOwnerLoopCharacteristicsDep(), (SmObjectImpl)value);
    }

    @objid ("7e3a4a34-46d1-452a-b3e7-b1c58f24d770")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerActivity
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataOutputSmClass)getClassOf()).getOwnerActivityDep());
        if (obj != null)
          return obj;
        // Catched
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataOutputSmClass)getClassOf()).getCatchedDep());
        if (obj != null)
          return obj;
        // OwnerLoopCharacteristics
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataOutputSmClass)getClassOf()).getOwnerLoopCharacteristicsDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("ebdd488c-1e17-494c-bbfb-a80d84dcae2d")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerActivity
        dep = ((BpmnDataOutputSmClass)getClassOf()).getOwnerActivityDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Catched
        dep = ((BpmnDataOutputSmClass)getClassOf()).getCatchedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerLoopCharacteristics
        dep = ((BpmnDataOutputSmClass)getClassOf()).getOwnerLoopCharacteristicsDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("58ba7b16-f18b-48f4-94eb-46ddaccb564d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnDataOutput(this);
    }

}
