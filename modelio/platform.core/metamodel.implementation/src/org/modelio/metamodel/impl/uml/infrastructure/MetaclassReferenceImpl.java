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
    @objid ("ee8075a7-bab8-4596-8814-ccc195232531")
    @Override
    public String getReferencedClassName() {
        return (String) getAttVal(((MetaclassReferenceSmClass)getClassOf()).getReferencedClassNameAtt());
    }

    @objid ("7e257777-3756-4870-8d53-8623be20a09a")
    @Override
    public void setReferencedClassName(String value) {
        setAttVal(((MetaclassReferenceSmClass)getClassOf()).getReferencedClassNameAtt(), value);
    }

    @objid ("37d0f386-9b39-4974-95e3-5b3f761b716f")
    @Override
    public PropertyTableDefinition getDefinedTable() {
        Object obj = getDepVal(((MetaclassReferenceSmClass)getClassOf()).getDefinedTableDep());
        return (obj instanceof PropertyTableDefinition)? (PropertyTableDefinition)obj : null;
    }

    @objid ("c3e4296a-9d71-468f-b04b-1d23d7b0fe8e")
    @Override
    public void setDefinedTable(PropertyTableDefinition value) {
        appendDepVal(((MetaclassReferenceSmClass)getClassOf()).getDefinedTableDep(), (SmObjectImpl)value);
    }

    @objid ("b0e5aabf-b45e-4127-baeb-6f6acc5e4bd5")
    @Override
    public EList<NoteType> getDefinedNoteType() {
        return new SmList<>(this, ((MetaclassReferenceSmClass)getClassOf()).getDefinedNoteTypeDep());
    }

    @objid ("10bff594-0985-43f6-b7ad-b8c2c09d92d9")
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

    @objid ("62cb69ac-7dae-4187-967d-342fe51ecb56")
    @Override
    public EList<ResourceType> getDefinedResourceType() {
        return new SmList<>(this, ((MetaclassReferenceSmClass)getClassOf()).getDefinedResourceTypeDep());
    }

    @objid ("9e58d39c-77f9-49a7-8148-ad7a3c6c6666")
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

    @objid ("223df721-c28e-4dee-b2a1-d9f944c6e6a9")
    @Override
    public Profile getOwnerProfile() {
        Object obj = getDepVal(((MetaclassReferenceSmClass)getClassOf()).getOwnerProfileDep());
        return (obj instanceof Profile)? (Profile)obj : null;
    }

    @objid ("0894bb28-aedf-4036-8bf0-13fdc1b043c7")
    @Override
    public void setOwnerProfile(Profile value) {
        appendDepVal(((MetaclassReferenceSmClass)getClassOf()).getOwnerProfileDep(), (SmObjectImpl)value);
    }

    @objid ("2cd76fed-86e8-4480-a830-5462f97771a5")
    @Override
    public EList<TagType> getDefinedTagType() {
        return new SmList<>(this, ((MetaclassReferenceSmClass)getClassOf()).getDefinedTagTypeDep());
    }

    @objid ("85dec7a7-bb41-4d7d-814e-80724c209a93")
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

    @objid ("f62a8a45-9ff4-4147-8384-4daa68389eb9")
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

    @objid ("9fb7ed96-e1a0-43bd-8111-145dc8cbd9bb")
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

    @objid ("7fb85d5f-5526-4643-932b-41ca50b4a61a")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMetaclassReference(this);
    }

    /**
     * Get the name of this MetaclassReference, i.e. the name of the referenced metaclass.
     */
    @objid ("0bfb491e-a229-4b20-971d-a6d4c13e292f")
    @Override
    public String getName() {
        return getReferencedClassName();
    }

}
