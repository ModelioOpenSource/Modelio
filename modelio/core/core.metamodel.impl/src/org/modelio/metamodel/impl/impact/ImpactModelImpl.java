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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
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
    @objid ("876bb4db-a194-4ca2-97a2-f649a0a3c18d")
    @Override
    public ImpactProject getProject() {
        Object obj = getDepVal(((ImpactModelSmClass)getClassOf()).getProjectDep());
        return (obj instanceof ImpactProject)? (ImpactProject)obj : null;
    }

    @objid ("39420ab1-aaf8-41b5-acc7-5d44d5f586d1")
    @Override
    public void setProject(ImpactProject value) {
        appendDepVal(((ImpactModelSmClass)getClassOf()).getProjectDep(), (SmObjectImpl)value);
    }

    @objid ("74b04761-6d4a-4cda-8d06-692c58c6ad8c")
    @Override
    public EList<ImpactLink> getOwnedLinks() {
        return new SmList<>(this, ((ImpactModelSmClass)getClassOf()).getOwnedLinksDep());
    }

    @objid ("cae26c22-3929-4583-b8bc-11105a53db1d")
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

    @objid ("0c0a402c-9d10-4509-a1c1-2b4acc41007f")
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

    @objid ("872474f7-a594-48f7-b28d-9dbb3a72538e")
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

    @objid ("37e2638e-60c7-41bf-9771-77d34bb31faf")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitImpactModel(this);
    }

}
