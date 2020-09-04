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

package org.modelio.diagram.editor.bpmn.wizard;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Transmuter implementation for {@link BpmnGateway} elements.
 */
@objid ("126880c4-e4ad-4d2b-9438-f0ee168f0f5f")
public class BpmnGatewayTransmuter extends AbstractElementTransmuter {
    /**
     * Public constructor required by the {@link TransformerRegistry}.
     * @param targetMetaclass target metaclass of the transmutation
     */
    @objid ("d75bb7ec-de90-48c2-86fe-eb3d68538c06")
    public BpmnGatewayTransmuter(final String targetMetaclass) {
        super(targetMetaclass);
    }

    @objid ("73a5f7cc-f1ec-48e3-8758-9c1613f21f49")
    @Override
    protected MObject transmuteElement(MObject elementToBeTransmuted, IMModelServices modelServices) {
        MObject owner = elementToBeTransmuted.getCompositionOwner();
        MExpert mExpert = owner.getMClass().getMetamodel().getMExpert();
        
        // Create the Element...
        final IStandardModelFactory modelFactory = modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
        MObject newElement = modelFactory.createElement(this.targetMetaclass);
        
        // The new element must be attached to its parent using the composition dependency
        // provided by the context.
        // If the context provides a null dependency, use the default dependency recommended by the metamodel
        MDependency effectiveDependency = mExpert.getDefaultCompositionDep(owner, newElement);
        // ... and attach it to its parent.
        
        try {
            owner.mGet(effectiveDependency).add(newElement);
        } catch (@SuppressWarnings ("unused") Exception e) {
            // The dependency indicated in the context cannot be used: try
            // to find a valid one!
            MDependency compositionDep = mExpert.getDefaultCompositionDep(owner, newElement);
            if (compositionDep != null) {
                owner.mGet(compositionDep).add(newElement);
            } else {
                newElement.delete();
                return null;
            }
        }
        if (newElement instanceof BpmnFlowElement && owner instanceof BpmnLane) {
            BpmnLane lane = (BpmnLane) owner;
            BpmnFlowElement flowElement = (BpmnFlowElement) newElement;
            flowElement.getLane().add(lane);
            lane.getFlowElementRef().add(flowElement);
        }
        
        // Set default name
        newElement.setName(modelServices.getElementNamer().getUniqueName(newElement));
        
        if (elementToBeTransmuted instanceof BpmnGateway && newElement instanceof BpmnGateway) {
            BpmnGateway newGateway = (BpmnGateway) newElement;
            BpmnGateway oldGateway = (BpmnGateway) elementToBeTransmuted;
            newGateway.setName(oldGateway.getName());
        
            for (BpmnLane lane : new ArrayList<>(oldGateway.getLane())) {
                newGateway.getLane().add(lane);
            }
        
            for (BpmnSequenceFlow subelement : new ArrayList<>(oldGateway.getOutgoing())) {
                newGateway.getOutgoing().add(subelement);
            }
        
            for (BpmnSequenceFlow subelement : new ArrayList<>(oldGateway.getIncoming())) {
                newGateway.getIncoming().add(subelement);
            }
        
            for (BpmnLane subelement : new ArrayList<>(oldGateway.getLane())) {
                newGateway.getLane().add(subelement);
            }
        
            for (Dependency subelement : new ArrayList<>(oldGateway.getImpactedDependency())) {
                newGateway.getImpactedDependency().add(subelement);
            }
        
            for (Dependency subelement : new ArrayList<>(oldGateway.getDependsOnDependency())) {
                newGateway.getDependsOnDependency().add(subelement);
            }
        
            BpmnSequenceFlow defaultflow = null;
            if (oldGateway instanceof BpmnExclusiveGateway) {
                defaultflow = ((BpmnExclusiveGateway) oldGateway).getDefaultFlow();
            } else if (oldGateway instanceof BpmnInclusiveGateway) {
                defaultflow = ((BpmnInclusiveGateway) oldGateway).getDefaultFlow();
            } else if (oldGateway instanceof BpmnComplexGateway) {
                defaultflow = ((BpmnComplexGateway) oldGateway).getDefaultFlow();
            }
        
            if (newGateway instanceof BpmnExclusiveGateway && defaultflow != null) {
                ((BpmnExclusiveGateway) newGateway).setDefaultFlow(defaultflow);
            } else if (newGateway instanceof BpmnInclusiveGateway && defaultflow != null) {
                ((BpmnInclusiveGateway) newGateway).setDefaultFlow(defaultflow);
            } else if (newGateway instanceof BpmnComplexGateway && defaultflow != null) {
                ((BpmnComplexGateway) newGateway).setDefaultFlow(defaultflow);
            }
        }
        return newElement;
    }

    @objid ("144b2444-9434-4480-8885-25208884682e")
    @Override
    public boolean isAvailable(AbstractDiagram diagram, ISelection selection) {
        return super.isAvailable(diagram, selection) && SelectionHelper.containsOnly(selection, BpmnGateway.class);
    }

}
