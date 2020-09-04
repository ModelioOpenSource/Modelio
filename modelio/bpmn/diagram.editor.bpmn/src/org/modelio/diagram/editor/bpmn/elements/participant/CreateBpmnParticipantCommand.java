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

package org.modelio.diagram.editor.bpmn.elements.participant;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.editor.bpmn.layout.BpmnLayouter;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Process;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific command to handle the creation of one BPMN participant.
 */
@objid ("74407378-0dba-4588-8304-32145ac0fc1f")
public class CreateBpmnParticipantCommand extends Command {
    @objid ("b66ca835-c4f8-4f98-97a4-30a843df3f34")
    private final BpmnCollaboration parentCollaboration;

    @objid ("af884225-c2c5-47b4-973c-42a7076d0d1d")
    private boolean isParticipantCreation;

    @objid ("9d92bd33-d7ad-4a63-a03a-ef68d1edfe02")
    private final Object newConstraint;

    @objid ("cac660ff-0a98-47bd-81cc-edcb3a44f1e2")
    private final GmCompositeNode parentNode;

    @objid ("fc727586-4040-48e5-8721-6d13efc0b914")
    private MObject elementToUnmask;

    @objid ("8b070583-8841-4e21-8635-bf4a1c115175")
    private Stereotype participantStereotype;

    @objid ("7e701c64-edf0-4e02-8b84-68b223eb030b")
    private Map<String, Object> creationProperties;

    /**
     * Creates a node creation command.
     * 
     * @param parentCollaboration the element that lead to this command.
     * @param parentNode The parent editPart
     * @param elementToUnmask The element to unmask. Might be <code>null</code>.
     * @param isParticipantCreation <code>true</code> if the command is a simple participant creation.
     * @param participantStereotype an optional {@link Stereotype} to apply on the new BPMN participant
     * @param creationProperties the creation properties or <tt>null</tt> if no property was defined.
     * @param requestConstraint Request Constraint
     */
    @objid ("0533624f-46c4-4005-830a-15c7296bc6f0")
    public CreateBpmnParticipantCommand(BpmnCollaboration parentCollaboration, GmCompositeNode parentNode, MObject elementToUnmask, boolean isParticipantCreation, Stereotype participantStereotype, Map<String, Object> creationProperties, Object requestConstraint) {
        this.parentNode = parentNode;
        this.parentCollaboration = parentCollaboration;
        this.newConstraint = requestConstraint;
        this.elementToUnmask = elementToUnmask;
        this.isParticipantCreation = isParticipantCreation;
        this.participantStereotype = participantStereotype;
        this.creationProperties = creationProperties;
    }

    @objid ("518810bc-d3d7-4639-b219-aeec44133d37")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        BpmnParticipant participant = null;
        BpmnProcess process = null;
        ModelElement reference = null;
        if (this.isParticipantCreation) {
            if (this.elementToUnmask instanceof BpmnParticipant) {
                participant = (BpmnParticipant) this.elementToUnmask;
            } else {
                reference = (ModelElement) this.elementToUnmask;
            }
        } else if (this.elementToUnmask instanceof BpmnProcess) {
            process = (BpmnProcess) this.elementToUnmask;
            participant = getExistingParticipant(this.parentCollaboration, process);
        }
        
        if (participant == null) {
            final IModelManager modelManager = diagram.getModelManager();
            final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
            final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
            final IElementNamer elementNamer = modelManager.getModelServices().getElementNamer();
        
            // Optionally create the Process
            if (process == null && !this.isParticipantCreation) {
                process = createBpmnProcess(modelFactory, elementNamer, getBpmnContext());
            }
        
            // Create the Participant
            participant = modelFactory.createBpmnParticipant();
            participant.setContainer(this.parentCollaboration);
            participant.setProcess(process);
        
            // Attach the stereotype if requested.
            if (this.participantStereotype != null) {
                participant.getExtension().add(this.participantStereotype);
            }
        
            // Configure element from properties
            elementConfigurer.configure(participant, this.creationProperties);
        
            // Set default name
            if (process == null) {
                participant.setName(elementNamer.getUniqueName(participant));
            } else {
                participant.setName(process.getName());
            }
        
            if (reference != null) {
                MClass linkMetaclass = modelManager.getMetamodel().getMClass(MethodologicalLink.MQNAME);
                IMdaExpert mdaExpert = modelManager.getMdaExpert();
                if (mdaExpert.canLink(Reference.MdaTypes.STEREOTYPE_ELT, linkMetaclass, participant.getMClass(), reference.getMClass())) {
                    Reference.setTarget(participant, reference);
                }
            }
        }
        
