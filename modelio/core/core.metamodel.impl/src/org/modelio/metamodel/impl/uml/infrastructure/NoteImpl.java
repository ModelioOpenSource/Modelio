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
    @objid ("9acf7503-09dd-4393-8977-746a9aeb1ca0")
    @Override
    public String getContent() {
        return (String) getAttVal(((NoteSmClass)getClassOf()).getContentAtt());
    }

    @objid ("0bbcd9ba-9adb-4483-bcdf-927f4ef6b9bd")
    @Override
    public void setContent(String value) {
        setAttVal(((NoteSmClass)getClassOf()).getContentAtt(), value);
    }

    @objid ("a3a45951-6ed5-4eca-a0f1-a7bce963a99e")
    @Override
    public String getMimeType() {
        return (String) getAttVal(((NoteSmClass)getClassOf()).getMimeTypeAtt());
    }

    @objid ("4549757f-ab90-4157-94e8-490662f51474")
    @Override
    public void setMimeType(String value) {
        setAttVal(((NoteSmClass)getClassOf()).getMimeTypeAtt(), value);
    }

    @objid ("aa53c3d4-3f0b-4d57-89bf-b15f01ae00ce")
    @Override
    public NoteType getModel() {
        Object obj = getDepVal(((NoteSmClass)getClassOf()).getModelDep());
        return (obj instanceof NoteType)? (NoteType)obj : null;
    }

    @objid ("43d9303d-26f9-4f53-a1ff-78e582530dbe")
    @Override
    public void setModel(NoteType value) {
        appendDepVal(((NoteSmClass)getClassOf()).getModelDep(), (SmObjectImpl)value);
    }

    @objid ("18b93c8c-a396-4fc9-b5a6-32cb0773f28e")
    @Override
    public ModelElement getSubject() {
        Object obj = getDepVal(((NoteSmClass)getClassOf()).getSubjectDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("746d3d18-d767-4b4d-b912-3bf381f2e7ea")
    @Override
    public void setSubject(ModelElement value) {
        appendDepVal(((NoteSmClass)getClassOf()).getSubjectDep(), (SmObjectImpl)value);
    }

    @objid ("7dd03cc6-ecfd-437d-8cbf-7b7ef54b2a0f")
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

    @objid ("3dbf4cdf-21e1-4a51-b6cd-d69a719f721b")
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

    @objid ("69ef582e-4699-447e-a5ab-ed8c8c6a9782")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitNote(this);
    }

}
