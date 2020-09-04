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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTypeData;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyBaseType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("006ef8c0-ec87-1098-b22e-001ec947cd2a")
public class PropertyTypeImpl extends ModelElementImpl implements PropertyType {
    @objid ("9fcb32fb-0cc3-40be-bb94-d95301136cee")
    @Override
    public PropertyBaseType getBaseType() {
        return (PropertyBaseType) getAttVal(((PropertyTypeSmClass)getClassOf()).getBaseTypeAtt());
    }

    @objid ("e698c503-5ce6-4491-b99e-9a0412a8091c")
    @Override
    public void setBaseType(PropertyBaseType value) {
        setAttVal(((PropertyTypeSmClass)getClassOf()).getBaseTypeAtt(), value);
    }

    @objid ("133b1fdd-0c73-4f90-8a0e-c6ba3dc662c2")
    @Override
    public ModuleComponent getModuleOwner() {
        Object obj = getDepVal(((PropertyTypeSmClass)getClassOf()).getModuleOwnerDep());
        return (obj instanceof ModuleComponent)? (ModuleComponent)obj : null;
    }

    @objid ("c43297fc-3cbe-45d1-9e88-c3d9eeb127c7")
    @Override
    public void setModuleOwner(ModuleComponent value) {
        appendDepVal(((PropertyTypeSmClass)getClassOf()).getModuleOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("28bd911d-973b-4361-a8f8-89804236c129")
    @Override
    public EList<PropertyDefinition> getTyped() {
        return new SmList<>(this, ((PropertyTypeSmClass)getClassOf()).getTypedDep());
    }

    @objid ("21b0a6ec-d255-46f9-b527-57095d1ed09c")
    @Override
    public <T extends PropertyDefinition> List<T> getTyped(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final PropertyDefinition element : getTyped()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("83b6859a-cb64-4ac3-98d4-447e14fc9882")
    @Override
    public Profile getAnalystOwner() {
        Object obj = getDepVal(((PropertyTypeSmClass)getClassOf()).getAnalystOwnerDep());
        return (obj instanceof Profile)? (Profile)obj : null;
    }

    @objid ("eceaf357-6233-447a-95e4-f1406c972245")
    @Override
    public void setAnalystOwner(Profile value) {
        appendDepVal(((PropertyTypeSmClass)getClassOf()).getAnalystOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("0455a673-dd08-40f7-8624-7d0de7c3d0ba")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // ModuleOwner
        obj = (SmObjectImpl)this.getDepVal(((PropertyTypeSmClass)getClassOf()).getModuleOwnerDep());
        if (obj != null)
          return obj;
        // AnalystOwner
        obj = (SmObjectImpl)this.getDepVal(((PropertyTypeSmClass)getClassOf()).getAnalystOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("db47c508-031b-4d2d-ae4d-3e0ee5d04fba")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // ModuleOwner
        dep = ((PropertyTypeSmClass)getClassOf()).getModuleOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // AnalystOwner
        dep = ((PropertyTypeSmClass)getClassOf()).getAnalystOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("51cd808c-dc7a-42d8-ac12-5450bc942ea2")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyType(this);
    }

}