        diagram.unmask(this.parentNode, participant, this.newConstraint);
    }

    @objid ("b55bc17d-7745-4d71-ae6d-efbdb48daaa6")
    @Override
    public boolean canExecute() {
        // The diagram must be valid and modifiable.
        final IGmDiagram gmDiagram = this.parentNode.getDiagram();
        AbstractDiagram diagram = gmDiagram.getRelatedElement();
        if (!MTools.getAuthTool().canModify(diagram)) {
            return false;
        }
        
        if (!MTools.getAuthTool().canModify(diagram.getOrigin())) {
            return false;
        }
        
        // If it is an actual creation (and not a simple unmasking).
        if (this.elementToUnmask == null) {
            // The parent element must be modifiable or both must be CMS nodes.
            if (this.isParticipantCreation) {
                if (!MTools.getAuthTool().canAdd(this.parentCollaboration, BpmnParticipant.class)) {
                    return false;
                }
            } else {
                if (!MTools.getAuthTool().canAdd(this.parentCollaboration, BpmnProcess.class)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return a participant already referencing the process being unmasked.
     */
    @objid ("518f1adc-f855-44f3-bffe-cccf9600448e")
    private BpmnParticipant getExistingParticipant(BpmnCollaboration collaboration, BpmnProcess process) {
        for (BpmnParticipant p : collaboration.getParticipants()) {
            if (process.equals(p.getProcess())) {
                return p;
            }
        }
        return null;
    }

    @objid ("1df2b1a5-8103-489e-ad38-3c286aba85db")
    private BpmnProcess createBpmnProcess(IStandardModelFactory modelFactory, IElementNamer elementNamer, MObject diagramContext) {
        BpmnProcess newProcess = null;
        if (diagramContext instanceof NameSpace) {
            newProcess = modelFactory.createBpmnProcess();
            newProcess.setOwner((NameSpace) diagramContext);
            newProcess.setName(elementNamer.getUniqueName(newProcess));
            populateDiagram(modelFactory, elementNamer, newProcess);
        } else if (diagramContext instanceof Operation) {
            newProcess = modelFactory.createBpmnProcess();
            newProcess.setOwnerOperation((Operation) diagramContext);
            newProcess.setName(elementNamer.getUniqueName(newProcess));
            populateDiagram(modelFactory, elementNamer, newProcess);
        } else {
            // Invalid context
            return null;
        }
        
        if (this.elementToUnmask != null) {
            // Set default name
            newProcess.setName(this.elementToUnmask.getName());
        
            final IModelManager modelManager = this.parentNode.getDiagram().getModelManager();
            MClass linkMetaclass = modelManager.getMetamodel().getMClass(MethodologicalLink.MQNAME);
            IMdaExpert mdaExpert = modelManager.getMdaExpert();
            if (mdaExpert.canLink(Process.MdaTypes.STEREOTYPE_ELT, linkMetaclass, newProcess.getMClass(), this.elementToUnmask.getMClass())) {
                Process.setTarget(newProcess, (ModelElement) this.elementToUnmask);
            }
        }
        return newProcess;
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
    @objid ("6824748a-5d26-4ef9-8526-aa402fd1b1cd")
    private void populateDiagram(IStandardModelFactory modelFactory, IElementNamer elementNamer, BpmnProcess process) {
        // Create a Process Design diagram
        BpmnProcessDesignDiagram diagram = modelFactory.createBpmnProcessDesignDiagram();
        diagram.setOrigin(process);
        diagram.setName(elementNamer.getUniqueName(diagram));
        
        // Create a Start event
        BpmnStartEvent startEvent = modelFactory.createBpmnStartEvent();
        startEvent.setContainer(process);
        startEvent.setName(elementNamer.getUniqueName(startEvent));
        
        // Create an End event
        BpmnEndEvent endEvent = modelFactory.createBpmnEndEvent();
        endEvent.setContainer(process);
        endEvent.setName(elementNamer.getUniqueName(endEvent));
        
        // Create a dumb task
        BpmnTask task = modelFactory.createBpmnTask();
        task.setContainer(process);
        task.setName(elementNamer.getUniqueName(task));
        
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
        
        // Layout diagram
        new BpmnLayouter(diagram).run();
    }

    @objid ("a5e12111-ecc6-408b-8afc-b7f8f1e90870")
    private MObject getBpmnContext() {
        BpmnProcess definedProcess = this.parentCollaboration.getDefinedProcess();
        if (definedProcess != null) {
            return definedProcess.getCompositionOwner();
        } else {
            return this.parentCollaboration.getCompositionOwner();
        }
    }

}
