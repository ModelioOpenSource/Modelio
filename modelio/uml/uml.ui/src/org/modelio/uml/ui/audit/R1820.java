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
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: InteractionModelChecker checkR61102 warning
 */
@objid ("652c30b3-3163-4e5a-84e6-731bf15ed63e")
public class R1820 extends AbstractUmlRule {
    @objid ("9e08ccef-0a5b-427f-99c9-1427cb101adc")
    private static final String RULEID = "R1820";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("3fd0248a-308a-412f-a836-b5ad1d231767")
    private CheckR1820 checkerInstance = null;

    @objid ("de644cf6-cfc0-46fa-a2e8-7ef8b299e36e")
    @Override
    public String getRuleId() {
        return R1820.RULEID;
    }

    @objid ("a5ad83f0-487a-4ae1-bb5f-8973516c0c60")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Gate.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2522da44-4282-434a-a649-89f70c0f4005")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2f1dd571-dfe4-4bbd-89fe-aeec2e85bcbd")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("50bf9272-f00d-4105-8473-0997763e461b")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1820
     */
    @objid ("b153d2bd-5ba7-428d-bd5d-93b0f8e641f0")
    public  R1820() {
        this.checkerInstance = new CheckR1820(this);
    }

    @objid ("9fa8b2a6-3287-4de4-bd29-9aa4a1b8766d")
    private static class CheckR1820 extends AbstractControl {
        @objid ("a241948b-24e6-45a2-8576-922aff579410")
        public  CheckR1820(IRule rule) {
            super(rule);
        }

        @objid ("a8c29223-d01a-47ea-85f0-1ea5e26df68d")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Gate) {
                diagnostic.addEntry(checkR1820((Gate) element));
            } else {
                UmlUi.LOG.warning("R1820: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("eab24d3a-453b-43fe-9204-3ab2582df424")
        private IAuditEntry checkR1820(final Gate gate) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    gate,
                    null);
            
            if (gate.getCovered().size() != 0) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(gate);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
