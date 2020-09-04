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
import org.modelio.audit.engine.core.AbstractRule;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.bpmn.ui.plugin.BpmnUi;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3320 Severity : error Description : A SequenceFlow outgoing from an EventBasedGateway must target an IntermediaryCatchEvent.
 */
@objid ("3bc3d06a-934b-4922-b381-a0051fd6f250")
public class R3220 extends AbstractBpmnRule {
    @objid ("d7b3de5b-2a64-4986-935c-22b6e3f89746")
    private static final String RULEID = "R3220";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("246867b6-05aa-4e50-bf87-59a113b36d5b")
    private CheckR3220 checkerInstance = null;

    @objid ("e2b12ea8-bd4b-4677-a260-a496317e7af9")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnSequenceFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    @objid ("642def62-4290-49b6-85a7-daa2da39420c")
    @Override
    public String getRuleId() {
        return R3220.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8ff65527-1c88-4139-bd4a-9fd3d52fd731")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6729a975-2526-4d77-8c4c-65aadee75f39")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ddc534cc-f79e-4e83-adcf-bb7a32baa79c")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3220
     */
    @objid ("2d6d10c9-2f88-4b09-a9a3-a00422a3e1b8")
    public R3220() {
        this.checkerInstance = new CheckR3220(this);
    }

    /**
     * Actual checker for R3220: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("e82ff667-81cb-4884-8657-1346d6da4252")
    private static class CheckR3220 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("9125dd8d-a9ad-489a-a730-b4ea8f5b0f1d")
        public CheckR3220(final IRule rule) {
            super(rule);
        }

        @objid ("f6c4f98c-afb5-469a-8dc4-301907666780")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnSequenceFlow) {
                diagnostic.addEntry(checkR3220((BpmnSequenceFlow) element));
            } else {
                BpmnUi.LOG.warning("R3220: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("4b670014-8702-4f11-94b3-a65e6724e3ad")
        private IAuditEntry checkR3220(final BpmnSequenceFlow seqFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    seqFlow,
                    null);
            
            if (seqFlow.getSourceRef() instanceof BpmnEventBasedGateway &&
                    !(seqFlow.getTargetRef() instanceof BpmnIntermediateCatchEvent)) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(seqFlow);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
