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

    @objid ("26e4bf8f-9f1c-4251-a726-d0d962f50be2")
    @Override
    public String getParamNumber() {
        return (String) getAttVal(((TagTypeSmClass)getClassOf()).getParamNumberAtt());
    }

    @objid ("fb67e4ca-6096-42fc-ace8-92cadbae7a9c")
    @Override
    public void setParamNumber(String value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getParamNumberAtt(), value);
    }

    @objid ("d9544eb7-9d6b-4d79-90ff-a6a0e7d16036")
    @Override
    public boolean isIsQualified() {
        return (Boolean) getAttVal(((TagTypeSmClass)getClassOf()).getIsQualifiedAtt());
    }

    @objid ("f78ceb2b-4e8f-40e0-8b32-503e16ea80ba")
    @Override
    public void setIsQualified(boolean value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getIsQualifiedAtt(), value);
    }

    @objid ("f095fd7c-4c97-473c-830e-3e2fc3831b13")
    @Override
    public boolean isBelongToPrototype() {
        return (Boolean) getAttVal(((TagTypeSmClass)getClassOf()).getBelongToPrototypeAtt());
    }

    @objid ("53adcbcd-a707-45dd-87d8-fac8213ea44e")
    @Override
    public void setBelongToPrototype(boolean value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getBelongToPrototypeAtt(), value);
    }

    @objid ("4ee26b16-5870-40d8-9185-702e7dddf555")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((TagTypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("5d5f0688-77d9-4768-aae3-46cfca6b9cef")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("10c8bc1c-425f-47e7-a7eb-83285ed93bda")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((TagTypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("a0ed9aa0-5e7f-4e71-ba9a-3728677708b1")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("591207ba-ed36-4686-b31a-61c2b9f9f671")
    @Override
    public EList<TaggedValue> getTagOccurence() {
        return new SmList<>(this, ((TagTypeSmClass)getClassOf()).getTagOccurenceDep());
    }

    @objid ("ec59529b-96c1-428f-bbcc-5f69a4daa65c")
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

    @objid ("141d5fb5-0e82-4f89-9724-c174b8813572")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((TagTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("5b3305b5-e14f-4c5e-9581-c5c61313e03e")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((TagTypeSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("37480bcc-22e9-48e3-bc8b-fa481f8f5d91")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((TagTypeSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("17e0bd27-e10b-4f97-af4a-2a658d9fb458")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((TagTypeSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("20d3d438-2563-4230-abf9-f488e5cfacfd")
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

    @objid ("e256d55c-9850-49d3-90c8-664d528069aa")
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

    @objid ("9cdad2ab-cfd4-45c8-b8e5-aa4768a6febf")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTagType(this);
    }

}
