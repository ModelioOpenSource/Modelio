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

package org.modelio.diagram.editor.bpmn.elements.participant;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.editor.bpmn.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specialization of the default drop request handling policy to add some smart interactions for {@link BpmnParticipant}.
 * <p>
 * Supports the drag & drop of a {@link BpmnProcess} a {@link Represents}-compatible or a {@link Reference}-compatible element to use it as the element's type.
 * </p>
 */
@objid ("f8af965b-ce7c-4401-a59d-e6e499e6034b")
public class BpmnParticipantElementDropEditPolicy extends DefaultElementDropEditPolicy {
    /**
     * Unmasking of a {@link BpmnProcess} a {@link Represents}-compatible or a {@link Reference}-compatible element provokes a smart interaction typing the {@link BpmnParticipant}.
     */
    @objid ("45025bc6-5094-4bff-b696-37209c702378")
    @Override
    protected EditPart getDropTargetEditPart(final ModelElementDropRequest request) {
        final Object model = getHost().getModel();
        if (!(model instanceof GmModel)) {
            return null;
        }
        
        if (request.isSmart() && request.getDroppedElements().length == 1) {
            GmModel gmModel = (GmModel) getHost().getModel();
            IGmDiagram gmDiagram = gmModel.getDiagram();
            MObject droppedElement = request.getDroppedElements()[0];
            if (isSmartProcess(droppedElement, gmDiagram)) {
                // Allow smart drop
                return getHost();
            }
        }
        return null;
    }

    @objid ("d0372cd3-fb0c-4cb6-b4ce-dd8b4c1c0dc7")
    @Override
    protected Command getSmartDropCommand(final ModelElementDropRequest request) {
        if (!request.isSmart()) {
            return null;
        }
        
        GmModel gmModel = (GmModel) getHost().getModel();
        IGmDiagram gmDiagram = gmModel.getDiagram();
        BpmnParticipant element = (BpmnParticipant) gmModel.getRelatedElement();
        // Smart type
        if (request.getDroppedElements().length == 1) {
            MObject droppedElement = request.getDroppedElements()[0];
            if (isSmartProcess(droppedElement, gmDiagram)) {
                return new SmartTypeBpmnParticipantCommand(element, (ModelElement) droppedElement, gmDiagram.getModelManager());
            }
        }
        return null;
    }

    @objid ("9bcfc0f5-1050-4b9e-9c7d-c79e15bdebdf")
    protected boolean isSmartProcess(MObject droppedElement, IGmDiagram gmDiagram) {
        if (droppedElement instanceof BpmnProcess) {
            return true;
        }
        
        IModelManager modelManager = gmDiagram.getModelManager();
        return BpmnParticipantElementDropEditPolicy.isMethodologicalLinkTarget(droppedElement, Represents.MdaTypes.STEREOTYPE_ELT, modelManager)
                || BpmnParticipantElementDropEditPolicy.isMethodologicalLinkTarget(droppedElement, Reference.MdaTypes.STEREOTYPE_ELT, modelManager);
    }

