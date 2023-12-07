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
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
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

    @objid ("b5fbcb21-fa60-45cc-bee7-f5e8c724edb3")
    @Override
    public String getImage() {
        return (String) getAttVal(((StereotypeSmClass)getClassOf()).getImageAtt());
    }

    @objid ("d1df4643-2036-471a-b2a7-1bcde43b44d6")
    @Override
    public void setImage(String value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getImageAtt(), value);
    }

    @objid ("ab6f783d-cda3-499b-b652-5fb0bd957673")
    @Override
    public String getIcon() {
        return (String) getAttVal(((StereotypeSmClass)getClassOf()).getIconAtt());
    }

    @objid ("25e32110-76ef-4646-b864-e973620702ce")
    @Override
    public void setIcon(String value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getIconAtt(), value);
    }

    @objid ("54223952-0d34-4239-9f2e-f03b2def1016")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((StereotypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("da0bd0ce-1eb5-42a2-8762-10b14e699945")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("28a50b90-74a8-4e9b-8b49-cf1ed5bc545e")
    @Override
    public boolean isIsAbstract() {
        return (Boolean) getAttVal(((StereotypeSmClass)getClassOf()).getIsAbstractAtt());
    }

    @objid ("5fd84bee-55d6-4767-84db-e4e7254ab3d3")
    @Override
    public void setIsAbstract(boolean value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getIsAbstractAtt(), value);
    }

    @objid ("5b1714c9-ccb5-4165-ba09-fdee938050ab")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((StereotypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("727edf7a-08d5-4915-bbcb-99dd37c7d361")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("92451666-0de1-43e3-83e1-28ae14fff8c7")
    @Override
    public String getBaseClassName() {
        return (String) getAttVal(((StereotypeSmClass)getClassOf()).getBaseClassNameAtt());
    }

    @objid ("0f031ba1-879f-4be5-8ccd-4a62bf7dd27f")
    @Override
    public void setBaseClassName(String value) {
        setAttVal(((StereotypeSmClass)getClassOf()).getBaseClassNameAtt(), value);
    }

    @objid ("19de4756-03be-4494-8774-4cb2297912a2")
    @Override
    public PropertyTableDefinition getDefinedTable() {
        Object obj = getDepVal(((StereotypeSmClass)getClassOf()).getDefinedTableDep());
        return (obj instanceof PropertyTableDefinition)? (PropertyTableDefinition)obj : null;
    }

    @objid ("4afdebd7-6006-4d4a-9d76-96bc33bdfe38")
    @Override
    public void setDefinedTable(PropertyTableDefinition value) {
        appendDepVal(((StereotypeSmClass)getClassOf()).getDefinedTableDep(), (SmObjectImpl)value);
    }

    @objid ("adbcc34a-1abc-402d-b5d5-6798c467d3a0")
    @Override
    public EList<ResourceType> getDefinedResourceType() {
        return new SmList<>(this, ((StereotypeSmClass)getClassOf()).getDefinedResourceTypeDep());
    }

    @objid ("ee2983b2-14dc-456d-b1aa-cba8976e3d27")
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

    @objid ("1685699d-69b8-4af8-b83e-afbff65dc6c2")
    @Override
    public Profile getOwner() {
        Object obj = getDepVal(((StereotypeSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof Profile)? (Profile)obj : null;
    }

    @objid ("4fd274ee-ffe6-40fb-9f53-3bca78b674da")
    @Override
    public void setOwner(Profile value) {
        appendDepVal(((StereotypeSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("2153a7ef-1b04-4f89-9c00-526326f5ab11")
    @Override
    public Stereotype getParent() {
        Object obj = getDepVal(((StereotypeSmClass)getClassOf()).getParentDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("c956a330-bb6a-4266-9d58-4d615e59e5df")
    @Override
    public void setParent(Stereotype value) {
        appendDepVal(((StereotypeSmClass)getClassOf()).getParentDep(), (SmObjectImpl)value);
    }

    @objid ("78c608e6-ff68-4c00-b6c0-02b6e5d726f6")
    @Override
    public EList<TagType> getDefinedTagType() {
        return new SmList<>(this, ((StereotypeSmClass)getClassOf()).getDefinedTagTypeDep());
    }

    @objid ("baca2146-60b9-42f5-85a1-eaff79591039")
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

    @objid ("fed24107-ae24-4a38-b3d7-5a6a7427ffdd")
    @Override
    public EList<Stereotype> getChild() {
        return new SmList<>(this, ((StereotypeSmClass)getClassOf()).getChildDep());
    }

    @objid ("e96c5824-bfdc-498a-885e-6afca7a2a05c")
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

    @objid ("52fa4494-c820-4ff4-a4b5-5e6218e7f360")
    @Override
    public EList<NoteType> getDefinedNoteType() {
        return new SmList<>(this, ((StereotypeSmClass)getClassOf()).getDefinedNoteTypeDep());
    }

    @objid ("2f3039cb-d454-4d04-90cf-b3e88dc945cd")
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

    @objid ("741745a1-c013-46bb-92db-b17533374bd1")
    @Override
    public EList<ModelElement> getExtendedElement() {
        return new SmList<>(this, ((StereotypeSmClass)getClassOf()).getExtendedElementDep());
    }

    @objid ("764e24b6-12f4-44d7-9cbb-93fe6e8b1742")
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

    @objid ("f4aa21d8-db65-49ea-bd5e-744bef9c587f")
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

    @objid ("ef65d1c0-9f17-4e4d-b7e0-4b17b2018edb")
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

    @objid ("9c319780-2b53-472e-91c5-ff9e403665ae")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitStereotype(this);
    }

}
