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
package org.modelio.metamodel.impl.bpmn.processCollaboration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.bpmnService.BpmnEndPoint;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnParticipantData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0075ac2e-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnParticipantImpl extends BpmnBaseElementImpl implements BpmnParticipant {
    @objid ("6c4ec23f-a37b-4cac-a9b9-3d93d4ed03a6")
    @Override
    public boolean isLocal() {
        BpmnProcess process = getProcess();
        BpmnCollaboration collab = getContainer();
        return process != null && collab != null && Objects.equals(collab.getDefinedProcess(), process);
    }

    @objid ("5395b26a-c8b1-4603-8068-a406ec9afb06")
    @Override
    public int getMultiplicityMin() {
        return (Integer) getAttVal(((BpmnParticipantSmClass)getClassOf()).getMultiplicityMinAtt());
    }

    @objid ("6a0d2551-797f-45ab-94f4-23713b26ce68")
    @Override
    public void setMultiplicityMin(int value) {
        setAttVal(((BpmnParticipantSmClass)getClassOf()).getMultiplicityMinAtt(), value);
    }

    @objid ("d0f65cef-6953-4ee7-bec8-daf7d8d9b84d")
    @Override
    public int getMultiplicityMax() {
        return (Integer) getAttVal(((BpmnParticipantSmClass)getClassOf()).getMultiplicityMaxAtt());
    }

    @objid ("e101e5f4-81f9-4384-a7ba-653641e351b9")
    @Override
    public void setMultiplicityMax(int value) {
        setAttVal(((BpmnParticipantSmClass)getClassOf()).getMultiplicityMaxAtt(), value);
    }

    @objid ("cdcd32b2-71ef-41aa-ac6f-a260af10baa9")
    @Override
    public BpmnProcess getProcess() {
        Object obj = getDepVal(((BpmnParticipantSmClass)getClassOf()).getProcessDep());
        return (obj instanceof BpmnProcess)? (BpmnProcess)obj : null;
    }

    @objid ("97fc98db-d918-432c-b30f-f4a3f72e0e20")
    @Override
    public void setProcess(BpmnProcess value) {
        appendDepVal(((BpmnParticipantSmClass)getClassOf()).getProcessDep(), (SmObjectImpl)value);
    }

    @objid ("940b76a0-0b6b-4c8e-9046-5d11984780a6")
    @Override
    public BpmnCollaboration getContainer() {
        Object obj = getDepVal(((BpmnParticipantSmClass)getClassOf()).getContainerDep());
        return (obj instanceof BpmnCollaboration)? (BpmnCollaboration)obj : null;
    }

    @objid ("53eb965d-0d28-4696-aec5-fcfce9bdaca5")
    @Override
    public void setContainer(BpmnCollaboration value) {
        appendDepVal(((BpmnParticipantSmClass)getClassOf()).getContainerDep(), (SmObjectImpl)value);
    }

    @objid ("0ce57be2-a8f7-4a4d-91aa-01e88fd7a3f6")
    @Override
    public EList<BpmnEndPoint> getEndPointRefs() {
        return new SmList<>(this, ((BpmnParticipantSmClass)getClassOf()).getEndPointRefsDep());
    }

    @objid ("37b99166-3dd5-41fe-8d9a-49f0921c11bd")
    @Override
    public <T extends BpmnEndPoint> List<T> getEndPointRefs(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnEndPoint element : getEndPointRefs()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("1a410e19-491a-4fc9-a71d-b9228dbe76de")
    @Override
    public EList<BpmnInterface> getInterfaceRefs() {
        return new SmList<>(this, ((BpmnParticipantSmClass)getClassOf()).getInterfaceRefsDep());
    }

    @objid ("3a441eb4-c5cb-46be-9150-bb6e1e91e169")
    @Override
    public <T extends BpmnInterface> List<T> getInterfaceRefs(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnInterface element : getInterfaceRefs()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8043a339-822a-4df8-8fad-a4018f6432f7")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Container
        obj = (SmObjectImpl)this.getDepVal(((BpmnParticipantSmClass)getClassOf()).getContainerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("35876fc9-defc-4d72-816b-a92668fa4163")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Container
        dep = ((BpmnParticipantSmClass)getClassOf()).getContainerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("c5b98308-d39d-4b22-a8dc-dfc2b08f89e3")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnParticipant(this);
    }

}
