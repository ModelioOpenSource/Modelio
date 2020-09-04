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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("7bf5ab1e-9918-4613-8f66-81f0ba6cefa7")
public class OLoopNode extends OStructuredActivityNode {
    @objid ("215ba0b5-a5e8-4ea0-8cdd-621bcecc1621")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createLoopNode();
    }

    @objid ("ba577b40-214c-487f-8051-3d1efa05f58b")
    public OLoopNode(LoopNode element) {
        super(element);
    }

    @objid ("0b8276b7-0f77-4cac-81bc-9cb892ed7304")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // Done when mapping the super type (org.eclipse.uml2.uml.StructuredActivityNode)
        super.attach(ecoreElt);
    }

    @objid ("7a17fa6c-ee16-44fa-9dac-0f5f73ba558d")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // Part of the Properties are setted when mapping the super type
        // (org.eclipse.uml2.uml.StructuredActivityNode)
         super.setProperties(ecoreElt);
        
        // Properties specific to org.eclipse.uml2.uml.LoopNodes:
        setIsTestedFirst((org.eclipse.uml2.uml.LoopNode) ecoreElt);
        setBody((org.eclipse.uml2.uml.LoopNode) ecoreElt);
        setSetup((org.eclipse.uml2.uml.LoopNode) ecoreElt);
        setTest((org.eclipse.uml2.uml.LoopNode) ecoreElt);
    }

    @objid ("701eaf8d-6815-4e10-87a0-40bede47c71c")
    private void setIsTestedFirst(org.eclipse.uml2.uml.LoopNode node) {
        node.setIsTestedFirst(getObjingElement().isIsTestedFirst());
    }

    @objid ("45ea780e-20e9-4317-81fc-4b5310fb19fe")
    private void setBody(org.eclipse.uml2.uml.LoopNode node) {
        for (ActivityNode objingBody : getObjingElement().getBody()) {
            org.eclipse.uml2.uml.Element ecoreBody = GenerationProperties.getInstance().getMappedElement(objingBody);
            if (ecoreBody instanceof org.eclipse.uml2.uml.ExecutableNode)
                node.getBodyParts().add( (org.eclipse.uml2.uml.ExecutableNode)ecoreBody);
        }
    }

    @objid ("02ea7b77-b61a-45d7-a54f-86cfb6c5114f")
    private void setSetup(org.eclipse.uml2.uml.LoopNode node) {
        String setup = getObjingElement().getSetup();
        if (setup.length() > 0) {
             org.eclipse.uml2.uml.ValueSpecificationAction ecoreSetupAction = UMLFactory.eINSTANCE
                    .createValueSpecificationAction();
            ObjingEAnnotation.setIsDeleted(ecoreSetupAction);
            ecoreSetupAction.setName(setup);
            org.eclipse.uml2.uml.LiteralString value = UMLFactory.eINSTANCE.createLiteralString();
            value.setValue(setup);
            ecoreSetupAction.setValue(value);
            node.getSetupParts().add(ecoreSetupAction);
            attachActionToActivity(ecoreSetupAction);
        }
    }

    @objid ("e3e8a904-bb5f-433e-8e17-d33c783c544c")
    private void setTest(org.eclipse.uml2.uml.LoopNode node) {
        String test = getObjingElement().getTest();
        
        if (test.length() > 0) {
        
             org.eclipse.uml2.uml.ValueSpecificationAction ecoreTestAction = UMLFactory.eINSTANCE
                    .createValueSpecificationAction();
        
            ObjingEAnnotation.setIsDeleted(ecoreTestAction);
        
            ecoreTestAction.setName(test);
            org.eclipse.uml2.uml.LiteralString value = UMLFactory.eINSTANCE.createLiteralString();
            value.setValue(test);
            ecoreTestAction.setValue(value);
            node.getTests().add(ecoreTestAction);
            attachActionToActivity(ecoreTestAction);
        }
    }

    @objid ("195ac640-8d5b-42eb-ba53-35857c7f47fb")
    private void attachActionToActivity(org.eclipse.uml2.uml.ValueSpecificationAction action) {
        // Setting composition relation (attach to  org.eclipse.uml2.uml.Activity)
        Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                .getEnclosingElement(getObjingElement(), getObjingElement().getMClass().getMetamodel().getMClass(Activity.class));
        
        if (enclosingActivity != null) {
        
            org.eclipse.uml2.uml.Element ecoreActivity = GenerationProperties.getInstance().getMappedElement(enclosingActivity);
        
            if (ecoreActivity instanceof  org.eclipse.uml2.uml.Activity) {
                ((org.eclipse.uml2.uml.Activity) ecoreActivity).getNodes().add(action);
            }
        
        }
    }

    @objid ("b9ceee1d-c5d7-4321-9736-aee054962fb4")
    @Override
    public LoopNode getObjingElement() {
        return (LoopNode) super.getObjingElement();
    }

}
