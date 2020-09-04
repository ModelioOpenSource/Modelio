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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: EventChecker checkSignalEvent
 */
@objid ("67901b5d-1714-4586-abc1-79b313766390")
public class R1700 extends AbstractUmlRule {
    @objid ("2fe961b9-86da-43da-bf74-9452a8de6cde")
    private static final String RULEID = "R1700";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("10e3914f-a878-42fa-89ba-bcc333dd5bc7")
    private CheckR1700 checkerInstance = null;

    @objid ("3e86e4e6-5a1a-44c1-814d-dacd0c6b6760")
    @Override
    public String getRuleId() {
        return R1700.RULEID;
    }

    @objid ("725e88dd-93a4-4785-b6c7-c084b37f8971")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Event.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("970de514-c282-4dc1-be62-f2db0afb4c75")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ef27fd2b-724b-4b49-9aa3-620f6938f25a")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("4053adac-68d6-4cf4-898f-c0525f8393cd")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1700
     */
    @objid ("5cb1b9d4-0111-4a26-8473-ccf0b35cccdc")
    public R1700() {
        this.checkerInstance = new CheckR1700(this);
    }

    @objid ("ed57e57e-d12d-427d-a694-2b24544a3973")
    private static class CheckR1700 extends AbstractControl {
        @objid ("d0c8aee1-46f7-4ecf-8b02-415894536540")
        public CheckR1700(IRule rule) {
            super(rule);
        }

        @objid ("11173e64-83d8-401a-ae1d-3545f45c70af")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Event) {
                diagnostic.addEntry(checkR1700((Event) element));
            } else {
                UmlUi.LOG.warning("R1700: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("0fac5d3c-002a-40a0-8882-6c82c45f92a4")
        private IAuditEntry checkR1700(final Event event) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    event,
                    null);
            
            if (event.getKind().equals(EventType.SIGNALEVENT)) {
                if (event.getModel() == null || event.getCalled() != null || !event.getExpression().isEmpty()) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(event);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
