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
import java.util.Map;
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
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkClassifierInheritedOperations warning
 */
@objid ("9f15be9f-ad24-4daa-9f7f-5218d23c53f7")
public class R2250 extends AbstractUmlRule {
    @objid ("feef8115-0a56-4741-a1b7-67fd30fd43f5")
    private static final String RULEID = "R2250";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("5b230999-0e11-49a8-a70e-6baacfe9fcdd")
    private CheckR2250 checkerInstance = null;

    @objid ("dfa1a118-4261-4a6f-a8c6-f862723622e3")
    @Override
    public String getRuleId() {
        return R2250.RULEID;
    }

    @objid ("032c64de-7924-4226-accd-33b1b76293d5")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE
                | AuditTrigger.MOVE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE
                | AuditTrigger.MOVE);
        plan.registerRule(Parameter.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE
                | AuditTrigger.MOVE);
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE
                | AuditTrigger.MOVE);
        plan.registerRule(InterfaceRealization.MQNAME, this, AuditTrigger.CREATE
                | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        
        // Classifier
        
        // Classifier.GeneralClass
        plan.registerRule(Interface.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Class.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.UPDATE);
        
        // Classifier.GeneralClass.Class
        plan.registerRule(Component.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d755261c-e9f4-426e-b001-368109ab5513")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("bbf1a85c-dc0f-43ce-9acd-c94085719a8e")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("559ff2d8-08f0-4ad5-a6aa-d5fe0f2b6fd1")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2250
     */
    @objid ("088abd77-b193-4604-9793-82b2149dd31e")
    public R2250() {
        this.checkerInstance = new CheckR2250(this);
    }

    @objid ("283534fa-b35c-4e38-9fd8-af1794ed02c5")
    private static class CheckR2250 extends AbstractControl {
        @objid ("aecc4cee-35df-45ff-8d20-51564c5f2bb3")
        public CheckR2250(IRule rule) {
            super(rule);
        }

        @objid ("75ccd5f5-023e-46d9-a6bd-426d6ea39045")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof TemplateParameter) {
                TemplateParameter p = (TemplateParameter) element;
                if (p.getParameterizedOperation() != null) {
                    diagnostic.addEntries(checkForRightClassifier(p.getParameterizedOperation().getOwner()));
                }
            } else if (element instanceof Parameter) {
                Parameter parameter = (Parameter) element;
                Operation operation = parameter.getComposed();
                if (operation == null) {
                    operation = parameter.getReturned();
                }
                if (operation != null) {
                    diagnostic.addEntries(checkForRightClassifier(operation.getOwner()));
                }
            } else if (element instanceof Operation) {
                final Operation op = (Operation) element;
                final Classifier owner = op.getOwner();
                if (owner != null) {
                    diagnostic.addEntries(checkForRightClassifier(owner));
                } else {
                    UmlUi.LOG.warning("R2250: %s(...) {%s} operation has no owner%s.", op.getName(), op.getUuid()
                            .toString(), op.isValid() ? "" : " and is not valid(deleted)");
                }
            } else if (element instanceof Interface) {
                diagnostic.addEntries(checkAllInterfaces((Interface) element, new ArrayList<Interface>()));
            } else if (element instanceof Classifier) {
                diagnostic.addEntries(checkForRightClassifier((Classifier) element));
            } else if (element instanceof Generalization) {
                NameSpace ns = ((Generalization) element).getSubType();
                if (ns instanceof Classifier) {
                    diagnostic.addEntries(checkAllClassifier((Classifier) ns, new ArrayList<Classifier>()));
                }
            } else if (element instanceof InterfaceRealization) {
                NameSpace ns = ((InterfaceRealization) element).getImplementer();
                if (ns instanceof Classifier) {
                    // In case of an .InterfaceRealization, the implementer can
                    // either be an .Interface or a Classifier
                    diagnostic.addEntries(checkForRightClassifier((Classifier) ns));
                }
            } else {
                UmlUi.LOG.warning("R2250: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("449ce037-2eb7-45b1-b620-8efc0180506c")
        private IAuditEntry checkR2250(final Classifier classifier) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, classifier, null);
            
            // Should never happen...
            if (classifier == null || !classifier.isValid()) {
                return auditEntry;
            }
            
            Map<String, Operation> accessibleOpSignatures = new HashMap<>();
            fetchAllOpSignatures(classifier, accessibleOpSignatures, new ArrayList<Classifier>());
            
            for (Operation op : classifier.getOwnedOperation()) {
                // The Operation is a constructor
                if (op.isStereotyped("ModelerModule", "create")) {
                    continue;
                }
            
                // The Operation is a destructor
                if (op.isStereotyped("ModelerModule", "destroy")) {
                    continue;
                }
            
                // The Operation Overrides another Operation
                if (op.getRedefines() != null) {
                    continue;
                }
            
                String opSignature = makeSignature(op);
            
                if (accessibleOpSignatures.containsKey(opSignature)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(classifier);
                    linkedObjects.add(op);
                    Operation maskedOp = accessibleOpSignatures.get(opSignature);
                    linkedObjects.add(maskedOp);
                    linkedObjects.add(maskedOp.getCompositionOwner());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        /**
         * This methods checks if the given classifier is an .Interface, in which case the appropriate method is called.
         * @param classifier The Classifier.
         * @return A list of AuditEntries.
         */
        @objid ("1760b80c-555b-493c-9788-4fc14f30590c")
        private List<IAuditEntry> checkForRightClassifier(final Classifier classifier) {
            if (classifier instanceof Interface) {
                return checkAllInterfaces((Interface) classifier, new ArrayList<Interface>());
            } else {
                return checkAllClassifier(classifier, new ArrayList<Classifier>());
            }
        }

        /**
         * This method recursively navigates the .Interface realizations until it find Classifiers and then call the appropriate method that check Classifiers.
         * @param interfaze The .Interface to check.
         * @return A list of AuditEntries.
         */
        @objid ("bb8c2003-3b32-4a8f-b39d-f4224ee55c2d")
        private List<IAuditEntry> checkAllInterfaces(final Interface interfaze, final List<Interface> interfaces) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            if (interfaces.contains(interfaze)) {
                return auditEntries;
            } else {
                interfaces.add(interfaze);
            }
            
            for (Generalization gen : interfaze.getSpecialization()) {
                NameSpace ns = gen.getSubType();
                if (ns instanceof Interface) {
                    auditEntries.addAll(checkAllInterfaces((Interface) ns, interfaces));
                }
            }
            
            for (InterfaceRealization ir : interfaze.getImplementedLink()) {
                NameSpace ns = ir.getImplementer();
                if (ns instanceof Classifier) {
                    auditEntries.addAll(checkAllClassifier((Classifier) ns, new ArrayList<Classifier>()));
                }
            }
            return auditEntries;
        }

        /**
         * This method recursively checks the given Classifier as well as all Classifier that inherits from it.
         * @param classifier The given Classifier.
         * @return A list of AuditEntries.
         */
        @objid ("303b84a8-aa9a-4d29-bf61-06de8c1d689a")
        private List<IAuditEntry> checkAllClassifier(final Classifier classifier, final List<Classifier> classifiers) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            if (classifiers.contains(classifier)) {
                return auditEntries;
            } else {
                classifiers.add(classifier);
            }
            
            // Checking the Classifier itself
            auditEntries.add(checkR2250(classifier));
            
            // Checking all Classifier that inherit from this Classifier
            for (Generalization gen : classifier.getSpecialization()) {
                NameSpace ns = gen.getSubType();
                if (ns instanceof Classifier) {
                    auditEntries.addAll(checkAllClassifier((Classifier) ns, classifiers));
                }
            }
            return auditEntries;
        }

        @objid ("2064e8d4-68c5-4dc9-a3bf-c1737dba27b5")
        private void fetchAllOpSignatures(final Classifier classifier, final Map<String, Operation> accessibleOpSignatures, final List<Classifier> classifiers) {
            if (classifiers.contains(classifier)) {
                return;
            } else {
                classifiers.add(classifier);
            }
            
            // Adds all operations from .Interface realization
            for (InterfaceRealization ir : classifier.getRealized()) {
                Interface interfaze = ir.getImplemented();
                for (Operation op : interfaze.getOwnedOperation()) {
                    if (!op.isStereotyped("ModelerModule", "create")
                            && !op.isStereotyped("ModelerModule", "destroy")
                            && (op.getVisibility().equals(VisibilityMode.PUBLIC) || op.getVisibility().equals(
                                    VisibilityMode.PROTECTED))) {
                        accessibleOpSignatures.put(makeSignature(op), op);
                    }
                }
            
                fetchAllOpSignatures(interfaze, accessibleOpSignatures, classifiers);
            }
            // Adds all operations from super classifiers and run recursively on
            // these classifiers
            for (Generalization gen : classifier.getParent()) {
                NameSpace ns = gen.getSuperType();
                if (ns instanceof Classifier) {
                    for (Operation op : ((Classifier) ns).getOwnedOperation()) {
                        if (!op.isStereotyped("ModelerModule", "create")
                                && !op.isStereotyped("ModelerModule", "destroy")
                                && (op.getVisibility().equals(VisibilityMode.PUBLIC) || op.getVisibility().equals(
                                        VisibilityMode.PROTECTED))) {
                            accessibleOpSignatures.put(makeSignature(op), op);
                        }
                    }
                    fetchAllOpSignatures((Classifier) ns, accessibleOpSignatures, classifiers);
                }
            }
        }

        @objid ("4cee6843-8b3f-4353-9f80-f650904f8d80")
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

        @objid ("be2d49e2-321b-459c-b135-4a5a89ec44b6")
        private String makeSignature(final TemplateParameter tp) {
            if (tp.isIsValueParameter()) {
                return (tp.getType() != null) ? tp.getType().getUuid().toString() : "undefined";
            } else {
                return tp.getMClass().getName();
            }
        }

        @objid ("2ceb1f7e-448d-4271-b34c-94b52df24008")
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
