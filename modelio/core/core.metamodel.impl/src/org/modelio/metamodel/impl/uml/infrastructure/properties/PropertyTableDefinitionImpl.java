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

    @objid ("6c69c6fd-4846-4028-b7c5-ffb90f8dd947")
    @Override
    public EList<TypedPropertyTable> getTypedTable() {
        return new SmList<>(this, ((PropertyTableDefinitionSmClass)getClassOf()).getTypedTableDep());
    }

    @objid ("1f77a021-1259-4069-a733-5ebf42b7af38")
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

    @objid ("5326e862-1e97-4f3f-bf0d-6ee67acf8355")
    @Override
    public MetaclassReference getOwnerReference() {
        Object obj = getDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerReferenceDep());
        return (obj instanceof MetaclassReference)? (MetaclassReference)obj : null;
    }

    @objid ("188f7369-6551-47ec-bb0e-eb6e44cc16ef")
    @Override
    public void setOwnerReference(MetaclassReference value) {
        appendDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerReferenceDep(), (SmObjectImpl)value);
    }

    @objid ("e40103f6-21d6-4942-86f3-4ee7e9e79bf4")
    @Override
    public Stereotype getOwnerStereotype() {
        Object obj = getDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerStereotypeDep());
        return (obj instanceof Stereotype)? (Stereotype)obj : null;
    }

    @objid ("4d661679-f6c3-43a3-9a60-bd9aa3f95feb")
    @Override
    public void setOwnerStereotype(Stereotype value) {
        appendDepVal(((PropertyTableDefinitionSmClass)getClassOf()).getOwnerStereotypeDep(), (SmObjectImpl)value);
    }

    @objid ("30777145-32b4-41a6-b456-3821347b3f90")
    @Override
    public EList<PropertyDefinition> getOwned() {
        return new SmList<>(this, ((PropertyTableDefinitionSmClass)getClassOf()).getOwnedDep());
    }

    @objid ("4d099719-4cc9-46da-887a-d2fc5322bb8e")
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

    @objid ("2edf994c-6513-46fb-9342-425c7ee2e03a")
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

    @objid ("dceab729-b69b-41d7-af0d-3ad46acba84c")
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

    @objid ("b6d1573b-88c2-402b-864a-61b1138031c3")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyTableDefinition(this);
    }

}
