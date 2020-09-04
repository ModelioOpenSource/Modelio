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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.EventType;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: EventChecker checkCallEvent
 */
@objid ("89f0af4b-2ad0-45ff-8aad-672e0c3b6de5")
public class R1680 extends AbstractUmlRule {
    @objid ("30c3d2ce-29a8-454c-81e9-ddb13112473c")
    private static final String RULEID = "R1680";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("6d77b7ae-43cf-4865-a31d-90224deb946d")
    private CheckR1680 checkerInstance = null;

    @objid ("7f8c4a4f-69c2-4407-b29d-6913e0a80617")
    @Override
    public String getRuleId() {
        return R1680.RULEID;
    }

    @objid ("ee10933b-0c50-499b-8f37-1cbbbd96626d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Event.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c6c64db3-443a-42b6-94e8-5197bd4c8c0c")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("59dccc77-1477-4252-873f-92e553d6f8ea")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("38de6466-ba67-4aea-9f1c-0bae7b5f23f1")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1680
     */
    @objid ("a50e6235-e171-4b15-9e72-1adcf6002e43")
    public R1680() {
        this.checkerInstance = new CheckR1680(this);
    }

    @objid ("8e276596-dfd9-41dc-9d41-44d5a211292e")
    private static class CheckR1680 extends AbstractControl {
        @objid ("dc3bd37a-0c03-490d-b912-dccd982f614f")
        public CheckR1680(IRule rule) {
            super(rule);
        }

        @objid ("befbeeb4-bfa7-4718-b5fe-918c93262e65")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Event) {
                diagnostic.addEntry(checkR1680((Event) element));
            } else {
                UmlUi.LOG.warning("R1680: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("0ed8b8ef-cad2-484b-b974-a66a0f436609")
        private IAuditEntry checkR1680(final Event event) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, event, null);
            
            if (event.getKind().equals(EventType.CALLEVENT)) {
                if (event.getCalled() == null || event.getModel() != null) {
            
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
