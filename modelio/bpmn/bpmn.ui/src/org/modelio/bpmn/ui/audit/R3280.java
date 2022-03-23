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
package org.modelio.bpmn.ui.audit;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.audit.engine.core.AbstractControl;
import org.modelio.audit.engine.core.AbstractRule;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnBusinessRuleTask;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnManualTask;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnScriptTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.activities.BpmnTransaction;
import org.modelio.metamodel.bpmn.activities.BpmnUserTask;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3280 Severity : error Description : A FlowElement must be part of a lane.
 */
@objid ("ad7c379b-69b8-42c6-b540-d8e978900c43")
public class R3280 extends AbstractBpmnRule {
    @objid ("b2fbd5da-a240-45b1-8f9d-6049b2754caf")
    private static final String RULEID = "R3280";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("ce6d0ce7-ac49-477c-8f55-c5e53f13c351")
    private CheckR3280 checkerInstance = null;

    @objid ("882c491b-eb8a-46c7-93d0-1395ed568ac0")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnActivity.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnAdHocSubProcess.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnBusinessRuleTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnCallActivity.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnComplexGateway.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnDataInput.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnDataObject.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnDataOutput.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnDataStore.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnEndEvent.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnEventBasedGateway.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnExclusiveGateway.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnImplicitThrowEvent.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnInclusiveGateway.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnIntermediateCatchEvent.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnIntermediateThrowEvent.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnManualTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnParallelGateway.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnReceiveTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnScriptTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnSendTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnServiceTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnStartEvent.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnSubProcess.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnTransaction.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnUserTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnLane.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        
    }

    @objid ("6e94fd71-3025-42e3-885b-1d4144ef4676")
    @Override
    public String getRuleId() {
        return R3280.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c401087b-4487-43bd-b4e6-1f91ea06edc6")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a9ef7f7d-d541-4bac-b2f5-837d87c97a61")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8fbf4b8e-6122-4791-8e57-ae32e13cfdb0")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3250
     */
    @objid ("2c75d652-59ff-4e57-9441-fa5dcf4ffff7")
    public  R3280() {
        this.checkerInstance = new CheckR3280(this);
    }

    /**
     * Actual checker for R3280: Checks that the FlowElement must be part of a lane if any exist in the process.
     */
    @objid ("88446e06-fbb5-4fd4-bede-c804cc2c0777")
    private static class CheckR3280 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("51862eb6-02f6-465e-8d8d-a7115b750e8d")
        public  CheckR3280(final IRule rule) {
            super(rule);
        }

        @objid ("928b15c8-84ae-45c7-95c0-ea00df386c9b")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnFlowElement) {
                diagnostic.addEntry(checkR3280((BpmnFlowElement) element));
            } else if (element instanceof BpmnLane) {
                BpmnLane lane = (BpmnLane) element;
                for (BpmnFlowElement ref : lane.getFlowElementRef()) {
                    diagnostic.addEntry(checkR3280(ref));
                }
            }
            return diagnostic;
        }

        @objid ("9d5f8218-d606-4e73-98cc-0e6e1251be46")
        private IAuditEntry checkR3280(final BpmnFlowElement element) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, element, null);
            
            final EList<BpmnLane> lanes = element.getLane();
            if (lanes.isEmpty()) {
                BpmnSubProcess subProcess = element.getSubProcess();
                if (subProcess != null) {
                    BpmnLaneSet laneSet = subProcess.getLaneSet();
                    if (laneSet != null && !laneSet.getLane().isEmpty()) {
                        fillAuditEntry(element, subProcess, auditEntry);
                    }
                } else {
                    final BpmnProcess process = getProcess(element);
                    BpmnLaneSet laneSet = process.getLaneSet();
                    if (laneSet != null && !laneSet.getLane().isEmpty()) {
                        fillAuditEntry(element, process, auditEntry);
                    }
                }
            }
            return auditEntry;
        }

        @objid ("3a224427-bfd5-4ece-a513-dbd6e4228642")
        private void fillAuditEntry(final BpmnFlowElement element, final ModelElement parent, AuditEntry auditEntry) {
            auditEntry.setSeverity(this.rule.getSeverity());
            List<Object> linkedObjects = new ArrayList<>();
            linkedObjects.add(element.getMClass().getName());
            linkedObjects.add(element);
            linkedObjects.add(parent);
            auditEntry.setLinkedInfos(linkedObjects);
            
        }

    }

}
