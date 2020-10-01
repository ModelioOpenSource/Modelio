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
import org.modelio.metamodel.impl.uml.infrastructure.ProfileData;
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
    @objid ("e231f443-0f68-477c-9731-be7b98b224b3")
    @Override
    public EList<Stereotype> getDefinedStereotype() {
        return new SmList<>(this, ((ProfileSmClass)getClassOf()).getDefinedStereotypeDep());
    }

    @objid ("28645410-bc25-4c32-a1bb-2765f6d100b9")
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

    @objid ("15d6700e-5a68-4744-8d5e-d7280cbbd9e9")
    @Override
    public EList<MetaclassReference> getOwnedReference() {
        return new SmList<>(this, ((ProfileSmClass)getClassOf()).getOwnedReferenceDep());
    }

    @objid ("a37e8a88-74fc-44ee-ba49-b555633b65fc")
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

    @objid ("d77a3f01-73c2-4289-a8c3-d82d3d081e51")
    @Override
    public ModuleComponent getOwnerModule() {
        Object obj = getDepVal(((ProfileSmClass)getClassOf()).getOwnerModuleDep());
        return (obj instanceof ModuleComponent)? (ModuleComponent)obj : null;
    }

    @objid ("c73327ac-8189-42c6-8c19-7ce891422817")
    @Override
    public void setOwnerModule(ModuleComponent value) {
        appendDepVal(((ProfileSmClass)getClassOf()).getOwnerModuleDep(), (SmObjectImpl)value);
    }

    @objid ("24fdbe37-cf21-4830-bdba-eee250e6ab8e")
    @Override
    public EList<PropertyType> getDefinedType() {
        return new SmList<>(this, ((ProfileSmClass)getClassOf()).getDefinedTypeDep());
    }

    @objid ("51a20b04-47ce-4272-8965-f7fe21aa37c4")
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

    @objid ("0671ab48-d99d-4813-8e06-63edbff20c63")
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

    @objid ("8e1dce1e-4a9f-4271-9102-a6ddc1049a00")
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

    @objid ("a25d00aa-86f7-4449-bad7-e92f59fff0fc")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitProfile(this);
    }

}
