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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataAssociationData;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementImpl;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0004abfa-c4c0-1fd8-97fe-001ec947cd2a")
public class BpmnDataAssociationImpl extends BpmnBaseElementImpl implements BpmnDataAssociation {
    @objid ("7b61d468-a32a-437b-a02f-ef7c21228a18")
    @Override
    public String getAssignment() {
        return (String) getAttVal(((BpmnDataAssociationSmClass)getClassOf()).getAssignmentAtt());
    }

    @objid ("997fbbad-813b-44c3-ae17-db200701c234")
    @Override
    public void setAssignment(String value) {
        setAttVal(((BpmnDataAssociationSmClass)getClassOf()).getAssignmentAtt(), value);
    }

    @objid ("9f3f8e2f-6894-4a8f-b23d-e4796e0a04ef")
    @Override
    public String getTransfomation() {
        return (String) getAttVal(((BpmnDataAssociationSmClass)getClassOf()).getTransfomationAtt());
    }

    @objid ("fd51250c-1f74-426d-bd2c-74dee650a00f")
    @Override
    public void setTransfomation(String value) {
        setAttVal(((BpmnDataAssociationSmClass)getClassOf()).getTransfomationAtt(), value);
    }

    @objid ("f101c3f4-9120-4b45-8ede-5cff4dad464c")
    @Override
    public String getLanguage() {
        return (String) getAttVal(((BpmnDataAssociationSmClass)getClassOf()).getLanguageAtt());
    }

    @objid ("8fbe82c9-3ba4-485b-9af6-91cae73f4d9c")
    @Override
    public void setLanguage(String value) {
        setAttVal(((BpmnDataAssociationSmClass)getClassOf()).getLanguageAtt(), value);
    }

    @objid ("637a093e-acfc-4a3a-b0f4-7deef8631b07")
    @Override
    public EList<BpmnItemAwareElement> getSourceRef() {
        return new SmList<>(this, ((BpmnDataAssociationSmClass)getClassOf()).getSourceRefDep());
    }

    @objid ("800cead5-e651-48af-a11e-a0b80a2ad59c")
    @Override
    public <T extends BpmnItemAwareElement> List<T> getSourceRef(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnItemAwareElement element : getSourceRef()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("2992cff7-bda5-4acc-a606-599fd9c0c606")
    @Override
    public BpmnItemAwareElement getTargetRef() {
        Object obj = getDepVal(((BpmnDataAssociationSmClass)getClassOf()).getTargetRefDep());
        return (obj instanceof BpmnItemAwareElement)? (BpmnItemAwareElement)obj : null;
    }

    @objid ("7611609c-6e7e-45b5-8bc6-7dc03aa025b2")
    @Override
    public void setTargetRef(BpmnItemAwareElement value) {
        appendDepVal(((BpmnDataAssociationSmClass)getClassOf()).getTargetRefDep(), (SmObjectImpl)value);
    }

    @objid ("8ef40e56-2336-4062-bcd4-6f6f060ab07f")
    @Override
    public BpmnActivity getEndingActivity() {
        Object obj = getDepVal(((BpmnDataAssociationSmClass)getClassOf()).getEndingActivityDep());
        return (obj instanceof BpmnActivity)? (BpmnActivity)obj : null;
    }

    @objid ("93ee22d3-afef-4a30-91f1-1a8fdf727999")
    @Override
    public void setEndingActivity(BpmnActivity value) {
        appendDepVal(((BpmnDataAssociationSmClass)getClassOf()).getEndingActivityDep(), (SmObjectImpl)value);
    }

    @objid ("f367bf29-0024-453e-9d6a-6fabd789b938")
    @Override
    public BpmnActivity getStartingActivity() {
        Object obj = getDepVal(((BpmnDataAssociationSmClass)getClassOf()).getStartingActivityDep());
        return (obj instanceof BpmnActivity)? (BpmnActivity)obj : null;
    }

    @objid ("4e6adc3f-8609-4580-a0a9-827dd637b1c6")
    @Override
    public void setStartingActivity(BpmnActivity value) {
        appendDepVal(((BpmnDataAssociationSmClass)getClassOf()).getStartingActivityDep(), (SmObjectImpl)value);
    }

    @objid ("952216ab-0075-45da-9986-94a6694c3f44")
    @Override
    public BpmnThrowEvent getStartingEvent() {
        Object obj = getDepVal(((BpmnDataAssociationSmClass)getClassOf()).getStartingEventDep());
        return (obj instanceof BpmnThrowEvent)? (BpmnThrowEvent)obj : null;
    }

    @objid ("5716a68e-d94f-4fab-b9df-74751f9d3784")
    @Override
    public void setStartingEvent(BpmnThrowEvent value) {
        appendDepVal(((BpmnDataAssociationSmClass)getClassOf()).getStartingEventDep(), (SmObjectImpl)value);
    }

    @objid ("35dfa103-53b2-41f9-ae7a-31fd27d2287b")
    @Override
    public EList<BpmnSequenceFlowDataAssociation> getVisualShortCut() {
        return new SmList<>(this, ((BpmnDataAssociationSmClass)getClassOf()).getVisualShortCutDep());
    }

    @objid ("c10e03fc-6cbd-4e25-9996-4cdffa9d03f7")
    @Override
    public <T extends BpmnSequenceFlowDataAssociation> List<T> getVisualShortCut(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final BpmnSequenceFlowDataAssociation element : getVisualShortCut()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("b7153a2d-a083-452e-bea8-db3d727e2a2c")
    @Override
    public BpmnCatchEvent getEndingEvent() {
        Object obj = getDepVal(((BpmnDataAssociationSmClass)getClassOf()).getEndingEventDep());
        return (obj instanceof BpmnCatchEvent)? (BpmnCatchEvent)obj : null;
    }

    @objid ("451adca0-ecd1-4001-a93d-f8c65da79dad")
    @Override
    public void setEndingEvent(BpmnCatchEvent value) {
        appendDepVal(((BpmnDataAssociationSmClass)getClassOf()).getEndingEventDep(), (SmObjectImpl)value);
    }

    @objid ("4c469634-6aac-4655-83bd-9924d3b8e605")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // EndingActivity
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataAssociationSmClass)getClassOf()).getEndingActivityDep());
        if (obj != null)
          return obj;
        // StartingActivity
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataAssociationSmClass)getClassOf()).getStartingActivityDep());
        if (obj != null)
          return obj;
        // StartingEvent
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataAssociationSmClass)getClassOf()).getStartingEventDep());
        if (obj != null)
          return obj;
        // EndingEvent
        obj = (SmObjectImpl)this.getDepVal(((BpmnDataAssociationSmClass)getClassOf()).getEndingEventDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("b6ac02f5-f965-47dc-8ec8-7176949433ae")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // EndingActivity
        dep = ((BpmnDataAssociationSmClass)getClassOf()).getEndingActivityDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // StartingActivity
        dep = ((BpmnDataAssociationSmClass)getClassOf()).getStartingActivityDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // StartingEvent
        dep = ((BpmnDataAssociationSmClass)getClassOf()).getStartingEventDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // EndingEvent
        dep = ((BpmnDataAssociationSmClass)getClassOf()).getEndingEventDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("7ad7a5ec-0335-4c92-9da3-2f6d8617c4ff")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnDataAssociation(this);
    }

}
