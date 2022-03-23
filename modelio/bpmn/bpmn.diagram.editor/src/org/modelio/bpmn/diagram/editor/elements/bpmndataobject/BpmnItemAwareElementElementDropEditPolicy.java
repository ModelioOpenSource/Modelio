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
package org.modelio.bpmn.diagram.editor.elements.bpmndataobject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
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
 * Specialization of the default drop request handling policy to add some smart interactions for {@link BpmnItemAwareElement}.
 * <p>
 * Supports the drag & drop of :
 * <ul>
 * <li>a {@link Represents}-compatible element to use it as the element's type.</li>
 * <li>a {@link State}-compatible element to use it as the element's type.</li>
 * </p>
 */
@objid ("b2cc3873-bd0d-482d-8c78-8b1786ad1ee3")
public class BpmnItemAwareElementElementDropEditPolicy extends DefaultElementDropEditPolicy {
    /**
     * Unmasking of a {@link Represents} or {@link State} compatible element on a {@link BpmnItemAwareElement} provokes a smart interaction typing the {@link BpmnItemAwareElement}.
     */
    @objid ("b86cab30-335b-4f0e-981b-8df78a372b3c")
    @Override
    protected EditPart getDropTargetEditPart(final ModelElementDropRequest request) {
        if (request.isSmart() && request.getDroppedElements().length == 1) {
            GmModel model = (GmModel) getHost().getModel();
            IGmDiagram gmDiagram = model.getDiagram();
            BpmnItemAwareElement element = (BpmnItemAwareElement) model.getRelatedElement();
        
            MObject droppedElement = request.getDroppedElements()[0];
            if (isSmartType(droppedElement, element, gmDiagram)) {
                // Allow smart Type drop on the BpmnItemAwareElement
                return getHost();
            } else if (isSmartState(droppedElement, element, gmDiagram)) {
                // Allow smart State drop on the BpmnItemAwareElement
                return getHost();
            }
        }
        return super.getDropTargetEditPart(request);
    }

    @objid ("4ff944ec-1afa-429f-a24c-1547eafe84c5")
    @Override
    protected Command getSmartDropCommand(final ModelElementDropRequest request) {
        if (!request.isSmart()) {
            return null;
        }
        
        GmModel model = (GmModel) getHost().getModel();
        IGmDiagram gmDiagram = model.getDiagram();
        BpmnItemAwareElement element = (BpmnItemAwareElement) model.getRelatedElement();
        if (request.getDroppedElements().length == 1) {
            if (isSmartType(request.getDroppedElements()[0], element, gmDiagram)) {
                return new SmartTypeBpmnItemAwareElementCommand(element, (ModelElement) request.getDroppedElements()[0]);
            } else if (isSmartState(request.getDroppedElements()[0], element, gmDiagram)) {
                return new SmartTypeBpmnItemAwareElementCommand(element, (ModelElement) request.getDroppedElements()[0], BpmnItemAwareElementElementDropEditPolicy.getStateMachineOwnerClassifier(request.getDroppedElements()[0]));
            }
        }
        return super.getSmartDropCommand(request);
    }

    /**
     * From a State element, follow the upwards composition hierarchy to get the StateMachine's owner. Said owner is returned only if it is a {@link Classifier}.
     */
    @objid ("1ee01de6-f013-44ec-b3c6-442c0e1690c6")
    private static Classifier getStateMachineOwnerClassifier(final MObject elt) {
        if (elt == null) {
            return null;
        } else if (elt instanceof StateMachine) {
            NameSpace owner = ((StateMachine) elt).getOwner();
            return owner instanceof Classifier ? (Classifier) owner : null;
        } else {
            return BpmnItemAwareElementElementDropEditPolicy.getStateMachineOwnerClassifier(elt.getCompositionOwner());
        }
        
    }

