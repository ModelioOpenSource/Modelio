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
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3290 Severity : error Description : A SequenceFlow must exist to support the DataAssociations..
 */
@objid ("acbeeee1-dcf8-4169-ae72-6c5a7b8362bd")
public class R3290 extends AbstractBpmnRule {
    @objid ("ca6eae0d-db1d-4ab2-a69c-a42b7ac00d7c")
    private static final String RULEID = "R3290";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("6d26a13d-9344-469e-9b62-2219ffa98bb0")
    private CheckR3290 checkerInstance = null;

    @objid ("3ce49ce8-73a0-4d5c-ac60-0c3cd4654512")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnDataAssociation.MQNAME, this, AuditTrigger.ALL);
        plan.registerRule(BpmnDataObject.MQNAME, this, AuditTrigger.ALL);
        plan.registerRule(BpmnSequenceFlow.MQNAME, this, AuditTrigger.ALL);
    }

    @objid ("badc0c58-9a04-42ff-8172-124106660a2c")
    @Override
    public String getRuleId() {
        return R3290.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8ed66201-be4a-4558-b3ec-3fccf7ccdbcf")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c3a0d90c-31f9-4731-9970-43a5ec37df8a")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fa62eb26-78c9-47a1-bc3a-52660d28ee27")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3250
     */
    @objid ("6d6ae756-0fa0-4769-9bfe-2dbcf80470e0")
    public R3290() {
        this.checkerInstance = new CheckR3290(this);
    }

    /**
     * Actual checker for R3290: Checks that a SequenceFlow exists to support the DataAssociations.
     */
    @objid ("2b0d0c4e-1106-4625-bdda-107fd0f85c9a")
    private static class CheckR3290 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("82cde3c8-6ef2-4052-8b86-91d2593130f5")
        public CheckR3290(final IRule rule) {
            super(rule);
        }

        @objid ("fa3de042-e978-4d19-a464-e593f50b8e18")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnDataAssociation) {
                BpmnDataAssociation dataAssociation = (BpmnDataAssociation) element;
            
                for (BpmnDataObject sourceRef : dataAssociation.getSourceRef(BpmnDataObject.class)) {
                    diagnostic.addEntries(checkR3290(sourceRef));
                }
            
                BpmnItemAwareElement targetRef = dataAssociation.getTargetRef();
                if (targetRef instanceof BpmnDataObject) {
                    diagnostic.addEntries(checkR3290((BpmnDataObject) targetRef));
                }
            } else if (element instanceof BpmnSequenceFlow) {
                BpmnSequenceFlow sequenceFlow = (BpmnSequenceFlow) element;
            
                BpmnFlowNode sourceRef = sequenceFlow.getSourceRef();
                if (sourceRef instanceof BpmnDataObject) {
                    diagnostic.addEntries(checkR3290((BpmnDataObject) sourceRef));
                } else if (sourceRef instanceof BpmnActivity) {
                    BpmnActivity activity = (BpmnActivity) sourceRef;
                    for (BpmnDataAssociation bpmnDataAssociation : activity.getDataInputAssociation()) {
                        doRun(diagnostic, bpmnDataAssociation);
                    }
                } else if (sourceRef instanceof BpmnCatchEvent) {
                    BpmnCatchEvent event = (BpmnCatchEvent) sourceRef;
                    for (BpmnDataAssociation bpmnDataAssociation : event.getDataOutputAssociation()) {
                        doRun(diagnostic, bpmnDataAssociation);
                    }
                }
            
                BpmnFlowNode targetRef = sequenceFlow.getTargetRef();
                if (targetRef instanceof BpmnDataObject) {
                    diagnostic.addEntries(checkR3290((BpmnDataObject) targetRef));
                } else if (targetRef instanceof BpmnActivity) {
                    BpmnActivity activity = (BpmnActivity) targetRef;
                    for (BpmnDataAssociation bpmnDataAssociation : activity.getDataOutputAssociation()) {
                        doRun(diagnostic, bpmnDataAssociation);
                    }
                } else if (targetRef instanceof BpmnThrowEvent) {
                    BpmnThrowEvent event = (BpmnThrowEvent) targetRef;
                    for (BpmnDataAssociation bpmnDataAssociation : event.getDataInputAssociation()) {
                        doRun(diagnostic, bpmnDataAssociation);
                    }
                }
            } else
            
            if (element instanceof BpmnDataObject) {
                diagnostic.addEntries(checkR3290((BpmnDataObject) element));
            }
            return diagnostic;
        }

        @objid ("883a59b0-bbee-4238-9bb8-3ec9a8e6cec3")
        private List<IAuditEntry> checkR3290(final BpmnDataObject element) {
            List<IAuditEntry> ret = new ArrayList<>();
            
            List<BpmnFlowNode> sources = new ArrayList<>();
            for (BpmnDataAssociation assoc : element.getSourceOfDataAssociation()) {
                if (!assoc.getVisualShortCut().isEmpty()) {
                    continue;
                }
            
                sources.add(assoc.getStartingActivity());
                sources.add(assoc.getStartingEvent());
            }
            
            List<BpmnFlowNode> targets = new ArrayList<>();
            for (BpmnDataAssociation assoc : element.getTargetOfDataAssociation()) {
                if (!assoc.getVisualShortCut().isEmpty()) {
                    continue;
                }
            
                targets.add(assoc.getEndingActivity());
                targets.add(assoc.getEndingEvent());
            }
            
            for (BpmnFlowNode source : sources) {
                if (source == null) {
                    continue;
                }
            
                for (BpmnFlowNode target : targets) {
                    if (target == null) {
                        continue;
                    }
            
                    AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, element, null);
            
                    boolean isSupportedByFlow = false;
                    for (BpmnSequenceFlow flow : source.getOutgoing()) {
                        if (target.equals(flow.getTargetRef())) {
                            isSupportedByFlow = true;
                            break;
                        }
                    }
            
                    if (!isSupportedByFlow) {
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(element);
                        linkedObjects.add(source);
                        linkedObjects.add(target);
                        auditEntry.setLinkedInfos(linkedObjects);
                    }
            
                    ret.add(auditEntry);
                }
            }
            return ret;
        }

    }

}
