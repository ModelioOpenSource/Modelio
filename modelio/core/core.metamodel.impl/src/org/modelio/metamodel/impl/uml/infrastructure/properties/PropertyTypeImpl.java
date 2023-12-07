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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
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
    @objid ("16cf33f2-032d-4fcb-8e08-46e2373d4dc1")
    @Override
    public PropertyBaseType getBaseType() {
        return (PropertyBaseType) getAttVal(((PropertyTypeSmClass)getClassOf()).getBaseTypeAtt());
    }

    @objid ("f4ccc21d-50f9-4758-924f-3a5b79881e1a")
    @Override
    public void setBaseType(PropertyBaseType value) {
        setAttVal(((PropertyTypeSmClass)getClassOf()).getBaseTypeAtt(), value);
    }

    @objid ("721623c8-b3e9-4593-89d9-707f0e467483")
    @Override
    public ModuleComponent getModuleOwner() {
        Object obj = getDepVal(((PropertyTypeSmClass)getClassOf()).getModuleOwnerDep());
        return (obj instanceof ModuleComponent)? (ModuleComponent)obj : null;
    }

    @objid ("3ca5c60c-27aa-4ac1-9162-9a4b1d0a32f4")
    @Override
    public void setModuleOwner(ModuleComponent value) {
        appendDepVal(((PropertyTypeSmClass)getClassOf()).getModuleOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("4f59809d-a4a5-4312-9a96-40083d9d45be")
    @Override
    public EList<PropertyDefinition> getTyped() {
        return new SmList<>(this, ((PropertyTypeSmClass)getClassOf()).getTypedDep());
    }

    @objid ("1b0168e0-2e6a-4991-81c5-f6c404cbbe6f")
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

    @objid ("fa937874-da1a-4cd5-bab4-f98947c0fb65")
    @Override
    public Profile getAnalystOwner() {
        Object obj = getDepVal(((PropertyTypeSmClass)getClassOf()).getAnalystOwnerDep());
        return (obj instanceof Profile)? (Profile)obj : null;
    }

    @objid ("d83ff2b2-429d-4fc2-8b82-dc292345ca04")
    @Override
    public void setAnalystOwner(Profile value) {
        appendDepVal(((PropertyTypeSmClass)getClassOf()).getAnalystOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("ae35135e-6e58-4475-8593-ad01e9522723")
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

    @objid ("5b115fab-b59d-4a93-8955-411d6b06012c")
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

    @objid ("4cfd27e6-be5f-4480-ad6a-6cdbf90b03f6")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyType(this);
    }

}
