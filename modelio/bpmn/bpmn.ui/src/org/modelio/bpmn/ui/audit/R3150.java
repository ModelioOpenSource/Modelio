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
package org.modelio.bpmn.ui.audit;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.modelio.bpmn.ui.plugin.BpmnUi;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3150 Severity : error Description : A MessageFlow cannot link two elements in the same top-level lane.
 */
@objid ("7f718a24-ceed-438f-8db4-1744afb85473")
public class R3150 extends AbstractBpmnRule {
    @objid ("56c29428-e201-4cf9-9b76-e4675ca074e3")
    private static final String RULEID = "R3150";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("74ccb51c-e945-4d1d-aae4-86774516645a")
    private CheckR3150 checkerInstance = null;

    @objid ("4ebe4fd6-8ef0-4c1a-8e8c-512798927b53")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnMessageFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
    }

    @objid ("b2cd320e-b6d0-464f-87b9-8329b6ebdc07")
    @Override
    public String getRuleId() {
        return R3150.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1a7c49a5-84b1-4e8e-ac35-c2af133faccc")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b1889f23-ab5d-4cf8-9157-74416f9917a9")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d02cafa4-bceb-429f-8183-eef367425fba")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3150
     */
    @objid ("74df0014-35f2-47ac-9d4e-3c8dc27c5c62")
    public  R3150() {
        this.checkerInstance = new CheckR3150(this);
    }

    /**
     * Actual checker for R3150: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("a492b391-92e4-46db-ac53-fbf76c58ca2c")
    private static class CheckR3150 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("390d3a76-ca84-417f-ad59-bf4075ae4de8")
        public  CheckR3150(final IRule rule) {
            super(rule);
        }

        @objid ("c39c130b-09f5-4b47-b990-9c85b12b5217")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnMessageFlow) {
                diagnostic.addEntry(checkR3150((BpmnMessageFlow) element));
            } else {
                BpmnUi.LOG.warning("R3150: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f20865a7-e8ef-4cb2-86ee-9f20cd975e06")
        private IAuditEntry checkR3150(final BpmnMessageFlow messageFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    messageFlow,
                    null);
            
            final BpmnBaseElement sourceRef = messageFlow.getSourceRef();
            final BpmnBaseElement targetRef = messageFlow.getTargetRef();
            
            final List<BpmnLane> sourceLanes;
            if (sourceRef instanceof BpmnFlowElement) {
                sourceLanes = ((BpmnFlowElement) sourceRef).getLane();
            } else if (sourceRef instanceof BpmnLane) {
                sourceLanes = Arrays.asList((BpmnLane) sourceRef);
            } else {
                return auditEntry;
            }
            
            final List<BpmnLane> targetLanes;
            if (targetRef instanceof BpmnFlowElement) {
                targetLanes = ((BpmnFlowElement) targetRef).getLane();
            } else if (targetRef instanceof BpmnLane) {
                targetLanes = Arrays.asList((BpmnLane) targetRef);
            } else {
                return auditEntry;
            }
            
            if (!sourceLanes.isEmpty() && !targetLanes.isEmpty()) {
                BpmnLane topSourceLane = null;
                BpmnLane topTargetLane = null;
            
                topSourceLane = getTopLane(sourceLanes.get(0));
                topTargetLane = getTopLane(targetLanes.get(0));
            
                if (topSourceLane.equals(topTargetLane)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(messageFlow);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        /**
         * Return the top-lane, starting from a lane.
         * @param lane The current lane.
         * @return The top-lane.
         */
        @objid ("1e6bf025-f132-4809-b34f-de2f1729fb9f")
        private BpmnLane getTopLane(final BpmnLane lane) {
            BpmnLane parentLane = lane.getLaneSet().getParentLane();
            
            if (parentLane != null) {
                return getTopLane(parentLane);
            } else {
                return lane;
            }
            
        }

    }

}
