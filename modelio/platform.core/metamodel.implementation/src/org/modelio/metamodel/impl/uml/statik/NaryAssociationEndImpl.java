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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.statik.NaryAssociationEndData;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0020dab4-c4bf-1fd8-97fe-001ec947cd2a")
public class NaryAssociationEndImpl extends StructuralFeatureImpl implements NaryAssociationEnd {
    @objid ("86b1b500-24e3-43d5-ac69-213c8dc66304")
    @Override
    public NaryAssociation getNaryAssociation() {
        Object obj = getDepVal(((NaryAssociationEndSmClass)getClassOf()).getNaryAssociationDep());
        return (obj instanceof NaryAssociation)? (NaryAssociation)obj : null;
    }

    @objid ("fc418bd8-1eb6-4d2a-be49-0ba2c02f6294")
    @Override
    public void setNaryAssociation(NaryAssociation value) {
        appendDepVal(((NaryAssociationEndSmClass)getClassOf()).getNaryAssociationDep(), (SmObjectImpl)value);
    }

    @objid ("89463c74-c178-4e47-a1d9-7849dd12d73a")
    @Override
    public Classifier getOwner() {
        Object obj = getDepVal(((NaryAssociationEndSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof Classifier)? (Classifier)obj : null;
    }

    @objid ("0e6f17e9-8ab4-4763-8ea7-3f2afb6c772a")
    @Override
    public void setOwner(Classifier value) {
        appendDepVal(((NaryAssociationEndSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("957a0fa6-f329-43e5-8f65-c91578f33380")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((NaryAssociationEndSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        // NaryAssociation
        obj = (SmObjectImpl)this.getDepVal(((NaryAssociationEndSmClass)getClassOf()).getNaryAssociationDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("5a6303d9-1e6b-4909-9867-5b48a027937d")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((NaryAssociationEndSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // NaryAssociation
        dep = ((NaryAssociationEndSmClass)getClassOf()).getNaryAssociationDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("80499ed9-0e00-453b-b03c-9056039a7ce4")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitNaryAssociationEnd(this);
    }

}
