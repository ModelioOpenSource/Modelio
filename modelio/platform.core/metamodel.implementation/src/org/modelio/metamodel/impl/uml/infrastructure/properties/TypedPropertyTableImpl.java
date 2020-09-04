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
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.properties.TypedPropertyTableData;
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

    @objid ("0b3c206c-a9c2-4a9a-987b-170b7b78ff74")
    @Override
    public PropertyTableDefinition getType() {
        Object obj = getDepVal(((TypedPropertyTableSmClass)getClassOf()).getTypeDep());
        return (obj instanceof PropertyTableDefinition)? (PropertyTableDefinition)obj : null;
    }

    @objid ("750e847f-b740-4eab-8d81-214418898b63")
    @Override
    public void setType(PropertyTableDefinition value) {
        appendDepVal(((TypedPropertyTableSmClass)getClassOf()).getTypeDep(), (SmObjectImpl)value);
    }

    @objid ("96d329b7-a20e-4731-ab39-575c1e64c653")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("3545cfd3-ae6c-490b-b1cc-4041fecf1b03")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        return super.getCompositionRelation();
    }

    @objid ("c03329a3-380c-4aa0-aa1f-772dd5609f4a")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitTypedPropertyTable(this);
    }

}
