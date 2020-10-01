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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.InterfaceRealizationData;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000f221a-c4bf-1fd8-97fe-001ec947cd2a")
public class InterfaceRealizationImpl extends UmlModelElementImpl implements InterfaceRealization {
    @objid ("edda6cd4-082e-4ef5-8c13-e487866a95ed")
    @Override
    public Interface getImplemented() {
        Object obj = getDepVal(((InterfaceRealizationSmClass)getClassOf()).getImplementedDep());
        return (obj instanceof Interface)? (Interface)obj : null;
    }

    @objid ("b1297b4d-5387-4fdd-904a-cbd39c63bc90")
    @Override
    public void setImplemented(Interface value) {
        appendDepVal(((InterfaceRealizationSmClass)getClassOf()).getImplementedDep(), (SmObjectImpl)value);
    }

    @objid ("e544bbb9-aa64-4d38-9adc-315e4558f7bd")
    @Override
    public NameSpace getImplementer() {
        Object obj = getDepVal(((InterfaceRealizationSmClass)getClassOf()).getImplementerDep());
        return (obj instanceof NameSpace)? (NameSpace)obj : null;
    }

    @objid ("911d4b91-38b6-4f6b-9a15-be7e7fd90617")
    @Override
    public void setImplementer(NameSpace value) {
        appendDepVal(((InterfaceRealizationSmClass)getClassOf()).getImplementerDep(), (SmObjectImpl)value);
    }

    @objid ("f49ec3d3-2725-4793-a3b8-f7561f195a41")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Implementer
        obj = (SmObjectImpl)this.getDepVal(((InterfaceRealizationSmClass)getClassOf()).getImplementerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("a0d72028-bbe0-40c3-88c4-5e2e36ae3412")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Implementer
        dep = ((InterfaceRealizationSmClass)getClassOf()).getImplementerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("51e3a7c7-885b-400d-bea2-fc1ff1378fca")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitInterfaceRealization(this);
    }

}
