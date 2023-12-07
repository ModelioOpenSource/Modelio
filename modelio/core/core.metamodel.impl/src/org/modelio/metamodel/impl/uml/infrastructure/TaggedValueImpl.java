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
    @objid ("59842a77-dd6e-4b49-8534-2b6928adc9db")
    @Override
    public EList<TagParameter> getActual() {
        return new SmList<>(this, ((TaggedValueSmClass)getClassOf()).getActualDep());
    }

    @objid ("e23d3a35-1b79-49fc-a095-6fe15355622f")
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

    @objid ("b39f008d-a57a-45b0-830a-8af9bbfbde9d")
    @Override
    public TagParameter getQualifier() {
        Object obj = getDepVal(((TaggedValueSmClass)getClassOf()).getQualifierDep());
        return (obj instanceof TagParameter)? (TagParameter)obj : null;
    }

    @objid ("4b6a5b38-b796-489f-98ff-06071dd1c4e1")
    @Override
    public void setQualifier(TagParameter value) {
        appendDepVal(((TaggedValueSmClass)getClassOf()).getQualifierDep(), (SmObjectImpl)value);
    }

    @objid ("9d70c644-f46a-4b1f-9bbe-d71a949b88d2")
    @Override
    public TagType getDefinition() {
        Object obj = getDepVal(((TaggedValueSmClass)getClassOf()).getDefinitionDep());
        return (obj instanceof TagType)? (TagType)obj : null;
    }

    @objid ("81e47f2f-17b8-43c9-832a-4d123e095085")
    @Override
    public void setDefinition(TagType value) {
        appendDepVal(((TaggedValueSmClass)getClassOf()).getDefinitionDep(), (SmObjectImpl)value);
    }

    @objid ("d6469513-221b-4ec1-8acd-867df27cfc1d")
    @Override
    public ModelElement getAnnoted() {
        Object obj = getDepVal(((TaggedValueSmClass)getClassOf()).getAnnotedDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("429c3ecb-939f-453b-8205-f9a7591a4dfa")
    @Override
    public void setAnnoted(ModelElement value) {
        appendDepVal(((TaggedValueSmClass)getClassOf()).getAnnotedDep(), (SmObjectImpl)value);
    }

    @objid ("4421298d-e706-4fa7-9c08-00a9893b4bc8")
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

    @objid ("9bb6d6da-3e1e-49d3-8138-53e94711203c")
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

    @objid ("4351a165-5c00-452e-b2b8-e1eff9a8ba49")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTaggedValue(this);
    }

}
