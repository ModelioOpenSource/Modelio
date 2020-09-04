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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61046 error
 */
@objid ("26ca3435-dc8c-4609-a19c-a1d7ba0703cc")
public class R1250 extends AbstractUmlRule {
    @objid ("1b04c92e-f342-4913-96df-1aa04813865a")
    private static final String RULEID = "R1250";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("ad9cb02e-f4bb-4398-bcf6-46bd9937a498")
    private CheckR1250 checkerInstance = null;

    @objid ("9dee868a-546d-44ba-9a5f-8ff6905957c8")
    @Override
    public String getRuleId() {
        return R1250.RULEID;
    }

    @objid ("0b473b09-59d2-421e-b133-1b8f14031886")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(ForkJoinNode.MQNAME, this,
                AuditTrigger.UPDATE);
        
        // ActivityEdge
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
    @objid ("4511fd16-4e34-4592-9340-6dd115f4667f")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a59f2ee1-ea7e-4a94-a636-b35cbdde540d")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9a252b20-dc23-4096-bca8-4fc2a946f318")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1250
     */
    @objid ("01e7fdf2-5339-4412-a14e-edbbf8a1484d")
    public R1250() {
        this.checkerInstance = new CheckR1250(this);
    }

    @objid ("e721af46-107c-490b-b474-6ef317792184")
    private static class CheckR1250 extends AbstractControl {
        @objid ("960d34c7-4ddc-4b57-94d4-ad2d72251f80")
        public CheckR1250(IRule rule) {
            super(rule);
        }

        @objid ("c24762a9-3e9c-4f3e-b9ac-71a17d36c38f")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ForkJoinNode) {
                diagnostic.addEntry(checkR1250((ForkJoinNode) element));
            } else if (element instanceof ActivityEdge) {
                diagnostic
                        .addEntries(checkR1250((ActivityEdge) element));
            } else {
                UmlUi.LOG.warning("R1210: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f37dd73d-0eba-49f4-8fde-1a70f189503a")
        private IAuditEntry checkR1250(ForkJoinNode forkJoinNode) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, forkJoinNode, null);
            
            boolean hasInObject = false;
            boolean hasOutObject = false;
            boolean hasInControl = false;
            boolean hasOutControl = false;
            
            for (ActivityEdge inEdge : forkJoinNode.getIncoming()) {
                if (!hasInObject && inEdge instanceof ObjectFlow) {
                    hasInObject = true;
                } else if (!hasInControl && inEdge instanceof ControlFlow) {
                    hasInControl = true;
                }
            }
            
            for (ActivityEdge outEdge : forkJoinNode.getOutgoing()) {
                if (!hasOutObject && outEdge instanceof ObjectFlow) {
                    hasOutObject = true;
                } else if (!hasOutControl && outEdge instanceof ControlFlow) {
                    hasOutControl = true;
                }
            }
            
            if (((hasInObject && hasOutObject)
                    || (!hasInObject && !hasOutObject))
                    && ((hasInControl && hasOutControl)
                            || (!hasInControl && !hasOutControl))) {
                return auditEntry;
            }
            
            // Rule failed
            
            auditEntry.setSeverity(this.rule.getSeverity());
            List<Object> linkedObjects = new ArrayList<>();
            linkedObjects.add(forkJoinNode);
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

        @objid ("6f73ee45-3d09-42f9-aec0-8253ad5f7733")
        private List<IAuditEntry> checkR1250(ActivityEdge activityEdge) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            ActivityNode sourceNode = activityEdge.getSource();
            ActivityNode targetNode = activityEdge.getTarget();
            
            if (sourceNode instanceof ForkJoinNode) {
                auditEntries.add(checkR1250((ForkJoinNode) sourceNode));
            } else if (targetNode instanceof ForkJoinNode) {
                auditEntries.add(checkR1250((ForkJoinNode) targetNode));
            }
            return auditEntries;
        }

    }

}
