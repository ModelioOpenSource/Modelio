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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
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

    @objid ("e9556f17-235d-4294-9ea9-6686e02b10e0")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((NoteTypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("142f2310-659d-40a2-a80e-db9ed3fafaa3")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((NoteTypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("48b73558-9514-4a7d-b3d2-44cb79cca21e")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((NoteTypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("93b2787b-518c-4da4-9bc6-aa27311f62fe")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((NoteTypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("34149364-3688-4475-a43b-d6c1873ebde5")
    @Override
    public String getMimeType() {
        return (String) getAttVal(((NoteTypeSmClass)getClassOf()).getMimeTypeAtt());
    }

    @objid ("eccc6c54-ea31-4808-a0ef-bbd68e0cc0a5")
    @Override
    public void setMimeType(String value) {
        setAttVal(((NoteTypeSmClass)getClassOf()).getMimeTypeAtt(), value);
    }

    @objid ("59e2f23b-1939-48f4-b7f9-9e0961e22341")
    @Override
    public EList<Note> getElement() {
        return new SmList<>(this, ((NoteTypeSmClass)getClassOf()).getElementDep());
    }

    @objid ("0e0be964-b113-4f92-a9d7-2f3b5875f065")
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

    @objid ("4f246dd7-40d5-4653-9c4c-44875f234509")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((NoteTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("ecbfd526-7dd4-4654-9c74-2fe717189ed9")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((NoteTypeSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("e69cfa59-1636-4646-a487-46a1b9efec03")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((NoteTypeSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("0aa7250e-0afc-47ba-a4c4-e481b790352f")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((NoteTypeSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("15131725-f2cc-403a-b605-aeb5cbb80531")
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

    @objid ("ce73b3fe-c244-4940-9ef2-d79da9fb0157")
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

    @objid ("fced220e-8910-4551-bcc2-ee6d27310b9b")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitNoteType(this);
    }

}
