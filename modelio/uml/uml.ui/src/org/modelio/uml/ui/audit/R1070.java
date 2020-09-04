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
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Checks that an ActivityParameterNode with incoming edges represents a BehaviourParameter that has either 'out' or 'inout' as passing mode or is a return parameter.
 * 
 * @author fpoyer
 */
@objid ("9972e128-9bfa-40d0-a692-5d11e71d1adb")
public class R1070 extends AbstractUmlRule {
    @objid ("340b4ddf-81d2-41ff-9b72-4b2e3915aadb")
    private static final String RULEID = "R1070";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("0e820537-5ab5-4a8b-acd5-3db891e51b8d")
    private CheckR1070 checkerInstance = null;

    @objid ("96d53ed2-2128-4e38-8991-833ac07dbc97")
    @Override
    public String getRuleId() {
        return R1070.RULEID;
    }

    @objid ("363d81c0-9eb1-41c2-add6-a0a6135ae63f")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // Needed for:
        // - creation of the activity parameter node
        // - activity parameter node receives/loses incoming edges (Incoming dependency)
        // - the represented behaviour parameter changes (RepresentedRealParameter dependency).
        plan.registerRule(ActivityParameterNode.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        // Needed for:
        // - the behaviour parameter's passing mode changes (ParameterPassing attribute or Returned dependency)
        plan.registerRule(BehaviorParameter.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(ControlFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("39c0385b-64ae-45f6-af31-2f7638eb6589")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7ca4f345-57a1-44f2-b2f8-8146c07e5f73")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("71064b44-9c6e-43cb-9bd6-f8c3391189c7")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1070
     */
    @objid ("9f8e16ba-3fec-4caf-8b04-f16ce5a532e3")
    public R1070() {
        this.checkerInstance = new CheckR1070(this);
    }

    /**
     * Actual Checker for R1070: Checks that an ActivityParameterNode with incoming edges represents a BehaviourParameter that has either 'out' or 'inout' as passing mode or is a return parameter.
     */
    @objid ("e3933a56-a421-4e43-8e66-1049bdbc0aed")
    private static class CheckR1070 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("e694128b-654f-4d8d-9652-d5c50f87999f")
        public CheckR1070(IRule rule) {
            super(rule);
        }

        @objid ("7a97d20b-e74d-4156-bef4-4cf644c30a23")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityParameterNode) {
                diagnostic.addEntry(checkR1070((ActivityParameterNode) element));
            } else if (element instanceof BehaviorParameter) {
                for (ActivityParameterNode representingObjectNode : ((BehaviorParameter) element)
                        .getRepresentingObjectNode(ActivityParameterNode.class)) {
                    diagnostic.addEntry(checkR1070(representingObjectNode));
                }
            } else if (element instanceof ActivityEdge) {
                diagnostic.addEntries(checkR1070((ActivityEdge) element));
            } else {
                UmlUi.LOG.warning("R1070: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("9e699fc0-b573-47d9-bf45-08972f53c567")
        private IAuditEntry checkR1070(ActivityParameterNode node) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, node, null);
            
            // If the activity parameter node has incoming edges, then it must
            // represent a behaviour parameter which passing mode must be either
            // OUT or INOUT or must be a return parameter.
            if (!node.getIncoming().isEmpty()) {
                BehaviorParameter bParam = node.getRepresentedRealParameter();
                if (bParam == null
                        || !(bParam.getParameterPassing() == PassingMode.OUT
                                || bParam.getParameterPassing() == PassingMode.INOUT || bParam.getReturned() != null)) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(node);
                    linkedObjects.add(bParam);
                    for (ActivityEdge edge : node.getIncoming()) {
                        linkedObjects.add(edge);
                    }
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        @objid ("841f92cc-8ff9-4474-8536-ddb6eb05ef3f")
        private List<IAuditEntry> checkR1070(ActivityEdge edge) {
            List<IAuditEntry> auditentries = new ArrayList<>();
            
            ActivityNode source = edge.getSource();
            ActivityNode target = edge.getTarget();
            
            if (source instanceof ActivityParameterNode) {
                auditentries.add(checkR1070((ActivityParameterNode) source));
            }
            
            if (target instanceof ActivityParameterNode) {
                auditentries.add(checkR1070((ActivityParameterNode) target));
            }
            return auditentries;
        }

    }

}
