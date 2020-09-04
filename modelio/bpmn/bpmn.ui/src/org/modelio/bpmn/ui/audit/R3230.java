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
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3230 Severity : warning Dscription : All SequenceFLow outgoing from an ExclusiveGateway must have a guard, except for the default SequenceFlow.
 */
@objid ("69b9793e-8a3d-4d43-a715-8ab868ecb2f9")
public class R3230 extends AbstractBpmnRule {
    @objid ("503866cc-c393-48fb-aaff-493a72fd4392")
    private static final String RULEID = "R3230";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("0273685f-fb28-4e89-bc0a-c9f538ead6de")
    private CheckR3230 checkerInstance = null;

    @objid ("cdf7c558-ae54-4cfc-8d28-584d04b00cc5")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnSequenceFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(BpmnExclusiveGateway.MQNAME, this, AuditTrigger.UPDATE);
    }

    @objid ("ac2b016b-d075-4126-83ce-587a61b8d255")
    @Override
    public String getRuleId() {
        return R3230.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e9e0b50a-6a26-42db-89ee-f6d21c399992")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("67330a9e-48a9-4b66-bc87-4a57c2ff0604")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9f6d43c0-dc86-4852-9e47-1fa0681243bd")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3230
     */
    @objid ("af5743e1-7300-4af6-8296-80a69f446387")
    public R3230() {
        this.checkerInstance = new CheckR3230(this);
    }

    /**
     * Actual checker for R3230: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("bcf1d6ea-a944-4f55-8e82-9fda89cd1e6f")
    private static class CheckR3230 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("429fd014-39b2-40e3-b4cf-244291a3fca5")
        public CheckR3230(final IRule rule) {
            super(rule);
        }

        @objid ("4900ee06-0711-4c5f-a1ad-6cb6c272217d")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnSequenceFlow) {
                BpmnFlowNode flowNode = ((BpmnSequenceFlow) element).getSourceRef();
                if (flowNode instanceof BpmnExclusiveGateway) {
                    diagnostic.addEntry(checkR1050((BpmnExclusiveGateway) flowNode));
                }
            } else if (element instanceof BpmnExclusiveGateway) {
                diagnostic.addEntry(checkR1050((BpmnExclusiveGateway) element));
            } else {
                BpmnUi.LOG.warning("R3230: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("915ebc31-b28c-42a5-8a75-c740e0814e61")
        private IAuditEntry checkR1050(final BpmnExclusiveGateway excluGateway) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    excluGateway,
                    null);
            
            if (excluGateway.getOutgoing().size() > 1) {
                for (BpmnSequenceFlow seqFlow : excluGateway.getOutgoing()) {
                    if (seqFlow.getDefaultOfExclusive() == null) {
                        if (seqFlow.getConditionExpression().isEmpty()) {
            
                            // Rule failed
            
                            auditEntry.setSeverity(this.rule.getSeverity());
                            List<Object> linkedObjects = new ArrayList<>();
                            linkedObjects.add(excluGateway);
                            auditEntry.setLinkedInfos(linkedObjects);
                        }
                    }
                }
            }
            return auditEntry;
        }

    }

}
