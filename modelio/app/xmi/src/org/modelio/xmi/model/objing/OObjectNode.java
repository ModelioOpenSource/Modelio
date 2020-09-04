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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.ObjectNodeOrderingKind;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ModelioPrimitiveTypeMapper;
import org.modelio.xmi.util.NotFoundException;

@objid ("87ba85d7-ef0e-48f9-afa1-ea5183737d13")
public class OObjectNode extends OActivityNode {
    @objid ("c43fe3c6-13cd-40fe-aab2-a7bb45058fb1")
    public OObjectNode(ObjectNode element) {
        super(element);
    }

    @objid ("fc154074-aac1-4bc2-a834-ccc0cbb028fb")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("ada4161b-9f41-40e3-a0dd-2e06fe08e64b")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setControlType((org.eclipse.uml2.uml.ObjectNode) ecoreElt);
        setOrdering((org.eclipse.uml2.uml.ObjectNode) ecoreElt);
        setSelectionBehavior((org.eclipse.uml2.uml.ObjectNode) ecoreElt);
        setUpperBound((org.eclipse.uml2.uml.ObjectNode) ecoreElt);
        setType((org.eclipse.uml2.uml.ObjectNode) ecoreElt);
        setState((org.eclipse.uml2.uml.ObjectNode) ecoreElt);
    }

    @objid ("395d6f5a-3506-4897-ab0d-89b349120d39")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return null;
    }

    @objid ("88c72a37-a880-4c75-8f04-210254c715b4")
    @Override
    public ObjectNode getObjingElement() {
        return (ObjectNode) super.getObjingElement();
    }

    @objid ("9dfa47f1-ff1c-4ce3-a3f4-e4e92c9e1afb")
    private void setControlType(final org.eclipse.uml2.uml.ObjectNode node) {
        node.setIsControlType(this.getObjingElement().isIsControlType());
    }

    @objid ("fa0bdf5d-5840-4e6a-935d-5632e0cbf201")
    private void setOrdering(final org.eclipse.uml2.uml.ObjectNode node) {
        switch (this.getObjingElement().getOrdering()) {
        case FIFO:
            node.setOrdering(ObjectNodeOrderingKind.FIFO_LITERAL);
            break;
        case LIFO:
            node.setOrdering(ObjectNodeOrderingKind.LIFO_LITERAL);
            break;
        case ORDERED:
            node.setOrdering(ObjectNodeOrderingKind.ORDERED_LITERAL);
            break;
        case UNORDERED:
            node.setOrdering(ObjectNodeOrderingKind.UNORDERED_LITERAL);
            break;
        default:
            node.setOrdering(ObjectNodeOrderingKind.FIFO_LITERAL);
        }
    }

    @objid ("f65fe0c6-d61c-42dd-b65b-8dd3c3d8d5bf")
    private void setSelectionBehavior(final org.eclipse.uml2.uml.ObjectNode node) {
        String selectionBehavior = this.getObjingElement().getSelectionBehavior();
        if (selectionBehavior.length() > 0) {
            org.eclipse.uml2.uml.OpaqueBehavior behavior = UMLFactory.eINSTANCE
                    .createOpaqueBehavior();
            behavior.setName("SelectionBehavior");
            behavior.getBodies().add(selectionBehavior);
            node.setSelection(behavior);
        
            // Setting composition relation
            Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                    .getEnclosingElement(this.getObjingElement(), getObjingElement().getMClass().getMetamodel().getMClass(Activity.class));
        
            Package objingPkg = AbstractObjingModelNavigation
                    .getNearestPackage(enclosingActivity);
        
            if (objingPkg != null) {
                org.eclipse.uml2.uml.Package ecorePkg = (org.eclipse.uml2.uml.Package)  GenerationProperties.getInstance()
                        .getMappedElement(objingPkg);
                if (ecorePkg != null)
                    ecorePkg.getPackagedElements().add(behavior);
                else{
                    node.destroy();
                    throw new NotFoundException("Owner Class not found.");
                }
            }
        }
    }

    @objid ("7e54d598-bdd4-46cd-a5ed-bf2349c50a3b")
    private void setUpperBound(final org.eclipse.uml2.uml.ObjectNode node) {
        String upperBound = this.getObjingElement().getUpperBound();
        if (upperBound.length() > 0) {
            org.eclipse.uml2.uml.LiteralString literal = UMLFactory.eINSTANCE.createLiteralString();
            literal.setName("UpperBound");
            literal.setValue(upperBound);
            node.setUpperBound(literal);
        }
    }

    @objid ("9700bfc1-7992-4bfd-81e0-b7dd73dc72c6")
    private void setType(final org.eclipse.uml2.uml.ObjectNode node) {
        GeneralClass objingType = this.getObjingElement().getType();
        if (objingType != null) {
        
            if (ModelioPrimitiveTypeMapper.isPredefinedType(objingType)) {
                ModelioPrimitiveTypeMapper.setEcorePredefinedType(node, (DataType) objingType);
            }else{
                org.eclipse.uml2.uml.Element ecoreType =  GenerationProperties.getInstance().getMappedElement(objingType);
                if (ecoreType instanceof org.eclipse.uml2.uml.Type)
                    node.setType( (org.eclipse.uml2.uml.Type) ecoreType);
            }
        
        
        }
    }

    @objid ("8dd8b110-8b36-4160-8d7f-4d983297755b")
    private void setState(final org.eclipse.uml2.uml.ObjectNode node) {
        State objingState = this.getObjingElement().getInState();
        if (objingState != null) {
            org.eclipse.uml2.uml.Element ecoreState =  GenerationProperties.getInstance().getMappedElement(objingState);
            if (ecoreState instanceof org.eclipse.uml2.uml.State)
                node.getInStates().add((org.eclipse.uml2.uml.State) ecoreState);
        }
    }

}
