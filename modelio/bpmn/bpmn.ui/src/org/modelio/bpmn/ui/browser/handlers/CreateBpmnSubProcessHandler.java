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

package org.modelio.bpmn.ui.browser.handlers;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.diagram.editor.bpmn.layout.BpmnLayouter;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This handler:
 * <ol>
 * <li>creates a BpmnSubProcess.</li>
 * <li>creates Start and End events</li>
 * <li>creates a dumb flow betwenn start and end</li>
 * <li>creates a sub process diagram</li>
 * <li>opens the sub process diagram</li>
 * <li>auto-layout the diagram</li>
 * </ol>
 */
@objid ("07ee1ade-7638-4a74-bd24-afbc99adc7ce")
public class CreateBpmnSubProcessHandler extends CreateBpmnFlowElement {
    @objid ("08b1dffc-f46b-4111-a7b8-74e80882714d")
    @Inject
     IActivationService activationService;

    @objid ("e6ff5469-69d7-47cc-86b9-95b400337f26")
    @Inject
     IModelioNavigationService navigationService;

    @objid ("426994c6-0f3b-4682-8654-e5652742328f")
    @Override
    protected void postCreationStep(MObject createdElement, IMModelServices mmServices) {
        super.postCreationStep(createdElement, mmServices);
        
        BpmnSubProcess subProcess = (BpmnSubProcess) createdElement;
        
        IStandardModelFactory modelFactory = mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        IElementNamer namer = mmServices.getElementNamer();
        
        if (!(createdElement instanceof BpmnAdHocSubProcess)) {
            populateDiagram(subProcess, modelFactory, namer);
        }
        
        // Create a diagram
        BpmnSubProcessDiagram diagram = modelFactory.createBpmnSubProcessDiagram();
        diagram.setOrigin(subProcess);
        diagram.setName(namer.getUniqueName(diagram));
        
        // Layout diagram
        new BpmnLayouter(diagram).run();
    }

    @objid ("8c00d5c6-b1fc-4e9d-85e4-36471da0079f")
    @Override
    protected void postCommit(MPart part, MObject element, IMModelServices mmServices) {
        super.postCommit(part, element, mmServices);
        
        if (!(element instanceof BpmnAdHocSubProcess)) {
            final BpmnSubProcessDiagram diagram = ((BpmnSubProcess) element).getProduct(BpmnSubProcessDiagram.class).get(0);
        
            // Open the diagram
            CreateBpmnSubProcessHandler.this.activationService.activateMObject(diagram);
        
            // Select the diagram in the browser
            this.navigationService.fireNavigate(diagram);
            this.navigationService.fireNavigate(diagram.getCompositionOwner());
        }
    }

    @objid ("8abb396a-65b4-4ddf-b8ef-2c1b7a13af48")
    @Override
    protected MObject doCreate(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype, IMModelServices mmServices) {
        BpmnSubProcess subProcess = (BpmnSubProcess) super.doCreate(owner, metaclass, dependency, stereotype, mmServices);
        return subProcess;
    }

    @objid ("ae205d66-fab5-4c50-89e9-8981750935d8")
    @Override
    protected boolean doCanExecute(MObject owner, MClass metaclass, MDependency dependency, Stereotype stereotype) {
        return super.doCanExecute(owner, metaclass, dependency, stereotype);
    }

    /**
     * Create an initial diagram contents:
     * <ul>
     * <li>A {@link BpmnStartEvent}</li>
     * <li>A {@link BpmnEndEvent}</li>
     * <li>A {@link BpmnTask}</li>
     * <li>A {@link BpmnSequenceFlow} from the start event to the task.</li>
     * <li>A {@link BpmnSequenceFlow} from the task to the end event.</li>
     * </ul>
     */
    @objid ("60a4031a-85a4-42b3-ae5a-fed1566846a2")
    private void populateDiagram(BpmnSubProcess subProcess, IStandardModelFactory modelFactory, IElementNamer namer) {
        // Create a Start event
        BpmnStartEvent startEvent = modelFactory.createBpmnStartEvent();
        startEvent.setSubProcess(subProcess);
        startEvent.setName(namer.getUniqueName(startEvent));
        
        // Create an End event
        BpmnEndEvent endEvent = modelFactory.createBpmnEndEvent();
        endEvent.setSubProcess(subProcess);
        endEvent.setName(namer.getUniqueName(endEvent));
        
        // Create a dumb task
        BpmnTask task = modelFactory.createBpmnTask();
        task.setSubProcess(subProcess);
        task.setName(namer.getUniqueName(task));
        
        // Create a flow between start and task
        BpmnSequenceFlow flow1 = modelFactory.createBpmnSequenceFlow();
        flow1.setSourceRef(startEvent);
        flow1.setTargetRef(task);
        flow1.setSubProcess(subProcess);
        
        // Create a flow between task and end
        BpmnSequenceFlow flow2 = modelFactory.createBpmnSequenceFlow();
        flow2.setSourceRef(task);
        flow2.setTargetRef(endEvent);
        flow2.setSubProcess(subProcess);
    }

}
