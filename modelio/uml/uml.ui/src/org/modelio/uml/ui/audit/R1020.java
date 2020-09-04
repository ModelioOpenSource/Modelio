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
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.FlowFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.behavior.activityModel.InitialNode;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61015 error
 */
@objid ("7b735f66-4ca7-4e1b-aa88-3286c4e3196d")
public class R1020 extends AbstractUmlRule {
    @objid ("f4b776d7-d2e9-47fa-9ea8-d440b6939b16")
    private static final String RULEID = "R1020";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("fc1fcda5-471f-4d76-b003-0981a08fbfd7")
    private CheckR1020 checkerInstance = null;

    /**
     * end class CheckR1020
     */
    @objid ("a4475d81-bbbe-476c-8cba-b8467121102a")
    @Override
    public String getRuleId() {
        return R1020.RULEID;
    }

    @objid ("ac78350a-2230-4920-be2d-5e4531e8fc2c")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // Activity edges
        plan.registerRule(ControlFlow.MQNAME, this, AuditTrigger.UPDATE |
                AuditTrigger.CREATE |
                AuditTrigger.MOVE);
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.UPDATE |
                AuditTrigger.CREATE |
                AuditTrigger.MOVE);
        plan.registerRule(MessageFlow.MQNAME, this, AuditTrigger.UPDATE |
                AuditTrigger.CREATE |
                AuditTrigger.MOVE);
        plan.registerRule(ExceptionHandler.MQNAME, this, AuditTrigger.UPDATE |
                AuditTrigger.CREATE |
                AuditTrigger.MOVE);
        
        // Activity nodes
        plan.registerRule(AcceptCallEventAction.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(AcceptChangeEventAction.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(AcceptTimeEventAction.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(AcceptSignalAction.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(CallBehaviorAction.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(CallOperationAction.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(OpaqueAction.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(SendSignalAction.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(StructuredActivityNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(ConditionalNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(LoopNode.MQNAME, this, AuditTrigger.MOVE);
        
        plan.registerRule(DecisionMergeNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(ActivityFinalNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(FlowFinalNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(ForkJoinNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(InitialNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(ActivityParameterNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(CentralBufferNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(DataStoreNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(InstanceNode.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(OutputPin.MQNAME, this, AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("24279067-ebb8-4fcc-b1e5-82bca24071bd")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("133e2fc1-ccfb-4c1c-9cbb-43078ca97a74")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("387331db-9862-45f7-9771-6e5e55730f46")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1020
     */
    @objid ("5d3fa42e-e63a-4703-bf6e-bc25083a4d25")
    public R1020() {
        this.checkerInstance = new CheckR1020(this);
    }

    @objid ("702e9870-8118-461b-8749-2d91af159705")
    private static class CheckR1020 extends AbstractControl {
        @objid ("4bf8c35d-9a38-44f0-b892-ad8d32f5cc16")
        public CheckR1020(IRule rule) {
            super(rule);
        }

        @objid ("5126d933-efa8-4168-a067-71b0d3ea349e")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityNode) {
                diagnostic.addEntries(checkR1020((ActivityNode) element));
            } else if (element instanceof ActivityEdge) {
                diagnostic.addEntry(checkR1020((ActivityEdge) element));
            } else if (element instanceof ExceptionHandler) {
                diagnostic.addEntry(checkR1020((ExceptionHandler) element));
            } else {
                UmlUi.LOG.warning("R1020: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("87a49be3-2336-4b2d-a8fc-4f3b7b651fc1")
        private List<IAuditEntry> checkR1020(ActivityNode node) {
            ArrayList<IAuditEntry> auditEntries = new ArrayList<>();
            
            // Incoming edges
            for (ActivityEdge edge : node.getIncoming()) {
                auditEntries.add(checkR1020(edge));
            }
            
            // Outgoing edges
            for (ActivityEdge edge : node.getOutgoing()) {
                auditEntries.add(checkR1020(edge));
            }
            return auditEntries;
        }

        @objid ("9e51d4c0-3350-43d2-8a0c-45a715323576")
        private IAuditEntry checkR1020(ActivityEdge edge) {
            ActivityNode source = edge.getSource();
            ActivityNode target = edge.getTarget();
            return checkR1020(source, target, edge);
        }

        @objid ("903069d7-d702-4c17-a931-c4699d3df8e2")
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
            
            // 'node' is an expansion node
            if (node instanceof ExpansionNode) {
                return getOwningStructuredActivityNode(((ExpansionNode) node).getOwnerNode());
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

        @objid ("0b82f1ed-9f9c-49fc-9f81-70f080ac3533")
        private IAuditEntry checkR1020(final ExceptionHandler exceptionHandler) {
            ActivityNode source = exceptionHandler.getProtectedNode();
            ActivityNode target = exceptionHandler.getExceptionInput();
            return checkR1020(source, target, exceptionHandler);
        }

        @objid ("11ed80ab-fc85-4354-a4d1-ea05c0f9c570")
        private IAuditEntry checkR1020(final ActivityNode source, final ActivityNode target, final Element element) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    element,
                    null);
            
            if (source != null && target != null) {
                StructuredActivityNode sourceOwner = getOwningStructuredActivityNode(source);
                StructuredActivityNode targetOwner = getOwningStructuredActivityNode(target);
            
                // valid case 1
                // the owner nodes are the same (either both NULL either both
                // the same StructuredActivityNode
                if (sourceOwner == null) {
                    if (targetOwner == null) {
                        return auditEntry; // both are null
                    }
                } else {
                    if (sourceOwner.equals(targetOwner)) {
                        return auditEntry; // both are equals
                    }
                }
            
                // valid case 2
                // activity edge from InputPin inputing a
                // StructuredActivityNode, to an object inside the same
                // StructuredActivityNode
                if (source instanceof InputPin) {
                    InputPin sourceAsInputPin = (InputPin) source;
                    if (sourceAsInputPin.getInputing() != null) {
                        if (sourceAsInputPin.getInputing().equals(targetOwner)) {
                            return auditEntry;
                        }
                    }
                }
            
                // valid case 3
                // activity edge from an object inside a StructuredActivityNode,
                // to an OutputPin outputing the same StructuredActivityNode
                if (target instanceof OutputPin) {
                    OutputPin targetAsOutputPin = (OutputPin) target;
                    if (targetAsOutputPin.getOutputing() != null) {
                        if (targetAsOutputPin.getOutputing().equals(sourceOwner)) {
                            return auditEntry;
                        }
                    }
                }
            
                // valid case 4
                // activity edge from (or to) from an object inside a
                // StructuredActivityNode, to (or from) an expansion node
                // contained by an expansion region in the same
                // StructuredActivityNode
                if (source instanceof ExpansionNode) {
                    ExpansionNode node = (ExpansionNode) source;
                    if (node.getOwnerNode().equals(targetOwner)) {
                        return auditEntry;
                    }
                }
                if (target instanceof ExpansionNode) {
                    ExpansionNode node = (ExpansionNode) target;
                    if (node.getOwnerNode().equals(sourceOwner)) {
                        return auditEntry;
                    }
                }
            
                // if this point is reached, the check failed
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(source);
                linkedObjects.add(target);
                linkedObjects.add(element);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
