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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("0d7be69c-5916-4b86-afa9-3f254f783ae5")
public class ELoopNode extends EStructuredActivityNode {
    @objid ("272000d2-4676-4370-91cc-4d87444ff138")
    private org.eclipse.uml2.uml.LoopNode ecoreElement;

    @objid ("1f23790d-11af-496e-b690-fabd0dcd55fc")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createLoopNode();
    }

    @objid ("6dbcad6b-ed7a-4a21-b683-18a99cd4a178")
    public ELoopNode(org.eclipse.uml2.uml.LoopNode element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("349df57b-5799-46eb-a027-0a658f8dd8a0")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        // Part of the Properties are setted when mapping the super type
        // (org.eclipse.uml2.uml.StructuredActivityNode)
        
        // Properties specific to org.eclipse.uml2.uml.LoopNodes:
        setIsTestedFirst((LoopNode) objingElt);
        setBody((LoopNode) objingElt);
        setSetup((LoopNode) objingElt);
        setTest((LoopNode) objingElt);
    }

    @objid ("062535ca-a668-4c23-9f04-c64e6dc974f9")
    private void setIsTestedFirst(LoopNode node) {
        node.setIsTestedFirst(this.ecoreElement.isTestedFirst());
    }

    @objid ("052a67e4-2d86-4d1f-a436-3356dcc20b4d")
    private void setBody(LoopNode node) {
        for (Object ecoreBody : this.ecoreElement.getBodyParts()) {
            Object objingBody = ReverseProperties.getInstance().getMappedElement((org.eclipse.uml2.uml.Element) ecoreBody);
            if (objingBody instanceof ActivityNode)
                node.getBody().add((ActivityNode) objingBody);
        }
    }

    @objid ("39dfc420-ee7c-41b6-9fc2-0180c81fa207")
    private void setSetup(LoopNode node) {
        String objingSetup = "";
        for (Object ecoreSetupPart : this.ecoreElement.getSetupParts()) {
            if (ecoreSetupPart instanceof  org.eclipse.uml2.uml.ValueSpecificationAction) {
                 org.eclipse.uml2.uml.ValueSpecification ecoreSetup = ( (org.eclipse.uml2.uml.ValueSpecificationAction) ecoreSetupPart)
                .getValue();
                if (ecoreSetup != null) {
                    String stringValue = ecoreSetup.stringValue();
                    if (stringValue != null)
                        objingSetup = objingSetup.concat(stringValue);
                }
            } else if (ecoreSetupPart != null) {
                String ecoreName = ( (org.eclipse.uml2.uml.ExecutableNode) ecoreSetupPart).getName();
                if (ecoreName != null)
                    objingSetup = objingSetup.concat(ecoreName);
            }
        }
        node.setSetup(objingSetup);
    }

    @objid ("1ee3a966-346a-418a-b626-c90fcbded171")
    private void setTest(LoopNode node) {
        String objingTest = "";
        for (Object ecoreTestPart : this.ecoreElement.getTests()) {
            if (ecoreTestPart instanceof  org.eclipse.uml2.uml.ValueSpecificationAction) {
        
                 org.eclipse.uml2.uml.ValueSpecification ecoreTest = ( (org.eclipse.uml2.uml.ValueSpecificationAction) ecoreTestPart)
                .getValue();
                if (ecoreTest != null) {
                    String stringValue = ecoreTest.stringValue();
                    if (stringValue != null)
                        objingTest = objingTest.concat(stringValue);
                }
            } else if (ecoreTestPart != null) {
                String ecoreName = ( (org.eclipse.uml2.uml.ExecutableNode) ecoreTestPart).getName();
                if (ecoreName != null)
                    objingTest = objingTest.concat(ecoreName);
            }
        }
        node.setTest(objingTest);
    }

}
