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

package org.modelio.bpmn.ui.audit;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractControl;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.bpmn.ui.plugin.BpmnUi;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3320 Severity : info Description : A MessageFlow should start from a SendTask/ThrowEvent/Participant and end on a ReceiveTask/CatchEvent/Participant.
 */
@objid ("11da1caa-8fcd-4690-b49d-aa8005569390")
public class R3320 extends AbstractBpmnRule {
    @objid ("7a0033fd-44c5-4d20-9c29-411fa50e43f6")
    private static final String RULEID = "R3320";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     */
    @objid ("86cd26f5-dcd7-486a-a2b9-889280c6b948")
    private CheckR3320 checkerInstance = null;

    @objid ("a9245a94-064c-41cc-8378-befdc7b624bf")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnMessageFlow.MQNAME, this, AuditTrigger.ALL);
    }

    @objid ("2327d668-471e-4b94-a17b-42e1f34e3849")
    @Override
    public String getRuleId() {
        return R3320.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f5ea6c36-30e3-466e-b39e-00136bdf3317")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1d04aa2c-0dc7-46eb-bc5a-21861bbd6680")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f19577ed-a8fb-4166-ac7a-fcf99b2725bd")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3250
     */
    @objid ("bea5acbb-f49c-4b7d-8105-f14ae39e6c72")
    public R3320() {
        this.checkerInstance = new CheckR3320(this);
    }

    /**
     * Actual checker for R3320: Checks that a MessageFlow starts from a SendTask/ThrowEvent/Participant and ends on a ReceiveTask/CatchEvent/Participant.
     */
    @objid ("9f9a8f17-d558-4133-98ab-93c7a2f1e380")
    private static class CheckR3320 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("89ff8129-3641-492d-8bed-b189de6d8bfb")
        public CheckR3320(final IRule rule) {
            super(rule);
        }

        @objid ("79d2005b-d00b-4c7e-b327-303f28b03515")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnMessageFlow) {
                diagnostic.addEntry(checkR3320((BpmnMessageFlow) element));
            } else {
                BpmnUi.LOG.warning("R3320: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("6be4ffbc-0129-4071-945a-7c96c5629c84")
        private IAuditEntry checkR3320(final BpmnMessageFlow messageFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, messageFlow, null);
            
            BpmnBaseElement source = messageFlow.getSourceRef();
            BpmnBaseElement target = messageFlow.getTargetRef();
            
            boolean isFromExternalParticipant = source instanceof BpmnParticipant && ((BpmnParticipant) source).getProcess() == null;
            boolean isToExternalParticipant = target instanceof BpmnParticipant && ((BpmnParticipant) target).getProcess() == null;
            
            // 
            // source                      -> recommended targets
            // ExternalParticipant         -> BpmnIntermediateCatchEvent | BpmnReceiveTask | ExternalParticipant
            // BpmnIntermediateThrowEvent  -> BpmnIntermediateCatchEvent | ExternalParticipant
            // BpmnSendTask                -> BpmnReceiveTask | BpmnIntermediateCatchEvent | ExternalParticipant
            // 
            
            BpmnThrowEvent n;
            BpmnIntermediateThrowEvent b;
            
            boolean isOk;
            if (isFromExternalParticipant) {
                isOk = target instanceof BpmnIntermediateCatchEvent || target instanceof BpmnReceiveTask || isToExternalParticipant;
                
            } else if (source instanceof BpmnIntermediateThrowEvent) {
                isOk = target instanceof BpmnIntermediateCatchEvent || target instanceof BpmnReceiveTask || isToExternalParticipant;
                
            } else if (source instanceof BpmnSendTask) {
                isOk = target instanceof BpmnReceiveTask || target instanceof BpmnIntermediateCatchEvent || isToExternalParticipant;
            } else {
                isOk = false;
            }
            
            if (!isOk) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(messageFlow);
                linkedObjects.add(source);
                linkedObjects.add(target);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
