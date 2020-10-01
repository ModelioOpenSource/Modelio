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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterSubstitutionData;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.TemplateBinding;
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

@objid ("001e4902-c4bf-1fd8-97fe-001ec947cd2a")
public class TemplateParameterSubstitutionImpl extends UmlModelElementImpl implements TemplateParameterSubstitution {
    @objid ("b201c9fc-fdd5-4a86-b9b4-7331c0aac0af")
    @Override
    public String getValue() {
        return (String) getAttVal(((TemplateParameterSubstitutionSmClass)getClassOf()).getValueAtt());
    }

    @objid ("0d2e2f09-096d-4dd7-9187-e533ab9ecc6c")
    @Override
    public void setValue(String value) {
        setAttVal(((TemplateParameterSubstitutionSmClass)getClassOf()).getValueAtt(), value);
    }

    @objid ("e2812f9f-c48d-4109-ab1a-33abbf6f0447")
    @Override
    public TemplateBinding getOwner() {
        Object obj = getDepVal(((TemplateParameterSubstitutionSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof TemplateBinding)? (TemplateBinding)obj : null;
    }

    @objid ("47c9ef88-9329-43f3-b11a-a7367d769212")
    @Override
    public void setOwner(TemplateBinding value) {
        appendDepVal(((TemplateParameterSubstitutionSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("9adbb333-7060-4b97-b733-3b726330b725")
    @Override
    public UmlModelElement getActual() {
        Object obj = getDepVal(((TemplateParameterSubstitutionSmClass)getClassOf()).getActualDep());
        return (obj instanceof UmlModelElement)? (UmlModelElement)obj : null;
    }

    @objid ("310c22c1-c1e0-4432-981f-23e6bded9069")
    @Override
    public void setActual(UmlModelElement value) {
        appendDepVal(((TemplateParameterSubstitutionSmClass)getClassOf()).getActualDep(), (SmObjectImpl)value);
    }

    @objid ("16cad655-d317-43bc-9c46-78c88afaa754")
    @Override
    public TemplateParameter getFormalParameter() {
        Object obj = getDepVal(((TemplateParameterSubstitutionSmClass)getClassOf()).getFormalParameterDep());
        return (obj instanceof TemplateParameter)? (TemplateParameter)obj : null;
    }

    @objid ("ef667eaa-c31b-4f4e-ab5a-aa0f09eb1bc0")
    @Override
    public void setFormalParameter(TemplateParameter value) {
        appendDepVal(((TemplateParameterSubstitutionSmClass)getClassOf()).getFormalParameterDep(), (SmObjectImpl)value);
    }

    @objid ("0cfd8a1d-2126-4a11-aa13-22dc947df649")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((TemplateParameterSubstitutionSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("440b1676-e433-4279-8577-762ce1bb5f5e")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((TemplateParameterSubstitutionSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("6446e747-9250-4e16-9452-624dfbdc2000")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitTemplateParameterSubstitution(this);
    }

}
