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
    @objid ("c2ff01ae-cf1c-466b-8be9-0dc8727aa56a")
    @Override
    public String getContent() {
        return (String) getAttVal(((NoteSmClass)getClassOf()).getContentAtt());
    }

    @objid ("3a309ed3-172e-43b5-aa5d-5f94f14df0da")
    @Override
    public void setContent(String value) {
        setAttVal(((NoteSmClass)getClassOf()).getContentAtt(), value);
    }

    @objid ("f75a4807-57f3-4b64-ab80-ae76b8dbada6")
    @Override
    public String getMimeType() {
        return (String) getAttVal(((NoteSmClass)getClassOf()).getMimeTypeAtt());
    }

    @objid ("88a0ac46-dca8-45b7-9d5b-4157c5a592f7")
    @Override
    public void setMimeType(String value) {
        setAttVal(((NoteSmClass)getClassOf()).getMimeTypeAtt(), value);
    }

    @objid ("2df2af99-6507-44aa-b601-7d5c50a77784")
    @Override
    public NoteType getModel() {
        Object obj = getDepVal(((NoteSmClass)getClassOf()).getModelDep());
        return (obj instanceof NoteType)? (NoteType)obj : null;
    }

    @objid ("23762478-7f30-45c4-9305-2610e057555a")
    @Override
    public void setModel(NoteType value) {
        appendDepVal(((NoteSmClass)getClassOf()).getModelDep(), (SmObjectImpl)value);
    }

    @objid ("4e277064-34ee-448f-ae8c-69e4aa6dcf0b")
    @Override
    public ModelElement getSubject() {
        Object obj = getDepVal(((NoteSmClass)getClassOf()).getSubjectDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("cd07ed47-1c4b-4b19-9b35-bc24f51eaf26")
    @Override
    public void setSubject(ModelElement value) {
        appendDepVal(((NoteSmClass)getClassOf()).getSubjectDep(), (SmObjectImpl)value);
    }

    @objid ("9d5b4445-0702-41f0-ac70-037e197f2088")
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

    @objid ("2eaaffd1-c134-4375-882d-5f6a58d73746")
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

    @objid ("9f92da81-16c5-4f90-b7d7-843b8eb5f7d0")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitNote(this);
    }

}
