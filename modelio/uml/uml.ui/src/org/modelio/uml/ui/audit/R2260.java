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
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkClassifierOperations error
 */
@objid ("4ecfab59-6c94-4756-9654-6e9a52f39e74")
public class R2260 extends AbstractUmlRule {
    @objid ("edc4dbff-441f-427f-9699-b68dc16582d6")
    private static final String RULEID = "R2260";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("7b7e0e6d-a122-47d3-978c-768fe07cbbaf")
    private CheckR2260 checkerInstance = null;

    @objid ("6788e381-47ce-42dd-b81a-d601da6d66b2")
    @Override
    public String getRuleId() {
        return R2260.RULEID;
    }

    @objid ("cb92b9af-7463-4c33-9d6a-a6f7f234a1b9")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // Classifier UPDATE
        plan.registerRule(Interface.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Class.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Component.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
        plan.registerRule(Parameter.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b3223553-6c5c-40ae-970e-608ae4bb000c")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("88552a8d-bf4b-45e1-9073-02a4a027e152")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8323f600-fd8c-49e6-a403-782f29d76dd8")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2260
     */
    @objid ("1aa1a4f8-bfaa-4bf1-9308-734e0dee3ba2")
    public R2260() {
        this.checkerInstance = new CheckR2260(this);
    }

    @objid ("7bb250ce-5149-4de6-9a0f-cc3941e62f47")
    private static class CheckR2260 extends AbstractControl {
        @objid ("996cae04-0bac-4b6f-a4d1-bc31f47bebb7")
        public CheckR2260(IRule rule) {
            super(rule);
        }

        @objid ("e73bfd33-da1d-4571-af60-50314b478bc8")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            // Beware of the inheritance ordering of the following code, for example TemplateParameter is a Classifier...
            if (element instanceof TemplateParameter) {
                TemplateParameter p = (TemplateParameter) element;
                if (p.getParameterizedOperation() != null) {
                    diagnostic.addEntry(checkR2260(p.getParameterizedOperation().getOwner()));
                }
            } else if (element instanceof Operation) {
                diagnostic.addEntry(checkR2260(((Operation) element).getOwner()));
            } else if (element instanceof Parameter) {
                Parameter parameter = (Parameter) element;
                Operation operation = parameter.getComposed();
                if (operation == null) {
                    operation = parameter.getReturned();
                }
                if (operation != null) {
                    diagnostic.addEntry(checkR2260(operation.getOwner()));
                }
            } else if (element instanceof Classifier) {
                diagnostic.addEntry(checkR2260((Classifier) element));
            } else {
                UmlUi.LOG.warning("R2260: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("08c7b16f-e292-45bf-8c56-f127a584d6b1")
        private IAuditEntry checkR2260(Classifier classifier) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    classifier,
                    null);
            
            // Should never happen...
            if (classifier == null || !classifier.isValid()) {
                return auditEntry;
            }
            
            HashMap<String, List<Operation>> duplicates = new HashMap<>();
            
            // analyze the class operations
            boolean failed = false;
            for (Operation op : classifier.getOwnedOperation(Operation.class)) {
                String signature = makeSignature(op);
                if (duplicates.containsKey(signature)) {
                    duplicates.get(signature).add(op);
                    failed = true;
                } else {
                    ArrayList<Operation> operations = new ArrayList<>();
                    operations.add(op);
                    duplicates.put(signature, operations);
                }
            }
            
            // produce the resulting audit entry
            if (failed) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(classifier.getMClass().getName());
                linkedObjects.add(classifier);
                linkedObjects.add("spare for duplicates number"); // index = 2
                int nDuplicates = 0;
                for (Entry<String, List<Operation>> entry : duplicates.entrySet()) {
                    if (entry.getValue().size() > 1) {
                        nDuplicates++;
                        for (Operation op : entry.getValue()) {
                            linkedObjects.add(op);
                        }
                    }
                }
                linkedObjects.set(2, Integer.valueOf(nDuplicates));
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("f11b4c63-5fa5-4549-b8b3-36edf14287e7")
        private String makeSignature(Operation op) {
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

        @objid ("3e8fee2f-5deb-431c-898d-ef80a0c3a192")
        private String makeSignature(TemplateParameter tp) {
            if (tp.isIsValueParameter()) {
                return (tp.getType() != null) ? tp.getType().getUuid().toString() : "undefined";
            } else {
                return tp.getMClass().getName();
            }
        }

        @objid ("d5b340c2-12f3-4427-99b6-d80afae7c74f")
        private String makeSignature(Parameter p) {
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
