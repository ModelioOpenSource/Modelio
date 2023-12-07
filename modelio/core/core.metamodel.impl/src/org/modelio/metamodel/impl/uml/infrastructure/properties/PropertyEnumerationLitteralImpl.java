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
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("0072f2fe-ec87-1098-b22e-001ec947cd2a")
public class PropertyEnumerationLitteralImpl extends ModelElementImpl implements PropertyEnumerationLitteral {
    @objid ("50b89a70-0ae4-4e4e-b53e-0de57d0f1e0e")
    @Override
    public int compareTo(PropertyEnumerationLitteral l) {
        List<PropertyEnumerationLitteral> literals = getOwner().getLitteral();
        return Integer.compare(literals.indexOf(l), literals.indexOf(this));
    }

    @objid ("5ce3b468-495f-4649-a835-dd214d35842b")
    @Override
    public EnumeratedPropertyType getOwner() {
        Object obj = getDepVal(((PropertyEnumerationLitteralSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof EnumeratedPropertyType)? (EnumeratedPropertyType)obj : null;
    }

    @objid ("e58070ab-078b-422a-aa7b-1c4fdddae863")
    @Override
    public void setOwner(EnumeratedPropertyType value) {
        appendDepVal(((PropertyEnumerationLitteralSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("68e5c15d-6a4c-4de2-9e62-4ee7ecad002d")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((PropertyEnumerationLitteralSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("4e2a7f8f-3cfb-489e-81f7-acf7a7ede7d3")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((PropertyEnumerationLitteralSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("f0e6ee44-097f-4160-960d-606f7eb1b2b9")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyEnumerationLitteral(this);
    }

}
