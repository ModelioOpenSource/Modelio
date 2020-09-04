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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ClassifierChecker checkAbstract
 */
@objid ("93003999-f4dd-4c6c-9e5a-d4ca5f16bdf0")
public class R1620 extends AbstractUmlRule {
    @objid ("6ff5c84d-5eb2-4675-99ba-a6c00af5606d")
    private static final String RULEID = "R1620";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("2d1af7d5-9cf9-4af3-b183-7a424ed656de")
    private CheckR1620 checkerInstance = null;

    @objid ("ce14e93e-68f3-42cd-b49b-d6aeacf95c91")
    @Override
    public String getRuleId() {
        return R1620.RULEID;
    }

    @objid ("d3620d9b-911c-4c07-b263-845403e19252")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // Classifier
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
        
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a723b887-9014-45f7-bb3c-5f5644ca586b")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("de9079e4-7e43-47c5-b29d-3ffabf51c43d")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("78f9c810-b317-464a-a65d-280940ce86bc")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1620
     */
    @objid ("f1d1262d-4115-49c9-9835-b8dde497bbde")
    public R1620() {
        this.checkerInstance = new CheckR1620(this);
    }

    @objid ("3f97656f-0744-4732-a8e0-8d6d17795672")
    private static class CheckR1620 extends AbstractControl {
        @objid ("5443772e-f1d9-4c48-bc82-678d941fe95e")
        public CheckR1620(IRule rule) {
            super(rule);
        }

        @objid ("ab28785b-9a55-4177-bde2-631875fd2c5a")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Classifier) {
                diagnostic.addEntry(checkR1620((Classifier) element));
            } else if (element instanceof Operation) {
                Classifier classifier = ((Operation) element).getOwner();
                if (classifier != null) {
                    diagnostic.addEntry(checkR1620(classifier));
                }
            } else {
                UmlUi.LOG.warning("R1620: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("e4a2c064-0d07-46cd-b7ee-8fa297b9d684")
        private IAuditEntry checkR1620(final Classifier classifier) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    classifier,
                    null);
            
            if (!classifier.isIsAbstract()) {
                for (Operation operation : classifier.getOwnedOperation()) {
                    if (operation.isIsAbstract()) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(classifier);
                        linkedObjects.add(operation);
                        auditEntry.setLinkedInfos(linkedObjects);
                    }
                }
            }
            return auditEntry;
        }

    }

}
