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
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00903bfc-c4be-1fd8-97fe-001ec947cd2a")
public class TaggedValueImpl extends ModelElementImpl implements TaggedValue {
    @objid ("ec5e1353-f46d-473f-a365-8aca88ccbd84")
    @Override
    public EList<TagParameter> getActual() {
        return new SmList<>(this, ((TaggedValueSmClass)getClassOf()).getActualDep());
    }

    @objid ("e3203144-d6e7-4dc8-b8dd-091cc101f844")
    @Override
    public <T extends TagParameter> List<T> getActual(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TagParameter element : getActual()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("c3379fc0-222c-4f0a-966e-e43b32199520")
    @Override
    public TagParameter getQualifier() {
        Object obj = getDepVal(((TaggedValueSmClass)getClassOf()).getQualifierDep());
        return (obj instanceof TagParameter)? (TagParameter)obj : null;
    }

    @objid ("f16b895a-b49f-436b-8f56-987c52a58e2f")
    @Override
    public void setQualifier(TagParameter value) {
        appendDepVal(((TaggedValueSmClass)getClassOf()).getQualifierDep(), (SmObjectImpl)value);
    }

    @objid ("7d9b66b8-e17a-4bb6-be28-e8abcb00a825")
    @Override
    public TagType getDefinition() {
        Object obj = getDepVal(((TaggedValueSmClass)getClassOf()).getDefinitionDep());
        return (obj instanceof TagType)? (TagType)obj : null;
    }

    @objid ("5f694bf9-4076-44b8-8023-b260dd504921")
    @Override
    public void setDefinition(TagType value) {
        appendDepVal(((TaggedValueSmClass)getClassOf()).getDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("34fabe64-3f7a-4627-98af-d30bf811ebf0")
    @Override
    public ModelElement getAnnoted() {
        Object obj = getDepVal(((TaggedValueSmClass)getClassOf()).getAnnotedDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("af72112c-6d34-47d5-ad57-14e1d91a23bb")
    @Override
    public void setAnnoted(ModelElement value) {
        appendDepVal(((TaggedValueSmClass)getClassOf()).getAnnotedDep(), (SmObjectImpl)value);
    }

    @objid ("df8c5798-4fab-4be8-8617-9f5ff67a963f")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Annoted
        obj = (SmObjectImpl)this.getDepVal(((TaggedValueSmClass)getClassOf()).getAnnotedDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("7e330eb8-d86f-4663-9e2c-4f90afde1f9c")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Annoted
        dep = ((TaggedValueSmClass)getClassOf()).getAnnotedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("43006c70-398b-458b-a6c2-900e92ce193b")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTaggedValue(this);
    }

}
