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
import org.modelio.metamodel.impl.uml.infrastructure.TagTypeData;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008f82a2-c4be-1fd8-97fe-001ec947cd2a")
public class TagTypeImpl extends ModelElementImpl implements TagType {
    @objid ("4f878b82-ac7e-4905-8a2e-40fc613813ee")
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

    @objid ("d048387a-e164-48a7-8921-31ddd6c6f124")
    @Override
    public String getParamNumber() {
        return (String) getAttVal(((TagTypeSmClass)getClassOf()).getParamNumberAtt());
    }

    @objid ("56fbe647-bee9-4434-8adb-72405e361d4f")
    @Override
    public void setParamNumber(String value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getParamNumberAtt(), value);
    }

    @objid ("d8c25fcf-d848-4a00-aee1-d05e06ae016e")
    @Override
    public boolean isIsQualified() {
        return (Boolean) getAttVal(((TagTypeSmClass)getClassOf()).getIsQualifiedAtt());
    }

    @objid ("9fb6d97c-1fa7-4325-88aa-e4e4bdd859e7")
    @Override
    public void setIsQualified(boolean value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getIsQualifiedAtt(), value);
    }

    @objid ("2529b8e3-44a0-43e0-878a-827e64542865")
    @Override
    public boolean isBelongToPrototype() {
        return (Boolean) getAttVal(((TagTypeSmClass)getClassOf()).getBelongToPrototypeAtt());
    }

    @objid ("f4e1aa0c-abdd-4c4d-a2e7-42a15cc2dc76")
    @Override
    public void setBelongToPrototype(boolean value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getBelongToPrototypeAtt(), value);
    }

    @objid ("64b9d011-4454-4331-8cb9-40b25b6fe976")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((TagTypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("3520ab75-9b28-4b5e-84da-275b60a09a12")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("4ce34e05-d6ca-4bb2-814f-c78c89660b2e")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((TagTypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("aaa441f8-12d4-4e46-b345-7ec972ed4d0c")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("ad2891d0-21d1-48cb-a171-dfc8363fd432")
    @Override
    public EList<TaggedValue> getTagOccurence() {
        return new SmList<>(this, ((TagTypeSmClass)getClassOf()).getTagOccurenceDep());
    }

    @objid ("f0962b59-0325-48ad-add8-7459d387e4c2")
    @Override
    public <T extends TaggedValue> List<T> getTagOccurence(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TaggedValue element : getTagOccurence()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("e4edba70-cfb0-48bc-8595-c638e0952504")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((TagTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("f84b91cb-7214-4ca0-a8d1-37d02599d615")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((TagTypeSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("1cde5f7b-6cc1-4784-b5e3-ef08a1303a3d")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((TagTypeSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("db974804-8fed-45e7-9c34-93db45f9679e")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((TagTypeSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("15f4c8cf-1cc5-498d-b16f-7958710f4943")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerStereotype
        obj = (SmObjectImpl)this.getDepVal(((TagTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        if (obj != null)
          return obj;
        // OwnerReference
        obj = (SmObjectImpl)this.getDepVal(((TagTypeSmClass)getClassOf()).getOwnerReferenceDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("ff764c7c-cb19-43da-9256-8d9aecf51c04")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerStereotype
        dep = ((TagTypeSmClass)getClassOf()).getOwnerStereotypeDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerReference
        dep = ((TagTypeSmClass)getClassOf()).getOwnerReferenceDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("a5ecc4e1-9428-4a66-89cb-b6ef7a183f52")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTagType(this);
    }

}
