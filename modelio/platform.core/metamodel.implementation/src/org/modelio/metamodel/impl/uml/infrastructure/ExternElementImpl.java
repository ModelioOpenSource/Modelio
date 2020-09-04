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
import org.modelio.metamodel.impl.uml.infrastructure.ExternElementData;
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
    @objid ("630fb9e8-ff32-47a7-a15b-cee7e4ba00dd")
    @Override
    public String getProvider() {
        return (String) getAttVal(((ExternElementSmClass)getClassOf()).getProviderAtt());
    }

    @objid ("4898d69b-42e4-4c66-8ced-7ca4566eba24")
    @Override
    public void setProvider(String value) {
        setAttVal(((ExternElementSmClass)getClassOf()).getProviderAtt(), value);
    }

    @objid ("70d8b11c-5cab-43b7-895c-c865a02299e4")
    @Override
    public String getExternId() {
        return (String) getAttVal(((ExternElementSmClass)getClassOf()).getExternIdAtt());
    }

    @objid ("9993792b-5058-424a-b664-2b06fc525965")
    @Override
    public void setExternId(String value) {
        setAttVal(((ExternElementSmClass)getClassOf()).getExternIdAtt(), value);
    }

    @objid ("eb38e5b0-dd20-474e-989f-fa5778c49091")
    @Override
    public String getLocation() {
        return (String) getAttVal(((ExternElementSmClass)getClassOf()).getLocationAtt());
    }

    @objid ("05d758ad-5e55-4f9f-b514-8a750bcb1d65")
    @Override
    public void setLocation(String value) {
        setAttVal(((ExternElementSmClass)getClassOf()).getLocationAtt(), value);
    }

    @objid ("8fe2d23a-3459-48a1-b61b-b1610ec9363f")
    @Override
    public MethodologicalLink getOwner() {
        Object obj = getDepVal(((ExternElementSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof MethodologicalLink)? (MethodologicalLink)obj : null;
    }

    @objid ("7c6f6015-851d-4f43-beb8-99edc80dc4e1")
    @Override
    public void setOwner(MethodologicalLink value) {
        appendDepVal(((ExternElementSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("949e3bfc-7dc2-4aa8-879e-0eb48b13ce03")
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

    @objid ("26ee9211-3cdf-484f-aab7-f817706d0c11")
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

    @objid ("66feeb1d-92b7-4e25-b30b-5ce778015cca")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitExternElement(this);
    }

}
