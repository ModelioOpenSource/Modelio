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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.DependencyData;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00860ba0-c4be-1fd8-97fe-001ec947cd2a")
public class DependencyImpl extends ModelElementImpl implements Dependency {
    @objid ("958624eb-b6e1-4f60-bc01-c851db7b8e13")
    @Override
    public ModelElement getImpacted() {
        Object obj = getDepVal(((DependencySmClass)getClassOf()).getImpactedDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("388497b4-d7e4-4b61-bc83-c660db52ef51")
    @Override
    public void setImpacted(ModelElement value) {
        appendDepVal(((DependencySmClass)getClassOf()).getImpactedDep(), (SmObjectImpl)value);
    }

    @objid ("230de310-6aa6-4db7-beac-8df7ff2d26b7")
    @Override
    public ModelElement getDependsOn() {
        Object obj = getDepVal(((DependencySmClass)getClassOf()).getDependsOnDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("3cf27b94-c0fe-453d-a322-a68a7b9bb974")
    @Override
    public void setDependsOn(ModelElement value) {
        appendDepVal(((DependencySmClass)getClassOf()).getDependsOnDep(), (SmObjectImpl)value);
    }

    @objid ("acb9871c-4eb1-4b75-bec0-7c4464f00e27")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Impacted
        obj = (SmObjectImpl)this.getDepVal(((DependencySmClass)getClassOf()).getImpactedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("8afb7b8e-7b77-460c-9483-3a7aada6a235")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Impacted
        dep = ((DependencySmClass)getClassOf()).getImpactedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("09d35286-2ef0-4497-908f-03ac8f70c54e")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitDependency(this);
    }

}
