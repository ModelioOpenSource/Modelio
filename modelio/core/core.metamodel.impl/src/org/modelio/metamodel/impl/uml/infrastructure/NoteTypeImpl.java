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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.NoteTypeData;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008bfe5c-c4be-1fd8-97fe-001ec947cd2a")
public class NoteTypeImpl extends ModelElementImpl implements NoteType {
    @objid ("9a3d8668-3b10-405b-98a5-c33bbf1d1190")
    @Override
    public ModuleComponent getModule() {
        MetaclassReference ref = getOwnerReference();
        Stereotype st = getOwnerStereotype();
        
        if (ref != null && ref.getOwnerProfile() != null) {
            return ref.getOwnerProfile().getOwnerModule();
        } else if (st != null && st.getOwner() != null) {
            return st.getOwner().getOwnerModule();
        } else
            return null;
    }

    @objid ("31784a00-c409-4eae-9032-bc38f5de0da1")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((NoteTypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("bb2f65ff-37b1-4a0e-bbb5-a0aac431e38b")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((NoteTypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("d1b4aeb5-8c38-41ac-8ac6-abee888670bb")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((NoteTypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("2c371f69-fa14-447b-a1f5-336fb6e59d1b")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((NoteTypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("4b47f54f-2553-4461-a686-50bc4f488b78")
    @Override
    public String getMimeType() {
        return (String) getAttVal(((NoteTypeSmClass)getClassOf()).getMimeTypeAtt());
    }

    @objid ("d44f54ba-7f0f-45f4-b689-dd6133b2e4bf")
    @Override
    public void setMimeType(String value) {
        setAttVal(((NoteTypeSmClass)getClassOf()).getMimeTypeAtt(), value);
    }

    @objid ("34bb4b96-8e28-4701-9b62-62caa6923141")
    @Override
    public EList<Note> getElement() {
        return new SmList<>(this, ((NoteTypeSmClass)getClassOf()).getElementDep());
    }

    @objid ("27094da5-36fd-42cf-a4cc-175fcbea6a90")
    @Override
    public <T extends Note> List<T> getElement(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Note element : getElement()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("11ae06fc-b306-4d9f-be6c-a562b1899d8c")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((NoteTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("fcb0a537-4dd2-4979-ad16-8ddf2536431a")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((NoteTypeSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("e69fe0f4-591e-4161-8574-c84a034000dd")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((NoteTypeSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("2f774257-9fe8-4440-a753-ea5350aaaf07")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((NoteTypeSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("3550e2a7-8bbd-4527-a88c-0437284a3170")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerStereotype
        obj = (SmObjectImpl)this.getDepVal(((NoteTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        if (obj != null)
          return obj;
        // OwnerReference
        obj = (SmObjectImpl)this.getDepVal(((NoteTypeSmClass)getClassOf()).getOwnerReferenceDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("68a63cbf-be5c-41e9-b62d-18b300bdabfb")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerStereotype
        dep = ((NoteTypeSmClass)getClassOf()).getOwnerStereotypeDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerReference
        dep = ((NoteTypeSmClass)getClassOf()).getOwnerReferenceDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("695e73f7-47a6-4291-bb53-d80e9f3b15c7")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitNoteType(this);
    }

}
