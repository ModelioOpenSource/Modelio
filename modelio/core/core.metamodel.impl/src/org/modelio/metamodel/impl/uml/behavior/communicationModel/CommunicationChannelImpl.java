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
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("3d66beff-bc9e-42aa-84dd-28f34e3a9a10")
public class CommunicationChannelImpl extends UmlModelElementImpl implements CommunicationChannel {
    @objid ("ef93767f-fe74-4ca8-8aa4-8092706fed66")
    @Override
    public EList<CommunicationMessage> getStartToEndMessage() {
        return new SmList<>(this, ((CommunicationChannelSmClass)getClassOf()).getStartToEndMessageDep());
    }

    @objid ("5f6d34dc-f175-40b0-b1c8-bd02eced38c2")
    @Override
    public <T extends CommunicationMessage> List<T> getStartToEndMessage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CommunicationMessage element : getStartToEndMessage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("a1dd27ff-cc4f-46a3-9a3e-293b17e08720")
    @Override
    public Link getChannel() {
        Object obj = getDepVal(((CommunicationChannelSmClass)getClassOf()).getChannelDep());
        return (obj instanceof Link)? (Link)obj : null;
    }

    @objid ("1fd2a183-a946-4dac-b972-a3842ec207de")
    @Override
    public void setChannel(Link value) {
        appendDepVal(((CommunicationChannelSmClass)getClassOf()).getChannelDep(), (SmObjectImpl)value);
    }

    @objid ("6942b1e2-9811-4a46-bf8a-c3a064b3d9fa")
    @Override
    public CommunicationNode getStart() {
        Object obj = getDepVal(((CommunicationChannelSmClass)getClassOf()).getStartDep());
        return (obj instanceof CommunicationNode)? (CommunicationNode)obj : null;
    }

    @objid ("aeca991d-33ae-40eb-a261-9e54347e40fe")
    @Override
    public void setStart(CommunicationNode value) {
        appendDepVal(((CommunicationChannelSmClass)getClassOf()).getStartDep(), (SmObjectImpl)value);
    }

    @objid ("abb580b8-0c87-4683-b905-1c0efabc7a01")
    @Override
    public NaryLink getNaryChannel() {
        Object obj = getDepVal(((CommunicationChannelSmClass)getClassOf()).getNaryChannelDep());
        return (obj instanceof NaryLink)? (NaryLink)obj : null;
    }

    @objid ("9856a21d-04fe-4b67-a07e-3846fa3a3fd3")
    @Override
    public void setNaryChannel(NaryLink value) {
        appendDepVal(((CommunicationChannelSmClass)getClassOf()).getNaryChannelDep(), (SmObjectImpl)value);
    }

    @objid ("7dfbe417-121e-4636-b843-478dfb976331")
    @Override
    public EList<CommunicationMessage> getEndToStartMessage() {
        return new SmList<>(this, ((CommunicationChannelSmClass)getClassOf()).getEndToStartMessageDep());
    }

    @objid ("6e595db7-f5be-44c1-9abd-95492a6c0de3")
    @Override
    public <T extends CommunicationMessage> List<T> getEndToStartMessage(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CommunicationMessage element : getEndToStartMessage()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("8959dfd4-4218-4df4-b721-6fd1f8afd29b")
    @Override
    public CommunicationNode getEnd() {
        Object obj = getDepVal(((CommunicationChannelSmClass)getClassOf()).getEndDep());
        return (obj instanceof CommunicationNode)? (CommunicationNode)obj : null;
    }

    @objid ("867b66bf-6712-4df7-957e-dc95c378d973")
    @Override
    public void setEnd(CommunicationNode value) {
        appendDepVal(((CommunicationChannelSmClass)getClassOf()).getEndDep(), (SmObjectImpl)value);
    }

    @objid ("49fe3f04-2041-462f-a5e1-75b2836c6685")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Start
        obj = (SmObjectImpl)this.getDepVal(((CommunicationChannelSmClass)getClassOf()).getStartDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("7e0e8b05-15a7-4044-9f26-a9b0457d563d")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Start
        dep = ((CommunicationChannelSmClass)getClassOf()).getStartDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("86d7cec8-e4b7-44af-8016-2adedbe909c1")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitCommunicationChannel(this);
    }

}
