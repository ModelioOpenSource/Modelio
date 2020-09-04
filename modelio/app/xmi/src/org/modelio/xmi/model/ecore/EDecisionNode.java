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
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("40b2f8f5-38bf-48a8-b111-ab4ad84dcba9")
public class EDecisionNode extends EActivityNode {
    @objid ("124ef5a9-804a-4b38-b872-2a142dab1d43")
    private org.eclipse.uml2.uml.DecisionNode ecoreElement = null;

    @objid ("f490a68f-5616-4eec-98ba-c529304a9085")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createDecisionMergeNode();
    }

    @objid ("2ca4905e-c8f9-4499-86ed-430a01143c18")
    public EDecisionNode(org.eclipse.uml2.uml.DecisionNode element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("0d7722ee-e9df-417e-9033-62cd7a19b594")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setDecisionInput((DecisionMergeNode) objingElt);
    }

    @objid ("6a0f1d33-3be5-4ab8-a0bd-25b04fde46e1")
    private void setDecisionInput(DecisionMergeNode node) {
        org.eclipse.uml2.uml. Behavior decisionInput = this.ecoreElement.getDecisionInput();
        if (decisionInput != null && decisionInput instanceof org.eclipse.uml2.uml.OpaqueBehavior) {
            for (Object body : ((org.eclipse.uml2.uml.OpaqueBehavior) decisionInput).getBodies()) {
                if (!"".equals(body)) {
                    node.setDecisionInputBehavior((String) body);
                    break;
                }
            }
        }
    }

}
