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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
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
    @objid ("6a6227f0-c211-40fa-9083-64b47c5c64f9")
    @Override
    public ExternElement getExternElement() {
        Object obj = getDepVal(((MethodologicalLinkSmClass)getClassOf()).getExternElementDep());
        return (obj instanceof ExternElement)? (ExternElement)obj : null;
    }

    @objid ("ed0743e1-35c1-4cd4-9ee3-61d6a52fc938")
    @Override
    public void setExternElement(ExternElement value) {
        appendDepVal(((MethodologicalLinkSmClass)getClassOf()).getExternElementDep(), (SmObjectImpl)value);
    }

    @objid ("34434e3f-3c57-4fcc-8908-4b28279e2e74")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("42327f50-b337-4d57-8035-6c63cd2fa95a")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("e4e0af51-ad7f-48a4-bc81-d349863f2b00")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitMethodologicalLink(this);
    }

}
