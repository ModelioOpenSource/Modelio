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

    @objid ("353c2315-04f2-4646-99bb-c88a0286f17b")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((NoteTypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("a674cd86-d260-41b9-a632-64477df4cae8")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((NoteTypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("b4f1c758-2323-456f-9056-e743b9b6eb84")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((NoteTypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("585c8054-f8cc-4afe-b445-73c2ecb95c4e")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((NoteTypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("ae307c72-e631-41d2-b9c2-8934f41e8cdd")
    @Override
    public String getMimeType() {
        return (String) getAttVal(((NoteTypeSmClass)getClassOf()).getMimeTypeAtt());
    }

    @objid ("aa4a15c1-e0dc-49fc-96da-21acb2877576")
    @Override
    public void setMimeType(String value) {
        setAttVal(((NoteTypeSmClass)getClassOf()).getMimeTypeAtt(), value);
    }

    @objid ("a96f4b8a-db80-4b54-aefd-76fafdad11a0")
    @Override
    public EList<Note> getElement() {
        return new SmList<>(this, ((NoteTypeSmClass)getClassOf()).getElementDep());
    }

    @objid ("05f4dcea-30cc-41f2-aba5-bb641e37d065")
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

    @objid ("b98ae09e-78cd-4231-bba7-534b4575558a")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((NoteTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("5ba4a2c3-66d9-4b3f-83a6-00328cd13a03")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((NoteTypeSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("95857ecc-da57-41ee-b347-3539e57b3934")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((NoteTypeSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("828505be-f447-4170-8e6d-5256654a175e")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((NoteTypeSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("c8314118-d234-44ed-8baa-0f6e7ac555da")
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

    @objid ("95ced7d3-bb6e-4fda-b48d-8ad2314ade2f")
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

    @objid ("8a2001ca-9b85-4733-ae2b-70e6280f0fc3")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitNoteType(this);
    }

}
