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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61020 error
 */
@objid ("9e30b2f3-981e-4822-90ed-a586dcddb8ae")
public class R1040 extends AbstractUmlRule {
    @objid ("20651f20-c1da-4ba3-8e08-dba8f5f4624c")
    private static final String RULEID = "R1040";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("c6713664-c454-4c10-8ddb-5ec892a48a5e")
    private CheckR1040 checkerInstance = null;

    @objid ("0f888528-54fc-4ba1-b263-14a57339ef21")
    @Override
    public String getRuleId() {
        return R1040.RULEID;
    }

    @objid ("27ab665b-bc6d-42ff-b2d2-77594a8d9662")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(ActivityParameterNode.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(BehaviorParameter.MQNAME, this, AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("eca99a24-e80d-4c67-85d6-58c18c38d4c6")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a003c3b9-87a6-4ba1-977f-4f04a5d43f85")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a123238d-88d0-4dc4-b071-9f8ef536a7ce")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1040
     */
    @objid ("097d783d-ad2c-4968-9dd4-9513d1450538")
    public R1040() {
        this.checkerInstance = new CheckR1040(this);
    }

    @objid ("22bb55ff-52d9-47a7-b1a2-d3b8f59a8c45")
    private static class CheckR1040 extends AbstractControl {
        @objid ("febfb9c2-99e5-4db8-bd4c-eab273893a5a")
        public CheckR1040(IRule rule) {
            super(rule);
        }

        @objid ("2deaec2f-2ce2-4fda-902b-66e601f2e399")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityParameterNode) {
                diagnostic.addEntry(checkR1040((ActivityParameterNode) element));
            } else if (element instanceof BehaviorParameter) {
                BehaviorParameter p = (BehaviorParameter) element;
                for (ActivityParameterNode node : p.getRepresentingObjectNode(ActivityParameterNode.class)) {
                    diagnostic.addEntry(checkR1040(node));
                }
            } else {
                UmlUi.LOG.warning("R1040: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("c4383940-4fc8-4f9b-9e1a-381d78be9f50")
        private IAuditEntry checkR1040(final ActivityParameterNode parameterNode) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    parameterNode,
                    null);
            
            Activity owner = parameterNode.getOwner();
            
            if (owner != null) {
                BehaviorParameter bp = parameterNode.getRepresentedRealParameter();
                if (bp == null || !owner.equals(bp.getOwner())) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(parameterNode);
                    if (bp != null) {
                        linkedObjects.add(bp);
                    }
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
