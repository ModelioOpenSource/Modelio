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
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ElementImportChecker checkUnicity
 */
@objid ("8c2556a7-7782-40ee-b70e-f9f2d9d57aec")
public class R1640 extends AbstractUmlRule {
    @objid ("aee9d3c3-8785-49d8-b26d-edf597a8f342")
    private static final String RULEID = "R1640";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("767368ec-5320-4e17-aef8-39e4f078b8dd")
    private CheckR1640 checkerInstance = null;

    @objid ("f2e4ae30-b9bc-4e1e-bfb1-d57d0a117a0a")
    @Override
    public String getRuleId() {
        return R1640.RULEID;
    }

    @objid ("54be420d-d54f-4e58-87dc-9cd1b9c69539")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(ElementImport.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
        // Namespace
        plan.registerRule(Package.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Collaboration.MQNAME, this, AuditTrigger.UPDATE);
        
        // Namespace.Classifier
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
    @objid ("3bd8f64e-40d7-4cc7-ba11-3e0567cf1532")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9602efd5-5b67-4272-850c-eca94d3b5417")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0efe04ff-8e02-4157-a61a-38b4e7c09cd9")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1640
     */
    @objid ("c2d39d65-0f2a-4b07-9706-ad30792e0c9f")
    public  R1640() {
        this.checkerInstance = new CheckR1640(this);
    }

    @objid ("0cf552c3-8053-45d0-85c7-b36cf8cba535")
    private static class CheckR1640 extends AbstractControl {
        @objid ("f93f7a61-60ae-45f2-99a7-822a1186d5e7")
        public  CheckR1640(IRule rule) {
            super(rule);
        }

        @objid ("0de83fe4-b89a-4c96-8fa8-a35d1b5bb234")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ElementImport) {
                diagnostic.addEntry(checkR1640((ElementImport) element));
            } else if (element instanceof Operation) {
                diagnostic.addEntry(checkR1640((Operation) element));
            } else if (element instanceof NameSpace) {
                diagnostic.addEntry(checkR1640((NameSpace) element));
            } else {
                UmlUi.LOG.warning("R1640: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("e7429418-de42-4d0e-8c15-dc3abd780284")
        private IAuditEntry checkR1640(final ElementImport elementImport) {
            NameSpace ns = elementImport.getImportingNameSpace();
            
            if (ns != null) {
                return checkR1640(ns);
            }
            
            Operation op = elementImport.getImportingOperation();
            
            if (op != null) {
                return checkR1640(op);
            }
            return new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, elementImport, null);
        }

        @objid ("1a0df899-2e1a-42a4-8896-3f1d70316c55")
        private IAuditEntry checkR1640(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    operation,
                    null);
            
            List<NameSpace> foundNS = new ArrayList<>();
            
            for (ElementImport ei : operation.getOwnedImport()) {
                NameSpace ns = ei.getImportedElement();
                if (foundNS.contains(ns)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(operation);
                    linkedObjects.add(Operation.MQNAME);
                    linkedObjects.add(ns);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                    return auditEntry;
                } else {
                    foundNS.add(ns);
                }
            }
            return auditEntry;
        }

        @objid ("05358870-14c1-4482-b917-291e7c740344")
        private IAuditEntry checkR1640(final NameSpace nameSpace) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    nameSpace,
                    null);
            
            List<NameSpace> foundNS = new ArrayList<>();
            
            for (ElementImport ei : nameSpace.getOwnedImport()) {
                NameSpace ns = ei.getImportedElement();
                if (foundNS.contains(ns)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(nameSpace);
                    linkedObjects.add(NameSpace.MQNAME);
                    linkedObjects.add(ns);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                    return auditEntry;
                } else {
                    foundNS.add(ns);
                }
            }
            return auditEntry;
        }

    }

}
