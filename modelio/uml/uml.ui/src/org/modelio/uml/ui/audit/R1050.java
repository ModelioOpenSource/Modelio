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
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
 * 
 * @author fpoyer
 */
@objid ("46d33409-bc25-4bce-a38c-914ad7a76fe6")
public class R1050 extends AbstractUmlRule {
    @objid ("6bab00df-ff2b-4733-bb6b-636e631084df")
    private static final String RULEID = "R1050";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("48e04512-5d4e-4683-991d-4cc9d1dee7a0")
    private CheckR1050 checkerInstance = null;

    @objid ("b123555f-6a65-456f-885c-bdb53e3b3757")
    private CheckFlowDeleteR1050 deleteCheckerInstance = null;

    @objid ("3a53f914-b0df-4a02-b6f9-78b1bdab980b")
    @Override
    public String getRuleId() {
        return R1050.RULEID;
    }

    @objid ("6109020b-7c36-41c2-973a-ecd4119ffd84")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(ActivityParameterNode.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE);
        
        plan.registerRule(ControlFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE |
                AuditTrigger.DELETE);
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE |
                AuditTrigger.DELETE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b08c42e5-019b-4441-bf85-067c8d71f107")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("dec6717e-91eb-4416-a58a-53c3bef0d988")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("63e34bcb-ff67-432c-a29f-3bf61de4bffd")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1050
     */
    @objid ("57bb4221-2db4-4290-bc91-f55fabb75d6f")
    public R1050() {
        this.checkerInstance = new CheckR1050(this);
        this.deleteCheckerInstance = new CheckFlowDeleteR1050(this);
    }

    @objid ("af3ff378-6804-4f76-a88f-aeb5fa7654ef")
    @Override
    public IControl getDeleteControl(final MObject element) {
        return this.deleteCheckerInstance;
    }

    /**
     * Actual checker for R1050: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("a94c98e4-ca15-44e6-9a33-e0ec4ba7646c")
    private static class CheckR1050 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("d35fd839-fc2d-4c31-a39d-e42220302090")
        public CheckR1050(IRule rule) {
            super(rule);
        }

        @objid ("bd02b3bb-bd1a-4139-8b71-455c1b822f6e")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityParameterNode) {
                diagnostic.addEntry(checkR1050((ActivityParameterNode) element));
            } else if (element instanceof ActivityEdge) {
                diagnostic.addEntries(checkR1050((ActivityEdge) element));
            } else {
                UmlUi.LOG.warning("R1050: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("22fc3d72-42a2-4463-a84d-7c3f097cd1d3")
        protected IAuditEntry checkR1050(ActivityParameterNode node) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    node,
                    null);
            
            if (!node.getIncoming().isEmpty() && !node.getOutgoing().isEmpty()) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(node);
                for (ActivityEdge edge : node.getIncoming()) {
                    linkedObjects.add(edge);
                }
                for (ActivityEdge edge : node.getOutgoing()) {
                    linkedObjects.add(edge);
                }
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("4040a3e1-3410-4baa-a944-dbd9f812fed2")
        private List<IAuditEntry> checkR1050(ActivityEdge edge) {
            List<IAuditEntry> auditentries = new ArrayList<>();
            
            ActivityNode source = edge.getSource();
            ActivityNode target = edge.getTarget();
            
            if (source instanceof ActivityParameterNode) {
                auditentries.add(checkR1050((ActivityParameterNode) source));
            }
            
            if (target instanceof ActivityParameterNode) {
                auditentries.add(checkR1050((ActivityParameterNode) target));
            }
            return auditentries;
        }

    }

    @objid ("d788afe1-71b7-49a4-9a51-c103f474f3d8")
    private static class CheckFlowDeleteR1050 extends CheckR1050 {
        @objid ("a6bb89dc-65f5-4e1c-94a7-f3bedb7e86f7")
        public CheckFlowDeleteR1050(final IRule rule) {
            super(rule);
        }

        @objid ("9bfe1462-a823-4c21-8967-922e2d416ce5")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof ActivityNode) {
                Activity activity = ((ActivityNode) element).getOwner();
                if (activity != null) {
                    diagnostic.addEntries(checkR1050(activity));
                }
            } else {
                UmlUi.LOG.warning("R1050: unsupported element type '%s'", element.getMClass().getName());
            }
            return null;
        }

        @objid ("169b5a5c-45a1-479c-8758-c1528b548b93")
        private List<IAuditEntry> checkR1050(final Activity activity) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            for (ActivityParameterNode apn : activity.getOwnedNode(ActivityParameterNode.class)) {
                auditEntries.add(checkR1050(apn));
            }
            return auditEntries;
        }

    }

}
