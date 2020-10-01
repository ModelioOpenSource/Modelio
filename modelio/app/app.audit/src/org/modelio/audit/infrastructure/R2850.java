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

package org.modelio.audit.infrastructure;

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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: UsageChecker checkSelfUsage
 */
@objid ("4a70bbba-0371-4361-9a7e-caec4656944a")
public class R2850 extends AbstractInfrastructureRule {
    @objid ("eeb0ad45-6579-404b-9ad5-3f797953938b")
    private static final String RULEID = "R2850";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("389231e6-2b09-4138-8c0b-0fc8f6d36710")
    private CheckR2850 checkerInstance = null;

    @objid ("8b830c6e-7647-41a9-9535-d6017b4ba2bc")
    @Override
    public String getRuleId() {
        return R2850.RULEID;
    }

    @objid ("68b600da-b9b8-4593-b515-ef7659e892ea")
    @Override
    public void autoRegister(InfrastructureAuditPlan plan) {
        plan.registerRule(Usage.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b4ea365a-0de0-4e72-af61-5ac5933149c9")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d8eead5e-af9c-4e7f-a549-fa455b5d0846")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f754a20c-37e4-49bd-9ea9-9763294055d0")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2850
     */
    @objid ("52accdba-74fb-4f62-8567-a4ccaa143a9d")
    public R2850() {
        this.checkerInstance = new CheckR2850(this);
    }

    @objid ("3057bdae-bf56-4f12-932d-e0438dd8ccbf")
    private static class CheckR2850 extends AbstractControl {
        @objid ("d87174c7-ebcf-4a33-9ee1-d63d9b19634f")
        public CheckR2850(IRule rule) {
            super(rule);
        }

        @objid ("be0c7826-fd7f-4c86-9c30-fdc4a102f885")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Usage) {
                diagnostic.addEntry(checkR2850((Usage) element));
            }
            return diagnostic;
        }

        @objid ("6472270d-9e25-4ba7-b930-2828ff706245")
        private IAuditEntry checkR2850(final Usage usage) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, usage, null);
            
            if (usage.getImpacted().equals(usage.getDependsOn())) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(usage.getImpacted());
                auditEntry.setLinkedInfos(linkedObjects);
            
            }
            return auditEntry;
        }

    }

}
