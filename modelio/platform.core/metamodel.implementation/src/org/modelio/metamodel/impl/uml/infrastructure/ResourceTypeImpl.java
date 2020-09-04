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

    @objid ("8c142c97-5891-44f4-8f55-314035048cd7")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((ResourceTypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("4ec2dce9-d980-4bd2-86e6-0ca81a5f548f")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("527b9453-00f6-4596-9108-be768806f7a6")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((ResourceTypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("be830649-0967-4510-85be-8cd839a5738f")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("7b113a90-8062-403a-8871-eb010b281f6f")
    @Override
    public String getIcon() {
        return (String) getAttVal(((ResourceTypeSmClass)getClassOf()).getIconAtt());
    }

    @objid ("96986cbe-4e60-4baa-9961-750bd3fc979e")
    @Override
    public void setIcon(String value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getIconAtt(), value);
    }

    @objid ("9f976af1-ba77-4541-a69e-6bc235859076")
    @Override
    public String getImage() {
        return (String) getAttVal(((ResourceTypeSmClass)getClassOf()).getImageAtt());
    }

    @objid ("c033145f-7a20-4467-b5cb-9de20e9c5fa3")
    @Override
    public void setImage(String value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getImageAtt(), value);
    }

    @objid ("c4d40464-2775-4fd2-94db-503a1b5259c2")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("7ca9a0ad-df1a-4986-978e-32c8b29448aa")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("23dbd2b8-0114-40a6-9598-afb4af070075")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("b63f3fc2-1674-4e70-ace3-4316451534e3")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("14082700-b1bf-499d-9e4d-c63117e2dd74")
    @Override
    public EList<AbstractResource> getTypedResource() {
        return new SmList<>(this, ((ResourceTypeSmClass)getClassOf()).getTypedResourceDep());
    }

    @objid ("c3890d14-8ba3-4d3c-9fc4-90a5ffe78759")
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

    @objid ("9d89a131-4a2e-438a-8484-b2baadbb4b29")
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

    @objid ("fe4ccc56-54b2-411f-bfb2-259a02968959")
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

    @objid ("3665b5ae-a78d-46ef-ac82-0d5c7c2d4089")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitResourceType(this);
    }

}
