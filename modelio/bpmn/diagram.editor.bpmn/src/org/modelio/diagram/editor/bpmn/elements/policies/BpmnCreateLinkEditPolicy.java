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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.modelio.diagram.editor.bpmn.elements.participant.GmBpmnParticipantPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.policies.msgflow.MessageFlowSolverDataModel;
import org.modelio.diagram.editor.bpmn.elements.policies.msgflow.MessageFlowSolverDialog;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkCommand;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * BPMN-specific version of the {@link DefaultCreateLinkEditPolicy}, adding a custom behavior for message flows.
 * <p>
 * When a {@link BpmnMessageFlow}'s source/target is a {@link GmBpmnParticipantPrimaryNode} or {@link GmBpmnProcessPrimarySimple} in simple representation mode,
 * opens a dialog box to choose a {@link BpmnBaseElement} from the participant before the link creation, and use it as the actual source/target.
 * </p>
 */
@objid ("609b9f54-1ed2-45a9-9f48-1f80238c1669")
public class BpmnCreateLinkEditPolicy extends DefaultCreateLinkEditPolicy {
    /**
     * Default c'tor: creates an opaque instance of this policy.
     * @see #DefaultCreateLinkEditPolicy(boolean)
     */
    @objid ("6405e04f-a7b8-4a89-a50a-7d596c683dc0")
    public BpmnCreateLinkEditPolicy() {
        super(true);
    }

    /**
     * C'tor.
     * @param isOpaque determines the behavior of this policy on request where the creation expert doesn't allow.
     */
    @objid ("89f77598-7580-4d96-92ac-f5d75bb62162")
    public BpmnCreateLinkEditPolicy(boolean isOpaque) {
        super(isOpaque);
    }

    @objid ("ee9b94f7-c49b-4ba1-8c76-56ee8bc62f91")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest req) {
        // Get standard creation command from super
        DefaultCreateLinkCommand ret = (DefaultCreateLinkCommand) super.getConnectionCompleteCommand(req);
        if (ret != null && ret.canExecute()) {
            final ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(req);
            if (context != null && context.getElementToUnmask() == null && BpmnMessageFlow.class.equals(context.getMetaclass().getJavaInterface())) {
                GmModel sourceGm = ((GmModel) req.getSourceEditPart().getModel());
                GmModel targetGm = ((GmModel) getHost().getModel());
        
                return new BpmnMessageFlowChooseEndCommand(ret, sourceGm, targetGm, context.getMetaclass());
            }
        }
        return ret;
    }

    /**
     * Command handling creation of {@link BpmnMessageFlow} involving at least one {@link BpmnParticipant}.
     * <p>
     * Delegates message flow's creation to a standard {@link DefaultCreateLinkCommand}, and then reconfigures the link's source or target.
     * </p>
     */
    @objid ("abbb11d7-3aa9-4bce-bca2-e38913df5f6c")
    private static class BpmnMessageFlowChooseEndCommand extends Command {
        @objid ("39b08c47-c219-4cc2-8e13-9731ad89aa02")
        private DefaultCreateLinkCommand baseCommand;

        @objid ("69292f1e-cf3d-4225-83ba-585fa7d9fb13")
        private GmModel sourceGm;

        @objid ("e6685bfa-6b89-4057-8d32-c26c1e315bff")
        private GmModel targetGm;

        @objid ("6154cc2e-008a-4821-b0d2-38e095cfa409")
        private MClass linkMClass;

        @objid ("3b823603-1ec5-4cb2-9074-951c3bce6137")
        public BpmnMessageFlowChooseEndCommand(DefaultCreateLinkCommand baseCommand, GmModel sourceGm, GmModel targetGm, MClass linkMClass) {
            this.baseCommand = baseCommand;
            this.sourceGm = sourceGm;
            this.targetGm = targetGm;
            this.linkMClass = linkMClass;
        }

        @objid ("e6c70104-3868-4a2d-a407-45ee997e25ae")
        @Override
        public void execute() {
            MExpert mExpert = this.linkMClass.getMetamodel().getMExpert();
            
            // Choose from the candidates
            List<BpmnBaseElement> sourceCandidates = getCandidates(this.sourceGm, source -> mExpert.canSource(this.linkMClass, source.getMClass()));
            List<BpmnBaseElement> targetCandidates = getCandidates(this.targetGm, target -> mExpert.canTarget(this.linkMClass, target.getMClass()));
            MessageFlowSolverDataModel dataModel = new MessageFlowSolverDataModel(sourceCandidates.get(0), sourceCandidates, targetCandidates.get(0), targetCandidates);
            
            MessageFlowSolverDialog dlg = new MessageFlowSolverDialog(null, dataModel);
            dlg.setBlockOnOpen(true);
            if ((sourceCandidates.size() == 1 && targetCandidates.size() == 1) || (dlg.open() == IDialogConstants.OK_ID)) {
                // Execute base command
                this.baseCommand.execute();
                IGmLink gmMessageFlow = this.baseCommand.getCreatedGraphicModel();
            
                BpmnBaseElement chosenSource = dataModel.getSelectedSource();
                BpmnBaseElement chosenTarget = dataModel.getSelectedTarget();
            
                // Set new source and target
                BpmnMessageFlow messageFlow = (BpmnMessageFlow) gmMessageFlow.getRepresentedElement();
                messageFlow.setSourceRef(chosenSource);
                messageFlow.setTargetRef(chosenTarget);
                messageFlow.setName(dataModel.getMessageFlowName());
            
                // Set owner
                IGmDiagram diagram = this.sourceGm.getDiagram();
                while (diagram.getDiagramOwner() != null) {
                    diagram = diagram.getDiagramOwner();
                }
            
                BpmnCollaboration collaboration = (BpmnCollaboration) diagram.getRelatedElement().getOrigin();
                messageFlow.setCollaboration(collaboration);
            }
        }

        @objid ("bf5310e6-51cd-4fd2-88b1-4d88097ba6c9")
        @Override
        public boolean canExecute() {
            return this.baseCommand.canExecute();
        }

        @objid ("c570d59a-0c58-48a1-8a91-131f54f29bf9")
        private List<BpmnBaseElement> getCandidates(GmModel gm, Predicate<BpmnFlowElement> predicate) {
            List<BpmnBaseElement> candidates = new ArrayList<>();
            
            // Given element is always a candidate
            MObject relatedElement = gm.getRelatedElement();
            candidates.add((BpmnBaseElement) relatedElement);
            
            if (gm.getRepresentationMode() == RepresentationMode.SIMPLE) {
                if (relatedElement instanceof BpmnParticipant) {
                    // For a participant, get the process' flow elements
                    BpmnParticipant participant = (BpmnParticipant) relatedElement;
            
                    BpmnProcess process = participant.getProcess();
                    if (process != null) {
                        for (BpmnFlowElement flowElement : process.getFlowElement()) {
                            if (predicate.test(flowElement)) {
                                candidates.add(flowElement);
                            }
                        }
                    }
                } else if (relatedElement instanceof BpmnProcess) {
                    // For a process, get the process' flow elements
                    BpmnProcess process = (BpmnProcess) relatedElement;
                    candidates.addAll(process.getFlowElement());
                }
            }
            return candidates;
        }

    }

}
