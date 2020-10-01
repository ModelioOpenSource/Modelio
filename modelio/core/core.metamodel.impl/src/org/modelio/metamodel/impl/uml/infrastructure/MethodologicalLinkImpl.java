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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.MethodologicalLinkData;
import org.modelio.metamodel.uml.infrastructure.ExternElement;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("54eeca61-6ddb-4cfc-9b17-2f0c30f9720b")
public class MethodologicalLinkImpl extends DependencyImpl implements MethodologicalLink {
    @objid ("56585732-ec5f-455b-aed1-2eb882200cf5")
    @Override
    public ExternElement getExternElement() {
        Object obj = getDepVal(((MethodologicalLinkSmClass)getClassOf()).getExternElementDep());
        return (obj instanceof ExternElement)? (ExternElement)obj : null;
    }

    @objid ("13a377dd-2fc9-446e-88f1-896f131e5b17")
    @Override
    public void setExternElement(ExternElement value) {
        appendDepVal(((MethodologicalLinkSmClass)getClassOf()).getExternElementDep(), (SmObjectImpl)value);
    }

    @objid ("f770f90f-d0a4-49a4-8ab1-3a1070066473")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("485f15ed-b308-4086-a41a-0e587a6a5f49")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("dde3feac-83be-4bf2-a038-fb4a8408d02a")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMethodologicalLink(this);
    }

}
