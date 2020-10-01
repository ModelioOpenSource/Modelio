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
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationNodeData;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("005ab766-c4bf-1fd8-97fe-001ec947cd2a")
public class CommunicationNodeImpl extends UmlModelElementImpl implements CommunicationNode {
    @objid ("51c26c2a-7ac0-4478-96e2-1f140f520a71")
    @Override
    public String getSelector() {
        return (String) getAttVal(((CommunicationNodeSmClass)getClassOf()).getSelectorAtt());
    }

    @objid ("b4cb9ae1-59ec-4b91-aed8-afb1b0bc4b3d")
    @Override
    public void setSelector(String value) {
        setAttVal(((CommunicationNodeSmClass)getClassOf()).getSelectorAtt(), value);
    }

    @objid ("f3c1306f-9a67-4da6-99bf-9043c9700698")
    @Override
    public CommunicationInteraction getOwner() {
        Object obj = getDepVal(((CommunicationNodeSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof CommunicationInteraction)? (CommunicationInteraction)obj : null;
    }

    @objid ("c05d999a-fcae-4ca6-a611-aa7bb93f7687")
    @Override
    public void setOwner(CommunicationInteraction value) {
        appendDepVal(((CommunicationNodeSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("a4c3dc2a-3111-4d71-b14b-8cb2bd06dd44")
    @Override
    public Instance getRepresented() {
        Object obj = getDepVal(((CommunicationNodeSmClass)getClassOf()).getRepresentedDep());
        return (obj instanceof Instance)? (Instance)obj : null;
    }

    @objid ("856438b3-c035-4767-8950-a1352f791541")
    @Override
    public void setRepresented(Instance value) {
        appendDepVal(((CommunicationNodeSmClass)getClassOf()).getRepresentedDep(), (SmObjectImpl)value);
    }

    @objid ("7245f788-fa12-4487-a36f-297b8c6122a0")
    @Override
    public EList<CommunicationChannel> getStarted() {
        return new SmList<>(this, ((CommunicationNodeSmClass)getClassOf()).getStartedDep());
    }

    @objid ("e6db2c3a-c648-41b9-a065-f3d43c1b68a2")
    @Override
    public <T extends CommunicationChannel> List<T> getStarted(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CommunicationChannel element : getStarted()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("660688e4-8686-4cb0-8cf1-31b13d9d2b41")
    @Override
    public EList<CommunicationChannel> getEnded() {
        return new SmList<>(this, ((CommunicationNodeSmClass)getClassOf()).getEndedDep());
    }

    @objid ("35616222-7629-4af0-9737-611422f9703a")
    @Override
    public <T extends CommunicationChannel> List<T> getEnded(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CommunicationChannel element : getEnded()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("b1d4907f-9543-4a84-b5e1-bc26c5497bd1")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((CommunicationNodeSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("62a01690-eba8-4e4d-810c-9c3de0387c10")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((CommunicationNodeSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("1cd12954-7b2f-4556-83c9-b316fc1a1933")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitCommunicationNode(this);
    }

}
