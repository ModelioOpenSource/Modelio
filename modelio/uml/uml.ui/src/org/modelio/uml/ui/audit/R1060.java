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
 * Checks that an ActivityParameterNode with outgoing edges represents a BehaviourParameter that has either 'in' or 'inout' as passing mode.
 * 
 * @author fpoyer
 */
@objid ("5a8469eb-cecf-4531-aa40-b0383b3e65aa")
public class R1060 extends AbstractUmlRule {
    @objid ("160d5169-e34c-465a-bd80-e6b7d915a98e")
    private static final String RULEID = "R1060";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("5930a4e7-a2a4-4b21-8429-eddebfe41463")
    private CheckR1060 checkerInstance = null;

    @objid ("6244776a-2020-470f-9642-0de97251765b")
    @Override
    public String getRuleId() {
        return R1060.RULEID;
    }

    @objid ("fbe53b97-f1aa-4049-830f-40d8c6e6e091")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // Needed for:
        // - creation of the activity parameter node
        // - activity parameter node receives/loses outgoing edges (Outgoing
        // dependency)
        // - the represented behaviour parameter changes
        // (RepresentedRealParameter dependency).
        plan.registerRule(ActivityParameterNode.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        
        // Needed for:
        // - the behaviour parameter's passing mode changes (ParameterPassing
        // attribute)
        plan.registerRule(BehaviorParameter.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(ControlFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e907dddd-896b-47c5-ab44-6ecbadb8925d")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("15c6c45a-2250-40c0-9a75-b8c50dc213fe")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e0d946e9-8e39-4849-98c5-b7ef4ca4d69a")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1060
     */
    @objid ("fe8b16be-2126-40df-a181-d43885fec3f5")
    public R1060() {
        this.checkerInstance = new CheckR1060(this);
    }

    /**
     * Actual checker for R1060: Checks that an ActivityParameterNode with outgoing edges represents a BehaviourParameter that has either 'in' or 'inout' as passing mode.
     */
    @objid ("4a9a6ac4-80e0-496f-bd9b-2edd427f9301")
    private static class CheckR1060 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("8bf48a47-8e0e-4d78-831e-755f1f0b066e")
        public CheckR1060(IRule rule) {
            super(rule);
        }

        @objid ("00585dc2-fbeb-4285-b074-e3bb3966825f")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityParameterNode) {
                diagnostic.addEntry(checkR1060((ActivityParameterNode) element));
            } else if (element instanceof BehaviorParameter) {
                for (ActivityParameterNode representingObjectNode : ((BehaviorParameter) element)
                        .getRepresentingObjectNode(ActivityParameterNode.class)) {
                    diagnostic.addEntry(checkR1060(representingObjectNode));
                }
            } else if (element instanceof ActivityEdge) {
                diagnostic.addEntries(checkR1060((ActivityEdge) element));
            } else {
                UmlUi.LOG.warning("R1060: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("74cabc09-2932-4b46-be37-71c08191e8e6")
        private IAuditEntry checkR1060(ActivityParameterNode node) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, node, null);
            
            // If the activity parameter node has outgoing edges, then it must
            // represent a behaviour parameter which passing mode must be either
            // IN or INOUT.
            if (!node.getOutgoing().isEmpty()) {
                BehaviorParameter bParam = node.getRepresentedRealParameter();
                if (bParam == null
                        || !(bParam.getParameterPassing() == PassingMode.IN || bParam.getParameterPassing() == PassingMode.INOUT)) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(node);
                    linkedObjects.add(bParam);
                    for (ActivityEdge edge : node.getOutgoing()) {
                        linkedObjects.add(edge);
                    }
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        @objid ("f8ed8395-8fb3-4708-a27a-c3002e38d473")
        private List<IAuditEntry> checkR1060(ActivityEdge edge) {
            List<IAuditEntry> auditentries = new ArrayList<>();
            
            ActivityNode source = edge.getSource();
            ActivityNode target = edge.getTarget();
            
            if (source instanceof ActivityParameterNode) {
                auditentries.add(checkR1060((ActivityParameterNode) source));
            }
            
            if (target instanceof ActivityParameterNode) {
                auditentries.add(checkR1060((ActivityParameterNode) target));
            }
            return auditentries;
        }

    }

}
