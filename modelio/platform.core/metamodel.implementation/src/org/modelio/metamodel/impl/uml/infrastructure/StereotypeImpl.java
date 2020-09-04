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
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
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

    @objid ("565d89fe-762d-4a51-980b-38262f10a65b")
    @Override
    public String getImage() {
        return (String) getAttVal(((StereotypeSmClass) getClassOf()).getImageAtt());
    }

    @objid ("a33beb5a-ab02-4af1-9a3f-a97cda853c23")
    @Override
    public void setImage(String value) {
        setAttVal(((StereotypeSmClass) getClassOf()).getImageAtt(), value);
    }

    @objid ("363b11a8-7958-4543-b08e-ae0e58aec94a")
    @Override
    public String getIcon() {
        return (String) getAttVal(((StereotypeSmClass) getClassOf()).getIconAtt());
    }

    @objid ("c7f912ef-cc10-4468-9622-4d2270892b58")
    @Override
    public void setIcon(String value) {
        setAttVal(((StereotypeSmClass) getClassOf()).getIconAtt(), value);
    }

    @objid ("258f7c0e-f77c-4f6a-9701-64cd6ebddb9e")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((StereotypeSmClass) getClassOf()).getIsHiddenAtt());
    }

    @objid ("a17f0d1d-164a-4508-987c-e7c769f63073")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((StereotypeSmClass) getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("3677e7f4-a350-46c0-9116-14db6fa4a179")
    @Override
    public boolean isIsAbstract() {
        return (Boolean) getAttVal(((StereotypeSmClass) getClassOf()).getIsAbstractAtt());
    }

    @objid ("867ad318-4811-4c40-a466-9dec4feeb433")
    @Override
    public void setIsAbstract(boolean value) {
        setAttVal(((StereotypeSmClass) getClassOf()).getIsAbstractAtt(), value);
    }

    @objid ("5ff86cce-ceb6-4b6a-a7e0-ba8d3e7f7a0d")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((StereotypeSmClass) getClassOf()).getLabelKeyAtt());
    }

    @objid ("0f537767-d8b0-4b71-abc8-d3628da75587")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((StereotypeSmClass) getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("d00537e0-ec32-4e5a-b753-63c88d966e64")
    @Override
    public String getBaseClassName() {
        return (String) getAttVal(((StereotypeSmClass) getClassOf()).getBaseClassNameAtt());
    }

    @objid ("dece1886-e87a-4156-b2ca-a12c42b9882c")
    @Override
    public void setBaseClassName(String value) {
        setAttVal(((StereotypeSmClass) getClassOf()).getBaseClassNameAtt(), value);
    }

    @objid ("5ecc919a-bf44-49f5-aecc-212478aad900")
    @Override
    public PropertyTableDefinition getDefinedTable() {
        Object obj = getDepVal(((StereotypeSmClass) getClassOf()).getDefinedTableDep());
        return (obj instanceof PropertyTableDefinition) ? (PropertyTableDefinition) obj : null;
    }

    @objid ("6015934b-2051-4f42-b54c-adaebcb97283")
    @Override
    public void setDefinedTable(PropertyTableDefinition value) {
        appendDepVal(((StereotypeSmClass) getClassOf()).getDefinedTableDep(), (SmObjectImpl) value);
    }

    @objid ("97eee87f-e264-4a6b-8c1e-5d288fee1eb7")
    @Override
    public EList<ResourceType> getDefinedResourceType() {
        return new SmList<>(this, ((StereotypeSmClass) getClassOf()).getDefinedResourceTypeDep());
    }

    @objid ("d633d5d5-0ba7-4290-9c92-655358eb900b")
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

    @objid ("bb19f2d8-68a0-448e-a6c4-511178266cfa")
    @Override
    public Profile getOwner() {
        Object obj = getDepVal(((StereotypeSmClass) getClassOf()).getOwnerDep());
        return (obj instanceof Profile) ? (Profile) obj : null;
    }

    @objid ("7d63e70c-9f96-4e2d-a7b6-fc5622cb744b")
    @Override
    public void setOwner(Profile value) {
        appendDepVal(((StereotypeSmClass) getClassOf()).getOwnerDep(), (SmObjectImpl) value);
    }

    @objid ("c028c2e7-cc28-4e97-a706-931060cfac85")
    @Override
    public Stereotype getParent() {
        Object obj = getDepVal(((StereotypeSmClass) getClassOf()).getParentDep());
        return (obj instanceof Stereotype) ? (Stereotype) obj : null;
    }

    @objid ("bb8ed681-0663-4f82-a5e6-68e7e49f7ced")
    @Override
    public void setParent(Stereotype value) {
        appendDepVal(((StereotypeSmClass) getClassOf()).getParentDep(), (SmObjectImpl) value);
    }

    @objid ("28eb2d56-687a-4961-9358-e8f08a096c62")
    @Override
    public EList<TagType> getDefinedTagType() {
        return new SmList<>(this, ((StereotypeSmClass) getClassOf()).getDefinedTagTypeDep());
    }

    @objid ("b6e2e02c-2c67-45bd-ba58-c311d20b96f8")
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

    @objid ("4f8c05e7-e420-4e49-a014-d740db09076e")
    @Override
    public EList<Stereotype> getChild() {
        return new SmList<>(this, ((StereotypeSmClass) getClassOf()).getChildDep());
    }

    @objid ("0d56e342-990b-4d1f-8923-28c0f279790c")
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

    @objid ("c93bdbcd-acf4-4d06-bc90-58f3ca7d2778")
    @Override
    public EList<NoteType> getDefinedNoteType() {
        return new SmList<>(this, ((StereotypeSmClass) getClassOf()).getDefinedNoteTypeDep());
    }

    @objid ("42423380-7b9d-4534-abc0-01817509b434")
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

    @objid ("668656ff-16d2-47ac-8319-ec8c901f3ad9")
    @Override
    public EList<ModelElement> getExtendedElement() {
        return new SmList<>(this, ((StereotypeSmClass) getClassOf()).getExtendedElementDep());
    }

    @objid ("ff925ec6-ef12-4f17-80c3-2fb90525da6d")
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

    @objid ("77cdb5b7-ca7f-4847-95aa-49e0936d5af5")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl) this.getDepVal(((StereotypeSmClass) getClassOf()).getOwnerDep());
        if (obj != null) {
            return obj;
        }
        return super.getCompositionOwner();
    }

    @objid ("5db6411c-3627-4a19-8782-77c89e823f7d")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((StereotypeSmClass) getClassOf()).getOwnerDep();
        obj = (SmObjectImpl) this.getDepVal(dep);
        if (obj != null) {
            return new SmDepVal(dep, obj);
        }
        return super.getCompositionRelation();
    }

    @objid ("f28264ad-8050-4a5d-99f4-cd72f3bbbe68")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitStereotype(this);
    }

}
