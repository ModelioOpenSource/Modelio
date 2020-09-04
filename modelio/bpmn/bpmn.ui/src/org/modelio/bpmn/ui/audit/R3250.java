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
import org.modelio.audit.engine.core.AbstractControl;
import org.modelio.audit.engine.core.AbstractRule;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.bpmn.ui.plugin.BpmnUi;
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
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3250 Severity : error Description : If there is at least one element in a a Process, there should be a StartEvent and a EndEvent.
 */
@objid ("3a34e1a7-fefe-4006-a0fc-6ac372bbde14")
public class R3250 extends AbstractBpmnRule {
    @objid ("6cdf7310-38f1-4981-a61f-0596a3c27216")
    private static final String RULEID = "R3250";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("df3a9672-45a3-4643-9b8e-590b72dbe0f0")
    private CheckR3250 checkerInstance = null;

    @objid ("5cf28ef7-5adc-4b97-9a8c-e79ea3a744e1")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnProcess.MQNAME, this, AuditTrigger.UPDATE);
        
        // BpmnFlowNode.Activity.Activity
        plan.registerRule(BpmnCallActivity.MQNAME, this, AuditTrigger.CREATE);
        
        // BpmnFlowNode.Activity.Task
        plan.registerRule(BpmnTask.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnSendTask.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnReceiveTask.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnServiceTask.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnUserTask.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnManualTask.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnScriptTask.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnBusinessRuleTask.MQNAME, this, AuditTrigger.CREATE);
        
        // BpmnFlowNode.Activity.SubProcess
        plan.registerRule(BpmnSubProcess.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnAdHocSubProcess.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnTransaction.MQNAME, this, AuditTrigger.CREATE);
        
        // BpmnFlowNode.Event.CatchEvent
        // Except BoundaryEvent, which are not concerned by the rule.
        // Except ImplicitThrowEvent which is not implemented by Modelio.
        plan.registerRule(BpmnStartEvent.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnIntermediateCatchEvent.MQNAME, this, AuditTrigger.CREATE);
        
        // BpmnFlowNode.Event.ThrowEvent
        plan.registerRule(BpmnEndEvent.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnIntermediateThrowEvent.MQNAME, this, AuditTrigger.CREATE);
        
        // BpmnFlowNode.Gateway
        plan.registerRule(BpmnParallelGateway.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnEventBasedGateway.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnComplexGateway.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnInclusiveGateway.MQNAME, this, AuditTrigger.CREATE);
        plan.registerRule(BpmnExclusiveGateway.MQNAME, this, AuditTrigger.CREATE);
    }

    @objid ("b1f76567-d511-4dd4-ae72-871313346ea6")
    @Override
    public String getRuleId() {
        return R3250.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0357bdd2-e399-437e-bea2-c7c52bdece4e")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("dd4a6ebb-ddb7-436a-8754-b7e7744aaa05")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("dec401f3-5477-4045-9e72-cbd02c9a48e9")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3250
     */
    @objid ("a31d714d-5e94-460f-afde-acf125e4395c")
    public R3250() {
        this.checkerInstance = new CheckR3250(this);
    }

    /**
     * Actual checker for R3250: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("1c1a943b-cf0b-4c53-a8e1-2ba8fbcbda01")
    private static class CheckR3250 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("bd717f8f-0051-49a3-84fa-27f50bd35d3d")
        public CheckR3250(final IRule rule) {
            super(rule);
        }

        @objid ("cab8b501-b2e3-4df0-bae6-911d93a03cf0")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnProcess) {
                diagnostic.addEntry(checkR3250((BpmnProcess) element));
            } else if (element instanceof BpmnSubProcess) {
                BpmnSubProcess subProcess = (BpmnSubProcess) element;
                diagnostic.addEntry(checkR3250(subProcess));
            } else if (element instanceof BpmnFlowElement) {
                BpmnFlowElement flowElement = (BpmnFlowElement) element;
            
                BpmnSubProcess subProcess = flowElement.getSubProcess();
            
                // Case the FlowElement is in a non triggeredByEvent SubProcess
                if (subProcess != null) {
                    diagnostic.addEntry(checkR3250(subProcess));
                } else {
                    diagnostic.addEntry(checkR3250(flowElement.getContainer()));
                }
            } else {
                BpmnUi.LOG.warning("R3250: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("fef3399e-2b15-4fd8-980f-d88258edce17")
        private IAuditEntry checkR3250(final BpmnProcess process) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    process,
                    null);
            
            List<BpmnFlowNode> foundNodes = new ArrayList<>();
            for (BpmnFlowNode flowNode : process.getFlowElement(BpmnFlowNode.class)) {
                if (flowNode.getLane().isEmpty()) {
                    foundNodes.add(flowNode);
                }
            }
            
            // Process can escape to the rule if it is empty
            if (!foundNodes.isEmpty()) {
                BpmnStartEvent start = null;
                BpmnEndEvent end = null;
            
                for (BpmnFlowNode node : foundNodes) {
                    if (node instanceof BpmnStartEvent) {
                        start = (BpmnStartEvent) node;
                    } else if (node instanceof BpmnEndEvent) {
                        end = (BpmnEndEvent) node;
                    }
                }
            
                if (start == null || end == null) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(process.getMClass().getName());
                    linkedObjects.add(process);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        @objid ("92fd9eb9-7279-4f87-8804-baf269894ec6")
        private IAuditEntry checkR3250(final BpmnSubProcess subProcess) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    subProcess,
                    null);
            
            // Rule does not apply on this type of subProcess
            if (subProcess.isTriggeredByEvent()) {
                return auditEntry;
            }
            
            List<BpmnFlowNode> nodes = subProcess.getFlowElement(BpmnFlowNode.class);
            
            // SubProcess can escape to the rule if it is empty
            if (!nodes.isEmpty()) {
            
                BpmnStartEvent start = null;
                BpmnEndEvent end = null;
            
                for (BpmnFlowNode node : nodes) {
                    if (node instanceof BpmnStartEvent) {
                        start = (BpmnStartEvent) node;
                    } else if (node instanceof BpmnEndEvent) {
                        end = (BpmnEndEvent) node;
                    }
                }
            
                if (start == null || end == null) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(subProcess.getMClass().getName());
                    linkedObjects.add(subProcess);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
