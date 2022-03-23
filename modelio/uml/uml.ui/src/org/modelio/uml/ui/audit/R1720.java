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
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: GeneralizationChecker checkGeneralizationAbstract warning
 */
@objid ("d09768a3-98a1-421b-9ebb-69a6351e30cf")
public class R1720 extends AbstractUmlRule {
    @objid ("fdceb400-1b82-4cfb-9eb9-df020f57d767")
    private static final String RULEID = "R1720";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("63cbee63-0cf3-41c1-99a7-81ca22cf8d35")
    private CheckR1720 checkerInstance = null;

    @objid ("99b84808-f691-40e6-a71b-278ccfc5e89e")
    @Override
    public String getRuleId() {
        return R1720.RULEID;
    }

    @objid ("5fa9bea1-117b-47ec-9eca-aa732ef53c51")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Generalization.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE |
                AuditTrigger.MOVE);
        
        // Namespaces
        plan.registerRule(Package.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Collaboration.MQNAME, this, AuditTrigger.UPDATE);
        
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
    @objid ("45d06fc4-5cbc-4e41-9df9-3b0e9a96daf0")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c52d1380-b1d3-46cb-b726-7c33434d772c")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("11051eb3-4a30-4244-8fde-2b15dea9af68")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1720
     */
    @objid ("d6fe4a59-4a48-4cc4-bdac-ff861197f17b")
    public  R1720() {
        this.checkerInstance = new CheckR1720(this);
    }

    @objid ("69cf6449-6bea-438b-8466-8c6e97283a48")
    private static class CheckR1720 extends AbstractControl {
        @objid ("4421616e-9cdb-41dc-a461-b02717be30e3")
        public  CheckR1720(IRule rule) {
            super(rule);
        }

        @objid ("2f297255-3f6e-4a2e-9394-f3732ea388fe")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Generalization) {
                diagnostic.addEntry(checkR1720(((Generalization) element).getSubType()));
            } else if (element instanceof NameSpace) {
            
                NameSpace nameSpace = (NameSpace) element;
            
                // Check the rule on the namespace itself
                diagnostic.addEntry(checkR1720(nameSpace));
            
                // Check the rule on every sub namespace in case the rule failed on one of them previously.
                for (Generalization gen : nameSpace.getSpecialization()) {
                    diagnostic.addEntry(checkR1720(gen.getSubType()));
                }
            } else {
                UmlUi.LOG.warning("R1720: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("3173e873-6f56-42a6-862b-a01f9f327693")
        private IAuditEntry checkR1720(final NameSpace nameSpace) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    nameSpace,
                    null);
            
            if (nameSpace.isIsAbstract()) {
            
                // Checking super namespaces are abstract
                for (Generalization gen : nameSpace.getParent()) {
            
                    NameSpace superNs = gen.getSuperType();
            
                    if (!superNs.isIsAbstract()) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(nameSpace);
                        linkedObjects.add(superNs);
                        auditEntry.setLinkedInfos(linkedObjects);
            
                    }
                }
            }
            return auditEntry;
        }

    }

}
