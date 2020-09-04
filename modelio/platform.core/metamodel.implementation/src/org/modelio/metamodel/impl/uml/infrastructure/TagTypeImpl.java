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

    @objid ("859546cc-d94e-4796-b456-4dfaaf550d48")
    @Override
    public String getParamNumber() {
        return (String) getAttVal(((TagTypeSmClass)getClassOf()).getParamNumberAtt());
    }

    @objid ("a2881093-51db-4dd9-a25f-2be86188c54e")
    @Override
    public void setParamNumber(String value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getParamNumberAtt(), value);
    }

    @objid ("b3d2c0c9-e854-4811-abd6-f28d9a8da40b")
    @Override
    public boolean isIsQualified() {
        return (Boolean) getAttVal(((TagTypeSmClass)getClassOf()).getIsQualifiedAtt());
    }

    @objid ("dc7431d8-36bb-4e67-a0b6-0d205bb4ad39")
    @Override
    public void setIsQualified(boolean value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getIsQualifiedAtt(), value);
    }

    @objid ("3f945519-5fda-4c0b-8966-242bed99ad9c")
    @Override
    public boolean isBelongToPrototype() {
        return (Boolean) getAttVal(((TagTypeSmClass)getClassOf()).getBelongToPrototypeAtt());
    }

    @objid ("7b5e7dcc-f413-4878-be36-34d1e6a464c2")
    @Override
    public void setBelongToPrototype(boolean value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getBelongToPrototypeAtt(), value);
    }

    @objid ("edd86b1a-c948-4006-acee-eecc3b32bc19")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((TagTypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("3dfdc836-ffe3-43bc-ad01-dd0b5856f4f7")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("94fbadae-511b-4304-8012-da782d24d5ea")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((TagTypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("eadae9fd-7e16-4874-b2b4-1c083c738f45")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((TagTypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("80cb1423-d2a3-49c3-8d19-9a4330e469ba")
    @Override
    public EList<TaggedValue> getTagOccurence() {
        return new SmList<>(this, ((TagTypeSmClass)getClassOf()).getTagOccurenceDep());
    }

    @objid ("417f4ef3-3efa-4586-81b0-80908ea59e92")
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

    @objid ("d6342c7a-c597-4f05-b172-957413d72001")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((TagTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("b42b8d86-aee6-48ec-97e9-71f3d75cd6bf")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((TagTypeSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("5337bfec-1a9c-41ed-93dd-56d63bd79607")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((TagTypeSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("ab22dd49-d0d2-47af-8721-9c41cfa603cc")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((TagTypeSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("9486ae79-c6b7-4a83-84ef-56578f11b18c")
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

    @objid ("8d3399bd-95ca-4a7a-95e9-c35018a7a67b")
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

    @objid ("0ad706c3-b0bc-465a-92b1-a5eaaaae2a1a")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTagType(this);
    }

}
