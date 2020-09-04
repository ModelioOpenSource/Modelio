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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Check multiplicity consistency on Instance.
 */
@objid ("a53a4f7f-41d4-4466-932f-378018f32636")
public class R1760 extends AbstractUmlRule {
    @objid ("2b2ed51a-966c-4636-8f10-d6d9fd1f28b0")
    private static final String RULEID = "R1760";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("d1364bd2-b149-4d64-ba94-9f787f840325")
    private CheckR1760 checkerInstance = null;

    @objid ("4a88cbfe-ee5e-4feb-8f80-81f21912d1f1")
    @Override
    public String getRuleId() {
        return R1760.RULEID;
    }

    @objid ("dc92830f-04e5-47e0-ae16-5e1c753675af")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Instance.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f4dacc6c-0fc6-4d40-817b-22738965121b")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3961b383-dbce-42e0-8374-9f0ff000fae2")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1760
     */
    @objid ("3376d430-9e27-4f36-9728-24c590d601fb")
    public R1760() {
        this.checkerInstance = new CheckR1760(this);
    }

    @objid ("55fc8a57-e673-42f1-87b3-7408fec6684e")
    private static class CheckR1760 extends AbstractControl {
        @objid ("d691ac74-8995-49d0-8cb3-6889905f412e")
        public CheckR1760(IRule rule) {
            super(rule);
        }

        @objid ("9edb810e-afcc-4d39-8efe-8e8fe5690287")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Instance) {
                diagnostic.addEntry(checkR1760((Instance) element));
            } else {
                UmlUi.LOG.warning("R1760: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("8933e449-3f09-485f-92b8-0ff1d97d70d3")
        private IAuditEntry checkR1760(final Instance instance) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    instance,
                    null);
            
            try {
                if ("*".equals(instance.getMultiplicityMin())) {
                    // min multiplicity cannot be "*"
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(instance);
                    linkedObjects.add(instance.getMultiplicityMin());
                    linkedObjects.add(instance.getMultiplicityMax());
                    auditEntry.setLinkedInfos(linkedObjects);
                } else {
                    int multiplicityMin = Integer.parseInt(instance.getMultiplicityMin());
                    int multiplicityMax = Integer.parseInt(instance.getMultiplicityMax());
                    if (multiplicityMin > multiplicityMax) {
                        // Error in multiplicities!
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(instance);
                        linkedObjects.add(instance.getMultiplicityMin());
                        linkedObjects.add(instance.getMultiplicityMax());
                        auditEntry.setLinkedInfos(linkedObjects);
                    }
                }
            } catch (@SuppressWarnings("unused") NumberFormatException e) {
                // Either multiplicity is not a number: cannot compare, so
                // consider success!
            }
            return auditEntry;
        }

    }

}
