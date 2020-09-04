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

package org.modelio.bpmn.ui.browser.handlers;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.diagram.editor.bpmn.layout.BpmnLayouter;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.model.browser.view.handlers.create.CreateCmsElementHandler;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This handler creates a BpmnProcess, its default design diagram and opens the PDD
 */
@objid ("8c975a0f-c9d8-11e1-b479-001ec947c8cc")
public class CreateBpmnProcessHandler extends CreateCmsElementHandler {
    @objid ("11a28ee4-a493-49a6-9b33-ad73af971f01")
    @Inject
     IModelioEventService eventService;

    /**
     * BPMNProcess post-creation => create a Process Design Diagram
     */
    @objid ("4e57e2a9-ccde-11e1-97e5-001ec947c8cc")
    @Override
    protected void postCreationStep(MObject createdElement, IMModelServices mmServices) {
        IStandardModelFactory modelFactory = mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        IElementNamer namer = mmServices.getElementNamer();
        
        BpmnProcess process = (BpmnProcess) createdElement;
        
        BpmnProcessDesignDiagram diagram = modelFactory.createBpmnProcessDesignDiagram();
        process.getProduct().add(diagram);
        diagram.setName(namer.getUniqueName(diagram));
        
        populateDiagram(modelFactory, namer, process);
        
        // Layout diagram
        new BpmnLayouter(diagram).run();
    }

    /**
     * BPMNProcess creation post-commit => open the diagram that has been created with the process
     */
    @objid ("53a54878-ccff-11e1-97e5-001ec947c8cc")
    @Override
    protected void postCommit(MPart part, MObject element, IMModelServices mmServices) {
        final BpmnProcessDesignDiagram diagram = ((BpmnProcess) element).getProduct(BpmnProcessDesignDiagram.class).get(0);
        Display.getDefault().asyncExec(
                () -> CreateBpmnProcessHandler.this.eventService.postAsyncEvent(
                        () -> "CreateBpmnProcessHandler.postCommit()",
                        ModelioEvent.EDIT_ELEMENT,
                        diagram));
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
    @objid ("6636a7e3-67cf-4996-ab43-8b0def80a627")
    private void populateDiagram(IStandardModelFactory modelFactory, IElementNamer namer, BpmnProcess process) {
        // Create a Start event
        BpmnStartEvent startEvent = modelFactory.createBpmnStartEvent();
        startEvent.setContainer(process);
        startEvent.setName(namer.getUniqueName(startEvent));
        
        // Create an End event
        BpmnEndEvent endEvent = modelFactory.createBpmnEndEvent();
        endEvent.setContainer(process);
        endEvent.setName(namer.getUniqueName(endEvent));
        
        // Create a dumb task
        BpmnTask task = modelFactory.createBpmnTask();
        task.setContainer(process);
        task.setName(namer.getUniqueName(task));
        
        // Create a flow between start and task
        BpmnSequenceFlow flow1 = modelFactory.createBpmnSequenceFlow();
        flow1.setSourceRef(startEvent);
        flow1.setTargetRef(task);
        flow1.setContainer(process);
        
        // Create a flow between task and end
        BpmnSequenceFlow flow2 = modelFactory.createBpmnSequenceFlow();
        flow2.setSourceRef(task);
        flow2.setTargetRef(endEvent);
        flow2.setContainer(process);
    }

}
