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
package org.modelio.xmi.model.ecore;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.ExecutableNode;
import org.eclipse.uml2.uml.SequenceNode;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("08d3d55e-f178-4680-9292-5a9108619472")
public class EActivityNode extends ENamedElement {
    @objid ("30c50b21-ccb2-41c8-aeb3-7fcc250fe3f0")
    public  EActivityNode(final org.eclipse.uml2.uml.ActivityNode element) {
        super(element);
    }

    @objid ("691b1b36-1442-4301-b25c-8e28577d124e")
    @Override
    public void attach(Element objingElt) {
        org.eclipse.uml2.uml.ActivityNode ecoreElement = (org.eclipse.uml2.uml.ActivityNode) getEcoreElement();
        if (ecoreElement.getOwner() != null) {
            if (EcoreModelNavigation.isInClause(ecoreElement)) {
                attachToClause(objingElt);
            } else if (EcoreModelNavigation.isInPartition(ecoreElement)) {
                attachToPartition(objingElt);
            } else if (EcoreModelNavigation.isInStructuredActivityNode(ecoreElement)) {
                attachToStructuredActivityNode(objingElt);
            } else if (EcoreModelNavigation.isInActivity(ecoreElement)) {
                attachToActivity(objingElt);
            } else if (EcoreModelNavigation.isInActionInputPin(ecoreElement)) {
                attachToActivity(objingElt);
            } else if (ecoreElement instanceof ExecutableNode) {
                attachExecutableNode(objingElt);
            } else {
                objingElt.delete();
            }
        }
        
    }

    @objid ("25424326-2ee8-472e-b4e3-4276d2d3760f")
    private void attachToPartition(final Element objingElt) {
        List<org.eclipse.uml2.uml.ActivityPartition> ecorePartitions = EcoreModelNavigation
                .getOwnerPartitions((org.eclipse.uml2.uml.ActivityNode)getEcoreElement());
        
        if (ecorePartitions.size() > 0) {
            org.eclipse.uml2.uml.ActivityPartition ecorePartition = null;
            if (ecorePartitions.size() > 1)
                ecorePartition = EcoreModelNavigation
                .getLeafPartition(ecorePartitions);
            else
                ecorePartition = ecorePartitions.get(0);
        
            if (ecorePartition != null) {
                ActivityPartition objingPartition = (ActivityPartition)  ReverseProperties.getInstance()
                        .getMappedElement(ecorePartition);
                ((ActivityNode) objingElt).setOwnerPartition(objingPartition);
            }
        }
        
    }

    @objid ("bd3661fe-a17a-49c3-905a-036af05fa908")
    private void attachToStructuredActivityNode(final Element objingElt) {
        org.eclipse.uml2.uml.StructuredActivityNode ecoreNode = ((org.eclipse.uml2.uml.ActivityNode) getEcoreElement()).getInStructuredNode();
        if (ecoreNode != null) {
            StructuredActivityNode objingNode = (StructuredActivityNode)  ReverseProperties.getInstance()
                    .getMappedElement(ecoreNode);
            if (objingNode != null) {
                if (objingNode instanceof ConditionalNode)
                    attachToActivity(objingElt);
                else
                    ((ActivityNode) objingElt).setOwnerNode(objingNode);
            }
        
        }
        
    }

    @objid ("33d4b1b1-fdf9-4b4b-8177-42a2d4edfeaa")
    private void attachToClause(final Element objingElt) {
        org.eclipse.uml2.uml.Clause ecoreClause = EcoreModelNavigation.getOwnerClause((org.eclipse.uml2.uml.ActivityNode)getEcoreElement());
        if (ecoreClause != null) {
            Clause objingClause = (Clause)  ReverseProperties.getInstance()
                    .getMappedElement(ecoreClause);
            if (objingClause != null)
                ((ActivityNode) objingElt)
                .setOwnerClause(objingClause);
        }
        
    }

    @objid ("b3f7bbb8-5778-48b8-876c-b744b8ade233")
    private void attachToActivity(final Element objingElt) {
        org.eclipse.uml2.uml.Activity ecoreActivity = EcoreModelNavigation
                .getOwnerActivity((org.eclipse.uml2.uml.ActivityNode) getEcoreElement());
        
        if (ecoreActivity != null) {
            Activity objingActivity = (Activity)  ReverseProperties.getInstance()
                    .getMappedElement(ecoreActivity);
        
            if (objingActivity != null)
                ((ActivityNode) objingElt).setOwner(objingActivity);
        }
        
    }

    @objid ("3d10795d-7722-44fb-ade7-b9ea0899bea9")
    private void attachExecutableNode(final Element objingElt) {
        org.eclipse.uml2.uml.Element ecoreNode = ((org.eclipse.uml2.uml.ExecutableNode) getEcoreElement()).getOwner();
        if ((ecoreNode != null)  && (ecoreNode instanceof SequenceNode)){
            StructuredActivityNode objingNode = (StructuredActivityNode)  ReverseProperties.getInstance()
                    .getMappedElement(ecoreNode);
            if (objingNode != null) {
                  ((ActivityNode) objingElt).setOwnerNode(objingNode);
            }
        
        }
        
    }

}
