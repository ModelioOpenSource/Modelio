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
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
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
 * Rule implementation origin: ActivityModelChecker checkR61014 error
 */
@objid ("bff74350-b135-4c23-bacc-7aaaee235665")
public class R1030 extends AbstractUmlRule {
    @objid ("55d6adbb-6c99-4192-a712-828c141576d6")
    private static final String RULEID = "R1030";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("b6747270-5a35-491a-a79a-f7d2fc800235")
    private CheckR1030 checkerInstance = null;

    /**
     * end class
     */
    @objid ("fd9f70f7-d420-4902-a183-f55350c44ef3")
    @Override
    public String getRuleId() {
        return R1030.RULEID;
    }

    @objid ("daa0a6e1-a234-440c-b770-632c10f4948f")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // <=> ActivityNode MOVE
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
        
        // ActivityEdge
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
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("17bffb42-36b6-4ed2-9f56-4d542b38cd64")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("64ee2c41-221c-46a8-83af-ab8ebf2dfb76")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a04c0b84-a54a-4da8-81e6-9933d238d9de")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1030
     */
    @objid ("a1c8f314-b2f2-4cbb-8d65-4172726a7cb2")
    public R1030() {
        this.checkerInstance = new CheckR1030(this);
    }

    @objid ("489c6581-50ab-4e17-a54c-df86dafe9f86")
    private static class CheckR1030 extends AbstractControl {
        @objid ("27de5381-76be-4820-a462-6fc6f3f08490")
        public CheckR1030(IRule rule) {
            super(rule);
        }

        @objid ("3c90dfb5-403b-4631-80e5-9549eb514734")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityNode) {
                diagnostic.addEntries(checkR1030((ActivityNode) element));
            
            } else if (element instanceof ActivityEdge) {
                diagnostic.addEntry(checkR1030((ActivityEdge) element));
            } else if (element instanceof ExceptionHandler) {
                diagnostic.addEntry(checkR1020((ExceptionHandler) element));
            } else {
                UmlUi.LOG.warning("R1030: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("a5ec0a82-d951-497c-9086-03d3cfc01533")
        private List<IAuditEntry> checkR1030(ActivityNode node) {
            // R61014 The source and target of an edge must be in the same activity
            ArrayList<IAuditEntry> auditEntries = new ArrayList<>();
            
            // Incoming edges
            for (ActivityEdge edge : node.getIncoming()) {
                auditEntries.add(checkR1030(edge));
            }
            
            // Outgoing edges
            for (ActivityEdge edge : node.getOutgoing()) {
                auditEntries.add(checkR1030(edge));
            }
            
            if (node instanceof ActivityAction) {
                // outgoing pins
                for (OutputPin pin : ((ActivityAction) node).getOutput()) {
                    auditEntries.addAll(checkR1030(pin));
                }
            
                // incoming pins
                for (InputPin pin : ((ActivityAction) node).getInput()) {
                    auditEntries.addAll(checkR1030(pin));
                }
            }
            return auditEntries;
        }

        @objid ("d34f60f1-7226-4d9c-8c42-2bbd6d7cca62")
        private IAuditEntry checkR1030(ActivityEdge edge) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    edge,
                    null);
            
            // R61014 The source and target of an edge must be in the same activity
            if (edge.getSource() == null) {
                return auditEntry;
            }
            
            if (edge.getTarget() == null) {
                return auditEntry;
            }
            
            ActivityNode source = edge.getSource();
            ActivityNode target = edge.getTarget();
            
            Activity sourceOwner = getOwningActivity(source);
            Activity targetOwner = getOwningActivity(target);
            
            if (sourceOwner.equals(targetOwner) == false) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(source);
                linkedObjects.add(target);
                linkedObjects.add(edge);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("d2a0e92f-5131-4324-90d6-5def5e4fbb09")
        private Activity getOwningActivity(ActivityNode node) {
            MObject parent = node;
            while (parent != null && !(parent instanceof Activity)) {
                parent = parent.getCompositionOwner();
            }
            return (Activity) parent;
        }

        @objid ("668a7aa4-8dea-4ffc-8df9-1df2248078a0")
        public IAuditEntry checkR1020(final ExceptionHandler exceptionHandler) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    exceptionHandler,
                    null);
            
            // R61014 The source and target of an edge must be in the same activity
            
            ActivityNode source = exceptionHandler.getProtectedNode();
            ActivityNode target = exceptionHandler.getExceptionInput();
            
            if (source == null) {
                return auditEntry;
            }
            
            if (target == null) {
                return auditEntry;
            }
            
            Activity sourceOwner = getOwningActivity(source);
            Activity targetOwner = getOwningActivity(target);
            
            if (sourceOwner.equals(targetOwner) == false) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(source);
                linkedObjects.add(target);
                linkedObjects.add(exceptionHandler);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
