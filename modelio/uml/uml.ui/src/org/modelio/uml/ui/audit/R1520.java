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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
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
 * Rule implementation origin: BindableInstanceChecker checkClassifierPartNames
 */
@objid ("40544a7d-4e52-47fa-ba64-aa3c8745a937")
public class R1520 extends AbstractUmlRule {
    @objid ("d54bec9c-ffa5-449a-8fea-db27f674e91b")
    private static final String RULEID = "R1520";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("ef89c203-6037-465d-83ee-b67c6e238c90")
    private CheckR1520 checkerInstance = null;

    @objid ("aa5a37d0-f6e8-4a49-aac9-891adf844abb")
    @Override
    public String getRuleId() {
        return R1520.RULEID;
    }

    @objid ("6ad3e43e-53ca-403f-ba5e-84ab355084dc")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(BindableInstance.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
        
        // Namespaces.Classifiers
        plan.registerRule(InformationItem.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Artifact.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(TemplateParameter.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Actor.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Class.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(DataType.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Interface.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Signal.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(UseCase.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Node.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Component.MQNAME, this, AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3816aa7f-1c0e-4740-952d-c1253e13ccc0")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("645c8a0e-e17c-46e0-8dc1-fb41849878f4")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6d2b2e61-6ac4-44ed-a350-c5f449d2c93c")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1520
     */
    @objid ("35724dbb-a351-4e93-b2ed-5c7445366cc4")
    public  R1520() {
        this.checkerInstance = new CheckR1520(this);
    }

    @objid ("cdc80e2a-a2af-4b4e-9dae-5800ed3f75c5")
    private static class CheckR1520 extends AbstractControl {
        @objid ("65d0d216-362d-467e-97af-5848c1d1d819")
        public  CheckR1520(IRule rule) {
            super(rule);
        }

        @objid ("0ef2355c-a67c-4a89-a0f1-5a4e30359644")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Classifier) {
                // A Classifier was potentially updated after an element moved in, so we need to check the rule on this Classifier.
                diagnostic.addEntry(checkR1520((Classifier) element));
            } else if (element instanceof BindableInstance) {
                // A BindableInstance element was updated (potentially its name).
                // If it's owned by another Classifier, we need to check the rule is still valid on this Classifier.
                MObject owner = element.getCompositionOwner();
                if (owner != null && owner instanceof Classifier) {
                    diagnostic.addEntry(checkR1520((Classifier) owner));
                }
            } else {
                UmlUi.LOG.warning("R1780: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("1e3f08b9-cef7-43ba-9ff9-8887a580cca6")
        private IAuditEntry checkR1520(final Classifier classifier) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    classifier,
                    null);
            
            Map<String, List<BindableInstance>> duplicates = new HashMap<>();
            
            for (BindableInstance bi : classifier.getInternalStructure()) {
                String name = bi.getName();
                if (!duplicates.containsKey(name)) {
                    duplicates.put(name, new ArrayList<BindableInstance>());
                }
                duplicates.get(bi.getName()).add(bi);
            }
            
            for (Entry<String, List<BindableInstance>> entry : duplicates.entrySet()) {
                if (entry.getValue().size() > 1) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(classifier);
                    linkedObjects.add(entry.getKey());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
