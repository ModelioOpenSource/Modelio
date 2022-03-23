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
package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("f8954db0-3d41-46cd-92e7-7ca045b5c9ba")
public class OClause extends OElement implements IOElement {
    @objid ("1b0dd9c7-e1d0-4b47-9af2-7aafc4ab6cf4")
    private GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("1c2520a0-0742-407c-b7fd-282163495322")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createClause();
    }

    @objid ("3431203f-0cc1-4197-a48f-2df7a2d5d063")
    public  OClause(Clause element) {
        super(element);
    }

    @objid ("332acc7c-8a66-4129-9660-aa983e7e629b")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        ConditionalNode objingOwner = ((Clause) getObjingElement()).getOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = this.genProp.getMappedElement(objingOwner);
        
        if (ecoreOwner instanceof org.eclipse.uml2.uml.ConditionalNode) {
            ((org.eclipse.uml2.uml.ConditionalNode) ecoreOwner).getClauses().add( (org.eclipse.uml2.uml.Clause)ecoreElt);
        } else {
            ecoreElt.destroy();
            String errorMsg = "";
            if (ecoreOwner == null)
                errorMsg = "Owner Class not Found";
            else
                errorMsg = "Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found";
            throw new NotFoundException(errorMsg);
        }
        
    }

    @objid ("3c1016ae-113d-4a7b-8749-28a64d38ff2c")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        setBody( (org.eclipse.uml2.uml.Clause) ecoreElt);
        setTest( (org.eclipse.uml2.uml.Clause) ecoreElt);
        setName( (org.eclipse.uml2.uml.Clause) ecoreElt);
        
    }

    @objid ("7431f330-8663-450e-8de1-eaa48b141ce8")
    private void setBody(org.eclipse.uml2.uml.Clause clause) {
        Clause objingElement = (Clause) getObjingElement();
        for (ActivityNode objingBody : objingElement.getBody()) {
            org.eclipse.uml2.uml.Element ecoreBody = this.genProp.getMappedElement(objingBody);
            if (ecoreBody instanceof org.eclipse.uml2.uml.ExecutableNode)
                clause.getBodies().add( (org.eclipse.uml2.uml.ExecutableNode)ecoreBody);
        }
        
    }

    @objid ("ae5dd2c8-53f0-46a9-a29d-4bdc8285ddd0")
    private void setTest(org.eclipse.uml2.uml.Clause clause) {
        String test = ((Clause) getObjingElement()).getTest();
        if (test.length() > 0) {
             org.eclipse.uml2.uml.ValueSpecificationAction ecoreTestAction = UMLFactory.eINSTANCE
                    .createValueSpecificationAction();
            ecoreTestAction.setName(test);
            org.eclipse.uml2.uml.LiteralString value = UMLFactory.eINSTANCE.createLiteralString();
            value.setValue(test);
            ecoreTestAction.setValue(value);
            clause.getTests().add(ecoreTestAction);
            attachActionToActivity(ecoreTestAction);
            ObjingEAnnotation.setIsDeleted(ecoreTestAction);
        }
        
    }

    @objid ("7d27bf41-983a-430e-96b9-66ff79d7a586")
    private void setName(org.eclipse.uml2.uml.Clause clause) {
        ObjingEAnnotation.setName(clause, ((Clause) getObjingElement()).getName());
    }

    @objid ("8f818bd9-e1b3-49ec-8d0c-0bf17e4554ff")
    private void attachActionToActivity(org.eclipse.uml2.uml.ValueSpecificationAction action) {
        // Setting composition relation (attach to  org.eclipse.uml2.uml.Activity)
        Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                .getEnclosingElement((getObjingElement()), getObjingElement().getMClass().getMetamodel().getMClass(Activity.class));
        if (enclosingActivity != null) {
            org.eclipse.uml2.uml.Element ecoreActivity = this.genProp.getMappedElement(enclosingActivity);
            if (ecoreActivity instanceof  org.eclipse.uml2.uml.Activity) {
                ((org.eclipse.uml2.uml.Activity) ecoreActivity).getNodes().add(action);
            }
        }
        
    }

}
