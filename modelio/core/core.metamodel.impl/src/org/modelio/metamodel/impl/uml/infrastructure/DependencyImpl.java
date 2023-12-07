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

package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
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
    @objid ("20dddfba-12a8-4416-911c-68496838821a")
    @Override
    public ModelElement getImpacted() {
        Object obj = getDepVal(((DependencySmClass)getClassOf()).getImpactedDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("6f28174e-47da-4afb-b2a1-2ebf35abe385")
    @Override
    public void setImpacted(ModelElement value) {
        appendDepVal(((DependencySmClass)getClassOf()).getImpactedDep(), (SmObjectImpl)value);
    }

    @objid ("35ab12d0-8a1d-4b9d-ac8b-b596bf3591a1")
    @Override
    public ModelElement getDependsOn() {
        Object obj = getDepVal(((DependencySmClass)getClassOf()).getDependsOnDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("e3584b0a-f392-4400-a987-6438d88a1372")
    @Override
    public void setDependsOn(ModelElement value) {
        appendDepVal(((DependencySmClass)getClassOf()).getDependsOnDep(), (SmObjectImpl)value);
    }

    @objid ("c50f822f-3873-40b7-bf21-b0512e9517cf")
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

    @objid ("805c1af4-c108-4174-ab52-9126ae0779ea")
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

    @objid ("772aea30-2393-4794-bc62-40bf54eefc84")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitDependency(this);
    }

}
