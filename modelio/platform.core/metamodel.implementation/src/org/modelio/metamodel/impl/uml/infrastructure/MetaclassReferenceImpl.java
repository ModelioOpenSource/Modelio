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
import org.modelio.metamodel.impl.uml.infrastructure.MetaclassReferenceData;
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
    @objid ("480a7e70-f2b0-4d29-be67-98eebc72b9e5")
    @Override
    public String getReferencedClassName() {
        return (String) getAttVal(((MetaclassReferenceSmClass)getClassOf()).getReferencedClassNameAtt());
    }

    @objid ("90a8fcd0-9032-49c0-89c4-4a003cb11a73")
    @Override
    public void setReferencedClassName(String value) {
        setAttVal(((MetaclassReferenceSmClass)getClassOf()).getReferencedClassNameAtt(), value);
    }

    @objid ("5abc7b2a-61b3-49f9-ad41-09c5a4c2ce69")
    @Override
    public PropertyTableDefinition getDefinedTable() {
        Object obj = getDepVal(((MetaclassReferenceSmClass)getClassOf()).getDefinedTableDep());
        return (obj instanceof PropertyTableDefinition)? (PropertyTableDefinition)obj : null;
    }

    @objid ("fda0fd66-4fe4-423a-9b8a-4312ec692caf")
    @Override
    public void setDefinedTable(PropertyTableDefinition value) {
        appendDepVal(((MetaclassReferenceSmClass)getClassOf()).getDefinedTableDep(), (SmObjectImpl)value);
    }

    @objid ("6d2329b8-ca98-40a8-a2f8-e61444881ae6")
    @Override
    public EList<NoteType> getDefinedNoteType() {
        return new SmList<>(this, ((MetaclassReferenceSmClass)getClassOf()).getDefinedNoteTypeDep());
    }

    @objid ("5377629a-f616-4d2f-bd30-7058fb55c3df")
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

    @objid ("cd66c002-f7cb-4cec-b0fc-2606752ac672")
    @Override
    public EList<ResourceType> getDefinedResourceType() {
        return new SmList<>(this, ((MetaclassReferenceSmClass)getClassOf()).getDefinedResourceTypeDep());
    }

    @objid ("480e9522-6e81-4492-aae5-bbeb16597a61")
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

    @objid ("fb8fb0b9-b776-4336-af80-d2518cfc98be")
    @Override
    public Profile getOwnerProfile() {
        Object obj = getDepVal(((MetaclassReferenceSmClass)getClassOf()).getOwnerProfileDep());
        return (obj instanceof Profile)? (Profile)obj : null;
    }

    @objid ("de726983-1eb9-4465-996a-9bfa7b2565b9")
    @Override
    public void setOwnerProfile(Profile value) {
        appendDepVal(((MetaclassReferenceSmClass)getClassOf()).getOwnerProfileDep(), (SmObjectImpl)value);
    }

    @objid ("139a8517-e69a-4380-a555-099a6cb63067")
    @Override
    public EList<TagType> getDefinedTagType() {
        return new SmList<>(this, ((MetaclassReferenceSmClass)getClassOf()).getDefinedTagTypeDep());
    }

    @objid ("fe2345a5-edc2-41b1-b4a6-acfd082abd88")
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

    @objid ("03ee575d-cac8-4f36-b274-413bcf675b47")
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

    @objid ("7605501f-81a0-4d56-a7d8-8868d887db8a")
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

    @objid ("5d652fb2-a9a9-4ef0-a909-517e2de8b8d7")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMetaclassReference(this);
    }

}
