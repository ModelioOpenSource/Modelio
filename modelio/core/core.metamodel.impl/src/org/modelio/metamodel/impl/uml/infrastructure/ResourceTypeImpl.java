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
import org.modelio.metamodel.impl.uml.infrastructure.ResourceTypeData;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("009339f6-c4be-1fd8-97fe-001ec947cd2a")
public class ResourceTypeImpl extends ModelElementImpl implements ResourceType {
    @objid ("f9337a44-b3be-45b9-9f22-cc4ed1552af0")
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

    @objid ("e4a4f175-d808-4df4-8e58-7c808a6ba645")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((ResourceTypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("6729365f-ef42-4d52-92d0-3b563546c530")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("2bc7a26a-f17d-4cac-beb4-32372189ffa4")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((ResourceTypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("fc74b76d-6e0f-4b26-ab96-dee9285aab81")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("8f0c6e58-8bd8-41e7-908b-4bef458e0c1e")
    @Override
    public String getIcon() {
        return (String) getAttVal(((ResourceTypeSmClass)getClassOf()).getIconAtt());
    }

    @objid ("9f99e5a1-eb1b-402b-909b-63c68c4c5807")
    @Override
    public void setIcon(String value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getIconAtt(), value);
    }

    @objid ("680a3567-c8ac-41ef-ad6f-a8f4a18a6625")
    @Override
    public String getImage() {
        return (String) getAttVal(((ResourceTypeSmClass)getClassOf()).getImageAtt());
    }

    @objid ("9627febf-0c93-494c-ac9a-d4b304c4c283")
    @Override
    public void setImage(String value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getImageAtt(), value);
    }

    @objid ("fedc25be-d480-43ca-aa22-401a564b20bb")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("5513be31-fddd-49f2-956f-74f68ef27d32")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("583f4324-49bb-491a-b054-cc8fe8c737cd")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("c62876b5-81d2-4a86-8071-2d6cd318550c")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("a55cb3dd-43c2-4120-8879-8960f4f0ac0e")
    @Override
    public EList<AbstractResource> getTypedResource() {
        return new SmList<>(this, ((ResourceTypeSmClass)getClassOf()).getTypedResourceDep());
    }

    @objid ("325f15dc-72b3-45fc-aaac-7d1fcb71ae9b")
    @Override
    public <T extends AbstractResource> List<T> getTypedResource(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final AbstractResource element : getTypedResource()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("1eaaec8e-e229-4157-865e-e1918267bd5d")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerStereotype
        obj = (SmObjectImpl)this.getDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        if (obj != null)
          return obj;
        // OwnerReference
        obj = (SmObjectImpl)this.getDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerReferenceDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("f0d14311-9c39-4801-b3d6-21644b01c77e")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerStereotype
        dep = ((ResourceTypeSmClass)getClassOf()).getOwnerStereotypeDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerReference
        dep = ((ResourceTypeSmClass)getClassOf()).getOwnerReferenceDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("aaeb1fb5-310e-42dc-ab70-b1ae3177d685")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitResourceType(this);
    }

}
