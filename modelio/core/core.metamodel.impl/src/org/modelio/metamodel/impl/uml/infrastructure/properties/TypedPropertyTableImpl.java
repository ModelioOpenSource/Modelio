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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("006b097c-ec87-1098-b22e-001ec947cd2a")
public class TypedPropertyTableImpl extends PropertyTableImpl implements TypedPropertyTable {
    @objid ("26b2c38d-2834-11e2-bf07-001ec947ccaf")
    @Override
    public String getProperty(PropertyDefinition pdef) {
        return getProperty(pdef.getName());
    }

    @objid ("96be10c4-8b06-4642-82aa-f261d737b6ad")
    @Override
    public Object getPropertyObject(PropertyDefinition pdef) {
        String storedValue = getProperty(pdef);
        if (storedValue == null) {
            storedValue = pdef.getDefaultValue();
        }
        // Delegate conversion to the PropertyDefinition
        return pdef.convertToObject(storedValue, (ModelElement) getCompositionOwner());
    }

    @objid ("26b2c393-2834-11e2-bf07-001ec947ccaf")
    @Override
    public void setProperty(PropertyDefinition pdef, String value) {
        // assert the property definition belongs to the table type
        assert (getType() != null && pdef.getOwner().equals(getType())) : pdef+" does not belong to "+getType();
        setProperty(pdef.getName(), value);
        
    }

    @objid ("dcabea63-bd75-41ac-a24e-b9a053e89a0d")
    @Override
    public void setPropertyObject(PropertyDefinition pdef, Object value) {
        // Delegate conversion to the PropertyDefinition
        String storedValue = pdef.convertToString(value, (ModelElement) getCompositionOwner());
        setProperty(pdef, storedValue);
        
    }

    @objid ("a97851f1-66a1-4ab1-b1d5-62c6b9a76e3b")
    @Override
    public PropertyTableDefinition getType() {
        Object obj = getDepVal(((TypedPropertyTableSmClass)getClassOf()).getTypeDep());
        return (obj instanceof PropertyTableDefinition)? (PropertyTableDefinition)obj : null;
    }

    @objid ("7f37183d-423d-4582-a8f8-64dcb238da60")
    @Override
    public void setType(PropertyTableDefinition value) {
        appendDepVal(((TypedPropertyTableSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("0bcaf560-4998-4059-80a2-6ff0f6ac4468")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("ea4903ee-93fe-46f1-b205-ef8eeea74f5a")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("d7087c34-af88-4f0f-b50f-7c7e343a3c14")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTypedPropertyTable(this);
    }

}
