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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.ClassAssociationData;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0002efae-c4bf-1fd8-97fe-001ec947cd2a")
public class ClassAssociationImpl extends UmlModelElementImpl implements ClassAssociation {
    @objid ("eb86b4e2-7d85-40d4-9ee3-77ef5a33b3e5")
    @Override
    public NaryAssociation getNaryAssociationPart() {
        Object obj = getDepVal(((ClassAssociationSmClass)getClassOf()).getNaryAssociationPartDep());
        return (obj instanceof NaryAssociation)? (NaryAssociation)obj : null;
    }

    @objid ("32ea65b7-72a4-40fc-8453-237241183c53")
    @Override
    public void setNaryAssociationPart(NaryAssociation value) {
        appendDepVal(((ClassAssociationSmClass)getClassOf()).getNaryAssociationPartDep(), (SmObjectImpl)value);
    }

    @objid ("f17a6d36-d0f3-4ec3-b18d-257dbb22597f")
    @Override
    public Class getClassPart() {
        Object obj = getDepVal(((ClassAssociationSmClass)getClassOf()).getClassPartDep());
        return (obj instanceof Class)? (Class)obj : null;
    }

    @objid ("84039135-c8b6-4749-b310-88119b9adfd4")
    @Override
    public void setClassPart(Class value) {
        appendDepVal(((ClassAssociationSmClass)getClassOf()).getClassPartDep(), (SmObjectImpl)value);
    }

    @objid ("67d01b29-9b3d-48b0-8992-02f16a46f7ef")
    @Override
    public Association getAssociationPart() {
        Object obj = getDepVal(((ClassAssociationSmClass)getClassOf()).getAssociationPartDep());
        return (obj instanceof Association)? (Association)obj : null;
    }

    @objid ("1219c590-6fa3-43f8-acdf-0ee9c3a43793")
    @Override
    public void setAssociationPart(Association value) {
        appendDepVal(((ClassAssociationSmClass)getClassOf()).getAssociationPartDep(), (SmObjectImpl)value);
    }

    @objid ("6401abfb-66ff-4ac1-8777-dd2f504fd0c9")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // NaryAssociationPart
        obj = (SmObjectImpl)this.getDepVal(((ClassAssociationSmClass)getClassOf()).getNaryAssociationPartDep());
        if (obj != null)
          return obj;
        // AssociationPart
        obj = (SmObjectImpl)this.getDepVal(((ClassAssociationSmClass)getClassOf()).getAssociationPartDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("9aae8b50-62ec-4bee-ac4b-5bb9751aaf96")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // NaryAssociationPart
        dep = ((ClassAssociationSmClass)getClassOf()).getNaryAssociationPartDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // AssociationPart
        dep = ((ClassAssociationSmClass)getClassOf()).getAssociationPartDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("8da01baf-97ba-4c48-ad98-81f75b347c6c")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitClassAssociation(this);
    }

}
