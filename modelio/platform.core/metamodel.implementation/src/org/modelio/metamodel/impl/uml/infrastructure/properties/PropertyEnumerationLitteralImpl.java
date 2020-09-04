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
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyEnumerationLitteralData;
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

    @objid ("ecbee17b-24d7-4d10-9677-759645ac4f9f")
    @Override
    public EnumeratedPropertyType getOwner() {
        Object obj = getDepVal(((PropertyEnumerationLitteralSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof EnumeratedPropertyType)? (EnumeratedPropertyType)obj : null;
    }

    @objid ("277fcca7-b4d5-4f0b-a58f-1f06a4e8e389")
    @Override
    public void setOwner(EnumeratedPropertyType value) {
        appendDepVal(((PropertyEnumerationLitteralSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("94d883b7-ee75-413a-8a57-f163c4e365bb")
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

    @objid ("c4b9e76a-0a4a-475b-9807-81a2684e87e5")
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

    @objid ("b1cddef5-7887-44ab-8906-355548eae0e4")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyEnumerationLitteral(this);
    }

}
