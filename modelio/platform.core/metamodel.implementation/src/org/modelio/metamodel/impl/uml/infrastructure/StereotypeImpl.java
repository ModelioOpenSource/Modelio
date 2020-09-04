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
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.StereotypeData;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
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

@objid ("008d5b58-c4be-1fd8-97fe-001ec947cd2a")
public class StereotypeImpl extends ModelElementImpl implements Stereotype {
    @objid ("b199cee3-7acd-4709-b1c4-bffbf275056d")
    @Override
    public ModuleComponent getModule() {
        final Profile prof = getOwner();
        return (prof != null) ? prof.getOwnerModule() : null;
    }

    @objid ("24d46fcb-d16a-4a11-acf6-6e73483cea1a")
    @Override
    public boolean hasBase(Stereotype parent) {
        Stereotype actualParent = getParent();
        if (parent == null || actualParent == null) {
            return false;
        } else {
            return Objects.equals(actualParent, parent) || actualParent.hasBase(parent);
        }
    }

    @objid ("49737d4a-186f-4704-b4b9-1dbaae0feb67")
    @Override
    public String getImage() {
        return (String) getAttVal(((StereotypeSmClass)getClassOf()).getImageAtt());
    }

    @objid ("13091bd3-e563-497b-a21a-ac1f849f0e3d")
    @Override
    public void setImage(String value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getImageAtt(), value);
    }

    @objid ("7030cb61-081c-4f69-8454-fe53164a2b71")
    @Override
    public String getIcon() {
        return (String) getAttVal(((StereotypeSmClass)getClassOf()).getIconAtt());
    }

    @objid ("aff9acb8-af88-4e09-b647-02be908c9966")
    @Override
    public void setIcon(String value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getIconAtt(), value);
    }

    @objid ("afa59bfd-f1c4-4e78-90ba-0613b4a7632d")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((StereotypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("94d785f1-a8d8-4991-a122-3a0572832ccd")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("6cb6b196-d057-4ea6-9a64-85ddbd4d87f5")
    @Override
    public boolean isIsAbstract() {
        return (Boolean) getAttVal(((StereotypeSmClass)getClassOf()).getIsAbstractAtt());
    }

    @objid ("c748555b-131b-40ec-8cfd-63f63b92a0e9")
    @Override
    public void setIsAbstract(boolean value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getIsAbstractAtt(), value);
    }

    @objid ("dfc3f95a-a8ce-416c-854e-53615654d63c")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((StereotypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("592e3606-aefe-4286-98d2-fa9e98d7dd86")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("ba95f71c-df06-41e7-90a9-43a3cce30358")
    @Override
    public String getBaseClassName() {
        return (String) getAttVal(((StereotypeSmClass)getClassOf()).getBaseClassNameAtt());
    }

    @objid ("111c08e6-163e-4568-9d5a-76835cfda54b")
    @Override
    public void setBaseClassName(String value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getBaseClassNameAtt(), value);
    }

    @objid ("e0b88542-1514-4649-be9c-aab3424149f5")
    @Override
    public PropertyTableDefinition getDefinedTable() {
        Object obj = getDepVal(((StereotypeSmClass)getClassOf()).getDefinedTableDep());
        return (obj instanceof PropertyTableDefinition)? (PropertyTableDefinition)obj : null;
    }

    @objid ("7e864d11-0152-4b50-af0d-2e354c9fd05d")
    @Override
    public void setDefinedTable(PropertyTableDefinition value) {
        appendDepVal(((StereotypeSmClass)getClassOf()).getDefinedTableDep(), (SmObjectImpl)value);
    }

    @objid ("b8f51e15-aac7-4944-836e-6dddcee5d207")
    @Override
    public EList<ResourceType> getDefinedResourceType() {
        return new SmList<>(this, ((StereotypeSmClass)getClassOf()).getDefinedResourceTypeDep());
    }

    @objid ("1317ab98-b1ad-4278-a860-d3bb6ef679a8")
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

    @objid ("4595f7f1-a584-4a8d-9bde-fac8a2f33cfd")
    @Override
    public Profile getOwner() {
        Object obj = getDepVal(((StereotypeSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof Profile)? (Profile)obj : null;
    }

    @objid ("3a2fe24c-2beb-47cf-b216-c18e03315a84")
    @Override
    public void setOwner(Profile value) {
        appendDepVal(((StereotypeSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("3c2b86b2-e58e-4506-a5bb-f649a4ac49c6")
    @Override
    public Stereotype getParent() {
        Object obj = getDepVal(((StereotypeSmClass)getClassOf()).getParentDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("7466810f-ca8e-46bf-bec5-85a2cfb2a8d0")
    @Override
    public void setParent(Stereotype value) {
        appendDepVal(((StereotypeSmClass)getClassOf()).getParentDep(), (SmObjectImpl)value);
    }

    @objid ("cd5bad7e-9463-4135-8edf-6c9e3aa06655")
    @Override
    public EList<TagType> getDefinedTagType() {
        return new SmList<>(this, ((StereotypeSmClass)getClassOf()).getDefinedTagTypeDep());
    }

    @objid ("9be0e5c0-d4a1-44db-ae77-5babfd96b240")
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

    @objid ("7da0ed8d-61af-479a-8394-4bd8d852ec77")
    @Override
    public EList<Stereotype> getChild() {
        return new SmList<>(this, ((StereotypeSmClass)getClassOf()).getChildDep());
    }

    @objid ("7f23655f-898d-4a8b-9fb5-242b694f153c")
    @Override
    public <T extends Stereotype> List<T> getChild(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Stereotype element : getChild()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("8358f2ca-4581-4a0a-92fb-a3cf5acf0685")
    @Override
    public EList<NoteType> getDefinedNoteType() {
        return new SmList<>(this, ((StereotypeSmClass)getClassOf()).getDefinedNoteTypeDep());
    }

    @objid ("124fdd10-8daf-4add-8cca-150b916b7e5a")
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

    @objid ("947016dd-026b-43f8-a26f-2219d07e3ea2")
    @Override
    public EList<ModelElement> getExtendedElement() {
        return new SmList<>(this, ((StereotypeSmClass)getClassOf()).getExtendedElementDep());
    }

    @objid ("80e71607-ae11-4d1c-9ca8-bebe7bab3b28")
    @Override
    public <T extends ModelElement> List<T> getExtendedElement(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ModelElement element : getExtendedElement()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("aa604218-c6c7-49fa-ac1c-b7fdb5d0a79a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((StereotypeSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("0b1d6117-5c77-4d3b-bf24-afa9ee0efe45")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((StereotypeSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("b8731d15-2f74-4b14-b400-faf0f6fc8e08")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitStereotype(this);
    }

}
