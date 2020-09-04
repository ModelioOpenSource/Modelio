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
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableDefinitionData;
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

    @objid ("392460fe-94e7-44f1-be54-932a9a59abfc")
    @Override
    public EList<TypedPropertyTable> getTypedTable() {
        return new SmList<>(this, ((PropertyTableDefinitionSmClass)getClassOf()).getTypedTableDep());
    }

    @objid ("3f11eec1-6f8b-493d-8ad0-cd0a1a25c647")
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

    @objid ("e8f2704e-3978-4808-aa27-56d93d43ce8c")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("632f04ae-e28c-45d7-9cfc-b51eb03f8acd")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("32cc426b-5f3d-4f43-bc52-a96adb8c148e")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("26e1ce6a-6a86-4abe-b7a1-45e04b81d655")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("b8e1532e-04f8-4b3d-8066-c6f95fed9b69")
    @Override
    public EList<PropertyDefinition> getOwned() {
        return new SmList<>(this, ((PropertyTableDefinitionSmClass)getClassOf()).getOwnedDep());
    }

    @objid ("90d2621c-5063-4c70-9f19-ce8bd899524a")
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

    @objid ("5eb7e7df-5520-4b3c-8248-81cf6b8acabf")
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

    @objid ("c0d8394d-e9e8-4a96-b16c-947f919acf2f")
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

    @objid ("65ff0ee4-50e6-4447-9f23-3b27f890d35b")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyTableDefinition(this);
    }

}
