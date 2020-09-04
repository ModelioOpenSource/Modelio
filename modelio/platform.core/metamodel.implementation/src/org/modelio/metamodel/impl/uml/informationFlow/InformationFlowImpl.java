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
package org.modelio.metamodel.impl.uml.informationFlow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("006399b2-c4bf-1fd8-97fe-001ec947cd2a")
public class InformationFlowImpl extends UmlModelElementImpl implements InformationFlow {
    @objid ("f3f4e7c9-9c01-498d-a80b-2d51ddf3d61d")
    @Override
    public NameSpace getOwner() {
        Object obj = getDepVal(((InformationFlowSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("04e745a1-7be9-4856-9f2a-43aa13aa7851")
    @Override
    public void setOwner(NameSpace value) {
        appendDepVal(((InformationFlowSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("f88fe02b-d3cd-4474-a370-9a6282710d06")
    @Override
    public EList<UmlModelElement> getInformationSource() {
        return new SmList<>(this, ((InformationFlowSmClass)getClassOf()).getInformationSourceDep());
    }

    @objid ("3516f741-caf7-4056-8d36-b88bad1ac992")
    @Override
    public <T extends UmlModelElement> List<T> getInformationSource(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final UmlModelElement element : getInformationSource()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("9c160718-a344-49c9-9b35-8f3e7131a5b7")
    @Override
    public EList<UmlModelElement> getInformationTarget() {
        return new SmList<>(this, ((InformationFlowSmClass)getClassOf()).getInformationTargetDep());
    }

    @objid ("dc3bdaee-2501-4c46-9e66-79767a17ce43")
    @Override
    public <T extends UmlModelElement> List<T> getInformationTarget(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final UmlModelElement element : getInformationTarget()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("3b7ad87e-05f0-441f-9f49-c2fd573987af")
    @Override
    public EList<ActivityEdge> getRealizingActivityEdge() {
        return new SmList<>(this, ((InformationFlowSmClass)getClassOf()).getRealizingActivityEdgeDep());
    }

    @objid ("224c0394-ffcc-44e2-accf-25b473ee0131")
    @Override
    public <T extends ActivityEdge> List<T> getRealizingActivityEdge(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ActivityEdge element : getRealizingActivityEdge()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("5ba73d1c-62da-4488-8cba-d915be5f4141")
    @Override
    public EList<CommunicationMessage> getRealizingCommunicationMessage() {
        return new SmList<>(this, ((InformationFlowSmClass)getClassOf()).getRealizingCommunicationMessageDep());
    }

    @objid ("d1e5ee01-04c1-405d-a0bc-91bfffc80ca3")
    @Override
    public <T extends CommunicationMessage> List<T> getRealizingCommunicationMessage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CommunicationMessage element : getRealizingCommunicationMessage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("01c2e323-369d-4a2f-9874-f21bef56c5b8")
    @Override
    public EList<StructuralFeature> getRealizingFeature() {
        return new SmList<>(this, ((InformationFlowSmClass)getClassOf()).getRealizingFeatureDep());
    }

    @objid ("196b9602-38cb-4a78-bc54-348d59f59175")
    @Override
    public <T extends StructuralFeature> List<T> getRealizingFeature(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final StructuralFeature element : getRealizingFeature()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("6461e2b9-f92f-4947-b7b8-2e620ec30262")
    @Override
    public EList<LinkEnd> getRealizingLink() {
        return new SmList<>(this, ((InformationFlowSmClass)getClassOf()).getRealizingLinkDep());
    }

    @objid ("a42e7c7d-7822-4b4a-b8e9-511953c7dbc8")
    @Override
    public <T extends LinkEnd> List<T> getRealizingLink(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final LinkEnd element : getRealizingLink()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("9ae471bc-ff4d-4fcb-9a3f-8ab16367ec16")
    @Override
    public EList<Message> getRealizingMessage() {
        return new SmList<>(this, ((InformationFlowSmClass)getClassOf()).getRealizingMessageDep());
    }

    @objid ("9c828c79-5d79-4b1b-b7df-2a8ef96b86f9")
    @Override
    public <T extends Message> List<T> getRealizingMessage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Message element : getRealizingMessage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("f9936b87-d25b-4fd1-a7a4-b1470e9fc6b8")
    @Override
    public EList<NaryLink> getRealizingNaryLink() {
        return new SmList<>(this, ((InformationFlowSmClass)getClassOf()).getRealizingNaryLinkDep());
    }

    @objid ("6ecade30-542f-42a4-a68a-b11bad427b7f")
    @Override
    public <T extends NaryLink> List<T> getRealizingNaryLink(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final NaryLink element : getRealizingNaryLink()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("fd4ccf2d-28c2-4fc3-ad27-eec514807cc9")
    @Override
    public EList<Classifier> getConveyed() {
        return new SmList<>(this, ((InformationFlowSmClass)getClassOf()).getConveyedDep());
    }

    @objid ("73a7d896-eb7c-482d-8e08-845c6f979407")
    @Override
    public <T extends Classifier> List<T> getConveyed(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Classifier element : getConveyed()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("c8848472-0416-44d6-9390-da4984cb7f09")
    @Override
    public AssociationEnd getChannel() {
        Object obj = getDepVal(((InformationFlowSmClass)getClassOf()).getChannelDep());
        return (obj instanceof AssociationEnd)? (AssociationEnd)obj : null;
    }

    @objid ("22121724-1a62-471d-8eb9-5f811568094e")
    @Override
    public void setChannel(AssociationEnd value) {
        appendDepVal(((InformationFlowSmClass)getClassOf()).getChannelDep(), (SmObjectImpl)value);
    }

    @objid ("7995356b-962f-4645-8f97-279cdb821b09")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((InformationFlowSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("d229aa4d-e79c-459e-8733-a41ed4633c1a")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((InformationFlowSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("accd48fc-a421-4dfa-8aa4-52fd69c0c5e5")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitInformationFlow(this);
    }

}
