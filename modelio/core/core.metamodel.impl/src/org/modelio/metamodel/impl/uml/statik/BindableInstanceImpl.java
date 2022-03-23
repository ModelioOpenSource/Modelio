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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0000df02-c4bf-1fd8-97fe-001ec947cd2a")
public class BindableInstanceImpl extends InstanceImpl implements BindableInstance {
    @objid ("3b6845d6-b545-4b05-b239-ef6232ea3ebd")
    @Override
    public Instance getCluster() {
        Object obj = getDepVal(((BindableInstanceSmClass)getClassOf()).getClusterDep());
        return (obj instanceof Instance)? (Instance)obj : null;
    }

    @objid ("08c67ad5-58cb-4562-82df-8206d9f75399")
    @Override
    public void setCluster(Instance value) {
        appendDepVal(((BindableInstanceSmClass)getClassOf()).getClusterDep(), (SmObjectImpl)value);
    }

    @objid ("6c1c1d6c-6541-4572-b174-0331f59b90f5")
    @Override
    public Classifier getInternalOwner() {
        Object obj = getDepVal(((BindableInstanceSmClass)getClassOf()).getInternalOwnerDep());
        return (obj instanceof Classifier)? (Classifier)obj : null;
    }

    @objid ("1e155dd5-1034-413b-a0d3-d94e7591f636")
    @Override
    public void setInternalOwner(Classifier value) {
        appendDepVal(((BindableInstanceSmClass)getClassOf()).getInternalOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("8bce3380-f7bd-46b8-8b15-fe78f801d35e")
    @Override
    public EList<Binding> getRepresentation() {
        return new SmList<>(this, ((BindableInstanceSmClass)getClassOf()).getRepresentationDep());
    }

    @objid ("9e5b504b-e70d-43e9-a8f9-e8f6ba8bba77")
    @Override
    public <T extends Binding> List<T> getRepresentation(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Binding element : getRepresentation()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("990efdb8-fa69-421e-ac12-5af715631338")
    @Override
    public UmlModelElement getRepresentedFeature() {
        Object obj = getDepVal(((BindableInstanceSmClass)getClassOf()).getRepresentedFeatureDep());
        return (obj instanceof UmlModelElement)? (UmlModelElement)obj : null;
    }

    @objid ("36808961-cd25-4d62-8682-7bd7cc4b8a0f")
    @Override
    public void setRepresentedFeature(UmlModelElement value) {
        appendDepVal(((BindableInstanceSmClass)getClassOf()).getRepresentedFeatureDep(), (SmObjectImpl)value);
    }

    @objid ("bc9b9ec9-0999-4ac6-ba2f-1f5dc570ce03")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Cluster
        obj = (SmObjectImpl)this.getDepVal(((BindableInstanceSmClass)getClassOf()).getClusterDep());
        if (obj != null)
          return obj;
        // InternalOwner
        obj = (SmObjectImpl)this.getDepVal(((BindableInstanceSmClass)getClassOf()).getInternalOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("9f4988b5-882a-4f0c-b72b-ea86d88bca93")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Cluster
        dep = ((BindableInstanceSmClass)getClassOf()).getClusterDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // InternalOwner
        dep = ((BindableInstanceSmClass)getClassOf()).getInternalOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("b973a490-d980-4c22-b795-d1da41083e1c")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBindableInstance(this);
    }

}
