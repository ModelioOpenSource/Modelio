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
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkR133
 */
@objid ("1a90fb55-2d61-4712-8df4-88df2b4d2127")
public class R2340 extends AbstractUmlRule {
    @objid ("7db5eaad-3fb0-4b2f-9051-8f00d85ae07d")
    private static final String RULEID = "R2340";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("0f0f9749-9e31-425b-af97-03edb2b017be")
    private CheckR2340 checkerInstance = null;

    @objid ("feff7a20-efa3-43ca-899e-6d3409023b0f")
    @Override
    public String getRuleId() {
        return R2340.RULEID;
    }

    @objid ("5fb7abce-4f86-4f0f-9072-a905a512c973")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE | AuditTrigger.MOVE);
        
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
        plan.registerRule(InterfaceRealization.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
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
    @objid ("569d6dea-5a6e-4af2-8e0e-fde78182a871")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("533fe1a8-3724-451c-ba7c-6117c6d63fd9")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a776be7e-546a-48dd-9703-f2be848af430")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2340
     */
    @objid ("b42d8b78-7f25-4ef0-bc23-bd72582214d1")
    public R2340() {
        this.checkerInstance = new CheckR2340(this);
    }

    @objid ("8e9de58b-c4fc-4c06-8e5d-3038d42d2272")
    private static class CheckR2340 extends AbstractControl {
        @objid ("f6c02bd4-3295-464a-9b03-e7b98d52c9f2")
        public CheckR2340(IRule rule) {
            super(rule);
        }

        @objid ("893ac3d5-c43b-40af-ae38-d35039cba555")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                diagnostic.addEntry(checkR2340((Operation) element));
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
                    // In case of an .InterfaceRealization, the implementer can either be an .Interface or a Classifier
                    diagnostic.addEntries(checkForRightClassifier((Classifier) ns));
                }
            } else {
                UmlUi.LOG.warning("R2340: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("64382f9c-fedc-4ae8-9c6b-cb8313acb4a6")
        private IAuditEntry checkR2340(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    operation,
                    null);
            
            Operation redefined = operation.getRedefines();
            
            if (redefined != null) {
            
                Classifier owner = operation.getOwner();
                Classifier redefinedOwner = redefined.getOwner();
            
                if (owner != null && redefinedOwner != null) {
            
                    List<Classifier> accessibleClassifiers = new ArrayList<>();
                    fetchAllOperations(owner, accessibleClassifiers);
            
                    if (!accessibleClassifiers.contains(redefinedOwner)) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(operation);
                        linkedObjects.add(redefined);
                        auditEntry.setLinkedInfos(linkedObjects);
                    }
                }
            }
            return auditEntry;
        }

        /**
         * This methods checks if the given classifier is an .Interface, in which case the appropriate method is called.
         * 
         * @param classifier The Classifier.
         * @return A list of AuditEntries.
         */
        @objid ("8d95f979-fdeb-43ab-a2e2-0483ec711119")
        private List<IAuditEntry> checkForRightClassifier(final Classifier classifier) {
            if (classifier instanceof Interface) {
                return checkAllInterfaces((Interface) classifier, new ArrayList<Interface>());
            } else {
                return checkAllClassifier(classifier, new ArrayList<Classifier>());
            }
        }

        /**
         * This method recursively navigates the .Interface realizations until it find Classifiers and then call the appropriate method that check Classifiers.
         * 
         * @param interfaze The .Interface to check.
         * @return A list of AuditEntries.
         */
        @objid ("eac59064-7a51-40c0-ae33-c213ce233663")
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
         * 
         * @param classifier The given Classifier.
         * @return A list of AuditEntries.
         */
        @objid ("558579a9-6454-49db-80fb-3a75fbabfc68")
        private List<IAuditEntry> checkAllClassifier(final Classifier classifier, final List<Classifier> classifiers) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            if (classifiers.contains(classifier)) {
                return auditEntries;
            } else {
                classifiers.add(classifier);
            }
            
            // Checking the Classifier itself
            auditEntries.addAll(checkR2340(classifier));
            
            // Checking all Classifier that inherit from this Classifier
            for (Generalization gen : classifier.getSpecialization()) {
                NameSpace ns = gen.getSubType();
                if (ns instanceof Classifier) {
                    auditEntries.addAll(checkAllClassifier((Classifier) ns, classifiers));
                }
            }
            return auditEntries;
        }

        @objid ("41f07b11-be75-4006-aa59-962e400c692a")
        private List<IAuditEntry> checkR2340(final Classifier classifier) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            for (Operation operation : classifier.getOwnedOperation()) {
                auditEntries.add(checkR2340(operation));
            }
            return auditEntries;
        }

        @objid ("d12c9919-1016-43d9-be16-289316165702")
        private void fetchAllOperations(final Classifier classifier, final List<Classifier> accessibleClassifiers) {
            // Adds all .Interfaces and run recursively on thse .Interfaces
            for (InterfaceRealization ir : classifier.getRealized()) {
                Interface interfaze = ir.getImplemented();
                accessibleClassifiers.add(interfaze);
                fetchAllOperations(interfaze, accessibleClassifiers);
            }
            // Adds all super classifiers and run recursively on these classifiers
            for (Generalization gen : classifier.getParent()) {
                NameSpace ns = gen.getSuperType();
                if (ns instanceof Classifier) {
                    Classifier clazzifier = (Classifier) ns;
                    accessibleClassifiers.add(clazzifier);
                    fetchAllOperations(clazzifier, accessibleClassifiers);
                }
            }
        }

    }

}
