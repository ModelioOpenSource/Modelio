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
package org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.vcore.model.api.IElementNamerService;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * When creating a sub process, create its diagram as well.
 */
@objid ("d566b123-0cf5-4ffc-922d-3a9fd3ba4e19")
public class CreateBpmnSubProcessCommand extends DefaultCreateElementCommand {
    /**
     * Creates a node creation command.
     * @param parentElement the element that lead to this command.
     * @param parentNode The parent editPart
     * @param context Details on the MObject and/or the node to create
     * @param requestConstraint Request Constraint
     */
    @objid ("0257512e-5dc4-4aef-8bcc-bc0e895d23b9")
    public  CreateBpmnSubProcessCommand(MObject parentElement, GmCompositeNode parentNode, ModelioCreationContext context, Object requestConstraint) {
        super(parentElement, parentNode, context, requestConstraint);
    }

    @objid ("d6b271a2-4fff-4286-893d-512a0449b299")
    @Override
    protected MObject createElement(IGmDiagram diagram) {
        // Call initial behavior
        final BpmnSubProcess subProcess = (BpmnSubProcess) super.createElement(diagram);
        
        // Add the subprocess to the current lane if needed
        MObject relatedElement = this.parentNode.getRelatedElement();
        if (relatedElement instanceof BpmnLane) {
            subProcess.getLane().add((BpmnLane) relatedElement);
        }
        
        // Create the BpmnSubProcessDiagram
        
        MTools mTools = MTools.get(subProcess);
        IStandardModelFactory modelFactory = mTools.getModelFactory(IStandardModelFactory.class);
        IElementNamerService namer = mTools.getNamer();
        
        BpmnSubProcessDiagram subDiagram = modelFactory.createBpmnSubProcessDiagram();
        subDiagram.setOrigin(subProcess);
        subDiagram.setName(namer.getUniqueName(subDiagram));
        
        if (!(subProcess instanceof BpmnAdHocSubProcess)) {
            populateDiagram(subProcess, modelFactory, namer, subDiagram);
        }
        return subProcess;
    }

    /**
     * Create an initial diagram contents, and layout it:
     * <ul>
     * <li>A {@link BpmnStartEvent}</li>
     * <li>A {@link BpmnEndEvent}</li>
     * <li>A {@link BpmnTask}</li>
     * <li>A {@link BpmnSequenceFlow} from the start event to the task.</li>
     * <li>A {@link BpmnSequenceFlow} from the task to the end event.</li>
     * </ul>
     */
    @objid ("c09d0edf-609e-4a25-9df0-ca580d437fe5")
    private void populateDiagram(BpmnSubProcess subProcess, IStandardModelFactory modelFactory, IElementNamerService namer, BpmnSubProcessDiagram diagram) {
        
    }

}
