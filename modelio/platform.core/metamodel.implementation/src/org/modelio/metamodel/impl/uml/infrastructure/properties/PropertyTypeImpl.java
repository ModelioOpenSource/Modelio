/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("2e139e49-b45b-4c59-be94-146fc81b0c0d")
    @Override
    public PropertyBaseType getBaseType() {
        return (PropertyBaseType) getAttVal(((PropertyTypeSmClass)getClassOf()).getBaseTypeAtt());
    }

    @objid ("394c6ca2-e848-43c7-b7a4-5edf693aef5a")
    @Override
    public void setBaseType(PropertyBaseType value) {
        setAttVal(((PropertyTypeSmClass)getClassOf()).getBaseTypeAtt(), value);
    }

    @objid ("5c99fa2f-29d3-47fc-b7f3-46d04faeb3c3")
    @Override
    public ModuleComponent getModuleOwner() {
        Object obj = getDepVal(((PropertyTypeSmClass)getClassOf()).getModuleOwnerDep());
        return (obj instanceof ModuleComponent)? (ModuleComponent)obj : null;
    }

    @objid ("4e74276f-787f-4c1a-b447-b167958fdebd")
    @Override
    public void setModuleOwner(ModuleComponent value) {
        appendDepVal(((PropertyTypeSmClass)getClassOf()).getModuleOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("48a84a3e-f33e-43b6-b17a-964c1e444f30")
    @Override
    public EList<PropertyDefinition> getTyped() {
        return new SmList<>(this, ((PropertyTypeSmClass)getClassOf()).getTypedDep());
    }

    @objid ("e2b39496-73c4-4f44-8513-0e6603134ea7")
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

    @objid ("bbec81c4-58dd-4927-a52d-4a3746a5c50b")
    @Override
    public Profile getAnalystOwner() {
        Object obj = getDepVal(((PropertyTypeSmClass)getClassOf()).getAnalystOwnerDep());
        return (obj instanceof Profile)? (Profile)obj : null;
    }

    @objid ("18665e00-f651-45ec-b1f0-65012b91f9dc")
    @Override
    public void setAnalystOwner(Profile value) {
        appendDepVal(((PropertyTypeSmClass)getClassOf()).getAnalystOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("cd9f831b-a39c-4db6-a40a-c5c75eed8007")
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

    @objid ("05a498e8-c468-49cd-abee-add8f3ad7df8")
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

    @objid ("4d001fc4-a653-430d-8eec-dc7473b90cce")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyType(this);
    }

}
