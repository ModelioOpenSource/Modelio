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
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: NameChecker checkClassifierFeatures error
 */
@objid ("820197e2-1eb5-41ad-99d2-c710cc00681c")
public class R1980 extends AbstractUmlRule {
    @objid ("ba7c3f1b-4947-4e0e-abfd-db2bc48ff9ea")
    private static final String RULEID = "R1980";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("e1b2c886-29bf-4e58-b19c-25fc8aa1b023")
    private CheckR1980 checkerInstance = null;

    @objid ("cb6d08b8-8753-430d-ba95-85c030573cbf")
    @Override
    public String getRuleId() {
        return R1980.RULEID;
    }

    @objid ("49a52076-ee0a-42dd-b065-8d9ffcd5b802")
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
        
        plan.registerRule(Attribute.MQNAME, this, AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.MOVE | AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1b7483c2-a56e-429b-a004-e2de31335577")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ac376454-576b-4789-939f-324d424de286")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("4adabe6d-8fcd-495c-bf86-c6427e274481")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1980
     */
    @objid ("ed044804-4dac-4acf-a5a1-fea1d090ed31")
    public  R1980() {
        this.checkerInstance = new CheckR1980(this);
    }

    @objid ("649a482a-8ccd-46d1-a3b7-596356b20072")
    private static class CheckR1980 extends AbstractControl {
        @objid ("b97784f6-9b07-4ace-9e65-15da1b9cf293")
        public  CheckR1980(IRule rule) {
            super(rule);
        }

        @objid ("d318b9f4-8a9e-4ea4-9e23-3d787ea53b96")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Classifier) {
                diagnostic.addEntry(checkR1980((Classifier) element));
            } else if (element instanceof Attribute) {
                MObject owner = ((Attribute) element).getCompositionOwner();
                if (owner instanceof Classifier) {
                    diagnostic.addEntry(checkR1980((Classifier) owner));
                }
            } else if (element instanceof AssociationEnd) {
            
                AssociationEnd end = (AssociationEnd) element;
                Classifier owner = end.getSource() != null ? end.getSource() : end.getOpposite().getTarget();
                diagnostic.addEntry(checkR1980(owner));
            } else {
                UmlUi.LOG.warning("R1980: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("725f5258-de05-4874-b57f-9fcd0647c29f")
        private IAuditEntry checkR1980(final Classifier classifier) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    classifier,
                    null);
            
            List<String> foundNames = new ArrayList<>();
            
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

    }

}
