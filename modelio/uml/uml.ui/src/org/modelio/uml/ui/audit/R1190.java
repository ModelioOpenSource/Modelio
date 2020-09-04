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
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * New rule for Modelio DecisionMerge behaviour
 */
@objid ("86d282ea-9680-4706-93ee-b69b2a8519f7")
public class R1190 extends AbstractUmlRule {
    @objid ("ea40f4eb-672d-4d1c-8c73-7a17f7ef8784")
    private static final String RULEID = "R1190";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("fccea71b-b11a-4e7e-a298-ba866bee4454")
    private CheckR1190 checkerInstance = null;

    @objid ("872be542-063d-454e-94ee-0f3e17aaf1a6")
    @Override
    public String getRuleId() {
        return R1190.RULEID;
    }

    @objid ("8382228d-f6da-4c18-ada9-6e99b6bd9ad4")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(DecisionMergeNode.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(ControlFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("833856a8-4770-4755-b4cf-1aeb83940636")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1e6217c3-de7d-4477-b1a9-eb382189c0c1")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c83222a5-6edb-44cb-9e75-fa177a16b742")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1190
     */
    @objid ("8b34ef15-a025-41c4-8431-accafa5c4784")
    public R1190() {
        this.checkerInstance = new CheckR1190(this);
    }

    @objid ("a56a546a-3de3-49a5-9ba7-6bb2c179c327")
    private static class CheckR1190 extends AbstractControl {
        @objid ("d6cd49e7-7615-4bf2-a16b-bd11d83843cf")
        public CheckR1190(IRule rule) {
            super(rule);
        }

        @objid ("89370bad-0689-4873-b36d-1e61486548aa")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof DecisionMergeNode) {
                diagnostic.addEntry(checkR1190((DecisionMergeNode) element));
            } else if (element instanceof ActivityEdge) {
                diagnostic.addEntries(checkR1190((ActivityEdge) element));
            } else {
                UmlUi.LOG.warning("R1190: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f6b7303b-a605-4f13-a534-0fec124f7595")
        private List<IAuditEntry> checkR1190(ActivityEdge edge) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            ActivityNode source = edge.getSource();
            if (source instanceof DecisionMergeNode) {
                auditEntries.add(checkR1190((DecisionMergeNode) source));
            }
            
            ActivityNode target = edge.getTarget();
            if (target instanceof DecisionMergeNode) {
                auditEntries.add(checkR1190((DecisionMergeNode) target));
            }
            return auditEntries;
        }

        @objid ("881ed302-7acd-4ffe-b613-1258c951e983")
        private IAuditEntry checkR1190(DecisionMergeNode decisionMerge) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, decisionMerge, null);
            
            // This rule is necessary because Modelio uses the same element to represents Decision nodes and Merge nodes.
            // However, it is advised to use the DecisionMerge in only one configuration, either Decision or Merge, and not the two at the same time.
            
            List<ActivityEdge> sourceEdges = decisionMerge.getIncoming();
            List<ActivityEdge> targetEdges = decisionMerge.getOutgoing();
            
            if (sourceEdges.size() > 1 && targetEdges.size() > 1) {
            
                // Rule failed
            
                List<Object> linkedObjects = new ArrayList<>();
                auditEntry.setSeverity(this.rule.getSeverity());
                linkedObjects.add(decisionMerge);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
