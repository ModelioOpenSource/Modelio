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
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: MessageChecker checkName
 */
@objid ("87e32199-32e7-4f9d-bc6c-ae3aab1594b6")
public class R1960 extends AbstractUmlRule {
    @objid ("fef2c620-7967-4e1b-bf27-8c65af18bf91")
    private static final String RULEID = "R1960";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("d099d566-183a-4d7b-82a4-9087f8d2f60e")
    private CheckR1960 checkerInstance = null;

    @objid ("2102056c-750a-4180-a985-8bcfaf389554")
    @Override
    public String getRuleId() {
        return R1960.RULEID;
    }

    @objid ("85fa7cad-756c-4b04-9d1a-7a0cb0bb5e96")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Message.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("94a94265-4575-4465-98d1-9db08e15daf1")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0b8a24a1-d461-49c5-b3b2-1e5189c9af9f")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6d2c4152-0626-42ce-9598-834d6ea127e1")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1960
     */
    @objid ("c102d72e-1b61-4a8b-aeab-5a1fc0b85270")
    public R1960() {
        this.checkerInstance = new CheckR1960(this);
    }

    @objid ("733a131a-026f-454a-ba75-0c19a5a519d4")
    private static class CheckR1960 extends AbstractControl {
        @objid ("cc1e6f82-8a4f-46b2-b1bc-2e67900ccc53")
        public CheckR1960(IRule rule) {
            super(rule);
        }

        @objid ("bebb4b6b-3f5a-4a9d-ad3f-00d2d3c60451")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Message) {
                diagnostic.addEntry(checkR1960((Message) element));
            } else if (element instanceof Operation) {
                for (Message msg : ((Operation) element).getUsage()) {
                    diagnostic.addEntry(checkR1960(msg));
                }
            } else {
                UmlUi.LOG.warning("R1960: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("29456588-9f17-4a15-864c-ee38652fb4ac")
        private IAuditEntry checkR1960(final Message message) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    message,
                    null);
            
            Operation operation = message.getInvoked();
            
            if (operation != null && !operation.getName().equals(message.getName())) {
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(message);
                linkedObjects.add(operation);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
