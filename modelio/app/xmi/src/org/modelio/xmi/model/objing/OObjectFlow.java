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
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("f102c72a-cd06-4493-88ab-221201389476")
public class OObjectFlow extends OActivityEdge {
    @objid ("b05caad4-223b-4d41-8a44-ceeee86f0006")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createObjectFlow();
    }

    @objid ("ad0edac5-794e-4c5b-a161-a33d893395af")
    public OObjectFlow(ObjectFlow element) {
        super(element);
    }

    @objid ("608e0883-6c5e-4004-b554-d8eaac8acf93")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // Properties defined on super classes:
        super.setProperties(ecoreElt);
        
        
        // Properties of ObjectFlows:
        setEffectEAnnotation((org.eclipse.uml2.uml.ObjectFlow) ecoreElt);
        setMultiCast((org.eclipse.uml2.uml.ObjectFlow) ecoreElt);
        setMultiReceive((org.eclipse.uml2.uml.ObjectFlow) ecoreElt);
        setSelectionBehavior((org.eclipse.uml2.uml.ObjectFlow) ecoreElt);
        setTransformationBehavior((org.eclipse.uml2.uml.ObjectFlow) ecoreElt);
    }

    @objid ("571d6964-acf1-424a-af1a-9c77d22e8113")
    private void setEffectEAnnotation(org.eclipse.uml2.uml.ObjectFlow flow) {
        String effect;
        switch (getObjingElement().getEffect()) {
        case CREATEFLOW:
            effect = "CREATE_FLOW";
            break;
        case DELETEFLOW:
            effect = "DELETE_FLOW";
            break;
        case EXCEPTIONFLOW:
            effect = "EXCEPTION_FLOW";
            break;
        case READFLOW:
            effect = "READ_FLOW";
            break;
        case UPDATEFLOW:
            effect = "UPDATEFLOW";
            break;
        default:
            effect = "READ_FLOW";
        }
        
        ObjingEAnnotation.setEffect(flow, effect);
    }

    @objid ("ebb6624f-4ff0-4604-bbd8-d721fa004c2d")
    private void setMultiCast(org.eclipse.uml2.uml.ObjectFlow flow) {
        flow.setIsMulticast(getObjingElement().isIsMultiCast());
    }

    @objid ("cf48a525-5a89-4faf-95e2-8f5e46e8fdc6")
    private void setMultiReceive(org.eclipse.uml2.uml.ObjectFlow flow) {
        flow.setIsMultireceive(getObjingElement().isIsMultiReceive());
    }

    @objid ("ce3957f2-f99c-4bab-9962-be534c617c86")
    private void setSelectionBehavior(org.eclipse.uml2.uml.ObjectFlow flow) {
        String selectionBehavior = getObjingElement().getSelectionBehavior();
        if (selectionBehavior.length() > 0) {
            org.eclipse.uml2.uml.OpaqueBehavior behavior = UMLFactory.eINSTANCE
                    .createOpaqueBehavior();
            behavior.setName("SelectionBehavior");
            behavior.getBodies().add(selectionBehavior);
            flow.setSelection(behavior);
        
            // Setting composition relation
            Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                    .getEnclosingElement(getObjingElement(), getObjingElement().getMClass().getMetamodel().getMClass(Activity.class));
        
            Package objingPkg = AbstractObjingModelNavigation
                    .getNearestPackage(enclosingActivity);
        
            if (objingPkg != null) {
                org.eclipse.uml2.uml.Package ecorePkg = (org.eclipse.uml2.uml.Package) GenerationProperties.getInstance()
                        .getMappedElement(objingPkg);
        
                if (ecorePkg != null)
                    ecorePkg.getPackagedElements().add(behavior);
            }
        }
    }

    @objid ("afe5b010-f892-40ac-b6a7-f0f207f126a1")
    private void setTransformationBehavior(org.eclipse.uml2.uml.ObjectFlow flow) {
        String transformationBehavior = getObjingElement()
                .getTransformationBehavior();
        if (transformationBehavior.length() > 0) {
            org.eclipse.uml2.uml.OpaqueBehavior behavior = UMLFactory.eINSTANCE
                    .createOpaqueBehavior();
            behavior.setName("TransformationBehavior");
            behavior.getBodies().add(transformationBehavior);
            flow.setSelection(behavior);
        }
    }

    @objid ("130f8756-eda2-46b3-9bc8-f64ff6140b4c")
    @Override
    public ObjectFlow getObjingElement() {
        return (ObjectFlow) super.getObjingElement();
    }

}
