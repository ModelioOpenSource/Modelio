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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61058 error UML 2.3 Constraint on InputPin: Input pins may have outgoing edges only when they are on actions that are structured nodes, and these edges must target a node contained by the structured node.
 */
@objid ("c3d5bf17-5dec-4e0d-a2ac-fc2621fcfb78")
public class R1360 extends AbstractUmlRule {
    @objid ("6e6efd7d-34e6-493d-9857-f1d9b36d04a1")
    private static final String RULEID = "R1360";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("765db196-85ac-44d7-b390-3bdc03d938fe")
    private CheckR1360 checkerInstance = null;

    @objid ("6369b207-e027-4095-bf5d-730ecd47aae7")
    @Override
    public String getRuleId() {
        return R1360.RULEID;
    }

    @objid ("7b9be177-83d9-4d47-9760-18700054a373")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
        // ActivityEdge
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
        plan.registerRule(ControlFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
        plan.registerRule(MessageFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b07f984f-ae95-414c-ba54-981ba919ded5")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fc2bdd4c-6fcb-47c0-a138-1b808ae83410")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1d0af064-f810-4721-8c16-4911d002cb59")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1360
     */
    @objid ("85dac772-acc2-4eb1-8e4c-e42108084833")
    public  R1360() {
        this.checkerInstance = new CheckR1360(this);
    }

    @objid ("d7720000-6592-425d-b5e8-2b6326304c77")
    private static class CheckR1360 extends AbstractControl {
        @objid ("4320855d-ed3b-422a-b419-b4a994303ae2")
        public  CheckR1360(IRule rule) {
            super(rule);
        }

        @objid ("b24e3002-7f96-4afd-ae25-ef6fda7b9f86")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof InputPin) {
                diagnostic.addEntries(checkR1360((InputPin) element));
            } else if (element instanceof ActivityEdge) {
                diagnostic.addEntry(checkR1360((ActivityEdge) element));
            } else {
                UmlUi.LOG.warning("R1360: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("33b3685b-6485-4348-a11a-0fb8a589676b")
        private List<IAuditEntry> checkR1360(InputPin inputPin) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            for (ActivityEdge outgoingEdge : inputPin.getOutgoing()) {
                auditEntries.add(checkR1360(outgoingEdge));
            }
            return auditEntries;
        }

        @objid ("91c83511-0907-4fcc-8042-304516e69ad0")
        private IAuditEntry checkR1360(ActivityEdge activityEdge) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    activityEdge,
                    null);
            
            ActivityNode source = activityEdge.getSource();
            ActivityNode target = activityEdge.getTarget();
            
            StructuredActivityNode targetStructuredOwner = getOwningStructuredActivityNode(target);
            
            if (source instanceof InputPin) {
                MObject owner = source.getCompositionOwner();
                if (!(owner instanceof StructuredActivityNode) ||
                        !((StructuredActivityNode) owner).equals(targetStructuredOwner)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(activityEdge);
                    linkedObjects.add(activityEdge.getSource());
                    linkedObjects.add(activityEdge.getTarget());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        @objid ("cf13e494-609e-443b-a6b0-2fcd1fdac1aa")
        private StructuredActivityNode getOwningStructuredActivityNode(ActivityNode node) {
            // Important guard as we use recursive calls
            if (node == null) {
                return null;
            }
            
            // 'node' is an input pin
            if (node instanceof InputPin) {
                return getOwningStructuredActivityNode(((InputPin) node).getInputing());
            }
            
            // 'node' is an output pin
            if (node instanceof OutputPin) {
                return getOwningStructuredActivityNode(((OutputPin) node).getOutputing());
            }
            
            // 'node' is a clause
            if (node instanceof Clause) {
                Clause clause = (Clause) node;
                return clause.getOwner();
            }
            
            // other cases
            if (node.getOwnerClause() != null && node.getOwnerClause().getOwner() != null) {
                return node.getOwnerClause().getOwner();
            } else if (node.getOwnerNode() != null) {
                return node.getOwnerNode();
            } else {
                return null;
            }
            
        }

    }

}
