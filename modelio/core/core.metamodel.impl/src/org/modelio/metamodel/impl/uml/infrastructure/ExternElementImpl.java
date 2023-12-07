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

@objid ("7cf7f530-18cf-49e9-a9a7-98d12acb807b")
public class ExternElementImpl extends ModelElementImpl implements ExternElement {
    @objid ("3f506952-575a-4fcc-a5b2-65a895089a43")
    @Override
    public String getProvider() {
        return (String) getAttVal(((ExternElementSmClass)getClassOf()).getProviderAtt());
    }

    @objid ("9a6875d2-f58c-4164-8ade-5ebd9dccd064")
    @Override
    public void setProvider(String value) {
        setAttVal(((ExternElementSmClass)getClassOf()).getProviderAtt(), value);
    }

    @objid ("582ba209-4efc-41e6-836f-9378e7178429")
    @Override
    public String getExternId() {
        return (String) getAttVal(((ExternElementSmClass)getClassOf()).getExternIdAtt());
    }

    @objid ("8fee3eca-fbe2-4a8a-9c4e-fb4f4df87742")
    @Override
    public void setExternId(String value) {
        setAttVal(((ExternElementSmClass)getClassOf()).getExternIdAtt(), value);
    }

    @objid ("522d6ea1-3528-43cd-919d-a3c16aab7579")
    @Override
    public String getLocation() {
        return (String) getAttVal(((ExternElementSmClass)getClassOf()).getLocationAtt());
    }

    @objid ("48f27b65-2218-457a-81ef-fe29e01eac1a")
    @Override
    public void setLocation(String value) {
        setAttVal(((ExternElementSmClass)getClassOf()).getLocationAtt(), value);
    }

    @objid ("31c3c095-872f-45ee-90ec-3f39b5a83702")
    @Override
    public MethodologicalLink getOwner() {
        Object obj = getDepVal(((ExternElementSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof MethodologicalLink)? (MethodologicalLink)obj : null;
    }

    @objid ("cb8ce5ec-5e77-44b8-a632-cd322d9ee2e5")
    @Override
    public void setOwner(MethodologicalLink value) {
        appendDepVal(((ExternElementSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("e6cdfa9b-6fbe-4c8e-91bc-c36da03a13c9")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((ExternElementSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("e2e64ce4-bdb6-4822-a320-f759dcc02a29")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((ExternElementSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("76414e1a-dec6-4c2f-b7d5-a3c9140028fb")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitExternElement(this);
    }

}
