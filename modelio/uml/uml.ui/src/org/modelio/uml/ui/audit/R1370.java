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
 * Rule implementation origin: ActivityModelChecker checkR61059 error UML 2.3 Constraint on OutputPin: Output pins may have incoming edges only when they are on actions that are structured nodes, and these edges may not target a node contained by the structured node.
 */
@objid ("c5eddc2d-077b-40e1-ac89-d9ea532a536f")
public class R1370 extends AbstractUmlRule {
    @objid ("c87ee393-b447-4ec2-8557-c8caea190bac")
    private static final String RULEID = "R1370";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("894d6bda-0b4d-40fd-ae80-218b5ae98097")
    private CheckR1370 checkerInstance = null;

    @objid ("95d7dad0-eb15-4931-b648-5defc4a8f588")
    @Override
    public String getRuleId() {
        return R1370.RULEID;
    }

    @objid ("48bf8577-41ce-403f-9427-eb7cd39b8e4f")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(OutputPin.MQNAME, this, AuditTrigger.CREATE |
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
    @objid ("339aa93d-5ba0-4a94-9a9c-65e390adda7c")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("37e5cd51-4d51-47c3-afb4-a73d87937bbe")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a45b858e-eae5-4914-bc26-03d10e3c716d")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1370
     */
    @objid ("d2709791-48a1-4300-b7a1-0d977fcdc1f0")
    public R1370() {
        this.checkerInstance = new CheckR1370(this);
    }

    @objid ("eb29d670-1d02-4af5-9102-a3c60c5333c0")
    private static class CheckR1370 extends AbstractControl {
        @objid ("5ce6de13-3ca6-419f-88b3-4d37dc8b1324")
        public CheckR1370(IRule rule) {
            super(rule);
        }

        @objid ("489959b4-589d-47c6-b343-737e93895437")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof OutputPin) {
                diagnostic.addEntries(checkR1370((OutputPin) element));
            } else if (element instanceof ActivityEdge) {
                diagnostic.addEntry(checkR1370((ActivityEdge) element));
            } else {
                UmlUi.LOG.warning("R1370: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("be303110-fb97-4d3b-a22e-9adf9403494c")
        private List<IAuditEntry> checkR1370(OutputPin outputPin) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            for (ActivityEdge outgoingEdge : outputPin.getIncoming()) {
                auditEntries.add(checkR1370(outgoingEdge));
            }
            return auditEntries;
        }

        @objid ("a9ea0e76-3d55-4787-8846-60487cb4ded8")
        private IAuditEntry checkR1370(ActivityEdge activityEdge) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    activityEdge,
                    null);
            
            ActivityNode source = activityEdge.getSource();
            ActivityNode target = activityEdge.getTarget();
            
            StructuredActivityNode sourceStructuredOwner = getOwningStructuredActivityNode(source);
            
            if (target instanceof OutputPin) {
                MObject owner = target.getCompositionOwner();
                if (!(owner instanceof StructuredActivityNode) ||
                        !((StructuredActivityNode) owner).equals(sourceStructuredOwner)) {
            
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

        @objid ("50ab68e9-fa48-44cb-ab02-4351aaf6399b")
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
