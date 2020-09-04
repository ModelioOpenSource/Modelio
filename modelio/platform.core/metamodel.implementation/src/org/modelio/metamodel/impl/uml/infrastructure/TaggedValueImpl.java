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
import org.modelio.metamodel.impl.uml.infrastructure.TaggedValueData;
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
    @objid ("f100abdc-dbb3-4633-a162-8369b1334323")
    @Override
    public EList<TagParameter> getActual() {
        return new SmList<>(this, ((TaggedValueSmClass)getClassOf()).getActualDep());
    }

    @objid ("f90b2d82-0249-4192-ae81-101d9b5aa8c8")
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

    @objid ("ee8150d4-e22d-4bc2-b611-04f6e02e6fad")
    @Override
    public TagParameter getQualifier() {
        Object obj = getDepVal(((TaggedValueSmClass)getClassOf()).getQualifierDep());
        return (obj instanceof TagParameter)? (TagParameter)obj : null;
    }

    @objid ("40f9368d-191a-42d9-8a4f-61200609e008")
    @Override
    public void setQualifier(TagParameter value) {
        appendDepVal(((TaggedValueSmClass)getClassOf()).getQualifierDep(), (SmObjectImpl)value);
    }

    @objid ("001ae712-6f48-400a-9014-f1c63e939dbb")
    @Override
    public TagType getDefinition() {
        Object obj = getDepVal(((TaggedValueSmClass)getClassOf()).getDefinitionDep());
        return (obj instanceof TagType)? (TagType)obj : null;
    }

    @objid ("b327fd1e-d4f6-4ec6-983b-7f389ed94a17")
    @Override
    public void setDefinition(TagType value) {
        appendDepVal(((TaggedValueSmClass)getClassOf()).getDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("5c283393-113a-4015-8af9-216b31ac187d")
    @Override
    public ModelElement getAnnoted() {
        Object obj = getDepVal(((TaggedValueSmClass)getClassOf()).getAnnotedDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("e6be2fca-9fc8-4bd8-83ee-36ba1d340147")
    @Override
    public void setAnnoted(ModelElement value) {
        appendDepVal(((TaggedValueSmClass)getClassOf()).getAnnotedDep(), (SmObjectImpl)value);
    }

    @objid ("cf53676e-4ed7-482e-808d-a301605930bf")
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

    @objid ("b1780354-e83d-424a-bd25-62337b87f8bf")
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

    @objid ("71fb06ef-3a3b-4b8f-9902-507ad562cda9")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTaggedValue(this);
    }

}
