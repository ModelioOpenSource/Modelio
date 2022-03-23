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

package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00041fdc-c4bf-1fd8-97fe-001ec947cd2a")
public class CollaborationImpl extends NameSpaceImpl implements Collaboration {
    @objid ("c15195b3-4d37-4d70-a502-50e703f4e6ca")
    @Override
    public boolean isIsConcurrent() {
        return (Boolean) getAttVal(((CollaborationSmClass)getClassOf()).getIsConcurrentAtt());
    }

    @objid ("94d851a7-b453-483a-8cd9-a5aa49b93aa6")
    @Override
    public void setIsConcurrent(boolean value) {
        setAttVal(((CollaborationSmClass)getClassOf()).getIsConcurrentAtt(), value);
    }

    @objid ("446212e4-3123-4dca-b7e0-2df0af0ce78c")
    @Override
    public Operation getORepresented() {
        Object obj = getDepVal(((CollaborationSmClass)getClassOf()).getORepresentedDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("f718983e-bcc1-4c91-b59d-66dde7075bca")
    @Override
    public void setORepresented(Operation value) {
        appendDepVal(((CollaborationSmClass)getClassOf()).getORepresentedDep(), (SmObjectImpl)value);
    }

    @objid ("d32e874b-0b0a-4407-87eb-c8ae3752fc5d")
    @Override
    public Behavior getBRepresented() {
        Object obj = getDepVal(((CollaborationSmClass)getClassOf()).getBRepresentedDep());
        return (obj instanceof Behavior)? (Behavior)obj : null;
    }

    @objid ("93d57e24-b4ff-46ca-957e-59e7becf31f2")
    @Override
    public void setBRepresented(Behavior value) {
        appendDepVal(((CollaborationSmClass)getClassOf()).getBRepresentedDep(), (SmObjectImpl)value);
    }

    @objid ("2026da57-c1e9-4e5c-82b8-d08431a632c0")
    @Override
    public EList<CollaborationUse> getOccurrence() {
        return new SmList<>(this, ((CollaborationSmClass)getClassOf()).getOccurrenceDep());
    }

    @objid ("4a78aded-a77a-4677-983c-98bed3beff81")
    @Override
    public <T extends CollaborationUse> List<T> getOccurrence(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final CollaborationUse element : getOccurrence()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("ccdc41e0-2553-40ba-9870-4d2b2ba756ca")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // ORepresented
        obj = (SmObjectImpl)this.getDepVal(((CollaborationSmClass)getClassOf()).getORepresentedDep());
        if (obj != null)
          return obj;
        // BRepresented
        obj = (SmObjectImpl)this.getDepVal(((CollaborationSmClass)getClassOf()).getBRepresentedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("ca089d63-65b4-465d-9000-509d33564260")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // ORepresented
        dep = ((CollaborationSmClass)getClassOf()).getORepresentedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // BRepresented
        dep = ((CollaborationSmClass)getClassOf()).getBRepresentedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("5a5b408b-9443-4766-a152-9288b2912e05")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitCollaboration(this);
    }

}
