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

package org.modelio.metamodel.impl.bpmn.rootElements;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociationDirection;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00775b3c-c4bf-1fd8-97fe-001ec947cd2a")
public class BpmnAssociationImpl extends BpmnArtifactImpl implements BpmnAssociation {
    @objid ("427e0144-87c9-4595-8131-45bcf7dbb980")
    @Override
    public BpmnAssociationDirection getAssociationDirection() {
        return (BpmnAssociationDirection) getAttVal(((BpmnAssociationSmClass)getClassOf()).getAssociationDirectionAtt());
    }

    @objid ("f33e8def-eb5c-42ca-85d8-f161507b430e")
    @Override
    public void setAssociationDirection(BpmnAssociationDirection value) {
        setAttVal(((BpmnAssociationSmClass)getClassOf()).getAssociationDirectionAtt(), value);
    }

    @objid ("a4bcbd3a-8fda-4788-8232-1a3eac9dd035")
    @Override
    public BpmnBaseElement getTargetRef() {
        Object obj = getDepVal(((BpmnAssociationSmClass)getClassOf()).getTargetRefDep());
        return (obj instanceof BpmnBaseElement)? (BpmnBaseElement)obj : null;
    }

    @objid ("d4b64d50-0f7f-4571-9d90-e0bd47f5affa")
    @Override
    public void setTargetRef(BpmnBaseElement value) {
        appendDepVal(((BpmnAssociationSmClass)getClassOf()).getTargetRefDep(), (SmObjectImpl)value);
    }

    @objid ("58b7b254-2835-4d15-990a-e0ef0d4956ae")
    @Override
    public BpmnBaseElement getSourceRef() {
        Object obj = getDepVal(((BpmnAssociationSmClass)getClassOf()).getSourceRefDep());
        return (obj instanceof BpmnBaseElement)? (BpmnBaseElement)obj : null;
    }

    @objid ("c59ab9c1-4237-4f48-b80c-a8a49e0b6948")
    @Override
    public void setSourceRef(BpmnBaseElement value) {
        appendDepVal(((BpmnAssociationSmClass)getClassOf()).getSourceRefDep(), (SmObjectImpl)value);
    }

    @objid ("0882b9b0-cfc5-4c95-a747-b7ff39d91304")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("59719534-9510-4f30-938d-05d336506cf5")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("8199a4f9-9a7b-4117-844e-37637b7df6cc")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitBpmnAssociation(this);
    }

}
