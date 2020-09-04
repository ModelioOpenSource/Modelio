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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.ObjectNodeOrderingKind;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("8ae1f1ee-7a0c-46c3-bcf6-b6eaa13c9016")
public class ECentralBufferNode extends EActivityNode {
    @objid ("89824240-c0f0-40e8-b5e2-c9b34dc4d18b")
    private org.eclipse.uml2.uml.CentralBufferNode ecoreElement = null;

    @objid ("ab28cbdc-a09d-4aa5-864b-0d1caab2f6ca")
    @Override
    public Element createObjingElt() {
        if ( ReverseProperties.getInstance().isRoundtripEnabled()){
            String type = ObjingEAnnotation.getType(this.ecoreElement);
            if ((type != null) && (type.equals("InstanceNode"))){
                    return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createInstanceNode();
            }
            return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createCentralBufferNode();
        }
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createCentralBufferNode();
    }

    @objid ("2dfe851b-a74a-4204-9057-0033c6051c76")
    public ECentralBufferNode(org.eclipse.uml2.uml.CentralBufferNode element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("33a634e2-be51-4840-bec3-44f8cb2c0d02")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        if (objingElt instanceof ObjectNode){
            setControlType((ObjectNode) objingElt);
            setOrdering((ObjectNode) objingElt);
            setSelectionBehavior((ObjectNode) objingElt);
            setUpperBound((ObjectNode) objingElt);
            setType((ObjectNode) objingElt);
            setState((ObjectNode) objingElt);
        }
    }

    @objid ("e875647f-1c95-476e-9ad1-c9241966fb97")
    private void setControlType(ObjectNode node) {
        node.setIsControlType(this.ecoreElement.isControlType());
    }

    @objid ("a3187a3b-029c-48af-999f-004f53d16516")
    private void setOrdering(ObjectNode node) {
        switch (this.ecoreElement.getOrdering().getValue()) {
        case ObjectNodeOrderingKind.FIFO:
            node.setOrdering(org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind.FIFO);
            break;
        case ObjectNodeOrderingKind.LIFO:
            node.setOrdering(org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind.LIFO);
            break;
        case ObjectNodeOrderingKind.ORDERED:
            node.setOrdering(org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind.ORDERED);
            break;
        case ObjectNodeOrderingKind.UNORDERED:
            node.setOrdering(org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind.UNORDERED);
            break;
        default:
            node.setOrdering(org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind.FIFO);
        }
    }

    @objid ("be97bbe4-6b73-421d-9268-72421c02113b")
    private void setSelectionBehavior(ObjectNode node) {
        org.eclipse.uml2.uml. Behavior ecoreBehavior = this.ecoreElement.getSelection();
        if (ecoreBehavior instanceof org.eclipse.uml2.uml.OpaqueBehavior) {
            String objingBehavior = "";
            for (Object body : ((org.eclipse.uml2.uml.OpaqueBehavior) ecoreBehavior).getBodies()) {
                objingBehavior = objingBehavior.concat((String) body);
            }
            node.setSelectionBehavior(objingBehavior);
        } else if (ecoreBehavior != null) {
            String behaviorName = ecoreBehavior.getName();
            if (behaviorName != null)
                node.setSelectionBehavior(behaviorName);
        }
    }

    @objid ("8e4e0363-eb90-4a3a-b65a-2bdd83461167")
    private void setUpperBound(ObjectNode node) {
        org.eclipse.uml2.uml.ValueSpecification ecoreUpperBound = this.ecoreElement.getUpperBound();
                if (ecoreUpperBound != null) {
           String stringValue = ecoreUpperBound.stringValue();
           if (stringValue != null)
               node.setUpperBound(stringValue);
                }
    }

    @objid ("6e0fd1ea-a45d-4826-acba-3b4ce7c7478a")
    private void setType(ObjectNode node) {
        org.eclipse.uml2.uml.Type ecoreType = this.ecoreElement.getType();
        if (ecoreType != null) {
            Object objingType =  ReverseProperties.getInstance().getMappedElement(ecoreType);
            if (objingType instanceof GeneralClass)
                node.setType((GeneralClass) objingType);
        }
    }

    @objid ("842aa9cb-0a1e-4cd4-b924-4ce806163164")
    private void setState(ObjectNode node) {
        EList<?> ecoreStates = this.ecoreElement.getInStates();
        if (ecoreStates != null && ecoreStates.size() > 0) {
            org.eclipse.uml2.uml.State ecoreState = (org.eclipse.uml2.uml.State) ecoreStates.get(0);
            if (ecoreState != null) {
                Object objingState =  ReverseProperties.getInstance().getMappedElement(ecoreState);
                if (objingState instanceof State)
                    node.setInState((State) objingState);
            }
        }
    }

}
