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
package org.modelio.metamodel.impl.uml.behavior.interactionModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageKind;
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

@objid ("00496c04-c4bf-1fd8-97fe-001ec947cd2a")
public class MessageImpl extends UmlModelElementImpl implements Message {
    @objid ("d752c107-14ac-43cd-95ab-041bd02b8fa8")
    @Override
    public String getArgument() {
        return (String) getAttVal(((MessageSmClass)getClassOf()).getArgumentAtt());
    }

    @objid ("409c033a-49f8-4b4f-ae45-fd5050b6d96e")
    @Override
    public void setArgument(String value) {
        setAttVal(((MessageSmClass)getClassOf()).getArgumentAtt(), value);
    }

    @objid ("3d257e14-c099-4179-a2e7-941f7bf72963")
    @Override
    public MessageKind getKindOfMessage() {
        return (MessageKind) getAttVal(((MessageSmClass)getClassOf()).getKindOfMessageAtt());
    }

    @objid ("cba1ebb7-6149-4c6c-a7bc-02902a84585d")
    @Override
    public void setKindOfMessage(MessageKind value) {
        setAttVal(((MessageSmClass)getClassOf()).getKindOfMessageAtt(), value);
    }

    @objid ("3f4a020f-6f57-4024-b3c4-1c1cf344e354")
    @Override
    public MessageSort getSortOfMessage() {
        return (MessageSort) getAttVal(((MessageSmClass)getClassOf()).getSortOfMessageAtt());
    }

    @objid ("18607411-b7e7-4327-afe0-1aaf74dd9375")
    @Override
    public void setSortOfMessage(MessageSort value) {
        setAttVal(((MessageSmClass)getClassOf()).getSortOfMessageAtt(), value);
    }

    @objid ("b3dfaa6f-0ece-4437-b283-a11aa31b6c1c")
    @Override
    public String getSequence() {
        return (String) getAttVal(((MessageSmClass)getClassOf()).getSequenceAtt());
    }

    @objid ("1c5ef0ea-5963-4ee4-9fc7-a77845adcde0")
    @Override
    public void setSequence(String value) {
        setAttVal(((MessageSmClass)getClassOf()).getSequenceAtt(), value);
    }

    @objid ("ed66f7c1-9aa4-4a8e-b2db-925e1f916f29")
    @Override
    public Signal getSignalSignature() {
        Object obj = getDepVal(((MessageSmClass)getClassOf()).getSignalSignatureDep());
        return (obj instanceof Signal)? (Signal)obj : null;
    }

    @objid ("b346900a-53ed-4070-879e-40823e6293ad")
    @Override
    public void setSignalSignature(Signal value) {
        appendDepVal(((MessageSmClass)getClassOf()).getSignalSignatureDep(), (SmObjectImpl)value);
    }

    @objid ("4d3106bd-e081-4c2e-9c40-01d04c6920d5")
    @Override
    public MessageEnd getReceiveEvent() {
        Object obj = getDepVal(((MessageSmClass)getClassOf()).getReceiveEventDep());
        return (obj instanceof MessageEnd)? (MessageEnd)obj : null;
    }

    @objid ("f03c695f-1244-4709-bd4e-42a59e1a501d")
    @Override
    public void setReceiveEvent(MessageEnd value) {
        appendDepVal(((MessageSmClass)getClassOf()).getReceiveEventDep(), (SmObjectImpl)value);
    }

    @objid ("bc782c7f-d44a-41d5-9aa4-09b1eeacc5be")
    @Override
    public MessageEnd getSendEvent() {
        Object obj = getDepVal(((MessageSmClass)getClassOf()).getSendEventDep());
        return (obj instanceof MessageEnd)? (MessageEnd)obj : null;
    }

    @objid ("699a1d06-bd11-47bc-a1df-501770bcd020")
    @Override
    public void setSendEvent(MessageEnd value) {
        appendDepVal(((MessageSmClass)getClassOf()).getSendEventDep(), (SmObjectImpl)value);
    }

    @objid ("91ef6863-6bc1-479e-95b2-6f3dfbbae6ae")
    @Override
    public Operation getInvoked() {
        Object obj = getDepVal(((MessageSmClass)getClassOf()).getInvokedDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("8226109c-f2c3-43fe-9a3a-c17812d34a28")
    @Override
    public void setInvoked(Operation value) {
        appendDepVal(((MessageSmClass)getClassOf()).getInvokedDep(), (SmObjectImpl)value);
    }

    @objid ("78679a4d-0b94-478c-9fdc-4ee900dab1d4")
    @Override
    public EList<InformationFlow> getRealizedInformationFlow() {
        return new SmList<>(this, ((MessageSmClass)getClassOf()).getRealizedInformationFlowDep());
    }

    @objid ("c9f835b6-ae6c-4ce0-957b-28b3ef921608")
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

    @objid ("f287570c-2d5e-4f8d-9c60-5952b53765c5")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // SendEvent
        obj = (SmObjectImpl)this.getDepVal(((MessageSmClass)getClassOf()).getSendEventDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("3102fa1b-e9f2-47e8-8125-acea080aeead")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // SendEvent
        dep = ((MessageSmClass)getClassOf()).getSendEventDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("8d97b6c0-7de9-4ca8-ad21-ea250b6afcfd")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitMessage(this);
    }

}