    @objid ("9cfd9def-b0e0-416d-8201-8ea9b2a81d0b")
    protected static boolean isMethodologicalLinkTarget(MObject droppedElement, Stereotype linkType, IModelManager modelManager) {
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass sourceMetaclass = metamodel.getMClass(BpmnParticipant.MQNAME);
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(linkType, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
    }

    /**
     * Command produced by the drop element edit policy that can type a {@link BpmnParticipant} with a {@link BpmnProcess} a {@link Represents}-compatible or a {@link Reference}-compatible element. <br/>
     * Also synchronize name for extern or non user-renamed participants
     */
    @objid ("75dfba2b-525d-4695-8a14-534842bd5a05")
    private static class SmartTypeBpmnParticipantCommand extends Command {
        @objid ("4376153e-423b-4655-82cb-289e85f8a41c")
        private BpmnParticipant elementToType;

        @objid ("776eef7e-2d0a-4798-85fb-eda79a4d2d9a")
        private ModelElement newType;

        @objid ("385a2d75-e151-489c-8cc9-2cc4b06174b2")
        private IModelManager modelManager;

        /**
         * Constructor to type the element.
         * 
         * @param elementToType the element to type.
         * @param newType the type to use.
         */
        @objid ("2bf7965e-4f87-41db-bc0e-9dced6f3b49b")
        public SmartTypeBpmnParticipantCommand(final BpmnParticipant elementToType, final ModelElement newType, final IModelManager modelManager) {
            this.elementToType = elementToType;
            this.newType = newType;
            this.modelManager = modelManager;
        }

        @objid ("76d06edb-cae3-472c-8c99-5dd558b09d28")
        @Override
        public boolean canExecute() {
            // make sure element is modifiable and the type is not already represented as a participant in the collaboration
            return MTools.getAuthTool().canModify(this.elementToType) && this.newType != null && SmartTypeBpmnParticipantCommand.getExistingParticipant(this.elementToType.getContainer(), this.newType) == null;
        }

        @objid ("4e1be0de-ca17-493f-bfbb-1862c77d7e9c")
        @Override
        public void execute() {
            if (isChangeConfirmed()) {
                changeType();
            }
        }

        @objid ("80168013-f8da-4f35-a481-d825609b2e7c")
        private boolean isChangeConfirmed() {
            // Warning message before replacing existing values
            StringBuilder warning = new StringBuilder();
            
            MObject oldType = this.elementToType.getProcess();
            if (oldType == null) {
                oldType = Represents.getTarget(this.elementToType);
            }
            if (oldType == null) {
                oldType = Reference.getTarget(this.elementToType);
            }
            
            if (oldType != null && !oldType.equals(this.newType)) {
                warning.append(DiagramEditorBpmn.I18N.getMessage("BpmnParticipantElementDropEditPolicy.confirmdialog.type", oldType.getName(), this.newType.getName()));
            }
            
            if (warning.length() > 0 && !MessageDialog.openQuestion(
                    Display.getDefault().getActiveShell(),
                    DiagramEditorBpmn.I18N.getString("BpmnParticipantElementDropEditPolicy.confirmdialog.title"),
                    DiagramEditorBpmn.I18N.getMessage("BpmnParticipantElementDropEditPolicy.confirmdialog.message", this.elementToType.getName(), warning.toString()))) {
                return false;
            }
            return true;
        }

        /**
         * @return a participant already referencing the type being unmasked.
         */
        @objid ("f4f27af7-49f0-410a-906d-0f28b5e717d3")
        private static BpmnParticipant getExistingParticipant(BpmnCollaboration collaboration, MObject type) {
            for (BpmnParticipant p : collaboration.getParticipants()) {
                if (type instanceof BpmnProcess) {
                    if (type.equals(p.getProcess())) {
                        return p;
                    }
                } else {
                    if (Objects.equals(type, Represents.getTarget(p))) {
                        return p;
                    }
                    if (Objects.equals(type, Reference.getTarget(p))) {
                        return p;
                    }
                }
            }
            return null;
        }

        @objid ("6fa774e0-210c-46c9-ad45-0137839453c1")
        private void changeType() {
            // Synchronize name for extern or non user-renamed participants
            BpmnProcess oldProcess = this.elementToType.getProcess();
            if (oldProcess == null || this.elementToType.getName().equals(oldProcess.getName())) {
                this.elementToType.setName(this.newType.getName());
            }
            
            // Update type
            if (this.newType instanceof BpmnProcess) {
                this.elementToType.setProcess((BpmnProcess) this.newType);
                Represents.setTarget(this.elementToType, (ModelElement) null);
                Reference.setTarget(this.elementToType, (ModelElement) null);
            } else if (BpmnParticipantElementDropEditPolicy.isMethodologicalLinkTarget(this.newType, Represents.MdaTypes.STEREOTYPE_ELT, this.modelManager)) {
                Represents.setTarget(this.elementToType, this.newType);
                Reference.setTarget(this.elementToType, (ModelElement) null);
                this.elementToType.setProcess(null);
            } else if (BpmnParticipantElementDropEditPolicy.isMethodologicalLinkTarget(this.newType, Reference.MdaTypes.STEREOTYPE_ELT, this.modelManager)) {
                Represents.setTarget(this.elementToType, (ModelElement) null);
                Reference.setTarget(this.elementToType, this.newType);
                this.elementToType.setProcess(null);
            }
        }

        @objid ("8dcc293a-fc71-43c3-bf13-57c96346c910")
        @Override
        public boolean canUndo() {
            return false;
        }

    }

}
