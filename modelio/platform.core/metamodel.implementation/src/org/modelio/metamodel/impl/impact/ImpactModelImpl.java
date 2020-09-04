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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.impact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.impl.impact.ImpactModelData;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("f9337910-9849-4739-82ec-e7423e142ab5")
public class ImpactModelImpl extends ModelElementImpl implements ImpactModel {
    @objid ("a45a23ac-5fa5-4066-a9a7-c7ccba7085a6")
    @Override
    public ImpactProject getProject() {
        Object obj = getDepVal(((ImpactModelSmClass)getClassOf()).getProjectDep());
        return (obj instanceof ImpactProject)? (ImpactProject)obj : null;
    }

    @objid ("01040c8d-d00b-416c-ad0a-5cac3834034c")
    @Override
    public void setProject(ImpactProject value) {
        appendDepVal(((ImpactModelSmClass)getClassOf()).getProjectDep(), (SmObjectImpl)value);
    }

    @objid ("07ff5552-f1a3-413b-a085-7d5f48e2745a")
    @Override
    public EList<ImpactLink> getOwnedLinks() {
        return new SmList<>(this, ((ImpactModelSmClass)getClassOf()).getOwnedLinksDep());
    }

    @objid ("4ef6435b-337b-4a40-ae89-81de76bb06b5")
    @Override
    public <T extends ImpactLink> List<T> getOwnedLinks(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ImpactLink element : getOwnedLinks()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("b7e4b454-5b31-4cf5-9b7d-ca9d5ee50c28")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // project
        obj = (SmObjectImpl)this.getDepVal(((ImpactModelSmClass)getClassOf()).getProjectDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("caa1c474-4361-4686-a2ca-a8b26fa1e9a5")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // project
        dep = ((ImpactModelSmClass)getClassOf()).getProjectDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("540ef9b9-b4bf-4bfa-8bfe-7334275debfb")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitImpactModel(this);
    }

}
