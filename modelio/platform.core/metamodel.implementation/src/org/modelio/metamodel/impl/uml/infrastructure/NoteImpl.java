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
import org.modelio.metamodel.impl.uml.infrastructure.NoteData;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008a098a-c4be-1fd8-97fe-001ec947cd2a")
public class NoteImpl extends ModelElementImpl implements Note {
    @objid ("d14ac972-23cd-4905-9097-2403a03b24a1")
    @Override
    public String getContent() {
        return (String) getAttVal(((NoteSmClass)getClassOf()).getContentAtt());
    }

    @objid ("f57f0f41-c2c7-44f5-8fb3-ae5163ce9401")
    @Override
    public void setContent(String value) {
        setAttVal(((NoteSmClass)getClassOf()).getContentAtt(), value);
    }

    @objid ("a8117b65-ee99-4480-aa2f-c6301013772a")
    @Override
    public String getMimeType() {
        return (String) getAttVal(((NoteSmClass)getClassOf()).getMimeTypeAtt());
    }

    @objid ("63743a6e-b8bd-403f-ad42-cdf970ffdd92")
    @Override
    public void setMimeType(String value) {
        setAttVal(((NoteSmClass)getClassOf()).getMimeTypeAtt(), value);
    }

    @objid ("fc52621b-8bfc-4c25-99e5-792693c62b67")
    @Override
    public NoteType getModel() {
        Object obj = getDepVal(((NoteSmClass)getClassOf()).getModelDep());
        return (obj instanceof NoteType)? (NoteType)obj : null;
    }

    @objid ("c7afcda1-1519-4fd9-81e1-21e831557f2c")
    @Override
    public void setModel(NoteType value) {
        appendDepVal(((NoteSmClass)getClassOf()).getModelDep(), (SmObjectImpl)value);
    }

    @objid ("7f049995-1f53-437a-8039-19f2c5286ed0")
    @Override
    public ModelElement getSubject() {
        Object obj = getDepVal(((NoteSmClass)getClassOf()).getSubjectDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("1e40f425-a086-4f36-a781-e0da90204b1e")
    @Override
    public void setSubject(ModelElement value) {
        appendDepVal(((NoteSmClass)getClassOf()).getSubjectDep(), (SmObjectImpl)value);
    }

    @objid ("5853e1d5-8431-444f-bbaa-ab0daae12602")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Subject
        obj = (SmObjectImpl)this.getDepVal(((NoteSmClass)getClassOf()).getSubjectDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("bc6c1828-a6e4-4089-90c0-bed0ca3e9545")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Subject
        dep = ((NoteSmClass)getClassOf()).getSubjectDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("b3e5006d-01ac-47fd-bc93-5d04dbb71e14")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitNote(this);
    }

}
