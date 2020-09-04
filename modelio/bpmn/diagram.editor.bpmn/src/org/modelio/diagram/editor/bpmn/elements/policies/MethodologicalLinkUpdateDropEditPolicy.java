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

package org.modelio.diagram.editor.bpmn.elements.policies;

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
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.AbstractMethodologicalLink;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specialization of the default drop request handling policy to add some smart interactions for a {@link ModelElement}.
 * <p>
 * Supports the drag & drop of a {@link ModelElement} that can be the target of a specific {@link MethodologicalLink} to use it as the element's type.
 * </p>
 */
@objid ("ab7c7d9d-45e5-4249-9b17-53f53b293219")
public class MethodologicalLinkUpdateDropEditPolicy extends DefaultElementDropEditPolicy {
    @objid ("6102653c-656d-4f77-8230-6a2502e2f685")
    private Stereotype methoLinkStereotype;

    /**
     * Unmasking of a {@link ModelElement} that can be the target of a {@link MethodologicalLink} to use it as the element's type.
     */
    @objid ("624a5a9f-866b-49b7-a8a7-f24d1d16d411")
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
            if (isSmartCallActivity(droppedElement, gmDiagram)) {
                // Allow smart drop
                return getHost();
            }
        }
        return null;
    }

    @objid ("332ad501-ff2a-407d-9fc8-18518666d477")
    @Override
    protected Command getSmartDropCommand(final ModelElementDropRequest request) {
        if (!request.isSmart()) {
            return null;
        }
        
        GmModel gmModel = (GmModel) getHost().getModel();
        IGmDiagram gmDiagram = gmModel.getDiagram();
        MObject element = ((GmModel) getHost().getModel()).getRelatedElement();
        if (request.getDroppedElements().length == 1) {
            MObject droppedElement = request.getDroppedElements()[0];
            if (isSmartCallActivity(droppedElement, gmDiagram)) {
                return new UpdateMethoLinkCommand(element, droppedElement, this.methoLinkStereotype);
            }
        }
        return null;
    }

    @objid ("6304b0d9-4a18-44cd-88ca-93f84fadbdde")
    private boolean isSmartCallActivity(MObject droppedElement, IGmDiagram gmDiagram) {
        GmModel gmModel = (GmModel) getHost().getModel();
        MObject element = gmModel.getRelatedElement();
        MClass linkMetaclass = element.getMClass().getMetamodel().getMClass(MethodologicalLink.MQNAME);
        IModelManager modelManager = gmDiagram.getModelManager();
        IMdaExpert mdaExpert = modelManager.getMdaExpert();
        return mdaExpert.canLink(this.methoLinkStereotype, linkMetaclass, element, droppedElement);
    }

    /**
     * Instanciate the policy for a specific {@link MethodologicalLink} stereotype.
     * @param methoLinkStereotype a {@link MethodologicalLink} stereotype.
     */
    @objid ("6c94c081-de35-400d-84e5-1bc398a401f8")
    public MethodologicalLinkUpdateDropEditPolicy(Stereotype methoLinkStereotype) {
        this.methoLinkStereotype = methoLinkStereotype;
    }

    /**
     * Command produced by the drop element edit policy to update a {@link MethodologicalLink}.
     */
    @objid ("3b625e51-7fe3-4681-b362-27a29d5fb75d")
    private static class UpdateMethoLinkCommand extends Command {
        @objid ("eb7057c4-a388-4eb9-81b2-d30af5451d91")
        private MObject elementToType;

        @objid ("d6a7b48e-af0b-461a-abd5-bae0b1eee25c")
        private MObject newType;

        @objid ("c8638c13-4ba7-499c-9496-4b7498321442")
        private Stereotype methoLinkStereotype;

        /**
         * Constructor for the command.
         * @param elementToType the element to type.
         * @param newType the type to use. Might be <code>null</code>.
         * @param methoLinkStereotype the stereotype to use for the {@link MethodologicalLink}.
         */
        @objid ("8021b9b5-18be-4014-8593-48efc363b2ed")
        public UpdateMethoLinkCommand(final MObject elementToType, final MObject newType, Stereotype methoLinkStereotype) {
            this.elementToType = elementToType;
            this.newType = newType;
            this.methoLinkStereotype = methoLinkStereotype;
        }

        @objid ("78381c06-9d89-45b0-bd28-f9a76109d1d9")
        @Override
        public boolean canExecute() {
            // make sure source element is modifiable
            return MTools.getAuthTool().canModify(this.elementToType) && this.elementToType instanceof ModelElement;
        }

        @objid ("60876570-10e8-4b42-8cd0-c03ca10fbc18")
        @Override
        public void execute() {
            if (isChangeConfirmed()) {
                AbstractMethodologicalLink.setTarget((ModelElement) this.elementToType, this.methoLinkStereotype, (ModelElement) this.newType);
            }
        }

        @objid ("d5f38146-ca40-401e-9a9b-392fc65f5a2a")
        private boolean isChangeConfirmed() {
            // Warning message before replacing existing values
            StringBuilder warning = new StringBuilder();
            ModelElement oldType = AbstractMethodologicalLink.getTarget((ModelElement) this.elementToType, this.methoLinkStereotype);
            if (oldType != null && !oldType.equals(this.newType)) {
                warning.append(DiagramEditorBpmn.I18N.getMessage("MethodologicalLinkUpdateDropEditPolicy.confirmdialog.type", oldType.getName(), this.newType != null ? this.newType.getName() : "null"));
            }
            
            if (warning.length() > 0 && !MessageDialog.openQuestion(
                    Display.getDefault().getActiveShell(),
                    DiagramEditorBpmn.I18N.getString("MethodologicalLinkUpdateDropEditPolicy.confirmdialog.title"),
                    DiagramEditorBpmn.I18N.getMessage("MethodologicalLinkUpdateDropEditPolicy.confirmdialog.message", this.elementToType.getName(), warning.toString()))) {
                return false;
            }
            return true;
        }

        @objid ("542aeafb-8ce6-47e6-b94f-bd1786556d0c")
        @Override
        public boolean canUndo() {
            return false;
        }

    }

}
