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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: EventChecker checkTimeEvent
 */
@objid ("06967331-8d47-4517-b276-50ef7c371587")
public class R1710 extends AbstractUmlRule {
    @objid ("5e074eb8-9cce-4133-a72f-78442317a081")
    private static final String RULEID = "R1710";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f660f0c3-bff1-4c58-a0a3-b7c52ad41772")
    private CheckR1710 checkerInstance = null;

    @objid ("3f47741a-a8e8-4fbb-a6ee-f34e1856eb9a")
    @Override
    public String getRuleId() {
        return R1710.RULEID;
    }

    @objid ("e00ac490-e8cb-4660-adc9-cac1ebbbb59b")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Event.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ff764656-2cd5-40d1-925b-3377c2f7901d")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5cf5e34c-8471-4249-a6b7-3b115eab3c81")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("873488f3-c0f1-4919-a2c4-bae16d5fde24")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1710
     */
    @objid ("c2355304-f7d4-4780-8065-8d7c5804dfa8")
    public  R1710() {
        this.checkerInstance = new CheckR1710(this);
    }

    @objid ("287b3a5a-3d5b-496f-b474-173f09ae5c2a")
    private static class CheckR1710 extends AbstractControl {
        @objid ("7ba6d84b-3b8d-45a9-b483-251b6a9ec0ab")
        public  CheckR1710(IRule rule) {
            super(rule);
        }

        @objid ("65ac6445-fc7e-442c-8d34-d7a7153fff08")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Event) {
                diagnostic.addEntry(checkR1710((Event) element));
            } else {
                UmlUi.LOG.warning("R1710: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("aa1ccd23-ef44-4106-93d0-7a3f677733ce")
        private IAuditEntry checkR1710(final Event event) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    event,
                    null);
            
            if (event.getKind().equals(EventType.TIMEEVENT)) {
                if (event.getExpression().isEmpty() || event.getModel() != null || event.getCalled() != null) {
            
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
