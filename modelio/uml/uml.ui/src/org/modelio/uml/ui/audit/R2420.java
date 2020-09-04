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
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkRedefinedOperationSignature
 */
@objid ("026a586f-c791-4649-9d9e-c47c3a857987")
public class R2420 extends AbstractUmlRule {
    @objid ("d4de7052-0de9-4c6a-b17f-559604312f57")
    private static final String RULEID = "R2420";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("47151c31-bcde-45f3-ba41-387fa6452c03")
    private CheckR2420 checkerInstance = null;

    @objid ("f1107b3f-60ae-48f5-bf19-39a9062cd2ac")
    @Override
    public String getRuleId() {
        return R2420.RULEID;
    }

    @objid ("39b5787b-abd9-4730-ab25-2d06f3b0c473")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Parameter.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("789985c4-fa5e-4fab-8002-a37115a2e494")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("45d7051b-8fea-4574-bf04-086863fef54d")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0b43ad63-108b-41b3-b760-1a4d85b34cf1")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2420
     */
    @objid ("1bbd5897-8fa1-4c1a-8fb2-7b5408b2ac4e")
    public R2420() {
        this.checkerInstance = new CheckR2420(this);
    }

    @objid ("e7ccb52f-7f25-419c-8e12-92ac168b290e")
    private static class CheckR2420 extends AbstractControl {
        @objid ("33dce41a-dd24-4b31-aa8e-90ba93907b13")
        public CheckR2420(IRule rule) {
            super(rule);
        }

        @objid ("0e366d68-910f-4dbe-ae8f-2f80c9126ba2")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof TemplateParameter) {
                TemplateParameter p = (TemplateParameter) element;
                Operation operation = p.getParameterizedOperation();
                if (operation != null) {
                    // We need to check the operation, but also any operation that redefines it
                    diagnostic.addEntry(checkR2420(operation));
                    for (Operation redefinition : operation.getRedefinition()) {
                        diagnostic.addEntry(checkR2420(redefinition));
                    }
                }
            } else if (element instanceof Parameter) {
                Parameter parameter = (Parameter) element;
                Operation operation = parameter.getComposed();
                if (operation == null) {
                    operation = parameter.getReturned();
                }
                if (operation != null) {
                    // We need to check the operation, but also any operation that redefines it
                    diagnostic.addEntry(checkR2420(operation));
                    for (Operation redefinition : operation.getRedefinition()) {
                        diagnostic.addEntry(checkR2420(redefinition));
                    }
                }
            } else if (element instanceof Operation) {
                // An Operation was modified
                // We need to check it, but also any operation that redefines it
                Operation operation = (Operation) element;
                diagnostic.addEntry(checkR2420(operation));
                for (Operation redefinition : operation.getRedefinition()) {
                    diagnostic.addEntry(checkR2420(redefinition));
                }
            } else {
                UmlUi.LOG.warning("R2420: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("166bb6b3-4328-44e7-8479-6e9523dce762")
        private IAuditEntry checkR2420(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    operation,
                    null);
            
            Operation redfined = operation.getRedefines();
            
            if (redfined != null && !makeSignature(operation).equals(makeSignature(redfined))) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(operation);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("76dd6d94-1f7d-486c-a1fa-358ef4163a5a")
        private String makeSignature(final Operation op) {
            StringBuilder signature = new StringBuilder();
            
            signature.append(op.getName());
            signature.append(op.getPassing());
            // Parameters
            for (Parameter p : op.getIO()) {
                signature.append(makeSignature(p));
            }
            // template parameters contribute to the signature too
            for (TemplateParameter tp : op.getTemplate()) {
                signature.append(makeSignature(tp));
            }
            return signature.toString();
        }

        @objid ("51d2ed62-a9ae-4e81-90d5-3d4f9baf1015")
        private String makeSignature(final TemplateParameter tp) {
            if (tp.isIsValueParameter()) {
                return (tp.getType() != null) ? tp.getType().getUuid().toString() : "undefined";
            } else {
                return tp.getMClass().getName();
            }
        }

        @objid ("ef45f4a6-2cb2-4473-832f-d8e2ceef93e0")
        private String makeSignature(final Parameter p) {
            StringBuilder buffer = new StringBuilder();
            
            // type name
            if (p.getType() != null) {
                buffer.append(p.getType().getUuid().toString());
            } else {
                buffer.append("undefined");
            }
            // passing mode
            buffer.append(p.getParameterPassing());
            
            // cardinality
            buffer.append(p.getMultiplicityMin());
            buffer.append(p.getMultiplicityMax());
            return buffer.toString();
        }

    }

}
