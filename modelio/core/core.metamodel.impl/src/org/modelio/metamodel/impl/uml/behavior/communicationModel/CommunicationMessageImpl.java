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

package org.modelio.metamodel.impl.uml.behavior.communicationModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("005b2ef8-c4bf-1fd8-97fe-001ec947cd2a")
public class CommunicationMessageImpl extends UmlModelElementImpl implements CommunicationMessage {
    @objid ("c2141e28-2779-4f4c-949e-af911ce1d868")
    @Override
    public String getArgument() {
        return (String) getAttVal(((CommunicationMessageSmClass)getClassOf()).getArgumentAtt());
    }

    @objid ("450c1f62-e142-4989-9f9f-63ed9f68ac6d")
    @Override
    public void setArgument(String value) {
        setAttVal(((CommunicationMessageSmClass)getClassOf()).getArgumentAtt(), value);
    }

    @objid ("33e6f0e1-9dd5-4cc4-9709-ff6c5376656e")
    @Override
    public String getSequence() {
        return (String) getAttVal(((CommunicationMessageSmClass)getClassOf()).getSequenceAtt());
    }

    @objid ("0d58f96e-3e48-487f-a8dd-4cc39edf3827")
    @Override
    public void setSequence(String value) {
        setAttVal(((CommunicationMessageSmClass)getClassOf()).getSequenceAtt(), value);
    }

    @objid ("82725ec3-fcbb-4009-a787-cd551a2c1b2a")
    @Override
    public MessageSort getSortOfMessage() {
        return (MessageSort) getAttVal(((CommunicationMessageSmClass)getClassOf()).getSortOfMessageAtt());
    }

    @objid ("06d43986-5109-4850-af26-2631ffb05281")
    @Override
    public void setSortOfMessage(MessageSort value) {
        setAttVal(((CommunicationMessageSmClass)getClassOf()).getSortOfMessageAtt(), value);
    }

    @objid ("2bf8c933-401e-4d05-bc24-0a72e1c7f137")
    @Override
    public EList<InformationFlow> getRealizedInformationFlow() {
        return new SmList<>(this, ((CommunicationMessageSmClass)getClassOf()).getRealizedInformationFlowDep());
    }

    @objid ("eefd3660-564a-49d6-acaa-349de6e2cde4")
    @Override
    public <T extends InformationFlow> List<T> getRealizedInformationFlow(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InformationFlow element : getRealizedInformationFlow()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("b1330d7d-57b1-4a39-a713-053f661f9a45")
    @Override
    public CommunicationChannel getChannel() {
        Object obj = getDepVal(((CommunicationMessageSmClass)getClassOf()).getChannelDep());
        return (obj instanceof CommunicationChannel)? (CommunicationChannel)obj : null;
    }

    @objid ("d683fa07-b3ee-4d5f-a1ea-7da0644623fe")
    @Override
    public void setChannel(CommunicationChannel value) {
        appendDepVal(((CommunicationMessageSmClass)getClassOf()).getChannelDep(), (SmObjectImpl)value);
    }

    @objid ("9b1328e1-c374-4a0d-a44d-e829547d045e")
    @Override
    public CommunicationChannel getInvertedChannel() {
        Object obj = getDepVal(((CommunicationMessageSmClass)getClassOf()).getInvertedChannelDep());
        return (obj instanceof CommunicationChannel)? (CommunicationChannel)obj : null;
    }

    @objid ("a2628193-4736-4008-9f37-0e562c3c2e7e")
    @Override
    public void setInvertedChannel(CommunicationChannel value) {
        appendDepVal(((CommunicationMessageSmClass)getClassOf()).getInvertedChannelDep(), (SmObjectImpl)value);
    }

    @objid ("69cf34a1-50bc-40f2-b40a-690138666b6b")
    @Override
    public Operation getInvoked() {
        Object obj = getDepVal(((CommunicationMessageSmClass)getClassOf()).getInvokedDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("24946b45-9219-498c-99a7-bdfa413f3bfc")
    @Override
    public void setInvoked(Operation value) {
        appendDepVal(((CommunicationMessageSmClass)getClassOf()).getInvokedDep(), (SmObjectImpl)value);
    }

    @objid ("62a6fa51-07f2-40a6-a17a-7abda3a13c82")
    @Override
    public Signal getSignalSignature() {
        Object obj = getDepVal(((CommunicationMessageSmClass)getClassOf()).getSignalSignatureDep());
        return (obj instanceof Signal)? (Signal)obj : null;
    }

    @objid ("dac80b50-9002-4d81-9c3e-b774dcdf9345")
    @Override
    public void setSignalSignature(Signal value) {
        appendDepVal(((CommunicationMessageSmClass)getClassOf()).getSignalSignatureDep(), (SmObjectImpl)value);
    }

    @objid ("e476b637-f46e-4031-bae0-2400f9874d0e")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Channel
        obj = (SmObjectImpl)this.getDepVal(((CommunicationMessageSmClass)getClassOf()).getChannelDep());
        if (obj != null)
          return obj;
        // InvertedChannel
        obj = (SmObjectImpl)this.getDepVal(((CommunicationMessageSmClass)getClassOf()).getInvertedChannelDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("71df6dba-9f51-482b-a479-f4ef355e8bf6")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Channel
        dep = ((CommunicationMessageSmClass)getClassOf()).getChannelDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // InvertedChannel
        dep = ((CommunicationMessageSmClass)getClassOf()).getInvertedChannelDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("03a31ee1-cb11-4fb2-bbb9-92d0f5100ced")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitCommunicationMessage(this);
    }

}
