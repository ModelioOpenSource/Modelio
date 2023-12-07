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
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("008caa50-c4be-1fd8-97fe-001ec947cd2a")
public class ProfileImpl extends ModelElementImpl implements Profile {
    @objid ("013beb8a-af98-4bd3-8a8d-424d9b778679")
    @Override
    public EList<Stereotype> getDefinedStereotype() {
        return new SmList<>(this, ((ProfileSmClass)getClassOf()).getDefinedStereotypeDep());
    }

    @objid ("00e06fa2-fdf4-4779-bd0b-7fc2e19410d5")
    @Override
    public <T extends Stereotype> List<T> getDefinedStereotype(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Stereotype element : getDefinedStereotype()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("10be11a5-889c-4617-a34f-c7041c6a0318")
    @Override
    public EList<MetaclassReference> getOwnedReference() {
        return new SmList<>(this, ((ProfileSmClass)getClassOf()).getOwnedReferenceDep());
    }

    @objid ("753792a4-d2bc-4698-b12b-2ad6fd42011c")
    @Override
    public <T extends MetaclassReference> List<T> getOwnedReference(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final MetaclassReference element : getOwnedReference()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("69ff308a-82b8-4bb6-be4e-e2a76d298b08")
    @Override
    public ModuleComponent getOwnerModule() {
        Object obj = getDepVal(((ProfileSmClass)getClassOf()).getOwnerModuleDep());
        return (obj instanceof ModuleComponent)? (ModuleComponent)obj : null;
    }

    @objid ("cfd541b0-074d-47ed-bc06-1be71fbb0be3")
    @Override
    public void setOwnerModule(ModuleComponent value) {
        appendDepVal(((ProfileSmClass)getClassOf()).getOwnerModuleDep(), (SmObjectImpl)value);
    }

    @objid ("17856d93-2f3c-464e-9b8d-983aaf01122a")
    @Override
    public EList<PropertyType> getDefinedType() {
        return new SmList<>(this, ((ProfileSmClass)getClassOf()).getDefinedTypeDep());
    }

    @objid ("11b3fb95-f9c0-44b7-b1d2-6516f2455aa6")
    @Override
    public <T extends PropertyType> List<T> getDefinedType(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final PropertyType element : getDefinedType()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("6e6e690f-e89a-40a9-afc4-568d4132b1ef")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerModule
        obj = (SmObjectImpl)this.getDepVal(((ProfileSmClass)getClassOf()).getOwnerModuleDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("04ce84a9-619e-4766-991a-c9f5158ee55b")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerModule
        dep = ((ProfileSmClass)getClassOf()).getOwnerModuleDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("05b9f97a-e942-42d9-b126-a71a70231e5c")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitProfile(this);
    }

}
