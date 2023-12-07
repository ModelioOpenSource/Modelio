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
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0091bf72-c4be-1fd8-97fe-001ec947cd2a")
public class MetaclassReferenceImpl extends ElementImpl implements MetaclassReference {
    @objid ("b84ec88a-389e-48d4-89e0-daceac3a1afe")
    @Override
    public ModuleComponent getModule() {
        final Profile prof = getOwnerProfile();
        return prof!=null ? prof.getOwnerModule() : null;
    }

    @objid ("35f44905-abeb-4553-a024-022c0fe0483a")
    @Override
    public String getReferencedClassName() {
        return (String) getAttVal(((MetaclassReferenceSmClass)getClassOf()).getReferencedClassNameAtt());
    }

    @objid ("19461da1-cec7-4f1b-a1ff-c5a528a20664")
    @Override
    public void setReferencedClassName(String value) {
        setAttVal(((MetaclassReferenceSmClass)getClassOf()).getReferencedClassNameAtt(), value);
    }

    @objid ("b8a0a21c-a569-4f9d-a52a-0261324c72ef")
    @Override
    public PropertyTableDefinition getDefinedTable() {
        Object obj = getDepVal(((MetaclassReferenceSmClass)getClassOf()).getDefinedTableDep());
        return (obj instanceof PropertyTableDefinition)? (PropertyTableDefinition)obj : null;
    }

    @objid ("3970ae40-e998-44fe-a81d-0639f3866a55")
    @Override
    public void setDefinedTable(PropertyTableDefinition value) {
        appendDepVal(((MetaclassReferenceSmClass)getClassOf()).getDefinedTableDep(), (SmObjectImpl)value);
    }

    @objid ("4bc417e7-1c6c-416d-b526-b774a71c8df7")
    @Override
    public EList<NoteType> getDefinedNoteType() {
        return new SmList<>(this, ((MetaclassReferenceSmClass)getClassOf()).getDefinedNoteTypeDep());
    }

    @objid ("20a6be78-1228-4147-905b-da3097ad0ddb")
    @Override
    public <T extends NoteType> List<T> getDefinedNoteType(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final NoteType element : getDefinedNoteType()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("826d655d-c6a1-4d39-aa8f-1155b2326c09")
    @Override
    public EList<ResourceType> getDefinedResourceType() {
        return new SmList<>(this, ((MetaclassReferenceSmClass)getClassOf()).getDefinedResourceTypeDep());
    }

    @objid ("3b165739-aecf-4be9-9c48-8bbb0907a9bf")
    @Override
    public <T extends ResourceType> List<T> getDefinedResourceType(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ResourceType element : getDefinedResourceType()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("6ac493bf-7421-4939-8ed6-713a7dd3d117")
    @Override
    public Profile getOwnerProfile() {
        Object obj = getDepVal(((MetaclassReferenceSmClass)getClassOf()).getOwnerProfileDep());
        return (obj instanceof Profile)? (Profile)obj : null;
    }

    @objid ("89413263-3cf4-49c7-abd5-e5c54ffb6756")
    @Override
    public void setOwnerProfile(Profile value) {
        appendDepVal(((MetaclassReferenceSmClass)getClassOf()).getOwnerProfileDep(), (SmObjectImpl)value);
    }

    @objid ("86b16a6d-eb56-40dc-80e2-79704ced89a4")
    @Override
    public EList<TagType> getDefinedTagType() {
        return new SmList<>(this, ((MetaclassReferenceSmClass)getClassOf()).getDefinedTagTypeDep());
    }

    @objid ("9bb084a9-12fc-4c80-87b5-665390d024d5")
    @Override
    public <T extends TagType> List<T> getDefinedTagType(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TagType element : getDefinedTagType()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("2f623de7-82ba-47d7-8f7e-12e8ded83498")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerProfile
        obj = (SmObjectImpl)this.getDepVal(((MetaclassReferenceSmClass)getClassOf()).getOwnerProfileDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("051911ac-8bc7-45ac-a660-ffa7ae00057b")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerProfile
        dep = ((MetaclassReferenceSmClass)getClassOf()).getOwnerProfileDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("47dbbbab-2785-429f-a498-2c4463f4acc9")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMetaclassReference(this);
    }

}
