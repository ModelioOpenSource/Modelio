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
    @objid ("01859b5e-1a07-47aa-9c32-854871cce3a3")
    @Override
    public EList<Stereotype> getDefinedStereotype() {
        return new SmList<>(this, ((ProfileSmClass)getClassOf()).getDefinedStereotypeDep());
    }

    @objid ("4dbca0d1-de4f-4fa5-8d55-2107305bb3c2")
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

    @objid ("66e44894-9153-4d95-b675-1027197fb6b3")
    @Override
    public EList<MetaclassReference> getOwnedReference() {
        return new SmList<>(this, ((ProfileSmClass)getClassOf()).getOwnedReferenceDep());
    }

    @objid ("3982a2ee-1c2e-4782-a0a9-ba7225157438")
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

    @objid ("afae90b1-9c2f-4fe8-a8b9-7dca1dae3088")
    @Override
    public ModuleComponent getOwnerModule() {
        Object obj = getDepVal(((ProfileSmClass)getClassOf()).getOwnerModuleDep());
        return (obj instanceof ModuleComponent)? (ModuleComponent)obj : null;
    }

    @objid ("8dfb11b0-662e-4e2e-b579-3dbc81b86bf4")
    @Override
    public void setOwnerModule(ModuleComponent value) {
        appendDepVal(((ProfileSmClass)getClassOf()).getOwnerModuleDep(), (SmObjectImpl)value);
    }

    @objid ("fba37ff0-c554-433e-9a84-1b3ed44cc20b")
    @Override
    public EList<PropertyType> getDefinedType() {
        return new SmList<>(this, ((ProfileSmClass)getClassOf()).getDefinedTypeDep());
    }

    @objid ("ca3f4f97-9612-4471-830e-96566d01e2bd")
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

    @objid ("38953ad6-58e4-4c2e-a2a4-11adc6465e26")
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

    @objid ("e3c9a21e-95fc-4868-b62d-fe5905433303")
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

    @objid ("a52d042e-650e-4c4e-90a1-43d0dbd3652a")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitProfile(this);
    }

}
