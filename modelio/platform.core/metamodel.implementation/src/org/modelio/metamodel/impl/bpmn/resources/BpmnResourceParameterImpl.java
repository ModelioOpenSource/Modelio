/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.metamodel.impl.bpmn.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceParameterData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000a1464-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnResourceParameterImpl extends BpmnBaseElementImpl implements BpmnResourceParameter {
    @objid ("cf8369ff-175a-4b30-be5f-0e882e012005")
    @Override
    public boolean isIsRequired() {
        return (Boolean) getAttVal(((BpmnResourceParameterSmClass)getClassOf()).getIsRequiredAtt());
    }

    @objid ("81bbaf84-9627-4903-b3e5-72674b34f15c")
    @Override
    public void setIsRequired(boolean value) {
        setAttVal(((BpmnResourceParameterSmClass)getClassOf()).getIsRequiredAtt(), value);
    }

    @objid ("6ab6127a-9331-4d4c-804b-33b1b3d7d40c")
    @Override
    public BpmnResource getResource() {
        Object obj = getDepVal(((BpmnResourceParameterSmClass)getClassOf()).getResourceDep());
        return (obj instanceof BpmnResource)? (BpmnResource)obj : null;
    }

    @objid ("99e4452b-2aa2-47e3-a384-338e2be8b7dd")
    @Override
    public void setResource(BpmnResource value) {
        appendDepVal(((BpmnResourceParameterSmClass)getClassOf()).getResourceDep(), (SmObjectImpl)value);
    }

    @objid ("d40e045e-585b-4ea1-a770-4fdb24f23fd0")
    @Override
    public BpmnItemDefinition getType() {
        Object obj = getDepVal(((BpmnResourceParameterSmClass)getClassOf()).getTypeDep());
        return (obj instanceof BpmnItemDefinition)? (BpmnItemDefinition)obj : null;
    }

    @objid ("5eb213af-cf68-4e58-9013-a86c155748f9")
    @Override
    public void setType(BpmnItemDefinition value) {
        appendDepVal(((BpmnResourceParameterSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("1d83ae29-e109-4246-857f-ba5f9e771ebc")
    @Override
    public EList<BpmnResourceParameterBinding> getParameterBindingRefs() {
        return new SmList<>(this, ((BpmnResourceParameterSmClass)getClassOf()).getParameterBindingRefsDep());
    }

    @objid ("55c3beab-c161-4bfd-a5f0-59c1539bd2d5")
    @Override
    public <T extends BpmnResourceParameterBinding> List<T> getParameterBindingRefs(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnResourceParameterBinding element : getParameterBindingRefs()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("f6285e82-0866-4b04-ad65-b7bb03a66c6f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Resource
        obj = (SmObjectImpl)this.getDepVal(((BpmnResourceParameterSmClass)getClassOf()).getResourceDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("abfb44cb-d77e-4094-93b7-8c11ae693e94")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Resource
        dep = ((BpmnResourceParameterSmClass)getClassOf()).getResourceDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("4487589d-689b-4289-984e-7456fc525a7e")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnResourceParameter(this);
    }

}
