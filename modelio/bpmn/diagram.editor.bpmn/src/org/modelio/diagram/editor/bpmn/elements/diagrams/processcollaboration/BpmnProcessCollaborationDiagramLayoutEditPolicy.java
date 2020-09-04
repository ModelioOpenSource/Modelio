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

package org.modelio.diagram.editor.bpmn.elements.diagrams.processcollaboration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.editor.bpmn.elements.participant.CreateBpmnParticipantCommand;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramEditLayoutPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.commands.NodeChangeLayoutCommand;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Process;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This policy extends the standard diagram edit layout policy with some specific behavior for {@link BpmnParticipant} and {@link BpmnProcess} elements.
 */
@objid ("d02548ab-6f71-49eb-9364-363c494575b2")
class BpmnProcessCollaborationDiagramLayoutEditPolicy extends DiagramEditLayoutPolicy {
    @objid ("a3ae6805-9883-4638-9eb5-c717d67b8f45")
    @Override
    protected Command getCreateCommand(CreateRequest request) {
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(request);
        if (ctx != null) {
            final Class<? extends MObject> cls = ctx.getJavaClass();
            BpmnCollaboration hostElement = getHostElement();
            MObject elementToUnmask = ctx.getElementToUnmask();
            GmCompositeNode hostGmNode = getHostCompositeNode();
        
            IGmDiagram gmDiagram = getHostCompositeNode().getDiagram();
            boolean isParticipantCreation = cls != BpmnProcess.class && (elementToUnmask == null || (!isSmartProcess(elementToUnmask, gmDiagram) && isSmartParticipant(elementToUnmask, gmDiagram)));
            if (isParticipantCreation || cls == BpmnProcess.class || isSmartProcess(elementToUnmask, gmDiagram)) {
                if (elementToUnmask != null && !hostGmNode.canUnmask(elementToUnmask)) {
                    return null;
                }
        
                Object requestConstraint = getConstraintFor(request);
                return new CreateBpmnParticipantCommand(hostElement, hostGmNode, elementToUnmask, isParticipantCreation, ctx.getStereotype(), ctx.getProperties(), requestConstraint);
            }
        }
        return super.getCreateCommand(request);
    }

    @objid ("d38c7aa8-05bf-4fce-8181-e6be8632ed7e")
    @Override
    protected boolean canHandle(MClass metaclass, String dep) {
        return getHostCompositeNode().canCreate(metaclass.getJavaInterface());
    }

    @objid ("db76d780-c24c-4101-88da-df7788b9aa79")
    @Override
    protected BpmnCollaboration getHostElement() {
        return (BpmnCollaboration) getHostCompositeNode().getDiagram().getRelatedElement().getOrigin();
    }

    @objid ("b811f53b-d7be-4043-8524-c813cc22d264")
    @Override
    protected Command createAddCommand(ChangeBoundsRequest request, final EditPart child, final Object constraint) {
        // No reparenting in the collaboration diagram
        return null;
    }

    @objid ("e3f553d3-8a1a-433c-bedd-7760f589be4d")
    @Override
    protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart movedEditPart, Object constraint) {
        // if child is a 'node' it usually can be resized and/or moved
        if (movedEditPart instanceof AbstractNodeEditPart || movedEditPart.getModel() instanceof GmDrawing) {
            final NodeChangeLayoutCommand command = new NodeChangeLayoutCommand();
            command.setModel(movedEditPart.getModel());
            command.setConstraint(constraint);
            return command;
        }
        return null;
    }

    @objid ("9388119c-c2fb-4fc1-b346-8da3248d96ac")
    private boolean isSmartProcess(MObject elementToUnmask, IGmDiagram gmDiagram) {
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass processMetaclass = metamodel.getMClass(BpmnProcess.MQNAME);
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(Process.MdaTypes.STEREOTYPE_ELT, linkMetaclass, processMetaclass, elementToUnmask.getMClass());
    }

    @objid ("032439cb-1ab0-4686-90a7-e8aaf4e0fcc0")
    private boolean isSmartParticipant(MObject elementToUnmask, IGmDiagram gmDiagram) {
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass participantMetaclass = metamodel.getMClass(BpmnParticipant.MQNAME);
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(Reference.MdaTypes.STEREOTYPE_ELT, linkMetaclass, participantMetaclass, elementToUnmask.getMClass());
    }

}
