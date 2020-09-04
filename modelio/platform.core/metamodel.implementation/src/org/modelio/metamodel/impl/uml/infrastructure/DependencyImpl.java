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
    @objid ("4aef5107-956d-4b58-9375-4e359ca074c9")
    @Override
    public ModelElement getImpacted() {
        Object obj = getDepVal(((DependencySmClass)getClassOf()).getImpactedDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("be1f517d-6c86-480e-95e4-1cc151d8edc0")
    @Override
    public void setImpacted(ModelElement value) {
        appendDepVal(((DependencySmClass)getClassOf()).getImpactedDep(), (SmObjectImpl)value);
    }

    @objid ("a2b21a4a-a1eb-469d-b21d-cf931049c415")
    @Override
    public ModelElement getDependsOn() {
        Object obj = getDepVal(((DependencySmClass)getClassOf()).getDependsOnDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("c4203d1d-e154-42aa-9675-eb62e2fc66a9")
    @Override
    public void setDependsOn(ModelElement value) {
        appendDepVal(((DependencySmClass)getClassOf()).getDependsOnDep(), (SmObjectImpl)value);
    }

    @objid ("4b5fe7fa-bcb8-4320-91bc-2d1feb9aa8db")
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

    @objid ("eb4fcb36-c00f-4d82-9a91-3f0001033f76")
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

    @objid ("e0cf11f0-a57b-4760-ba90-c75241a083ab")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitDependency(this);
    }

}
