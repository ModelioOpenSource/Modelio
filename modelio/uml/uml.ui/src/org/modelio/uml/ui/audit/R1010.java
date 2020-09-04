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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61013 error
 */
@objid ("c6783794-100c-4ad4-9ab4-6bfc49fe626d")
public class R1010 extends AbstractUmlRule {
    @objid ("0c6344c5-43aa-43ea-8476-86432de9b22d")
    private static final String RULEID = "R1010";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("94eb52f6-f3c0-44a6-9214-341225c3f0a0")
    private CheckR1010 checkerInstance = null;

    @objid ("4fea3112-7de9-4af6-baff-048f8fe71a59")
    @Override
    public String getRuleId() {
        return R1010.RULEID;
    }

    @objid ("4ea341a7-6902-40e0-9d70-32b77f3f6506")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(ActivityPartition.MQNAME, this,
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e866fe7a-704f-43ba-b26c-65776810789a")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("673d9832-8f9b-43d9-9b95-4c952768b53d")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("db9b2b18-d685-46c9-afcb-e48334b92541")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1010
     */
    @objid ("e4cb0193-e104-4548-b0ff-e8964f656993")
    public R1010() {
        this.checkerInstance = new CheckR1010(this);
    }

    @objid ("fd398140-22e5-4c67-b829-d11bbeb35758")
    private static class CheckR1010 extends AbstractControl {
        @objid ("7f347b3f-8ca0-4c42-858a-3cd80c9712f0")
        public CheckR1010(IRule rule) {
            super(rule);
        }

        @objid ("aca2d9ff-77f0-4a77-9753-b9cc192202da")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ActivityPartition) {
                diagnostic.addEntry(checkR1010((ActivityPartition) element));
            } else {
                UmlUi.LOG.warning("R1010: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("9a3b473f-7c6f-4e46-9bdc-85623f3821f1")
        private IAuditEntry checkR1010(ActivityPartition partition) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, partition, null);
            
            if (partition.getInActivity() != null
                    && partition.getSuperPartition() != null) {
                // test failed
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(partition);
                linkedObjects.add(partition.getInActivity());
                linkedObjects.add(partition.getSuperPartition());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
