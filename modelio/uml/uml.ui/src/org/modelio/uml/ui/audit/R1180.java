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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61036 error
 */
@objid ("49008e6a-56d5-4e93-b35f-779481061305")
public class R1180 extends AbstractUmlRule {
    @objid ("6b37c6d3-18f2-4f68-9629-be9f99e54afb")
    private static final String RULEID = "R1180";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("1c3d0395-8e3e-4ca1-9211-8367c74576a3")
    private CheckR1180 checkerInstance = null;

    @objid ("04a174f7-b255-4c2c-b499-c2827d40fce2")
    @Override
    public String getRuleId() {
        return R1180.RULEID;
    }

    @objid ("5287b9b3-78ab-4bd1-8597-fc8add3e0871")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(ControlFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
        
        // ObjectNode
        plan.registerRule(ExpansionNode.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(InstanceNode.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(OutputPin.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(ActivityParameterNode.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(CentralBufferNode.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ed23bd8f-dae5-4ff7-8f09-c3df102f7fb1")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a44e054f-4c4d-42e5-b990-e68a50626ebe")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("4782385c-edca-47e7-a41c-b22775940125")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1180
     */
    @objid ("6db1e624-d327-4628-9f16-0a83339c774a")
    public R1180() {
        this.checkerInstance = new CheckR1180(this);
    }

    @objid ("c8380789-be07-47c1-a0ab-f975c5e50629")
    private static class CheckR1180 extends AbstractControl {
        @objid ("0b4a82f6-3cc0-403d-85cd-938e490196b4")
        public CheckR1180(IRule rule) {
            super(rule);
        }

        @objid ("7cc43b32-7d69-4956-8ee8-6c394cbdf472")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ControlFlow) {
                diagnostic.addEntry(checkR1180((ControlFlow) element));
            } else if (element instanceof ObjectNode) {
                diagnostic.addEntries(checkR1180((ObjectNode) element));
            } else {
                UmlUi.LOG.warning("R1180: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("b27d2a12-c1e4-432d-a68c-6bc6e0d32d9c")
        private IAuditEntry checkR1180(ControlFlow controlFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, controlFlow, null);
            
            ActivityNode sourceNode = controlFlow.getSource();
            ActivityNode targetNode = controlFlow.getTarget();
            
            if ((sourceNode instanceof ObjectNode && !((ObjectNode) sourceNode)
                    .isIsControlType())
                    || (targetNode instanceof ObjectNode && !((ObjectNode) targetNode)
                            .isIsControlType())) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(controlFlow);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("2f0d8734-44a9-4251-8f1b-be2439f5def6")
        private List<IAuditEntry> checkR1180(ObjectNode objectNode) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            for (ActivityEdge edge : objectNode.getIncoming()) {
                if (edge instanceof ControlFlow) {
                    auditEntries.add(checkR1180((ControlFlow) edge));
                }
            }
            
            for (ActivityEdge edge : objectNode.getOutgoing()) {
                if (edge instanceof ControlFlow) {
                    auditEntries.add(checkR1180((ControlFlow) edge));
                }
            }
            return auditEntries;
        }

    }

}
