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
 * Rule implementation origin: EventChecker checkChangeEvent
 */
@objid ("33a89ae5-329c-499f-83fc-d50f7c57100c")
public class R1690 extends AbstractUmlRule {
    @objid ("a36b869f-ed34-482d-b662-0290b5227323")
    private static final String RULEID = "R1690";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("1b4fc0db-9e52-4c92-af72-05faa099a409")
    private CheckR1690 checkerInstance = null;

    @objid ("ce7810dd-1710-455b-88c9-62459bcedc1e")
    @Override
    public String getRuleId() {
        return R1690.RULEID;
    }

    @objid ("d371b7c1-89ae-4ab2-b3f0-aeae9277395b")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Event.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0b5c4b88-65cf-4180-803a-b1f7d4266ba0")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7a73d56b-6b69-4ac1-95b6-44236bf54a63")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a28c8088-89de-4cb4-b39b-f46a4d1b830b")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1690
     */
    @objid ("66c1a35a-ea35-4e3a-a51a-ba017da92399")
    public  R1690() {
        this.checkerInstance = new CheckR1690(this);
    }

    @objid ("8427a2d1-14a7-4f6b-8734-5114beafb2d7")
    private static class CheckR1690 extends AbstractControl {
        @objid ("238e57c0-4e2e-491a-8699-389c14d192ff")
        public  CheckR1690(IRule rule) {
            super(rule);
        }

        @objid ("5c4878e0-e2d3-427d-96a3-d6b01f48f967")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Event) {
                diagnostic.addEntry(checkR1690((Event) element));
            } else {
                UmlUi.LOG.warning("R1690: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("593c8056-574e-42fa-a255-7a64713eac01")
        private IAuditEntry checkR1690(final Event event) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    event,
                    null);
            
            if (event.getKind().equals(EventType.CHANGEEVENT)) {
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
