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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessage;

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
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specialization of the default drop request handling policy to add some smart interactions for {@link BpmnMessage}.
 * <p>
 * Supports the drag & drop of :
 * <ul>
 * <li>a {@link Represents}-compatible element to use it as the message's type.</li>
 * <li>a {@link State}-compatible element to use it as the message's type.</li>
 * </p>
 * </p>
 */
@objid ("8ef12282-042f-4625-9c75-18cc42d7e465")
public class BpmnMessageElementDropEditPolicy extends DefaultElementDropEditPolicy {
    /**
     * Unmasking of a {@link Represents} or {@link State} compatible element on a {@link BpmnMessage} provokes a smart interaction typing the {@link BpmnMessage}.
     */
    @objid ("72b53a4f-75b4-475d-800a-0b11f377f991")
    @Override
    protected EditPart getDropTargetEditPart(final ModelElementDropRequest request) {
        if (request.isSmart() && request.getDroppedElements().length == 1) {
            GmModel model = (GmModel) getHost().getModel();
            IGmDiagram gmDiagram = model.getDiagram();
            BpmnMessage message = (BpmnMessage) model.getRelatedElement();
        
            MObject droppedElement = request.getDroppedElements()[0];
            if (isSmartType(droppedElement, message, gmDiagram)) {
                // Allow smart Type drop on the BpmnMessage
                return getHost();
            } else if (isSmartState(droppedElement, message, gmDiagram)) {
                // Allow smart State drop on the BpmnMessage
                return getHost();
            }
        }
        return super.getDropTargetEditPart(request);
    }

    @objid ("cb76a863-7c67-4d6a-8ae5-1261251b86cc")
    @Override
    protected Command getSmartDropCommand(final ModelElementDropRequest request) {
        if (!request.isSmart()) {
            return null;
        }
        
        GmModel model = (GmModel) getHost().getModel();
        IGmDiagram gmDiagram = model.getDiagram();
        BpmnMessage message = (BpmnMessage) model.getRelatedElement();
        if (request.getDroppedElements().length == 1) {
            MObject droppedElement = request.getDroppedElements()[0];
            if (isSmartType(droppedElement, message, gmDiagram)) {
                return new SmartTypeBpmnMessageCommand(message, (ModelElement) droppedElement, null);
            } else if (isSmartState(droppedElement, message, gmDiagram)) {
                return new SmartTypeBpmnMessageCommand(message, BpmnMessageElementDropEditPolicy.getStateMachineOwnerClassifier(droppedElement), (ModelElement) droppedElement);
            }
        }
        return super.getSmartDropCommand(request);
    }

    /**
     * From a State element, follow the upwards composition hierarchy to get the StateMachine's owner. Said owner is returned only if it is a {@link Classifier}.
     */
    @objid ("e32a620f-5b56-4b58-91ff-e6c404ecb732")
    private static Classifier getStateMachineOwnerClassifier(final MObject elt) {
        if (elt == null) {
            return null;
        } else if (elt instanceof StateMachine) {
            NameSpace owner = ((StateMachine) elt).getOwner();
            return owner instanceof Classifier ? (Classifier) owner : null;
        } else {
            return BpmnMessageElementDropEditPolicy.getStateMachineOwnerClassifier(elt.getCompositionOwner());
        }
    }

    @objid ("bb3ecbfb-69f1-427c-8ce1-a18d2fe90d9b")
    protected boolean isSmartType(MObject droppedElement, BpmnMessage targetElement, IGmDiagram gmDiagram) {
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass sourceMetaclass = targetElement.getMClass();
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
    }

