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
import org.modelio.metamodel.impl.uml.statik.BindingData;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000191ae-c4bf-1fd8-97fe-001ec947cd2a")
public class BindingImpl extends UmlModelElementImpl implements Binding {
    @objid ("d9b96462-c01e-4300-aee7-c0269d396dc5")
    @Override
    public ConnectorEnd getConnectorEndRole() {
        Object obj = getDepVal(((BindingSmClass)getClassOf()).getConnectorEndRoleDep());
        return (obj instanceof ConnectorEnd)? (ConnectorEnd)obj : null;
    }

    @objid ("1a84a73b-f871-45ea-a957-02002cabb9fc")
    @Override
    public void setConnectorEndRole(ConnectorEnd value) {
        appendDepVal(((BindingSmClass)getClassOf()).getConnectorEndRoleDep(), (SmObjectImpl)value);
    }

    @objid ("86800852-03eb-4fe7-af55-75135d24ae4d")
    @Override
    public NaryConnector getConnectorRole() {
        Object obj = getDepVal(((BindingSmClass)getClassOf()).getConnectorRoleDep());
        return (obj instanceof NaryConnector)? (NaryConnector)obj : null;
    }

    @objid ("15160b19-30a6-4728-beff-36c8b2fd1fc7")
    @Override
    public void setConnectorRole(NaryConnector value) {
        appendDepVal(((BindingSmClass)getClassOf()).getConnectorRoleDep(), (SmObjectImpl)value);
    }

    @objid ("c449a69a-90aa-4c2b-867e-3b2037ab44de")
    @Override
    public BindableInstance getRole() {
        Object obj = getDepVal(((BindingSmClass)getClassOf()).getRoleDep());
        return (obj instanceof BindableInstance)? (BindableInstance)obj : null;
    }

    @objid ("c77df155-9b56-490c-9910-05785a239a25")
    @Override
    public void setRole(BindableInstance value) {
        appendDepVal(((BindingSmClass)getClassOf()).getRoleDep(), (SmObjectImpl)value);
    }

    @objid ("1aaee7ae-54b0-451b-bef6-944394d709da")
    @Override
    public UmlModelElement getRepresentedFeature() {
        Object obj = getDepVal(((BindingSmClass)getClassOf()).getRepresentedFeatureDep());
        return (obj instanceof UmlModelElement)? (UmlModelElement)obj : null;
    }

    @objid ("807f5dfe-044b-4a09-81a6-84a443869eb3")
    @Override
    public void setRepresentedFeature(UmlModelElement value) {
        appendDepVal(((BindingSmClass)getClassOf()).getRepresentedFeatureDep(), (SmObjectImpl)value);
    }

    @objid ("a8e298c3-5508-4e17-8325-d1cce36e137f")
    @Override
    public CollaborationUse getOwner() {
        Object obj = getDepVal(((BindingSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof CollaborationUse)? (CollaborationUse)obj : null;
    }

    @objid ("0794b865-f99b-4be4-9807-b933fec330f8")
    @Override
    public void setOwner(CollaborationUse value) {
        appendDepVal(((BindingSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("713e76f9-2411-4d3b-ad29-f034b9b7c1ce")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((BindingSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("9c6f1e0a-47cc-40e5-8c26-ef849b9493b7")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((BindingSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("5b687d2b-6e5f-44a0-8dc7-0d60ec04401b")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBinding(this);
    }

}
