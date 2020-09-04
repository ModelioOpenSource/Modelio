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
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: AbstractionChecker checkAutoAbstraction
 */
@objid ("ef26ba9c-948b-4cdd-b336-6e3dd8764a79")
public class R1000 extends AbstractUmlRule {
    @objid ("a12f136d-dd8d-4beb-94ae-ccb57780a137")
    private static final String RULEID = "R1000";

    @objid ("2166d5b6-0c17-43f5-b27c-4f737223f9e7")
    private CheckR1000 checkerInstance;

    @objid ("804dc031-35eb-444e-a82d-c015bcd009f1")
    @Override
    public String getRuleId() {
        return R1000.RULEID;
    }

    @objid ("b2d1d6f8-1d2b-4222-8b8e-e245424fb741")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Abstraction.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a singleton for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5285a9eb-0a57-4eb0-9786-024e8319e255")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a singleton for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fab171e5-cbfd-48b0-bdec-8f4672bb3a38")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a singleton for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a4974d0a-80f2-42f1-a94f-99892c85425d")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1000
     */
    @objid ("b2a4c220-ce29-4bbd-9096-d295bf2e08f8")
    public R1000() {
        this.checkerInstance = new CheckR1000(this);
    }

    @objid ("39df62b7-a841-4e18-ac68-91285310a860")
    private static class CheckR1000 extends AbstractControl {
        @objid ("75e4a1fb-724c-498b-88a1-00d8ab7fcc4f")
        public CheckR1000(IRule rule) {
            super(rule);
        }

        @objid ("ef72d862-8bde-4ab2-b6ad-52e2703f9bf5")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Abstraction) {
                diagnostic.addEntry(checkR1000((Abstraction) element));
            } else {
                UmlUi.LOG.warning("R1000: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("4fa895e8-4434-4a91-9d66-8f6193c0d68f")
        private IAuditEntry checkR1000(Abstraction abstraction) {
            AuditEntry auditEntry = new AuditEntry(R1000.RULEID, AuditSeverity.AuditSuccess, abstraction, null);
            // Check that there is no communication link on a class
            ModelElement dependsOn = abstraction.getDependsOn();
            ModelElement impacted = abstraction.getImpacted();
            if (dependsOn != null && dependsOn.equals(impacted)) {
                // test failed
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(abstraction);
                linkedObjects.add(abstraction.getMClass().getName());
                linkedObjects.add(dependsOn);
                linkedObjects.add(dependsOn.getMClass().getName());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