    @objid ("9836259e-2c10-4139-b603-bd9da5ea8278")
    protected boolean isSmartState(MObject droppedElement, BpmnMessage targetElement, IGmDiagram gmDiagram) {
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass sourceMetaclass = targetElement.getMClass();
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(State.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
    }

    /**
     * Command produced by the drop element edit policy of {@link GmBpmnMessage} that can type a {@link BpmnMessage} with a {@link State} or a {@link Classifier}.
     */
    @objid ("62f67406-9ece-4cd8-9272-697a5d01b59e")
    private static class SmartTypeBpmnMessageCommand extends Command {
        @objid ("81b58d1c-bb36-41e1-9de5-ddf5c6ab10dd")
        private BpmnMessage messageToType;

        @objid ("f5f5ee2b-c72d-4a16-9a61-325dfccfff0f")
        private ModelElement state;

        @objid ("880dc59a-655b-4723-855a-71657ede0271")
        private ModelElement type;

        /**
         * Constructor to type the message with {@link Classifier} or {@link State}.
         * 
         * @param messageToType the message to type.
         * @param type the general class to use. Might be <code>null</code>.
         * @param state the state to use. Might be <code>null</code>.
         */
        @objid ("db69ab3e-a0d2-4c5d-8fc3-b78f76b1c3bc")
        public SmartTypeBpmnMessageCommand(final BpmnMessage messageToType, final ModelElement type, final ModelElement state) {
            this.messageToType = messageToType;
            this.state = state;
            this.type = type;
        }

        /**
         * Constructor to type the message with {@link Classifier} or {@link State}.
         * 
         * @param messageToType the message to type.
         * @param type the general class to use. Might be <code>null</code>.
         */
        @objid ("b0797e48-d214-4ecc-a69f-62ce7fb4c1eb")
        public SmartTypeBpmnMessageCommand(final BpmnMessage messageToType, final ModelElement type) {
            this.messageToType = messageToType;
            this.type = type;
        }

        @objid ("77b96ef8-d440-47bf-bdc7-f14576c5b05f")
        @Override
        public boolean canExecute() {
            return MTools.getAuthTool().canModify(this.messageToType) && (this.state != null || this.type != null);
        }

        @objid ("d6c85a05-c00a-46f9-8e46-f4d20be2aa29")
        @Override
        public void execute() {
            if (isChangeConfirmed()) {
                if (this.state != null) {
                    State.setTarget(this.messageToType, this.state);
                    Represents.setTarget(this.messageToType, this.type);
                } else if (this.type != null) {
                    Represents.setTarget(this.messageToType, this.type);
            
                    // Keep the state consistent
                    ModelElement inState = State.getTarget(this.messageToType);
                    if (inState != null && !this.type.equals(BpmnMessageElementDropEditPolicy.getStateMachineOwnerClassifier(inState))) {
                        State.setTarget(this.messageToType, null);
                    }
                }
            }
        }

        @objid ("9bb39048-ab6f-4b14-ab22-e2061fa2c0be")
        @Override
        public void undo() {
            if (this.state != null) {
                State.setTarget(this.messageToType, null);
            } else if (this.type != null) {
                Represents.setTarget(this.messageToType, null);
            }
        }

        @objid ("e2d03470-1c56-4baf-9c3f-6b575da4e877")
        @Override
        public void redo() {
            if (this.state != null) {
                State.setTarget(this.messageToType, this.state);
                Represents.setTarget(this.messageToType, this.type);
            } else if (this.type != null) {
                Represents.setTarget(this.messageToType, this.type);
            }
        }

        @objid ("fe16065a-63e5-4e2d-908f-c391845cb5b5")
        private boolean isChangeConfirmed() {
            // Warning message before replacing existing values
            StringBuilder warning = new StringBuilder();
            ModelElement oldInState = State.getTarget(this.messageToType);
            if (oldInState != null && this.state != null && !oldInState.equals(this.state)) {
                warning.append(DiagramEditorBpmn.I18N.getMessage("BpmnMessageElementDropEditPolicy.confirmdialog.instate", oldInState.getName(), this.state != null ? this.state.getName() : "null"));
            }
            
            ModelElement oldType = Represents.getTarget(this.messageToType);
            if (oldType != null && this.type != null && !oldType.equals(this.type)) {
                warning.append(DiagramEditorBpmn.I18N.getMessage("BpmnMessageElementDropEditPolicy.confirmdialog.type", oldType.getName(), this.type.getName()));
            }
            
            if (warning.length() > 0 && !MessageDialog.openQuestion(
                    Display.getDefault().getActiveShell(),
                    DiagramEditorBpmn.I18N.getString("BpmnMessageElementDropEditPolicy.confirmdialog.title"),
                    DiagramEditorBpmn.I18N.getMessage("BpmnMessageElementDropEditPolicy.confirmdialog.message", this.messageToType.getName(), warning.toString()))) {
                return false;
            }
            return true;
        }

    }

}
