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

package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0067251e-ec87-1098-b22e-001ec947cd2a")
public class PropertyTableDefinitionImpl extends ModelElementImpl implements PropertyTableDefinition {
    @objid ("3c15be4c-a379-4451-bbb3-d2fd764d4819")
    private Map<String, PropertyDefinition> pDefsCache = new HashMap<>();

    @objid ("53e1d3c8-db3c-41a7-bd71-983865f30672")
    @Override
    public PropertyDefinition getOwned(String propName) {
        PropertyDefinition pDef = this.pDefsCache.get(propName);
        if (pDef == null) {
            for (PropertyDefinition p : getOwned()) {
                if (p.getName().equals(propName)) {
                    pDef = p;
                    this.pDefsCache.put(propName, p);
                }
            }
        }
        return pDef;
    }

    @objid ("a4aa97a8-8163-47ae-bc75-46d46c2d8a55")
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

    @objid ("de95edb4-3810-4a33-a05a-5fa6d81e1f82")
    @Override
    public EList<TypedPropertyTable> getTypedTable() {
        return new SmList<>(this, ((PropertyTableDefinitionSmClass)getClassOf()).getTypedTableDep());
    }

    @objid ("44b6d08a-c094-4adb-bc67-3eb44d2f2c57")
    @Override
    public <T extends TypedPropertyTable> List<T> getTypedTable(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TypedPropertyTable element : getTypedTable()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("0b930b94-0e18-4f79-bce9-6e8345f8c864")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("b402a139-b5c6-4005-8a92-acb473480368")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("46e1e02e-852d-4a21-ba0d-d016876594c4")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("4057fda5-7f3a-4916-963f-6e3eb3e80921")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("1d91d189-8982-4dde-9efd-df19c5073c48")
    @Override
    public EList<PropertyDefinition> getOwned() {
        return new SmList<>(this, ((PropertyTableDefinitionSmClass)getClassOf()).getOwnedDep());
    }

    @objid ("2e58e7d2-9a6c-4be8-8712-0c747655fbd6")
    @Override
    public <T extends PropertyDefinition> List<T> getOwned(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final PropertyDefinition element : getOwned()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("9065c2ba-5257-4a67-9715-3602dd2fc2bd")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerReference
        obj = (SmObjectImpl)this.getDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerReferenceDep());
        if (obj != null)
          return obj;
        // OwnerStereotype
        obj = (SmObjectImpl)this.getDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerStereotypeDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("13d9cb91-97e5-472b-bdc9-06a69fea4693")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerReference
        dep = ((PropertyTableDefinitionSmClass)getClassOf()).getOwnerReferenceDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerStereotype
        dep = ((PropertyTableDefinitionSmClass)getClassOf()).getOwnerStereotypeDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("aa78ee6f-46e5-4d96-a9c1-df391433ff1d")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyTableDefinition(this);
    }

}
