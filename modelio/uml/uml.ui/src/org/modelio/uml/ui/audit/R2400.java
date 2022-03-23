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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkR141
 */
@objid ("98eb7a56-e79d-4152-ba10-96a18cf579c4")
public class R2400 extends AbstractUmlRule {
    @objid ("229bbc09-4b06-4796-a976-8d62d2c811fa")
    private static final String RULEID = "R2400";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("a5a8ddef-13e2-4415-8dac-e41effff4792")
    private CheckR2400 checkerInstance = null;

    @objid ("f21d412d-010f-4ae9-83f8-4e980432d32a")
    @Override
    public String getRuleId() {
        return R2400.RULEID;
    }

    @objid ("52759408-328f-460d-91af-6f1bb45fd97c")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // This cover the case of moving or deleting a Parameter, which triggers
        // an UPDATE on the old parent.
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
        
        // This cover the case of [creating|moving] a Parameter [under|to] a new
        // parent.
        // It also covers the case of renaming a Parameter.
        plan.registerRule(Parameter.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE
                | AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d01e9d94-9f1d-479e-b07b-c55f9b6c01dd")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("bade33ea-4d3b-4494-b6f4-3937f906b911")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3267922e-ca61-4029-8c4f-2ddee1438f44")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2400
     */
    @objid ("ee328220-a379-4af6-b68c-b4f19b8d94dc")
    public  R2400() {
        this.checkerInstance = new CheckR2400(this);
    }

    @objid ("d4197b8a-bf9f-4ef5-bd8c-260c669b598f")
    private static class CheckR2400 extends AbstractControl {
        @objid ("b69a5114-a97c-4c3b-ae9b-036e7650613c")
        public  CheckR2400(IRule rule) {
            super(rule);
        }

        @objid ("9cc4064f-0cf6-422b-85ab-a969d2bcf773")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                diagnostic.addEntry(checkR2400((Operation) element));
            } else if (element instanceof Parameter) {
                Parameter parameter = (Parameter) element;
                MObject owner = parameter.getCompositionOwner();
                if (owner instanceof Operation) {
                    diagnostic.addEntry(checkR2400((Operation) owner));
                }
            } else {
                UmlUi.LOG.warning("R2400: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("37eebab7-123d-43a8-9faf-b6fa3c462d43")
        private IAuditEntry checkR2400(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, operation, null);
            
            boolean failed = false;
            if (operation.isStereotyped("ModelerModule", "destroy")
                    && (operation.getReturn() != null || !operation.getIO().isEmpty())) {
                // Rule failed
                failed = true;
            }
            
            if (failed) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(operation);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
