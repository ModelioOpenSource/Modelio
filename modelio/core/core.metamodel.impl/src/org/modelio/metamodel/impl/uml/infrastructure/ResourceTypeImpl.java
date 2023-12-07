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

    @objid ("e205ad99-23a0-4738-8d64-3dea57fde4a1")
    @Override
    public boolean isIsHidden() {
        return (Boolean) getAttVal(((ResourceTypeSmClass)getClassOf()).getIsHiddenAtt());
    }

    @objid ("665383aa-ed93-4d4f-b380-a113e68bf47a")
    @Override
    public void setIsHidden(boolean value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getIsHiddenAtt(), value);
    }

    @objid ("30a25977-d32a-4872-b932-7862ea89d96f")
    @Override
    public String getLabelKey() {
        return (String) getAttVal(((ResourceTypeSmClass)getClassOf()).getLabelKeyAtt());
    }

    @objid ("7414c61c-674c-40df-b521-49c39e59b242")
    @Override
    public void setLabelKey(String value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getLabelKeyAtt(), value);
    }

    @objid ("b83fedb5-603e-4318-84fa-e0e1723125e2")
    @Override
    public String getIcon() {
        return (String) getAttVal(((ResourceTypeSmClass)getClassOf()).getIconAtt());
    }

    @objid ("5d6c8d65-c44e-4d8d-94f0-9d5c216fb8cc")
    @Override
    public void setIcon(String value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getIconAtt(), value);
    }

    @objid ("fe4c7baa-8371-40bc-9827-79c121b51d7b")
    @Override
    public String getImage() {
        return (String) getAttVal(((ResourceTypeSmClass)getClassOf()).getImageAtt());
    }

    @objid ("8db2753f-8475-4916-a9dd-7061820b7aa7")
    @Override
    public void setImage(String value) {
        setAttVal(((ResourceTypeSmClass)getClassOf()).getImageAtt(), value);
    }

    @objid ("2cc09fab-db4f-47fe-9817-425557786ec3")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("73b3b0bb-153c-4ccd-b8c6-fbe962deebbe")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("78e80347-d2db-4592-bd51-9fa34bc9fb06")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("08b834cd-2b45-4b14-9af4-4946e50eaf6a")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((ResourceTypeSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("fede630a-d6a8-4486-b822-6bf791cdc7ce")
    @Override
    public EList<AbstractResource> getTypedResource() {
        return new SmList<>(this, ((ResourceTypeSmClass)getClassOf()).getTypedResourceDep());
    }

    @objid ("72d8767e-cfa3-4ae0-ba85-f29c8d86332c")
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

    @objid ("d57501dd-dac9-4da7-9ff5-7c018dc5d33b")
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

    @objid ("90919825-39c9-462a-8cde-8de474889685")
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

    @objid ("70928f6f-f993-4024-a998-d590b1a51578")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitResourceType(this);
    }

}
