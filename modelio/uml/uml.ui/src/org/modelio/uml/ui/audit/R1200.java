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

package org.modelio.uml.ui.audit;

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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61038 error
 */
@objid ("73d0a10a-93e8-48ab-9fb7-fe6940eb1bff")
public class R1200 extends AbstractUmlRule {
    @objid ("35713b4c-4902-4794-8305-352b7cfd16c9")
    private static final String RULEID = "R1200";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("e832f68f-3f4d-474b-ade5-9271b488ca4a")
    private CheckR1200 checkerInstance = null;

    @objid ("f8304236-4d7a-47b1-87ea-b9c32643606c")
    @Override
    public String getRuleId() {
        return R1200.RULEID;
    }

    @objid ("47dc93fb-9664-4bc7-b6d4-0e675a762144")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(DecisionMergeNode.MQNAME, this,
                AuditTrigger.UPDATE);
        
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE
                | AuditTrigger.MOVE);
        plan.registerRule(ControlFlow.MQNAME, this, AuditTrigger.CREATE
                | AuditTrigger.MOVE);
        plan.registerRule(MessageFlow.MQNAME, this, AuditTrigger.CREATE
                | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a7526ea8-487a-4911-abec-f02c277c1873")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("59c47608-81d3-4f26-8d1b-b6774cacf0d3")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("30bb6d29-d5d9-404f-88bd-4bcd65dab51d")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1200
     */
    @objid ("9e93e1ab-6504-48aa-99ec-0bc3766b4732")
    public R1200() {
        this.checkerInstance = new CheckR1200(this);
    }

    @objid ("0061a4f9-e318-41ba-bad9-4e7f584041b2")
    private static class CheckR1200 extends AbstractControl {
        @objid ("85ae7ca1-7c62-412e-8e86-89602c349ec0")
        public CheckR1200(IRule rule) {
            super(rule);
        }

        @objid ("a0688a46-fdad-4403-93ff-0a04a3313e5b")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof DecisionMergeNode) {
                diagnostic.addEntry(checkR1200((DecisionMergeNode) element));
            } else if (element instanceof ActivityEdge) {
                IAuditEntry entry = checkR1200((ActivityEdge) element);
                if (entry != null) {
                    diagnostic.addEntry(entry);
                }
            } else {
                UmlUi.LOG.warning("R1200: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("8b6e6df0-2004-4f5c-bc97-f66bac89abcb")
        private IAuditEntry checkR1200(DecisionMergeNode decisionMergeNode) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, decisionMergeNode, null);
            
            if (decisionMergeNode.getIncoming().isEmpty()
                    && decisionMergeNode.getOutgoing().isEmpty()) {
                return auditEntry;
            }
            
            List<ActivityEdge> edges = new ArrayList<>();
            edges.addAll(decisionMergeNode.getIncoming());
            edges.addAll(decisionMergeNode.getOutgoing());
            
            String referenceEdge = null;
            
            for (ActivityEdge edge : edges) {
            
                if (!(edge instanceof MessageFlow)) {
            
                    if (referenceEdge == null) {
                        referenceEdge = edge.getMClass().getName();
                        continue;
                    } else if (edge.getMClass().getName().equals(referenceEdge)) {
                        continue;
                    }
                }
            
                // Rule Failed because there was a message flow or because there
                // was both control and object flows.
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(decisionMergeNode);
                auditEntry.setLinkedInfos(linkedObjects);
            
                break;
            }
            return auditEntry;
        }

        @objid ("665e9ce4-aa20-4816-b2b3-0c2e09f3d5c0")
        private IAuditEntry checkR1200(ActivityEdge edge) {
            if (edge.getSource() instanceof DecisionMergeNode) {
                return checkR1200((DecisionMergeNode) edge.getSource());
            } else if (edge.getTarget() instanceof DecisionMergeNode) {
                return checkR1200((DecisionMergeNode) edge.getTarget());
            }
            return null;
        }

    }

}
