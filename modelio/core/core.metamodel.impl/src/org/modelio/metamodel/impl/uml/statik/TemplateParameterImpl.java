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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterData;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("001d5f2e-c4bf-1fd8-97fe-001ec947cd2a")
public class TemplateParameterImpl extends GeneralClassImpl implements TemplateParameter {
    @objid ("4ba42900-4d08-4bdb-bf75-cbc440326828")
    @Override
    public String getDefaultValue() {
        return (String) getAttVal(((TemplateParameterSmClass)getClassOf()).getDefaultValueAtt());
    }

    @objid ("645f7976-704c-4a48-846c-1ead64a95c05")
    @Override
    public void setDefaultValue(String value) {
        setAttVal(((TemplateParameterSmClass)getClassOf()).getDefaultValueAtt(), value);
    }

    @objid ("4965077c-b9f5-4f95-90e8-06b8951f3b70")
    @Override
    public boolean isIsValueParameter() {
        return (Boolean) getAttVal(((TemplateParameterSmClass)getClassOf()).getIsValueParameterAtt());
    }

    @objid ("e1640e53-7344-41d1-8548-258a49e708ba")
    @Override
    public void setIsValueParameter(boolean value) {
        setAttVal(((TemplateParameterSmClass)getClassOf()).getIsValueParameterAtt(), value);
    }

    @objid ("01eeb56a-c312-4807-ae15-3c2c9ecc5e62")
    @Override
    public EList<TemplateParameterSubstitution> getParameterSubstitution() {
        return new SmList<>(this, ((TemplateParameterSmClass)getClassOf()).getParameterSubstitutionDep());
    }

    @objid ("7ffdeb67-9f5d-479f-a7d4-f1eaab3ff16e")
    @Override
    public <T extends TemplateParameterSubstitution> List<T> getParameterSubstitution(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final TemplateParameterSubstitution element : getParameterSubstitution()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("d224a4c4-8aba-491f-9d6e-7b0a2e06f6e8")
    @Override
    public UmlModelElement getType() {
        Object obj = getDepVal(((TemplateParameterSmClass)getClassOf()).getTypeDep());
        return (obj instanceof UmlModelElement)? (UmlModelElement)obj : null;
    }

    @objid ("11b118c2-1ffd-4f73-a497-35065159018c")
    @Override
    public void setType(UmlModelElement value) {
        appendDepVal(((TemplateParameterSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("12961e97-704a-4ae3-a122-07b439f3fff9")
    @Override
    public NameSpace getParameterized() {
        Object obj = getDepVal(((TemplateParameterSmClass)getClassOf()).getParameterizedDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("a29b7ecd-78ce-4662-a4e8-2cb27ebc44ef")
    @Override
    public void setParameterized(NameSpace value) {
        appendDepVal(((TemplateParameterSmClass)getClassOf()).getParameterizedDep(), (SmObjectImpl)value);
    }

    @objid ("372d6204-e57c-4525-888f-afd6c5ba4f2c")
    @Override
    public UmlModelElement getOwnedParameterElement() {
        Object obj = getDepVal(((TemplateParameterSmClass)getClassOf()).getOwnedParameterElementDep());
        return (obj instanceof UmlModelElement)? (UmlModelElement)obj : null;
    }

    @objid ("2bb0dbfb-8075-4c2d-a403-6b70beb8433d")
    @Override
    public void setOwnedParameterElement(UmlModelElement value) {
        appendDepVal(((TemplateParameterSmClass)getClassOf()).getOwnedParameterElementDep(), (SmObjectImpl)value);
    }

    @objid ("3e4db786-6700-4478-bb58-1978dfd22d7c")
    @Override
    public UmlModelElement getDefaultType() {
        Object obj = getDepVal(((TemplateParameterSmClass)getClassOf()).getDefaultTypeDep());
        return (obj instanceof UmlModelElement)? (UmlModelElement)obj : null;
    }

    @objid ("b47e1703-df3f-435c-9c3a-adfd9ba51d66")
    @Override
    public void setDefaultType(UmlModelElement value) {
        appendDepVal(((TemplateParameterSmClass)getClassOf()).getDefaultTypeDep(), (SmObjectImpl)value);
    }

    @objid ("38666b40-ae05-4d47-9c2c-6f000649eeb9")
    @Override
    public Operation getParameterizedOperation() {
        Object obj = getDepVal(((TemplateParameterSmClass)getClassOf()).getParameterizedOperationDep());
        return (obj instanceof Operation)? (Operation)obj : null;
    }

    @objid ("3392fc77-1b39-4708-b919-f0396713871e")
    @Override
    public void setParameterizedOperation(Operation value) {
        appendDepVal(((TemplateParameterSmClass)getClassOf()).getParameterizedOperationDep(), (SmObjectImpl)value);
    }

    @objid ("019e0506-f27f-44a7-bf3c-1f5169f000a4")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Parameterized
        obj = (SmObjectImpl)this.getDepVal(((TemplateParameterSmClass)getClassOf()).getParameterizedDep());
        if (obj != null)
          return obj;
        // ParameterizedOperation
        obj = (SmObjectImpl)this.getDepVal(((TemplateParameterSmClass)getClassOf()).getParameterizedOperationDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("8d10822d-d3a0-43e4-8e24-665ff774d227")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Parameterized
        dep = ((TemplateParameterSmClass)getClassOf()).getParameterizedDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // ParameterizedOperation
        dep = ((TemplateParameterSmClass)getClassOf()).getParameterizedOperationDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("bb02cf34-76a1-4ede-832c-cbd72955cb0d")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitTemplateParameter(this);
    }

}