    @objid ("2ddcd15a-21b5-49c3-b2e3-60b3e17a910c")
    protected boolean isSmartState(MObject droppedElement, BpmnItemAwareElement targetElement, IGmDiagram gmDiagram) {
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass sourceMetaclass = targetElement.getMClass();
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(State.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
    }

    @objid ("45498b12-9dfc-4450-98af-901dab0bae76")
    protected boolean isSmartType(MObject droppedElement, BpmnItemAwareElement targetElement, IGmDiagram gmDiagram) {
        IModelManager modelManager = gmDiagram.getModelManager();
        MMetamodel metamodel = modelManager.getMetamodel();
        MClass sourceMetaclass = targetElement.getMClass();
        MClass linkMetaclass = metamodel.getMClass(MethodologicalLink.MQNAME);
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(Represents.MdaTypes.STEREOTYPE_ELT, linkMetaclass, sourceMetaclass, droppedElement.getMClass());
    }

    /**
     * Command produced by the drop element edit policy that can type a {@link BpmnItemAwareElement} with a {@link State} or a {@link Represents}.
     */
    @objid ("edba999a-551e-4996-80dc-08eef708d73a")
    private static class SmartTypeBpmnItemAwareElementCommand extends Command {
        @objid ("0d6987b4-39e7-447c-80ba-640a0e229816")
        private BpmnItemAwareElement elementToType;

        @objid ("4419ec65-f822-48f7-a858-4e3ff51270fb")
        private ModelElement state;

        @objid ("f589e752-f915-4b3f-a741-02871ff0593f")
        private ModelElement type;

        /**
         * Constructor to type the element with a {@link State} or a {@link Represents}.
         * @param elementToType the element to type.
         * @param state the state to use. Might be <code>null</code>.
         * @param type the general class to use.
         */
        @objid ("152d851e-17d0-4b95-8dfe-f5a476d17a10")
        public  SmartTypeBpmnItemAwareElementCommand(final BpmnItemAwareElement elementToType, final ModelElement state, final ModelElement type) {
            this.elementToType = elementToType;
            this.state = state;
            this.type = type;
            
        }

        /**
         * Constructor to type the element with a {@link State} or a {@link Represents}.
         * @param elementToType the element to type.
         * @param type the general class to use.
         */
        @objid ("e09ed5d8-16ca-4098-a4f9-e17d63311b1c")
        public  SmartTypeBpmnItemAwareElementCommand(final BpmnItemAwareElement elementToType, final ModelElement type) {
            this.elementToType = elementToType;
            this.type = type;
            
        }

        @objid ("5a06f1a5-2b48-43ca-95a0-67eb8aac1127")
        @Override
        public boolean canExecute() {
            return MTools.getAuthTool().canModify(this.elementToType) && (this.state != null || this.type != null);
        }

        @objid ("068ba36c-5813-4a1f-aedc-c9659c99e37e")
        @Override
        public void execute() {
            if (isChangeConfirmed()) {
                if (this.state != null) {
                    State.setTarget(this.elementToType, this.state);
                    Represents.setTarget(this.elementToType, this.type);
                } else if (this.type != null) {
                    Represents.setTarget(this.elementToType, this.type);
            
                    // Keep the state consistent
                    ModelElement inState = State.getTarget(this.elementToType);
                    if (inState != null && !this.type.equals(BpmnItemAwareElementElementDropEditPolicy.getStateMachineOwnerClassifier(inState))) {
                        State.setTarget(this.elementToType, null);
                    }
                }
            }
            
        }

        @objid ("e2c549b6-78b9-42ba-982c-afd0a1cccd5e")
        @Override
        public void undo() {
            if (this.state != null) {
                State.setTarget(this.elementToType, null);
            } else if (this.type != null) {
                Represents.setTarget(this.elementToType, null);
            }
            
        }

        @objid ("bc662cfe-3f64-430b-b82b-919b5d3adc44")
        @Override
        public void redo() {
            if (this.state != null) {
                State.setTarget(this.elementToType, this.state);
                Represents.setTarget(this.elementToType, this.type);
            } else if (this.type != null) {
                Represents.setTarget(this.elementToType, this.type);
            }
            
        }

        @objid ("91e70cd8-f5e6-43b7-9db3-aea00ff5e51a")
        private boolean isChangeConfirmed() {
            // Warning message before replacing existing values
            StringBuilder warning = new StringBuilder();
            ModelElement oldInState = State.getTarget(this.elementToType);
            if (oldInState != null && this.state != null && !oldInState.equals(this.state)) {
                warning.append(DiagramEditorBpmn.I18N.getMessage("BpmnItemAwareElementElementDropEditPolicy.confirmdialog.instate", oldInState.getName(), this.state != null ? this.state.getName() : "null"));
            }
            
            ModelElement oldType = Represents.getTarget(this.elementToType);
            if (oldType != null && this.type != null && !oldType.equals(this.type)) {
                warning.append(DiagramEditorBpmn.I18N.getMessage("BpmnItemAwareElementElementDropEditPolicy.confirmdialog.type", oldType.getName(), this.type.getName()));
            }
            
            if (warning.length() > 0 && !MessageDialog.openQuestion(
                    Display.getDefault().getActiveShell(),
                    DiagramEditorBpmn.I18N.getString("BpmnItemAwareElementElementDropEditPolicy.confirmdialog.title"),
                    DiagramEditorBpmn.I18N.getMessage("BpmnItemAwareElementElementDropEditPolicy.confirmdialog.message", this.elementToType.getName(), warning.toString()))) {
                return false;
            }
            return true;
        }

    }

}
