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
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition.IDynamicPropertyResolver;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("922ada73-a631-45a4-8ccb-b91d7a26cccb")
public class DynamicPropertyDefinitionImpl extends PropertyDefinitionImpl implements DynamicPropertyDefinition {
    @objid ("261a4d72-4079-4832-9c3d-48b507d6b152")
    @Override
    public Object convertToObject(String value, ModelElement object) {
        // If there is no base type, can only return the string
        if (getType().getBaseType() == null) {
            return value;
        }
        
        IDynamicPropertyResolver resolver = getDynamicPropertyResolver();
        if (resolver != null) {
            return resolver.convertToObject(this, value, object);
        } else {
            throw new UnsupportedOperationException("No DynamicBehavior found for the " + this.toString() + " property.");
        }
        
    }

    @objid ("0b651b9e-97a7-4ce8-8e1f-e15f15e9807b")
    @Override
    public String convertToString(Object value, ModelElement object) {
        IDynamicPropertyResolver resolver = getDynamicPropertyResolver();
        if (resolver != null) {
            return resolver.convertToString(this, value, object);
        } else {
            throw new UnsupportedOperationException("No DynamicBehavior found for the " + this.toString() + " property.");
        }
        
    }

    @objid ("6b0eecda-d721-4335-9cd4-cd403584761d")
    private IDynamicPropertyResolver getDynamicPropertyResolver() {
        String resolverKey = getProperty(DynamicPropertyDefinition.PROPERTY_RESOLVER_TABLE, DynamicPropertyDefinition.PROPERTY_RESOLVER_KEY);
        return getMClass().getOrigin().getDynamicBehavior(resolverKey, IDynamicPropertyResolver.class);
    }

    @objid ("1d69c937-0753-4adb-bb17-a25904d82941")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("3933de84-3c18-4b4c-8892-d848eb880b8a")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("43c0fe77-9238-4a41-a38d-a03517631c16")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitDynamicPropertyDefinition(this);
    }

}
