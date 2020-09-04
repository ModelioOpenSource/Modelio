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

package org.modelio.diagram.editor.bpmn.wizard;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessStructuredStyleKeys;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Transmuter implementation for {@link BpmnSubProcess} elements.
 */
@objid ("8bb952e0-7ccd-4dd9-a767-d62ffe7eb63c")
public class BpmnSubProcessTransmuter extends BpmnActivityTransmuter {
    /**
     * Public constructor required by the {@link TransformerRegistry}.
     * 
     * @param targetMetaclass target metaclass of the transmutation
     */
    @objid ("9f1752da-2de5-475b-94e3-c77156f16316")
    public BpmnSubProcessTransmuter(String targetMetaclass) {
        super(targetMetaclass);
    }

    @objid ("fb745cfa-36ee-4ee0-9e90-6a1f45c9a6dc")
    @Override
    protected MObject transmuteElement(MObject elementToBeTransmuted, IMModelServices modelServices) {
        MObject newElement = super.transmuteElement(elementToBeTransmuted, modelServices);
        
        if (newElement instanceof BpmnSubProcess) {
            BpmnSubProcess newSubProcess = (BpmnSubProcess) newElement;
            if (elementToBeTransmuted instanceof BpmnSubProcess) {
                // Keep subprocess contents
                BpmnSubProcess oldSubProcss = (BpmnSubProcess) elementToBeTransmuted;
        
                for (BpmnFlowElement subelement : new ArrayList<>(oldSubProcss.getFlowElement())) {
                    newSubProcess.getFlowElement().add(subelement);
                }
        
                // Keep sub-process diagrams
                for (BpmnSubProcessDiagram subelement : new ArrayList<>(oldSubProcss.getProduct(BpmnSubProcessDiagram.class))) {
                    newSubProcess.getProduct().add(subelement);
                }
            } else {
                // Create a diagram in the new subprocess
                final IStandardModelFactory modelFactory = modelServices.getModelFactory().getFactory(IStandardModelFactory.class);
                BpmnSubProcessDiagram spdd = modelFactory.createBpmnSubProcessDiagram();
                spdd.setOrigin(newSubProcess);
                spdd.setName(newSubProcess.getName());
            }
        }
        return newElement;
    }

    @objid ("db4b59ea-c4c4-4433-9cbb-992eeeb486c4")
    @Override
    protected GmNodeModel transmuteGm(MObject transmutedElement, GmModel gmToBeTransmuted) {
        GmNodeModel transmutedGm = super.transmuteGm(transmutedElement, gmToBeTransmuted);
        
        if (transmutedElement instanceof BpmnAdHocSubProcess) {
            transmutedGm.getDisplayedStyle().setProperty(GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT, false);
        } else if (transmutedElement instanceof BpmnSubProcess) {
            transmutedGm.getDisplayedStyle().setProperty(GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT, true);
        }
        return transmutedGm;
    }

}
