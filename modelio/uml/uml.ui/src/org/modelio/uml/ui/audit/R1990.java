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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameChecker checkClassifierInheritedFeatures warning
 */
@objid ("a2ac863c-936b-45fe-a87b-e052d4e4de1c")
public class R1990 extends AbstractUmlRule {
    @objid ("d54ea8ef-e19b-40e3-9120-0ebd34b4c65e")
    private static final String RULEID = "R1990";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("62eddba8-d2a9-48bc-990c-308189bb90ca")
    private CheckR1990 checkerInstance = null;

    @objid ("7bbc338f-a98a-4e20-9802-208b6335ce5b")
    @Override
    public String getRuleId() {
        return R1990.RULEID;
    }

    @objid ("09743732-8802-4818-a813-27ad22289921")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // IClassifier
        // In case of a delete or move event, which call an update on the old parent.
        plan.registerRule(InformationItem.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Artifact.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Actor.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(org.modelio.metamodel.uml.statik.Class.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(DataType.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Interface.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(UseCase.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Node.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Component.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(Attribute.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("840f25f7-2945-4423-ab27-d41620e35182")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3bbd2866-bdfd-428e-88c2-b227b7827fdb")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1c3692c3-809b-45ab-b14f-686af55aee5e")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1990
     */
    @objid ("a08bc7ed-fcc5-4dc3-a0d9-973b4bc0ddd9")
    public  R1990() {
        this.checkerInstance = new CheckR1990(this);
    }

    @objid ("ab1cf316-bd28-4df1-8e59-2dead0e29ce1")
    private static class CheckR1990 extends AbstractControl {
        @objid ("6f8edc9d-b25a-4b61-87de-c228090ab607")
        public  CheckR1990(IRule rule) {
            super(rule);
        }

        @objid ("7afef1bb-9997-4335-90bf-8e1449c3d5c4")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Classifier) {
                diagnostic.addEntry(checkR1990((Classifier) element));
            }
            if (element instanceof Generalization) {
                diagnostic.addEntries(checkDown((Generalization) element, new ArrayList<Generalization>()));
            }
            if (element instanceof Attribute) {
                Classifier classifier = ((Attribute) element).getOwner();
                if (classifier != null) {
                    diagnostic.addEntry(checkR1990(classifier));
                    for (Generalization gen : classifier.getSpecialization()) {
                        diagnostic.addEntries(checkDown(gen, new ArrayList<Generalization>()));
                    }
                }
            }
            if (element instanceof AssociationEnd) {
                AssociationEnd end = (AssociationEnd) element;
            
                Classifier classifier = end.getSource() != null ? end.getSource() : end.getOpposite().getTarget();
                if (classifier != null) {
                    diagnostic.addEntry(checkR1990(classifier));
                    for (Generalization gen : classifier.getSpecialization()) {
                        diagnostic.addEntries(checkDown(gen, new ArrayList<Generalization>()));
                    }
                }
            }
            return diagnostic;
        }

        @objid ("67b0319f-972f-4892-b245-7de212365a5d")
        private IAuditEntry checkR1990(final Classifier classifier) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    classifier,
                    null);
            
            List<String> foundNames = new ArrayList<>();
            for (Generalization gen : classifier.getParent()) {
                NameSpace ns = gen.getSuperType();
                if (ns instanceof Classifier) {
                    fetchNames((Classifier) ns, foundNames, new ArrayList<Classifier>());
                }
            }
            
            for (Attribute attribute : classifier.getOwnedAttribute(Attribute.class)) {
                String attName = attribute.getName();
                if (foundNames.contains(attName)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(classifier);
                    linkedObjects.add(attName);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                } else if (!attName.isEmpty()) {
                    foundNames.add(attName);
                }
            }
            
            for (AssociationEnd assocEnd : classifier.getOwnedEnd(AssociationEnd.class)) {
                if (assocEnd.getSource() != null) {
                    String assocName = assocEnd.getName();
                    if (foundNames.contains(assocName)) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(classifier);
                        linkedObjects.add(assocName);
                        auditEntry.setLinkedInfos(linkedObjects);
            
                    } else if (!assocName.isEmpty()) {
                        foundNames.add(assocName);
                    }
                }
            }
            return auditEntry;
        }

        @objid ("6e45a0e3-6c8f-441f-ba31-f412f1033d59")
        private List<IAuditEntry> checkDown(final Generalization generalization, final List<Generalization> generalizations) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            if (generalizations.contains(generalization)) {
                return auditEntries;
            } else {
                generalizations.add(generalization);
            }
            
            NameSpace ns = generalization.getSubType();
            
            if (ns instanceof Classifier) {
                Classifier cl = (Classifier) ns;
                auditEntries.add(checkR1990(cl));
                for (Generalization gen : cl.getSpecialization()) {
                    auditEntries.addAll(checkDown(gen, generalizations));
                }
            }
            return auditEntries;
        }

        @objid ("072f1901-e5bd-426e-962b-9c7b007a6ce1")
        private void fetchNames(final Classifier classifier, final List<String> foundNames, final List<Classifier> classifiers) {
            if (classifiers.contains(classifier)) {
                return;
            } else {
                classifiers.add(classifier);
            }
            
            for (Attribute attribute : classifier.getOwnedAttribute(Attribute.class)) {
                foundNames.add(attribute.getName());
            }
            
            for (AssociationEnd assocEnd : classifier.getOwnedEnd(AssociationEnd.class)) {
                if (assocEnd.getSource() != null) {
                    foundNames.add(assocEnd.getName());
                }
            }
            
            for (Generalization gen : classifier.getParent()) {
                NameSpace ns = gen.getSuperType();
                if (ns instanceof Classifier) {
                    fetchNames((Classifier) ns, foundNames, classifiers);
                }
            }
            
        }

    }

}
