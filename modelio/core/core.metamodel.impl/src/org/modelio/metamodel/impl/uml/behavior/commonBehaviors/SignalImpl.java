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
package org.modelio.metamodel.impl.uml.behavior.commonBehaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalData;
import org.modelio.metamodel.impl.uml.statik.GeneralClassImpl;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0042379a-c4bf-1fd8-97fe-001ec947cd2a")
public class SignalImpl extends GeneralClassImpl implements Signal {
    @objid ("2dbd793c-43e3-410c-8f84-14b60848852a")
    @Override
    public boolean isIsEvent() {
        return (Boolean) getAttVal(((SignalSmClass)getClassOf()).getIsEventAtt());
    }

    @objid ("f7a0bfce-a395-4d09-bfc8-c116da1c6c41")
    @Override
    public void setIsEvent(boolean value) {
        setAttVal(((SignalSmClass)getClassOf()).getIsEventAtt(), value);
    }

    @objid ("e5f80b2c-90e5-4ea6-a8d0-e9a037fbe6b0")
    @Override
    public boolean isIsException() {
        return (Boolean) getAttVal(((SignalSmClass)getClassOf()).getIsExceptionAtt());
    }

    @objid ("ffc568c4-deb6-4a91-8008-0f8e9c7d97e1")
    @Override
    public void setIsException(boolean value) {
        setAttVal(((SignalSmClass)getClassOf()).getIsExceptionAtt(), value);
    }

    @objid ("3c8c541b-db68-4177-8583-825fcc2c27df")
    @Override
    public EList<SendSignalAction> getSender() {
        return new SmList<>(this, ((SignalSmClass)getClassOf()).getSenderDep());
    }

    @objid ("84f3da5e-447c-4964-8f75-1a3d0336ee46")
    @Override
    public <T extends SendSignalAction> List<T> getSender(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final SendSignalAction element : getSender()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("ec287bc5-b424-4375-9afb-221e4f090a05")
    @Override
    public EList<Message> getUsage() {
        return new SmList<>(this, ((SignalSmClass)getClassOf()).getUsageDep());
    }

    @objid ("2f125519-22f0-40ca-8451-c9d3e6266671")
    @Override
    public <T extends Message> List<T> getUsage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Message element : getUsage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("7ce4193e-e886-419b-8be4-1ade61aad0d6")
    @Override
    public EList<Transition> getSends() {
        return new SmList<>(this, ((SignalSmClass)getClassOf()).getSendsDep());
    }

    @objid ("d3508e2e-3490-4537-bdd2-47a9e17bfa74")
    @Override
    public <T extends Transition> List<T> getSends(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Transition element : getSends()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8ac39c74-a4c1-4d15-8665-0fcca967581b")
    @Override
    public Parameter getPBase() {
        Object obj = getDepVal(((SignalSmClass)getClassOf()).getPBaseDep());
        return (obj instanceof Parameter)? (Parameter)obj : null;
    }

    @objid ("041da403-d8b1-4ae0-b4e7-e87754c41dd8")
    @Override
    public void setPBase(Parameter value) {
        appendDepVal(((SignalSmClass)getClassOf()).getPBaseDep(), (SmObjectImpl)value);
    }

    @objid ("0129fd3f-3a6d-40d1-a695-10b35ba03ec6")
    @Override
    public Operation getOBase() {
        Object obj = getDepVal(((SignalSmClass)getClassOf()).getOBaseDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("adee26b0-a5c5-493e-915a-a24c1383ec70")
    @Override
    public void setOBase(Operation value) {
        appendDepVal(((SignalSmClass)getClassOf()).getOBaseDep(), (SmObjectImpl)value);
    }

    @objid ("c784c905-76cf-4467-ab41-ee432a3bbfc4")
    @Override
    public EList<CommunicationMessage> getCommunicationUsage() {
        return new SmList<>(this, ((SignalSmClass)getClassOf()).getCommunicationUsageDep());
    }

    @objid ("63f96270-689b-458f-9657-d2cde7a1f865")
    @Override
    public <T extends CommunicationMessage> List<T> getCommunicationUsage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CommunicationMessage element : getCommunicationUsage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("3baeb1a7-009f-403b-85a4-a19516812177")
    @Override
    public EList<DataFlow> getDOccurence() {
        return new SmList<>(this, ((SignalSmClass)getClassOf()).getDOccurenceDep());
    }

    @objid ("aa48db5b-cc60-46c4-8d0a-6fc4aa81ac14")
    @Override
    public <T extends DataFlow> List<T> getDOccurence(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final DataFlow element : getDOccurence()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("6c6135d8-4be1-42e3-871d-4aed3241af0d")
    @Override
    public EList<Event> getEOccurence() {
        return new SmList<>(this, ((SignalSmClass)getClassOf()).getEOccurenceDep());
    }

    @objid ("29ad1441-e448-4b5d-84a7-75804f230fd4")
    @Override
    public <T extends Event> List<T> getEOccurence(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Event element : getEOccurence()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d44dca6e-99dc-41b5-b4c5-9455f0a97a4b")
    @Override
    public GeneralClass getBase() {
        Object obj = getDepVal(((SignalSmClass)getClassOf()).getBaseDep());
        return (obj instanceof GeneralClass)? (GeneralClass)obj : null;
    }

    @objid ("ee3ef676-a1c5-47da-b3a3-469f30f22112")
    @Override
    public void setBase(GeneralClass value) {
        appendDepVal(((SignalSmClass)getClassOf()).getBaseDep(), (SmObjectImpl)value);
    }

    @objid ("ebc8ee0e-b62e-4359-9f2e-8717ddae613d")
    @Override
    public EList<AcceptSignalAction> getReceiver() {
        return new SmList<>(this, ((SignalSmClass)getClassOf()).getReceiverDep());
    }

    @objid ("ce24b6e8-3b28-40b7-823b-e16536ed7073")
    @Override
    public <T extends AcceptSignalAction> List<T> getReceiver(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AcceptSignalAction element : getReceiver()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("3170e988-a2f4-4040-b43a-5305e255b53f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("0e863592-3577-4683-8ab3-cf786b422b2d")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("63f9f60c-ddcf-4219-a585-edefd4608572")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitSignal(this);
    }

}
