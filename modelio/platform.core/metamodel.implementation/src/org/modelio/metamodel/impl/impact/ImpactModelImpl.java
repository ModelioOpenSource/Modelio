/* 
 * Copyright 2013-2019 Modeliosoft
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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("7efc8081-2737-4355-8642-957802c872a3")
    @Override
    public ImpactProject getProject() {
        Object obj = getDepVal(((ImpactModelSmClass)getClassOf()).getProjectDep());
        return (obj instanceof ImpactProject)? (ImpactProject)obj : null;
    }

    @objid ("6d4c5292-8ce3-4983-95aa-35397fed3503")
    @Override
    public void setProject(ImpactProject value) {
        appendDepVal(((ImpactModelSmClass)getClassOf()).getProjectDep(), (SmObjectImpl)value);
    }

    @objid ("8734b414-9235-41a3-967e-f1acbfc011ee")
    @Override
    public EList<ImpactLink> getOwnedLinks() {
        return new SmList<>(this, ((ImpactModelSmClass)getClassOf()).getOwnedLinksDep());
    }

    @objid ("4b2020de-5b0c-45ce-a1db-c3b02d9b5146")
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

    @objid ("734f9d7e-82f4-43b1-a814-727981a855fc")
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

    @objid ("b175a8a9-31f7-41bd-b4cb-7d3e8d16f534")
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

    @objid ("d60f23c8-a31b-4492-951e-3e44138d2ca1")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitImpactModel(this);
    }

}
