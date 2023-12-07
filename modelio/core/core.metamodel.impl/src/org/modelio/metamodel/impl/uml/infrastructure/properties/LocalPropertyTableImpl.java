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
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("006cfe26-ec87-1098-b22e-001ec947cd2a")
public class LocalPropertyTableImpl extends PropertyTableImpl implements LocalPropertyTable {
    @objid ("b63cc280-956e-4c7d-a68b-3a7a1f8484e8")
    @Override
    public ModelElement getLocalAnnoted() {
        Object obj = getDepVal(((LocalPropertyTableSmClass)getClassOf()).getLocalAnnotedDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("2ef3399c-c23d-488f-9932-fd130043802b")
    @Override
    public void setLocalAnnoted(ModelElement value) {
        appendDepVal(((LocalPropertyTableSmClass)getClassOf()).getLocalAnnotedDep(), (SmObjectImpl)value);
    }

    @objid ("29c4d441-bd2d-496a-9569-b174116ca05c")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("2f2616b0-d4db-48f2-93e2-bbee255d0c79")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("8a307c48-961d-4ad8-b0eb-f40d52286b64")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitLocalPropertyTable(this);
    }

}
