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
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.app.core.IModelioEventService;
import org.modelio.app.core.events.ModelioEvent;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.model.browser.view.handlers.create.CreateCmsElementHandler;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This handler creates a BpmnCollaboration, its default collaboration diagram and opens the PCD.
 */
@objid ("3d848502-b068-4a4e-b3d5-600efca51886")
public class CreateBpmnCollaborationHandler extends CreateCmsElementHandler {
    @objid ("f38a09cd-2015-486e-aca7-2af5794b5c18")
    @Inject
    protected IModelioEventService eventService;

    /**
     * BpmnCollaboration post-creation => create a Collaboration Diagram
     */
    @objid ("056bcec7-965f-4341-becb-218eabfe7aa0")
    @Override
    protected void postCreationStep(MObject createdElement, IMModelServices mmServices) {
        IStandardModelFactory mmFactory = mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        
        BpmnCollaboration collab = (BpmnCollaboration) createdElement;
        
        BpmnCollaborationDiagram diagram = mmFactory.createBpmnCollaborationDiagram();
        collab.getProduct().add(diagram);
        diagram.setName(mmServices.getElementNamer().getUniqueName(diagram));
        
        BpmnProcess process = collab.getDefinedProcess();
        if (process != null) {
            // Create a participant
            BpmnParticipant participant = mmFactory.createBpmnParticipant();
            participant.setName(process.getName());
            participant.setProcess(process);
            participant.setContainer(collab);
        
            // Unamsk it
            try (IDiagramHandle dh = Modelio.getInstance().getDiagramService().getDiagramHandle(diagram)) {
                dh.unmask(participant, 0, 0);
                dh.save();
            }
        }
    }

    /**
     * BpmnCollaboration creation post-commit => open the diagram that has been created with the process
     */
    @objid ("eee43714-ed60-4266-98c3-671e1d6f39f3")
    @Override
    protected void postCommit(MPart part, MObject element, IMModelServices mmServices) {
        final BpmnCollaborationDiagram param = ((BpmnCollaboration) element).getProduct(BpmnCollaborationDiagram.class).get(0);
        Display.getDefault().asyncExec(
                () -> CreateBpmnCollaborationHandler.this.eventService.postAsyncEvent(
                        () -> "CreateBpmnCollaborationHandler.postCommit()",
                        ModelioEvent.EDIT_ELEMENT,
                        param));
    }

}
